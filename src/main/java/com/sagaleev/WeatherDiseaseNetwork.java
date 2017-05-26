package com.sagaleev;

import com.sagaleev.domain.model.AmbulanceCallStats;
import com.sagaleev.domain.model.WeatherStats;
import com.sagaleev.service.AmbulanceCallStatsService;
import com.sagaleev.service.WeatherStatsService;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.data.basic.BasicMLDataPair;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.ml.train.MLTrain;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;
import org.encog.util.arrayutil.NormalizationAction;
import org.encog.util.arrayutil.NormalizedField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class WeatherDiseaseNetwork {
    private final WeatherStatsService weatherStatsService;
    private final AmbulanceCallStatsService ambulanceCallStatsService;

    private static final int NORMALIZED_HIGH = 1;
    private static final int NORMALIZED_LOW = 0;
    private static final double ACTUAL_HIGH_FOR_AMBULANCE_CALLS = 2925;
    private static final double ACTUAL_LOW_FOR_AMBULANCE_CALLS = 0;
    private static final double ACTUAL_HIGH_FOR_WEATHER_INPUT = 772.7;
    private static final double ACTUAL_LOW_FOR_WEATHER_INPUT = -29.6;

    @Autowired
    public WeatherDiseaseNetwork(WeatherStatsService weatherStatsService, AmbulanceCallStatsService ambulanceCallStatsService) {
        this.weatherStatsService = weatherStatsService;
        this.ambulanceCallStatsService = ambulanceCallStatsService;
    }

    public double[] computeNetwork(double[] weatherInput) {
        BasicNetwork network = train();
        double[] normalizedWeatherInput = new double[weatherInput.length];
        for (int i = 0; i < weatherInput.length; i++) {
            normalizedWeatherInput[i] = normalize(ACTUAL_HIGH_FOR_WEATHER_INPUT, ACTUAL_LOW_FOR_WEATHER_INPUT, weatherInput[i]);
        }

        MLData inputData = new BasicMLData(normalizedWeatherInput);
        final MLData output = network.compute(inputData);

        double[] result = new double[output.getData().length];
        for (int i = 0; i < output.getData().length; i++) {
            result[i] = denormalize(ACTUAL_HIGH_FOR_AMBULANCE_CALLS, ACTUAL_LOW_FOR_AMBULANCE_CALLS, output.getData(i));
        }

        return result;
    }

    private BasicNetwork train() {
        double[][] weatherDataSet = getTrainingWeatherDataSet();
        double[][] ambulanceCallDataSet = getTrainingAmbulanceCallsDataSet();

        printDataArray(weatherDataSet, "weather data");
        printDataArray(ambulanceCallDataSet, "ambulanceCallResultArray");

        double[][] normalizedWeatherDataSet = normalizeArray(weatherDataSet);
        double[][] normalizedAmbulanceCallDataSet = normalizeArray(ambulanceCallDataSet);

        MLDataSet trainingSet = new BasicMLDataSet(normalizedWeatherDataSet, normalizedAmbulanceCallDataSet);

        BasicNetwork network = new BasicNetwork();
        network.addLayer(new BasicLayer(null, true, 8));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 32));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 64));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 32));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), false, 15));
        network.getStructure().finalizeStructure();

        MLTrain train = new ResilientPropagation(network, trainingSet);

        //TODO разбить dataSet на данные для обучения(2011-2014) и данные для проверки(2015) и вычислять еще ActualError
        int epoch = 1;
        do {
            train.iteration();
            System.out.println("Epoch #" + epoch + "Error:" + train.getError());
            epoch++;
        } while (train.getError() > 0.005);

        return network;
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
        comparator = comparator.thenComparing(Comparator.comparing(AmbulanceCallStats::getDisease));
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

    private void printDataArray(double[][] dataArray, String name) {
        for (int i = 0; i < dataArray.length; i++) {
            System.out.print(name + " [" + i + "]: ");
            for (int j = 0; j < dataArray[i].length; j++) {
                System.out.print(dataArray[i][j] + ", ");
            }
            System.out.println();
        }

    }

    private double[][] normalizeArray(double[][] input) {
        double[][] result = new double[input.length][input[0].length];
        double min = getMinInArray(input);
        double max = getMaxInArray(input);

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                result[i][j] = normalize(max, min, input[i][j]);
            }
        }
        return result;
    }

    private double normalize(double actualHigh, double actualLow, double valueToNormalize) {
        NormalizedField normalizedField = new NormalizedField(NormalizationAction.Normalize, "normalize", actualHigh, actualLow, NORMALIZED_HIGH, NORMALIZED_LOW);
        return normalizedField.normalize(valueToNormalize);
    }

    private double denormalize(double actualHigh, double actualLow, double normalizedValue) {
        NormalizedField normalizedField = new NormalizedField(NormalizationAction.Normalize, "denormalize", actualHigh, actualLow, NORMALIZED_HIGH, NORMALIZED_LOW);
        return normalizedField.deNormalize(normalizedValue);
    }

    private double getMaxInArray(double[][] arr) {
        double max = arr[0][0];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] > max) max = arr[i][j];
            }
        }
        return max;
    }

    private double getMinInArray(double[][] arr) {
        double min = arr[0][0];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] < min) min = arr[i][j];
            }
        }
        return min;
    }

    //TODO что еще попробовать: попробовать передавать в качестве actualHigh = max * 1.25
}



















