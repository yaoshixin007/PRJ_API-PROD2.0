package com.jci.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jci.common.core.InjectableLogger;
import com.jci.common.exception.BatteryOperationBusinessException;
import com.jci.common.exception.InvalidDateFormatException;
import com.jci.common.exception.StringTooLongException;
import com.jci.service.IBatterySN;
import com.jci.service.IBatterySNOperationService;
import com.jci.service.IBusinessPartnerMDG;
import com.jci.service.ICmrlProduct;
import com.jci.service.IProductQAD;
import com.jci.transfer.BPResponse;
import com.jci.transfer.BatteryOperationRequest;
import com.jci.transfer.BatteryOperationResponse;
import com.jci.transfer.BatterySNResponse;
import com.jci.transfer.CmrlProductsResponse;
import com.jci.transfer.QADProductsResponse;
import com.jci.util.ApplicationConstants;
import com.jci.util.ApplicationUtils;


import io.swagger.annotations.Api;

@RestController
@Api(description="Operations pertaining to Data Service")
public class HomeController {

	@InjectableLogger
	private static Logger logger;

	@Autowired
	IProductQAD prdQad;
	
	@Autowired
	ICmrlProduct cmrlPrd;

	@Autowired
	IBusinessPartnerMDG iBusinessPartnerMDG;

	@Autowired
	IBatterySN iBatterySN;

	@Autowired
	ApplicationUtils applicationUtils;

	@Autowired
	@Qualifier(ApplicationConstants.BATTERY_OPERATION_SERVICE)
	IBatterySNOperationService<?> batteryOperationService;

	@GetMapping("/")
	public String defaultMethod() {
		return "index";
	}

	/**
	 * 
	 * @param mdgCustKey
	 * @return
	 * @throws StringTooLongException
	 */
	@RequestMapping(value = ApplicationConstants.RestEndPoints.MAPPING_GET_MDGBUSINESSPARTNER, method = RequestMethod.GET)
	public ResponseEntity<BPResponse> getMDGBusinessPartners(
			@RequestParam(name = "mdgCustKey", required = false) List<String> mdgCustKey,
			@RequestParam(name = "messageId", required = false) String messageId,
			@RequestParam(name = "channel", required = false) String channel)
			throws StringTooLongException, MissingServletRequestParameterException {

		logger.info("Start Business Partner MDG interface to get details");
		BPResponse bpResponse = null;

		if (mdgCustKey != null && !mdgCustKey.isEmpty()) {
			applicationUtils.validateLengthTypeList(mdgCustKey);
			bpResponse = iBusinessPartnerMDG.getMDGBusinessPartner(mdgCustKey);
		} else {
			bpResponse = iBusinessPartnerMDG.getMDGBusinessPartner();
		}

		logger.info("End of  Business Partner MDG Interface ");
		return new ResponseEntity<>(bpResponse, HttpStatus.OK);

	}

	/**
	 * Fetch either specific serial Number data of BatterySn or multiple records for
	 * given list of SerialNos data
	 * 
	 * @param serialNo
	 * @return
	 * @throws MissingServletRequestParameterException
	 * @throws StringTooLongException
	 */
	@RequestMapping(value = ApplicationConstants.RestEndPoints.MAPPING_GET_BATTERY_SN, method = RequestMethod.GET)
	public ResponseEntity<BatterySNResponse> getBatterySNDetails(
			@RequestParam(name = "serialNo", required = false) String[] serialNo,
			@RequestParam(name = "messageId", required = false) String messageId,
			@RequestParam(name = "channel", required = false) String channel)
			throws StringTooLongException, MissingServletRequestParameterException {

		logger.info("Start BatterySN interface to get Battery Serial Number details");
		BatterySNResponse batterySNResponse;

		applicationUtils.validateLengthTypeList(Arrays.asList(serialNo));
		batterySNResponse = iBatterySN.getBatterySerialNumber(serialNo);

		logger.info("End of Battery Serial Number Interface ");
		return new ResponseEntity<>(batterySNResponse, HttpStatus.OK);

	}

	/**
	 * Fetch either entire data or specific material Num data of Product QAD
	 * 
	 * @param materialNum
	 * @param messageId
	 * @param channel
	 * @return
	 * @throws MissingServletRequestParameterException
	 * @throws StringTooLongException
	 */
	@RequestMapping(value = ApplicationConstants.RestEndPoints.MAPPING_GET_QADPRODUCTS, method = RequestMethod.GET)
	public ResponseEntity<QADProductsResponse> fetchProducts(
			@RequestParam(name = "materialNum", required = false) String[] materialNum,
			@RequestParam(name = "messageId", required = false) String messageId,
			@RequestParam(name = "channel", required = false) String channel)
			throws StringTooLongException, MissingServletRequestParameterException {
		logger.info("Start QAD Product interface to fetch Products based on Material Num");

		QADProductsResponse response;
		// If num is passed in request then fetch data based on num else all data based
		// on limit.
		if (materialNum != null && materialNum.length > 0) {
			applicationUtils.validateLengthTypeList(Arrays.asList(materialNum));
			response = prdQad.getQadProducts(materialNum);
		} else {
			response = prdQad.getQadProducts();
		}
		logger.info("End of QAD Product interface");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Fetch either entire data or specific material Num data of Commercial Product
	 * 
	 * @param materialNum
	 * @param messageId
	 * @param channel
	 * @return
	 * @throws MissingServletRequestParameterException
	 * @throws StringTooLongException
	 */
	@RequestMapping(value = ApplicationConstants.RestEndPoints.MAPPING_GET_CMRL_PRODUCTS, method = RequestMethod.GET)
	public ResponseEntity<CmrlProductsResponse> fetchCmrlProducts(
			@RequestParam(name = "mtrlCmrlNbr", required = false) String[] mtrlCmrlNbr,
			@RequestParam(name = "messageId", required = false) String messageId,
			@RequestParam(name = "channel", required = false) String channel)
			throws StringTooLongException, MissingServletRequestParameterException {
		logger.info("Start Commercial Product interface to fetch Products based on Material Num");

		CmrlProductsResponse response;
		// If num is passed in request then fetch data based on num else all data based
		// on limit.
		if (mtrlCmrlNbr != null && mtrlCmrlNbr.length > 0) {
			applicationUtils.validateLengthTypeList(Arrays.asList(mtrlCmrlNbr));
			response = cmrlPrd.getCmrlProducts(mtrlCmrlNbr);
		} else {
			response = cmrlPrd.getCmrlProducts();
		}
		logger.info("End of Commercial Product interface");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	
	@RequestMapping(value = ApplicationConstants.RestEndPoints.MAPPING_BATTERY_SN_OPERATION, method = RequestMethod.GET)
	public ResponseEntity<BatteryOperationResponse> fetchBatterySNOperation(
			@RequestParam(name = ApplicationConstants.SERIAL_NO, required = false) String serialNo,
			@RequestParam(name = "messageId", required = false) String messageId,
			@RequestParam(name = "channel", required = false) String channel)
			throws StringTooLongException, MissingServletRequestParameterException {
		logger.info("Start GET-Battery SN Operation interface");

		applicationUtils.validateLengthTypeString(serialNo);
		BatteryOperationResponse response = (BatteryOperationResponse) batteryOperationService
				.fetchBatterySNOperation(serialNo);

		logger.info("End GET-Battery SN Operation interface");

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = ApplicationConstants.RestEndPoints.MAPPING_BATTERY_SN_OPERATION, method = RequestMethod.POST)
	public ResponseEntity<Object> createBatterySNOperation(@RequestBody BatteryOperationRequest batteryOperationRequest)
			throws BatteryOperationBusinessException, MissingServletRequestParameterException {
		logger.info("Start POST-Battery SN Operation interface");

		Integer location = (Integer) batteryOperationService.createBatterySNOperation(batteryOperationRequest);
		logger.info("End POST-Battery SN Operation interface");

		return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.TEXT_PLAIN)
				.body("Successful processed with OperationId :" + location);

	}

	@RequestMapping(value = ApplicationConstants.RestEndPoints.MAPPING_BATTERY_SN_OPERATION, method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteBatterySNOperation(@RequestBody BatteryOperationRequest batteryOperationRequest)
			throws MissingServletRequestParameterException {
		logger.info("Start DELETE-Battery SN Operation interface");

		batteryOperationService.deleteBatterySNOperation(batteryOperationRequest);

		logger.info("End DELETE-Battery SN Operation interface");

		return ResponseEntity.ok("Delete operation successfully processed");
	}

	/**
	 * Fetch either specific serial Number/Package Serial Number data of BatterySn
	 * or multiple records for given list of SerialNos data
	 * 
	 * @param serialNo
	 * @return
	 * @throws StringTooLongException
	 */
	@RequestMapping(value = ApplicationConstants.RestEndPoints.MAPPING_GET_PACKAGE_SN, method = RequestMethod.GET)
	public ResponseEntity<BatterySNResponse> getPackageSNDetails(
			@RequestParam(name = "serialNo", required = false) String[] serialNo,
			@RequestParam(name = "messageId", required = false) String messageId,
			@RequestParam(name = "channel", required = false) String channel)
			throws StringTooLongException, MissingServletRequestParameterException {

		logger.info("Start PackageSN interface to get Package Serial Number details");
		BatterySNResponse batterySNResponse;

		applicationUtils.validateLengthTypeAndAlphnumericList(Arrays.asList(serialNo));
		batterySNResponse = iBatterySN.getPackageSerialNumber(serialNo);

		logger.info("End of Package Serial Number Interface ");
		return new ResponseEntity<>(batterySNResponse, HttpStatus.OK);

	}

	/**
	 * Fetch either specific serial Number/Package Serial Number data of BatterySn
	 * or multiple records for given list of SerialNos data
	 * 
	 * @param serialNo
	 * @return
	 * @throws StringTooLongException
	 */
	@RequestMapping(value = ApplicationConstants.RestEndPoints.MAPPING_GET_DUAL_SN, method = RequestMethod.GET)
	public ResponseEntity<BatterySNResponse> getDualSNDetails(
			@RequestParam(name = "serialNo", required = false) String serialNo,
			@RequestParam(name = "messageId", required = false) String messageId,
			@RequestParam(name = "channel", required = false) String channel)
			throws StringTooLongException, MissingServletRequestParameterException {

		logger.info("Start DualSN interface to get Dual Serial Number details");
		BatterySNResponse batterySNResponse;

		String[] serialNoArray = new String[1];
		serialNoArray[0] = serialNo;

		applicationUtils.validateLengthTypeAndAlphnumericList(Arrays.asList(serialNoArray));
		batterySNResponse = iBatterySN.getDualSerialNumber(serialNoArray);

		logger.info("End of Dual Serial Number Interface ");
		return new ResponseEntity<>(batterySNResponse, HttpStatus.OK);

	}

	/**
	 * 
	 * @param mdgCustKey
	 * @return
	 * @throws StringTooLongException
	 */
	@RequestMapping(value = ApplicationConstants.RestEndPoints.MAPPING_GET_MDGBUSINESSPARTNER_V2, method = RequestMethod.GET)
	public ResponseEntity<BPResponse> getMDGBusinessPartnersV2(
			@RequestParam(name = "mdgCustKey", required = false) List<String> mdgCustKey,
			@RequestParam(name = "messageId", required = false) String messageId,
			@RequestParam(name = "channel", required = false) String channel,
			@RequestParam(name = "bpStatus", required = false) List<String> bpStatus,
			@RequestParam(name = "bpType", required = false) List<String> bpType,
			@RequestParam(name = "bpCode", required = false) List<String> bpCode,
			@RequestParam(name = "countryCode", required = false) List<String> countryCode)
			throws MissingServletRequestParameterException, StringTooLongException {

		logger.info("Start V2 Business Partner MDG interface to get details with filters");
		BPResponse bpResponse = null;

		if (mdgCustKey != null && !mdgCustKey.isEmpty()) {
			applicationUtils.validateLengthTypeList(mdgCustKey);
		}
		bpResponse = iBusinessPartnerMDG.getMDGBusinessPartnerV2(mdgCustKey, bpStatus, bpType, bpCode, countryCode);

		logger.info("End of V2 Business Partner MDG Interface ");
		return new ResponseEntity<>(bpResponse, HttpStatus.OK);

	}

	/**
	 * Fetch either entire data or specific material Num data of Product QAD
	 * 
	 * @param materialNum
	 * @param messageId
	 * @param channel
	 * @return
	 * @throws MissingServletRequestParameterException
	 * @throws StringTooLongException
	 * @throws ParseException
	 * @throws InvalidDataException
	 */
	@RequestMapping(value = ApplicationConstants.RestEndPoints.MAPPING_GET_QADPRODUCTS_V2, method = RequestMethod.GET)
	public ResponseEntity<QADProductsResponse> fetchProductsV2(
			@RequestParam(name = "materialNum", required = false) String[] materialNum,
			@RequestParam(name = "messageId", required = false) String messageId,
			@RequestParam(name = "channel", required = false) String channel,
			@RequestParam(name = "plmSkuStatusCode", required = false) String[] plmSkuStatusCode,
			@RequestParam(name = "qadSkuStatus", required = false) String[] qadSkuStatus,
			@RequestParam(name = "productPromoCodeCn", required = false) String[] productPromoCodeCn,
			@RequestParam(name = "productLegacyBrandCode", required = false) String[] productLegacyBrandCode,
			@RequestParam(name = "groupSizeCode", required = false) String[] groupSizeCode,
			@RequestParam(name = "materialQadNumber", required = false) String[] materialQadNumber,
			@RequestParam(name = "materialCommercialItem", required = false) String[] materialCommercialItem,
			@RequestParam(name = "addDateFrom", required = false) String addDateFrom,
			@RequestParam(name = "addDateTo", required = false) String addDateTo,
			@RequestParam(name = "expiredDateFrom", required = false) String expiredDateFrom,
			@RequestParam(name = "expiredDateTo", required = false) String expiredDateTo,
			@RequestParam(name = "activeDateFrom", required = false) String activeDateFrom,
			@RequestParam(name = "activeDateTo", required = false) String activeDateTo,
			@RequestParam(name = "lastModifiedFrom", required = false) String lastModifiedFrom,
			@RequestParam(name = "lastModifiedTo", required = false) String lastModifiedTo)
			throws StringTooLongException, MissingServletRequestParameterException, InvalidDateFormatException {
		logger.info("Start QAD Product interface to fetch Products based on Material Num");

		QADProductsResponse response;
		// If num is passed in request then fetch data based on num else all data based
		// on limit.
		if (materialNum != null && materialNum.length > 0) {
			applicationUtils.validateLengthTypeList(Arrays.asList(materialNum));
		}

		List<String> dateFieldsList = new ArrayList<>();
		dateFieldsList.add(addDateFrom);
		dateFieldsList.add(addDateTo);
		dateFieldsList.add(expiredDateFrom);
		dateFieldsList.add(expiredDateTo);
		dateFieldsList.add(activeDateFrom);
		dateFieldsList.add(activeDateTo);
		dateFieldsList.add(lastModifiedFrom);
		dateFieldsList.add(lastModifiedTo);

		ApplicationUtils.validateDateField(dateFieldsList);

		response = prdQad.getQadProductsV2(materialNum, plmSkuStatusCode, qadSkuStatus, productPromoCodeCn,
				productLegacyBrandCode, groupSizeCode, materialQadNumber, materialCommercialItem, addDateFrom, addDateTo, expiredDateFrom,
				expiredDateTo, activeDateFrom, activeDateTo, lastModifiedFrom, lastModifiedTo);

		logger.info("End of QAD Product interface");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
