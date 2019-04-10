package com.jci.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jci.common.core.InjectableLogger;
import com.jci.common.exception.BusinessValidationException;
import com.jci.domain.PrdCmrlDtl;
import com.jci.repository.CmrlProductRepository;
import com.jci.repository.JCIApplicationRepositoryCustom;
import com.jci.service.ICmrlProduct;
import com.jci.transfer.CmrlProduct;
import com.jci.transfer.CmrlProductsResponse;
import com.jci.util.ApplicationConstants;

/**
 * Service to implement CmrlProductService
 *
 */

@Service
public class CmrlProductService implements ICmrlProduct {

	@InjectableLogger
	private static Logger logger;

	@Autowired
	CmrlProductRepository cmrlPrdRepo;

	@Autowired
	JCIApplicationRepositoryCustom jciRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CmrlProductsResponse getCmrlProducts(String[] mtrlCmrlNbr) {
		logger.debug("Start Commercial Product Service to fetch data based on Material No: {}", mtrlCmrlNbr);

		List<String> dataError = new ArrayList<>();
		List<BigInteger> mtrlCmrlNbrs = new ArrayList<>();
		BigInteger val;
		// Validate data. If any error then throw Custom exception.
		for (String num : mtrlCmrlNbr) {
			try {
				val = new BigInteger(num);
				mtrlCmrlNbrs.add(val);
			} catch (Exception ex) {
				dataError.add(num);
			}
		}
		if (dataError.size() > 0) {
			throw new BusinessValidationException(
					ApplicationConstants.CMRL_PRD_EXCEP_VALIDATION_MESSAGE + dataError.toString());
		}
		List<PrdCmrlDtl> prdEntity = cmrlPrdRepo.findByMtrlCmrlNbrIn(mtrlCmrlNbrs);
		logger.debug("Data retireved from db. No of records: {}", prdEntity.size());

		CmrlProductsResponse response = new CmrlProductsResponse();
		List<CmrlProduct> products = new ArrayList<>();
		if (!prdEntity.isEmpty()) {
			prdEntity.forEach(productItem -> {
				CmrlProduct detailItem = modelMapper.map(productItem, CmrlProduct.class);
				products.add(detailItem);

			});
		}
		response.setProducts(products);
		response.setTotalNumber(prdEntity.size());

		logger.debug("End Product Commercial Service");
		return response;
	}

	@Override
	public CmrlProductsResponse getCmrlProducts() {
		logger.debug("Start Product Commercial Service");

		List<PrdCmrlDtl> prdEntity = cmrlPrdRepo.findAllCmrlProducts();
		logger.debug("Data retireved from db. No of records: {}", prdEntity.size());

		CmrlProductsResponse response = new CmrlProductsResponse();
		List<CmrlProduct> products = new ArrayList<>();
		if (!prdEntity.isEmpty()) {
			prdEntity.forEach(productItem -> {
				CmrlProduct detailItem = modelMapper.map(productItem, CmrlProduct.class);
				products.add(detailItem);

			});
		}
		response.setProducts(products);
		response.setTotalNumber(prdEntity.size());

		logger.debug("End Product Commercial Service");
		return response;

	}

}
