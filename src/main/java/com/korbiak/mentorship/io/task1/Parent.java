package com.korbiak.mentorship.io.task1;

import java.io.Serializable;

public class Parent implements Serializable {

    private String parentValue;

    public Parent() {
        this.parentValue = "parentValue";
    }

    public String getParentValue() {
        return parentValue;
    }

    public void setParentValue(String parentValue) {
        this.parentValue = parentValue;
    }
}


