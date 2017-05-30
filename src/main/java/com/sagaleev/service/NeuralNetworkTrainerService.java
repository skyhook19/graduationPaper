package com.sagaleev.service;

import com.sagaleev.domain.model.NeuralNetwork;

public interface NeuralNetworkTrainerService {

    void train(NeuralNetwork neuralNetwork, double[][] trainingWeatherDataSet, double[][] trainingAmbulanceCallsDataSet);
}
