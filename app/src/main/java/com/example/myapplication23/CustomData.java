package com.example.myapplication23;

import com.anychart.anychart.ValueDataEntry;

public class CustomData extends ValueDataEntry {
    public CustomData(String x, int value, Number value2, Number value3) {
        super(x, value);
        setValue("value2", value2);
        setValue("value3", value3);
    }
}
