package com.guddy.android_modular_sample;

import android.app.Application;

import com.guddy.android_modular_sample.di.ComponentSecondController;
import com.guddy.android_modular_sample.di.DaggerComponentSecondController;
import com.guddy.android_modular_sample.di.ModuleSecondController;

public class AndroidModularApplication extends Application {

    //region Static fields
    private static AndroidModularApplication sInstance;
    //endregion

    //region Fields
    private ComponentSecondController mComponentSecondController;
    //endregion

    //region Overridden methods
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mComponentSecondController = DaggerComponentSecondController.builder()
                .moduleSecondController(new ModuleSecondController())
                .build();
    }
    //endregion

    //region Static getter
    public static AndroidModularApplication getInstance() {
        return sInstance;
    }
    //endregion

    //region Getters
    public ComponentSecondController getComponentSecondController() {
        return mComponentSecondController;
    }
    //endregion
}
