package com.sagaleev.service.impl;

import com.sagaleev.domain.model.NeuralNetwork;
import com.sagaleev.service.NeuralNetworkTrainerService;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.ml.train.MLTrain;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;
import org.encog.util.arrayutil.NormalizationAction;
import org.encog.util.arrayutil.NormalizedField;
import org.springframework.stereotype.Service;

@Service
public class NeuralNetworkTrainerServiceImpl implements NeuralNetworkTrainerService {

    @Override
    public void train(NeuralNetwork neuralNetwork, double[][] trainingWeatherDataSet, double[][] trainingAmbulanceCallsDataSet) {
        BasicNetwork network = neuralNetwork.getNetwork();
        neuralNetwork.setActualHighForInput(getMax(trainingWeatherDataSet));
        neuralNetwork.setActualLowForInput(getMin(trainingWeatherDataSet));
        neuralNetwork.setActualHighForOutput(getMax(trainingAmbulanceCallsDataSet));
        neuralNetwork.setActualLowForOutput(getMin(trainingAmbulanceCallsDataSet));


        printDataArray(trainingWeatherDataSet, "weather data");
        printDataArray(trainingAmbulanceCallsDataSet, "ambulanceCallResultArray");

        double[][] normalizedWeatherDataSet = normalizeArray(neuralNetwork.getActualHighForInput(),
                neuralNetwork.getActualLowForInput(), trainingWeatherDataSet);
        double[][] normalizedAmbulanceCallDataSet = normalizeArray(neuralNetwork.getActualHighForOutput(),
                neuralNetwork.getActualLowForOutput(), trainingAmbulanceCallsDataSet);

        MLDataSet trainingSet = new BasicMLDataSet(normalizedWeatherDataSet, normalizedAmbulanceCallDataSet);
        MLTrain train = new ResilientPropagation(network, trainingSet);

        int epoch = 1;
        do {
            train.iteration();
            System.out.println("Epoch #" + epoch + "Error:" + train.getError());
            epoch++;
        } while (train.getError() > 0.01);
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

    private double[][] normalizeArray(double actualHigh, double actualLow, double[][] input) {
        double[][] result = new double[input.length][input[0].length];

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                result[i][j] = normalize(actualHigh, actualLow, input[i][j]);
            }
        }
        return result;
    }

    private double normalize(double actualHigh, double actualLow, double valueToNormalize) {
        NormalizedField normalizedField = new NormalizedField(NormalizationAction.Normalize, "normalize",
                actualHigh, actualLow, NeuralNetwork.NORMALIZED_HIGH, NeuralNetwork.NORMALIZED_LOW);
        return normalizedField.normalize(valueToNormalize);
    }

    private double getMax(double[][] arr) {
        double max = arr[0][0];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] > max) max = arr[i][j];
            }
        }

        return max;
    }

    private double getMin(double[][] arr) {
        double min = arr[0][0];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] < min) min = arr[i][j];
            }
        }

        return min;
    }
}