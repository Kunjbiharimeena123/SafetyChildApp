import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;

public class ChildSafetyApp extends JFrame {

    // Parent Panel
    private JPanel parentPanel;
    
    // Labels
    private JLabel currentLocationLabel;
    private JLabel locationHistoryLabel;
    private JLabel geofenceStatusLabel;
    private JLabel childImageLabel;

    // Buttons
    private JButton geofenceButton;
    private JButton trackButton;
    private JButton sosButton;
    private JButton viewHistoryButton;
    private JButton exitButton;

    // Image Icon for the child
    private ImageIcon childIcon;

    // Location tracking simulation
    private String currentLocation = "Home";
    private Set<String> unfamiliarPlaces = new HashSet<>();  // A set to store unfamiliar places
    private Timer movementTimer;

    // Constructor for GUI layout
    public ChildSafetyApp() {
        // Basic Frame Settings
        setTitle("Child Safety Tracking Application");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize Panels
        parentPanel = new JPanel();
        parentPanel.setLayout(new BorderLayout(10, 10));
        parentPanel.setBackground(new Color(245, 245, 245)); // Light background for better contrast

        // Initialize Labels
        currentLocationLabel = new JLabel("Current Location: Home", SwingConstants.CENTER);
        currentLocationLabel.setFont(new Font("Arial", Font.BOLD, 18));

        locationHistoryLabel = new JLabel("Recent History: Home", SwingConstants.CENTER);
        locationHistoryLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        geofenceStatusLabel = new JLabel("Geofence: Not Set", SwingConstants.CENTER);
        geofenceStatusLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        // Load child image (example path, you should place a valid image file)
        childIcon = new ImageIcon("images.jpeg"); // Replace with your own image path
        childImageLabel = new JLabel(childIcon);

        // Initialize Buttons
        geofenceButton = new JButton("Set Geofence");
        trackButton = new JButton("Track Location");
        sosButton = new JButton("SOS Alert");
        viewHistoryButton = new JButton("View Location History");
        exitButton = new JButton("Exit");

        // Button Customizations
        customizeButton(geofenceButton);
        customizeButton(trackButton);
        customizeButton(sosButton);
        customizeButton(viewHistoryButton);
        customizeButton(exitButton);

        // Action Listeners for Buttons
        geofenceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setGeofence();
            }
        });

        trackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trackLocation();
            }
        });

        sosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                triggerSOS();
            }
        });

        viewHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewHistory();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Layout of components
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 3, 10, 10));
        buttonPanel.setBackground(new Color(245, 245, 245)); // Match the parent panel background
        buttonPanel.add(geofenceButton);
        buttonPanel.add(trackButton);
        buttonPanel.add(sosButton);
        buttonPanel.add(viewHistoryButton);
        buttonPanel.add(exitButton);

        // Add components to parent panel
        parentPanel.add(childImageLabel, BorderLayout.WEST);
        parentPanel.add(currentLocationLabel, BorderLayout.NORTH);
        parentPanel.add(locationHistoryLabel, BorderLayout.CENTER);
        parentPanel.add(geofenceStatusLabel, BorderLayout.SOUTH);
        parentPanel.add(buttonPanel, BorderLayout.EAST);

        // Add parent panel to frame
        add(parentPanel);

        // Initialize the unfamiliar places
        unfamiliarPlaces.add("Park");
        unfamiliarPlaces.add("Mall");
        unfamiliarPlaces.add("Supermarket");

        // Set up the movement timer to simulate child moving
        startMovementTimer();
    }

    // Start the movement timer to simulate child location updates
    private void startMovementTimer() {
        movementTimer = new Timer(3000, new ActionListener() { // Update every 3 seconds
            @Override
            public void actionPerformed(ActionEvent e) {
                // Simulate random movement (for demonstration purposes)
                simulateMovement();
            }
        });
        movementTimer.start();
    }

    // Simulate movement and check if the child has entered an unfamiliar place
    private void simulateMovement() {
        String[] possibleLocations = {"Home", "School", "Park", "Mall", "Supermarket", "Library", "Restaurant"};
        int randomIndex = (int) (Math.random() * possibleLocations.length);
        currentLocation = possibleLocations[randomIndex];

        currentLocationLabel.setText("Current Location: " + currentLocation);

        // Check if the current location is in the unfamiliar places
        if (unfamiliarPlaces.contains(currentLocation)) {
            notifyUnfamiliarPlace();
        }
    }

    // Notify the parent when the child enters an unfamiliar place
    private void notifyUnfamiliarPlace() {
        JOptionPane.showMessageDialog(this, "Alert! Your child has entered an unfamiliar place: " + currentLocation);
    }

    // Method to simulate setting geofence
    private void setGeofence() {
        geofenceStatusLabel.setText("Geofence: Home Zone Set");
        JOptionPane.showMessageDialog(this, "Geofence set for the Home zone.");
    }

    // Method to simulate tracking location
    private void trackLocation() {
        currentLocationLabel.setText("Current Location: School");
        locationHistoryLabel.setText("Recent History: School -> Park");
        JOptionPane.showMessageDialog(this, "Tracking child to School.");
    }

    // Method to simulate SOS alert
    private void triggerSOS() {
        JOptionPane.showMessageDialog(this, "SOS Alert sent to parents! Location: Park.");
        currentLocationLabel.setText("Current Location: Emergency Alert Triggered");
    }

    // Method to view location history
    private void viewHistory() {
        JOptionPane.showMessageDialog(this, "Location History:\n1. School -> Park\n2. Park -> Mall");
    }

    // Method to customize button styles
    private void customizeButton(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setBackground(new Color(60, 179, 113)); // Set green color for buttons
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(150, 40)); // Customize button size
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change cursor to hand when hovered
    }

    public static void main(String[] args) {
        // Create and display the application window
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ChildSafetyApp().setVisible(true);
            }
        });
    }
}
