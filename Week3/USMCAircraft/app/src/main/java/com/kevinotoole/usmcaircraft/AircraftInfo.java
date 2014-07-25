package com.kevinotoole.usmcaircraft;

import java.io.Serializable;

/**
 * Author: Kevin OToole
 * MDF3 Term 1407
 * Week 3 Project
 * Project: USMC Aircraft
 * Package: com.kevinotoole.usmcaircraft;
 * File: AircraftInfo.java
 * Purpose: Custom class for USMC Aircraft to include image, title and description:
 */

public class AircraftInfo implements Serializable {

    private static Long serialVersionUID = 1L;
    private  int imageId;
    private String title;
    private String description;

    public AircraftInfo(int imageId, String title, String description){
        super();
        this.imageId = imageId;
        this.title = title;
        this.description = description;
    }

    public int getImageId(){
        return imageId;
    }

    public void setImageId(){
        this.imageId = imageId;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(){
        this.title = title;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(){
        this.description = description;
    }
}
