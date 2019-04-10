/**
 * 
 */
package com.jci.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jci.common.core.InjectableLogger;
import com.jci.common.exception.InvalidDateFormatException;
import com.jci.common.exception.StringTooLongException;
import com.jci.service.impl.BatterySNOperationServiceImpl;
import com.jci.service.impl.BatterySNService;
import com.jci.service.impl.BusinessPartnerMDGService;
import com.jci.service.impl.CmrlProductService;
import com.jci.service.impl.ProductQADService;
import com.jci.test.CHLPApplicationTests;
import com.jci.transfer.BPResponse;
import com.jci.transfer.BPSDetails;
import com.jci.transfer.BatteryOperationDetails;
import com.jci.transfer.BatteryOperationRequest;
import com.jci.transfer.BatteryOperationResponse;
import com.jci.transfer.BatterySNResponse;
import com.jci.transfer.CmrlProductsResponse;
import com.jci.transfer.QADProductsResponse;
import com.jci.util.ApplicationConstants;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author apiadmin2
 *
 */
@WebAppConfiguration
public class HomeControllerTest extends CHLPApplicationTests {

	private MockMvc mockMvc;

	@MockBean
	private ProductQADService prdQad;

	@MockBean
	private CmrlProductService cmrlPrd;

	@MockBean
	private BusinessPartnerMDGService bpService;

	@MockBean
	private BatterySNService batterySn;

	@MockBean
	private BatterySNOperationServiceImpl batterySnOper;

	@InjectableLogger
	private static Logger logger;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void fetchProducts() {
		QADProductsResponse response = new QADProductsResponse();
		response.setTotalNumber(1);
		Mockito.when(prdQad.getQadProducts()).thenReturn(response);
		try {
			mockMvc.perform(
					get(ApplicationConstants.RestEndPoints.MAPPING_GET_QADPRODUCTS + "?messageId=111&channel=OMS"))
					.andExpect(status().isOk()).andExpect(jsonPath("$.totalNumber").value(1));
		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}
	}

	@Test
	public void fetchProductsBasedOnNum() {
		QADProductsResponse response = new QADProductsResponse();
		response.setTotalNumber(1);
		String[] materialNums = { "1235" };
		Mockito.when(prdQad.getQadProducts(materialNums)).thenReturn(response);
		try {
			mockMvc.perform(get(ApplicationConstants.RestEndPoints.MAPPING_GET_QADPRODUCTS
					+ "?materialNum=1235&messageId=111&channel=123")).andExpect(status().isOk())
					.andExpect(jsonPath("$.totalNumber").value(1));
		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}
	}

	@Test
	public void failMessageIdInProductQAD() {
		try {
			mockMvc.perform(get(ApplicationConstants.RestEndPoints.MAPPING_GET_QADPRODUCTS + "?channel=OMS"))
					.andExpect(status().is(400));
		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}
	}

	@Test
	public void failChannelInProductQAD() {
		try {
			mockMvc.perform(get(ApplicationConstants.RestEndPoints.MAPPING_GET_QADPRODUCTS + "?messageId=1234"))
					.andExpect(status().is(400));
		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}
	}

	// ProductCommercial Controller
	@Test
	public void fetchCmrlProducts() {
		CmrlProductsResponse response = new CmrlProductsResponse();
		response.setTotalNumber(1);
		Mockito.when(cmrlPrd.getCmrlProducts()).thenReturn(response);
		try {
			mockMvc.perform(
					get(ApplicationConstants.RestEndPoints.MAPPING_GET_CMRL_PRODUCTS + "?messageId=111&channel=OMS"))
					.andExpect(status().isOk()).andExpect(jsonPath("$.totalNumber").value(1));
		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}
	}

	@Test
	public void fetchCmrlProductsBasedOnCmrlNum() {
		CmrlProductsResponse response = new CmrlProductsResponse();
		response.setTotalNumber(1);
		String[] cmrlPrds = { "12" };
		Mockito.when(cmrlPrd.getCmrlProducts(cmrlPrds)).thenReturn(response);
		try {
			mockMvc.perform(get(ApplicationConstants.RestEndPoints.MAPPING_GET_CMRL_PRODUCTS
					+ "?mtrlCmrlNbr=12&messageId=111&channel=123")).andExpect(status().isOk())
					.andExpect(jsonPath("$.totalNumber").value(1));
		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}
	}

	@Test
	public void failMessageIdInCmrlPrd() {
		try {
			mockMvc.perform(get(ApplicationConstants.RestEndPoints.MAPPING_GET_CMRL_PRODUCTS + "?channel=OMS"))
					.andExpect(status().is(400));
		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}
	}

	@Test
	public void failChannelInCmrlPrd() {
		try {
			mockMvc.perform(get(ApplicationConstants.RestEndPoints.MAPPING_GET_CMRL_PRODUCTS + "?messageId=1234"))
					.andExpect(status().is(400));
		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}
	}

	// MDGBusinessPartner
	@Test
	public void getMDGBusinessPartnersTest() {

		List<BPSDetails> bpsList = new ArrayList<>();
		BPSDetails bpsDetails = new BPSDetails();
		bpsDetails.setMdgCustKey("10000266614");
		bpsList.add(bpsDetails);

		bpsDetails = new BPSDetails();
		bpsDetails.setMdgCustKey("10000266615");
		bpsList.add(bpsDetails);

		BPResponse bpResponse = new BPResponse();
		bpResponse.setTotalNumber(2);
		bpResponse.setBpsList(bpsList);

		Mockito.when(bpService.getMDGBusinessPartner(Mockito.anyListOf(String.class))).thenReturn(bpResponse);
		try {
			mockMvc.perform(get(ApplicationConstants.RestEndPoints.MAPPING_GET_MDGBUSINESSPARTNER
					+ "?mdgCustKey=10000266614,10000266615&messageId=123&channel=ORM")).andExpect(status().isOk())
					.andExpect(jsonPath("$.totalNumber").value(2));
		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}

	}

	@Test
	public void getMDGBusinessPartnersAllRecordsTest() {

		List<BPSDetails> bpsList = new ArrayList<>();
		BPSDetails bpsDetails = new BPSDetails();
		bpsDetails.setMdgCustKey("10000266614");
		bpsList.add(bpsDetails);

		BPResponse bpResponse = new BPResponse();
		bpResponse.setTotalNumber(1);
		bpResponse.setBpsList(bpsList);

		Mockito.when(bpService.getMDGBusinessPartner(Mockito.anyListOf(String.class))).thenReturn(bpResponse);
		try {
			mockMvc.perform(get(
					ApplicationConstants.RestEndPoints.MAPPING_GET_MDGBUSINESSPARTNER + "?messageId=123&channel=ORM"))
					.andExpect(status().isOk());
		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}

	}

	// BatterySN
	@Test
	public void getBatterySNDetailsTest() {
		BatterySNResponse batterySNResponse = new BatterySNResponse();
		batterySNResponse.setTotalNumber(1);

		Mockito.when(batterySn.getBatterySerialNumber(Mockito.any())).thenReturn(batterySNResponse);
		try {
			mockMvc.perform(get(ApplicationConstants.RestEndPoints.MAPPING_GET_BATTERY_SN
					+ "?serialNo=7005191587980114,66666667&messageId=12&channel=OMS")).andExpect(status().isOk())
					.andExpect(jsonPath("$.totalNumber").value(1));
		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}
	}

	@Test
	public void getBatterySNDetailsWithInvalidSerialNumTest() {
		BatterySNResponse batterySNResponse = new BatterySNResponse();
		batterySNResponse.setTotalNumber(1);

		Mockito.when(batterySn.getBatterySerialNumber(Mockito.any())).thenReturn(batterySNResponse);
		try {
			mockMvc.perform(get(ApplicationConstants.RestEndPoints.MAPPING_GET_BATTERY_SN
					+ "?serialNo=aed676ff66&messageId=12&channel=OMS")).andExpect(status().is(400));
		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}
	}

	@Test
	public void getBatterySNDetailsWithTooLongSerialNumTest() {
		BatterySNResponse batterySNResponse = new BatterySNResponse();
		batterySNResponse.setTotalNumber(1);

		Mockito.when(batterySn.getBatterySerialNumber(Mockito.any())).thenReturn(batterySNResponse);
		try {
			mockMvc.perform(get(ApplicationConstants.RestEndPoints.MAPPING_GET_BATTERY_SN
					+ "?serialNo=45546543353535367642578&messageId=12&channel=OMS")).andExpect(status().is(400));
		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}
	}

	@Test
	public void failSerialNoInBatterySN() {
		try {
			mockMvc.perform(
					get(ApplicationConstants.RestEndPoints.MAPPING_GET_BATTERY_SN + "?channel=OMS&messageId=111"))
					.andExpect(status().is(400));
		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}
	}

	@Test
	public void failMessageIdInBatterySN() {
		try {
			mockMvc.perform(get(ApplicationConstants.RestEndPoints.MAPPING_GET_BATTERY_SN
					+ "?serialNo=7005191587980114&channel=OMS")).andExpect(status().is(400));
		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}
	}

	@Test
	public void failChannelInBatterySN() {
		try {
			mockMvc.perform(get(ApplicationConstants.RestEndPoints.MAPPING_GET_BATTERY_SN
					+ "?serialNo=7005191587980114&&messageId=12")).andExpect(status().is(400));
		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}
	}

	@Test
	public void fetchBatterySNOperation() {
		String serialNo = "123";
		List<BatteryOperationDetails> batteryOperationDetailsList = new ArrayList<>();
		BatteryOperationResponse response = new BatteryOperationResponse();
		BatteryOperationDetails operationDetails = new BatteryOperationDetails();
		operationDetails.setOperationId(11);
		operationDetails.setSerialNo(serialNo);

		batteryOperationDetailsList.add(operationDetails);
		response.setBatteryOperationResponse(batteryOperationDetailsList);

		Mockito.when(batterySnOper.fetchBatterySNOperation(Mockito.anyObject())).thenReturn(response);
		try {
			mockMvc.perform(get(ApplicationConstants.RestEndPoints.MAPPING_BATTERY_SN_OPERATION
					+ "?serialNo=123&messageId=1223&channel=ORM")).andExpect(status().isOk());
		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}
	}

	@Test
	public void createBatterySNOperation() {
		BatteryOperationRequest batteryOperationRequest = new BatteryOperationRequest();
		batteryOperationRequest.setChannel("QAD");
		batteryOperationRequest.setMessageId("1");
		batteryOperationRequest.setOperationDest("JCI");
		batteryOperationRequest.setOperationFrom("JCI");
		batteryOperationRequest.setOperationId(21);
		batteryOperationRequest.setOperationRemark("Battery Production");
		batteryOperationRequest.setOperationTime(new Timestamp(System.currentTimeMillis()));
		batteryOperationRequest.setOperationTypeId(21);
		batteryOperationRequest.setOperator("NA");
		batteryOperationRequest.setOperatorSourceSystem("QAD");
		batteryOperationRequest.setReferenceOrderNumber("NA");
		batteryOperationRequest.setReferenceOrderType("NA");
		batteryOperationRequest.setRequestTime(new Timestamp(System.currentTimeMillis()));
		batteryOperationRequest.setSerialNo("7742960289233525");

		try {

			mockMvc.perform(MockMvcRequestBuilders.post(ApplicationConstants.RestEndPoints.MAPPING_BATTERY_SN_OPERATION)
					.contentType(MediaType.APPLICATION_JSON).content(asJsonString(batteryOperationRequest))
					.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}
	}

	@Test
	public void deleteBatterySNOperation() {
		BatteryOperationRequest batteryOperationRequest = new BatteryOperationRequest();
		batteryOperationRequest.setMessageId("1223");
		batteryOperationRequest.setOperationId(21);
		batteryOperationRequest.setChannel("ORM");

		try {

			mockMvc.perform(
					MockMvcRequestBuilders.delete(ApplicationConstants.RestEndPoints.MAPPING_BATTERY_SN_OPERATION)
							.contentType(MediaType.APPLICATION_JSON).content(asJsonString(batteryOperationRequest))
							.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());

		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}
	}

	@Test
	public void fetchBatterySNOperationWithTooLongSerialNum() {
		String serialNo = "123456789";
		List<BatteryOperationDetails> batteryOperationDetailsList = new ArrayList<>();
		BatteryOperationResponse response = new BatteryOperationResponse();
		BatteryOperationDetails operationDetails = new BatteryOperationDetails();
		operationDetails.setOperationId(11);
		operationDetails.setSerialNo(serialNo);

		batteryOperationDetailsList.add(operationDetails);
		response.setBatteryOperationResponse(batteryOperationDetailsList);

		Mockito.when(batterySnOper.fetchBatterySNOperation(Mockito.anyObject())).thenReturn(response);
		try {
			mockMvc.perform(get(ApplicationConstants.RestEndPoints.MAPPING_BATTERY_SN_OPERATION
					+ "?serialNo=123456789101112131415161718&messageId=1223&channel=ORM")).andExpect(status().is(400));
		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}
	}

	@Test
	public void fetchBatterySNOperationWithInvalidSerialNum() {
		String serialNo = "123456789";
		List<BatteryOperationDetails> batteryOperationDetailsList = new ArrayList<>();
		BatteryOperationResponse response = new BatteryOperationResponse();
		BatteryOperationDetails operationDetails = new BatteryOperationDetails();
		operationDetails.setOperationId(11);
		operationDetails.setSerialNo(serialNo);

		batteryOperationDetailsList.add(operationDetails);
		response.setBatteryOperationResponse(batteryOperationDetailsList);

		Mockito.when(batterySnOper.fetchBatterySNOperation(Mockito.anyObject())).thenReturn(response);
		try {
			mockMvc.perform(get(ApplicationConstants.RestEndPoints.MAPPING_BATTERY_SN_OPERATION
					+ "?serialNo=123456789!&messageId=1223&channel=ORM")).andExpect(status().is(400));
		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}
	}

	@Test
	public void getMDGBusinessPartnersWithTooLongMdgCustKeyTest() {

		List<BPSDetails> bpsList = new ArrayList<>();
		BPSDetails bpsDetails = new BPSDetails();
		bpsDetails.setMdgCustKey("10000266614");
		bpsList.add(bpsDetails);

		bpsDetails = new BPSDetails();
		bpsDetails.setMdgCustKey("10000266615");
		bpsList.add(bpsDetails);

		BPResponse bpResponse = new BPResponse();
		bpResponse.setTotalNumber(2);
		bpResponse.setBpsList(bpsList);

		Mockito.when(bpService.getMDGBusinessPartner(Mockito.anyListOf(String.class))).thenReturn(bpResponse);
		try {
			mockMvc.perform(get(ApplicationConstants.RestEndPoints.MAPPING_GET_MDGBUSINESSPARTNER
					+ "?mdgCustKey=100002666141000026661410000266614123,10000266615&messageId=123&channel=ORM"))
					.andExpect(status().is(400));
		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}

	}

	@Test
	public void getMDGBusinessPartnersWithInvalidMdgCustKeyTest() {

		List<BPSDetails> bpsList = new ArrayList<>();
		BPSDetails bpsDetails = new BPSDetails();
		bpsDetails.setMdgCustKey("10000266614");
		bpsList.add(bpsDetails);

		bpsDetails = new BPSDetails();
		bpsDetails.setMdgCustKey("10000266615");
		bpsList.add(bpsDetails);

		BPResponse bpResponse = new BPResponse();
		bpResponse.setTotalNumber(2);
		bpResponse.setBpsList(bpsList);

		Mockito.when(bpService.getMDGBusinessPartner(Mockito.anyListOf(String.class))).thenReturn(bpResponse);
		try {
			mockMvc.perform(get(ApplicationConstants.RestEndPoints.MAPPING_GET_MDGBUSINESSPARTNER
					+ "?mdgCustKey=10000266614!,$10000266615&messageId=123&channel=ORM")).andExpect(status().is(400));
		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}

	}

	@Test
	public void createBatterySNOperationWithMissingParameters() {
		BatteryOperationRequest batteryOperationRequest = new BatteryOperationRequest();
		batteryOperationRequest.setOperationDest("JCI");
		batteryOperationRequest.setOperationId(21);
		batteryOperationRequest.setOperationRemark("Battery Production");
		batteryOperationRequest.setOperationTime(new Timestamp(System.currentTimeMillis()));
		batteryOperationRequest.setOperationTypeId(21);
		batteryOperationRequest.setOperator("NA");
		batteryOperationRequest.setOperatorSourceSystem("QAD");
		batteryOperationRequest.setReferenceOrderNumber("NA");
		batteryOperationRequest.setReferenceOrderType("NA");
		batteryOperationRequest.setRequestTime(new Timestamp(System.currentTimeMillis()));
		batteryOperationRequest.setSerialNo("7742960289233525");

		try {

			mockMvc.perform(MockMvcRequestBuilders.post(ApplicationConstants.RestEndPoints.MAPPING_BATTERY_SN_OPERATION)
					.contentType(MediaType.APPLICATION_JSON).content(asJsonString(batteryOperationRequest))
					.accept(MediaType.APPLICATION_JSON)).andExpect(status().is(400));

		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}
	}

	@Test
	public void getPackageSNDetailsTest() {
		BatterySNResponse batterySNResponse = new BatterySNResponse();
		batterySNResponse.setTotalNumber(1);

		Mockito.when(batterySn.getPackageSerialNumber(Mockito.any())).thenReturn(batterySNResponse);
		try {
			mockMvc.perform(get(ApplicationConstants.RestEndPoints.MAPPING_GET_PACKAGE_SN
					+ "?serialNo=C003067329&messageId=111&channel=OMS")).andExpect(status().isOk())
					.andExpect(jsonPath("$.totalNumber").value(1));
		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}
	}

	@Test
	public void getPackageSNDetailsWithInvalidSerialNumTest() {
		BatterySNResponse batterySNResponse = new BatterySNResponse();
		batterySNResponse.setTotalNumber(1);

		Mockito.when(batterySn.getPackageSerialNumber(Mockito.any())).thenReturn(batterySNResponse);
		try {
			mockMvc.perform(get(ApplicationConstants.RestEndPoints.MAPPING_GET_PACKAGE_SN
					+ "?serialNo=C003067329!&messageId=111&channel=OMS")).andExpect(status().is(400));
		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}
	}

	@Test
	public void getPackageSNDetailsWithTooLongSerialNumTest() {
		BatterySNResponse batterySNResponse = new BatterySNResponse();
		batterySNResponse.setTotalNumber(1);

		Mockito.when(batterySn.getPackageSerialNumber(Mockito.any())).thenReturn(batterySNResponse);
		try {
			mockMvc.perform(get(ApplicationConstants.RestEndPoints.MAPPING_GET_PACKAGE_SN
					+ "?serialNo=C777779654325467885356768798989774433345767&messageId=12&channel=OMS"))
					.andExpect(status().is(400));
		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}
	}

	@Test
	public void getDualSNDetailsForPrdSerialNumTest() {
		BatterySNResponse batterySNResponse = new BatterySNResponse();
		batterySNResponse.setTotalNumber(1);

		Mockito.when(batterySn.getDualSerialNumber(Mockito.any())).thenReturn(batterySNResponse);
		try {
			mockMvc.perform(get(
					ApplicationConstants.RestEndPoints.MAPPING_GET_DUAL_SN + "?serialNo=1&messageId=111&channel=OMS"))
					.andExpect(status().isOk()).andExpect(jsonPath("$.totalNumber").value(1));
		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}
	}

	@Test
	public void getDualSNDetailsForPkgSerialNumTest() {
		BatterySNResponse batterySNResponse = new BatterySNResponse();
		batterySNResponse.setTotalNumber(1);

		Mockito.when(batterySn.getDualSerialNumber(Mockito.any())).thenReturn(batterySNResponse);
		try {
			mockMvc.perform(get(ApplicationConstants.RestEndPoints.MAPPING_GET_DUAL_SN
					+ "?serialNo=C003067329&messageId=111&channel=OMS")).andExpect(status().isOk())
					.andExpect(jsonPath("$.totalNumber").value(1));
		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}
	}

	@Test
	public void getDualSNDetailsWithInvalidSerialNumTest() {
		BatterySNResponse batterySNResponse = new BatterySNResponse();
		batterySNResponse.setTotalNumber(1);

		Mockito.when(batterySn.getDualSerialNumber(Mockito.any())).thenReturn(batterySNResponse);
		try {
			mockMvc.perform(get(ApplicationConstants.RestEndPoints.MAPPING_GET_DUAL_SN
					+ "?serialNo=C003067329!&messageId=111&channel=OMS")).andExpect(status().is(400));
		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}
	}

	@Test
	public void getDualSNDetailsWithTooLongSerialNumTest() {
		BatterySNResponse batterySNResponse = new BatterySNResponse();
		batterySNResponse.setTotalNumber(1);

		Mockito.when(batterySn.getDualSerialNumber(Mockito.any())).thenReturn(batterySNResponse);
		try {
			mockMvc.perform(get(ApplicationConstants.RestEndPoints.MAPPING_GET_DUAL_SN
					+ "?serialNo=71162926748490757116292674849075&messageId=111&channel=OMS"))
					.andExpect(status().is(400));
		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}
	}

	@Test
	public void getMDGBusinessPartnersV2TestWithMdgCustKeyBpTypeCountryCode() {

		List<BPSDetails> bpsList = new ArrayList<>();
		BPSDetails bpsDetails = new BPSDetails();
		bpsDetails.setMdgCustKey("10000266614");
		bpsList.add(bpsDetails);

		bpsDetails = new BPSDetails();
		bpsDetails.setMdgCustKey("10000266615");
		bpsList.add(bpsDetails);

		BPResponse bpResponse = new BPResponse();
		bpResponse.setTotalNumber(2);
		bpResponse.setBpsList(bpsList);

		Mockito.when(bpService.getMDGBusinessPartnerV2(Mockito.anyListOf(String.class), Mockito.anyListOf(String.class),
				Mockito.anyListOf(String.class), Mockito.anyListOf(String.class), Mockito.anyListOf(String.class)))
				.thenReturn(bpResponse);
		try {
			mockMvc.perform(get(ApplicationConstants.RestEndPoints.MAPPING_GET_MDGBUSINESSPARTNER_V2
					+ "?mdgCustKey=10000266614,10000266615&messageId=123&channel=ORM&bpType=BBP000&countryCode=CN"))
					.andExpect(status().isOk())
					//.andExpect(jsonPath("$.totalNumber").value(2))
					;
		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}

	}

	@Test
	public void getMDGBusinessPartnersV2TestWithBpTypeCountryCode() {

		List<BPSDetails> bpsList = new ArrayList<>();
		BPSDetails bpsDetails = new BPSDetails();
		bpsList.add(bpsDetails);

		BPResponse bpResponse = new BPResponse();
		bpResponse.setTotalNumber(5);
		bpResponse.setBpsList(bpsList);

		Mockito.when(bpService.getMDGBusinessPartnerV2(Mockito.anyListOf(String.class), Mockito.anyListOf(String.class),
				Mockito.anyListOf(String.class), Mockito.anyListOf(String.class), Mockito.anyListOf(String.class)))
				.thenReturn(bpResponse);
		try {
			mockMvc.perform(get(ApplicationConstants.RestEndPoints.MAPPING_GET_MDGBUSINESSPARTNER_V2
					+ "?messageId=123&channel=ORM&bpCode=OPXMCS&bpType=BBP000&countryCode=CN"))
					.andExpect(status().isOk())
					//.andExpect(jsonPath("$.totalNumber").value(5))
					;
		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}

	}

	@Test
	public void getMDGBusinessPartnersV2TestWithBpType() {

		List<BPSDetails> bpsList = new ArrayList<>();
		BPSDetails bpsDetails = new BPSDetails();
		bpsList.add(bpsDetails);

		BPResponse bpResponse = new BPResponse();
		bpResponse.setTotalNumber(1);
		bpResponse.setBpsList(bpsList);

		Mockito.when(bpService.getMDGBusinessPartnerV2(Mockito.anyListOf(String.class), Mockito.anyListOf(String.class),
				Mockito.anyListOf(String.class), Mockito.anyListOf(String.class), Mockito.anyListOf(String.class)))
				.thenReturn(bpResponse);
		try {
			mockMvc.perform(get(ApplicationConstants.RestEndPoints.MAPPING_GET_MDGBUSINESSPARTNER_V2
					+ "?messageId=123&channel=ORM&bpCode=OPXMCS&bpType=BBP000")).andExpect(status().isOk())
					//.andExpect(jsonPath("$.totalNumber").value(1))
					;
		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}

	}

	@Test
	public void getMDGBusinessPartnersV2TestWithCountryCode() {

		List<BPSDetails> bpsList = new ArrayList<>();
		BPSDetails bpsDetails = new BPSDetails();
		bpsList.add(bpsDetails);

		BPResponse bpResponse = new BPResponse();
		bpResponse.setTotalNumber(5);
		bpResponse.setBpsList(bpsList);

		Mockito.when(bpService.getMDGBusinessPartnerV2(Mockito.anyListOf(String.class), Mockito.anyListOf(String.class),
				Mockito.anyListOf(String.class), Mockito.anyListOf(String.class), Mockito.anyListOf(String.class)))
				.thenReturn(bpResponse);
		try {
			mockMvc.perform(get(ApplicationConstants.RestEndPoints.MAPPING_GET_MDGBUSINESSPARTNER_V2
					+ "?messageId=123&channel=ORM&bpCode=OPXMCS&countryCode=CN")).andExpect(status().isOk())
					//.andExpect(jsonPath("$.totalNumber").value(5))
					;
		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}

	}

	@Test
	public void getMDGBusinessPartnersV2TestWithBpStatus() {

		List<BPSDetails> bpsList = new ArrayList<>();
		BPSDetails bpsDetails = new BPSDetails();
		bpsList.add(bpsDetails);

		BPResponse bpResponse = new BPResponse();
		bpResponse.setTotalNumber(2);
		bpResponse.setBpsList(bpsList);

		Mockito.when(bpService.getMDGBusinessPartnerV2(Mockito.anyListOf(String.class), Mockito.anyListOf(String.class),
				Mockito.anyListOf(String.class), Mockito.anyListOf(String.class), Mockito.anyListOf(String.class)))
				.thenReturn(bpResponse);
		try {
			mockMvc.perform(get(ApplicationConstants.RestEndPoints.MAPPING_GET_MDGBUSINESSPARTNER_V2
					+ "?messageId=123&channel=ORM&bpCode=OPXMCS&bpStatus=X")).andExpect(status().isOk())
					//.andExpect(jsonPath("$.totalNumber").value(2))
					;
		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}

	}

	@Test
	public void getMDGBusinessPartnersV2TestWithAllFilters() {

		List<BPSDetails> bpsList = new ArrayList<>();
		BPSDetails bpsDetails = new BPSDetails();
		bpsList.add(bpsDetails);

		BPResponse bpResponse = new BPResponse();
		bpResponse.setTotalNumber(2);
		bpResponse.setBpsList(bpsList);

		Mockito.when(bpService.getMDGBusinessPartnerV2(Mockito.anyListOf(String.class), Mockito.anyListOf(String.class),
				Mockito.anyListOf(String.class), Mockito.anyListOf(String.class), Mockito.anyListOf(String.class)))
				.thenReturn(bpResponse);
		try {
			mockMvc.perform(get(ApplicationConstants.RestEndPoints.MAPPING_GET_MDGBUSINESSPARTNER_V2
					+ "?messageId=123&channel=ORM&mdgCustKey=10000266614&bpCode=OPXMCS&bpType=BBP000&bpStatus=X&countryCode=CN"))
					.andExpect(status().isOk()).andExpect(jsonPath("$.totalNumber").value(2));
		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}

	}

	@Test
	public void failChannelInMDGBusinessPartnersV2Test() {
		try {
			mockMvc.perform(get(ApplicationConstants.RestEndPoints.MAPPING_GET_MDGBUSINESSPARTNER_V2 + "?channel=ORM"))
					.andExpect(status().is(400));
		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}

	}

	@Test
	public void failMessageIdInMDGBusinessPartnersV2Test() {
		try {
			mockMvc.perform(
					get(ApplicationConstants.RestEndPoints.MAPPING_GET_MDGBUSINESSPARTNER_V2 + "?messageId=123"))
					.andExpect(status().is(400));
		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}

	}

	@Test
	public void fetchProductsV2Test() throws InvalidDateFormatException, StringTooLongException {
		QADProductsResponse response = new QADProductsResponse();
		response.setTotalNumber(1);
		Mockito.when(prdQad.getQadProductsV2(Mockito.any(String[].class), Mockito.any(String[].class),
				Mockito.any(String[].class), Mockito.any(String[].class), Mockito.any(String[].class),
				Mockito.any(String[].class), Mockito.any(String[].class), Mockito.any(String[].class),
				Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
				Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
				Mockito.any(String.class), Mockito.any(String.class))).thenReturn(response);
		try {
			mockMvc.perform(get(ApplicationConstants.RestEndPoints.MAPPING_GET_QADPRODUCTS_V2
					+ "?messageId=123&channel=ORM&lastModifiedFrom=20181022&lastModifiedTo=20181231&addDateFrom=20181022&qadSkuStatus=Active"))
					.andExpect(status().isOk())
					//.andExpect(jsonPath("$.totalNumber").value(1))
					;
		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}
	}

	@Test
	public void fetchProductsV2TestWithInvalidMonthDate() throws InvalidDateFormatException, StringTooLongException {
		QADProductsResponse response = new QADProductsResponse();
		response.setTotalNumber(1);
		Mockito.when(prdQad.getQadProductsV2(Mockito.any(String[].class), Mockito.any(String[].class),
				Mockito.any(String[].class), Mockito.any(String[].class), Mockito.any(String[].class),
				Mockito.any(String[].class), Mockito.any(String[].class), Mockito.any(String[].class),
				Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
				Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
				Mockito.any(String.class), Mockito.any(String.class))).thenReturn(response);
		try {
			mockMvc.perform(get(ApplicationConstants.RestEndPoints.MAPPING_GET_QADPRODUCTS_V2
					+ "?messageId=123&channel=ORM&lastModifiedFrom=20188822&lastModifiedTo=20181231&addDateFrom=20181022&qadSkuStatus=Active"))
					.andExpect(status().is(400));
		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}
	}

	@Test
	public void fetchProductsV2TestWithInvalidDateLength() throws InvalidDateFormatException, StringTooLongException {
		QADProductsResponse response = new QADProductsResponse();
		response.setTotalNumber(1);
		Mockito.when(prdQad.getQadProductsV2(Mockito.any(String[].class), Mockito.any(String[].class),
				Mockito.any(String[].class), Mockito.any(String[].class), Mockito.any(String[].class),
				Mockito.any(String[].class), Mockito.any(String[].class), Mockito.any(String[].class),
				Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
				Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
				Mockito.any(String.class), Mockito.any(String.class))).thenReturn(response);
		try {
			mockMvc.perform(get(ApplicationConstants.RestEndPoints.MAPPING_GET_QADPRODUCTS_V2
					+ "?messageId=123&channel=ORM&lastModifiedFrom=201811122&lastModifiedTo=20181231&addDateFrom=20181022&qadSkuStatus=Active"))
					.andExpect(status().is(400));
		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}
	}

	@Test
	public void fetchProductsV2TestWithInvalidDate() throws InvalidDateFormatException, StringTooLongException {
		QADProductsResponse response = new QADProductsResponse();
		response.setTotalNumber(1);
		Mockito.when(prdQad.getQadProductsV2(Mockito.any(String[].class), Mockito.any(String[].class),
				Mockito.any(String[].class), Mockito.any(String[].class), Mockito.any(String[].class),
				Mockito.any(String[].class), Mockito.any(String[].class), Mockito.any(String[].class),
				Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
				Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
				Mockito.any(String.class), Mockito.any(String.class))).thenReturn(response);
		try {
			mockMvc.perform(get(ApplicationConstants.RestEndPoints.MAPPING_GET_QADPRODUCTS_V2
					+ "?messageId=123&channel=ORM&lastModifiedFrom=20181R22&lastModifiedTo=20181231&addDateFrom=20181022&qadSkuStatus=Active"))
					.andExpect(status().is(400));
		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}
	}

	@Test
	public void fetchProductsV2TestWithOnlyDateFilters() throws InvalidDateFormatException, StringTooLongException {
		QADProductsResponse response = new QADProductsResponse();
		response.setTotalNumber(1);
		Mockito.when(prdQad.getQadProductsV2(Mockito.any(String[].class), Mockito.any(String[].class),
				Mockito.any(String[].class), Mockito.any(String[].class), Mockito.any(String[].class),
				Mockito.any(String[].class), Mockito.any(String[].class), Mockito.any(String[].class),
				Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
				Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
				Mockito.any(String.class), Mockito.any(String.class))).thenReturn(response);
		try {
			mockMvc.perform(get(ApplicationConstants.RestEndPoints.MAPPING_GET_QADPRODUCTS_V2
					+ "?messageId=123&channel=ORM&lastModifiedFrom=20181022&lastModifiedTo=20181231&addDateFrom=20181022"))
					.andExpect(status().isOk())
				//	.andExpect(jsonPath("$.totalNumber",is(1)))
					;
			logger.error("HomeController Test Exception occured ");

		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}
	}

	@Test
	public void fetchProductsV2TestWithAddDateFromAndToFilters() throws InvalidDateFormatException, StringTooLongException {
		QADProductsResponse response = new QADProductsResponse();
		response.setTotalNumber(2);
		Mockito.when(prdQad.getQadProductsV2(Mockito.any(String[].class), Mockito.any(String[].class),
				Mockito.any(String[].class), Mockito.any(String[].class), Mockito.any(String[].class),
				Mockito.any(String[].class), Mockito.any(String[].class), Mockito.any(String[].class),
				Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
				Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
				Mockito.any(String.class), Mockito.any(String.class))).thenReturn(response);
		try {
			mockMvc.perform(get(ApplicationConstants.RestEndPoints.MAPPING_GET_QADPRODUCTS_V2
					+ "?messageId=123&channel=ORM&addDateFrom=20181022&addDateTo=20181030"))
					.andExpect(status().isOk())
					//.andExpect(jsonPath("$.totalNumber").value(2))
					;
		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}
	}
	
	@Test
	public void fetchProductsV2TestWithoutFilters() throws InvalidDateFormatException, StringTooLongException {
		QADProductsResponse response = new QADProductsResponse();
		response.setTotalNumber(2);
		Mockito.when(prdQad.getQadProductsV2(Mockito.any(String[].class), Mockito.any(String[].class),
				Mockito.any(String[].class), Mockito.any(String[].class), Mockito.any(String[].class),
				Mockito.any(String[].class), Mockito.any(String[].class), Mockito.any(String[].class),
				Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
				Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
				Mockito.any(String.class), Mockito.any(String.class))).thenReturn(response);
		try {
			mockMvc.perform(
					get(ApplicationConstants.RestEndPoints.MAPPING_GET_QADPRODUCTS_V2 + "?messageId=123&channel=ORM"))
					.andExpect(status().isOk())
					//.andExpect(jsonPath("$.totalNumber").value(2))
					;
		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}
	}

	@Test
	public void fetchProductsV2TestWithMaterialNumFilters() throws InvalidDateFormatException, StringTooLongException {
		QADProductsResponse response = new QADProductsResponse();
		response.setTotalNumber(1);
		Mockito.when(prdQad.getQadProductsV2(Mockito.any(String[].class), Mockito.any(String[].class),
				Mockito.any(String[].class), Mockito.any(String[].class), Mockito.any(String[].class),
				Mockito.any(String[].class), Mockito.any(String[].class), Mockito.any(String[].class),
				Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
				Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
				Mockito.any(String.class), Mockito.any(String.class))).thenReturn(response);
		try {
			mockMvc.perform(get(ApplicationConstants.RestEndPoints.MAPPING_GET_QADPRODUCTS_V2
					+ "?messageId=123&channel=ORM&materialNum=1235")).andExpect(status().isOk())
					//.andExpect(jsonPath("$.totalNumber").value(1))
					;
		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}
	}
	
	@Test
	public void fetchProductsV2TestWithFewFilters() throws InvalidDateFormatException, StringTooLongException {
		QADProductsResponse response = new QADProductsResponse();
		response.setTotalNumber(2);
		Mockito.when(prdQad.getQadProductsV2(Mockito.any(String[].class), Mockito.any(String[].class),
				Mockito.any(String[].class), Mockito.any(String[].class), Mockito.any(String[].class),
				Mockito.any(String[].class), Mockito.any(String[].class), Mockito.any(String[].class),
				Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
				Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
				Mockito.any(String.class), Mockito.any(String.class))).thenReturn(response);
		try {
			mockMvc.perform(get(ApplicationConstants.RestEndPoints.MAPPING_GET_QADPRODUCTS_V2
					+ "?messageId=123&channel=ORM&plmSkuStatusCode=80&groupSizeCode=G27")).andExpect(status().isOk())
					//.andExpect(jsonPath("$.totalNumber").value(2))
					;
		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}
	}

	@Test
	public void failByMessageIdInFetchProductsV2TestWithFilters()
			throws InvalidDateFormatException, StringTooLongException {

		try {
			mockMvc.perform(get(ApplicationConstants.RestEndPoints.MAPPING_GET_QADPRODUCTS_V2
					+ "?channel=ORM&lastModifiedFrom=20181022&lastModifiedTo=20181231&addDateFrom=20181022"))
					.andExpect(status().is(400));
		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}
	}

	@Test
	public void failByChannelInFetchProductsV2TestWithFilters()
			throws InvalidDateFormatException, StringTooLongException {

		try {
			mockMvc.perform(get(ApplicationConstants.RestEndPoints.MAPPING_GET_QADPRODUCTS_V2
					+ "?messageId=123&lastModifiedFrom=20181022&lastModifiedTo=20181231&addDateFrom=20181022"))
					.andExpect(status().is(400));
		} catch (Exception e) {
			logger.error("HomeController Test Exception occured " + e.getMessage());
		}
	}

	public String asJsonString(final Object obj) {

		logger.debug("Beginning of HomeControllerTest.asJsonString()");

		String jsonString = null;
		try {

			jsonString = new ObjectMapper().writeValueAsString(obj);

		} catch (JsonProcessingException e) {
			logger.error("Exception Occured inside  of HomeControllerTest.asJsonString()");
		}

		logger.debug("End of HomeControllerTest.asJsonString()");
		return jsonString;

	}
}
