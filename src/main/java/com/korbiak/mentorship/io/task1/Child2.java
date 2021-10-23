package com.korbiak.mentorship.io.task1;

public class Child2 extends Parent{
    private String child2Value;
    private final transient Integer child2ValueInt;

    public Child2(Integer child2ValueInt) {
        this.child2Value = "child2Value";
        this.child2ValueInt = child2ValueInt;
    }

    public String getChild2Value() {
        return child2Value;
    }

    public void setChild2Value(String child2Value) {
        this.child2Value = child2Value;
    }

    public Integer getChild2ValueInt() {
        return child2ValueInt;
    }
}
