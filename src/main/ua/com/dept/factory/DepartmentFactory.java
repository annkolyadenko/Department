package main.ua.com.dept.factory;

import main.ua.com.dept.entities.*;
import main.ua.com.dept.exceptions.RuntimeCustomException;

import java.util.Arrays;
import java.util.Random;

public class DepartmentFactory {

    public static Department createDepartment(int amountOfManagers, int... amountOfEmployees) {
        if (amountOfManagers == amountOfEmployees.length && notExceedLimit(amountOfManagers, amountOfEmployees)) {
            Employee manager;
            Employee employee;
            Department department = new Department();
            int count = 0;
            for (int i = 0; i < amountOfManagers; i++) {
                manager = createManager(count);
                count++;
                for (int j = 0; j < amountOfEmployees[i]; j++) {
                    employee = createEmployee(manager, count);
                    ((Manager) manager).addComponent(employee);
                    count++;
                }
                department.addEmployee(manager);
            }
            return department;
        } else {
            throw new RuntimeCustomException("Amount of Employees for each Manager is not correct or exceeded data limit! Manager amount : " + amountOfManagers + " Employees : " + Arrays.toString(amountOfEmployees));
        }
    }

    private static Employee createManager(int id) {
        return new Manager.ManagerBuilder()
                .id(generateId(id))
                .firstName(generateFirstName(id))
                .lastName(generateLastName(id))
                .baseRate(generateBaseRate(FactoryData.MANAGER_SALARY))
                .experience(generateExperience())
                .build();
    }

    private static Employee createEmployee(Employee manager, int id) {
        Employee employee = null;
        int random = new Random().nextInt(2);
        switch (random) {
            case 0:
                employee = new Designer.DesignerBuilder()
                        .id(generateId(id))
                        .firstName(generateFirstName(id))
                        .lastName(generateLastName(id))
                        .baseRate(generateBaseRate(FactoryData.DESIGNER_SALARY))
                        .experience(generateExperience())
                        .efficiency(generateEfficiency())
                        .manager(manager)
                        .build();
                break;
            case 1:
                employee = new Developer.DeveloperBuilder()
                        .id(generateId(id))
                        .firstName(generateFirstName(id))
                        .lastName(generateLastName(id))
                        .baseRate(generateBaseRate(FactoryData.DEVELOPER_SALARY))
                        .experience(generateExperience())
                        .manager(manager)
                        .build();
                break;
        }
        return employee;
    }

    /**
     * Sorry! Private static 4 factory purposes.
     */
    private static long generateId(int id) {
        return (long) id + 1;
    }

    private static String generateFirstName(int id) {
        return FactoryData.FIRST_NAME_LIST[id];
    }

    private static String generateLastName(int id) {
        return FactoryData.LAST_NAME_LIST[id];
    }

    private static float generateEfficiency() {
        return new Random().nextInt(10) / 10f;
    }

    private static int generateBaseRate(int baseSalary) {
        return baseSalary + new Random().nextInt(1000);
    }

    private static int generateExperience() {
        return new Random().nextInt(11);
    }

    private static boolean notExceedLimit(int amountOfManagers, int... amountOfEmployees) {
        int sum = 0;
        for (int i = 0; i < amountOfManagers; i++) {
            sum += amountOfEmployees[i];
        }
        sum += amountOfManagers;
        System.out.println(sum);
        return sum < FactoryData.FIRST_NAME_LIST.length & sum < FactoryData.LAST_NAME_LIST.length;
    }
}

