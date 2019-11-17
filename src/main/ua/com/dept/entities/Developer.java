package main.ua.com.dept.entities;

import main.ua.com.dept.interfaces.IBuilder;
import main.ua.com.dept.interfaces.IComponent;

import java.util.Objects;

public final class Developer extends Employee implements IComponent {

    private Employee manager;

    private Developer(DeveloperBuilder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.baseRate = builder.baseRate;
        this.experience = builder.experience;
        this.manager = builder.manager;
    }

    public static class DeveloperBuilder implements IBuilder {
        private Long id;
        private String firstName;
        private String lastName;
        private Integer baseRate;
        private Integer experience;
        private Employee manager;

        @Override
        public DeveloperBuilder id(Long id) {
            this.id = id;
            return this;
        }

        @Override
        public DeveloperBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        @Override
        public DeveloperBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        @Override
        public DeveloperBuilder baseRate(Integer baseRate) {
            this.baseRate = baseRate;
            return this;
        }

        @Override
        public DeveloperBuilder experience(Integer experience) {
            this.experience = experience;
            return this;
        }

        public DeveloperBuilder manager(Employee manager) {
            this.manager = manager;
            return this;
        }

        @Override
        public Developer build() {
            return new Developer(this);
        }
    }

    @Override
    public void calculateSalary() {
        printSalary(defaultSalary());
    }

    @Override
    public void printEmployee() {
        System.out.println(this.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Developer developer = (Developer) o;
        return Objects.equals(manager, developer.manager);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), manager);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.firstName + " ");
        sb.append(this.lastName);
        sb.append(", id: " + this.id);
        sb.append(", manager: " + manager.getLastName());
        sb.append(", experience: " + this.experience);
        return sb.toString();
    }
}
