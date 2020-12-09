package com.arrizaqu.samplecrud.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arrizaqu.samplecrud.controller.MainController;
import com.arrizaqu.samplecrud.entity.Employee;
import com.arrizaqu.samplecrud.entity.Training;
import com.arrizaqu.samplecrud.repo.EmployeeRepository;
import com.arrizaqu.samplecrud.repo.TrainingRepository;

@Service
@Transactional
public class EmployeeService {

	Logger logger = LoggerFactory.getLogger(EmployeeService.class);
	
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private TrainingRepository trainingRepository;
	
	public void save(Employee employee) {
		logger.trace("execute method save() for new employee");
		this.employeeRepository.save(employee);
	}
	
	public void saveEdit(Employee e) {
		logger.trace("execute method edit() for new employee");
		Employee employee = this.employeeRepository.findById(e.getId()).get();
		employee.setName(e.getName());
		employee.setAddress(e.getAddress());
		employee.setEmail(e.getEmail());
		employee.setPosition(e.getPosition());
		this.employeeRepository.save(employee);
	}
	
	public void saveBatch(List<Employee> employees) {
		logger.trace("execute method saveBatch() for new employee");
		this.employeeRepository.saveAll(employees);
	}

	public Page<Employee> findByNameContainsOrEmailContainsOrAddressContainsAllIgnoreCase(String searchText,
			String searchText2, String searchText3, Pageable paging) {
		// TODO Auto-generated method stub
		return this.employeeRepository.findByNameContainsOrEmailContainsOrAddressContainsAllIgnoreCase(searchText, searchText2, searchText3, paging);
	}

	public Employee findById(Long id) {
		// TODO Auto-generated method stub
		return this.employeeRepository.findById(id).get();
	}

	public List<Employee> findTrainingByEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return null;//this.trainingRepository.findByEmployee(emp);
	}

	public void saveTraining(long employeeId, long trainingId) {
		// TODO Auto-generated method stub
		Employee employee = this.employeeRepository.findById(employeeId).get();
		Training tr = new Training();
		tr.setId(trainingId);
		
		employee.setId(employeeId);
		List<Training> trs = new ArrayList<Training>();
		if(!employee.getTrainings().isEmpty()) {
			for(Training tt : employee.getTrainings()) {
				trs.add(tt);
			}
		}
		trs.add(tr);
		employee.setTrainings(trs);
		this.employeeRepository.save(employee);
	}

	public void delete(long id) {
		// TODO Auto-generated method stub
		this.employeeRepository.deleteById(id);
	}

}
