package com.example.EmployeeAsync;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
class EmployeeDAO {
    public List<Employee> fetchEmployee() {
        List<Employee> employees = getEmployees();

        return employees;
    }

    private List<Employee> getEmployees() {
        log.info("Calling----> EmployeeDAO->getEmployees()");
         Employee emp = new Employee();
        emp.setEmpId("1234"); 
        emp.setEmpName("Sacin Tendulakr");

        Employee emp1 = new Employee();
        emp1.setEmpId("5678");
        emp1.setEmpName("M.S. Dhoni");

        Employee emp2 = new Employee();
        emp2.setEmpId("90123");
        emp2.setEmpName("Rahul Dravid");

        List<Employee> employees = new ArrayList<>();
        employees.add(emp);
        employees.add(emp1);
        employees.add(emp2);
        return employees;
    }
}