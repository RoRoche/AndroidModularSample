package com.guddy.android_modular_sample.di;

import com.guddy.android_modular_sample.second.IDateFormatter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ModuleSecondController {

    @Provides
    @Singleton
    public IDateFormatter providesApplication() {
        return new DateFormatter();
    }

}
