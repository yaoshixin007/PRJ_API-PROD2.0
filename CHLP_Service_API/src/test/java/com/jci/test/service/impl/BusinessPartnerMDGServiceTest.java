package com.jci.test.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jci.service.impl.BusinessPartnerMDGService;
import com.jci.test.CHLPApplicationTests;
import com.jci.transfer.BPResponse;

@Profile("Test")
@WebAppConfiguration
public class BusinessPartnerMDGServiceTest extends CHLPApplicationTests {
	
	@Autowired
	BusinessPartnerMDGService bpMdg;
	

	@Test
	public void getMDGBusinessPartnerTest() {
		BPResponse expectedBPResponse = new BPResponse();
		expectedBPResponse.setTotalNumber(1);
		List<String> mdgCustKeyList = new ArrayList<>();
		mdgCustKeyList.add("10000266614");
		BPResponse actualBPResponse = bpMdg.getMDGBusinessPartner(mdgCustKeyList);
		assertEquals(expectedBPResponse.getTotalNumber(), actualBPResponse.getTotalNumber());

	}
	
	@Test
	public void getMDGBusinessPartnerWithWrongMdgCustKeyTest() {
		BPResponse expectedBPResponse = new BPResponse();
		expectedBPResponse.setTotalNumber(0);
		List<String> mdgCustKeyList = new ArrayList<>();
		mdgCustKeyList.add("1000026661512");
		BPResponse actualBPResponse = bpMdg.getMDGBusinessPartner(mdgCustKeyList);
		assertEquals(expectedBPResponse.getTotalNumber(), actualBPResponse.getTotalNumber());

	}
	
	@Test
	public void getMDGBusinessPartnerAllRecordsTest() {
		BPResponse expectedBPResponse = new BPResponse();
		expectedBPResponse.setTotalNumber(5);
		BPResponse actualBPResponse = bpMdg.getMDGBusinessPartner();
		assertEquals(expectedBPResponse.getTotalNumber(), actualBPResponse.getTotalNumber());

	}
	
	@Test
	public void getMDGBusinessPartnerV2Test() {
		BPResponse expectedBPResponse = new BPResponse();
		List<String> mdgCustKeyList = new ArrayList<>();
		mdgCustKeyList.add("10000266614");
		
		List<String> bpTypeList = new ArrayList<>();
		bpTypeList.add("GREATWALL-OS");
		
		List<String> bpCodeList = new ArrayList<>();
		bpCodeList.add("BBP000");
		
		List<String> bpStatusList = null;
		
		List<String> countryCodeList = new ArrayList<>();
		countryCodeList.add("CN");
		
		expectedBPResponse.setTotalNumber(5);
		BPResponse actualBPResponse = bpMdg.getMDGBusinessPartnerV2(mdgCustKeyList, bpStatusList, bpTypeList, bpCodeList, countryCodeList);
		assertEquals(expectedBPResponse.getTotalNumber(), actualBPResponse.getTotalNumber());
	}
	
	@Test
	public void getMDGBusinessPartnerV2TestWithNoData() {
		BPResponse expectedBPResponse = new BPResponse();
		List<String> mdgCustKeyList = new ArrayList<>();
		mdgCustKeyList.add("100002666122");
		
		List<String> bpTypeList = new ArrayList<>();
		bpTypeList.add("GREATWALL-OS1");
		
		List<String> bpCodeList = new ArrayList<>();
		bpCodeList.add("BBP000123");
		
		List<String> bpStatusList = null;
		
		List<String> countryCodeList = new ArrayList<>();
		countryCodeList.add("IN");
		
		expectedBPResponse.setTotalNumber(0);
		BPResponse actualBPResponse = bpMdg.getMDGBusinessPartnerV2(mdgCustKeyList, bpStatusList, bpTypeList, bpCodeList, countryCodeList);
		assertEquals(expectedBPResponse.getTotalNumber(), actualBPResponse.getTotalNumber());
	}

}
