package com.example.EmployeeAsync;
import lombok.Data;
import java.io.Serializable;
@Data
public class EmployeeSalary implements Serializable {

    private String empId;
    private double salary;
}