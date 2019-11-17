package main.ua;


import main.ua.com.dept.entities.Department;
import main.ua.com.dept.factory.DepartmentFactory;

public class Runner {
    public static void main(String[] args) {
        Department dept = DepartmentFactory.createDepartment(3, 12, 6, 3);
        dept.calculateSalary();
    }
}

