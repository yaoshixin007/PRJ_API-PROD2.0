package com.jci.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the PRD_CMRL_DTLS database table.
 * 
 */
@Entity
@Table(name = "PRD_CMRL_DTLS")
@NamedQuery(name = "PrdCmrlDtl.findAll", query = "SELECT p FROM PrdCmrlDtl p")
public class PrdCmrlDtl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PRD_CMRL_KEY")
	private BigInteger prdCmrlKey;

	@Column(name = "MTRL_CMRL_NBR")
	private BigInteger mtrlCmrlNbr;

	@Column(name = "MTRL_CMRL_NBR_DESC")
	private String mtrlCmrlNbrDesc;

	@Column(name = "PRD_STD_DESC")
	private String productStandardDesc;

	@Column(name = "PRD_ALIAS_DESC")
	private String productAliasDesc;

	@Column(name = "INSRT_TSTMP")
	private Timestamp insertTimestamp;

	@Column(name = "EXP_TSTMP")
	private Timestamp expiryTimestamp;

	@Column(name = "MTRL_DESC_CN")
	private String materialDescCN;

	@Column(name = "MTRL_DESC_EN")
	private String materialDescEN;

	@Column(name = "GRP_SIZE_DESC_CN")
	private String groupSizeDescCN;

	@Column(name = "GRP_SIZE_CDE_CN")
	private String groupSizeCodeCN;

	@Column(name = "MTRL_UNIT_CDE")
	private String materialUnitCode;

	@Column(name = "MTRL_UNIT_CDE_DESC")
	private String materialUnitCodeDesc;

	@Column(name = "PRD_TYP_DESC_CN")
	private String productTypeDescCN;

	@Column(name = "PRD_TYP_CDE_CN")
	private String productTypeCodeCN;

	@Column(name = "PRD_BRND_DESC_CN")
	private String productBrandDescCN;

	@Column(name = "PRD_BRND_CDE_CN")
	private String productBrandCodeCN;

	@Column(name = "PRD_STAT_PLM")
	private String productStatusPlm;

	@Column(name = "PRD_STAT_DESC_PLM")
	private String productStatusDescPlm;

	@Column(name = "GR_WT")
	private Double grossWeight;

	@Column(name = "NET_WT")
	private Double netWeight;

	@Column(name = "WT_UNIT_CDE")
	private String weightUnitCode;

	@Column(name = "WT_UNIT_DESC")
	private String weightUnitDesc;

	@Column(name = "MTRL_LEN")
	private String matrialLength;

	@Column(name = "MTRL_WID")
	private String matrialWidth;

	@Column(name = "MTRL_HEI")
	private String matrialHeight;

	@Column(name = "PRD_LN_DESC_CN")
	private String productLineDescCN;

	@Column(name = "PRD_LN_CDE_CN")
	private String productLineCodeCN;

	@Column(name = "PRD_CUST_AH")
	private Double productCustomerAH;

	@Column(name = "PRD_CUST_CCA")
	private String productCustomerCCA;

	public PrdCmrlDtl() {
	}

	public BigInteger getPrdCmrlKey() {
		return this.prdCmrlKey;
	}

	public void setPrdCmrlKey(BigInteger prdCmrlKey) {
		this.prdCmrlKey = prdCmrlKey;
	}

	public BigInteger getMtrlCmrlNbr() {
		return this.mtrlCmrlNbr;
	}

	public void setMtrlCmrlNbr(BigInteger mtrlCmrlNbr) {
		this.mtrlCmrlNbr = mtrlCmrlNbr;
	}

	public String getMtrlCmrlNbrDesc() {
		return this.mtrlCmrlNbrDesc;
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

}
