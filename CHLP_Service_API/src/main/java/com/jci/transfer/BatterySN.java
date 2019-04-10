/**
 * 
 */
package com.jci.transfer;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BatterySN {
	
	private int serialId;
	private String serialNo;
	private String packageSerialNo;
	private String materialQadNumber;
	private BigInteger materialPlmNumber;
	private BigInteger materialCommercialItemCode;
	private String materialDescriptionCn;             
	private String productPromoDes;
	private String productLegacyBrandDes;
	private String lastOperationChannel;
	private Integer lastOperationTypeId;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Timestamp lastOperationTime;
	private String lastOperatorSystem;
	private String lastOperator;
	private String lastOperationFrom;
	private String lastOperationDest;
	private String lastOperationRemark;
	private String lastReferenceOrderType;
	private String lastReferenceOrderNumber;
	
	public BatterySN()
	{
		
	}
	
	public BatterySN(int serialId, String serialNo, String packageSerialNo, String materialQadNumber,
			BigInteger materialPlmNumber, BigInteger materialCommercialItemCode, String materialDescriptionCn,
			String productPromoDes, String productLegacyBrandDes, String lastOperationChannel,
			Integer lastOperationTypeId, Timestamp lastOperationTime, String lastOperatorSystem, String lastOperator,
			String lastOperationFrom, String lastOperationDest, String lastOperationRemark,
			String lastReferenceOrderType, String lastReferenceOrderNumber) {
		super();

		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd"); 
		
		this.serialId = serialId;
		this.serialNo = serialNo;
		this.packageSerialNo = packageSerialNo;
		this.materialQadNumber = materialQadNumber;
		this.materialPlmNumber = materialPlmNumber;
		this.materialCommercialItemCode = materialCommercialItemCode;
		this.materialDescriptionCn = materialDescriptionCn;
		this.productPromoDes = productPromoDes;
		this.productLegacyBrandDes = productLegacyBrandDes;
		this.lastOperationChannel = lastOperationChannel;
		this.lastOperationTypeId = lastOperationTypeId;
		//if(lastOperationTime != null) {
					this.lastOperationTime = lastOperationTime;
			//	}
		this.lastOperatorSystem = lastOperatorSystem;
		this.lastOperator = lastOperator;
		this.lastOperationFrom = lastOperationFrom;
		this.lastOperationDest = lastOperationDest;
		this.lastOperationRemark = lastOperationRemark;
		this.lastReferenceOrderType = lastReferenceOrderType;
		this.lastReferenceOrderNumber = lastReferenceOrderNumber;
	} 
	
	public int getSerialId() {
		return serialId;
	}
	public void setSerialId(int serialId) {
		this.serialId = serialId;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getPackageSerialNo() {
		return packageSerialNo;
	}
	public void setPackageSerialNo(String packageSerialNo) {
		this.packageSerialNo = packageSerialNo;
	}
	public String getMaterialQadNumber() {
		return materialQadNumber;
	}
	public void setMaterialQadNumber(String materialQadNumber) {
		this.materialQadNumber = materialQadNumber;
	}
	public BigInteger getMaterialPlmNumber() {
		return materialPlmNumber;
	}
	public void setMaterialPlmNumber(BigInteger materialPlmNumber) {
		this.materialPlmNumber = materialPlmNumber;
	}
	public BigInteger getMaterialCommercialItemCode() {
		return materialCommercialItemCode;
	}
	public void setMaterialCommercialItemCode(BigInteger materialCommercialItemCode) {
		this.materialCommercialItemCode = materialCommercialItemCode;
	}
	public String getMaterialDescriptionCn() {
		return materialDescriptionCn;
	}
	public void setMaterialDescriptionCn(String materialDescriptionCn) {
		this.materialDescriptionCn = materialDescriptionCn;
	}
	public String getProductPromoDes() {
		return productPromoDes;
	}
	public void setProductPromoDes(String productPromoDes) {
		this.productPromoDes = productPromoDes;
	}
	public String getProductLegacyBrandDes() {
		return productLegacyBrandDes;
	}
	public void setProductLegacyBrandDes(String productLegacyBrandDes) {
		this.productLegacyBrandDes = productLegacyBrandDes;
	}
	public String getLastOperationChannel() {
		return lastOperationChannel;
	}
	public void setLastOperationChannel(String lastOperationChannel) {
		this.lastOperationChannel = lastOperationChannel;
	}
	public Integer getLastOperationTypeId() {
		return lastOperationTypeId;
	}
	public void setLastOperationTypeId(Integer lastOperationTypeId) {
		this.lastOperationTypeId = lastOperationTypeId;
	}
	public Timestamp getLastOperationTime() {
		return lastOperationTime;
	}
	public void setLastOperationTime(Timestamp lastOperationTime) {
		this.lastOperationTime = lastOperationTime;
	}
	public String getLastOperatorSystem() {
		return lastOperatorSystem;
	}
	public void setLastOperatorSystem(String lastOperatorSystem) {
		this.lastOperatorSystem = lastOperatorSystem;
	}
	public String getLastOperator() {
		return lastOperator;
	}
	public void setLastOperator(String lastOperator) {
		this.lastOperator = lastOperator;
	}
	
	public String getLastOperationFrom() {
		return lastOperationFrom;
	}
	public void setLastOperationFrom(String lastOperationFrom) {
		this.lastOperationFrom = lastOperationFrom;
	}
	public String getLastOperationTo() {
		return lastOperationDest;
	}
	public void setLastOperationTo(String lastOperationDest) {
		this.lastOperationDest = lastOperationDest;
	}
	public String getLastOperationRemark() {
		return lastOperationRemark;
	}
	public void setLastOperationRemark(String lastOperationRemark) {
		this.lastOperationRemark = lastOperationRemark;
	}
	public String getLastReferenceOrderType() {
		return lastReferenceOrderType;
	}
	public void setLastReferenceOrderType(String lastReferenceOrderType) {
		this.lastReferenceOrderType = lastReferenceOrderType;
	}
	public String getLastReferenceOrderNumber() {
		return lastReferenceOrderNumber;
	}
	public void setLastReferenceOrderNumber(String lastReferenceOrderNumber) {
		this.lastReferenceOrderNumber = lastReferenceOrderNumber;
	}
	@Override
	public String toString() {
		return "SerialNoDetails [serialId=" + serialId + ", serialNo=" + serialNo
				+ ", packageSerialNo=" + packageSerialNo + ", materialQadNumber=" + materialQadNumber
				+ ", materialPlmNumber=" + materialPlmNumber + ", materialCommercialItemCode=" + materialCommercialItemCode
				+ ", materialDescriptionCn=" + materialDescriptionCn + ", productPromoDes=" + productPromoDes
				+ ", productLegacyBrandDes=" + productLegacyBrandDes + ", lastOperationChannel=" + lastOperationChannel
				+ ", lastOperationTypeId=" + lastOperationTypeId + ", lastOperationTime=" + lastOperationTime
				+ ", lastOperatorSystem=" + lastOperatorSystem + ", lastOperator=" + lastOperator
				+ ", lastOperationFrom=" + lastOperationFrom + ", lastOperationTo=" + lastOperationDest
				+ ", lastOperationRemark=" + lastOperationRemark + ", lastReferenceOrderType=" + lastReferenceOrderType
				+ ", lastReferenceOrderNumber=" + lastReferenceOrderNumber + "]";
	}
	
	}
