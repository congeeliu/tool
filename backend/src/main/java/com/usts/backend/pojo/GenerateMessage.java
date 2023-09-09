package com.usts.backend.pojo;

import java.util.List;

public class GenerateMessage {
    private String packageName;
    private String className;
    private List<Property> properties;

    public GenerateMessage() {
    }

    public GenerateMessage(String packageName, String className, List<Property> properties) {
        this.packageName = packageName;
        this.className = className;
        this.properties = properties;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
}
