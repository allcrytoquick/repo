package com.coinhub.DataModelPackage;

/**
 * Created by Ibrahim Khalil on 28-Oct-17.
 */

/**
 * Menu Expanded Model (POJO Data Model)
 */
public class MenuExpandedModel {

    private String name;
    private int icon;


    public MenuExpandedModel(String name, int icon) {
        this.icon = icon;
        this.name = name;

    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
