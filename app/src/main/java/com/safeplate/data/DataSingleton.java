package com.safeplate.data;

public final class DataSingleton {
    static public DataManager instance;

    static public void SetInstance(DataManager _instance) {
        instance = _instance;
    }
}
