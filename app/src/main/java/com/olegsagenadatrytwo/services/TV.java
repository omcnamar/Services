package com.olegsagenadatrytwo.services;

import java.io.Serializable;

/**
 * Created by omcna on 8/16/2017.
 */

public class TV implements Serializable{
    private String color;
    private String definition;
    private String size;
    private String currentChanel;

    public TV(String color, String definition, String size, String currentChanel) {
        this.color = color;
        this.definition = definition;
        this.size = size;
        this.currentChanel = currentChanel;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCurrentChanel() {
        return currentChanel;
    }

    public void setCurrentChanel(String currentChanel) {
        this.currentChanel = currentChanel;
    }

    @Override
    public String toString() {
        return "TV{" +
                "color='" + color + '\'' +
                ", definition='" + definition + '\'' +
                ", size='" + size + '\'' +
                ", currentChanel='" + currentChanel + '\'' +
                '}';
    }
}
