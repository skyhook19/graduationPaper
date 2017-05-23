package com.sagaleev.domain.model;

public enum Disease{
    ACUTE_RESPIRATORY_VIRAL_INFECTION(0),
    MYOCARDIAL_INFARCTION(1),
    MYOCARDIAL_INFARCTION_WITH_HOSPITALIZATION(2),
    FATAL_MYOCARDIAL_INFARCTION(3),
    ANGINA_PECTORIS(4),
    ARRHYTHMIA(5),
    CARDIO_VASCULAR_DISEASE(6),
    FATAL_CARDIO_VASCULAR_DISEASE(7),
    STROKE(8),
    FATAL_STROKE(9),
    PNEUMONIA(10),
    BRONCHIAL_ASTHMA(11),
    BRONCHITIS(12),
    PEPTIC_ULCER_DISEASE(13),
    GASTRITIS(14);

    private int number;

    Disease(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
