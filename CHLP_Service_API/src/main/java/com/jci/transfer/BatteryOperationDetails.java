package com.jci.transfer;

public class BatteryOperationDetails {
	
private Integer operationId;
	
	private String serialNo;
	
	private String operationChannel;

	private Integer operationTypeId;

	private String operationTime;

	private String operatorSourceSystem;

	private String operator;

	private String operationFrom;

	private String operationDest;

	private String operationRemark;

	private String referenceOrderType;

	private String referenceOrderNumber;

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

	public String getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(String operationTime) {
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

	@Override
	public String toString() {
		return "BatteryOperationResponse [operationId=" + operationId + ", serialNo=" + serialNo + ", operationChannel="
				+ operationChannel + ", operationTypeId=" + operationTypeId + ", operationTime=" + operationTime
				+ ", operatorSourceSystem=" + operatorSourceSystem + ", operator=" + operator + ", operationFrom="
				+ operationFrom + ", operationDest=" + operationDest + ", operationRemark=" + operationRemark
				+ ", referenceOrderType=" + referenceOrderType + ", referenceOrderNumber=" + referenceOrderNumber + "]";
	}



}
