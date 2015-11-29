package de.christianspecht.tasko.androidclient;

/**
 * Interface for the callback after ApiRequest call
 */
public interface ApiRequestCallback {
	void processResponse(ApiRequestResult result);
}
