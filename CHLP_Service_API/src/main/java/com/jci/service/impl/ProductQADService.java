package com.jci.service.impl;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.jci.common.core.InjectableLogger;
import com.jci.common.exception.BusinessValidationException;
import com.jci.common.exception.StringTooLongException;
import com.jci.domain.MtrlDimCn;
import com.jci.repository.JCIApplicationRepositoryCustom;
import com.jci.repository.ProductQADRepository;
import com.jci.service.IProductQAD;
import com.jci.transfer.QADProduct;
import com.jci.transfer.QADProductsResponse;
import com.jci.util.ApplicationConstants;

/**
 * Service to implement Product QAD
 * 
 * @author apiadmin
 *
 */

@Service
public class ProductQADService implements IProductQAD {

	@InjectableLogger
	private static Logger logger;

	@Autowired
	ProductQADRepository prdRepo;

	@Autowired
	JCIApplicationRepositoryCustom jciRepo;

	@Override
	public QADProductsResponse getQadProducts(String[] materialNum) {
		logger.debug("Start Product QAD Service to fetch data based on Material No: {}", materialNum);
		logger.debug("Fetch data from database");
		List<String> dataError = new ArrayList<>();
		List<BigInteger> materialNums = new ArrayList<>();
		BigInteger val;
		// Validate data. If any error then throw Custom exception.
		for (String num : materialNum) {
			try {
				val = new BigInteger(num);
				materialNums.add(val);
			} catch (Exception ex) {
				dataError.add(num);
			}
		}
		if (dataError.size() > 0) {
			throw new BusinessValidationException(
					ApplicationConstants.PRD_EXCEP_VALIDATION_MESSAGE + dataError.toString());
		}
		List<MtrlDimCn> prdEntity = prdRepo.findByMtrlPlmNbrIn(materialNums);
		logger.debug("Data retireved from db. No of records: {}", prdEntity.size());
		QADProductsResponse response = mappingEntityToJaxb(prdEntity);
		logger.debug("End Product QAD Service");
		return response;
	}

	@Override
	public QADProductsResponse getQadProducts() {
		logger.debug("Start Product QAD Service");
		logger.debug("Fetch data from database");
		List<MtrlDimCn> prdEntity = prdRepo.findByMtrlCmrlNbrNot(BigInteger.ZERO);
		logger.debug("Data retireved from db. No of records: {}", prdEntity.size());
		QADProductsResponse response = mappingEntityToJaxb(prdEntity);
		logger.debug("End Product QAD Service");
		return response;
	}

	@Override
	public QADProductsResponse getQadProductsV2(String[] materialNum, String[] plmSkuStatusCode, String[] qadSkuStatus,
			String[] productPromoCodeCn, String[] productLegacyBrandCode, String[] groupSizeCode, String[] materialQadNumber,
			String[] materialCommercialItem, String addDateFrom, String addDateTo, String expiredDateFrom,
			String expiredDateTo, String activeDateFrom, String activeDateTo, String lastModifiedFrom,
			String lastModifiedTo) throws StringTooLongException {

		logger.debug("Start Product QAD Service With Filters");
		logger.debug("Fetch data from database");

		if (materialNum != null) {
			List<String> dataError = new ArrayList<>();
			List<BigInteger> materialNums = new ArrayList<>();
			BigInteger val;
			for (String num : materialNum) {
				try {
					val = new BigInteger(num);
					materialNums.add(val);
				} catch (Exception ex) {
					dataError.add(num);
				}
			}
			if (!dataError.isEmpty()) {
				throw new BusinessValidationException(
						ApplicationConstants.PRD_EXCEP_VALIDATION_MESSAGE + dataError.toString());
			}
		}

		List<MtrlDimCn> prdEntity = jciRepo.getQadProductsV2(materialNum, plmSkuStatusCode, qadSkuStatus,
				productPromoCodeCn, productLegacyBrandCode, groupSizeCode, materialQadNumber, materialCommercialItem, addDateFrom,
				addDateTo, expiredDateFrom, expiredDateTo, activeDateFrom, activeDateTo, lastModifiedFrom,
				lastModifiedTo);
		logger.debug("Data retireved from db. No of records: {}", prdEntity.size());
		QADProductsResponse response = mappingEntityToJaxb(prdEntity);
		logger.debug("End Product QAD Service");
		return response;
	}

	private QADProductsResponse mappingEntityToJaxb(List<MtrlDimCn> prdEntity) {
		logger.debug("Mapping Entity to POJO ");
		QADProductsResponse response = new QADProductsResponse();
		List<QADProduct> products = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat(ApplicationConstants.DATE_FORMAT_YYYY_MM_DD);
		if (!CollectionUtils.isEmpty(prdEntity)) {
			prdEntity.forEach(entity -> {
				QADProduct product = new QADProduct();
				product.setMaterialPlmNumber(entity.getMtrlPlmNbr());
				product.setMaterialDescriptionEn(entity.getMtrlDescEn());
				if (entity.getPrdActiveDate() != null) {
					product.setActiveDate(sdf.format(entity.getPrdActiveDate().getTime()));
				}
				if (entity.getPlmQadSyncDate() != null) {
					product.setAddDate(sdf.format(entity.getPlmQadSyncDate().getTime()));
				}
				if (entity.getPrdExpDate() != null) {
					product.setExpiredDate(sdf.format(entity.getPrdExpDate().getTime()));
				}
				product.setGroupSizeCode(entity.getGrpSizeCdeCn());
				product.setGroupSizeDes(entity.getGrpSizeDescCn());
				// product.setInventoryFlag(); --Not mentioned
				if (entity.getLastMdfdDateQad() != null) {
					product.setLastModified(sdf.format(entity.getLastMdfdDateQad().getTime()));
				}
				product.setMaterialCommercialItem(entity.getMtrlCmrlNbr());
				product.setMaterialDescriptionCn(entity.getMtrlDescCn());
				product.setMaterialDescriptionEn(entity.getMtrlDescEn());
				product.setMaterialQadNumber(entity.getMtrlQadNbr());
				product.setMinOrderQty(entity.getMinmOrdQty());
				product.setPackageSize(entity.getMtrlPckSize());
				product.setPlmSkuStatusCode(entity.getPrdStatPlm());
				product.setPlmSkuStatusDesc(entity.getPrdStatDescPlm());
				product.setProductLegacyBrandCode(entity.getPrdBrndCdeCn());
				product.setProductLegacyBrandDes(entity.getPrdBrndDescCn());
				product.setProductPromoCodeCn(entity.getPrdLnCdeCn());
				product.setProductPromoDesCn(entity.getPrdLnDescCn());
				product.setQadSkuStatus(entity.getPrdStatQad());
				products.add(product);
			});
			response.setProducts(products);
			response.setTotalNumber(prdEntity.size());
		}
		return response;
	}
}
