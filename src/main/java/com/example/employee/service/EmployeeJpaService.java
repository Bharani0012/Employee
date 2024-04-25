package com.example.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeJpaRepository;
import com.example.employee.repository.EmployeeRepository;

@Service
public class EmployeeJpaService implements EmployeeRepository {

	@Autowired
	private EmployeeJpaRepository employeejparepository;

	@Override
	public ArrayList<Employee> getEmployees() {
		List<Employee> employeeList = employeejparepository.findAll();
		ArrayList<Employee> employees = new ArrayList<>(employeeList);
		return employees;
	}

	@Override
	public Employee getEmployeeById(int employeeId) {

		try {
			Employee employee = employeejparepository.findById(employeeId).get();
			return employee;

		} catch (Exception e) {

			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public Employee addEmployee(Employee employee) {
		employeejparepository.save(employee);
		return employee;
	}

	@Override
	public Employee updateEmployee(Employee employee, int employeeId) {
		try {
			Employee newEmployee = employeejparepository.findById(employeeId).get();
			if (employee.getEmployeeName() != null) {
				newEmployee.setEmployeeName(employee.getEmployeeName());
			}
			if (employee.getEmail() != null) {
				newEmployee.setEmail(employee.getEmail());
			}
			if (employee.getDepartment() != null) {
				newEmployee.setDepartment(employee.getDepartment());
			}
			employeejparepository.save(newEmployee);

			return newEmployee;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public void deleteEmployee(int employeeId) {
		try {

			employeejparepository.deleteById(employeeId);

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

}
