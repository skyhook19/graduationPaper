package com.sagaleev.domain.model;


import java.util.HashMap;

public class NetworkHelper {

    public static final HashMap<Integer, Double> MAXIMUMS_FOR_WEATHER = new HashMap<Integer, Double>() {{
        put(0, 26.0);
        put(1, 772.7);
        put(2, 87.2);
        put(3, 5.9);
        put(4, 84.09);
        put(5, 13.0);
        put(6, 38.4);
        put(7, 153.2);
    }};

    public static final HashMap<Integer, Double> MINIMUMS_FOR_WEATHER = new HashMap<Integer, Double>() {{
        put(0, -14.6);
        put(1, 756.4);
        put(2, 49.9);
        put(3, 3.2);
        put(4, 32.79);
        put(5, -29.6);
        put(6, 1.2);
        put(7, 3.2);
    }};

    public static final HashMap<Integer, Double> MAXIMUMS_FOR_AMBULANCE_CALLS = new HashMap<Integer, Double>() {{
        put(0, 2973.0);
        put(1, 144.0);
        put(2, 140.0);
        put(3, 13.0);
        put(4, 449.0);
        put(5, 671.0);
        put(6, 459.0);
        put(7, 190.0);
        put(8, 515.0);
        put(9, 33.0);
        put(10, 471.0);
        put(11, 316.0);
        put(12, 191.0);
        put(13, 32.0);
        put(14, 119.0);
    }};

    public static final HashMap<Integer, Double> MINIMUMS_FOR_AMBULANCE_CALLS = new HashMap<Integer, Double>() {{
        put(0, 473.0);
        put(1, 69.0);
        put(2, 52.0);
        put(3, 0.0);
        put(4, 38.0);
        put(5, 439.0);
        put(6, 66.0);
        put(7, 83.0);
        put(8, 212.0);
        put(9, 1.0);
        put(10, 112.0);
        put(11, 169.0);
        put(12, 16.0);
        put(13, 4.0);
        put(14, 22.0);
    }};

}
