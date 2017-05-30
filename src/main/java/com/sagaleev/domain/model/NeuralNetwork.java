package com.sagaleev.domain.model;

import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;

public class NeuralNetwork {

    private BasicNetwork network;
    private Disease disease;
    public static final double NORMALIZED_HIGH = 1;
    public static final double NORMALIZED_LOW = 0;
    private double actualHighForInput;
    private double actualLowForInput;
    private double actualHighForOutput;
    private double actualLowForOutput;

    public NeuralNetwork(Disease disease) {
        network = new BasicNetwork();
        network.addLayer(new BasicLayer(null, true, 8));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 16));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), false, 1));
        network.getStructure().finalizeStructure();
        network.reset();

        this.disease = disease;
    }

    public BasicNetwork getNetwork() {
        return network;
    }

    public void setNetwork(BasicNetwork network) {
        this.network = network;
    }

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    public double getActualHighForInput() {
        return actualHighForInput;
    }

    public void setActualHighForInput(double actualHighForInput) {
        this.actualHighForInput = actualHighForInput;
    }

    public double getActualLowForInput() {
        return actualLowForInput;
    }

    public void setActualLowForInput(double actualLowForInput) {
        this.actualLowForInput = actualLowForInput;
    }

    public double getActualHighForOutput() {
        return actualHighForOutput;
    }

    public void setActualHighForOutput(double actualHighForOutput) {
        this.actualHighForOutput = actualHighForOutput;
    }

    public double getActualLowForOutput() {
        return actualLowForOutput;
    }

    public void setActualLowForOutput(double actualLowForOutput) {
        this.actualLowForOutput = actualLowForOutput;
    }
}
