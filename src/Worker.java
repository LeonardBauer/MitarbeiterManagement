//  Author:     leonard
//  Project:    MitarbeiterManagement
//  Date:       13:56 03.07.24 


import java.time.LocalDate;

public class Worker extends Employee {
    private double hourlyWage;
    private double hoursWorked;
    private double overtimePay;
    private double overtimeHours;
    private double shiftAllowance;

    public Worker(String firstName, String lastName, String address, LocalDate birthDate, LocalDate entryDate, char gender, double hourlyWage, double hoursWorked, double overtimePay, double overtimeHours, double shiftAllowance) {
        super(firstName, lastName, address, birthDate, entryDate, gender, "Worker");
        this.hourlyWage = hourlyWage;
        this.hoursWorked = hoursWorked;
        this.overtimePay = overtimePay;
        this.overtimeHours = overtimeHours;
        this.shiftAllowance = shiftAllowance;
    }

    @Override
    public double calculateMonthlySalary() {
        return (hourlyWage * hoursWorked) + (overtimePay * overtimeHours) + shiftAllowance;
    }

    public double getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(double hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public double getOvertimePay() {
        return overtimePay;
    }

    public void setOvertimePay(double overtimePay) {
        this.overtimePay = overtimePay;
    }

    public double getOvertimeHours() {
        return overtimeHours;
    }

    public void setOvertimeHours(double overtimeHours) {
        this.overtimeHours = overtimeHours;
    }

    public double getShiftAllowance() {
        return shiftAllowance;
    }

    public void setShiftAllowance(double shiftAllowance) {
        this.shiftAllowance = shiftAllowance;
    }
}
