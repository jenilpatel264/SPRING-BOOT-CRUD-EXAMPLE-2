package com.javatechie.crud.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javatechie.crud.example.entity.studentVO;

public interface studentDAO extends JpaRepository<studentVO, Integer>{

}
