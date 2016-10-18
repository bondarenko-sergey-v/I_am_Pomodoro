package com.bond.iampomodoro.Presenter;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class Presenter extends Service {
    public Presenter() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
