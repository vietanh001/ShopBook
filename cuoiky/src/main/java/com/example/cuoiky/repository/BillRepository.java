package com.example.cuoiky.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cuoiky.model.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long>{

}
