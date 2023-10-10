package com.javatechie.crud.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.javatechie.crud.example.entity.addVO;
import com.javatechie.crud.example.entity.addressVO;

public interface addressDAO extends JpaRepository<addressVO, Integer>{
	
	
	@Query(value = "select id,address from addressvo",nativeQuery = true)
	public List<addVO> allList();
	
	@Query("select u from addressVO u where u.country = :n")
	public List<addressVO> allList(@Param("n") String name);

}
