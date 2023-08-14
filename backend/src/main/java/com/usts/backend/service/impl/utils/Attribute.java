package com.usts.backend.service.impl.utils;

public class Attribute {
    public String name;
    public String type;

    public Attribute(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
