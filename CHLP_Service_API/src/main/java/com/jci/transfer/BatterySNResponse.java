/**
 * 
 */
package com.jci.transfer;

import java.util.List;

/**
 * @author apiadmin2
 *
 */
public class BatterySNResponse {
	
	private int totalNumber;
	private List<BatterySN> serialNumberList;
	
	public int getTotalNumber() {
		return totalNumber;
	}
	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}
	public List<BatterySN> getSerialNumberList() {
		return serialNumberList;
	}
	public void setSerialNumberList(List<BatterySN> batterySerialNos) {
		this.serialNumberList = batterySerialNos;
	}
	
	@Override
	public String toString() {
		return "BatterySNResponse [totalNumber=" + totalNumber + ", serialNumberList=" + serialNumberList + "]";
	}


}
