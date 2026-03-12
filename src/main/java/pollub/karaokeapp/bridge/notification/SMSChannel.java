package pollub.karaokeapp.bridge.notification;

import pollub.karaokeapp.model.user.User;

/**
 * Tydzień 3, Wzorzec Bridge 4
 * Konkretna implementacja - kanał SMS
 */
public class SMSChannel implements NotificationChannel {

    private String phoneNumber;
    private int creditBalance;

    public SMSChannel(String phoneNumber, int creditBalance) {
        this.phoneNumber = phoneNumber;
        this.creditBalance = creditBalance;
    }

    @Override
    public void deliver(String message, User user) {
        if (creditBalance > 0) {
            System.out.println("📱 SMS na numer: " + phoneNumber);
            System.out.println("   Treść: " + message.substring(0, Math.min(160, message.length())));
            System.out.println("   Pozostało SMS: " + (--creditBalance));
        } else {
            System.out.println("⚠ Brak środków na SMS dla " + user.getNickname());
        }
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
// Koniec, Tydzień 3, Wzorzec Bridge 4