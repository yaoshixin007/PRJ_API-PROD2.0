package com.jci.transfer;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class BatteryOperationResponse {
	
	
	@JsonProperty("batterySNOperation")
	private List<BatteryOperationDetails> batteryOperationResponse;

	public List<BatteryOperationDetails> getBatteryOperationResponse() {
		return batteryOperationResponse;
	}

	public void setBatteryOperationResponse(List<BatteryOperationDetails> batteryOperationResponse) {
		this.batteryOperationResponse = batteryOperationResponse;
	}

	@Override
	public String toString() {
		return "BatteryOperationResponse [batteryOperationResponse=" + batteryOperationResponse + "]";
	}
	
	
	
}
