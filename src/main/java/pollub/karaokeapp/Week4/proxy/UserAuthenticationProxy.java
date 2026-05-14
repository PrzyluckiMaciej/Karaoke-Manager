package pollub.karaokeapp.Week4.proxy;

import pollub.karaokeapp.Week4.common.AccessPermission;
import pollub.karaokeapp.Week4.common.TestCredentials;
import pollub.karaokeapp.model.user.User;
import pollub.karaokeapp.Week2.singleton.LoggerSingleton;

/**
 * Tydzień 4, Wzorzec Proxy 3
 * Proxy dla kontroli dostępu - autentykacja i autoryzacja
 * Kontroluje dostęp do danych użytkownika na podstawie uprawnień
 */
public class UserAuthenticationProxy extends User {
    private final User realUser;
    private final LoggerSingleton logger = LoggerSingleton.getInstance();
    private final String userRole;
    private boolean isAuthenticated;
    private String authToken;

    public UserAuthenticationProxy(User user, String userRole) {
        super(user.getNickname(), user.getLevel(), user.getPoints());
        this.realUser = user;
        this.userRole = userRole;
        this.isAuthenticated = false;
        this.authToken = null;
    }

    public boolean authenticate(String password) {
        logAuthenticationAttempt();
        if (isValidPassword(password)) {
            completeSuccessfulAuthentication();
            return true;
        }
        logFailedAuthentication();
        return false;
    }

    private void logAuthenticationAttempt() {
        logger.log("[AUTH-PROXY] Autentykacja użytkownika: " + realUser.getNickname());
    }

    private boolean isValidPassword(String password) {
        return TestCredentials.TEST_PASSWORD.equals(password);
    }

    private void completeSuccessfulAuthentication() {
        isAuthenticated = true;
        authToken = generateToken();
        logger.log("[AUTH-PROXY] ✓ Autentykacja pomyślna");
    }

    private void logFailedAuthentication() {
        logger.log("[AUTH-PROXY] ✗ Autentykacja nieudana");
    }

    private void checkAccess(AccessPermission permission) {
        verifyAuthentication();
        verifyPermission(permission);
    }

    private void verifyAuthentication() {
        if (!isAuthenticated) {
            throw new SecurityException("[AUTH-PROXY] Dostęp odmówiony - użytkownik nie jest zalogowany!");
        }
    }

    private void verifyPermission(AccessPermission permission) {
        if (!hasPermission(permission)) {
            throw new SecurityException("[AUTH-PROXY] Dostęp odmówiony - brak uprawnień do: " + permission);
        }
    }

    @Override
    public int getPoints() {
        checkAccess(AccessPermission.READ_POINTS);
        logger.log("[AUTH-PROXY] Dostęp przyznany do odczytu punktów");
        return realUser.getPoints();
    }

    @Override
    public void setPoints(int points) {
        checkAccess(AccessPermission.WRITE_POINTS);
        realUser.setPoints(points);
        logger.log("[AUTH-PROXY] Punkty zaktualizowane");
    }

    @Override
    public int getLevel() {
        checkAccess(AccessPermission.READ_LEVEL);
        return realUser.getLevel();
    }

    @Override
    public void setLevel(int level) {
        checkAccess(AccessPermission.WRITE_LEVEL);
        realUser.setLevel(level);
        logger.log("[AUTH-PROXY] Poziom zaktualizowany");
    }

    @Override
    public String getNickname() {
        return realUser.getNickname();
    }

    private String generateToken() {
        return "TOKEN_" + System.currentTimeMillis();
    }

    private boolean hasPermission(AccessPermission permission) {
        if ("admin".equals(userRole)) {
            return true;
        }
        if ("premium".equals(userRole)) {
            return permission != AccessPermission.ADMIN_ONLY;
        }
        return permission == AccessPermission.READ_POINTS || permission == AccessPermission.READ_LEVEL;
    }

    public boolean isAuthenticated() { return isAuthenticated; }
    public String getUserRole() { return userRole; }

    public void logout() {
        isAuthenticated = false;
        authToken = null;
        logger.log("[AUTH-PROXY] Użytkownik wylogowany");
    }

    @Override
    public String toString() {
        if (!isAuthenticated) {
            return "User{nickname='" + realUser.getNickname() + "', [NOT AUTHENTICATED]}";
        }
        return realUser.toString();
    }
}
// Koniec, Tydzień 4, Wzorzec Proxy 3
