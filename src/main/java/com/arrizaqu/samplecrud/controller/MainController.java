package com.arrizaqu.samplecrud.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.arrizaqu.samplecrud.datatable.ColumnCriterias;
import com.arrizaqu.samplecrud.datatable.Datatables;
import com.arrizaqu.samplecrud.datatable.OrderCriterias;
import com.arrizaqu.samplecrud.datatable.SearchCriterias;
import com.arrizaqu.samplecrud.entity.Employee;
import com.arrizaqu.samplecrud.entity.Position;
import com.arrizaqu.samplecrud.entity.Training;
import com.arrizaqu.samplecrud.repo.EmployeeRepository;
import com.arrizaqu.samplecrud.service.EmployeeService;
import com.arrizaqu.samplecrud.service.PositionService;
import com.arrizaqu.samplecrud.service.TrainingService;

@Controller
@RequestMapping("/")
public class MainController {
	
	Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private PositionService positionService;
	@Autowired
	private TrainingService trainingService;
	
	@GetMapping
	public String index() {
		return "redirect:/employee";
	}

	@GetMapping("/employee")
	public String employee(Model model) {
		
		List<Position> positions = this.positionService.findAll();
		List<Training> trainings = this.trainingService.findAll();
		model.addAttribute("positions", positions);
		model.addAttribute("trainings", trainings);
		return "employee";
	}

	@ModelAttribute("Datatables")
	public Datatables modelFormObject(Datatables formObject) {
	    return formObject;
	}
	
	//data view employee
	@PostMapping(value="/data-employee")
	@ResponseBody
	public Datatables<Employee> getDataEmployee(@ModelAttribute("Datatables") Datatables dto) {
		
		//get active order desc / asc
		Map<String, String> mapOrder = (Map<String, String>) dto.getOrder().get(0);
		String direction = mapOrder.get(OrderCriterias.dir);
		
		//get active index column order
		int indexColumn = Integer.parseInt(mapOrder.get(OrderCriterias.column));
		
		//get columnNmae for order
		Map<String, String> mapColumn = (Map<String, String>) dto.getColumns().get(indexColumn);
		String columnNameForOrder = mapColumn.get(ColumnCriterias.data);
		
		//get search text
		Map<String, String> mapSearch = dto.getSearch();
		String searchText = mapSearch.get(SearchCriterias.value);
		
		Pageable paging = null;
		if(direction.equals("asc")) {
			paging = PageRequest.of(dto.getStart()/dto.getLength(), dto.getLength(), Sort.by(columnNameForOrder).ascending());
		} else {
			paging = PageRequest.of(dto.getStart()/dto.getLength(), dto.getLength(), Sort.by(columnNameForOrder).descending());
		}
		
		Page<Employee> data = employeeService.findByNameContainsOrEmailContainsOrAddressContainsAllIgnoreCase(searchText, searchText,searchText, paging);
		 
		List<Employee> dataEmp = data.getContent();
		Datatables dt = new Datatables<Employee>();
		dt.setData(data.getContent());
		dt.setDraw(dto.getDraw());
		dt.setRecordsFiltered((int)data.getTotalElements());
		dt.setRecordsTotal((int) data.getTotalElements());
		logger.info("execute view employee datatable");
		return dt;
	}
	
	@PostMapping("/data-employee/save")
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody Employee e) {
		this.employeeService.save(e);
		logger.info("employee successfully saved "+ e.getName());
	}
	
	@PostMapping("/data-employee/save-edit")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveEdit(@RequestBody Employee e) {
		this.employeeService.saveEdit(e);
		logger.info("employee successfully edited "+ e.getName());
	}
	
	@GetMapping("/data-employee/delete/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void save(@PathVariable long id) {
		this.employeeService.delete(id);
		logger.info("employee successfully deleted for id : "+ id);
	}
	
	@GetMapping("/data-employee/findtrainingbyemp/{id}")
	@ResponseBody
	public List<Training> findTrainingByEmploye(@PathVariable("id") Long id) {
		return this.employeeService.findById(id).getTrainings();
	}
	
	@GetMapping("/data-employee/findbyid/{id}")
	@ResponseBody
	public Employee findbyid(@PathVariable("id") Long id) {
		return this.employeeService.findById(id);
	}
	
	@PostMapping("/data-employee/savetraining")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveTrainingEmployee(@RequestParam("employee_id") long employeeId, @RequestParam("training_id") long trainingId) {
		this.employeeService.saveTraining(employeeId, trainingId);
		logger.info("employee "+ employeeId + ", successfully add training : "+ trainingId);
	}
	
}
