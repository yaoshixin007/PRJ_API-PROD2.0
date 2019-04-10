package com.jci.service;

import com.jci.transfer.CmrlProductsResponse;

public interface ICmrlProduct {

	public CmrlProductsResponse getCmrlProducts(String[] mtrlCmrlNbr);

	public CmrlProductsResponse getCmrlProducts();

}
