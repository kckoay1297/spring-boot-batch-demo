package com.spring.batch.job.processor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.batch.item.ItemProcessor;

import com.spring.batch.job.model.bean.Transaction;
import com.spring.batch.job.model.dto.BankTransactionDto;

public class TransactionItemProcessor implements ItemProcessor<BankTransactionDto, Transaction> {

	@Override
	public Transaction process(BankTransactionDto item) throws Exception {
		System.out.println("item=" + item.toString());
		
		Transaction transactionBean = new Transaction();
		transactionBean.setAccountNumber(item.getAccountNumber());
		transactionBean.setCustomerId(item.getCustomerId());
		transactionBean.setDescription(item.getDescription());
		transactionBean.setTrxAmount(item.getTrxAmount());
		
		if(item.getTrxDate() != null && item.getTrxTime() != null) {
			Date trxDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(item.getTrxDate() + " " + item.getTrxTime());
			transactionBean.setTrxDate(trxDate);
		}else if(item.getTrxDate() != null && item.getTrxTime() == null) {
			Date trxDate = new SimpleDateFormat("yyyy-MM-dd").parse(item.getTrxDate());
			transactionBean.setTrxDate(trxDate);
		}

		
		return transactionBean;
	}
	
}
