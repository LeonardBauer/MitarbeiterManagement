//  Author:     leonard
//  Project:    MitarbeiterManagement
//  Date:       13:57 03.07.24 

import java.time.LocalDate;

public class Staff extends Employee {
    private double baseSalary;
    private double localityAllowance;
    private double additionalAllowance;

    public Staff(String firstName, String lastName, String address, LocalDate birthDate, LocalDate entryDate, char gender, double baseSalary, double localityAllowance, double additionalAllowance) {
        super(firstName, lastName, address, birthDate, entryDate, gender, "Staff");
        this.baseSalary = baseSalary;
        this.localityAllowance = localityAllowance;
        this.additionalAllowance = additionalAllowance;
    }

    @Override
    public double calculateMonthlySalary() {
        return baseSalary + localityAllowance + additionalAllowance;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public double getLocalityAllowance() {
        return localityAllowance;
    }

    public void setLocalityAllowance(double localityAllowance) {
        this.localityAllowance = localityAllowance;
    }

    public double getAdditionalAllowance() {
        return additionalAllowance;
    }

    public void setAdditionalAllowance(double additionalAllowance) {
        this.additionalAllowance = additionalAllowance;
    }
}
