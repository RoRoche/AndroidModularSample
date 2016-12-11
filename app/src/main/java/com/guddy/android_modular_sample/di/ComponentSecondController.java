package com.guddy.android_modular_sample.di;

import android.support.annotation.NonNull;

import com.guddy.android_modular_sample.second.SecondController;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ModuleSecondController.class})
public interface ComponentSecondController {

    void inject(@NonNull final SecondController poSecondController);

}
