//  Author:     leonard
//  Project:    MitarbeiterManagement
//  Date:       14:00 03.07.24 


import java.io.*;
import java.util.*;
import java.time.LocalDate;
import java.util.stream.Collectors;

public class EmployeeManager {
    List<Employee> employees;
    private List<Employee> formerEmployees;
    private final int MAX_EMPLOYEES = 20;

    public EmployeeManager() {
        employees = new ArrayList<>();
        formerEmployees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        if (employees.size() >= MAX_EMPLOYEES) {
            System.out.println("Cannot add more employees. Maximum limit reached.");
            return;
        }
        employees.add(employee);
    }

    public void removeEmployee(String employeeId) {
        Employee employee = findEmployeeById(employeeId);
        if (employee != null) {
            employees.remove(employee);
            formerEmployees.add(employee);
        } else {
            System.out.println("Employee not found.");
        }
    }

    public Employee findEmployeeById(String employeeId) {
        for (Employee employee : employees) {
            if (employee.getId().equals(employeeId)) {
                return employee;
            }
        }
        return null;
    }

    public void giveRaise(String employeeId, double percentage) {
        Employee employee = findEmployeeById(employeeId);
        if (employee instanceof Staff) {
            ((Staff) employee).setBaseSalary(((Staff) employee).getBaseSalary() * (1 + percentage / 100));
        } else if (employee instanceof Manager) {
            ((Manager) employee).setBaseSalary(((Manager) employee).getBaseSalary() * (1 + percentage / 100));
        } else if (employee instanceof Worker) {
            ((Worker) employee).setHourlyWage(((Worker) employee).getHourlyWage() * (1 + percentage / 100));
        }
    }

    public void giveRaiseToGroup(String employeeType, double percentage) {
        for (Employee employee : employees) {
            if (employee.getEmployeeType().equals(employeeType)) {
                giveRaise(employee.getId(), percentage);
            }
        }
    }

    public void awardBonus(String employeeId, double bonus) {
        Employee employee = findEmployeeById(employeeId);
        if (employee instanceof Staff) {
            ((Staff) employee).setAdditionalAllowance(((Staff) employee).getAdditionalAllowance() + bonus);
        } else if (employee instanceof Manager) {
            ((Manager) employee).setSales(((Manager) employee).getSales() + bonus);
        } else if (employee instanceof Worker) {
            ((Worker) employee).setShiftAllowance(((Worker) employee).getShiftAllowance() + bonus);
        }
    }

    public void generatePayroll(String month) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("payroll_" + month + ".txt"))) {
            for (Employee employee : employees) {
                writer.write(employee.getFirstName() + " " + employee.getLastName() + " (" + employee.getId() + "): " + employee.calculateMonthlySalary());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadEmployees(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String type = data[0];
                String firstName = data[1];
                String lastName = data[2];
                String address = data[3];
                LocalDate birthDate = LocalDate.parse(data[4]);
                LocalDate entryDate = LocalDate.parse(data[5]);
                char gender = data[6].charAt(0);

                switch (type) {
                    case "Worker":
                        double hourlyWage = Double.parseDouble(data[7]);
                        double hoursWorked = Double.parseDouble(data[8]);
                        double overtimePay = Double.parseDouble(data[9]);
                        double overtimeHours = Double.parseDouble(data[10]);
                        double shiftAllowance = Double.parseDouble(data[11]);
                        employees.add(new Worker(firstName, lastName, address, birthDate, entryDate, gender, hourlyWage, hoursWorked, overtimePay, overtimeHours, shiftAllowance));
                        break;
                    case "Staff":
                        double baseSalary = Double.parseDouble(data[7]);
                        double localityAllowance = Double.parseDouble(data[8]);
                        double additionalAllowance = Double.parseDouble(data[9]);
                        employees.add(new Staff(firstName, lastName, address, birthDate, entryDate, gender, baseSalary, localityAllowance, additionalAllowance));
                        break;
                    case "Manager":
                        baseSalary = Double.parseDouble(data[7]);
                        double commissionPercentage = Double.parseDouble(data[8]);
                        double sales = Double.parseDouble(data[9]);
                        employees.add(new Manager(firstName, lastName, address, birthDate, entryDate, gender, baseSalary, commissionPercentage, sales));
                        break;
                    case "Executive":
                        baseSalary = Double.parseDouble(data[7]);
                        commissionPercentage = Double.parseDouble(data[8]);
                        sales = Double.parseDouble(data[9]);
                        double executiveAllowance = Double.parseDouble(data[10]);
                        employees.add(new Executive(firstName, lastName, address, birthDate, entryDate, gender, baseSalary, commissionPercentage, sales, executiveAllowance));
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveEmployees(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Employee employee : employees) {
                writer.write(employee.getEmployeeType() + "," + employee.getFirstName() + "," + employee.getLastName() + "," + employee.getAddress() + "," + employee.getBirthDate() + "," + employee.getEntryDate() + "," + employee.getGender());
                if (employee instanceof Worker) {
                    Worker worker = (Worker) employee;
                    writer.write("," + worker.getHourlyWage() + "," + worker.getHoursWorked() + "," + worker.getOvertimePay() + "," + worker.getOvertimeHours() + "," + worker.getShiftAllowance());
                } else if (employee instanceof Staff) {
                    Staff staff = (Staff) employee;
                    writer.write("," + staff.getBaseSalary() + "," + staff.getLocalityAllowance() + "," + staff.getAdditionalAllowance());
                } else if (employee instanceof Manager) {
                    Manager manager = (Manager) employee;
                    writer.write("," + manager.getBaseSalary() + "," + manager.getCommissionPercentage() + "," + manager.getSales());
                } else if (employee instanceof Executive) {
                    Executive executive = (Executive) employee;
                    writer.write("," + executive.getBaseSalary() + "," + executive.getCommissionPercentage() + "," + executive.getSales() + "," + executive.getExecutiveAllowance());
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
