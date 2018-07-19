package mx.yellowme.java.legato.presentation.dispatch;

import mx.yellowme.java.legato.common.CommonBaseView;

/**
 * Contract for the presenter and view of Launcher
 *
 * Created by luisburgos on 5/22/18.
 */
public interface DispatchContract {
    interface View extends CommonBaseView {

        /**
         * Triggers all the methods from this contract, from animations to
         * display information. This methods is useful when running dispatch
         * validations and you need to redirect the user.
         */
        void reload();

        /**
         * Execute ENTER animations for view layout.
         * (Optional, presenter must implment StartAwarePresenter)
         */
        //void runInitialAnimations();

        /**
         * Execute EXIT animations for view layout.
         * (Optional, presenter must implment StartAwarePresenter)
         */
        //void runExitAnimations();

        /**
         * Executes or clears the blinking animation.
         *
         * @param active value for the indicator
         */
        void setProgress(boolean active);

        /**
         * Redirects the user to the main screen.
         */
        void sendToMain();

        //HERE: Below add all your "sendToScreen" methods mapping each case of the "Screen" enum

        /**
         * Redirects the user to the login screen.
         */
        void sendToLogin();

        /**
         * Redirects the user to the error screen.
         */
        void sendToError();

    }

    interface Presenter {

        /**
         * Executes all flow handlers to decide which is the first screen the
         * user should see inside the app. This method should setup a FLAG in order for
         * the method {@link Presenter#sendToMainScreen()} to work properly.
         */
        void decideUserMainScreen();

        /**
         * Executes a pending "transaction" inside the presenter to tell the
         * view which "send" method should execute.
         */
        void sendToMainScreen();
    }

    /**
     * Matches all possible main screens for the user given the "app navigation" flow.
     */
    enum Screen {
        LOGIN,
        MAIN,
        ERROR
        //HERE: Add each screen from your navigation flow.
    }
}