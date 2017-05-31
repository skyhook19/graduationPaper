package com.sagaleev.controllers.rest;

import com.sagaleev.domain.model.AmbulanceCallStats;
import com.sagaleev.domain.model.NetworkHelper;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController
public class RestNeuralNetworkController {

    private final AmbulanceCallStatsService ambulanceCallStatsService;
    private final WeatherStatsService weatherStatsService;
    private final NeuralNetworkTrainerService neuralNetworkTrainerService;
    private final NeuralNetwork network;

    @Autowired
    public RestNeuralNetworkController(AmbulanceCallStatsService ambulanceCallStatsService, WeatherStatsService weatherStatsService, NeuralNetworkTrainerService neuralNetworkTrainerService) {
        this.ambulanceCallStatsService = ambulanceCallStatsService;
        this.weatherStatsService = weatherStatsService;
        this.neuralNetworkTrainerService = neuralNetworkTrainerService;
        network = new NeuralNetwork();
    }

    @PostMapping("/getResultsOfNetworkWork")
    public int[] getResultsOfNetworkWork(@RequestBody double[] weatherInput) {
        System.out.println("im here!");

        System.out.print("weatherInput = [");
        for (int i = 0; i < weatherInput.length; i++) {
            System.out.print(weatherInput[i] + ", ");
        }
        System.out.println("]");

        double[] output = getResultOfNetworkWork(weatherInput, network);
        double[] denormalizedResult = new double[output.length];

        for (int i = 0; i < output.length; i++) {
            denormalizedResult[i] = denormalize(NetworkHelper.MAXIMUMS_FOR_AMBULANCE_CALLS.get(i),
                    NetworkHelper.MINIMUMS_FOR_AMBULANCE_CALLS.get(i), output[i]);
        }

        int[] finalResult = new int[denormalizedResult.length];

        for (int i = 0; i < denormalizedResult.length; i++) {
            Long l = Math.round(denormalizedResult[i]);
            finalResult[i] = l.intValue();
        }

        return finalResult;
    }

    private double[] getResultOfNetworkWork(double[] weatherInput, NeuralNetwork neuralNetwork) {
        neuralNetworkTrainerService.train(neuralNetwork, getTrainingWeatherDataSet(), getTrainingAmbulanceCallsDataSet());

        double[] normalizedWeatherInput = new double[weatherInput.length];
        for (int i = 0; i < weatherInput.length; i++) {
            normalizedWeatherInput[i] = normalize(NetworkHelper.MAXIMUMS_FOR_WEATHER.get(i), NetworkHelper.MINIMUMS_FOR_WEATHER.get(i), weatherInput[i]);
        }

        MLData inputData = new BasicMLData(normalizedWeatherInput);
        final MLData output = neuralNetwork.getNetwork().compute(inputData);

        return output.getData();
    }

    private double[][] getTrainingWeatherDataSet() {
        List<WeatherStats> weatherStats = weatherStatsService.getAll();
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

    private double[][] getTrainingAmbulanceCallsDataSet() {
        List<AmbulanceCallStats> ambulanceCallStats = ambulanceCallStatsService.getAll();
        double[][] result = new double[ambulanceCallStats.size() / 15][];

        Comparator<AmbulanceCallStats> comparator = Comparator.comparing(AmbulanceCallStats::getYearMonth);
        comparator.thenComparing(Comparator.comparing(AmbulanceCallStats::getDisease));
        ambulanceCallStats.sort(comparator);

        for (int i = 0, k = 0; k < ambulanceCallStats.size() / 15; k++) {
            double[] ambulanceCallValues = new double[15];
            for (int j = 0; j < 15; i++, j++) {
                ambulanceCallValues[j] = ambulanceCallStats.get(i).getCount();
            }
            result[k] = ambulanceCallValues;
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
}
