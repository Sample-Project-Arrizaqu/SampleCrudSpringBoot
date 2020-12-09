package com.arrizaqu.samplecrud.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arrizaqu.samplecrud.entity.Position;

public interface PositionRepository extends JpaRepository<Position, Long> {

}
