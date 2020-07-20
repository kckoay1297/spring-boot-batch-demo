package com.spring.batch.job.model;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.batch.job.model.bean.Transaction;

@Repository
@Transactional
public interface TransactionRepository extends JpaRepository<Transaction ,Integer>{

	
}
