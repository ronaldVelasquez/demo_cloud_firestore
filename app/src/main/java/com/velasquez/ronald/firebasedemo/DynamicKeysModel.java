package com.velasquez.ronald.firebasedemo;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Created by ronaldvelasquez on 20/02/18.
 */

public class DynamicKeysModel {
    @SerializedName("keys")
    private Map<String, Object> keys;
    @SerializedName("version")
    private int version;

    public Map<String, Object> getKeys() {
        return keys;
    }

    public void setKeys(Map<String, Object> keys) {
        this.keys = keys;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
