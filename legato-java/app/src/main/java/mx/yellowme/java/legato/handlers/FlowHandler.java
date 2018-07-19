package mx.yellowme.java.legato.handlers;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Base flow handler class.
 *
 * This class allows to create a chain of flow validation with interruption callbacks.
 *
 * Created by luisburgos on 5/24/18.
 */
public abstract class FlowHandler<LogicHandler, ConcreteFlow> {

    @Nullable
    private FlowHandler next;
    @NonNull
    private ConcreteFlow concreteFlow;
    @NonNull
    LogicHandler logicHandler;

    public FlowHandler(@NonNull LogicHandler logicHandler, @NonNull ConcreteFlow concreteFlow) {
        this.logicHandler = logicHandler;
        this.concreteFlow = concreteFlow;
    }

    /**
     * Builds chains of handler objects.
     */
    public FlowHandler linkWith(FlowHandler next) {
        this.next = next;
        return next;
    }

    /**
     * Subclasses will implement this method with concrete validations.
     *
     * IMPORTANT: This method should call
     * {@link FlowHandler#executeNext(FlowHandlerCallback)} method in order to
     * continue with the chain.
     *
     * Also you are responsible for calling the method
     * {@link FlowHandlerCallback#onEarlyExit(Object)}
     * which is the interruption assigned on the concrete handler.
     */
    public abstract void runValidations(FlowHandlerCallback callback);

    /**
     * Runs the {@link FlowHandler#runValidations(FlowHandlerCallback)} ()} method on the
     * next not null handler.
     *
     * The chain will reach the end if there is no next handler on the chain. If the
     * chain reaches the end and the success execution callback will be called.
     */
    void executeNext(FlowHandlerCallback callback) {
        if (next != null) {
            next.runValidations(callback);
        } else {
            callback.onEndReached();
        }
    }

    void executeEarlyExit(FlowHandlerCallback callback) {
        callback.onEarlyExit(concreteFlow);
    }

    /**
     * Notifies when the chain of flow reaches the end with success.
     */
    public interface FlowHandlerCallback {
        void onEndReached();
        void onEarlyExit(Object flow);
    }

}