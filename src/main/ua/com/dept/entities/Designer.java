package main.ua.com.dept.entities;

import main.ua.com.dept.interfaces.IBuilder;

import java.util.Objects;

public final class Designer extends Employee {

    private Float efficiency;
    private Employee manager;

    private Designer(DesignerBuilder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.baseRate = builder.baseRate;
        this.experience = builder.experience;
        this.efficiency = builder.efficiency;
        this.manager = builder.manager;
    }

    public static class DesignerBuilder implements IBuilder {
        private Long id;
        private String firstName;
        private String lastName;
        private Integer baseRate;
        private Integer experience;
        private Float efficiency;
        private Employee manager;

        @Override
        public DesignerBuilder id(Long id) {
            this.id = id;
            return this;
        }

        @Override
        public DesignerBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        @Override
        public DesignerBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        @Override
        public DesignerBuilder baseRate(Integer baseRate) {
            this.baseRate = baseRate;
            return this;
        }

        @Override
        public DesignerBuilder experience(Integer experience) {
            this.experience = experience;
            return this;
        }

        public DesignerBuilder efficiency(Float efficiency) {
            this.efficiency = efficiency;
            return this;
        }

        public DesignerBuilder manager(Employee manager) {
            this.manager = manager;
            return this;
        }

        @Override
        public Designer build() {
            return new Designer(this);
        }
    }

    @Override
    public void calculateSalary() {
        printSalary(defaultSalary() * this.efficiency);
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
        Designer designer = (Designer) o;
        return Objects.equals(efficiency, designer.efficiency) &&
                Objects.equals(manager, designer.manager);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), efficiency, manager);
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
