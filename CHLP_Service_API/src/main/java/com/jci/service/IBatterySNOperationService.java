package com.jci.service;

import com.jci.common.exception.BatteryOperationBusinessException;
import com.jci.transfer.BatteryOperationRequest;

public interface IBatterySNOperationService<T> {
	
	/**
	 * Fetch all records 
	 * @return QADProductsResponse
	 */
	public T fetchBatterySNOperation(String serialNo) ;

	public T createBatterySNOperation(BatteryOperationRequest batteryOperationRequest) throws BatteryOperationBusinessException;

	public void deleteBatterySNOperation(BatteryOperationRequest batteryOperationRequest);
	
}
