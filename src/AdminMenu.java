import javax.swing.*;

import net.proteanit.sql.DbUtils;

import java.awt.event.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class AdminMenu {

    public static void admin_menu() {
        JFrame frame = new JFrame("Admin Functions");

        // Buttons
        JButton viewBooksButton = new JButton("View Books");
        viewBooksButton.setBounds(20, 20, 120, 25);
        viewBooksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewBooks();
            }
        });

        JButton viewUsersButton = new JButton("View Users");
        viewUsersButton.setBounds(150, 20, 120, 25);
        viewUsersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewUsers();
            }
        });

        JButton issuedBooksButton = new JButton("View Issued Books");
        issuedBooksButton.setBounds(280, 20, 160, 25);
        issuedBooksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewIssuedBooks();
            }
        });

        JButton addUserButton = new JButton("Add User");
        addUserButton.setBounds(20, 60, 120, 25);
        addUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addUser();
            }
        });

        JButton addBookButton = new JButton("Add Book");
        addBookButton.setBounds(150, 60, 120, 25);
        addBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });

        JButton issueBookButton = new JButton("Issue Book");
        issueBookButton.setBounds(450, 20, 120, 25);
        issueBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                issueBook();
            }
        });

        JButton returnBookButton = new JButton("Return Book");
        returnBookButton.setBounds(280, 60, 160, 25);
        returnBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                returnBook();
            }
        });

        // Adding buttons to frame
        frame.add(viewBooksButton);
        frame.add(viewUsersButton);
        frame.add(issuedBooksButton);
        frame.add(addUserButton);
        frame.add(addBookButton);
        frame.add(issueBookButton);
        frame.add(returnBookButton);

        // Frame settings
        frame.setSize(600, 200);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit application on close
    }

    // Method to view all books
    private static void viewBooks() {
        JFrame booksFrame = new JFrame("Books Available");
        booksFrame.setSize(800, 400);
        booksFrame.setLocationRelativeTo(null);

        Connection connection = Connect.connect();
        if (connection == null) {
            JOptionPane.showMessageDialog(booksFrame, "Database connection failed.", "Connection Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sql = "SELECT * FROM BOOKS";
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("USE LIBRARY");
            ResultSet rs = stmt.executeQuery(sql);

            // Using DbUtils to populate JTable
            JTable booksTable = new JTable(DbUtils.resultSetToTableModel(rs));
            JScrollPane scrollPane = new JScrollPane(booksTable);

            booksFrame.add(scrollPane);
            booksFrame.setVisible(true);

            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(booksFrame, e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to view all users
    private static void viewUsers() {
        JFrame usersFrame = new JFrame("Users List");
        usersFrame.setSize(800, 400);
        usersFrame.setLocationRelativeTo(null);

        Connection connection = Connect.connect();
        if (connection == null) {
            JOptionPane.showMessageDialog(usersFrame, "Database connection failed.", "Connection Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sql = "SELECT * FROM USERS";
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("USE LIBRARY");
            ResultSet rs = stmt.executeQuery(sql);

            // Using DbUtils to populate JTable
            JTable usersTable = new JTable(DbUtils.resultSetToTableModel(rs));
            JScrollPane scrollPane = new JScrollPane(usersTable);

            usersFrame.add(scrollPane);
            usersFrame.setVisible(true);

            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(usersFrame, e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to view all issued books
    private static void viewIssuedBooks() {
        JFrame issuedBooksFrame = new JFrame("Issued Books");
        issuedBooksFrame.setSize(800, 400);
        issuedBooksFrame.setLocationRelativeTo(null);

        Connection connection = Connect.connect();
        if (connection == null) {
            JOptionPane.showMessageDialog(issuedBooksFrame, "Database connection failed.", "Connection Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sql = "SELECT * FROM ISSUED";
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("USE LIBRARY");
            ResultSet rs = stmt.executeQuery(sql);

            // Using DbUtils to populate JTable
            JTable issuedBooksTable = new JTable(DbUtils.resultSetToTableModel(rs));
            JScrollPane scrollPane = new JScrollPane(issuedBooksTable);

            issuedBooksFrame.add(scrollPane);
            issuedBooksFrame.setVisible(true);

            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(issuedBooksFrame, e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to add a new user
    private static void addUser() {
        JFrame addUserFrame = new JFrame("Enter User Details");
        addUserFrame.setSize(350, 200);
        addUserFrame.setLocationRelativeTo(null);
        addUserFrame.setLayout(null);

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

        // Radio Buttons for Role
        JRadioButton adminRadio = new JRadioButton("Admin");
        adminRadio.setBounds(55, 80, 80, 30);
        JRadioButton userRadio = new JRadioButton("User");
        userRadio.setBounds(130, 80, 80, 30);

        // Grouping Radio Buttons
        ButtonGroup roleGroup = new ButtonGroup();
        roleGroup.add(adminRadio);
        roleGroup.add(userRadio);

        // Submit Button
        JButton submitButton = new JButton("Create");
        submitButton.setBounds(130, 130, 80, 25);

        // Action Listener for Submit Button
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = String.valueOf(passwordField.getPassword()).trim();
                boolean isAdmin = adminRadio.isSelected();

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(addUserFrame, "Username and Password cannot be empty.", "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Connection connection = Connect.connect();
                if (connection == null) {
                    JOptionPane.showMessageDialog(addUserFrame, "Database connection failed.", "Connection Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String sql = "INSERT INTO USERS (USERNAME, PASSWORD, ADMIN) VALUES (?, ?, ?)";
                try {
                    PreparedStatement pstmt = connection.prepareStatement(sql);
                    pstmt.setString(1, username);
                    pstmt.setString(2, password);
                    pstmt.setBoolean(3, isAdmin);
                    pstmt.executeUpdate();

                    JOptionPane.showMessageDialog(addUserFrame, "User added successfully!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    addUserFrame.dispose();
                    pstmt.close();
                    connection.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(addUserFrame, ex.getMessage(), "SQL Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Adding components to frame
        addUserFrame.add(usernameLabel);
        addUserFrame.add(passwordLabel);
        addUserFrame.add(usernameField);
        addUserFrame.add(passwordField);
        addUserFrame.add(adminRadio);
        addUserFrame.add(userRadio);
        addUserFrame.add(submitButton);

        addUserFrame.setVisible(true);
    }

    // Method to add a new book
    private static void addBook() {
        JFrame addBookFrame = new JFrame("Enter Book Details");
        addBookFrame.setSize(350, 200);
        addBookFrame.setLocationRelativeTo(null);
        addBookFrame.setLayout(null);

        // Labels
        JLabel nameLabel = new JLabel("Book Name");
        nameLabel.setBounds(30, 15, 100, 30);

        JLabel genreLabel = new JLabel("Genre");
        genreLabel.setBounds(30, 53, 100, 30);

        JLabel priceLabel = new JLabel("Price");
        priceLabel.setBounds(30, 90, 100, 30);

        // Text Fields
        JTextField nameField = new JTextField();
        nameField.setBounds(110, 15, 200, 30);

        JTextField genreField = new JTextField();
        genreField.setBounds(110, 53, 200, 30);

        JTextField priceField = new JTextField();
        priceField.setBounds(110, 90, 200, 30);

        // Submit Button
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(130, 130, 80, 25);

        // Action Listener for Submit Button
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String bookName = nameField.getText().trim();
                String genre = genreField.getText().trim();
                String priceText = priceField.getText().trim();

                if (bookName.isEmpty() || genre.isEmpty() || priceText.isEmpty()) {
                    JOptionPane.showMessageDialog(addBookFrame, "All fields are required.", "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int price;
                try {
                    price = Integer.parseInt(priceText);
                    if (price < 0)
                        throw new NumberFormatException();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(addBookFrame, "Price must be a positive integer.", "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Connection connection = Connect.connect();
                if (connection == null) {
                    JOptionPane.showMessageDialog(addBookFrame, "Database connection failed.", "Connection Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String sql = "INSERT INTO BOOKS (BNAME, GENRE, PRICE) VALUES (?, ?, ?)";
                try {
                    PreparedStatement pstmt = connection.prepareStatement(sql);
                    pstmt.setString(1, bookName);
                    pstmt.setString(2, genre);
                    pstmt.setInt(3, price);
                    pstmt.executeUpdate();

                    JOptionPane.showMessageDialog(addBookFrame, "Book added successfully!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    addBookFrame.dispose();
                    pstmt.close();
                    connection.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(addBookFrame, ex.getMessage(), "SQL Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Adding components to frame
        addBookFrame.add(nameLabel);
        addBookFrame.add(genreLabel);
        addBookFrame.add(priceLabel);
        addBookFrame.add(nameField);
        addBookFrame.add(genreField);
        addBookFrame.add(priceField);
        addBookFrame.add(submitButton);

        addBookFrame.setVisible(true);
    }

    // Method to issue a book
    private static void issueBook() {
        JFrame issueBookFrame = new JFrame("Issue Book");
        issueBookFrame.setSize(350, 250);
        issueBookFrame.setLocationRelativeTo(null);
        issueBookFrame.setLayout(null);

        // Labels
        JLabel bidLabel = new JLabel("Book ID (BID)");
        bidLabel.setBounds(30, 15, 150, 30);

        JLabel uidLabel = new JLabel("User ID (UID)");
        uidLabel.setBounds(30, 53, 150, 30);

        JLabel periodLabel = new JLabel("Period (days)");
        periodLabel.setBounds(30, 90, 150, 30);

        JLabel issueDateLabel = new JLabel("Issued Date (DD-MM-YYYY)");
        issueDateLabel.setBounds(30, 127, 200, 30);

        // Text Fields
        JTextField bidField = new JTextField();
        bidField.setBounds(180, 15, 130, 30);

        JTextField uidField = new JTextField();
        uidField.setBounds(180, 53, 130, 30);

        JTextField periodField = new JTextField();
        periodField.setBounds(180, 90, 130, 30);

        JTextField issueDateField = new JTextField();
        issueDateField.setBounds(180, 130, 130, 30);

        // Submit Button
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(130, 170, 80, 25);

        // Action Listener for Submit Button
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String uid = uidField.getText().trim();
                String bid = bidField.getText().trim();
                String periodText = periodField.getText().trim();
                String issuedDate = issueDateField.getText().trim();

                if (uid.isEmpty() || bid.isEmpty() || periodText.isEmpty() || issuedDate.isEmpty()) {
                    JOptionPane.showMessageDialog(issueBookFrame, "All fields are required.", "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int period;
                try {
                    period = Integer.parseInt(periodText);
                    if (period <= 0)
                        throw new NumberFormatException();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(issueBookFrame, "Period must be a positive integer.", "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validate date format
                if (!isValidDate(issuedDate)) {
                    JOptionPane.showMessageDialog(issueBookFrame, "Issued Date must be in DD-MM-YYYY format.",
                            "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Connection connection = Connect.connect();
                if (connection == null) {
                    JOptionPane.showMessageDialog(issueBookFrame, "Database connection failed.", "Connection Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String sql = "INSERT INTO ISSUED (UID, BID, ISSUED_DATE, PERIOD) VALUES (?, ?, ?, ?)";
                try {
                    PreparedStatement pstmt = connection.prepareStatement(sql);
                    pstmt.setInt(1, Integer.parseInt(uid));
                    pstmt.setInt(2, Integer.parseInt(bid));
                    pstmt.setString(3, issuedDate);
                    pstmt.setInt(4, period);
                    pstmt.executeUpdate();

                    JOptionPane.showMessageDialog(issueBookFrame, "Book issued successfully!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    issueBookFrame.dispose();
                    pstmt.close();
                    connection.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(issueBookFrame, ex.getMessage(), "SQL Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Adding components to frame
        issueBookFrame.add(bidLabel);
        issueBookFrame.add(uidLabel);
        issueBookFrame.add(periodLabel);
        issueBookFrame.add(issueDateLabel);
        issueBookFrame.add(bidField);
        issueBookFrame.add(uidField);
        issueBookFrame.add(periodField);
        issueBookFrame.add(issueDateField);
        issueBookFrame.add(submitButton);

        issueBookFrame.setVisible(true);
    }

    // Method to return a book
    private static void returnBook() {
        JFrame returnBookFrame = new JFrame("Return Book");
        returnBookFrame.setSize(350, 200);
        returnBookFrame.setLocationRelativeTo(null);
        returnBookFrame.setLayout(null);

        // Labels
        JLabel iidLabel = new JLabel("Issue ID (IID)");
        iidLabel.setBounds(30, 15, 150, 30);

        JLabel returnDateLabel = new JLabel("Return Date (DD-MM-YYYY)");
        returnDateLabel.setBounds(30, 50, 200, 30);

        // Text Fields
        JTextField iidField = new JTextField();
        iidField.setBounds(180, 15, 130, 30);

        JTextField returnDateField = new JTextField();
        returnDateField.setBounds(180, 50, 130, 30);

        // Return Button
        JButton returnButton = new JButton("Return");
        returnButton.setBounds(130, 100, 80, 25);

        // Action Listener for Return Button
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String iid = iidField.getText().trim();
                String returnDate = returnDateField.getText().trim();

                if (iid.isEmpty() || returnDate.isEmpty()) {
                    JOptionPane.showMessageDialog(returnBookFrame, "All fields are required.", "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validate date format
                if (!isValidDate(returnDate)) {
                    JOptionPane.showMessageDialog(returnBookFrame, "Return Date must be in DD-MM-YYYY format.",
                            "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Connection connection = Connect.connect();
                if (connection == null) {
                    JOptionPane.showMessageDialog(returnBookFrame, "Database connection failed.", "Connection Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    connection.setAutoCommit(false); // Start transaction

                    Statement stmt = connection.createStatement();
                    stmt.executeUpdate("USE LIBRARY");

                    // Retrieve issued date and period
                    String issuedDate = null;
                    int period = 0;
                    String sqlRetrieve = "SELECT ISSUED_DATE, PERIOD FROM ISSUED WHERE IID = " + iid;
                    ResultSet rs = stmt.executeQuery(sqlRetrieve);
                    if (rs.next()) {
                        issuedDate = rs.getString("ISSUED_DATE");
                        period = rs.getInt("PERIOD");
                    } else {
                        JOptionPane.showMessageDialog(returnBookFrame, "Invalid Issue ID.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        rs.close();
                        stmt.close();
                        connection.close();
                        return;
                    }
                    rs.close();

                    // Calculate the number of days between issued date and return date
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    Date dateIssued = (Date) sdf.parse(issuedDate);
                    Date dateReturned = (Date) sdf.parse(returnDate);
                    long diffInMillies = dateReturned.getTime() - dateIssued.getTime();
                    int days = (int) TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

                    // Update return date
                    String sqlUpdateReturn = "UPDATE ISSUED SET RETURN_DATE = '" + returnDate + "' WHERE IID = " + iid;
                    stmt.executeUpdate(sqlUpdateReturn);

                    // Calculate fine if any
                    if (days > period) {
                        int fine = (days - period) * 10; // Rs. 10 per extra day
                        String sqlUpdateFine = "UPDATE ISSUED SET FINE = " + fine + " WHERE IID = " + iid;
                        stmt.executeUpdate(sqlUpdateFine);
                        JOptionPane.showMessageDialog(returnBookFrame, "Book returned with a fine of Rs. " + fine,
                                "Fine Applied", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(returnBookFrame, "Book returned successfully!", "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                    }

                    connection.commit(); // Commit transaction
                    stmt.close();
                    connection.close();
                    returnBookFrame.dispose();

                } catch (SQLException ex) {
                    try {
                        connection.rollback(); // Rollback transaction on error
                    } catch (SQLException rollbackEx) {
                        JOptionPane.showMessageDialog(returnBookFrame,
                                "Error during rollback: " + rollbackEx.getMessage(), "Rollback Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    JOptionPane.showMessageDialog(returnBookFrame, ex.getMessage(), "SQL Error",
                            JOptionPane.ERROR_MESSAGE);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(returnBookFrame, "Invalid date format.", "Date Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Adding components to frame
        returnBookFrame.add(iidLabel);
        returnBookFrame.add(returnDateLabel);
        returnBookFrame.add(iidField);
        returnBookFrame.add(returnDateField);
        returnBookFrame.add(returnButton);

        returnBookFrame.setVisible(true);
    }

    // Helper method to validate date format
    private static boolean isValidDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(false); // Strict parsing
        try {
            sdf.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
