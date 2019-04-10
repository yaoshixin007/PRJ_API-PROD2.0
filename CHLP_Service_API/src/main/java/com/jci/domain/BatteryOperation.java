package com.jci.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
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
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "BATT_OPRTN")
@NamedQuery(name = "BatteryOperation.findAll", query = "SELECT a FROM BatteryOperation a")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"insertTimestamp", "expiryTimeStamp"}, 
        allowGetters = true)

public class BatteryOperation implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "BATT_OPRTN_KEY", nullable = false)
	private Integer operationId;
	
	@Column(name = "PRD_SRL_NBR")
	private String serialNo;
	
	@Column(name = "MESSAGE_ID")
	private String messageId;

	@Column(name = "OPRTN_CHL")
	private String operationChannel;

	@Column(name = "OPRTN_TSTMP")
	private Timestamp operationTime;

	@Column(name = "OPRTR_SYS")
	private String operatorSourceSystem;

	@Column(name = "OPRTR")
	private String operator;

	@Column(name = "OPRTN_ORGN")
	private String operationFrom;

	@Column(name = "OPRTN_DEST")
	private String operationDest;

	@Column(name = "OPRTR_RMKS")
	private String operationRemark;

	@Column(name = "RFRNC_ORD_TYP")
	private String referenceOrderType;

	@Column(name = "RFRNC_ORD_NBR")
	private String referenceOrderNumber;
	
	@Column(name = "INSRT_TSTMP", nullable = false, updatable = false)
    @Temporal(TemporalType.DATE)
	@CreatedDate
	private Date insertTimestamp;

	@Column(name = "EXP_TSTMP")
	private Date expiryTimeStamp;

	@Column(name = "BATT_OPRTN_TYP_DIM_KEY")
	private Integer operationTypeId;


   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name="BATT_OPRTN_TYP_DIM_KEY", nullable=false,updatable=false, insertable=false)
   private BatteryOperationType batteryOperationType;
 
   public BatteryOperation() {
		super();
	}
	
	public BatteryOperation(Integer operationId,String serialNo) {
		this.operationId = operationId;
		this.serialNo = serialNo;
	}


	public Integer getOperationId() {
		return operationId;
	}

	public void setOperationId(Integer operationId) {
		this.operationId = operationId;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getOperationChannel() {
		return operationChannel;
	}

	public void setOperationChannel(String operationChannel) {
		this.operationChannel = operationChannel;
	}

	public Integer getOperationTypeId() {
		return operationTypeId;
	}

	public void setOperationTypeId(Integer operationTypeId) {
		this.operationTypeId = operationTypeId;
	}

	public Timestamp getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Timestamp operationTime) {
		this.operationTime = operationTime;
	}

	public String getOperatorSourceSystem() {
		return operatorSourceSystem;
	}

	public void setOperatorSourceSystem(String operatorSourceSystem) {
		this.operatorSourceSystem = operatorSourceSystem;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperationFrom() {
		return operationFrom;
	}

	public void setOperationFrom(String operationFrom) {
		this.operationFrom = operationFrom;
	}

	public String getOperationDest() {
		return operationDest;
	}

	public void setOperationDest(String operationDest) {
		this.operationDest = operationDest;
	}

	public String getOperationRemark() {
		return operationRemark;
	}

	public void setOperationRemark(String operationRemark) {
		this.operationRemark = operationRemark;
	}

	public String getReferenceOrderType() {
		return referenceOrderType;
	}

	public void setReferenceOrderType(String referenceOrderType) {
		this.referenceOrderType = referenceOrderType;
	}

	public String getReferenceOrderNumber() {
		return referenceOrderNumber;
	}

	public void setReferenceOrderNumber(String referenceOrderNumber) {
		this.referenceOrderNumber = referenceOrderNumber;
	}

	public Date getInsertTimestamp() {
		return insertTimestamp;
	}

	public void setInsertTimestamp(Date insertTimestamp) {
		this.insertTimestamp = insertTimestamp;
	}

	public Date getExpiryTimeStamp() {
		return expiryTimeStamp;
	}

	public void setExpiryTimeStamp(Date expiryTimeStamp) {
		this.expiryTimeStamp = expiryTimeStamp;
	}

	
	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}


}
