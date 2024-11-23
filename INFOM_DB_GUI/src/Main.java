import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Employee Database Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Main panel with CardLayout
        JPanel mainPanel = new JPanel(new CardLayout());

        // Create the individual panels
        JPanel mainMenuPanel = createMainMenu(mainPanel);
        JPanel hireTerminateTransferPanel = createHireTerminateTransferPanel(mainPanel);
        JPanel terminateEmployeePanel = createTerminatePanel(mainPanel);
        JPanel transferEmployeePanel = createTransferPanel(mainPanel);
        JPanel changeDisbursementPanel = createChangeDisbursementPanel(mainPanel);
        JPanel hireEmployeePanel = createHireEmployeePanel(mainPanel);
        JPanel changeStatusPanel = createChangeStatusPanel(mainPanel);
        JPanel performancePanel = createPerformancePanel(mainPanel);
        JPanel salaryReportPanel = createSalaryReportPanel(mainPanel);
        JPanel performanceReportPanel = createPerformanceReportPanel(mainPanel);

        // Add panels to CardLayout
        mainPanel.add(mainMenuPanel, "MainMenu");
        mainPanel.add(hireTerminateTransferPanel, "HireTerminateTransfer");
        mainPanel.add(terminateEmployeePanel, "TerminateEmployee");
        mainPanel.add(transferEmployeePanel, "TransferEmployee");
        mainPanel.add(changeDisbursementPanel, "ChangeDisbursement");
        mainPanel.add(hireEmployeePanel, "HireEmployee");
        mainPanel.add(changeStatusPanel, "ChangeStatus");
        mainPanel.add(performancePanel, "Performance");
        mainPanel.add(salaryReportPanel, "SalaryReport");
        mainPanel.add(performanceReportPanel, "PerformanceReport");

        // Add the main panel to the frame
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    // Helper method to create navigation buttons
    private static JButton createBackButton(JPanel mainPanel, String targetPanel) {
        JButton backButton = new JButton("Back to Main Menu");
        backButton.addActionListener(e -> navigateToPanel(mainPanel, targetPanel));
        return backButton;
    }

    // Helper method for navigation
    private static void navigateToPanel(JPanel mainPanel, String targetPanel) {
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, targetPanel);
    }

    // Main Menu Panel
    private static JPanel createMainMenu(JPanel mainPanel) {
        JPanel panel = new JPanel(new GridLayout(1, 2));

        JPanel transactionsPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        JPanel reportsPanel = new JPanel(new GridLayout(5, 1, 5, 5));

        transactionsPanel.add(new JLabel("<html><h2><center>This is where the transactions will be</center></h2></html>", JLabel.CENTER));
        JButton updateStatusButton = new JButton("Update Employee Status");
        JButton changeDisbursementButton = new JButton("Change Employee Disbursement");
        JButton performanceButton = new JButton("Check Employee Performance");
        JButton hireTerminateTransferButton = new JButton("Hire, Terminate, or Transfer Employee");

        transactionsPanel.add(updateStatusButton);
        transactionsPanel.add(changeDisbursementButton);
        transactionsPanel.add(performanceButton);
        transactionsPanel.add(hireTerminateTransferButton);

        reportsPanel.add(new JLabel("<html><h2><center>This is where the reports will be</center></h2></html>", JLabel.CENTER));
        JButton attendanceReportButton = new JButton("Generate Attendance Report");
        JButton performanceReportButton = new JButton("Generate Performance Report per Year");
        JButton hiresAttritionReportButton = new JButton("Generate Hires and Attrition Report");
        JButton salaryReportButton = new JButton("Generate Salary Report per Year");

        reportsPanel.add((attendanceReportButton));
        reportsPanel.add(salaryReportButton);
        reportsPanel.add(performanceReportButton);
        reportsPanel.add(hiresAttritionReportButton);

        panel.add(transactionsPanel);
        panel.add(reportsPanel);

        // Navigation actions
        updateStatusButton.addActionListener(e -> navigateToPanel(mainPanel, "ChangeStatus"));
        changeDisbursementButton.addActionListener(e -> navigateToPanel(mainPanel, "ChangeDisbursement"));
        hireTerminateTransferButton.addActionListener(e -> navigateToPanel(mainPanel, "HireTerminateTransfer"));
        performanceButton.addActionListener(e -> navigateToPanel(mainPanel, "Performance"));
        salaryReportButton.addActionListener(e -> navigateToPanel(mainPanel, "SalaryReport"));
        performanceReportButton.addActionListener(e -> navigateToPanel(mainPanel, "PerformanceReport"));

        attendanceReportButton.addActionListener(e -> JOptionPane.showMessageDialog(panel, "Generating Attendance Report... (Placeholder)", "Information", JOptionPane.INFORMATION_MESSAGE));
        hiresAttritionReportButton.addActionListener(e -> JOptionPane.showMessageDialog(panel, "Generating Hires and Attrition Report... (Placeholder)", "Information", JOptionPane.INFORMATION_MESSAGE));

        return panel;
    }

    private static JPanel createPerformancePanel(JPanel mainPanel) {
        JPanel panel = new JPanel(new GridLayout(6, 1, 5, 5));

        panel.add(new JLabel("<html><h2><center>Select an Employee to View Performance</center></h2></html>", JLabel.CENTER));

        JComboBox<String> employeeDropdown = new JComboBox<>(new String[]{"Option 1", "Option 2"});
        panel.add(employeeDropdown);

        JButton viewPerformanceButton = new JButton("View Performance");
        viewPerformanceButton.addActionListener(e -> JOptionPane.showMessageDialog(
                panel,
                "Performance details for: " + employeeDropdown.getSelectedItem() + " (Placeholder)",
                "Performance",
                JOptionPane.INFORMATION_MESSAGE
        ));
        panel.add(viewPerformanceButton);

        panel.add(createBackButton(mainPanel, "MainMenu"));
        return panel;
    }

    private static JPanel createSalaryReportPanel(JPanel mainPanel) {
        JPanel panel = new JPanel(new GridLayout(6, 1, 5, 5));

        panel.add(new JLabel("<html><h2><center>Select an Employee to Generate Salary Report</center></h2></html>", JLabel.CENTER));

        JComboBox<String> employeeDropdown = new JComboBox<>(new String[]{"Option 1", "Option 2"});
        panel.add(employeeDropdown);

        JButton generateSalaryButton = new JButton("Generate Salary Report");
        generateSalaryButton.addActionListener(e -> JOptionPane.showMessageDialog(
                panel,
                "Salary Report for: " + employeeDropdown.getSelectedItem() + " (Placeholder)",
                "Salary Report",
                JOptionPane.INFORMATION_MESSAGE
        ));
        panel.add(generateSalaryButton);

        panel.add(createBackButton(mainPanel, "MainMenu"));
        return panel;
    }

    private static JPanel createPerformanceReportPanel(JPanel mainPanel) {
        JPanel panel = new JPanel(new GridLayout(6, 1, 5, 5));

        panel.add(new JLabel("<html><h2><center>Select an Employee to Generate Performance Report</center></h2></html>", JLabel.CENTER));

        JComboBox<String> employeeDropdown = new JComboBox<>(new String[]{"Option 1", "Option 2"});
        panel.add(employeeDropdown);

        JButton generatePerformanceButton = new JButton("Generate Performance Report");
        generatePerformanceButton.addActionListener(e -> JOptionPane.showMessageDialog(
                panel,
                "Performance Report for: " + employeeDropdown.getSelectedItem() + " (Placeholder)",
                "Performance Report",
                JOptionPane.INFORMATION_MESSAGE
        ));
        panel.add(generatePerformanceButton);

        panel.add(createBackButton(mainPanel, "MainMenu"));
        return panel;
    }

    // Hire/Terminate/Transfer Panel
    private static JPanel createHireTerminateTransferPanel(JPanel mainPanel) {
        JPanel panel = new JPanel(new GridLayout(4, 1, 5, 5));

        panel.add(new JLabel("<html><h2><center>Select an Action</center></h2></html>", JLabel.CENTER));

        JButton hireButton = new JButton("Hire Employee");
        JButton terminateButton = new JButton("Terminate Employee");
        JButton transferButton = new JButton("Transfer Employee");

        panel.add(hireButton);
        panel.add(terminateButton);
        panel.add(transferButton);

        hireButton.addActionListener(e -> navigateToPanel(mainPanel, "HireEmployee"));
        terminateButton.addActionListener(e -> navigateToPanel(mainPanel, "TerminateEmployee"));
        transferButton.addActionListener(e -> navigateToPanel(mainPanel, "TransferEmployee"));

        panel.add(createBackButton(mainPanel, "MainMenu"));
        return panel;
    }

    // Terminate Employee Panel
    private static JPanel createTerminatePanel(JPanel mainPanel) {
        JPanel panel = new JPanel(new GridLayout(6, 1, 5, 5));

        panel.add(new JLabel("<html><h2><center>Terminate Employees Here</center></h2></html>", JLabel.CENTER));
        panel.add(new JLabel("Choose an employee: ", JLabel.CENTER));

        JComboBox<String> employeeDropdown = new JComboBox<>(new String[]{"Option 1", "Option 2"});
        panel.add(employeeDropdown);

        JButton fireButton = new JButton("Fire Employee");
        panel.add(fireButton);

        panel.add(createBackButton(mainPanel, "HireTerminateTransfer"));
        return panel;
    }

    // Transfer Employee Panel
    private static JPanel createTransferPanel(JPanel mainPanel) {
        JPanel panel = new JPanel(new GridLayout(8, 1, 5, 5));

        panel.add(new JLabel("<html><h2><center>Transfer Employees Here</center></h2></html>", JLabel.CENTER));
        panel.add(new JLabel("Choose an employee: ", JLabel.CENTER));

        JComboBox<String> employeeDropdown = new JComboBox<>(new String[]{"Option 1", "Option 2"});
        panel.add(employeeDropdown);

        panel.add(new JLabel("New Department: ", JLabel.CENTER));
        JTextField newDepartmentField = new JTextField();
        panel.add(newDepartmentField);

        JButton transferButton = new JButton("Transfer Employee");
        panel.add(transferButton);

        panel.add(createBackButton(mainPanel, "HireTerminateTransfer"));
        return panel;
    }

    /// Hire Employee Panel
    private static JPanel createHireEmployeePanel(JPanel mainPanel) {
        JPanel panel = new JPanel(new GridLayout(20, 1, 5, 5));

        panel.add(new JLabel("<html><h2><center>Hire Employees Here</center></h2></html>", JLabel.CENTER));

        // Employee ID
        panel.add(new JLabel("Employee ID:"));
        JTextField employeeIDField = new JTextField();
        panel.add(employeeIDField);

        // First Name
        panel.add(new JLabel("First Name:"));
        JTextField firstNameField = new JTextField();
        panel.add(firstNameField);

        // Last Name
        panel.add(new JLabel("Last Name:"));
        JTextField lastNameField = new JTextField();
        panel.add(lastNameField);

        // Gender
        panel.add(new JLabel("Gender:"));
        JComboBox<String> genderDropdown = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        panel.add(genderDropdown);

        // Department Name
        panel.add(new JLabel("Department Name:"));
        JTextField departmentNameField = new JTextField();
        panel.add(departmentNameField);

        // Position Type
        panel.add(new JLabel("Position Type:"));
        JTextField positionTypeField = new JTextField();
        panel.add(positionTypeField);

        // Years of Experience
        panel.add(new JLabel("Years of Experience:"));
        JTextField yearsOfExperienceField = new JTextField();
        panel.add(yearsOfExperienceField);

        // Education
        panel.add(new JLabel("Education:"));
        JTextField educationField = new JTextField();
        panel.add(educationField);

        // Hire Button
        JButton hireButton = new JButton("Hire Employee");
        panel.add(hireButton);

        // Back Button
        panel.add(createBackButton(mainPanel, "MainMenu"));

        // Action Listener for Hire Button
        hireButton.addActionListener(e -> {
            // Retrieve input values
            int employeeID = Integer.parseInt(employeeIDField.getText());
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String gender = (String) genderDropdown.getSelectedItem();
            String departmentName = departmentNameField.getText();
            String positionType = positionTypeField.getText();
            int yearsOfExperience = Integer.parseInt(yearsOfExperienceField.getText());
            String education = educationField.getText();

            // Perform actions with the data (e.g., save to database, display message, etc.)
            JOptionPane.showMessageDialog(
                    panel,
                    "Employee Hired:\n" +
                            "ID: " + employeeID + "\n" +
                            "Name: " + firstName + " " + lastName + "\n" +
                            "Gender: " + gender + "\n" +
                            "Department: " + departmentName + "\n" +
                            "Position: " + positionType + "\n" +
                            "Experience: " + yearsOfExperience + " years\n" +
                            "Education: " + education,
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            // Clear fields after hiring
            employeeIDField.setText("");
            firstNameField.setText("");
            lastNameField.setText("");
            genderDropdown.setSelectedIndex(0);
            departmentNameField.setText("");
            positionTypeField.setText("");
            yearsOfExperienceField.setText("");
            educationField.setText("");
        });

        return panel;
    }



    private static JPanel createChangeDisbursementPanel(JPanel mainPanel) {
        JPanel panel = new JPanel(new GridLayout(6, 1, 5, 5));

        panel.add(new JLabel("<html><h2><center>Change Employee Disbursement Here</center></h2></html>", JLabel.CENTER));
        panel.add(new JLabel("Choose an employee: ", JLabel.CENTER));

        JComboBox<String> employeeDropdown = new JComboBox<>(new String[]{"Option 1", "Option 2"});
        panel.add(employeeDropdown);

        JButton updateButton = new JButton("Update Disbursement");
        panel.add(updateButton);

        panel.add(createBackButton(mainPanel, "MainMenu"));
        return panel;
    }

    private static JPanel createChangeStatusPanel(JPanel mainPanel) {
        JPanel panel = new JPanel(new GridLayout(6, 1, 5, 5));

        panel.add(new JLabel("<html><h2><center>Change Employee Status Here</center></h2></html>", JLabel.CENTER));

        // Dropdown to choose an employee
        panel.add(new JLabel("Choose an employee: ", JLabel.CENTER));
        JComboBox<String> employeeDropdown = new JComboBox<>(new String[]{"Employee 1", "Employee 2", "Employee 3"});
        panel.add(employeeDropdown);

        // Dropdown to select status
        panel.add(new JLabel("Select New Status: ", JLabel.CENTER));
        JComboBox<String> statusDropdown = new JComboBox<>(new String[]{"Active", "Terminated", "Promoted"});
        panel.add(statusDropdown);

        // Update Status Button
        JButton updateStatusButton = new JButton("Update Status Based on Documentation");
        panel.add(updateStatusButton);

        // Back Button
        panel.add(createBackButton(mainPanel, "MainMenu"));

        // ActionListener for Update Status Button
        updateStatusButton.addActionListener(e -> {
            // Get selected employee and status
            String selectedEmployee = (String) employeeDropdown.getSelectedItem();
            String newStatus = (String) statusDropdown.getSelectedItem();

            // Simulate updating the status
            JOptionPane.showMessageDialog(panel,
                    "Status Updated!\n" +
                            "Employee: " + selectedEmployee + "\n" +
                            "New Status: " + newStatus,
                    "Success", JOptionPane.INFORMATION_MESSAGE);

            // Here, you would normally update the employee status in the database or application logic
        });

        return panel;
    }


}