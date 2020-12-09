package com.arrizaqu.samplecrud.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arrizaqu.samplecrud.entity.Training;
import com.arrizaqu.samplecrud.repo.TrainingRepository;

@Service
@Transactional
public class TrainingService {

	@Autowired
	private TrainingRepository trainingRepository;
	public List<Training> findAll(){
		return this.trainingRepository.findAll();
	}
}
