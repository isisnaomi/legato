package mx.yellowme.java.legato.common;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import mx.yellowme.java.legato.utils.MessageHelper;

/**
 * This class allows to avoid making the presenter attribute public or
 * protected to be accessed when testing.
 *
 * Also defines an abstract method to retrieve the view layout identifier.
 *
 * Created by luisburgos on 5/16/18.
 */
public abstract class CommonActivityPresenter<ConcretePresenter>
        extends AppCompatActivity implements CommonBaseView {

    private ConcretePresenter mPresenter;

    @SuppressWarnings("FieldCanBeLocal")
    private Snackbar commonSnackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
    }

    public void setPresenter(@NonNull ConcretePresenter presenter) {
        mPresenter = presenter;
    }

    public ConcretePresenter getPresenter() {
        return mPresenter;
    }

    /**
     * This method must be implemented by child classes in order to
     * determine the layout for the content view.
     *
     * @return the concrete layout reference for the content view.
     */
    protected abstract int getLayoutId();

    @Override
    public void setProgress(boolean isActive) {
        //TODO: Implement a common method, displayProgressDialog(isActive);
    }

    @Override
    public void display(String message) {
        commonSnackbar = MessageHelper.buildSnackBar(
                findViewById(android.R.id.content),
                message
        );
    }
}
