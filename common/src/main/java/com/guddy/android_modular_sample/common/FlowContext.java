package com.guddy.android_modular_sample.common;

import android.os.Bundle;
import android.support.annotation.NonNull;

import au.com.ds.ef.StatefulContext;

public class FlowContext extends StatefulContext {
    //region Fields
    @NonNull
    private final Bundle mArgs = new Bundle();
    //endregion

    //region Visible API
    @NonNull
    public Bundle args() {
        return mArgs;
    }
    //endregion
}
