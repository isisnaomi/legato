package mx.yellowme.java.legato.common;

/**
 * Provides common use methods when interacting with a view (MVP) component
 *
 * Created by luisburgos on 6/19/18.
 */
public interface CommonBaseView {

    void setProgress(boolean isActive);

    void display(String message);
    
}