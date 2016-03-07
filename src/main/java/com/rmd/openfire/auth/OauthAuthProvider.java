
package com.rmd.openfire.auth;

import org.jivesoftware.openfire.auth.AuthProvider;
import org.jivesoftware.openfire.auth.ConnectionException;
import org.jivesoftware.openfire.auth.InternalUnauthenticatedException;
import org.jivesoftware.openfire.auth.UnauthorizedException;
import org.jivesoftware.openfire.user.UserNotFoundException;
import org.jivesoftware.util.Log;

public class OauthAuthProvider implements AuthProvider {

    public OauthAuthProvider() {
        // Needs to to be here otherwise the plugin wouldn't work
    }

    /**
     * Returns true if this AuthProvider supports authentication using
     * plain-text passwords according to JEP--0078. Plain text authentication is
     * not secure and should generally only be used for a TLS/SSL connection.
     *
     * @return <code>true</code> if plain text password authentication is
     * supported by this AuthProvider, otherwise <code>false</code>.
     */
    public boolean isPlainSupported() {
        return true;
    }

    /**
     * Returns true if this AuthProvider supports digest authentication
     * according to JEP-0078.
     *
     * @return <code>true</code> if digest authentication is supported by this
     * AuthProvider, otherwise <code>false</code>.
     */
    public boolean isDigestSupported() {
        return false;
    }

    /**
     * Returns if the username and password are valid; otherwise this method
     * throws an <code>UnauthorizedException</code>.
     * <p/>
     * <p/>
     * If {@link #isPlainSupported()} returns false, this method should throw an
     * <code>UnsupportedOperationException</code>.
     *
     * @param username The username or full JID.
     * @param password The shared secret password.
     * @throws UnauthorizedException            If the username and password do not match any existing user.
     * @throws ConnectionException              If Openfire is not able to connect to the user and group system.
     * @throws InternalUnauthenticatedException If Openfire is not able to authenticate itself into the user
     *                                          and group system.
     */
    public void authenticate(final String username, final String password)
            throws UnauthorizedException, ConnectionException, InternalUnauthenticatedException {

        if (Log.isDebugEnabled()) {
            Log.debug(String.format("Username: %s logged with shared secret: %s", username, password));
        }

        String sharedSecret = "12345";

        // Check the shared secret
        if (!password.equals(sharedSecret)) {
            throw new UnauthorizedException(String.format("User %s provided wrong shared secret", username));
        }
    }

    /**
     * Returns if the username, token, and digest are valid; otherwise this
     * method throws an <code>UnauthorizedException</code>.
     * <p/>
     * <p/>
     * If {@link #isDigestSupported()} returns false, this method should throw
     * an <code>UnsupportedOperationException</code>.
     *
     * @param username The username or full JID.
     * @param token    The token that was used with plain-text password to generate
     *                 the digest.
     * @param digest   The digest generated from plain-text password and unique
     *                 token.
     * @throws UnauthorizedException            If the username and password do not match any existing user.
     * @throws ConnectionException              If Openfire is not able to connect to the user and group system.
     * @throws InternalUnauthenticatedException If Openfire is not able to authenticate itself into the user
     *                                          and group system.
     */
    public void authenticate(final String username, final String token, final String digest)
            throws UnauthorizedException, ConnectionException, InternalUnauthenticatedException {
        throw new UnsupportedOperationException("Digest is unsupported");
    }

    /**
     * Returns the user's password. This method should throw an
     * <code>UnsupportedOperationException</code> if this operation is not
     * supported by the backend user store.
     *
     * @param username The username of the user.
     * @return The user's password.
     * @throws UserNotFoundException         If the given user's password could not be loaded.
     * @throws UnsupportedOperationException If the provider does not support the operation (this is an
     *                                       optional operation).
     */
    public String getPassword(final String username) throws UserNotFoundException, UnsupportedOperationException {
        throw new UnsupportedOperationException("Passwords are one-way hashed and cannot be recovered");
    }

    /**
     * Sets the users's password. This method should throw an
     * <code>UnsupportedOperationException</code> if this operation is not
     * supported by the backend user store.
     *
     * @param username The username of the user.
     * @param password The new plaintext password for the user.
     * @throws UserNotFoundException         If the given user could not be loaded.
     * @throws UnsupportedOperationException If the provider does not support the operation (this is an
     *                                       optional operation).
     */
    public void setPassword(final String username, final String password)
            throws UserNotFoundException, UnsupportedOperationException {
        throw new UnsupportedOperationException("Passwords are one-way hashed and cannot be recovered");
    }

    /**
     * Returns true if this UserProvider is able to retrieve user passwords from
     * the backend user store. If this operation is not supported then
     * {@link #getPassword(String)} will throw an
     * {@link UnsupportedOperationException} if invoked.
     *
     * @return true if this UserProvider is able to retrieve user passwords from
     * the backend user store.
     */
    public boolean supportsPasswordRetrieval() {
        return false;
    }
}
