package com.jci.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import com.jci.transfer.BPSDetails;

/**
 * The persistent class for the BUS_PRTNR_DIM_CN database table.
 * 
 */
@Entity
@Table(name = "BUS_PRTNR_DIM_CN")
@NamedQuery(name = "BusPrtnrDimCn.findAll", query = "SELECT b FROM BusPrtnrDimCn b")

@SqlResultSetMapping(name = "BPSDetails", classes = { @ConstructorResult(targetClass = BPSDetails.class, columns = {

		@ColumnResult(name = "busPrtnrDimCnKey", type = int.class),

		@ColumnResult(name = "busPrtnrCde", type = String.class),

		@ColumnResult(name = "busPrtnrCntryCde", type = String.class),

		@ColumnResult(name = "busPrtnrNm", type = String.class),

		@ColumnResult(name = "busPrtnrStat", type = String.class),

		@ColumnResult(name = "busPrtnrTyp", type = String.class),

		@ColumnResult(name = "busPrtnrTypDesc", type = String.class),

		@ColumnResult(name = "expTstmp", type = Calendar.class),

		@ColumnResult(name = "insrtTstmp", type = Calendar.class),

		@ColumnResult(name = "mdgCustKey", type = String.class),

		@ColumnResult(name = "minmOrdQty", type = Integer.class),

		@ColumnResult(name = "mdgOprtnTstmp", type = Calendar.class)

		}) })

public class BusPrtnrDimCn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "BUS_PRTNR_DIM_CN_KEY")
	private int busPrtnrDimCnKey;

	@Column(name = "BUS_PRTNR_CDE")
	private String busPrtnrCde;

	@Column(name = "BUS_PRTNR_CNTRY_CDE")
	private String busPrtnrCntryCde;

	@Column(name = "BUS_PRTNR_NM")
	private String busPrtnrNm;

	@Column(name = "BUS_PRTNR_STAT")
	private String busPrtnrStat;

	@Column(name = "BUS_PRTNR_TYP")
	private String busPrtnrTyp;

	@Column(name = "BUS_PRTNR_TYP_DESC")
	private String busPrtnrTypDesc;

	@Column(name = "EXP_TSTMP")
	private Timestamp expTstmp;

	@Column(name = "INSRT_TSTMP")
	private Timestamp insrtTstmp;

	@Column(name = "MDG_CUST_KEY")
	private String mdgCustKey;

	@Column(name = "MDG_OPRTN_TSTMP")
	private Timestamp mdgOprtnTstmp;

	@Column(name = "MINM_ORD_QTY")
	private Integer minmOrdQty;

	public BusPrtnrDimCn() {
	}

	public int getBusPrtnrDimCnKey() {
		return this.busPrtnrDimCnKey;
	}

	public void setBusPrtnrDimCnKey(int busPrtnrDimCnKey) {
		this.busPrtnrDimCnKey = busPrtnrDimCnKey;
	}

	public String getBusPrtnrCde() {
		return this.busPrtnrCde;
	}

	public void setBusPrtnrCde(String busPrtnrCde) {
		this.busPrtnrCde = busPrtnrCde;
	}

	public String getBusPrtnrCntryCde() {
		return this.busPrtnrCntryCde;
	}

	public void setBusPrtnrCntryCde(String busPrtnrCntryCde) {
		this.busPrtnrCntryCde = busPrtnrCntryCde;
	}

	public String getBusPrtnrNm() {
		return this.busPrtnrNm;
	}

	public void setBusPrtnrNm(String busPrtnrNm) {
		this.busPrtnrNm = busPrtnrNm;
	}

	public String getBusPrtnrStat() {
		return this.busPrtnrStat;
	}

	public void setBusPrtnrStat(String busPrtnrStat) {
		this.busPrtnrStat = busPrtnrStat;
	}

	public String getBusPrtnrTyp() {
		return this.busPrtnrTyp;
	}

	public void setBusPrtnrTyp(String busPrtnrTyp) {
		this.busPrtnrTyp = busPrtnrTyp;
	}

	public String getBusPrtnrTypDesc() {
		return this.busPrtnrTypDesc;
	}

	public void setBusPrtnrTypDesc(String busPrtnrTypDesc) {
		this.busPrtnrTypDesc = busPrtnrTypDesc;
	}

	public Timestamp getExpTstmp() {
		return this.expTstmp;
	}

	public void setExpTstmp(Timestamp expTstmp) {
		this.expTstmp = expTstmp;
	}

	public Timestamp getInsrtTstmp() {
		return this.insrtTstmp;
	}

	public void setInsrtTstmp(Timestamp insrtTstmp) {
		this.insrtTstmp = insrtTstmp;
	}

	public String getMdgCustKey() {
		return this.mdgCustKey;
	}

	public void setMdgCustKey(String mdgCustKey) {
		this.mdgCustKey = mdgCustKey;
	}

	public Timestamp getMdgOprtnTstmp() {
		return this.mdgOprtnTstmp;
	}

	public void setMdgOprtnTstmp(Timestamp mdgOprtnTstmp) {
		this.mdgOprtnTstmp = mdgOprtnTstmp;
	}

	public Integer getMinmOrdQty() {
		return this.minmOrdQty;
	}

	public void setMinmOrdQty(Integer minmOrdQty) {
		this.minmOrdQty = minmOrdQty;
	}

}
