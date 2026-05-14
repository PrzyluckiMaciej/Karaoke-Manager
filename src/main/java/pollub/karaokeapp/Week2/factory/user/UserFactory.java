package pollub.karaokeapp.Week2.factory.user;

import pollub.karaokeapp.Week2.KaraokeConstants;
import pollub.karaokeapp.model.user.User;

/**
 * Tydzień 2, Wzorzec Factory 2
 * Zastosowano Simple Factory do tworzenia różnych typów użytkowników.
 */
public class UserFactory {

    public static User createUser(String type, String nickname) {

        switch (type.toLowerCase()) {
            case "standard":
                return new User(nickname, KaraokeConstants.USER_DEFAULT_LEVEL, KaraokeConstants.USER_DEFAULT_POINTS);
            case "premium":
                return new User(nickname, KaraokeConstants.USER_PREMIUM_LEVEL, KaraokeConstants.USER_PREMIUM_POINTS);
            case "admin":
                return new User(nickname, KaraokeConstants.USER_ADMIN_LEVEL, KaraokeConstants.USER_ADMIN_POINTS);
            case "guest":
                return new User(nickname, KaraokeConstants.USER_GUEST_LEVEL, KaraokeConstants.USER_GUEST_POINTS);
            default:
                throw new IllegalArgumentException("Nieznany typ użytkownika!");
        }
    }
}
//Koniec, Tydzień 2, Wzorzec Factory 2