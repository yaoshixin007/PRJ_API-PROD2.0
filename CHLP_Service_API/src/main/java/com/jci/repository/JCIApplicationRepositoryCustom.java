package com.jci.repository;

import java.util.List;

import com.jci.domain.BusPrtnrDimCn;
import com.jci.domain.MtrlDimCn;

/**
*/

public interface JCIApplicationRepositoryCustom {

	public List<BusPrtnrDimCn> getMDGBusinessPartnerDetailsV2(List<String> mdgCustKey, List<String> bpStatus,
			List<String> bpType, List<String> bpCode, List<String> countryCode);

	public List<MtrlDimCn> getQadProductsV2(String[] materialNum, String[] plmSkuStatusCode, String[] qadSkuStatus,
			String[] productPromoCodeCn, String[] productLegacyBrandCode, String[] groupSizeCode,
			String[] materialQadNumber, String[] materialCommercialItem, String addDateFrom, String addDateTo,
			String expiredDateFrom, String expiredDateTo, String activeDateFrom, String activeDateTo,
			String lastModifiedFrom, String lastModifiedTo);
}
