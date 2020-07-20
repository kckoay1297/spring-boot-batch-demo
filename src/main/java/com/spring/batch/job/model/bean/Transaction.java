package com.spring.batch.job.model.bean;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@RequiredArgsConstructor
@Table(name = "TRANSACTION")
public class Transaction{
	
	@Id
	@GeneratedValue
	@Column(name = "TRANSACTION_ID")
	private Integer transactionId;
	
	@NonNull
	@Column(name = "ACCOUNT_NUMBER")
	private String accountNumber;
	
	@NonNull
	@Column(name = "TRX_AMOUNT")
	private BigDecimal trxAmount;
	
	@NonNull
	@Column(name = "DESCRIPTION")
	private String description;
	
	@NonNull
	@Column(name = "TRX_DATE")
	private Date trxDate;
	
	@NonNull
	@Column(name = "CUSTOMER_ID")
	private String customerId;

	public Integer getTransactionId() {
		return transactionId;
	}
	
	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}
	
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

	public Date getTrxDate() {
		return trxDate;
	}

	public void setTrxDate(Date trxDate) {
		this.trxDate = trxDate;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	@Override
	public String toString() {
		return "[Transaction][accountNumber:"+accountNumber+"][trxAmount:"+trxAmount+"][description:"+description+"][trxDate:"+trxDate+"]"
				+ "[customerId:"+customerId+"][TRANSACTION_ID:"+transactionId+"]";
	}
}
