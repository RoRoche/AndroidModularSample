package com.guddy.android_modular_sample.second;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bluelinelabs.conductor.Controller;
import com.google.common.base.Preconditions;

import java.util.Date;

import javax.inject.Inject;

import au.com.ds.ef.StateEnum;

public class SecondController extends Controller {

    //region Args
    private final String mLogin;
    //endregion

    //region Injected fields
    @Inject
    public IDateFormatter mDateFormatter;
    //endregion

    //region Constructor
    public SecondController() {
        this("");
    }

    public SecondController(@NonNull final String psLogin) {
        super();
        mLogin = psLogin;
    }
    //endregion

    //region Controller
    @NonNull
    @Override
    protected View onCreateView(@NonNull final LayoutInflater poInflater, @NonNull final ViewGroup poContainer) {
        Preconditions.checkNotNull(mDateFormatter, "Field mDateFormatter is null, did you miss to inject this with your dependency injection mechanism?");

        final View loView = poInflater.inflate(R.layout.second_controller, poContainer, false);
        final TextView loTextViewWelcome = (TextView) loView.findViewById(R.id.SecondController_TextView_Welcome);
        loTextViewWelcome.setText(getApplicationContext().getString(R.string.welcome, mDateFormatter.format(new Date()), mLogin));
        return loView;
    }
    //endregion

    //region FSM
    public enum States implements StateEnum {
        SHOWING_WELCOME
    }
    //endregion
}
