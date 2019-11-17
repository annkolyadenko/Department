package main.ua.com.dept.entities;

import main.ua.com.dept.exceptions.RuntimeCustomException;
import main.ua.com.dept.interfaces.IComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Department implements IComponent {

    //TODO if needed
    private Long id;
    private String name;
    private List<IComponent> components = new ArrayList<>();

    public void addEmployee(IComponent component) {
        components.add(component);
    }

    public void removeEmployee(IComponent component) {
        components.remove(component);
    }

    //TODO builder if needed
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void calculateSalary() {
        if (Objects.nonNull(components)) {
            for (IComponent component : components) {
                component.calculateSalary();
            }
        } else {
            throw new RuntimeCustomException("List<IComponent> components may not been initialized for Department :" + this.toString());
        }
    }

    @Override
    public void printEmployee() {
        if (Objects.nonNull(components)) {
            for (IComponent component : components) {
                component.printEmployee();
            }
        } else {
            throw new RuntimeCustomException("List<IComponent> components may not been initialized for Department :" + this.toString());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(name, that.name) &&
                //TODO to ask about correctness
                Objects.deepEquals(components, that.components);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, components);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Department :");
        sb.append(" " + this.name);
        return sb.toString();
    }
}
