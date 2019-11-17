package main.ua.com.dept.interfaces;

import main.ua.com.dept.entities.Employee;

public interface IBuilder {

    IBuilder id(Long id);

    IBuilder firstName(String firstName);

    IBuilder lastName(String lastName);

    IBuilder baseRate(Integer baseRate);

    IBuilder experience(Integer experience);

    Employee build();

}
