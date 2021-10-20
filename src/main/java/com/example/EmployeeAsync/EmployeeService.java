package com.example.EmployeeAsync;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class EmployeeService {
    private EmployeeDAO employeeDAO;

    @Autowired
    private EmployeeSalaryAsyncService employeeSalaryAsyncService;

    public EmployeeService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public List<Employee> getAllEmployee() {
        List<Employee> employeeList = employeeDAO.fetchEmployee();
        List<EmployeeSalary> employeeSalaryList = new ArrayList<>();

        List<CompletableFuture<EmployeeSalary>> listOfCompletableFutureEmployee = new ArrayList<>();

        for (Employee e : employeeList){

            CompletableFuture<EmployeeSalary> completableFuture = employeeSalaryAsyncService
                    .fetchSalary(e.getEmpId());
            listOfCompletableFutureEmployee.add(completableFuture);
        }

        CompletableFuture<EmployeeSalary>[] completableFuturesArray = Arrays
                .copyOf(listOfCompletableFutureEmployee.toArray(),
                        listOfCompletableFutureEmployee.size(), CompletableFuture[].class);

        CompletableFuture.allOf(completableFuturesArray).join();

        employeeSalaryList = getEmployeeSalaryList(completableFuturesArray);

           populateSalary(employeeList, employeeSalaryList);

        return employeeList;
    }

    private void populateSalary(List<Employee> employeeList, List<EmployeeSalary> employeeSalaryList) {
        for (Employee employee : employeeList){
            for (EmployeeSalary employeeSalary : employeeSalaryList){
                if (employee.getEmpId()==employeeSalary.getEmpId()){
                    employee.setSalary(employeeSalary);
                }
            }
        }
    }

    private List<EmployeeSalary>  getEmployeeSalaryList(CompletableFuture<EmployeeSalary>[]
                                                                completableFuturesArray) {
        List<EmployeeSalary> employeeSalaryList = new ArrayList<>();
        for(CompletableFuture<EmployeeSalary> employeeSalary : completableFuturesArray)
        {
            try {
                employeeSalaryList.add(employeeSalary.get());

            } catch (InterruptedException | ExecutionException e) {
                log.error("Exception during parallel processing exception {}", e.getMessage());
                throw new RuntimeException(null, e);
            }
        }
        return employeeSalaryList;
    }
}
