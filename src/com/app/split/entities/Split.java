package com.app.split.entities;

import java.util.Objects;

public class Split {
    String owedByUserId;
    String oweToUserId;

    public Split(String owedByUserId, String oweToUserId) {
        this.owedByUserId = owedByUserId;
        this.oweToUserId = oweToUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Split split = (Split) o;
        return Objects.equals(owedByUserId, split.owedByUserId) && Objects.equals(oweToUserId, split.oweToUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owedByUserId, oweToUserId);
    }

    @Override
    public String toString() {
        return "Split{" +
                "owedByUserId='" + owedByUserId + '\'' +
                ", oweToUserId='" + oweToUserId + '\'' +
                '}';
    }

    public String getOwedByUserId() {
        return owedByUserId;
    }

    public String getOweToUserId() {
        return oweToUserId;
    }
}


