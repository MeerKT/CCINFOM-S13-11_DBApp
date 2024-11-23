import javax.swing.*;
import java.awt.*;

public class gui {

    //Jcombobox models
    public DefaultComboBoxModel employeeList = new DefaultComboBoxModel();


    public static Hiring_Termination_Transfer hireTerminateTransfer = new Hiring_Termination_Transfer();
    public static employee_records records = new employee_records();
    public static hire_attrition_report har = new hire_attrition_report();
    public static performance_report pr = new performance_report();
    public static salary_report sr = new salary_report();
    public static employeeCountReport er = new employeeCountReport();

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

    //reports
    public JPanel salaryReportPanel = createSalaryReportPanel(mainPanel);
    public JPanel performanceReportPanel = createPerformanceReportPanel(mainPanel);
    public JPanel hiresAttritionReport = createHiresAttritionReportPanel(mainPanel);



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

        //reports
        mainPanel.add(salaryReportPanel, "SalaryReport");
        mainPanel.add(performanceReportPanel, "PerformanceReport");
        mainPanel.add(hiresAttritionReport, "HiresAttritionReport");

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
        JButton changeDisbursementButton = new JButton("Manage Employee Salary");
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

        //reports
        salaryReportButton.addActionListener(e -> navigateToPanel(mainPanel, "SalaryReport"));
        performanceReportButton.addActionListener(e -> navigateToPanel(mainPanel, "PerformanceReport"));
        attendanceReportButton.addActionListener(e -> {
            er.generate();
            er.printReport();
        }

        );
        hiresAttritionReportButton.addActionListener(e -> navigateToPanel(mainPanel, "AttendanceReport"));

        return panel;
    }

    public JPanel createPerformancePanel(JPanel mainPanel) {
        JPanel panel = new JPanel(new GridLayout(10, 1, 5, 5));
        Performance_Review review = new Performance_Review();
        panel.add(new JLabel("<html><h2><center>Transfer Employees Here</center></h2></html>", JLabel.CENTER));
        panel.add(new JLabel("Choose an employee: ", JLabel.CENTER));

        records.getEmployees();
        for(int i = 0; i < records.employee_IDList.size();i++) {
            if(records.termination_dateList.get(i) == null) {
                employeeList.addElement(records.employee_IDList.get(i));
            }
        }

        JComboBox<Integer> employeeDropdown = new JComboBox<>(employeeList);
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

    //Salary Report
    public static JPanel createSalaryReportPanel(JPanel mainPanel) {
        JPanel panel = new JPanel(new GridLayout(6, 1, 5, 5));

        panel.add(new JLabel("<html><h2><center>Select a Year to Generate Salary Report</center></h2></html>", JLabel.CENTER));

        // Dropdown menu for year selection
        JComboBox<Integer> yearDropdown = new JComboBox<>();
        for (int year = 2000; year <= 2030; year++) {
            yearDropdown.addItem(year);
        }
        panel.add(new JLabel("Select Year:"));
        panel.add(yearDropdown);

        // Button to generate the salary report
        JButton generateSalaryButton = new JButton("Generate Salary Report");
        generateSalaryButton.addActionListener(e -> {
            Integer selectedYear = (Integer) yearDropdown.getSelectedItem();

            // Generate the salary report
            int status = sr.generate(selectedYear);
            if (status == 1) {
                // Print the report to the console
                sr.printReport();

                JOptionPane.showMessageDialog(panel, "The salary report has been printed to the console.", "Report", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(panel, "Failed to generate the salary report. Check the input data.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(generateSalaryButton);

        // Back button to return to the main menu
        panel.add(createBackButton(mainPanel, "MainMenu"));

        return panel;
    }


    //Performance Report
    public static JPanel createPerformanceReportPanel(JPanel mainPanel) {
        JPanel panel = new JPanel(new GridLayout(6, 1, 5, 5));

        panel.add(new JLabel("<html><h2><center>Select a Year to Generate Performance Report</center></h2></html>", JLabel.CENTER));

        // Dropdown menu for year selection
        JComboBox<Integer> yearDropdown = new JComboBox<>();
        for (int year = 2000; year <= 2030; year++) {
            yearDropdown.addItem(year);
        }
        panel.add(new JLabel("Select Year:"));
        panel.add(yearDropdown);

        // Button to generate the performance report
        JButton generatePerformanceButton = new JButton("Generate Performance Report");
        generatePerformanceButton.addActionListener(e -> {
            Integer selectedYear = (Integer) yearDropdown.getSelectedItem();

            // Generate the performance report
            int status = pr.generate(selectedYear);
            if (status == 1) {
                // Print the report to the console
                pr.printReport();

                JOptionPane.showMessageDialog(panel, "The performance report has been printed to the console.", "Report", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(panel, "Failed to generate the performance report. Check the input data.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(generatePerformanceButton);

        // Back button to return to the main menu
        panel.add(createBackButton(mainPanel, "MainMenu"));

        return panel;
    }


    // Hires and Attrition Panel
    public static JPanel createHiresAttritionReportPanel(JPanel mainPanel) {
        JPanel panel = new JPanel(new GridLayout(7, 1, 5, 5));
        panel.add(new JLabel("<html><h2><center>Hires and Attrition Report Generator</center></h2></html>", JLabel.CENTER));

        // Dropdown for hire year and month
        panel.add(new JLabel("Select Hire Year and Month:"));
        JPanel hireDatePanel = new JPanel(new FlowLayout());

        // Year dropdown for hire date
        JComboBox<Integer> hireYearDropdown = new JComboBox<>();
        for (int year = 2000; year <= 2030; year++) {
            hireYearDropdown.addItem(year);
        }

        // Month dropdown for hire date
        JComboBox<Integer> hireMonthDropdown = new JComboBox<>();
        for (int month = 1; month <= 12; month++) {
            hireMonthDropdown.addItem(month);
        }

        hireDatePanel.add(new JLabel("Year:"));
        hireDatePanel.add(hireYearDropdown);
        hireDatePanel.add(new JLabel("Month:"));
        hireDatePanel.add(hireMonthDropdown);
        panel.add(hireDatePanel);

        // Dropdown for termination year and month
        panel.add(new JLabel("Select Termination Year and Month:"));
        JPanel terminateDatePanel = new JPanel(new FlowLayout());

        // Year dropdown for termination date
        JComboBox<Integer> terminateYearDropdown = new JComboBox<>();
        for (int year = 2000; year <= 2030; year++) {
            terminateYearDropdown.addItem(year);
        }

        // Month dropdown for termination date
        JComboBox<Integer> terminateMonthDropdown = new JComboBox<>();
        for (int month = 1; month <= 12; month++) {
            terminateMonthDropdown.addItem(month);
        }

        terminateDatePanel.add(new JLabel("Year:"));
        terminateDatePanel.add(terminateYearDropdown);
        terminateDatePanel.add(new JLabel("Month:"));
        terminateDatePanel.add(terminateMonthDropdown);
        panel.add(terminateDatePanel);

        // Generate report button
        JButton generateButton = new JButton("Generate Hires and Attrition Report");
        generateButton.addActionListener(e -> {
            Integer hireYear = (Integer) hireYearDropdown.getSelectedItem();
            Integer hireMonth = (Integer) hireMonthDropdown.getSelectedItem();
            Integer terminateYear = (Integer) terminateYearDropdown.getSelectedItem();
            Integer terminateMonth = (Integer) terminateMonthDropdown.getSelectedItem();

            int status = har.generate(hireYear, hireMonth, terminateYear, terminateMonth);
            if (status == 1) {
                // Print the report to CLI
                har.printReport();

                JOptionPane.showMessageDialog(panel, "The report has been printed to the console.", "Report", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(panel, "Failed to generate report. Check the input data.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(generateButton);

        // Back button to return to the main menu
        panel.add(createBackButton(mainPanel, "MainMenu"));

        return panel;
    }



    // Hire/Terminate/Transfer Panel
    public JPanel createHireTerminateTransferPanel(JPanel mainPanel) {
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
    public JPanel createTerminatePanel(JPanel mainPanel) {
        JPanel panel = new JPanel(new GridLayout(6, 1, 5, 5));

        panel.add(new JLabel("<html><h2><center>Terminate Employees Here</center></h2></html>", JLabel.CENTER));
        panel.add(new JLabel("Choose an employee: ", JLabel.CENTER));


        JComboBox<Integer> employeeDropdown = new JComboBox<>(employeeList);
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
                employeeList.removeElement(ID);
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
    public  JPanel createTransferPanel(JPanel mainPanel) {
        JPanel panel = new JPanel(new GridLayout(8, 1, 5, 5));
        panel.add(new JLabel("<html><h2><center>Transfer Employees Here</center></h2></html>", JLabel.CENTER));
        panel.add(new JLabel("Choose an employee: ", JLabel.CENTER));


        JComboBox<Integer> employeeDropdown = new JComboBox<>(employeeList);
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

            // Perform actions with the data (e.g., save to database, display message, etc.)
                try {

                    String firstName = firstNameField.getText();
                    String lastName = lastNameField.getText();
                    String gender = (String) genderDropdown.getSelectedItem();
                    String departmentName = (String) departmentDropDown.getSelectedItem();
                    String positionType = positionTypeField.getText();
                    int yearsOfExperience = Integer.parseInt(yearsOfExperienceField.getText());
                    String education = educationField.getText();

                    if(firstName.isEmpty() || lastName.isEmpty() || departmentName.isEmpty() || positionType.isEmpty() || education.isEmpty()) {
                        JOptionPane.showMessageDialog(
                                panel,
                                "Employee Hire Failed", "Employee Error",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }
                    else if(hireTerminateTransfer.hire_employee(firstName, lastName, gender, departmentName, positionType, yearsOfExperience, education) == 1)
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
                } catch(NumberFormatException error) {
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



    public JPanel createChangeDisbursementPanel(JPanel mainPanel) {
        JPanel panel = new JPanel(new GridLayout(20, 1, 5, 5));
        employee_salary reference = new employee_salary();

        panel.add(new JLabel("<html><h2><center>Update Employee Salary Here</center></h2></html>", JLabel.CENTER));

        // First Name
        panel.add(new JLabel("Choose an employee: ", JLabel.CENTER));

        reference.fillLists();

        JComboBox<Integer> employeeDropdown = new JComboBox<>(employeeList);
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

            try {
                // Retrieve input values
                int employeeID = (int) employeeDropdown.getSelectedItem();
                Double newSalary = Double.parseDouble(salary.getText());
                String bank = bankAcc.getText();
                int overtime = Integer.parseInt(overtimeRecord.getText());
                Double tax = Double.parseDouble(taxes.getText());
                String benefits = benefitsList.getText();
                Double raise = Double.parseDouble(raises.getText());

                if(bank.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            panel,
                            "Please input a bank account!",
                            "Error",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }

                // Perform actions with the data (e.g., save to database, display message, etc.)
                else if (reference.register_salary(employeeID, newSalary, bank,overtime,tax,benefits, raise) == 1)
                {
                    JOptionPane.showMessageDialog(
                            panel,
                            "Salary successfully updated!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }

            catch(NumberFormatException error) {
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