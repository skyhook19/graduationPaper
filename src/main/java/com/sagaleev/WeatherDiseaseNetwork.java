package com.sagaleev;

import com.sagaleev.domain.model.AmbulanceCallStats;
import com.sagaleev.domain.model.WeatherStats;
import com.sagaleev.service.AmbulanceCallStatsService;
import com.sagaleev.service.WeatherStatsService;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
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

    @Autowired
    public WeatherDiseaseNetwork(WeatherStatsService weatherStatsService, AmbulanceCallStatsService ambulanceCallStatsService) {
        this.weatherStatsService = weatherStatsService;
        this.ambulanceCallStatsService = ambulanceCallStatsService;
    }


    public double[] computeNetwork(double[][] weatherInput) {
        BasicNetwork network = train();

 /*       double[][] normalizedWeatherInput = normalizeArray(weatherInput);
        double[][] ideal = null;

        MLDataSet set = new BasicMLDataSet(normalizedWeatherInput, ideal);

        System.out.println("Neural Network Results: ");*/

        double[] result = new double[15];

        /*for (MLDataPair pair : set) {
            final MLData output = network.compute(pair.getInput());

            System.out.print("Weather data input: ");
            for (int i = 0; i < pair.getInputArray().length; i++) {
                System.out.print(", " + denormalize(pair.getInputArray()[i]));
            }
            System.out.println();

            System.out.print("Ambulance call data result: ");
            for (int i = 0; i < output.getData().length; i++) {
                double outputValue = output.getData(i);
                System.out.print(", " + denormalize(outputValue));
                result[i] = denormalize(outputValue);
            }
        }*/

        return result;
    }

    private BasicNetwork train() {
        double[][] weatherDataSet = getTrainingWeatherDataSet();
        double[][] ambulanceCallDataSet = getTrainingAmbulanceCallsDataSet();
        printDataArray(weatherDataSet, "weather data");
        printDataArray(ambulanceCallDataSet, "ambulanceCallResultArray");

        double[][] normalizedWeatherDataSet = new double[weatherDataSet.length][];
        for (int i = 0; i < weatherDataSet.length; i++) {
            normalizedWeatherDataSet[i] = normalizeArray(weatherDataSet[i]);
        }

        double[][] normalizedAmbulanceCallDataSet = new double[ambulanceCallDataSet.length][];
        for (int i = 0; i < ambulanceCallDataSet.length; i++) {
            normalizedAmbulanceCallDataSet[i] = normalizeArray(ambulanceCallDataSet[i]);
        }

        MLDataSet trainingSet = new BasicMLDataSet(normalizedWeatherDataSet, normalizedAmbulanceCallDataSet);

        BasicNetwork network = new BasicNetwork();
        network.addLayer(new BasicLayer(null, true, 8));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 16));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 32));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 64));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 32));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), false, 15));
        network.getStructure().finalizeStructure();

        MLTrain train = new ResilientPropagation(network, trainingSet);

        //обучение, до тех пор пока ошибка не станет меньше n
        //TODO разбить dataSet на данные для обучения(2011-2014) и данные для проверки(2015) и вычислять еще ActualError
        int epoch = 1;
        do {
            train.iteration();
            System.out.println("Epoch #" + epoch + "Error:" + train.getError());
            epoch++;
        } while (train.getError() > 0.01);

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
        double[][] result = new double[ambulanceCallStats.size()/15][];

        Comparator<AmbulanceCallStats> comparator = Comparator.comparing(AmbulanceCallStats::getYearMonth);
        comparator = comparator.thenComparing(Comparator.comparing(AmbulanceCallStats::getDisease));
        ambulanceCallStats.sort(comparator);

        for (int i = 0, k = 0; k < ambulanceCallStats.size()/15; k++) {
            double[] ambulanceCallValues = new double[15];
            for (int j = 0; j < 15 ; i++, j++) {
                ambulanceCallValues[j] = ambulanceCallStats.get(i).getCount();
            }
            result[k] = ambulanceCallValues;
        }

        return result;
    }

    private void printDataArray(double[][] dataArray, String name){
        for (int i = 0; i < dataArray.length; i++) {
            System.out.print(name + " [" + i + "]: ");
            for (int j = 0; j < dataArray[i].length; j++) {
                System.out.print(dataArray[i][j] + ", ");
            }
            System.out.println();
        }

    }

    private double[] normalizeArray(double[] arr){
        double[] normalizedInputArray = new double[arr.length];
        double min = getMinInArray(arr);
        double max = getMaxInArray(arr);

        for (int i = 0; i < arr.length; i++) {
            normalizedInputArray[i] = normalize(max, min, arr[i]);
        }
        return normalizedInputArray;
    }

    private double[] denormalizeArray(double[] arr){
        double[] denormalizedInputArray = new double[arr.length];
        double min = getMinInArray(arr);
        double max = getMaxInArray(arr);

        for (int i = 0; i < arr.length; i++) {
            denormalizedInputArray[i] = denormalize(max, min, arr[i]);
        }
        return denormalizedInputArray;
    }

    private double normalize(double actualHigh, double actualLow, double valueToNormalize) {
        NormalizedField normalizedField = new NormalizedField(NormalizationAction.Normalize, "normalize", actualHigh, actualLow, NORMALIZED_HIGH, NORMALIZED_LOW);
        return normalizedField.normalize(valueToNormalize);
    }

    private double denormalize(double actualHigh, double actualLow, double normalizedValue) {
        NormalizedField normalizedField = new NormalizedField(NormalizationAction.Normalize, "denormalize", actualHigh, actualLow, NORMALIZED_HIGH, NORMALIZED_LOW);
        return normalizedField.deNormalize(normalizedValue);
    }

    private double getMaxInArray(double[] arr){
        double max = arr[0];
        for (int i = 1; i < arr.length; i++){
            if(arr[i] > max) max = arr[i];
        }
        return max;
    }

    private double getMinInArray(double[] arr){
        double min = arr[0];
        for (int i = 1; i < arr.length; i++){
            if(arr[i] < min) min = arr[i];
        }
        return min;
    }

    //TODO что еще попробовать: попробовать передавать в качестве actualHigh = max * 1.25
}



















