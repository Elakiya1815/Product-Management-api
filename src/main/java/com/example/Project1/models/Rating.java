package com.example.Project1.models;
import jakarta.persistence.Embeddable;

@Embeddable
public class Rating {
    private Double rate;
    private Integer count;

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
