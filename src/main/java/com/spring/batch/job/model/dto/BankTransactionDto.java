package com.spring.batch.job.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class BankTransactionDto implements Serializable{

	private static final long serialVersionUID = 751928124875311115L;

	private String accountNumber;
	
	private BigDecimal trxAmount;
	
	private String description;
	
	private String trxDate;
	
	private String trxTime;
	
	private String customerId;
	
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public BigDecimal getTrxAmount() {
		return trxAmount;
	}
	public void setTrxAmount(BigDecimal trxAmount) {
		this.trxAmount = trxAmount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTrxDate() {
		return trxDate;
	}
	public void setTrxDate(String trxDate) {
		this.trxDate = trxDate;
	}
	public String getTrxTime() {
		return trxTime;
	}
	public void setTrxTime(String trxTime) {
		this.trxTime = trxTime;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	@Override
	public String toString() {
		return "[BankTransactionDto][accountNumber:"+accountNumber+"][trxAmount:"+trxAmount+"][description:"+description+"][trxDate:"+trxDate+"][trxTime:"
			+ trxTime +"][customerId:"+customerId+"]";
	}
}
