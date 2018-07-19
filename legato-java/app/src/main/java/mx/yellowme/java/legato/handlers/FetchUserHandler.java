package mx.yellowme.java.legato.handlers;

import android.support.annotation.NonNull;

import mx.yellowme.java.legato.presentation.dispatch.DispatchContract;
import mx.yellowme.java.legato.utils.DataFaker;


public class FetchUserHandler extends FlowHandler<DataFaker, DispatchContract.Screen> {

    public FetchUserHandler(@NonNull DataFaker dataFaker, @NonNull DispatchContract.Screen screen) {
        super(dataFaker, screen);
    }

    @Override
    public void runValidations(FlowHandlerCallback callback) {
        if (logicHandler.userHasSomeValidProperty()) {
            executeNext(callback);
        } else {
            executeEarlyExit(callback);
        }
    }
}
