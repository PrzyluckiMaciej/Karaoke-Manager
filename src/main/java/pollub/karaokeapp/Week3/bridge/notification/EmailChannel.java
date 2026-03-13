// EmailChannel.java
package pollub.karaokeapp.Week3.bridge.notification;

import pollub.karaokeapp.model.user.User;

/**
 * Tydzień 3, Wzorzec Bridge 4
 * Konkretna implementacja - kanał email
 */
public class EmailChannel implements NotificationChannel {

    private String serverAddress;
    private boolean isConnected;

    public EmailChannel(String serverAddress) {
        this.serverAddress = serverAddress;
        this.isConnected = true;
    }

    @Override
    public void deliver(String message, User user) {
        System.out.println("📧 EMAIL do: " + user.getNickname() + " (" + user.getNickname() + "@example.com)");
        System.out.println("   Serwer: " + serverAddress);
        System.out.println("   Treść: " + message);
        System.out.println("   --- Koniec emaila ---");
    }

    @Override
    public boolean isAvailable() {
        return isConnected;
    }

    @Override
    public String getChannelName() {
        return "Email (" + serverAddress + ")";
    }
}
// Koniec, Tydzień 3, Wzorzec Bridge 4
