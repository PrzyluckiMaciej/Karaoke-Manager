package pollub.karaokeapp.composite.performer;

import pollub.karaokeapp.model.user.User;

/**
 * Tydzień 3, Wzorzec Composite 2
 * Interfejs dla wykonawców (pojedynczych lub grup)
 */
public interface PerformerComponent {
    String getName();
    int getTotalLevel();
    int getMemberCount();
    void perform(String indent);
    User getUser(int index);
}
// Koniec, Tydzień 3, Wzorzec Composite 2