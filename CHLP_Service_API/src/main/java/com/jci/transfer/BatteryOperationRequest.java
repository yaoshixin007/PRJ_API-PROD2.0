package com.jci.transfer;

import java.math.BigInteger;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BatteryOperationRequest {

	private String messageId;

	private String channel;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SS")
	private Timestamp requestTime;

	private String serialNo;

	private Integer operationTypeId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SS")
	private Timestamp operationTime;

	private String operatorSourceSystem;

	private String operator;

	private String operationFrom;

	private String operationDest;

	private String operationRemark;

	private String referenceOrderType;

	private String referenceOrderNumber;

	private Integer operationId;
;
	
	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Timestamp getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Timestamp requestTime) {
		this.requestTime = requestTime;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
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

	@Override
	public String toString() {
		return "BatteryOperationRequest [messageId=" + messageId + ", channel=" + channel + ", requestTime="
				+ requestTime + ", serialNo=" + serialNo + ", operationTypeId=" + operationTypeId + ", operationTime="
				+ operationTime + ", operatorSourceSystem=" + operatorSourceSystem + ", operator=" + operator
				+ ", operationFrom=" + operationFrom + ", operationDest=" + operationDest + ", operationRemark="
				+ operationRemark + ", referenceOrderType=" + referenceOrderType + ", referenceOrderNumber="
				+ referenceOrderNumber + "]";
	}

	public Integer getOperationId() {
		return operationId;
	}

	public void setOperationId(Integer operationId) {
		this.operationId = operationId;
	}

}
