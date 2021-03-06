package com.jci.transfer;

import java.util.List;

public class CmrlProductsResponse {

	private int totalNumber;
	private List<CmrlProduct> products;
	
	public int getTotalNumber() {
		return totalNumber;
	}
	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}
	public List<CmrlProduct> getProducts() {
		return products;
	}
	public void setProducts(List<CmrlProduct> products) {
		this.products = products;
	}
	@Override
	public String toString() {
		return "QADProductsResponse [totalNumber=" + totalNumber + ", products=" + products + "]";
	}
}
