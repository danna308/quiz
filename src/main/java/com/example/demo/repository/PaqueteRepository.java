package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ClienteEntity;
import com.example.demo.entity.PaqueteEntity;

@Repository
public interface PaqueteRepository extends JpaRepository<PaqueteEntity, Long>{

	List<PaqueteEntity> findByCliente(ClienteEntity cliente);
}
