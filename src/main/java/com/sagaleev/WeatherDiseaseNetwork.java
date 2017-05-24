package com.sagaleev;

import com.sagaleev.domain.model.AmbulanceCallStats;
import com.sagaleev.domain.model.WeatherStats;
import com.sagaleev.service.AmbulanceCallStatsService;
import com.sagaleev.service.WeatherStatsService;
import org.apache.commons.lang3.ArrayUtils;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.ml.train.MLTrain;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;
import org.encog.util.arrayutil.NormalizedField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class WeatherDiseaseNetwork {
    private final WeatherStatsService weatherStatsService;
    private final AmbulanceCallStatsService ambulanceCallStatsService;

    @Autowired
    public WeatherDiseaseNetwork(WeatherStatsService weatherStatsService, AmbulanceCallStatsService ambulanceCallStatsService) {
        this.weatherStatsService = weatherStatsService;
        this.ambulanceCallStatsService = ambulanceCallStatsService;
    }

    public double[] computeNetwork(double[][] weatherInput) {
        BasicNetwork network = train();

        double[][] normalizedWeatherInput = new double[weatherInput.length][weatherInput[0].length];

        for (int i = 0; i < weatherInput.length; i++) {
            for (int j = 0; j < weatherInput[i].length; j++) {
                normalizedWeatherInput[i][j] = normalize(weatherInput[i][j]);
            }
        }

        double[][] ideal = null;


        MLDataSet set = new BasicMLDataSet(weatherInput, ideal);

        System.out.println("Neural Network Results: ");

        double[] result = new double[15];

        for (MLDataPair pair : set) {
            final MLData output = network.compute(pair.getInput());

            System.out.print("Weather data input: ");
            for (int i = 0; i < pair.getInputArray().length; i++) {
                System.out.print(", " + pair.getInputArray()[i]);
            }

            System.out.println();

            System.out.print("Ambulance call data result: ");

            for (int i = 0; i < output.getData().length; i++) {
                double normalizedOutputValue = output.getData()[i];

                System.out.print(", " + denormalize(normalizedOutputValue));
                result[i] = normalizedOutputValue;
            }
        }

        return result;
    }

    private double denormalize(double normalizedValue) {
        NormalizedField normalizedField = new NormalizedField(1.0, -1.0);
        return normalizedField.deNormalize(normalizedValue);
    }

    private double normalize(double value) {
        NormalizedField normalizedField = new NormalizedField(1.0, -1.0);
        return normalizedField.normalize(value);
    }

    private BasicNetwork train() {

        List<Double[]> weatherInput = getWeatherData();
        Double[][] weatherArray = new Double[weatherInput.size()][];
        for (int a = 0; a < weatherInput.size(); a++) {
            weatherArray[a] = weatherInput.get(a);
        }
        double[][] weatherInputArray = new double[weatherArray.length][];
        for (int i = 0; i < weatherArray.length; i++) {
            weatherInputArray[i] = ArrayUtils.toPrimitive(weatherArray[i]);
        }

        for (int i = 0; i < weatherInputArray.length; i++) {
            System.out.print("weatherInputArray[" + i + "]: ");
            for (int j = 0; j < weatherInputArray[i].length; j++) {
                System.out.print(", " + weatherInputArray[i][j]);
            }
            System.out.println();
        }


        List<Double[]> ambulanceCallInput = getAmbulanceCallsData();
        Double[][] ambulanceCallArray = new Double[ambulanceCallInput.size()][];
        for (int a = 0; a < ambulanceCallInput.size(); a++) {
            ambulanceCallArray[a] = ambulanceCallInput.get(a);
        }
        double[][] ambulanceCallResultArray = new double[ambulanceCallArray.length][];
        for (int i = 0; i < ambulanceCallArray.length; i++) {
            ambulanceCallResultArray[i] = ArrayUtils.toPrimitive(ambulanceCallArray[i]);
        }

        for (int i = 0; i < ambulanceCallResultArray.length; i++) {
            System.out.print("ambulanceCallResultArray[" + i + "]: ");
            for (int j = 0; j < ambulanceCallResultArray[i].length; j++) {
                System.out.print(", " + ambulanceCallResultArray[i][j]);
            }
            System.out.println();
        }
/*
        NormalizeArray normalizeArray = new NormalizeArray();
        normalizeArray.setNormalizedHigh(1);
        normalizeArray.setNormalizedLow(-1);

        double[][] normalizedWeatherInput = new double[weatherInputArray.length][];
        for (int i = 0; i < weatherInputArray.length; i++) {
            normalizedWeatherInput[i] = normalizeArray.process(weatherInputArray[i]);
        }

        double[][] normalizedAmbulanceCallResult = new double[ambulanceCallResultArray.length][];
        for (int i = 0; i < ambulanceCallResultArray.length; i++) {
            normalizedAmbulanceCallResult[i] = normalizeArray.process(ambulanceCallResultArray[i]);
        }*/
        double[][] normalizedWeatherInput = new double[weatherInputArray.length][8];
        for (int i = 0; i < weatherInputArray.length; i++) {
            for (int j = 0; j < weatherInputArray[i].length; j++) {
                normalizedWeatherInput[i][j] = normalize(weatherInputArray[i][j]);
            }
        }

        double[][] normalizedAmbulanceCallResult = new double[ambulanceCallResultArray.length][ambulanceCallResultArray[1].length];
        for (int i = 0; i < ambulanceCallResultArray.length; i++) {
            for (int j = 0; j < ambulanceCallResultArray[i].length; j++) {
                normalizedAmbulanceCallResult[i][j] = normalize(ambulanceCallResultArray[i][j]);
            }
        }

        //набор данных для обучения
        MLDataSet trainingSet = new BasicMLDataSet(normalizedWeatherInput, normalizedAmbulanceCallResult);

        //инициализация нейросети
        BasicNetwork network = new BasicNetwork();
        network.addLayer(new BasicLayer(null, true, 8));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 16));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 32));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 64));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 32));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 16));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), false, 15));
        network.getStructure().finalizeStructure();

        //создаю обьект, обуч нейросеть
        MLTrain train = new ResilientPropagation(network, trainingSet);

        //обучение, до тех пор пока ошибка не станет меньше n
        int epoch = 1;
        do {
            train.iteration();
            System.out.println("Epoch #" + epoch + "Error:" + train.getError());
            epoch++;
        } while (train.getError() > 0.01);

        return network;
    }

    private List<Double[]> getWeatherData() {
        List<Double[]> result = new ArrayList<>();
        List<WeatherStats> weatherStats = weatherStatsService.getAll();

        //TODO check if it works wrong
        weatherStats.sort(Comparator.comparing(WeatherStats::getYearMonth));

        for (WeatherStats stats :
                weatherStats) {
            Double[] weatherValues = new Double[8];
            weatherValues[0] = stats.getAvgTemperature();
            weatherValues[1] = stats.getAtmospherePressure();
            weatherValues[2] = stats.getHumidity();
            weatherValues[3] = stats.getWindSpeed();
            weatherValues[4] = stats.getCloudiness();
            weatherValues[5] = stats.getMinTemperature();
            weatherValues[6] = stats.getMaxTemperature();
            weatherValues[7] = stats.getDownfall();
            result.add(weatherValues);
        }

        return result;
    }

    private List<Double[]> getAmbulanceCallsData() {
        List<Double[]> result = new ArrayList<>();
        List<AmbulanceCallStats> ambulanceCallStats = ambulanceCallStatsService.getAll();

        //TODO rewrite this
/*

        for(int monthNum = 1; monthNum < 13; monthNum++){
            Month month = Month.of(monthNum);
            List<AmbulanceCallStats> ambulanceCallStats = ambulanceCallStatsService.getAmbulanceCallStatsByYear(2012, month);

            ambulanceCallStats.sort(new Comparator<AmbulanceCallStats>() {
                @Override
                public int compare(AmbulanceCallStats o1, AmbulanceCallStats o2) {
                    return o1.getDisease().getNumber() - o2.getDisease().getNumber();
                }
            });

            Double[] values = new Double[(ambulanceCallStats.size())];
            for(int i = 0; i < values.length; i++){
                values[i] = 1.0 * ambulanceCallStats.get(i).getCount();
            }

            result.add(values);
        }
*/

        return result;
    }
}



















