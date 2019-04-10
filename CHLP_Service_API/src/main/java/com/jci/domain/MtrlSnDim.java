package com.jci.domain;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the MTRL_SN_DIM database table.
 * 
 */

@Entity
@Table(name="MTRL_SN_DIM")
@NamedQuery(name="MtrlSnDim1.findAll", query="SELECT m FROM MtrlSnDim m")
public class MtrlSnDim {



	@Id
	@Column(name = "MTRL_SN_DIM_KEY")
	private int serialId;

	@Column(name = "EXP_TSTMP")
	private Calendar expTstmp;

	/*@Column(name = "INSRT_TSTMP")
	private Calendar insrtTstmp;*/

	@Column(name = "LAST_BATT_OPRTN_TYP_DIM_KEY")
	private Integer lastOperationTypeId;

	@Column(name = "LAST_OPRTN_CHL")
	private String lastOperationChannel;

	@Column(name = "LAST_OPRTN_DEST")
	private String lastOperationDest;

	@Column(name = "LAST_OPRTN_ORGN")
	private String lastOperationFrom;

	@Column(name = "LAST_OPRTN_TSTMP")
	private Timestamp lastOprtnTstmp;

	@Column(name = "LAST_OPRTR")
	private String lastOperator;

	@Column(name = "LAST_OPRTR_RMKS")
	private String lastOperationRemark;

	@Column(name = "LAST_OPRTR_SYS")
	private String lastOperatorSystem;

	@Column(name = "LAST_RFRNC_ORD_NBR")
	private String lastReferenceOrderNumber;

	@Column(name = "LAST_RFRNC_ORD_TYP")
	private String lastReferenceOrderType;

	@Column(name = "MTRL_QAD_NBR")
	private String materialQadNumber;

	@Column(name = "PKG_SRL_NBR")
	private String packageSerialNo;

	@Column(name = "PRD_SRL_NBR")
	private String serialNo;

	// bi-directional many-to-one association to MtrlDimCn1
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MTRL_DIM_CN_KEY")
	private MtrlDimCn mtrlDimCn;

	public int getSerialId() {
		return serialId;
	}

	public void setSerialId(int serialId) {
		this.serialId = serialId;
	}

	public Calendar getExpTstmp() {
		return expTstmp;
	}

	public void setExpTstmp(Calendar expTstmp) {
		this.expTstmp = expTstmp;
	}

	/*public Calendar getInsrtTstmp() {
		return insrtTstmp;
	}

	public void setInsrtTstmp(Calendar insrtTstmp) {
		this.insrtTstmp = insrtTstmp;
	}*/

	public Integer getLastOperationTypeId() {
		return lastOperationTypeId;
	}

	public void setLastOperationTypeId(Integer lastOperationTypeId) {
		this.lastOperationTypeId = lastOperationTypeId;
	}

	public String getLastOperationChannel() {
		return lastOperationChannel;
	}

	public void setLastOperationChannel(String lastOperationChannel) {
		this.lastOperationChannel = lastOperationChannel;
	}

	public String getLastOperationDest() {
		return lastOperationDest;
	}

	public void setLastOperationDest(String lastOperationDest) {
		this.lastOperationDest = lastOperationDest;
	}

	public String getLastOperationFrom() {
		return lastOperationFrom;
	}

	public void setLastOperationFrom(String lastOperationFrom) {
		this.lastOperationFrom = lastOperationFrom;
	}

	public Timestamp getLastOprtnTstmp() {
		return lastOprtnTstmp;
	}

	public void setLastOprtnTstmp(Timestamp lastOprtnTstmp) {
		this.lastOprtnTstmp = lastOprtnTstmp;
	}

	public String getLastOperator() {
		return lastOperator;
	}

	public void setLastOperator(String lastOperator) {
		this.lastOperator = lastOperator;
	}

	public String getLastOperationRemark() {
		return lastOperationRemark;
	}

	public void setLastOperationRemark(String lastOperationRemark) {
		this.lastOperationRemark = lastOperationRemark;
	}

	public String getLastOperatorSystem() {
		return lastOperatorSystem;
	}

	public void setLastOperatorSystem(String lastOperatorSystem) {
		this.lastOperatorSystem = lastOperatorSystem;
	}

	public String getLastReferenceOrderNumber() {
		return lastReferenceOrderNumber;
	}

	public void setLastReferenceOrderNumber(String lastReferenceOrderNumber) {
		this.lastReferenceOrderNumber = lastReferenceOrderNumber;
	}

	public String getLastReferenceOrderType() {
		return lastReferenceOrderType;
	}

	public void setLastReferenceOrderType(String lastReferenceOrderType) {
		this.lastReferenceOrderType = lastReferenceOrderType;
	}

	public String getMaterialQadNumber() {
		return materialQadNumber;
	}

	public void setMaterialQadNumber(String materialQadNumber) {
		this.materialQadNumber = materialQadNumber;
	}

	public String getPackageSerialNo() {
		return packageSerialNo;
	}

	public void setPackageSerialNo(String packageSerialNo) {
		this.packageSerialNo = packageSerialNo;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public MtrlDimCn getMtrlDimCn() {
		return this.mtrlDimCn;
	}

	public void setMtrlDimCn(MtrlDimCn mtrlDimCn) {
		this.mtrlDimCn = mtrlDimCn;
	}

}
