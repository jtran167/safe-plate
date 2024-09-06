package com.safeplate.data;

import com.safeplate.data.instances.User;
import com.safeplate.data.interfaces.IUser;

public final class Interactor {
    static private Interactor instance;
    static private IUser User;

    private Interactor() {
        this.User = new User();
    }

    static private Interactor getInstance() {
        if (instance == null) {
            instance = new Interactor();
        }
        return instance;
    }

    static public IUser getUserInstance() {
        if (instance == null) {
            getInstance();
        }
        return User;
    }
}
