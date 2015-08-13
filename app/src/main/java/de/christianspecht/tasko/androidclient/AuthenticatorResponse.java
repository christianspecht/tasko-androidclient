package de.christianspecht.tasko.androidclient;

/**
 * Interface for the result of authentication
 */
public interface AuthenticatorResponse {
	void processToken(String token);
}
