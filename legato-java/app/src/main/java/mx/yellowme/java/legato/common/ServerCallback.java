package mx.yellowme.java.legato.common;

public interface ServerCallback {

	void onNetworkError();

	void onServerError(String error);

}