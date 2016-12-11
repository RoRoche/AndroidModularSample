package com.guddy.android_modular_sample.first;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Controller;
import com.guddy.android_modular_sample.common.FlowContext;

import au.com.ds.ef.EventEnum;
import au.com.ds.ef.StateEnum;
import au.com.ds.ef.err.LogicViolationError;

public class FirstController extends Controller {
    //region Constants
    private static final String ARG_KEY_LOGIN = "LOGIN";
    //endregion

    //region Fields
    @NonNull
    private final FlowContext mFlowContext;
    //endregion

    //region Bound views
    private TextInputEditText mEditTextLogin;
    //endregion

    //region Constructor
    public FirstController() {
        this(new FlowContext());
    }

    public FirstController(@NonNull final FlowContext poFlowContext) {
        super();
        mFlowContext = poFlowContext;
    }
    //endregion

    //region Controller
    @NonNull
    @Override
    protected View onCreateView(@NonNull final LayoutInflater poInflater, @NonNull final ViewGroup poContainer) {
        final View loView = poInflater.inflate(R.layout.first_controller, poContainer, false);
        mEditTextLogin = (TextInputEditText) loView.findViewById(R.id.FirstController_EditText_Login);
        loView.findViewById(R.id.FirstController_Button_Start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View poView) {
                onClickButtonStart();
            }
        });
        return loView;
    }
    //endregion

    //region User interaction
    private void onClickButtonStart() {
        final String lsLogin = mEditTextLogin.getText().toString();
        if (TextUtils.isEmpty(lsLogin)) {
            mEditTextLogin.setError(getApplicationContext().getString(R.string.login_error));
        } else {
            try {
                mFlowContext.args().putString(ARG_KEY_LOGIN, lsLogin);
                mFlowContext.trigger(Events.loginProvided);
            } catch (final LogicViolationError poLogicViolationError) {
                // does nothing
            }
        }
    }
    //endregion

    //region FSM
    public enum States implements StateEnum {
        WAITING_LOGIN
    }

    public enum Events implements EventEnum {
        loginProvided
    }

    public static String getLogin(@NonNull final FlowContext poFlowContext) {
        return poFlowContext.args().getString(ARG_KEY_LOGIN);
    }
    //endregion
}
