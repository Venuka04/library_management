import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Run the login UI on the Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Login.login();
            }
        });
    }
}
