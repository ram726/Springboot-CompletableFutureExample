package com.example.Async.EmployeeApi;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;
@Service
public class EmployeeSalaryAsyncService {
    @Async
    public CompletableFuture<EmployeeSalary> fetchSalary(String empId) {
        EmployeeSalary employeeSalary = new EmployeeSalary();
        employeeSalary.setEmpId(empId);
        employeeSalary.setSalary(20000);
        return CompletableFuture.completedFuture(employeeSalary);
    }
}