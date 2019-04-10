/**
 * 
 */
package com.jci.test.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jci.service.impl.BatterySNService;
import com.jci.test.CHLPApplicationTests;
import com.jci.transfer.BatterySNResponse;

/**
 * @author apiadmin2
 *
 */

public class BatterySNServiceTest  extends CHLPApplicationTests{

	@Autowired
	BatterySNService batterySNService;

	@Test
	public void getBatterySerialNumber() {

		BatterySNResponse batterySNResponseExpected = new BatterySNResponse();
		batterySNResponseExpected.setTotalNumber(2);
		BatterySNResponse batterySNResponseActual = new BatterySNResponse();
		String serialNo[] = {"10E","10C"};
		batterySNResponseActual = batterySNService.getBatterySerialNumber(serialNo);
		assertEquals(batterySNResponseExpected.getTotalNumber(), batterySNResponseActual.getTotalNumber());
		
	}
	
	@Test
	public void getPackageSerialNumber() {

		BatterySNResponse batterySNResponseExpected = new BatterySNResponse();
		batterySNResponseExpected.setTotalNumber(2);
		BatterySNResponse batterySNResponseActual = new BatterySNResponse();
		String serialNo[] = {"100E","100C"};
		batterySNResponseActual = batterySNService.getPackageSerialNumber(serialNo);
		assertEquals(batterySNResponseExpected.getTotalNumber(), batterySNResponseActual.getTotalNumber());
		
	}
	
	@Test
	public void getDualSerialNumberWithPrdSerialNum() {

		BatterySNResponse batterySNResponseExpected = new BatterySNResponse();
		batterySNResponseExpected.setTotalNumber(2);
		BatterySNResponse batterySNResponseActual = new BatterySNResponse();
		String serialNo[] = {"10E","10C"};
		batterySNResponseActual = batterySNService.getDualSerialNumber(serialNo);
		assertEquals(batterySNResponseExpected.getTotalNumber(), batterySNResponseActual.getTotalNumber());
		
	}
	
	@Test
	public void getDualSerialNumberWithPkgSerialNum() {

		BatterySNResponse batterySNResponseExpected = new BatterySNResponse();
		batterySNResponseExpected.setTotalNumber(2);
		BatterySNResponse batterySNResponseActual = new BatterySNResponse();
		String serialNo[] = {"100E","100C"};
		batterySNResponseActual = batterySNService.getDualSerialNumber(serialNo);
		assertEquals(batterySNResponseExpected.getTotalNumber(), batterySNResponseActual.getTotalNumber());
		
	}
}
