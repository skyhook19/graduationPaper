package com.sagaleev.controllers;

import com.sagaleev.domain.dto.AmbulanceCallStatsDto;
import com.sagaleev.domain.dtoConverter.AmbulanceCallStatsConverter;
import com.sagaleev.domain.model.AmbulanceCallStats;
import com.sagaleev.domain.model.WeatherStats;
import com.sagaleev.service.AmbulanceCallStatsService;
import com.sagaleev.service.WeatherStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Controller
public class PearsonСorrelationСoefficientController {

    private final AmbulanceCallStatsService ambulanceCallStatsService;
    private final WeatherStatsService weatherStatsService;

    @Autowired
    public PearsonСorrelationСoefficientController(AmbulanceCallStatsService ambulanceCallStatsService, WeatherStatsService weatherStatsService) {
        this.ambulanceCallStatsService = ambulanceCallStatsService;
        this.weatherStatsService = weatherStatsService;
    }

    @GetMapping(value = "/pearsonCorrelation")
    public String getCorrelations(Model model){
        List<Double[]> correlations = getCorrelationValues();
        Map<String, Double[]> map = new HashMap<String, Double[]>(){{
            put("Температура", correlations.get(0));
            put("Атмосферное давление", correlations.get(1));
            put("Относительная влажность", correlations.get(2));
            put("Скорость ветра", correlations.get(3));
            put("Облачность", correlations.get(4));
            put("Мин. температура", correlations.get(5));
            put("Макс. температура", correlations.get(6));
            put("Количество осадков", correlations.get(7));
        }};
        model.addAttribute("map", map);

        return "pearsonCorrelation";
    }

    private List<Double[]> getCorrelationValues(){
        List<Double[]> correlations = new ArrayList<>(8);

        double[][] allWeatherValues = getWeatherValuesSplittedByWeatherCondition();
        double[][] allAmbulanceCallsValues = getAmbulanceCallsValuesSplittedByDisease();

        for (int i = 0; i < allWeatherValues.length; i++) {
            Double[] correlationsBetweenOneWeatherConditionAndAllDiseases = new Double[allAmbulanceCallsValues.length];

            for (int j = 0; j < allAmbulanceCallsValues.length; j++) {
                double[] concreteWeatherValues = allWeatherValues[i];
                double[] concreteDiseaseAmbulanceCallsValues = allAmbulanceCallsValues[j];

                double weatherAvg = getAvg(concreteWeatherValues);
                double ambCallsAvg = getAvg(concreteDiseaseAmbulanceCallsValues);

                double[] weatherDeltaArr = getDeltaArray(concreteWeatherValues, weatherAvg);
                double[] ambCallsDeltaArr = getDeltaArray(concreteDiseaseAmbulanceCallsValues, ambCallsAvg);

                double[] multArr = multiplArrays(weatherDeltaArr, ambCallsDeltaArr);

                double sum = getSumOfArrayElems(multArr);

                double[] squaredWeatherDeltaArr = squareArr(weatherDeltaArr);
                double[] squaredAmbCallsDeltaArr = squareArr(ambCallsDeltaArr);

                double sumOfSquaredWeatherDeltaArr = getSumOfArrayElems(squaredWeatherDeltaArr);
                double sumOfSquaredAmbCallsDeltaArr = getSumOfArrayElems(squaredAmbCallsDeltaArr);

                double multOfSums = sumOfSquaredWeatherDeltaArr * sumOfSquaredAmbCallsDeltaArr;
                double sqRootOfMult = Math.sqrt(multOfSums);

                double result = sum / sqRootOfMult;
                double newDouble = new BigDecimal(result).setScale(3, RoundingMode.UP).doubleValue();

                correlationsBetweenOneWeatherConditionAndAllDiseases[j] = newDouble;
            }

            correlations.add(correlationsBetweenOneWeatherConditionAndAllDiseases);
        }

        return correlations;
    }

    private double[][] getWeatherValuesSplittedByWeatherCondition(){
        List<WeatherStats> allWeatherStats = weatherStatsService.getAll();
        double[] allTempValues = new double[allWeatherStats.size()];
        double[] allPressValues = new double[allWeatherStats.size()];
        double[] allHumidValues = new double[allWeatherStats.size()];
        double[] allWindSpeedValues = new double[allWeatherStats.size()];
        double[] allCloudinessValues = new double[allWeatherStats.size()];
        double[] allMinTemperatureValues = new double[allWeatherStats.size()];
        double[] allMaxTemperatureValues = new double[allWeatherStats.size()];
        double[] allDownfallValues = new double[allWeatherStats.size()];
        for (int i = 0; i < allWeatherStats.size(); i++) {
            allTempValues[i] = allWeatherStats.get(i).getAvgTemperature();
            allPressValues[i] = allWeatherStats.get(i).getAtmospherePressure();
            allHumidValues[i] = allWeatherStats.get(i).getHumidity();
            allWindSpeedValues[i] = allWeatherStats.get(i).getWindSpeed();
            allCloudinessValues[i] = allWeatherStats.get(i).getCloudiness();
            allMinTemperatureValues[i] = allWeatherStats.get(i).getMinTemperature();
            allMaxTemperatureValues[i] = allWeatherStats.get(i).getMaxTemperature();
            allDownfallValues[i] = allWeatherStats.get(i).getDownfall();
        }

        return new double[][]{allTempValues, allPressValues, allHumidValues, allWindSpeedValues,
                allCloudinessValues, allMinTemperatureValues, allMaxTemperatureValues, allDownfallValues};
    }

    private double[][] getAmbulanceCallsValuesSplittedByDisease(){
        List<AmbulanceCallStats> stats = ambulanceCallStatsService.getAll();
        List<AmbulanceCallStatsDto> dtos = new ArrayList<>(stats.size() / 15);

        Comparator<AmbulanceCallStats> comparator = Comparator.comparing(AmbulanceCallStats::getYearMonth);
        comparator.thenComparing(Comparator.comparing(AmbulanceCallStats::getDisease));
        stats.sort(comparator);

        for (int i = 0, k = 0; k < stats.size() / 15; k++) {
            List<AmbulanceCallStats> statsToConvert = new ArrayList<>(15);
            for (int j = 0; j < 15; i++, j++) {
                statsToConvert.add(stats.get(i));
            }
            dtos.add(AmbulanceCallStatsConverter.convertListForOneMonthToOneDto(statsToConvert));
        }

        double[] allAcuteRespiratoryViralInfectionValues = new double[dtos.size()];
        double[] allMyocardialInfarctionValues = new double[dtos.size()];
        double[] allMyocardialInfarctionWithHospitalizationValues = new double[dtos.size()];
        double[] allFatalMyocardialInfarctionValues = new double[dtos.size()];
        double[] allAnginaPectorisValues = new double[dtos.size()];
        double[] allArrhythmiaValues = new double[dtos.size()];
        double[] allCardioVascularDiseaseValues = new double[dtos.size()];
        double[] allFatalCardioVascularDiseaseValues = new double[dtos.size()];
        double[] allStrokeValues = new double[dtos.size()];
        double[] allFatalStrokeValues = new double[dtos.size()];
        double[] allPneumoniaValues = new double[dtos.size()];
        double[] allBronchialAsthmaValues = new double[dtos.size()];
        double[] allBronchitisValues = new double[dtos.size()];
        double[] allPepticUlcerDiseaseValues = new double[dtos.size()];
        double[] allGastritisValues = new double[dtos.size()];

        for (int i = 0; i < dtos.size(); i++) {
            allAcuteRespiratoryViralInfectionValues[i] = dtos.get(i).getAcute_respiratory_viral_infection();
            allMyocardialInfarctionValues[i] = dtos.get(i).getMyocardial_infarction();
            allMyocardialInfarctionWithHospitalizationValues[i] = dtos.get(i).getMyocardial_infarction_with_hospitalization();
            allFatalMyocardialInfarctionValues[i] = dtos.get(i).getFatal_myocardial_infarction();
            allAnginaPectorisValues[i] = dtos.get(i).getAngina_pectoris();
            allArrhythmiaValues[i] = dtos.get(i).getArrhythmia();
            allCardioVascularDiseaseValues[i] = dtos.get(i).getCardio_vascular_disease();
            allFatalCardioVascularDiseaseValues[i] = dtos.get(i).getFatal_cardio_vascular_disease();
            allStrokeValues[i] = dtos.get(i).getStroke();
            allFatalStrokeValues[i] = dtos.get(i).getFatal_stroke();
            allPneumoniaValues[i] = dtos.get(i).getPneumonia();
            allBronchialAsthmaValues[i] = dtos.get(i).getBronchial_asthma();
            allBronchitisValues[i] = dtos.get(i).getBronchitis();
            allPepticUlcerDiseaseValues[i] = dtos.get(i).getPeptic_ulcer_disease();
            allGastritisValues[i] = dtos.get(i).getGastritis();
        }

        return new double[][]{allAcuteRespiratoryViralInfectionValues, allMyocardialInfarctionValues,
                allMyocardialInfarctionWithHospitalizationValues, allFatalMyocardialInfarctionValues,
                allAnginaPectorisValues, allArrhythmiaValues, allCardioVascularDiseaseValues,
                allFatalCardioVascularDiseaseValues, allStrokeValues, allFatalStrokeValues, allPneumoniaValues,
                allBronchialAsthmaValues, allBronchitisValues, allPepticUlcerDiseaseValues, allGastritisValues};
    }

    private double getAvg(double[] arr){
        double sum = 0;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        return sum / arr.length;
    }

    private double[] getDeltaArray(double[] arr, double avg){
        double[] deltaArray = new double[arr.length];

        for (int i = 0; i < arr.length; i++) {
            deltaArray[i] = arr[i] - avg;
        }

        return deltaArray;
    }

    private double[] multiplArrays(double[] arr, double[] arr1){
        double[] res = new double[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i] * arr1[i];
        }
        return res;
    }

    private double getSumOfArrayElems(double[] arr){
        double sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }

    private double[] squareArr(double[] arr){
        double[] squared = new double[arr.length];
        for (int i = 0; i < arr.length; i++) {
            squared[i] = arr[i] * arr[i];
        }
        return squared;
    }
}
