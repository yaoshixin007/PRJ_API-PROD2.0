package com.jci.transfer;

import java.util.List;

public class QADProductsResponse {

	private int totalNumber;
	private List<QADProduct> products;
	
	public int getTotalNumber() {
		return totalNumber;
	}
	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}
	public List<QADProduct> getProducts() {
		return products;
	}
	public void setProducts(List<QADProduct> products) {
		this.products = products;
	}
	@Override
	public String toString() {
		return "QADProductsResponse [totalNumber=" + totalNumber + ", products=" + products + "]";
	}
}
