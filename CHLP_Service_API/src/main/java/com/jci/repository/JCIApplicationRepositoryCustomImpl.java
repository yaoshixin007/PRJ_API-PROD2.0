package com.jci.repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.jci.common.core.InjectableLogger;
import com.jci.domain.BusPrtnrDimCn;
import com.jci.domain.MtrlDimCn;
import com.jci.util.ApplicationConstants;
import com.jci.util.ApplicationUtils;

@Repository
public class JCIApplicationRepositoryCustomImpl implements JCIApplicationRepositoryCustom {

	@InjectableLogger
	Logger logger;

	@Autowired
	EntityManager entityManager;

	@Autowired
	private Environment env;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<BusPrtnrDimCn> getMDGBusinessPartnerDetailsV2(List<String> mdgCustKey, List<String> bpStatus,
			List<String> bpType, List<String> bpCode, List<String> countryCode) {
		StringBuilder queryBuilder = new StringBuilder();
		boolean isfirstFieldPresent = false;
		String dbEnv = env.getProperty("spring.jpa.properties.hibernate.default_schema");
		queryBuilder.append(ApplicationConstants.QUERY_BUILDER_SELECT);
		queryBuilder.append(dbEnv);
		queryBuilder.append(".");
		queryBuilder.append(ApplicationConstants.BusinessPartnerMDG.TABLE_NAME);

		Map<String, List<String>> fieldValueMap = new HashMap<>();
		fieldValueMap.put(ApplicationConstants.BusinessPartnerMDG.MDG_CUST_KEY, mdgCustKey);
		fieldValueMap.put(ApplicationConstants.BusinessPartnerMDG.BUS_PRTNR_STAT, bpStatus);
		fieldValueMap.put(ApplicationConstants.BusinessPartnerMDG.BUS_PRTNR_TYP, bpType);
		fieldValueMap.put(ApplicationConstants.BusinessPartnerMDG.BUS_PRTNR_CDE, bpCode);
		fieldValueMap.put(ApplicationConstants.BusinessPartnerMDG.BUS_PRTNR_CNTRY_CDE, countryCode);

		queryBuilder.append(ApplicationUtils.getQueryWhereClause(fieldValueMap, isfirstFieldPresent));

		logger.debug("Query : {}", queryBuilder);

		Query query = entityManager.createNativeQuery(queryBuilder.toString(), BusPrtnrDimCn.class);

		query = ApplicationUtils.setParameterValues(query, fieldValueMap, null, null);

		List<BusPrtnrDimCn> bpResponseList = (List<BusPrtnrDimCn>) query.getResultList();

		return bpResponseList;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<MtrlDimCn> getQadProductsV2(String[] materialNum, String[] plmSkuStatusCode, String[] qadSkuStatus,
			String[] productPromoCodeCn, String[] productLegacyBrandCode, String[] groupSizeCode,
			String[] materialQadNumber, String[] materialCommercialItem, String addDateFrom, String addDateTo,
			String expiredDateFrom, String expiredDateTo, String activeDateFrom, String activeDateTo,
			String lastModifiedFrom, String lastModifiedTo) {

		StringBuilder queryBuilder = new StringBuilder();
		boolean isfirstFieldPresent = false;
		boolean isWhereClausePresent = false;
		Map<String, List<String>> fieldValueMap = new HashMap<>();
		Map<String, String> rangeFromValueMap = new HashMap<>();
		Map<String, String> rangeToValueMap = new HashMap<>();

		String dbEnv = env.getProperty("spring.jpa.properties.hibernate.default_schema");
		queryBuilder.append(ApplicationConstants.QUERY_BUILDER_SELECT);
		queryBuilder.append(dbEnv);
		queryBuilder.append(".");
		queryBuilder.append(ApplicationConstants.ProductQAD.TABLE_NAME);
		queryBuilder.append(" ");

		fieldValueMap.put(ApplicationConstants.ProductQAD.MTRL_PLM_NBR,
				Arrays.asList(Optional.ofNullable(materialNum).orElse(new String[0])));
		fieldValueMap.put(ApplicationConstants.ProductQAD.PRD_STAT_PLM,
				Arrays.asList(Optional.ofNullable(plmSkuStatusCode).orElse(new String[0])));
		fieldValueMap.put(ApplicationConstants.ProductQAD.PRD_STAT_QAD,
				Arrays.asList(Optional.ofNullable(qadSkuStatus).orElse(new String[0])));
		fieldValueMap.put(ApplicationConstants.ProductQAD.PRD_LN_CDE_CN,
				Arrays.asList(Optional.ofNullable(productPromoCodeCn).orElse(new String[0])));
		fieldValueMap.put(ApplicationConstants.ProductQAD.PRD_BRND_CDE_CN,
				Arrays.asList(Optional.ofNullable(productLegacyBrandCode).orElse(new String[0])));
		fieldValueMap.put(ApplicationConstants.ProductQAD.GRP_SIZE_CDE_CN,
				Arrays.asList(Optional.ofNullable(groupSizeCode).orElse(new String[0])));
		fieldValueMap.put(ApplicationConstants.ProductQAD.MTRL_QAD_NBR,
				Arrays.asList(Optional.ofNullable(materialQadNumber).orElse(new String[0])));
		fieldValueMap.put(ApplicationConstants.ProductQAD.MTRL_CMRL_NBR,
				Arrays.asList(Optional.ofNullable(materialCommercialItem).orElse(new String[0])));

		StringBuilder whereClauseBuilder = ApplicationUtils.getQueryWhereClause(fieldValueMap, isfirstFieldPresent);
		if (whereClauseBuilder != null && !whereClauseBuilder.toString().isEmpty()) {
			queryBuilder.append(whereClauseBuilder);
			isWhereClausePresent = true;
		}

		rangeFromValueMap.put(ApplicationConstants.ProductQAD.PLM_QAD_SYNC_DATE, addDateFrom);
		rangeFromValueMap.put(ApplicationConstants.ProductQAD.PRD_EXP_DATE, expiredDateFrom);
		rangeFromValueMap.put(ApplicationConstants.ProductQAD.PRD_ACTIVE_DATE, activeDateFrom);
		rangeFromValueMap.put(ApplicationConstants.ProductQAD.LAST_MDFD_DATE_QAD, lastModifiedFrom);

		rangeToValueMap.put(ApplicationConstants.ProductQAD.PLM_QAD_SYNC_DATE, addDateTo);
		rangeToValueMap.put(ApplicationConstants.ProductQAD.PRD_EXP_DATE, expiredDateTo);
		rangeToValueMap.put(ApplicationConstants.ProductQAD.PRD_ACTIVE_DATE, activeDateTo);
		rangeToValueMap.put(ApplicationConstants.ProductQAD.LAST_MDFD_DATE_QAD, lastModifiedTo);

		queryBuilder.append(
				ApplicationUtils.getRangeClauseFilter(isWhereClausePresent, rangeFromValueMap, rangeToValueMap));
		Query query = entityManager.createNativeQuery(queryBuilder.toString(), MtrlDimCn.class);

		query = ApplicationUtils.setParameterValues(query, fieldValueMap, rangeFromValueMap, rangeToValueMap);

		List<MtrlDimCn> qadResponseList = (List<MtrlDimCn>) query.getResultList();

		return qadResponseList;

	}

}
