/**
 * 
 */
package com.jci.transfer;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @author apiadmin2
 *
 */
public class SerialNoDetails {
	
	private int productSerialId;
	private String productSerialNo;
	private String packageSerialNo;
	private String materialQadNumber;
	private String materialPlmNumber;
	private String materialCommercialItem;
	private String materialDescriptionCn;             
	private String productPromoDes;
	private String productLegacyBrandDes;
	private String lastOperationChannel;
	private String lastOperationTypeId;
	private Timestamp lastOperationTime;
	private String lastOperatorSystem;
	private String lastOperator;
	private String lastOperationFrom;
	private String lastOperationTo;
	private String lastOperationRemark;
	private String lastReferenceOrderType;
	private String lastReferenceOrderNumber;
	
	public int getProductSerialId() {
		return productSerialId;
	}
	public void setProductSerialId(int serialId) {
		this.productSerialId = serialId;
	}
	public String getProductSerialNo() {
		return productSerialNo;
	}
	public void setProductSerialNo(String productSerialNo) {
		this.productSerialNo = productSerialNo;
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
	public String getMaterialPlmNumber() {
		return materialPlmNumber;
	}
	public void setMaterialPlmNumber(String materialPlmNumber) {
		this.materialPlmNumber = materialPlmNumber;
	}
	public String getMaterialCommercialItem() {
		return materialCommercialItem;
	}
	public void setMaterialCommercialItem(String materialCommercialItem) {
		this.materialCommercialItem = materialCommercialItem;
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
	public String getLastOperationTypeId() {
		return lastOperationTypeId;
	}
	public void setLastOperationTypeId(String lastOperationTypeId) {
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
		return lastOperationTo;
	}
	public void setLastOperationTo(String lastOperationTo) {
		this.lastOperationTo = lastOperationTo;
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
		return "SerialNoDetails [productSerialId=" + productSerialId + ", productSerialNo=" + productSerialNo
				+ ", packageSerialNo=" + packageSerialNo + ", materialQadNumber=" + materialQadNumber
				+ ", materialPlmNumber=" + materialPlmNumber + ", materialCommercialItem=" + materialCommercialItem
				+ ", materialDescriptionCn=" + materialDescriptionCn + ", productPromoDes=" + productPromoDes
				+ ", productLegacyBrandDes=" + productLegacyBrandDes + ", lastOperationChannel=" + lastOperationChannel
				+ ", lastOperationTypeId=" + lastOperationTypeId + ", lastOperationTime=" + lastOperationTime
				+ ", lastOperatorSystem=" + lastOperatorSystem + ", lastOperator=" + lastOperator
				+ ", lastOperationFrom=" + lastOperationFrom + ", lastOperationTo=" + lastOperationTo
				+ ", lastOperationRemark=" + lastOperationRemark + ", lastReferenceOrderType=" + lastReferenceOrderType
				+ ", lastReferenceOrderNumber=" + lastReferenceOrderNumber + "]";
	}
	
	}
