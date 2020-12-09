package com.arrizaqu.samplecrud.repo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.arrizaqu.samplecrud.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	 Page<Employee> findAll(Pageable pageable);
	 Page<Employee> findByNameContainsOrEmailContainsOrAddressContainsAllIgnoreCase(String searchName, String searchEmail, String searchAddress, Pageable pageable);
}
