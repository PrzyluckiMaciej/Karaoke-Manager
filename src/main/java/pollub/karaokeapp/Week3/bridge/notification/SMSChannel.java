package pollub.karaokeapp.Week3.bridge.notification;

import pollub.karaokeapp.Week3.bridge.constants.BridgeConstants;
import pollub.karaokeapp.model.user.User;

/**
 * Tydzień 3, Wzorzec Bridge 4
 * Konkretna implementacja - kanał SMS
 */
public class SMSChannel implements NotificationChannel {

    private final String phoneNumber;
    private int creditBalance;

    public SMSChannel(String phoneNumber, int creditBalance) {
        this.phoneNumber = phoneNumber;
        this.creditBalance = creditBalance;
    }

    @Override
    public void deliver(String message, User user) {
        if (!isAvailable()) {
            throw new ChannelUnavailableException(
                    "Brak środków na SMS dla użytkownika: " + user.getNickname()
            );
        }

        String truncatedMessage = truncateMessage(message);
        creditBalance--;

        System.out.println("📱 SMS na numer: " + phoneNumber);
        System.out.println("   Treść: " + truncatedMessage);
        System.out.println("   Pozostało SMS: " + creditBalance);
    }

    private String truncateMessage(String message) {
        if (message.length() <= BridgeConstants.SMS_MAX_LENGTH) {
            return message;
        }
        return message.substring(0, BridgeConstants.SMS_MAX_LENGTH);
    }

    @Override
    public boolean isAvailable() {
        return creditBalance > 0;
    }

    @Override
    public String getChannelName() {
        return "SMS (nr: " + phoneNumber + ", saldo: " + creditBalance + ")";
    }
}

class ChannelUnavailableException extends RuntimeException {
    public ChannelUnavailableException(String message) {
        super(message);
    }
}
// Koniec, Tydzień 3, Wzorzec Bridge 4