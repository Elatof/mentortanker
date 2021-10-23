package com.korbiak.mentorship.io.task1;

public class Child3 extends Child2{

    private String child3Value;
    private transient int child3ValueInt;

    public Child3(Integer child2ValueInt, int child3ValueInt) {
        super(child2ValueInt);
        this.child3Value = "child3Value";
        this.child3ValueInt = child3ValueInt;
    }

    public String getChild3Value() {
        return child3Value;
    }

    public void setChild3Value(String child3Value) {
        this.child3Value = child3Value;
    }

    public int getChild3ValueInt() {
        return child3ValueInt;
    }

    public void setChild3ValueInt(int child3ValueInt) {
        this.child3ValueInt = child3ValueInt;
    }
}
