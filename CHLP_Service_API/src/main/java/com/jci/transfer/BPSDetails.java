package com.jci.transfer;

import java.sql.Timestamp;


/**
 * @author apiadmin2
 *
 */
public class BPSDetails {
	
	private String mdgCustKey;
	private String bpCode;
	private String bpName;
	private String bpType;
	private String bpTypeDesc;
	private String countryCode;
	private Integer minOrderQty;
	private String bpStatus;
	private String oprTimestamp;
	
	public String getMdgCustKey() {
		return mdgCustKey;
	}
	public void setMdgCustKey(String mdgCustKey) {
		this.mdgCustKey = mdgCustKey;
	}
	public String getBpCode() {
		return bpCode;
	}
	public void setBpCode(String bpCode) {
		this.bpCode = bpCode;
	}
	public String getBpName() {
		return bpName;
	}
	public void setBpName(String bpName) {
		this.bpName = bpName;
	}
	public String getBpType() {
		return bpType;
	}
	public void setBpType(String bpType) {
		this.bpType = bpType;
	}
	public String getBpTypeDesc() {
		return bpTypeDesc;
	}
	public void setBpTypeDesc(String bpTypeDesc) {
		this.bpTypeDesc = bpTypeDesc;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public Integer getMinOrderQty() {
		return minOrderQty;
	}
	public void setMinOrderQty(Integer minOrderQty) {
		this.minOrderQty = minOrderQty;
	}
	public String getBpStatus() {
		return bpStatus;
	}
	public void setBpStatus(String bpStatus) {
		this.bpStatus = bpStatus;
	}
	public String getOprTimestamp() {
		return oprTimestamp;
	}
	public void setOprTimestamp(String oprTimestamp) {
		this.oprTimestamp = oprTimestamp;
	}
	
	@Override
	public String toString() {
		return "BPS [mdgCustKey=" + mdgCustKey + ", bpCode=" + bpCode + ", bpName=" + bpName + ", bpType=" + bpType
				+ ", bpTypeDesc=" + bpTypeDesc + ", countryCode=" + countryCode + ", minOrderQty=" + minOrderQty
				+ ", bpStatus=" + bpStatus + ", oprTimestamp=" + oprTimestamp + "]";
	}
	
}
