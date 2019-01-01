package com.test.maps.model;

import java.io.Serializable;

public class VaultData implements Serializable {

    private String mapApiKey;

    public String getMapApiKey() {
        return mapApiKey;
    }

    public void setMapApiKey(String mapApiKey) {
        this.mapApiKey = mapApiKey;
    }

    @Override
    public String toString() {
        return "VaultData{" +
                "mapApiKey='" + mapApiKey + '\'' +
                '}';
    }
}
