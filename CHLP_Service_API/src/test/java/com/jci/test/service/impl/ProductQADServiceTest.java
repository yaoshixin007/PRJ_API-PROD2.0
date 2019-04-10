package com.jci.test.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jci.common.exception.BusinessValidationException;
import com.jci.common.exception.InvalidDateFormatException;
import com.jci.common.exception.StringTooLongException;
import com.jci.service.impl.ProductQADService;
import com.jci.test.CHLPApplicationTests;
import com.jci.transfer.QADProductsResponse;

public class ProductQADServiceTest extends CHLPApplicationTests {
	
	@Autowired
	ProductQADService prdServ;

	@Test
	public void getQadProducts() {
		QADProductsResponse expected = new QADProductsResponse();
		expected.setTotalNumber(2);
		QADProductsResponse actual_AllPrd = prdServ.getQadProducts();
		assertEquals(expected.getTotalNumber(), actual_AllPrd.getTotalNumber());
	}

	@Test
	public void getQadProductsBasedOnMaterialNum() {
		QADProductsResponse expected = new QADProductsResponse();
		expected.setTotalNumber(1);
		String[] materialNums = { "1235" };
		QADProductsResponse actualSelected = prdServ.getQadProducts(materialNums);
		assertEquals(expected.getTotalNumber(), actualSelected.getTotalNumber());
	}

	@Test
	public void getQadProductsWithInvalidMaterialNum() {

		Throwable e = null;

		try {
			String[] materialNums = { "1235R" };
			prdServ.getQadProducts(materialNums);
		} catch (Throwable ex) {
			e = ex;
		}

		assertTrue(e instanceof BusinessValidationException);
	}

	@Test
	public void getQadProductsV2() throws InvalidDateFormatException, StringTooLongException {

		String[] materialNum = { "1235" };
		String[] plmSkuStatusCode = { "80" };
		String[] qadSkuStatus = null;
		String[] productPromoCodeCn = null;
		String[] productLegacyBrandCode = null;
		String[] groupSizeCode = null;
		String[] materialQadNumber = null;
		String[] materialCommercialItem = null;
		String addDateFrom = null;
		String addDateTo = null;
		String expiredDateFrom = null;
		String expiredDateTo = null;
		String activeDateFrom = null;
		String activeDateTo = null;
		String lastModifiedFrom = "20151201";
		String lastModifiedTo = "20161231";

		QADProductsResponse expectedResponse = new QADProductsResponse();
		expectedResponse.setTotalNumber(2);

		QADProductsResponse actualResponse = prdServ.getQadProductsV2(materialNum, plmSkuStatusCode, qadSkuStatus,
				productPromoCodeCn, productLegacyBrandCode, groupSizeCode, materialQadNumber, materialCommercialItem, addDateFrom,
				addDateTo, expiredDateFrom, expiredDateTo, activeDateFrom, activeDateTo, lastModifiedFrom,
				lastModifiedTo);

		assertEquals(expectedResponse.getTotalNumber(), actualResponse.getTotalNumber());

	}

	@Test(expected = BusinessValidationException.class)
	public void getQadProductsV2WithInvalidMaterialNum() throws InvalidDateFormatException, StringTooLongException {

		String[] materialNum = { "123R" };
		String[] plmSkuStatusCode = { "15" };
		String[] qadSkuStatus = null;
		String[] productPromoCodeCn = null;
		String[] productLegacyBrandCode = null;
		String[] groupSizeCode = null;
		String[] materialQadNumber = null;
		String[] materialCommercialItem = null;
		String addDateFrom = null;
		String addDateTo = null;
		String expiredDateFrom = null;
		String expiredDateTo = null;
		String activeDateFrom = null;
		String activeDateTo = null;
		String lastModifiedFrom = "20151201";
		String lastModifiedTo = "20161231";

		prdServ.getQadProductsV2(materialNum, plmSkuStatusCode, qadSkuStatus, productPromoCodeCn,
				productLegacyBrandCode, groupSizeCode, materialQadNumber, materialCommercialItem, addDateFrom, addDateTo, expiredDateFrom,
				expiredDateTo, activeDateFrom, activeDateTo, lastModifiedFrom, lastModifiedTo);
	}
	
	@Test
	public void getQadProductsV2WithoutFilter() throws InvalidDateFormatException, StringTooLongException {

		String[] materialNum = null;
		String[] plmSkuStatusCode = null;
		String[] qadSkuStatus = null;
		String[] productPromoCodeCn = null;
		String[] productLegacyBrandCode = null;
		String[] groupSizeCode = null;
		String[] materialQadNumber = null;
		String[] materialCommercialItem = null;
		String addDateFrom = null;
		String addDateTo = null;
		String expiredDateFrom = null;
		String expiredDateTo = null;
		String activeDateFrom = null;
		String activeDateTo = null;
		String lastModifiedFrom = null;
		String lastModifiedTo = null;

		QADProductsResponse expectedResponse = new QADProductsResponse();
		expectedResponse.setTotalNumber(2);

		QADProductsResponse actualResponse = prdServ.getQadProductsV2(materialNum, plmSkuStatusCode, qadSkuStatus,
				productPromoCodeCn, productLegacyBrandCode, groupSizeCode, materialQadNumber, materialCommercialItem, addDateFrom,
				addDateTo, expiredDateFrom, expiredDateTo, activeDateFrom, activeDateTo, lastModifiedFrom,
				lastModifiedTo);

		assertEquals(expectedResponse.getTotalNumber(), actualResponse.getTotalNumber());

	}

}
