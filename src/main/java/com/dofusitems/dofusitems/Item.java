package com.dofusitems.dofusitems;

import java.io.Serializable;

public class Item implements Serializable{

    private String imgItem;
    private String name;
    private String setName;
    private String type;
    private String level;
    private String condition;
    private String stats;
    private String description;

    public Item(){
    }

    public Item(String imgItem, String name, String setName, String type,
                String level, String condition, String stats, String description){
        this.imgItem = imgItem;
        this.name = name;
        this.setName = setName;
        this.type = type;
        this.level = level;
        this.condition = condition;
        this.stats = stats;
        this.description = description;
    }

    public String getImgItem() {
        return imgItem;
    }

    public void setImgItem(String imgItem) {
        this.imgItem = imgItem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getStats() {
        return stats;
    }

    public void setStats(String stats) {
        this.stats = stats;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
