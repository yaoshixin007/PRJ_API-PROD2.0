package com.jci.transfer;

import java.util.List;


/**
 * @author apiadmin2
 *
 */
public class BPResponse {
	
	private int totalNumber;
	private List<BPSDetails> bpsList;
	
	public int getTotalNumber() {
		return totalNumber;
	}
	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}
	public List<BPSDetails> getBpsList() {
		return bpsList;
	}
	public void setBpsList(List<BPSDetails> bpsList) {
		this.bpsList = bpsList;
	}
	@Override
	public String toString() {
		return "BPResponse [totalNumber=" + totalNumber + ", bpsList=" + bpsList + "]";
	}

}
