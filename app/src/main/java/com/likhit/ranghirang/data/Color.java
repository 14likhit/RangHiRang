package com.likhit.ranghirang.data;

import com.google.gson.annotations.SerializedName;

public class Color {

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("year")
    private Integer year;

    @SerializedName("color")
    private String color;

    @SerializedName("pantone_value")
    private String pantoneValue;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    public String getPantoneValue() {
        return pantoneValue;
    }
}
