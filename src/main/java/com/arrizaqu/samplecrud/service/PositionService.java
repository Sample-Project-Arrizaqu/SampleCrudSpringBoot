package com.arrizaqu.samplecrud.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arrizaqu.samplecrud.entity.Position;
import com.arrizaqu.samplecrud.repo.PositionRepository;

@Service
@Transactional
public class PositionService {
	
	@Autowired
	private PositionRepository positionRepository;
	
	public List<Position> findAll(){
		return this.positionRepository.findAll();
	}
}
