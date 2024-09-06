package com.safeplate;

import com.safeplate.data.models.User;

public final class LoggedInUser {
    static private com.safeplate.data.models.User instance;

    static public void setInstance(com.safeplate.data.models.User _instance) {
        instance = _instance;
    }

    static public User getInstance() {
        return instance;
    }

    static public void clearInstance() {
        instance = null;
    }
}
