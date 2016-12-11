package com.guddy.android_modular_sample.common;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

public class UiThreadExecutor implements java.util.concurrent.Executor {
    //region Fields
    @NonNull
    private Handler mHandler = new Handler(Looper.getMainLooper());
    //endregion

    //region Executor
    @Override
    public void execute(final Runnable poCommand) {
        mHandler.post(poCommand);
    }
    //endregion
}
