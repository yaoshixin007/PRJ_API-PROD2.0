package com.jci.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "BATT_OPRTN_TYP_DIM")
@NamedQuery(name = "BatteryOperationType.findAll", query = "SELECT a FROM BatteryOperationType a")
public class BatteryOperationType implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "BATT_OPRTN_TYP_DIM_KEY")
	private Integer batteryOperationAuditId;
	
	@Column(name = "OPRTN_TYP_CDE")
	private String opertationTypeCode;

	@Column(name = "OPRTN_TYP_DESC")
	private String opertationTypeDesc;

	@Column(name = "OPRTR_NM")
	private Timestamp operatorNM;


	@Column(name = "OPRTN_ORGN")
	private String operationOrgin;

	@Column(name = "OPRTN_DEST")
	private String operationDestination;

	@Column(name = "INSRT_TSTMP", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date insertTimestamp;

	
	@Column(name = "EXP_TSTMP")
	private Date expiryTimestamp;

	/**
	 * by-directional many-to-one association to BatteryOperation
	 * */
	@ManyToOne
	@JoinColumn(name = "BATT_OPRTN_KEY", updatable = false)
	private BatteryOperation batteryOperation;
	
	
	public BatteryOperationType() {
		super();
	}



	public Integer getBatteryOperationAuditId() {
		return batteryOperationAuditId;
	}



	public void setBatteryOperationAuditId(Integer batteryOperationAuditId) {
		this.batteryOperationAuditId = batteryOperationAuditId;
	}



	public String getOpertationTypeCode() {
		return opertationTypeCode;
	}



	public void setOpertationTypeCode(String opertationTypeCode) {
		this.opertationTypeCode = opertationTypeCode;
	}



	public String getOpertationTypeDesc() {
		return opertationTypeDesc;
	}



	public void setOpertationTypeDesc(String opertationTypeDesc) {
		this.opertationTypeDesc = opertationTypeDesc;
	}



	public Timestamp getOperatorNM() {
		return operatorNM;
	}



	public void setOperatorNM(Timestamp operatorNM) {
		this.operatorNM = operatorNM;
	}



	public String getOperationOrgin() {
		return operationOrgin;
	}



	public void setOperationOrgin(String operationOrgin) {
		this.operationOrgin = operationOrgin;
	}



	public String getOperationDestination() {
		return operationDestination;
	}



	public void setOperationDestination(String operationDestination) {
		this.operationDestination = operationDestination;
	}



	public Date getInsertTimestamp() {
		return insertTimestamp;
	}



	public void setInsertTimestamp(Date insertTimestamp) {
		this.insertTimestamp = insertTimestamp;
	}



	public Date getExpiryTimestamp() {
		return expiryTimestamp;
	}



	public void setExpiryTimestamp(Date expiryTimestamp) {
		this.expiryTimestamp = expiryTimestamp;
	}



	public BatteryOperation getBatteryOperation() {
		return batteryOperation;
	}



	public void setBatteryOperation(BatteryOperation batteryOperation) {
		this.batteryOperation = batteryOperation;
	}



}
