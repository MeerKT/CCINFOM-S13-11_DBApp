import javax.swing.*;
import java.awt.*;

public class gui {

    public static Hiring_Termination_Transfer hireTerminateTransfer = new Hiring_Termination_Transfer();
    public static employee_records records = new employee_records();

    // Main panel with CardLayout
    public JPanel mainPanel = new JPanel(new CardLayout());

    // Create the individual panels
    public JPanel mainMenuPanel = createMainMenu(mainPanel);
    public JPanel hireTerminateTransferPanel = createHireTerminateTransferPanel(mainPanel);
    public JPanel terminateEmployeePanel = createTerminatePanel(mainPanel);
    public JPanel transferEmployeePanel = createTransferPanel(mainPanel);
    public JPanel changeDisbursementPanel = createChangeDisbursementPanel(mainPanel);
    public JPanel hireEmployeePanel = createHireEmployeePanel(mainPanel);
    public JPanel changeStatusPanel = createChangeStatusPanel(mainPanel);
    public JPanel performancePanel = createPerformancePanel(mainPanel);
    public JPanel salaryReportPanel = createSalaryReportPanel(mainPanel);
    public JPanel performanceReportPanel = createPerformanceReportPanel(mainPanel);

    public gui () {
        JFrame frame = new JFrame("Employee Database Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

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

        transactionsPanel.add(new JLabel("<html><h2><center>Employee Management</center></h2></html>", JLabel.CENTER));
        JButton updateStatusButton = new JButton("Update Employee Status");
        JButton changeDisbursementButton = new JButton("Change Employee Disbursement");
        JButton performanceButton = new JButton("Leave a review for an employee");
        JButton hireTerminateTransferButton = new JButton("Hire, Terminate, or Transfer Employee");

        transactionsPanel.add(updateStatusButton);
        transactionsPanel.add(changeDisbursementButton);
        transactionsPanel.add(performanceButton);
        transactionsPanel.add(hireTerminateTransferButton);

        reportsPanel.add(new JLabel("<html><h2><center>Employee Reports</center></h2></html>", JLabel.CENTER));
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

    public static JPanel createPerformancePanel(JPanel mainPanel) {
        JPanel panel = new JPanel(new GridLayout(10, 1, 5, 5));
        Performance_Review review = new Performance_Review();
        panel.add(new JLabel("<html><h2><center>Transfer Employees Here</center></h2></html>", JLabel.CENTER));
        panel.add(new JLabel("Choose an employee: ", JLabel.CENTER));

        JComboBox<Integer> employeeDropdown = new JComboBox<>();
        records.getEmployees();
        for(int i = 0; i < records.employee_IDList.size();i++) {
            if(records.termination_dateList.get(i) == null) {
                employeeDropdown.addItem(records.employee_IDList.get(i));
            }
        }
        panel.add(employeeDropdown);

        panel.add(new JLabel("Leave a Score: "));
        JTextField score = new JTextField();
        panel.add(score);

        panel.add(new JLabel("Leave a comment: "));
        JTextField comment = new JTextField();
        panel.add(comment);

        JButton reviewButton = new JButton("Leave review");
        panel.add(reviewButton);

        reviewButton.addActionListener(e -> {
            // Retrieve input values
            int ID = (int)employeeDropdown.getSelectedItem();
            int reviewScore = Integer.parseInt(score.getText());
            String reviewComments = comment.getText();
            // Perform actions with the data (e.g., save to database, display message, etc.)
            if (review.add_review(ID, reviewScore, reviewComments) == 1) {
                JOptionPane.showMessageDialog(
                        panel,
                        "Employee Reviewed!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                JOptionPane.showMessageDialog(
                        panel,
                        "Employee Review Failed", "Employee Error",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        panel.add(createBackButton(mainPanel, "HireTerminateTransfer"));
        return panel;
    }

    public static JPanel createSalaryReportPanel(JPanel mainPanel) {
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

    public static JPanel createPerformanceReportPanel(JPanel mainPanel) {
        JPanel panel = new JPanel(new GridLayout(6, 1, 5, 5));

        panel.add(new JLabel("<html><h2><center>Select an Employee to Generate Performance Report</center></h2></html>", JLabel.CENTER));

        JComboBox<Integer> employeeDropdown = new JComboBox<>();
        records.getEmployees();
        for(int i = 0; i < records.employee_IDList.size();i++) {
            if(records.termination_dateList.get(i) == null) {
                employeeDropdown.addItem(records.employee_IDList.get(i));
            }
        }
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
    public static JPanel createHireTerminateTransferPanel(JPanel mainPanel) {
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
    public static JPanel createTerminatePanel(JPanel mainPanel) {
        JPanel panel = new JPanel(new GridLayout(6, 1, 5, 5));

        panel.add(new JLabel("<html><h2><center>Terminate Employees Here</center></h2></html>", JLabel.CENTER));
        panel.add(new JLabel("Choose an employee: ", JLabel.CENTER));

        JComboBox<Integer> employeeDropdown = new JComboBox<>();
        records.getEmployees();
            for(int i = 0; i < records.employee_IDList.size();i++) {
                if(records.termination_dateList.get(i) == null) {
                    employeeDropdown.addItem(records.employee_IDList.get(i));
                }
            }
        panel.add(employeeDropdown);

        JButton fireButton = new JButton("Fire Employee");
        panel.add(fireButton);

        fireButton.addActionListener(e -> {
            // Retrieve input values
            int ID = (int) employeeDropdown.getSelectedItem();
            // Perform actions with the data (e.g., save to database, display message, etc.)

            if(hireTerminateTransfer.terminate_employee(ID) == 1)
            {
                JOptionPane.showMessageDialog(
                        panel,
                        "Employee Terminated!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );
                employeeDropdown.removeItem((Object) ID);
            }

            else {
                JOptionPane.showMessageDialog(
                        panel,
                        "Employee Termination Failed", "Employee Error",
                JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        panel.add(createBackButton(mainPanel, "HireTerminateTransfer"));
        return panel;
    }

    // Transfer Employee Panel
    public static JPanel createTransferPanel(JPanel mainPanel) {
        JPanel panel = new JPanel(new GridLayout(8, 1, 5, 5));
        panel.add(new JLabel("<html><h2><center>Transfer Employees Here</center></h2></html>", JLabel.CENTER));
        panel.add(new JLabel("Choose an employee: ", JLabel.CENTER));

        JComboBox<Integer> employeeDropdown = new JComboBox<>();
        records.getEmployees();
        for(int i = 0; i < records.employee_IDList.size();i++) {
            if(records.termination_dateList.get(i) == null) {
                employeeDropdown.addItem(records.employee_IDList.get(i));
            }
        }
        panel.add(employeeDropdown);

        panel.add(new JLabel("New Department: ", JLabel.CENTER));
        JComboBox<String> departmentDropDown = new JComboBox<>(new String[]{"IT", "HR", "Marketing","Finance","Operations"});
        panel.add(departmentDropDown);

        JButton transferButton = new JButton("Transfer Employee");
        panel.add(transferButton);

        transferButton.addActionListener(e -> {
                    // Retrieve input values
                    int ID = (int) employeeDropdown.getSelectedItem();
                    String departmentName = (String) departmentDropDown.getSelectedItem();
                    // Perform actions with the data (e.g., save to database, display message, etc.)

                    if (hireTerminateTransfer.transfer_employee(ID, departmentName) == 1) {
                        JOptionPane.showMessageDialog(
                                panel,
                                "Employee Transferred!",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    } else {
                        JOptionPane.showMessageDialog(
                                panel,
                                "Employee Transfer Failed", "Employee Error",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }
                });

        panel.add(createBackButton(mainPanel, "HireTerminateTransfer"));
        return panel;
    }

    /// Hire Employee Panel
    public JPanel createHireEmployeePanel(JPanel mainPanel) {
        JPanel panel = new JPanel(new GridLayout(20, 1, 5, 5));

        panel.add(new JLabel("<html><h2><center>Hire Employees Here</center></h2></html>", JLabel.CENTER));

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
        JComboBox<String> genderDropdown = new JComboBox<>(new String[]{"male","female"});
        panel.add(genderDropdown);

        // Department Name
        panel.add(new JLabel("Department Name:"));
        JComboBox<String> departmentDropDown = new JComboBox<>(new String[]{"IT", "HR", "Marketing","Finance","Operations"});
        panel.add(departmentDropDown);

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
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String gender = (String) genderDropdown.getSelectedItem();
            String departmentName = (String) departmentDropDown.getSelectedItem();
            String positionType = positionTypeField.getText();
            int yearsOfExperience = Integer.parseInt(yearsOfExperienceField.getText());
            String education = educationField.getText();



            // Perform actions with the data (e.g., save to database, display message, etc.)

            if(hireTerminateTransfer.hire_employee(firstName, lastName, gender, departmentName, positionType, yearsOfExperience, education) == 1)
            {
                JOptionPane.showMessageDialog(
                        panel,
                        "Employee Hired:\n" +
                                "Name: " + firstName + " " + lastName + "\n" +
                                "Gender: " + gender + "\n" +
                                "Department: " + departmentName + "\n" +
                                "Position: " + positionType + "\n" +
                                "Experience: " + yearsOfExperience + " years\n" +
                                "Education: " + education,
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

            else {
                JOptionPane.showMessageDialog(
                        panel,
                        "Employee Hire Failed", "Employee Error",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

            // Clear fields after hiring
            firstNameField.setText("");
            lastNameField.setText("");
            departmentDropDown.setSelectedIndex(0);
            positionTypeField.setText("");
            yearsOfExperienceField.setText("");
            educationField.setText("");
        });

        return panel;
    }



    public static JPanel createChangeDisbursementPanel(JPanel mainPanel) {
        JPanel panel = new JPanel(new GridLayout(20, 1, 5, 5));
        employee_salary reference = new employee_salary();

        panel.add(new JLabel("<html><h2><center>Update Employee Salary</center></h2></html>", JLabel.CENTER));

        // First Name
        panel.add(new JLabel("Choose an employee: ", JLabel.CENTER));

        reference.fillLists();
        records.getEmployees();
        JComboBox<Integer> employeeDropdown = new JComboBox<>();
        records.getEmployees();
        for(int i = 0; i < records.employee_IDList.size();i++) {
            if((!reference.employee_IDList.contains((Object) (i+1))) && !(records.position_typeList.get(i).equals("TERMINATED"))) {
                employeeDropdown.addItem(records.employee_IDList.get(i));
            }
        }
        panel.add(employeeDropdown);

//        Salary
        panel.add(new JLabel("New Base Salary:"));
        JTextField salary = new JTextField();
        panel.add(salary);

        //Bank acc no.
        panel.add(new JLabel("Bank acc. no:"));
        JTextField bankAcc = new JTextField();
        panel.add(bankAcc);

        // Overtime Record
        panel.add(new JLabel("Overtime Record:"));
        JTextField overtimeRecord = new JTextField();
        panel.add(overtimeRecord);

        // Taxes
        panel.add(new JLabel("Amount of taxes:"));
        JTextField taxes = new JTextField();
        panel.add(taxes);

        // Benefits
        panel.add(new JLabel("Input Benefits if any:"));
        JTextField benefitsList = new JTextField();
        panel.add(benefitsList);

        // Raises
        panel.add(new JLabel("Input any raise to the employee's salary if any:"));
        JTextField raises = new JTextField();
        panel.add(raises);

        // Update Salary Button
        JButton updateSalary = new JButton("Update Salary");
        panel.add(updateSalary);

        // Action Listener for Hire Button
        updateSalary.addActionListener(e -> {
            // Retrieve input values
            int employeeID = (int) employeeDropdown.getSelectedItem();
            Double newSalary = Double.parseDouble(salary.getText());
            String bank = bankAcc.getText();
            int overtime = Integer.parseInt(overtimeRecord.getText());
            Double tax = Double.parseDouble(taxes.getText());
            String benefits = benefitsList.getText();
            Double raise = Double.parseDouble(raises.getText());
            // Perform actions with the data (e.g., save to database, display message, etc.)

            if(reference.register_salary(employeeID, newSalary, bank,overtime,tax,benefits, raise) == 1)
            {
                JOptionPane.showMessageDialog(
                        panel,
                        "Salary successfully updated!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

            else {
                JOptionPane.showMessageDialog(
                        panel,
                        "Salary failed to update", "Employee Error",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

            // Clear fields after hiring
            salary.setText("");
            taxes.setText("");
            employeeDropdown.setSelectedIndex(0);
            bankAcc.setText("");
            overtimeRecord.setText("");
            benefitsList.setText("");
            raises.setText("");
        });


        // Back Button
        panel.add(createBackButton(mainPanel, "MainMenu"));
        return panel;
    }

    public static JPanel createChangeStatusPanel(JPanel mainPanel) {
        JPanel panel = new JPanel(new GridLayout(6, 1, 5, 5));

        panel.add(new JLabel("<html><h2><center>Change Employee Status Here</center></h2></html>", JLabel.CENTER));

        // Dropdown to choose an employee
        JComboBox<Integer> employeeDropdown = new JComboBox<>();
        records.getEmployees();
        for(int i = 0; i < records.employee_IDList.size();i++) {
            if(records.termination_dateList.get(i) == null) {
                employeeDropdown.addItem(records.employee_IDList.get(i));
            }
        }
        panel.add(employeeDropdown);

        // Dropdown to select status
        panel.add(new JLabel("Select New Status: ", JLabel.CENTER));
        JComboBox<String> statusDropdown = new JComboBox<>(new String[]{"Active", "Terminated", "Promoted"});
        panel.add(statusDropdown);

        // Update Status Button
        JButton updateStatusButton = new JButton("Update Status");
        panel.add(updateStatusButton);

        // Back Button
        panel.add(createBackButton(mainPanel, "MainMenu"));

        // ActionListener for Update Status Button
        updateStatusButton.addActionListener(e -> {
            // Get selected employee and status
            int selectedEmployee = (int) employeeDropdown.getSelectedItem();
            String newStatus = (String) statusDropdown.getSelectedItem();
            change_employee_status function = new change_employee_status();

            // Simulate updating the status
            if(function.update_employee_status(selectedEmployee, newStatus) == 1)
            {
                JOptionPane.showMessageDialog(panel,
                        "Status Updated!\n",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
            }

            else {
                JOptionPane.showMessageDialog(panel,
                        "Update Failed!\n",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
            }

        });

        return panel;
    }


}