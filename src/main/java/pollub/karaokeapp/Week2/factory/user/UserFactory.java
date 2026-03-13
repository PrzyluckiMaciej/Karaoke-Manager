package pollub.karaokeapp.Week2.factory.user;

import pollub.karaokeapp.model.user.User;

/**
 * Tydzień 2, Wzorzec Factory 2
 * Zastosowano Simple Factory do tworzenia różnych typów użytkowników.
 */
public class UserFactory {

    public static User createUser(String type, String nickname) {

        switch (type.toLowerCase()) {
            case "standard":
                return new User(nickname, 1, 0);
            case "premium":
                return new User(nickname, 5, 100);
            case "admin":
                return new User(nickname, 10, 1000);
            case "guest":
                return new User(nickname, 0, 0);
            default:
                throw new IllegalArgumentException("Nieznany typ użytkownika!");
        }
    }
}
//Koniec, Tydzień 2, Wzorzec Factory 2