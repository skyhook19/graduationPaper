package com.sagaleev.controllers.rest;

import com.sagaleev.domain.model.AmbulanceCallStats;
import com.sagaleev.domain.model.Disease;
import com.sagaleev.domain.model.NeuralNetwork;
import com.sagaleev.domain.model.WeatherStats;
import com.sagaleev.service.AmbulanceCallStatsService;
import com.sagaleev.service.NeuralNetworkTrainerService;
import com.sagaleev.service.WeatherStatsService;
import org.encog.ml.data.MLData;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.util.arrayutil.NormalizationAction;
import org.encog.util.arrayutil.NormalizedField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
public class RestNeuralNetworkController {

    private final AmbulanceCallStatsService ambulanceCallStatsService;
    private final WeatherStatsService weatherStatsService;
    private final NeuralNetworkTrainerService neuralNetworkTrainerService;

    @Autowired
    public RestNeuralNetworkController(AmbulanceCallStatsService ambulanceCallStatsService, WeatherStatsService weatherStatsService, NeuralNetworkTrainerService neuralNetworkTrainerService) {
        this.ambulanceCallStatsService = ambulanceCallStatsService;
        this.weatherStatsService = weatherStatsService;
        this.neuralNetworkTrainerService = neuralNetworkTrainerService;
    }

    @PostMapping("/getResultsOfNetworkWork")
    public double[] getResultsOfNetworkWork() {
        List<NeuralNetwork> networks = getAllNetworks();

        double[] weatherInput = new double[]{-14.2, 764.7, 79.6, 4.7, 44.53, -26.1, 13.5, 56.6};
        double[] result = new double[1];

        result[0] = getResultOfNetworkWork(weatherInput, networks.get(0));

/*        for (int i = 0; i < networks.size(); i++) {
            result[i] = getResultOfNetworkWork(weatherInput, networks.get(i));
        }*/

        return result;
    }

    public double getResultOfNetworkWork(double[] weatherInput, NeuralNetwork neuralNetwork) {
        neuralNetworkTrainerService.train(neuralNetwork, getTrainingWeatherDataSet(), getTrainingAmbulanceCallsDataSet(neuralNetwork.getDisease()));

        double[] normalizedWeatherInput = new double[weatherInput.length];
        for (int i = 0; i < weatherInput.length; i++) {
            normalizedWeatherInput[i] = normalize(neuralNetwork.getActualHighForInput(), neuralNetwork.getActualLowForInput(), weatherInput[i]);
        }

        MLData inputData = new BasicMLData(normalizedWeatherInput);
        final MLData output = neuralNetwork.getNetwork().compute(inputData);

        return denormalize(neuralNetwork.getActualHighForOutput(), neuralNetwork.getActualLowForOutput(), output.getData(0));
    }

    private double[][] getTrainingAmbulanceCallsDataSet(Disease disease) {
        List<AmbulanceCallStats> ambulanceCallStats = ambulanceCallStatsService.getAmbulanceCallStatsByYearAndDisease(2011, disease);
        ambulanceCallStats.addAll(ambulanceCallStatsService.getAmbulanceCallStatsByYearAndDisease(2012, disease));
        ambulanceCallStats.addAll(ambulanceCallStatsService.getAmbulanceCallStatsByYearAndDisease(2013, disease));
        ambulanceCallStats.addAll(ambulanceCallStatsService.getAmbulanceCallStatsByYearAndDisease(2014, disease));
        double[][] result = new double[ambulanceCallStats.size()][];

        Comparator<AmbulanceCallStats> comparator = Comparator.comparing(AmbulanceCallStats::getYearMonth);
        ambulanceCallStats.sort(comparator);

        for (int i = 0; i < ambulanceCallStats.size(); i++) {
            result[i] = new double[]{ambulanceCallStats.get(i).getCount()};
        }

        return result;
    }

    private double[][] getTrainingWeatherDataSet() {
        List<WeatherStats> weatherStats = weatherStatsService.getByYear(2011);
        weatherStats.addAll(weatherStatsService.getByYear(2012));
        weatherStats.addAll(weatherStatsService.getByYear(2013));
        weatherStats.addAll(weatherStatsService.getByYear(2014));
        double[][] result = new double[weatherStats.size()][];

        weatherStats.sort(Comparator.comparing(WeatherStats::getYearMonth));

        for (int i = 0; i < weatherStats.size(); i++) {
            double[] weatherValues = new double[8];
            weatherValues[0] = weatherStats.get(i).getAvgTemperature();
            weatherValues[1] = weatherStats.get(i).getAtmospherePressure();
            weatherValues[2] = weatherStats.get(i).getHumidity();
            weatherValues[3] = weatherStats.get(i).getWindSpeed();
            weatherValues[4] = weatherStats.get(i).getCloudiness();
            weatherValues[5] = weatherStats.get(i).getMinTemperature();
            weatherValues[6] = weatherStats.get(i).getMaxTemperature();
            weatherValues[7] = weatherStats.get(i).getDownfall();
            result[i] = weatherValues;
        }

        return result;
    }

    private double denormalize(double actualHigh, double actualLow, double normalizedValue) {
        NormalizedField normalizedField = new NormalizedField(NormalizationAction.Normalize, "denormalize",
                actualHigh, actualLow, NeuralNetwork.NORMALIZED_HIGH, NeuralNetwork.NORMALIZED_LOW);
        return normalizedField.deNormalize(normalizedValue);
    }

    private double normalize(double actualHigh, double actualLow, double valueToNormalize) {
        NormalizedField normalizedField = new NormalizedField(NormalizationAction.Normalize, "normalize",
                actualHigh, actualLow, NeuralNetwork.NORMALIZED_HIGH, NeuralNetwork.NORMALIZED_LOW);
        return normalizedField.normalize(valueToNormalize);
    }

    private List<NeuralNetwork> getAllNetworks() {
        List<NeuralNetwork> networks = new ArrayList<>();

        networks.add(new NeuralNetwork(Disease.ACUTE_RESPIRATORY_VIRAL_INFECTION));
        networks.add(new NeuralNetwork(Disease.MYOCARDIAL_INFARCTION));
        networks.add(new NeuralNetwork(Disease.MYOCARDIAL_INFARCTION_WITH_HOSPITALIZATION));
        networks.add(new NeuralNetwork(Disease.FATAL_MYOCARDIAL_INFARCTION));
        networks.add(new NeuralNetwork(Disease.ANGINA_PECTORIS));
        networks.add(new NeuralNetwork(Disease.ARRHYTHMIA));
        networks.add(new NeuralNetwork(Disease.CARDIO_VASCULAR_DISEASE));
        networks.add(new NeuralNetwork(Disease.FATAL_CARDIO_VASCULAR_DISEASE));
        networks.add(new NeuralNetwork(Disease.STROKE));
        networks.add(new NeuralNetwork(Disease.FATAL_STROKE));
        networks.add(new NeuralNetwork(Disease.PNEUMONIA));
        networks.add(new NeuralNetwork(Disease.BRONCHIAL_ASTHMA));
        networks.add(new NeuralNetwork(Disease.BRONCHITIS));
        networks.add(new NeuralNetwork(Disease.PEPTIC_ULCER_DISEASE));
        networks.add(new NeuralNetwork(Disease.GASTRITIS));

        return networks;
    }
}
