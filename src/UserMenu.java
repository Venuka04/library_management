import javax.swing.*;

import net.proteanit.sql.DbUtils;

import java.awt.event.*;
import java.sql.*;

public class UserMenu {

    public static void user_menu(String UID) {
        JFrame frame = new JFrame("User Functions");

        // Button to View Available Books
        JButton viewBooksButton = new JButton("View Books");
        viewBooksButton.setBounds(20, 20, 120, 25);
        viewBooksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewAvailableBooks();
            }
        });

        // Button to View Issued Books
        JButton myBooksButton = new JButton("My Books");
        myBooksButton.setBounds(150, 20, 120, 25);
        myBooksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewMyBooks(UID);
            }
        });

        // Adding buttons to frame
        frame.add(viewBooksButton);
        frame.add(myBooksButton);

        // Frame settings
        frame.setSize(300, 100);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit application on close
    }

    // Method to view all available books
    private static void viewAvailableBooks() {
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

    // Method to view books issued by the user
    private static void viewMyBooks(String UID) {
        JFrame myBooksFrame = new JFrame("My Books");
        myBooksFrame.setSize(800, 400);
        myBooksFrame.setLocationRelativeTo(null);

        Connection connection = Connect.connect();
        if (connection == null) {
            JOptionPane.showMessageDialog(myBooksFrame, "Database connection failed.", "Connection Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sql = "SELECT DISTINCT issued.*, books.BNAME, books.GENRE, books.PRICE FROM ISSUED, BOOKS "
                + "WHERE ISSUED.UID=" + UID + " AND BOOKS.BID = ISSUED.BID GROUP BY ISSUED.IID";

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("USE LIBRARY");
            ResultSet rs = stmt.executeQuery(sql);

            // Using DbUtils to populate JTable
            JTable myBooksTable = new JTable(DbUtils.resultSetToTableModel(rs));
            JScrollPane scrollPane = new JScrollPane(myBooksTable);

            myBooksFrame.add(scrollPane);
            myBooksFrame.setVisible(true);

            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(myBooksFrame, e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
