import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Login {

    public static void login() {
        JFrame frame = new JFrame("Login");

        // Labels
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(30, 15, 100, 30);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(30, 50, 100, 30);

        // Text Fields
        JTextField usernameField = new JTextField();
        usernameField.setBounds(110, 15, 200, 30);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(110, 50, 200, 30);

        // Login Button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(130, 90, 80, 25);

        // Action Listener for Login Button
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = String.valueOf(passwordField.getPassword()).trim();

                if (username.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter username.", "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (password.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter password.", "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Connection connection = Connect.connect();
                if (connection == null) {
                    JOptionPane.showMessageDialog(frame, "Database connection failed.", "Connection Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    Statement stmt = connection.createStatement();
                    stmt.executeUpdate("USE LIBRARY");
                    String query = "SELECT * FROM USERS WHERE USERNAME='" + username + "' AND PASSWORD='" + password
                            + "'";
                    ResultSet rs = stmt.executeQuery(query);

                    if (rs.next()) {
                        String isAdmin = rs.getString("ADMIN");
                        String UID = rs.getString("UID");
                        frame.dispose(); // Close login window

                        if ("1".equals(isAdmin)) {
                            AdminMenu.admin_menu();
                        } else {
                            UserMenu.user_menu(UID);
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid Username or Password!", "Login Failed",
                                JOptionPane.ERROR_MESSAGE);
                    }

                    rs.close();
                    stmt.close();
                    connection.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Adding components to frame
        frame.add(usernameLabel);
        frame.add(passwordLabel);
        frame.add(usernameField);
        frame.add(passwordField);
        frame.add(loginButton);

        // Frame settings
        frame.setSize(350, 180);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit application on close
    }
}
