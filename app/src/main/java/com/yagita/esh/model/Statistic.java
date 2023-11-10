package com.yagita.esh.model;

public class Statistic {
    private String unit;
    private String statis;

    public Statistic(String unit, String statis) {
        this.unit = unit;
        this.statis = statis;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getStatis() {
        return statis;
    }

    public void setStatis(String statis) {
        this.statis = statis;
    }
}
