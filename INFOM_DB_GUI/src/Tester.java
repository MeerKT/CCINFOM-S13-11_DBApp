import javax.swing.*;
import java.awt.*;

public class Tester {
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

    private static JButton createBackButton(JPanel mainPanel, String targetPanel) {
        JButton backButton = new JButton("Back to Main Menu");
        backButton.addActionListener(e -> navigateToPanel(mainPanel, targetPanel));
        return backButton;
    }

    private static void navigateToPanel(JPanel mainPanel, String targetPanel) {
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, targetPanel);
    }

    private static JPanel createMainMenu(JPanel mainPanel) {
        JPanel panel = new JPanel(new GridLayout(1, 2));

        JPanel transactionsPanel = new JPanel(new GridLayout(6, 1, 5, 5));
        JPanel reportsPanel = new JPanel(new GridLayout(6, 1, 5, 5));

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
        JButton salaryReportButton = new JButton("Generate Salary Report per Year");
        JButton performanceReportButton = new JButton("Generate Performance Report per Year");
        JButton hiresAttritionReportButton = new JButton("Generate Hires and Attrition Report");

        reportsPanel.add(attendanceReportButton);
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

    // Missing Panels:
    private static JPanel createHireTerminateTransferPanel(JPanel mainPanel) {
        JPanel panel = new JPanel();
        panel.add(new JLabel("<html><h2>Hire, Terminate, Transfer Employee</h2></html>", JLabel.CENTER));
        panel.add(createBackButton(mainPanel, "MainMenu"));
        return panel;
    }

    private static JPanel createTerminatePanel(JPanel mainPanel) {
        JPanel panel = new JPanel();
        panel.add(new JLabel("<html><h2>Terminate Employee</h2></html>", JLabel.CENTER));
        panel.add(createBackButton(mainPanel, "MainMenu"));
        return panel;
    }

    private static JPanel createTransferPanel(JPanel mainPanel) {
        JPanel panel = new JPanel();
        panel.add(new JLabel("<html><h2>Transfer Employee</h2></html>", JLabel.CENTER));
        panel.add(createBackButton(mainPanel, "MainMenu"));
        return panel;
    }

    private static JPanel createChangeDisbursementPanel(JPanel mainPanel) {
        JPanel panel = new JPanel();
        panel.add(new JLabel("<html><h2>Change Employee Disbursement</h2></html>", JLabel.CENTER));
        panel.add(createBackButton(mainPanel, "MainMenu"));
        return panel;
    }

    private static JPanel createHireEmployeePanel(JPanel mainPanel) {
        JPanel panel = new JPanel();
        panel.add(new JLabel("<html><h2>Hire Employee</h2></html>", JLabel.CENTER));
        panel.add(createBackButton(mainPanel, "MainMenu"));
        return panel;
    }

    private static JPanel createChangeStatusPanel(JPanel mainPanel) {
        JPanel panel = new JPanel();
        panel.add(new JLabel("<html><h2>Change Employee Status</h2></html>", JLabel.CENTER));
        panel.add(createBackButton(mainPanel, "MainMenu"));
        return panel;
    }
}