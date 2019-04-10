package com.jci.transfer;

import java.math.BigInteger;

public class QADProduct {
	private BigInteger materialPlmNumber;
	private String materialQadNumber;
	private BigInteger materialCommercialItem;
	private String materialDescriptionCn;
	private String materialDescriptionEn;
	private BigInteger packageSize;
	private Integer minOrderQty;
	private Boolean inventoryFlag;
	private String activeDate;
	private String expiredDate;
	private String addDate;
	private Integer plmSkuStatusCode;
	private String plmSkuStatusDesc;
	private String qadSkuStatus;
	private String productPromoCodeCn;
	private String productPromoDesCn;
	private String productLegacyBrandCode;
	private String productLegacyBrandDes;
	private String groupSizeCode;
	private String groupSizeDes;
	private String lastModified;
	
	public BigInteger getMaterialPlmNumber() {
		return materialPlmNumber;
	}
	public void setMaterialPlmNumber(BigInteger materialPlmNumber) {
		this.materialPlmNumber = materialPlmNumber;
	}
	public String getMaterialQadNumber() {
		return materialQadNumber;
	}
	public void setMaterialQadNumber(String materialQadNumber) {
		this.materialQadNumber = materialQadNumber;
	}
	public BigInteger getMaterialCommercialItem() {
		return materialCommercialItem;
	}
	public void setMaterialCommercialItem(BigInteger materialCommercialItem) {
		this.materialCommercialItem = materialCommercialItem;
	}
	public String getMaterialDescriptionCn() {
		return materialDescriptionCn;
	}
	public void setMaterialDescriptionCn(String materialDescriptionCn) {
		this.materialDescriptionCn = materialDescriptionCn;
	}
	public String getMaterialDescriptionEn() {
		return materialDescriptionEn;
	}
	public void setMaterialDescriptionEn(String materialDescriptionEn) {
		this.materialDescriptionEn = materialDescriptionEn;
	}
	public BigInteger getPackageSize() {
		return packageSize;
	}
	public void setPackageSize(BigInteger packageSize) {
		this.packageSize = packageSize;
	}
	public Integer getMinOrderQty() {
		return minOrderQty;
	}
	public void setMinOrderQty(Integer minOrderQty) {
		this.minOrderQty = minOrderQty;
	}
	public Boolean getInventoryFlag() {
		return inventoryFlag;
	}
	public void setInventoryFlag(Boolean inventoryFlag) {
		this.inventoryFlag = inventoryFlag;
	}
	public String getActiveDate() {
		return activeDate;
	}
	public void setActiveDate(String activeDate) {
		this.activeDate = activeDate;
	}
	public String getExpiredDate() {
		return expiredDate;
	}
	public void setExpiredDate(String expiredDate) {
		this.expiredDate = expiredDate;
	}
	public String getAddDate() {
		return addDate;
	}
	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}
	public Integer getPlmSkuStatusCode() {
		return plmSkuStatusCode;
	}
	public void setPlmSkuStatusCode(Integer plmSkuStatusCode) {
		this.plmSkuStatusCode = plmSkuStatusCode;
	}
	public String getPlmSkuStatusDesc() {
		return plmSkuStatusDesc;
	}
	public void setPlmSkuStatusDesc(String plmSkuStatusDesc) {
		this.plmSkuStatusDesc = plmSkuStatusDesc;
	}
	public String getQadSkuStatus() {
		return qadSkuStatus;
	}
	public void setQadSkuStatus(String qadSkuStatus) {
		this.qadSkuStatus = qadSkuStatus;
	}
	public String getProductPromoCodeCn() {
		return productPromoCodeCn;
	}
	public void setProductPromoCodeCn(String productPromoCodeCn) {
		this.productPromoCodeCn = productPromoCodeCn;
	}
	public String getProductPromoDesCn() {
		return productPromoDesCn;
	}
	public void setProductPromoDesCn(String productPromoDesCn) {
		this.productPromoDesCn = productPromoDesCn;
	}
	public String getProductLegacyBrandCode() {
		return productLegacyBrandCode;
	}
	public void setProductLegacyBrandCode(String productLegacyBrandCode) {
		this.productLegacyBrandCode = productLegacyBrandCode;
	}
	public String getProductLegacyBrandDes() {
		return productLegacyBrandDes;
	}
	public void setProductLegacyBrandDes(String productLegacyBrandDes) {
		this.productLegacyBrandDes = productLegacyBrandDes;
	}
	public String getGroupSizeCode() {
		return groupSizeCode;
	}
	public void setGroupSizeCode(String groupSizeCode) {
		this.groupSizeCode = groupSizeCode;
	}
	public String getGroupSizeDes() {
		return groupSizeDes;
	}
	public void setGroupSizeDes(String groupSizeDes) {
		this.groupSizeDes = groupSizeDes;
	}
	public String getLastModified() {
		return lastModified;
	}
	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}

	@Override
	public String toString() {
		return "QADProduct [materialPlmNumber=" + materialPlmNumber + ", materialQadNumber=" + materialQadNumber
				+ ", materialCommercialItem=" + materialCommercialItem + ", materialDescriptionCn="
				+ materialDescriptionCn + ", materialDescriptionEn=" + materialDescriptionEn + ", packageSize="
				+ packageSize + ", minOrderQty=" + minOrderQty + ", inventoryFlag=" + inventoryFlag + ", activeDate="
				+ activeDate + ", expiredDate=" + expiredDate + ", addDate=" + addDate + ", plmSkuStatusCode="
				+ plmSkuStatusCode + ", plmSkuStatusDesc=" + plmSkuStatusDesc + ", qadSkuStatus=" + qadSkuStatus
				+ ", productPromoCodeCn=" + productPromoCodeCn + ", productPromoDesCn=" + productPromoDesCn
				+ ", productLegacyBrandCode=" + productLegacyBrandCode + ", productLegacyBrandDes="
				+ productLegacyBrandDes + ", groupSizeCode=" + groupSizeCode + ", groupSizeDes=" + groupSizeDes
				+ ", lastModified=" + lastModified + "]";
	}
}
