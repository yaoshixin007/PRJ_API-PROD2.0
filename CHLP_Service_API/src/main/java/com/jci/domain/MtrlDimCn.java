package com.jci.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import com.jci.transfer.QADProductsResponse;

/**
 * The persistent class for the MTRL_DIM_CN database table.
 * 
 */
@Entity
@Table(name = "MTRL_DIM_CN")
@NamedQuery(name = "MtrlDimCn.findAll", query = "SELECT m FROM MtrlDimCn m")
@SqlResultSetMapping(name = "QADProductsResponse", classes = { @ConstructorResult(targetClass = QADProductsResponse.class, columns = {

		@ColumnResult(name = "mtrlDimCnKey", type = int.class),
		
		@ColumnResult(name = "expTstmp", type = Calendar.class),

		@ColumnResult(name = "grpSizeCdeCn", type = String.class),

		@ColumnResult(name = "grpSizeDescCn", type = String.class),
		
		@ColumnResult(name = "lastMdfdDateQad", type = Calendar.class),
		
		@ColumnResult(name = "minmOrdQty", type = Integer.class),

		@ColumnResult(name = "mtrlCmrlNbr", type = BigInteger.class),

		@ColumnResult(name = "mtrlDescCn", type = String.class),

		@ColumnResult(name = "mtrlDescEn", type = String.class),

		@ColumnResult(name = "mtrlPckSize", type = BigInteger.class),
		
		@ColumnResult(name = "mtrlPlmNbr", type = BigInteger.class),

		@ColumnResult(name = "mtrlQadNbr", type = String.class),

		@ColumnResult(name = "plmQadSyncDate", type = Calendar.class),
		
		@ColumnResult(name = "prdActiveDate", type = Calendar.class),
		
		@ColumnResult(name = "prdBrndCdeCn", type = String.class),
		
		@ColumnResult(name = "prdBrndDescCn", type = String.class),
		
		@ColumnResult(name = "prdExpDate", type = Calendar.class),
		
		@ColumnResult(name = "prdLnCdeCn", type = String.class),
		
		@ColumnResult(name = "prdLnDescCn", type = String.class),
		
		@ColumnResult(name = "prdStatDescPlm", type = String.class),
		
		@ColumnResult(name = "prdStatPlm", type = Integer.class),
		
		@ColumnResult(name = "prdStatQad", type = String.class)

		}) })
public class MtrlDimCn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "MTRL_DIM_CN_KEY")
	private Integer mtrlDimCnKey;

	@Column(name = "EXP_TSTMP")
	private Calendar expTstmp;

	@Column(name = "GRP_SIZE_CDE_CN")
	private String grpSizeCdeCn;

	@Column(name = "GRP_SIZE_DESC_CN")
	private String grpSizeDescCn;

	/*
	 * @Column(name="INSRT_TSTMP") private Calendar insrtTstmp;
	 */

	@Column(name = "LAST_MDFD_DATE_QAD")
	private Calendar lastMdfdDateQad;

	@Column(name = "MINM_ORD_QTY")
	private Integer minmOrdQty;

	@Column(name = "MTRL_CMRL_NBR")
	private BigInteger mtrlCmrlNbr;

	@Column(name = "MTRL_DESC_CN")
	private String mtrlDescCn;

	@Column(name = "MTRL_DESC_EN")
	private String mtrlDescEn;

	@Column(name = "MTRL_PCK_SIZE")
	private BigInteger mtrlPckSize;

	@Column(name = "MTRL_PLM_NBR")
	private BigInteger mtrlPlmNbr;

	@Column(name = "MTRL_QAD_NBR")
	private String mtrlQadNbr;

	@Column(name = "PLM_QAD_SYNC_DATE")
	private Calendar plmQadSyncDate;

	@Column(name = "PRD_ACTIVE_DATE")
	private Calendar prdActiveDate;

	@Column(name = "PRD_BRND_CDE_CN")
	private String prdBrndCdeCn;

	@Column(name = "PRD_BRND_DESC_CN")
	private String prdBrndDescCn;

	@Column(name = "PRD_EXP_DATE")
	private Calendar prdExpDate;

	@Column(name = "PRD_LN_CDE_CN")
	private String prdLnCdeCn;

	@Column(name = "PRD_LN_DESC_CN")
	private String prdLnDescCn;

	@Column(name = "PRD_STAT_DESC_PLM")
	private String prdStatDescPlm;

	@Column(name = "PRD_STAT_PLM")
	private Integer prdStatPlm;

	@Column(name = "PRD_STAT_QAD")
	private String prdStatQad;

	// bi-directional many-to-one association to MtrlSnDim1
	@OneToMany(mappedBy = "mtrlDimCn", cascade = CascadeType.ALL)
	private List<MtrlSnDim> mtrlSnDims;

	public MtrlDimCn() {
	}

	public MtrlDimCn(Integer mtrlDimCnKey, Calendar expTstmp, String grpSizeCdeCn, String grpSizeDescCn,
			Calendar lastMdfdDateQad, Integer minmOrdQty, BigInteger mtrlCmrlNbr, String mtrlDescCn, String mtrlDescEn,
			BigInteger mtrlPckSize, BigInteger mtrlPlmNbr, String mtrlQadNbr, Calendar plmQadSyncDate,
			Calendar prdActiveDate, String prdBrndCdeCn, String prdBrndDescCn, Calendar prdExpDate, String prdLnCdeCn,
			String prdLnDescCn, String prdStatDescPlm, Integer prdStatPlm, String prdStatQad,
			List<MtrlSnDim> mtrlSnDims) {
		super();
		this.mtrlDimCnKey = mtrlDimCnKey;
		this.expTstmp = expTstmp;
		this.grpSizeCdeCn = grpSizeCdeCn;
		this.grpSizeDescCn = grpSizeDescCn;
		this.lastMdfdDateQad = lastMdfdDateQad;
		this.minmOrdQty = minmOrdQty;
		this.mtrlCmrlNbr = mtrlCmrlNbr;
		this.mtrlDescCn = mtrlDescCn;
		this.mtrlDescEn = mtrlDescEn;
		this.mtrlPckSize = mtrlPckSize;
		this.mtrlPlmNbr = mtrlPlmNbr;
		this.mtrlQadNbr = mtrlQadNbr;
		this.plmQadSyncDate = plmQadSyncDate;
		this.prdActiveDate = prdActiveDate;
		this.prdBrndCdeCn = prdBrndCdeCn;
		this.prdBrndDescCn = prdBrndDescCn;
		this.prdExpDate = prdExpDate;
		this.prdLnCdeCn = prdLnCdeCn;
		this.prdLnDescCn = prdLnDescCn;
		this.prdStatDescPlm = prdStatDescPlm;
		this.prdStatPlm = prdStatPlm;
		this.prdStatQad = prdStatQad;
		this.mtrlSnDims = mtrlSnDims;
	}

	public Integer getMtrlDimCnKey() {
		return this.mtrlDimCnKey;
	}

	public void setMtrlDimCnKey(Integer mtrlDimCnKey) {
		this.mtrlDimCnKey = mtrlDimCnKey;
	}

	public Calendar getExpTstmp() {
		return this.expTstmp;
	}

	public void setExpTstmp(Calendar expTstmp) {
		this.expTstmp = expTstmp;
	}

	public String getGrpSizeCdeCn() {
		return this.grpSizeCdeCn;
	}

	public void setGrpSizeCdeCn(String grpSizeCdeCn) {
		this.grpSizeCdeCn = grpSizeCdeCn;
	}

	public String getGrpSizeDescCn() {
		return this.grpSizeDescCn;
	}

	public void setGrpSizeDescCn(String grpSizeDescCn) {
		this.grpSizeDescCn = grpSizeDescCn;
	}

	/*
	 * public Calendar getInsrtTstmp() { return this.insrtTstmp; }
	 * 
	 * public void setInsrtTstmp(Calendar insrtTstmp) { this.insrtTstmp =
	 * insrtTstmp; }
	 */

	public Calendar getLastMdfdDateQad() {
		return this.lastMdfdDateQad;
	}

	public void setLastMdfdDateQad(Calendar lastMdfdDateQad) {
		this.lastMdfdDateQad = lastMdfdDateQad;
	}

	public Integer getMinmOrdQty() {
		return this.minmOrdQty;
	}

	public void setMinmOrdQty(Integer minmOrdQty) {
		this.minmOrdQty = minmOrdQty;
	}

	public BigInteger getMtrlCmrlNbr() {
		return this.mtrlCmrlNbr;
	}

	public void setMtrlCmrlNbr(BigInteger mtrlCmrlNbr) {
		this.mtrlCmrlNbr = mtrlCmrlNbr;
	}

	public String getMtrlDescCn() {
		return this.mtrlDescCn;
	}

	public void setMtrlDescCn(String mtrlDescCn) {
		this.mtrlDescCn = mtrlDescCn;
	}

	public String getMtrlDescEn() {
		return this.mtrlDescEn;
	}

	public void setMtrlDescEn(String mtrlDescEn) {
		this.mtrlDescEn = mtrlDescEn;
	}

	public BigInteger getMtrlPckSize() {
		return this.mtrlPckSize;
	}

	public void setMtrlPckSize(BigInteger mtrlPckSize) {
		this.mtrlPckSize = mtrlPckSize;
	}

	public BigInteger getMtrlPlmNbr() {
		return this.mtrlPlmNbr;
	}

	public void setMtrlPlmNbr(BigInteger mtrlPlmNbr) {
		this.mtrlPlmNbr = mtrlPlmNbr;
	}

	public String getMtrlQadNbr() {
		return this.mtrlQadNbr;
	}

	public void setMtrlQadNbr(String mtrlQadNbr) {
		this.mtrlQadNbr = mtrlQadNbr;
	}

	public Calendar getPlmQadSyncDate() {
		return this.plmQadSyncDate;
	}

	public void setPlmQadSyncDate(Calendar plmQadSyncDate) {
		this.plmQadSyncDate = plmQadSyncDate;
	}

	public Calendar getPrdActiveDate() {
		return this.prdActiveDate;
	}

	public void setPrdActiveDate(Calendar prdActiveDate) {
		this.prdActiveDate = prdActiveDate;
	}

	public String getPrdBrndCdeCn() {
		return this.prdBrndCdeCn;
	}

	public void setPrdBrndCdeCn(String prdBrndCdeCn) {
		this.prdBrndCdeCn = prdBrndCdeCn;
	}

	public String getPrdBrndDescCn() {
		return this.prdBrndDescCn;
	}

	public void setPrdBrndDescCn(String prdBrndDescCn) {
		this.prdBrndDescCn = prdBrndDescCn;
	}

	public Calendar getPrdExpDate() {
		return this.prdExpDate;
	}

	public void setPrdExpDate(Calendar prdExpDate) {
		this.prdExpDate = prdExpDate;
	}

	public String getPrdLnCdeCn() {
		return this.prdLnCdeCn;
	}

	public void setPrdLnCdeCn(String prdLnCdeCn) {
		this.prdLnCdeCn = prdLnCdeCn;
	}

	public String getPrdLnDescCn() {
		return this.prdLnDescCn;
	}

	public void setPrdLnDescCn(String prdLnDescCn) {
		this.prdLnDescCn = prdLnDescCn;
	}

	public String getPrdStatDescPlm() {
		return this.prdStatDescPlm;
	}

	public void setPrdStatDescPlm(String prdStatDescPlm) {
		this.prdStatDescPlm = prdStatDescPlm;
	}

	public Integer getPrdStatPlm() {
		return this.prdStatPlm;
	}

	public void setPrdStatPlm(Integer prdStatPlm) {
		this.prdStatPlm = prdStatPlm;
	}

	public String getPrdStatQad() {
		return this.prdStatQad;
	}

	public void setPrdStatQad(String prdStatQad) {
		this.prdStatQad = prdStatQad;
	}
	
	public List<MtrlSnDim> getMtrlSnDims() {
		return this.mtrlSnDims;
	}

	public void setMtrlSnDims(List<MtrlSnDim> mtrlSnDims) {
		this.mtrlSnDims = mtrlSnDims;
	}

	public MtrlSnDim addMtrlSnDim(MtrlSnDim mtrlSnDim) {
		getMtrlSnDims().add(mtrlSnDim);
		mtrlSnDim.setMtrlDimCn(this);

		return mtrlSnDim;
	}

	public MtrlSnDim removeMtrlSnDim(MtrlSnDim mtrlSnDim) {
		getMtrlSnDims().remove(mtrlSnDim);
		mtrlSnDim.setMtrlDimCn(null);

		return mtrlSnDim;
	}
}
