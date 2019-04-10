/**
 * 
 */
package com.jci.service;

import com.jci.transfer.BatterySNResponse;

/**
 * @author apiadmin2
 *
 */
public interface IBatterySN {

	public BatterySNResponse getBatterySerialNumber(String[] serialNo);

	public BatterySNResponse getPackageSerialNumber(String[] serialNo);

	public BatterySNResponse getDualSerialNumber(String[] serialNo);

}
