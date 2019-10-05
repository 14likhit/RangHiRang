package com.likhit.ranghirang.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

//model class for colorlist
public class ColorList {

    @SerializedName("page")
    private Integer page;

    @SerializedName("per_page")
    private Integer perPage;

    @SerializedName("total")
    private Integer total;

    @SerializedName("total_pages")
    private Integer totalPages;

    @SerializedName("data")
    private List<Color> colors;

    public Integer getPage() {
        return page;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public Integer getTotal() {
        return total;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public List<Color> getColors() {
        return colors;
    }
}
