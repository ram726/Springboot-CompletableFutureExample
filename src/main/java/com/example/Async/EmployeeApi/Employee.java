package com.example.Async.EmployeeApi;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class Employee implements Serializable {
    private String empId;
    private String empName;
    private EmployeeSalary salary;
}
