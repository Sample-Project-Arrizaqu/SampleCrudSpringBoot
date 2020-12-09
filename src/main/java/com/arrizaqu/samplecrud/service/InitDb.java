package com.arrizaqu.samplecrud.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.arrizaqu.samplecrud.entity.Employee;
import com.arrizaqu.samplecrud.entity.Position;
import com.arrizaqu.samplecrud.entity.Training;
import com.arrizaqu.samplecrud.repo.EmployeeRepository;
import com.arrizaqu.samplecrud.repo.PositionRepository;
import com.arrizaqu.samplecrud.repo.TrainingRepository;

@Component
public class InitDb {
	
	@Autowired
	private EmployeeRepository employeeRepo;
	@Autowired
	private PositionRepository positionRepo;
	@Autowired
	private TrainingRepository trainingRepo;
	
	@PostConstruct
	public void saveDataEmployee() {
		List<Employee> employees = new ArrayList<Employee>();
		List<Position> positions = new ArrayList<Position>();
		List<Training> trainings = new ArrayList<Training>();
		
		//default data employee
		Employee employee = new Employee();
		employee.setEmail("arrizaqu@arrizaqu.com");
		employee.setAddress("seputih banyak lampung");
		employee.setName("Masyda Arrizaqu");
		employees.add(employee);
		
		//default data position
		Position javaPosition = new Position();
		javaPosition.setName("Java Developer");
		javaPosition.setDescription("Assess and develop web application with java programming language");
		
		Position phpPosition = new Position();
		phpPosition.setName("PHP Developer");
		phpPosition.setDescription("Assess and develop web application with php programming language");
		
		Position androidPosition = new Position();
		androidPosition.setName("Android Developer");
		androidPosition.setDescription("Assess and develop web application with android programming language");
		
		positions.add(javaPosition);
		positions.add(phpPosition);
		positions.add(androidPosition);
		
		//default data training
		Training javaFund = new Training();
		javaFund.setTitle("Training Java Fundamental");
		javaFund.setDescription("training java for beginer");
		javaFund.setPlace("West Jakarta Indonesia");
		
		Training javaAdvance = new Training();
		javaAdvance.setTitle("Training Java Advance");
		javaAdvance.setDescription("training java for advance / senior programmer");
		javaAdvance.setPlace("DKI Jakarta Indonesia");
		
		Training phpFund = new Training();
		phpFund.setTitle("Training PHP Fundamental");
		phpFund.setDescription("training PHP for beginer");
		phpFund.setPlace("West Jakarta Indonesia");
		
		Training phpAdvance = new Training();
		phpAdvance.setTitle("Training PHP Advance");
		phpAdvance.setDescription("training PHP for advance / senior programmer");
		phpAdvance.setPlace("DKI Jakarta Indonesia");
		
		trainings.add(javaFund);
		trainings.add(javaAdvance);
		trainings.add(phpFund);
		trainings.add(phpAdvance);

		List<Training> defaultTraining = new ArrayList();
		defaultTraining.add(javaFund);
		defaultTraining.add(phpFund);
		
		//add default data
		trainingRepo.saveAll(trainings);
		positionRepo.saveAll(positions);
		employee.setPosition(javaPosition);
		employee.setTrainings(defaultTraining);
		employeeRepo.saveAll(employees);
		
	}

}
