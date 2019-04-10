package com.jci.test.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jci.common.exception.BusinessValidationException;
import com.jci.service.impl.CmrlProductService;
import com.jci.test.CHLPApplicationTests;
import com.jci.transfer.CmrlProductsResponse;

public class CmrlProductServiceTest extends CHLPApplicationTests {
	
	@Autowired
	CmrlProductService	cmrlPrdService;	
	
	@Test
	public void fetchCmrlProducts() {
		CmrlProductsResponse expected = new CmrlProductsResponse();
		expected.setTotalNumber(2);
		CmrlProductsResponse actual_AllPrd = cmrlPrdService.getCmrlProducts();
		assertEquals(expected.getTotalNumber(), actual_AllPrd.getTotalNumber());
	}

	@Test
	public void fetchCmrlProductsBasedOnCmrlNum() {
		CmrlProductsResponse expected = new CmrlProductsResponse();
		expected.setTotalNumber(1);
		String[] cmrlPrds = { "20000" };
		CmrlProductsResponse actualSelected = cmrlPrdService.getCmrlProducts(cmrlPrds);
		assertEquals(expected.getTotalNumber(), actualSelected.getTotalNumber());
	}

	@Test
	public void getCmrlProductsWithInvalidCmrlPrdNum() {

		Throwable e = null;

		try {
			String[] cmrlPrds = { "11h" };
			cmrlPrdService.getCmrlProducts(cmrlPrds);
		} catch (Throwable ex) {
			e = ex;
		}

		assertTrue(e instanceof BusinessValidationException);
	}
}
