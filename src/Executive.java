//  Author:     leonard
//  Project:    MitarbeiterManagement
//  Date:       13:59 03.07.24 


import java.time.LocalDate;

public class Executive extends Manager {
    private double executiveAllowance;

    public Executive(String firstName, String lastName, String address, LocalDate birthDate, LocalDate entryDate, char gender, double baseSalary, double commissionPercentage, double sales, double executiveAllowance) {
        super(firstName, lastName, address, birthDate, entryDate, gender, baseSalary, commissionPercentage, sales);
        this.executiveAllowance = executiveAllowance;
    }

    @Override
    public double calculateMonthlySalary() {
        return super.calculateMonthlySalary() + executiveAllowance;
    }

    public double getExecutiveAllowance() {
        return executiveAllowance;
    }

    public void setExecutiveAllowance(double executiveAllowance) {
        this.executiveAllowance = executiveAllowance;
    }
}
