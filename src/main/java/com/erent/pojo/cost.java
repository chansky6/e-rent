package com.erent.pojo;

public class cost {
    int id;
    int arrears_id;
    int cost;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArrears_id() {
        return arrears_id;
    }

    public void setArrears_id(int arrears_id) {
        this.arrears_id = arrears_id;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "cost{" +
                "id=" + id +
                ", arrears_id=" + arrears_id +
                ", cost=" + cost +
                '}';
    }
}
