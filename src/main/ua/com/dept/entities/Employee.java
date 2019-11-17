package main.ua.com.dept.entities;

import main.ua.com.dept.interfaces.IComponent;

import java.io.Serializable;
import java.util.Objects;

public abstract class Employee implements IComponent, Cloneable, Serializable {

    protected Long id;
    protected String firstName;
    protected String lastName;
    protected Integer baseRate;
    protected Integer experience;
    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getBaseRate() {
        return baseRate;
    }

    public Integer getExperience() {
        return experience;
    }

    public float defaultSalary() {
        int experience = this.experience;
        if (experience > 5) {
            return this.baseRate * 1.2f + 500;
        } else if (experience > 2) {
            return this.baseRate + 200;
        }
        return this.baseRate;
    }

    public void printSalary(float salary) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.firstName);
        sb.append(" " + this.lastName);
        sb.append(" got salary: ");
        sb.append(salary);
        System.out.println(sb.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(baseRate, employee.baseRate) &&
                Objects.equals(experience, employee.experience);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, baseRate, experience);
    }

    @Override
    //TODO in classes that extend this
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
