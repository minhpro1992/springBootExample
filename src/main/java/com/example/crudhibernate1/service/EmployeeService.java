package com.example.crudhibernate1.service;

import com.example.crudhibernate1.entity.EmployeeEntity;
import com.example.crudhibernate1.model.Employee;
import com.example.crudhibernate1.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jca.cci.RecordTypeNotSupportedException;
import org.springframework.stereotype.Service;

import javax.transaction.NotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public List<EmployeeEntity> getAllEmployees() {
        List<EmployeeEntity> employeeList = employeeRepository.findAll();
        if (employeeList.size() > 0) {
            return  employeeList;
        } else {
            return new ArrayList<EmployeeEntity>();
        }
    }

    public EmployeeEntity getEmployeeById(Long id) throws DataAccessException {
            Optional<EmployeeEntity> employee =  employeeRepository.findById(id);
            if(employee.isPresent()) {
                return employee.get();
            }
        return null;
    }

    public EmployeeEntity addEmployee(EmployeeEntity employeeEntity) {
        Long employeeId = employeeEntity.getId();
        if (employeeId != null) {
            Optional<EmployeeEntity> employee = employeeRepository.findById(employeeEntity.getId());
            if (employee.isPresent()) {
                EmployeeEntity newEmployeeEntity = employee.get();
                newEmployeeEntity.setFirstName(employeeEntity.getFirstName());
                newEmployeeEntity.setLastName(employeeEntity.getLastName());
                newEmployeeEntity.setEmail(employeeEntity.getEmail());
                newEmployeeEntity = employeeRepository.save(newEmployeeEntity);
                return newEmployeeEntity;
            } else {
                employeeEntity = employeeRepository.save(employeeEntity);
                return employeeEntity;
            }
        } else {
            int newEmployeeId = employeeRepository.findAll().size() + 1;
            employeeEntity.setId((long) newEmployeeId);
            employeeEntity = employeeRepository.save(employeeEntity);
            return employeeEntity;
        }
    }

    public List<EmployeeEntity> getEmployeesByConditions() {
        List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();
        employeeEntityList = employeeEntityList.stream()
                .filter(employeeEntity -> employeeEntity.getAge() < 25 && employeeEntity.getGender().equals("male"))
                .collect(Collectors.toList());
        return employeeEntityList;
    }

    public List<Employee> getEmployeesByModel() {
        List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();
        List<Employee> list = new ArrayList<>();
        for (EmployeeEntity employeeEntity : employeeEntityList
             ) {
            Employee employee = new Employee();
            employee.setEmail(employeeEntity.getEmail());
            employee.setAge(employeeEntity.getAge());
            employee.setGender(employeeEntity.getGender());
            list.add(employee);
        }
        return list;
    }

    public void deleteEmployeeById(Long id) {
        Optional<EmployeeEntity> deletedEmployee = employeeRepository.findById(id);
        if(deletedEmployee.isPresent()) {
            employeeRepository.deleteById(id);
        }
    }
}
