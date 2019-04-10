package com.jci.test.repository;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.jci.common.core.InjectableLogger;
import com.jci.domain.BusPrtnrDimCn;
import com.jci.domain.MtrlDimCn;
import com.jci.repository.JCIApplicationRepositoryCustom;
import com.jci.test.CHLPApplicationTests;

@WebAppConfiguration
public class JCIApplicationRepositoryCustomImplTest extends CHLPApplicationTests {

	private MockMvc mockMvc;

	@InjectableLogger
	private static Logger logger;

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private JCIApplicationRepositoryCustom jciRepo;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void getMDGBusinessPartnerDetailsV2WithFilters() {

		List<String> mdgCustKey = new ArrayList<>();
		mdgCustKey.add("10000266614");

		List<String> bpStatus = null;
		List<String> bpType = new ArrayList<>();
		bpType.add("GREATWALL-OS");
		List<String> bpCode = new ArrayList<>();
		bpCode.add("BBP000");
		List<String> countryCode = null;

		List<BusPrtnrDimCn> expectedBpResponseList = new ArrayList<>();
		BusPrtnrDimCn bpObj = new BusPrtnrDimCn();
		bpObj.setMdgCustKey("10000266614");
		expectedBpResponseList.add(bpObj);

		List<BusPrtnrDimCn> actualBpResponseList = jciRepo.getMDGBusinessPartnerDetailsV2(mdgCustKey, bpStatus, bpType,
				bpCode, countryCode);

		assertEquals(expectedBpResponseList.size(), actualBpResponseList.size());

	}

	@Test
	public void getMDGBusinessPartnerDetailsV2() {

		List<String> mdgCustKey = new ArrayList<>();
		mdgCustKey.add("10000266614");

		List<String> bpStatus = null;
		List<String> bpType = new ArrayList<>();
		bpType.add("GREATWALL-OS");
		List<String> bpCode = new ArrayList<>();
		bpCode.add("BBP000");
		List<String> countryCode = new ArrayList<>();
		countryCode.add("CN");

		List<BusPrtnrDimCn> expectedBpResponseList = new ArrayList<>();
		BusPrtnrDimCn bpObj = new BusPrtnrDimCn();
		bpObj.setMdgCustKey("10000266614");
		expectedBpResponseList.add(bpObj);

		bpObj = new BusPrtnrDimCn();
		bpObj.setMdgCustKey("10000266615");
		expectedBpResponseList.add(bpObj);

		bpObj = new BusPrtnrDimCn();
		bpObj.setMdgCustKey("10000266616");
		expectedBpResponseList.add(bpObj);

		bpObj = new BusPrtnrDimCn();
		bpObj.setMdgCustKey("10000266617");
		expectedBpResponseList.add(bpObj);

		bpObj = new BusPrtnrDimCn();
		bpObj.setMdgCustKey("10000266618");
		expectedBpResponseList.add(bpObj);

		List<BusPrtnrDimCn> actualBpResponseList = jciRepo.getMDGBusinessPartnerDetailsV2(mdgCustKey, bpStatus, bpType,
				bpCode, countryCode);

		assertEquals(expectedBpResponseList.size(), actualBpResponseList.size());

	}

	@Test
	public void getQadProductsV2TestWithDateFilter() {

		String[] materialNum = null;
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

		List<MtrlDimCn> expectedResponseList = new ArrayList<>();
		MtrlDimCn mtrlDimCnObj = new MtrlDimCn();
		mtrlDimCnObj.setMtrlPlmNbr(new BigInteger("1235"));
		expectedResponseList.add(mtrlDimCnObj);

		mtrlDimCnObj = new MtrlDimCn();
		mtrlDimCnObj.setMtrlPlmNbr(new BigInteger("1236"));
		expectedResponseList.add(mtrlDimCnObj);

		List<MtrlDimCn> actualResponseList = jciRepo.getQadProductsV2(materialNum, plmSkuStatusCode, qadSkuStatus,
				productPromoCodeCn, productLegacyBrandCode, groupSizeCode, materialQadNumber, materialCommercialItem, addDateFrom,
				addDateTo, expiredDateFrom, expiredDateTo, activeDateFrom, activeDateTo, lastModifiedFrom,
				lastModifiedTo);

		assertEquals(expectedResponseList.size(), actualResponseList.size());

	}

}
