package main.ua.com.dept.entities;

import main.ua.com.dept.exceptions.RuntimeCustomException;
import main.ua.com.dept.interfaces.IBuilder;
import main.ua.com.dept.interfaces.IComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Manager extends Employee {

    private List<IComponent> components = new ArrayList<>();

    public void addComponent(IComponent component) {
        components.add(component);
    }

    public void removeComponent(IComponent component) {
        components.remove(component);
    }

    private Manager(ManagerBuilder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.baseRate = builder.baseRate;
        this.experience = builder.experience;
    }

    public static class ManagerBuilder implements IBuilder {
        private Long id;
        private String firstName;
        private String lastName;
        private Integer baseRate;
        private Integer experience;

        @Override
        public ManagerBuilder id(Long id) {
            this.id = id;
            return this;
        }

        @Override
        public ManagerBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        @Override
        public ManagerBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        @Override
        public ManagerBuilder baseRate(Integer baseRate) {
            this.baseRate = baseRate;
            return this;
        }

        @Override
        public ManagerBuilder experience(Integer experience) {
            this.experience = experience;
            return this;
        }

        @Override
        public Manager build() {
            return new Manager(this);
        }
    }

    @Override
    public void calculateSalary() {
        Integer numberOfEmployees = this.components.size();
        if (Objects.nonNull(numberOfEmployees)) {
            float defaultSalary = moreThanHalfDevelopers() ? defaultSalary() * 1.1f : defaultSalary();
            float totalSalary = defaultSalary;
            if (numberOfEmployees > 10) {
                totalSalary = defaultSalary + 300;
            } else if (numberOfEmployees > 5) {
                totalSalary = defaultSalary + 200;
            }
            printSalary(totalSalary);
            for (IComponent component : components) {
                component.calculateSalary();
            }
        } else {
            throw new RuntimeCustomException("List<IComponent> components may not been initialized for Manager :" + this.toString());
        }
    }

    private boolean moreThanHalfDevelopers() {
        int sum = 0;
        if (Objects.nonNull(components)) {
            for (IComponent component : components) {
                if (component instanceof Developer)
                    sum++;
            }
            return sum >= components.size() / 2;
        } else {
            throw new RuntimeCustomException("List<IComponent> components may not been initialized for Manager :" + this.toString());
        }
    }

    @Override
    public void printEmployee() {
        System.out.println(this.toString());
        if (Objects.nonNull(components)) {
            for (IComponent component : components) {
                component.printEmployee();
            }
        } else {
            throw new RuntimeCustomException("List<IComponent> components may not been initialized for Manager :" + this.toString());
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Manager manager = (Manager) o;
        //TODO to ask about correctness
        return Objects.deepEquals(components, manager.components);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), components);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("This employee is Manager :");
        sb.append(this.firstName + " ");
        sb.append(this.lastName);
        sb.append(", id: " + this.id);
        sb.append(", experience: " + this.experience);
        return sb.toString();
    }
}
