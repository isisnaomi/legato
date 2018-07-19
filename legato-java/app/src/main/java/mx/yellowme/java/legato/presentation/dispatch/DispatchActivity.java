package mx.yellowme.java.legato.presentation.dispatch;

import android.content.Intent;
import android.os.Bundle;

import mx.yellowme.java.legato.common.CommonActivityPresenter;
import mx.yellowme.java.legato.presentation.error.ErrorActivity;
import mx.yellowme.java.legato.presentation.login.LoginActivity;
import mx.yellowme.java.legato.presentation.main.MainActivity;

import mx.yellowme.java.legato.R;

public class DispatchActivity
        extends CommonActivityPresenter<DispatchContract.Presenter>
            implements DispatchContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter(new DispatchPresenter(this));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dispatch;
    }

    @Override
    public void onResume(){
        super.onResume();
        getPresenter().decideUserMainScreen();
    }

    @Override
    public void reload() {
        getPresenter().decideUserMainScreen();
    }

    @Override
    public void sendToMain() {
        //TODO: Use a ActivityUtils or Navigation helper.
        Intent intent = new Intent().setClass(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void sendToLogin() {
        //TODO: Use a ActivityUtils or Navigation helper.
        Intent intent = new Intent().setClass(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void sendToError() {
        //TODO: Use a ActivityUtils or Navigation helper.
        Intent intent = new Intent().setClass(this, ErrorActivity.class);
        startActivity(intent);
        finish();
    }
}
