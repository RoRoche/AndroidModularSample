package com.guddy.android_modular_sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;
import com.guddy.android_modular_sample.common.FlowContext;
import com.guddy.android_modular_sample.common.UiThreadExecutor;
import com.guddy.android_modular_sample.first.FirstController;
import com.guddy.android_modular_sample.second.SecondController;

import au.com.ds.ef.EasyFlow;
import au.com.ds.ef.EventEnum;
import au.com.ds.ef.err.LogicViolationError;

import static au.com.ds.ef.FlowBuilder.from;
import static au.com.ds.ef.FlowBuilder.on;

public class MainActivity extends AppCompatActivity {

    //region Fields
    private Router mRouter;
    private EasyFlow<FlowContext> mFlow;
    private FlowContext mFlowContext;
    //endregion

    //region Lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ViewGroup container = (ViewGroup) findViewById(R.id.ViewGroup_Container);
        mRouter = Conductor.attachRouter(this, container, savedInstanceState);

        mFlow =
                from(FirstController.States.WAITING_LOGIN)
                        .transit(
                                on(FirstController.Events.loginProvided)
                                        .to(SecondController.States.SHOWING_WELCOME)
                                        .transit(
                                                on(Events.backClicked)
                                                        .to(FirstController.States.WAITING_LOGIN)
                                        )
                        )
                        .executor(new UiThreadExecutor());

        mFlow
                .whenEnter(FirstController.States.WAITING_LOGIN, (final FlowContext poContext) -> {
                    if (!mRouter.hasRootController()) {
                        mRouter.setRoot(RouterTransaction.with(new FirstController(poContext)));
                    }
                })
                .whenEnter(SecondController.States.SHOWING_WELCOME, (final FlowContext poContext) -> {
                    final SecondController loController = new SecondController(FirstController.getLogin(poContext));

                    AndroidModularApplication.getInstance().getComponentSecondController().inject(loController);

                    final RouterTransaction loTransaction = RouterTransaction.with(loController)
                            .pushChangeHandler(new FadeChangeHandler())
                            .popChangeHandler(new FadeChangeHandler());

                    mRouter.pushController(loTransaction);
                })
                .whenLeave(SecondController.States.SHOWING_WELCOME, (final FlowContext poContext) -> {
                    poContext.args().clear();
                });

        mFlowContext = new FlowContext();
        mFlow.start(mFlowContext);
    }

    @Override
    public void onBackPressed() {
        try {
            mFlowContext.trigger(Events.backClicked);
        } catch (final LogicViolationError poLogicViolationError) {
            // does nothing
        }

        if (!mRouter.handleBack()) {
            super.onBackPressed();
        }
    }
    //endregion

    //region FSM
    public enum Events implements EventEnum {
        backClicked
    }
    //endregion
}
