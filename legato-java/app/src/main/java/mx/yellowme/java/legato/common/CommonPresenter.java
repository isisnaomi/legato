package mx.yellowme.java.legato.common;

import android.support.annotation.NonNull;

/**
 * This class allows to avoid duplicated code when listen to server requests.
 * A common scenario for a presenter is to make a server request and subscribe to
 * the {@link ServerCallback} interface.
 *
 * This methods could be duplicated across presenters if we are not careful:
 *
 * 1) {@link ServerCallback#onNetworkError()}
 * 2) {@link ServerCallback#onServerError(String)}
 *
 * This class provides the abstraction to automatically handle the server responses and
 * call methods on the view which necessary has to inherit from {@link CommonBaseView}
 *
 * Created by luisburgos on 5/14/18.
 */
public abstract class CommonPresenter<ConcreteView extends CommonBaseView> implements ServerCallback {

    protected ConcreteView mView;

    public CommonPresenter(@NonNull ConcreteView view) {
        this.mView = view;
    }

    @Override
    public void onNetworkError() {
        String noInternetError = "No internet";
        mView.display(noInternetError);
        mView.setProgress(false);
    }

    @Override
    public void onServerError(String error) {
        mView.display(error);
        mView.setProgress(false);
    }

}