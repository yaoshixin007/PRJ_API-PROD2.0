package com.jci.service;

import com.jci.common.exception.InvalidDateFormatException;
import com.jci.common.exception.StringTooLongException;
import com.jci.transfer.QADProductsResponse;

public interface IProductQAD {

	/**
	 * Fetch all records
	 * 
	 * @return QADProductsResponse
	 */
	public QADProductsResponse getQadProducts();

	/**
	 * Fetch data based on material number
	 * 
	 * @param materialNum
	 * @return
	 */
	public QADProductsResponse getQadProducts(String[] materialNum);

	public QADProductsResponse getQadProductsV2(String[] materialNum, String[] plmSkuStatusCode, String[] qadSkuStatus,
			String[] productPromoCodeCn, String[] productLegacyBrandCode, String[] groupSizeCode,
			String[] materialQadNumber, String[] materialCommercialItem, String addDateFrom, String addDateTo,
			String expiredDateFrom, String expiredDateTo, String activeDateFrom, String activeDateTo,
			String lastModifiedFrom, String lastModifiedTo) throws InvalidDateFormatException, StringTooLongException;

}
