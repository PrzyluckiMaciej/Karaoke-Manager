package pollub.karaokeapp.Week4.proxy;

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
    private String userRole;
    private boolean isAuthenticated;
    private String authToken;

    public UserAuthenticationProxy(User user, String userRole) {
        super(user.getNickname(), user.getLevel(), user.getPoints());
        this.realUser = user;
        this.userRole = userRole;
        this.isAuthenticated = false;
        this.authToken = null;
    }

    // Autentykacja użytkownika
    public boolean authenticate(String password) {
        logger.log("[AUTH-PROXY] Autentykacja użytkownika: " + realUser.getNickname());
        // Symulacja weryfikacji hasła
        if (validatePassword(password)) {
            isAuthenticated = true;
            authToken = generateToken();
            logger.log("[AUTH-PROXY] ✓ Autentykacja pomyślna");
            return true;
        }
        logger.log("[AUTH-PROXY] ✗ Autentykacja nieudana");
        return false;
    }

    // Sprawdzenie, czy użytkownik ma dostęp do funkcji
    private void checkAccess(String feature) {
        if (!isAuthenticated) {
            throw new SecurityException("[AUTH-PROXY] Dostęp odmówiony - użytkownik nie jest zalogowany!");
        }
        if (!hasPermission(feature)) {
            throw new SecurityException("[AUTH-PROXY] Dostęp odmówiony - brak uprawnień do: " + feature);
        }
    }

    @Override
    public int getPoints() {
        checkAccess("read_points");
        logger.log("[AUTH-PROXY] Dostęp przyznany do odczytu punktów");
        return realUser.getPoints();
    }

    @Override
    public void setPoints(int points) {
        checkAccess("write_points");
        realUser.setPoints(points);
        logger.log("[AUTH-PROXY] Punkty zaktualizowane");
    }

    @Override
    public int getLevel() {
        checkAccess("read_level");
        return realUser.getLevel();
    }

    @Override
    public void setLevel(int level) {
        checkAccess("write_level");
        realUser.setLevel(level);
        logger.log("[AUTH-PROXY] Poziom zaktualizowany");
    }

    @Override
    public String getNickname() {
        return realUser.getNickname();
    }

    @Override
    public String toString() {
        if (!isAuthenticated) {
            return "User{nickname='" + realUser.getNickname() + "', [NOT AUTHENTICATED]}";
        }
        return realUser.toString();
    }

    // --- Prywatne metody pomocnicze ---
    private boolean validatePassword(String password) {
        // Symulacja weryfikacji
        return password.equals("password123");
    }

    private String generateToken() {
        return "TOKEN_" + System.currentTimeMillis();
    }

    private boolean hasPermission(String feature) {
        if (userRole.equals("admin")) {
            return true;
        }
        if (userRole.equals("premium")) {
            return !feature.equals("admin_only");
        }
        return feature.equals("read_points") || feature.equals("read_level");
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public String getUserRole() {
        return userRole;
    }

    public void logout() {
        isAuthenticated = false;
        authToken = null;
        logger.log("[AUTH-PROXY] Użytkownik wylogowany");
    }
}
// Koniec, Tydzień 4, Wzorzec Proxy 3
