//  Author:     leonard
//  Project:    MitarbeiterManagement
//  Date:       13:58 03.07.24 


import java.time.LocalDate;

public class Manager extends Employee {
    private double baseSalary;
    private double commissionPercentage;
    private double sales;

    public Manager(String firstName, String lastName, String address, LocalDate birthDate, LocalDate entryDate, char gender, double baseSalary, double commissionPercentage, double sales) {
        super(firstName, lastName, address, birthDate, entryDate, gender, "Manager");
        this.baseSalary = baseSalary;
        this.commissionPercentage = commissionPercentage;
        this.sales = sales;
    }

    @Override
    public double calculateMonthlySalary() {
        return baseSalary + (sales * commissionPercentage / 100);
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public double getCommissionPercentage() {
        return commissionPercentage;
    }

    public void setCommissionPercentage(double commissionPercentage) {
        this.commissionPercentage = commissionPercentage;
    }

    public double getSales() {
        return sales;
    }

    public void setSales(double sales) {
        this.sales = sales;
    }
}
