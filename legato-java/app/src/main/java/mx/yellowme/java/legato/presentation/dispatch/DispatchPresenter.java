package mx.yellowme.java.legato.presentation.dispatch;

import android.os.Handler;
import android.support.annotation.NonNull;

import mx.yellowme.java.legato.common.CommonPresenter;
import mx.yellowme.java.legato.handlers.FetchUserHandler;
import mx.yellowme.java.legato.handlers.FlowHandler;
import mx.yellowme.java.legato.handlers.SessionFlowHandler;
import mx.yellowme.java.legato.handlers.ValidSessionStatusHandler;
import mx.yellowme.java.legato.utils.DataFaker;


public class DispatchPresenter
        extends CommonPresenter<DispatchContract.View>
            implements DispatchContract.Presenter {

    private FlowHandler flowHandler;

    //HERE: Add here all your local managers or remote data sources.
    // Do not forget to inject using the class constructor

    /**
     * This holds the decided screen for the user even
     * before running all validations.
     */
    private DispatchContract.Screen mScreen = DispatchContract.Screen.MAIN;

    /**
     * Minimum delay on the view in order for the user to
     * appreciate the splash
     */
    @SuppressWarnings("FieldCanBeLocal")
    private final int MIN_DELAY_TIME = 2000;

    DispatchPresenter(@NonNull DispatchContract.View view) {
        super(view);

        DataFaker dataFaker = new DataFaker();
        flowHandler = new SessionFlowHandler(
                dataFaker, DispatchContract.Screen.LOGIN
        );
        flowHandler
                .linkWith(new ValidSessionStatusHandler(
                        dataFaker, DispatchContract.Screen.LOGIN)
                )
                .linkWith(new FetchUserHandler(
                        dataFaker, DispatchContract.Screen.ERROR)
                );
    }

    @Override
    public void decideUserMainScreen() {
        mView.setProgress(true);
        flowHandler.runValidations(new FlowHandler.FlowHandlerCallback() {
            @Override
            public void onEndReached() {
                executeAfterDecideMainScreenAction();
            }

            @Override
            public void onEarlyExit(Object concreteFlow) {
                mScreen = (DispatchContract.Screen) concreteFlow;
                executeAfterDecideMainScreenAction();
            }
        });
    }

    @Override
    public void sendToMainScreen() {
        switch (mScreen) {
            case MAIN:
                mView.sendToMain();
                break;
            case LOGIN:
                mView.sendToLogin();
                break;
            case ERROR:
                mView.sendToError();
                break;
        }
    }

    private void executeAfterDecideMainScreenAction() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mView.setProgress(false);
                sendToMainScreen();
            }
        }, MIN_DELAY_TIME);
    }
}
