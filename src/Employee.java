//  Author:     leonard
//  Project:    MitarbeiterManagement
//  Date:       13:55 03.07.24 


import java.util.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class Employee extends EmployeeManager {
    protected String id;
    protected String firstName;
    protected String lastName;
    protected String address;
    protected LocalDate birthDate;
    protected LocalDate entryDate;
    protected char gender; // 'M' for Male, 'F' for Female
    protected String employeeType;

    public Employee(String firstName, String lastName, String address, LocalDate birthDate, LocalDate entryDate, char gender, String employeeType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.birthDate = birthDate;
        this.entryDate = entryDate;
        this.gender = gender;
        this.employeeType = employeeType;
        this.id = generateEmployeeID();
    }

    private String generateEmployeeID() {
        String genderCode = (gender == 'M') ? "M" : "F";
        String birthYear = String.valueOf(birthDate.getYear()).substring(2);
        String initial = firstName.charAt(0) + "" + lastName.charAt(0);
        String uniqueNumber = String.format("%04d", Math.abs((new Random(firstName.charAt(0)).nextInt()*10 +( new Random(lastName.charAt(0)).nextInt()))%9999)); // Random 4 digit number for simplicity
        return genderCode + birthYear + initial + uniqueNumber + employeeType.charAt(0);
    }

    public abstract double calculateMonthlySalary();

    public String getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getAddress() { return address; }
    public LocalDate getBirthDate() { return birthDate; }
    public LocalDate getEntryDate() { return entryDate; }
    public char getGender() { return gender; }
    public String getEmployeeType() { return employeeType; }

    public void setAddress(String address) { this.address = address; }

    public long getYearsInCompany() {
        return ChronoUnit.YEARS.between(entryDate, LocalDate.now());
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }
}
