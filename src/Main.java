//  Author:     leonard
//  Project:    MitarbeiterManagement
//  Date:       14:01 03.07.24 

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.OptionalDouble;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EmployeeManager manager = new EmployeeManager();
        Scanner scanner = new Scanner(System.in);
        String choice;

        // Load employees from file
        manager.loadEmployees("employees.csv");

        while (true) {
            System.out.println("Employee Management System");
            System.out.println("1. Add Employee");
            System.out.println("2. Remove Employee");
            System.out.println("3. Give Raise to an Employee");
            System.out.println("4. Give Raise to a Group");
            System.out.println("5. Award Bonus");
            System.out.println("6. Generate Payroll");
            System.out.println("7. View Employee Information");
            System.out.println("8. View Employee with Lowest Salary");
            System.out.println("9. View Salary Range");
            System.out.println("10. View Gender Distribution");
            System.out.println("11. View Average Salary");
            System.out.println("12. View Number of Employees");
            System.out.println("13. View Average Age of Employees");
            System.out.println("14. View Employee with Longest Tenure");
            System.out.println("15. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addEmployee(manager, scanner);
                    break;
                case "2":
                    removeEmployee(manager, scanner);
                    break;
                case "3":
                    giveRaise(manager, scanner);
                    break;
                case "4":
                    giveRaiseToGroup(manager, scanner);
                    break;
                case "5":
                    awardBonus(manager, scanner);
                    break;
                case "6":
                    generatePayroll(manager, scanner);
                    break;
                case "7":
                    viewEmployeeInfo(manager, scanner);
                    break;
                case "8":
                    viewEmployeeWithLowestSalary(manager);
                    break;
                case "9":
                    viewSalaryRange(manager);
                    break;
                case "10":
                    viewGenderDistribution(manager);
                    break;
                case "11":
                    viewAverageSalary(manager);
                    break;
                case "12":
                    viewNumberOfEmployees(manager);
                    break;
                case "13":
                    viewAverageAgeOfEmployees(manager);
                    break;
                case "14":
                    viewEmployeeWithLongestTenure(manager);
                    break;
                case "15":
                    manager.saveEmployees("employees.csv");
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addEmployee(EmployeeManager manager, Scanner scanner) {
        // Get common employee details
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter birth date (YYYY-MM-DD): ");
        LocalDate birthDate = LocalDate.parse(scanner.nextLine());
        System.out.print("Enter entry date (YYYY-MM-DD): ");
        LocalDate entryDate = LocalDate.parse(scanner.nextLine());
        System.out.print("Enter gender (M/F): ");
        char gender = scanner.nextLine().charAt(0);
        System.out.print("Enter employee type (Worker/Staff/Manager/Executive): ");
        String type = scanner.nextLine();

        switch (type) {
            case "Worker":
                System.out.print("Enter hourly wage: ");
                double hourlyWage = Double.parseDouble(scanner.nextLine());
                System.out.print("Enter hours worked: ");
                double hoursWorked = Double.parseDouble(scanner.nextLine());
                System.out.print("Enter overtime pay: ");
                double overtimePay = Double.parseDouble(scanner.nextLine());
                System.out.print("Enter overtime hours: ");
                double overtimeHours = Double.parseDouble(scanner.nextLine());
                System.out.print("Enter shift allowance: ");
                double shiftAllowance = Double.parseDouble(scanner.nextLine());
                manager.addEmployee(new Worker(firstName, lastName, address, birthDate, entryDate, gender, hourlyWage, hoursWorked, overtimePay, overtimeHours, shiftAllowance));
                break;
            case "Staff":
                System.out.print("Enter base salary: ");
                double baseSalary = Double.parseDouble(scanner.nextLine());
                System.out.print("Enter locality allowance: ");
                double localityAllowance = Double.parseDouble(scanner.nextLine());
                System.out.print("Enter additional allowance: ");
                double additionalAllowance = Double.parseDouble(scanner.nextLine());
                manager.addEmployee(new Staff(firstName, lastName, address, birthDate, entryDate, gender, baseSalary, localityAllowance, additionalAllowance));
                break;
            case "Manager":
                System.out.print("Enter base salary: ");
                baseSalary = Double.parseDouble(scanner.nextLine());
                System.out.print("Enter commission percentage: ");
                double commissionPercentage = Double.parseDouble(scanner.nextLine());
                System.out.print("Enter sales: ");
                double sales = Double.parseDouble(scanner.nextLine());
                manager.addEmployee(new Manager(firstName, lastName, address, birthDate, entryDate, gender, baseSalary, commissionPercentage, sales));
                break;
            case "Executive":
                System.out.print("Enter base salary: ");
                baseSalary = Double.parseDouble(scanner.nextLine());
                System.out.print("Enter commission percentage: ");
                commissionPercentage = Double.parseDouble(scanner.nextLine());
                System.out.print("Enter sales: ");
                sales = Double.parseDouble(scanner.nextLine());
                System.out.print("Enter executive allowance: ");
                double executiveAllowance = Double.parseDouble(scanner.nextLine());
                manager.addEmployee(new Executive(firstName, lastName, address, birthDate, entryDate, gender, baseSalary, commissionPercentage, sales, executiveAllowance));
                break;
            default:
                System.out.println("Invalid employee type.");
        }
    }

    private static void removeEmployee(EmployeeManager manager, Scanner scanner) {
        System.out.print("Enter employee ID to remove: ");
        String id = scanner.nextLine();
        manager.removeEmployee(id);
    }

    private static void giveRaise(EmployeeManager manager, Scanner scanner) {
        System.out.print("Enter employee ID to give raise: ");
        String id = scanner.nextLine();
        System.out.print("Enter raise percentage: ");
        double percentage = Double.parseDouble(scanner.nextLine());
        manager.giveRaise(id, percentage);
    }

    private static void giveRaiseToGroup(EmployeeManager manager, Scanner scanner) {
        System.out.print("Enter employee type to give raise (Staff/Manager/Worker): ");
        String type = scanner.nextLine();
        System.out.print("Enter raise percentage: ");
        double percentage = Double.parseDouble(scanner.nextLine());
        manager.giveRaiseToGroup(type, percentage);
    }

    private static void awardBonus(EmployeeManager manager, Scanner scanner) {
        System.out.print("Enter employee ID to award bonus: ");
        String id = scanner.nextLine();
        System.out.print("Enter bonus amount: ");
        double bonus = Double.parseDouble(scanner.nextLine());
        manager.awardBonus(id, bonus);
    }

    private static void generatePayroll(EmployeeManager manager, Scanner scanner) {
        System.out.print("Enter month for payroll (e.g., July): ");
        String month = scanner.nextLine();
        manager.generatePayroll(month);
    }

    private static void viewEmployeeInfo(EmployeeManager manager, Scanner scanner) {
        System.out.print("Enter employee ID to view information: ");
        String id = scanner.nextLine();
        Employee employee = manager.findEmployeeById(id);
        if (employee != null) {
            System.out.println("Employee ID: " + employee.getId());
            System.out.println("First Name: " + employee.getFirstName());
            System.out.println("Last Name: " + employee.getLastName());
            System.out.println("Address: " + employee.getAddress());
            System.out.println("Birth Date: " + employee.getBirthDate());
            System.out.println("Entry Date: " + employee.getEntryDate());
            System.out.println("Gender: " + employee.getGender());
            System.out.println("Employee Type: " + employee.getEmployeeType());
            System.out.println("Monthly Salary: " + employee.calculateMonthlySalary());
        } else {
            System.out.println("Employee not found.");
        }
    }



    private static void viewEmployeeWithLowestSalary(EmployeeManager manager) {
        Employee employee = manager.employees.stream().min(Comparator.comparing(Employee::calculateMonthlySalary)).orElse(null);
        if (employee != null) {
            System.out.println("Employee with lowest salary: " + employee.getFirstName() + " " + employee.getLastName() + " (" + employee.getId() + ") with salary " + employee.calculateMonthlySalary());
        } else {
            System.out.println("No employees found.");
        }
    }

    private static void viewSalaryRange(EmployeeManager manager) {
        OptionalDouble minSalary = manager.employees.stream().mapToDouble(Employee::calculateMonthlySalary).min();
        OptionalDouble maxSalary = manager.employees.stream().mapToDouble(Employee::calculateMonthlySalary).max();
        if (minSalary.isPresent() && maxSalary.isPresent()) {
            System.out.println("Salary range: " + minSalary.getAsDouble() + " - " + maxSalary.getAsDouble());
        } else {
            System.out.println("No employees found.");
        }
    }

    private static void viewGenderDistribution(EmployeeManager manager) {
        long maleCount = manager.employees.stream().filter(e -> e.getGender() == 'M').count();
        long femaleCount = manager.employees.stream().filter(e -> e.getGender() == 'F').count();
        System.out.println("Gender distribution: " + maleCount + " males, " + femaleCount + " females.");
    }

    private static void viewAverageSalary(EmployeeManager manager) {
        OptionalDouble avgSalary = manager.employees.stream().mapToDouble(Employee::calculateMonthlySalary).average();
        if (avgSalary.isPresent()) {
            System.out.println("Average salary: " + avgSalary.getAsDouble());
        } else {
            System.out.println("No employees found.");
        }
    }

    private static void viewNumberOfEmployees(EmployeeManager manager) {
        System.out.println("Number of employees: " + manager.employees.size());
    }

    private static void viewAverageAgeOfEmployees(EmployeeManager manager) {
        OptionalDouble avgAge = manager.employees.stream().mapToDouble(e -> ChronoUnit.YEARS.between(e.getBirthDate(), LocalDate.now())).average();
        if (avgAge.isPresent()) {
            System.out.println("Average age of employees: " + avgAge.getAsDouble());
        } else {
            System.out.println("No employees found.");
        }
    }

    private static void viewEmployeeWithLongestTenure(EmployeeManager manager) {
        Employee employee = manager.employees.stream().max(Comparator.comparing(e -> ChronoUnit.YEARS.between(e.getEntryDate(), LocalDate.now()))).orElse(null);
        if (employee != null) {
            System.out.println("Employee with longest tenure: " + employee.getFirstName() + " " + employee.getLastName() + " (" + employee.getId() + ") with tenure " + ChronoUnit.YEARS.between(employee.getEntryDate(), LocalDate.now()) + " years.");
        } else {
            System.out.println("No employees found.");
        }
    }
}
