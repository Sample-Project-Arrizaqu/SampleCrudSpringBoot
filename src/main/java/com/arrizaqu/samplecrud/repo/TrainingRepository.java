package com.arrizaqu.samplecrud.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arrizaqu.samplecrud.entity.Employee;
import com.arrizaqu.samplecrud.entity.Training;

public interface TrainingRepository extends JpaRepository<Training, Long> {

}
