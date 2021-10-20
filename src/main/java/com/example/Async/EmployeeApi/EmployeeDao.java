package com.example.Async.EmployeeApi;

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
        emp.setEmpName("Employee-1");

        Employee emp1 = new Employee();
        emp1.setEmpId("5678");
        emp1.setEmpName("Employee-2");

        Employee emp2 = new Employee();
        emp2.setEmpId("90123");
        emp2.setEmpName("Employee-3");

        List<Employee> employees = new ArrayList<>();
        employees.add(emp);
        employees.add(emp1);
        employees.add(emp2);
        return employees;
    }
}