package com.jci.transfer;

import java.math.BigInteger;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CmrlProduct {

	private BigInteger mtrlCmrlNbr;
	private String mtrlCmrlNbrDesc;

	private String productStandardDesc;
	private String productAliasDesc;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Timestamp insertTimestamp;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Timestamp expiryTimestamp;
	private String materialDescCN;
	private String materialDescEN;
	private String groupSizeDescCN;
	private String groupSizeCodeCN;
	private String materialUnitCode;
	private String materialUnitCodeDesc;
	private String productTypeDescCN;
	private String productTypeCodeCN;
	private String productBrandDescCN;
	private String productBrandCodeCN;
	private String productStatusPlm;
	private String productStatusDescPlm;
	private Double grossWeight;
	private Double netWeight;
	private String weightUnitCode;
	private String weightUnitDesc;
	private String matrialLength;
	private String matrialWidth;
	private String matrialHeight;
	private String productLineDescCN;
	private String productLineCodeCN;
	private Double productCustomerAH;
	private String productCustomerCCA;

	public BigInteger getMtrlCmrlNbr() {
		return mtrlCmrlNbr;
	}

	public void setMtrlCmrlNbr(BigInteger mtrlCmrlNbr) {
		this.mtrlCmrlNbr = mtrlCmrlNbr;
	}

	public String getMtrlCmrlNbrDesc() {
		return mtrlCmrlNbrDesc;
	}

	public void setMtrlCmrlNbrDesc(String mtrlCmrlNbrDesc) {
		this.mtrlCmrlNbrDesc = mtrlCmrlNbrDesc;
	}

	public String getProductStandardDesc() {
		return productStandardDesc;
	}

	public void setProductStandardDesc(String productStandardDesc) {
		this.productStandardDesc = productStandardDesc;
	}

	public String getProductAliasDesc() {
		return productAliasDesc;
	}

	public void setProductAliasDesc(String productAliasDesc) {
		this.productAliasDesc = productAliasDesc;
	}

	public Timestamp getInsertTimestamp() {
		return insertTimestamp;
	}

	public void setInsertTimestamp(Timestamp insertTimestamp) {
		this.insertTimestamp = insertTimestamp;
	}

	public Timestamp getExpiryTimestamp() {
		return expiryTimestamp;
	}

	public void setExpiryTimestamp(Timestamp expiryTimestamp) {
		this.expiryTimestamp = expiryTimestamp;
	}

	public String getMaterialDescCN() {
		return materialDescCN;
	}

	public void setMaterialDescCN(String materialDescCN) {
		this.materialDescCN = materialDescCN;
	}

	public String getMaterialDescEN() {
		return materialDescEN;
	}

	public void setMaterialDescEN(String materialDescEN) {
		this.materialDescEN = materialDescEN;
	}

	public String getGroupSizeDescCN() {
		return groupSizeDescCN;
	}

	public void setGroupSizeDescCN(String groupSizeDescCN) {
		this.groupSizeDescCN = groupSizeDescCN;
	}

	public String getGroupSizeCodeCN() {
		return groupSizeCodeCN;
	}

	public void setGroupSizeCodeCN(String groupSizeCodeCN) {
		this.groupSizeCodeCN = groupSizeCodeCN;
	}

	public String getMaterialUnitCode() {
		return materialUnitCode;
	}

	public void setMaterialUnitCode(String materialUnitCode) {
		this.materialUnitCode = materialUnitCode;
	}

	public String getMaterialUnitCodeDesc() {
		return materialUnitCodeDesc;
	}

	public void setMaterialUnitCodeDesc(String materialUnitCodeDesc) {
		this.materialUnitCodeDesc = materialUnitCodeDesc;
	}

	public String getProductTypeDescCN() {
		return productTypeDescCN;
	}

	public void setProductTypeDescCN(String productTypeDescCN) {
		this.productTypeDescCN = productTypeDescCN;
	}

	public String getProductTypeCodeCN() {
		return productTypeCodeCN;
	}

	public void setProductTypeCodeCN(String productTypeCodeCN) {
		this.productTypeCodeCN = productTypeCodeCN;
	}

	public String getProductBrandDescCN() {
		return productBrandDescCN;
	}

	public void setProductBrandDescCN(String productBrandDescCN) {
		this.productBrandDescCN = productBrandDescCN;
	}

	public String getProductBrandCodeCN() {
		return productBrandCodeCN;
	}

	public void setProductBrandCodeCN(String productBrandCodeCN) {
		this.productBrandCodeCN = productBrandCodeCN;
	}

	public String getProductStatusPlm() {
		return productStatusPlm;
	}

	public void setProductStatusPlm(String productStatusPlm) {
		this.productStatusPlm = productStatusPlm;
	}

	public String getProductStatusDescPlm() {
		return productStatusDescPlm;
	}

	public void setProductStatusDescPlm(String productStatusDescPlm) {
		this.productStatusDescPlm = productStatusDescPlm;
	}

	public Double getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(Double grossWeight) {
		this.grossWeight = grossWeight;
	}

	public Double getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(Double netWeight) {
		this.netWeight = netWeight;
	}

	public String getWeightUnitCode() {
		return weightUnitCode;
	}

	public void setWeightUnitCode(String weightUnitCode) {
		this.weightUnitCode = weightUnitCode;
	}

	public String getWeightUnitDesc() {
		return weightUnitDesc;
	}

	public void setWeightUnitDesc(String weightUnitDesc) {
		this.weightUnitDesc = weightUnitDesc;
	}

	public String getMatrialLength() {
		return matrialLength;
	}

	public void setMatrialLength(String matrialLength) {
		this.matrialLength = matrialLength;
	}

	public String getMatrialWidth() {
		return matrialWidth;
	}

	public void setMatrialWidth(String matrialWidth) {
		this.matrialWidth = matrialWidth;
	}

	public String getMatrialHeight() {
		return matrialHeight;
	}

	public void setMatrialHeight(String matrialHeight) {
		this.matrialHeight = matrialHeight;
	}

	public String getProductLineDescCN() {
		return productLineDescCN;
	}

	public void setProductLineDescCN(String productLineDescCN) {
		this.productLineDescCN = productLineDescCN;
	}

	public String getProductLineCodeCN() {
		return productLineCodeCN;
	}

	public void setProductLineCodeCN(String productLineCodeCN) {
		this.productLineCodeCN = productLineCodeCN;
	}

	public Double getProductCustomerAH() {
		return productCustomerAH;
	}

	public void setProductCustomerAH(Double productCustomerAH) {
		this.productCustomerAH = productCustomerAH;
	}

	public String getProductCustomerCCA() {
		return productCustomerCCA;
	}

	public void setProductCustomerCCA(String productCustomerCCA) {
		this.productCustomerCCA = productCustomerCCA;
	}

	@Override
	public String toString() {
		return "CmrlProduct [mtrlCmrlNbr=" + mtrlCmrlNbr + ", mtrlCmrlNbrDesc=" + mtrlCmrlNbrDesc
				+ ", productStandardDesc=" + productStandardDesc + ", productAliasDesc=" + productAliasDesc
				+ ", InsertTimestamp=" + insertTimestamp + ", expiryTimestamp=" + expiryTimestamp + ", materialDescCN="
				+ materialDescCN + ", materialDescEN=" + materialDescEN + ", groupSizeDescCN=" + groupSizeDescCN
				+ ", groupSizeCodeCN=" + groupSizeCodeCN + ", materialUnitCode=" + materialUnitCode
				+ ", materialUnitCodeDesc=" + materialUnitCodeDesc + ", productTypeDescCN=" + productTypeDescCN
				+ ", productTypeCodeCN=" + productTypeCodeCN + ", productBrandDescCN=" + productBrandDescCN
				+ ", productBrandCodeCN=" + productBrandCodeCN + ", productStatusPlm=" + productStatusPlm
				+ ", productStatusDescPlm=" + productStatusDescPlm + ", grossWeight=" + grossWeight + ", netWeight="
				+ netWeight + ", weightUnitCode=" + weightUnitCode + ", weightUnitDesc=" + weightUnitDesc
				+ ", matrialLength=" + matrialLength + ", matrialWidth=" + matrialWidth + ", matrialHeight="
				+ matrialHeight + ", productLineDescCN=" + productLineDescCN + ", productLineCodeCN="
				+ productLineCodeCN + ", productCustomerAH=" + productCustomerAH + ", productCustomerCCA="
				+ productCustomerCCA + "]";
	}

}
