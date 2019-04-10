/**
 * 
 */
package com.jci.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.jci.common.core.InjectableLogger;
import com.jci.domain.MtrlSnDim;
import com.jci.repository.BatterySNRepository;
import com.jci.service.IBatterySN;
import com.jci.transfer.BatterySN;
import com.jci.transfer.BatterySNResponse;
import com.jci.util.ApplicationConstants;

/**
 * @author apiadmin2
 *
 */
@Service
public class BatterySNService implements IBatterySN{
	
	@InjectableLogger
	private static Logger logger;
	
	@Autowired
	BatterySNRepository batterySNRepository;

	@Override
	public BatterySNResponse getBatterySerialNumber(String[] serialNo) {
		long startTime = System.currentTimeMillis();
		logger.debug("Start Service to get Battery Serial Number Line details");
		logger.debug("Getting Serial Number line details");
		
		List<MtrlSnDim> batterySNList = batterySNRepository.findBySerialNoIn(serialNo);
		logger.debug("Data retrieved from db. No of records: {} and time taken  to retrieve data : {}", batterySNList.size(), (System.currentTimeMillis() - startTime));
		BatterySNResponse response = mappingEntityToJaxb(batterySNList);
		logger.debug("End Battery Serial Number Service");
		return response;
		
	}
	
	@Override
	public BatterySNResponse getPackageSerialNumber(String[] serialNo) {
		
		logger.debug("Start Service to get Package Serial Number Line details");
		logger.debug("Getting Package Serial Number line details");
		
		List<MtrlSnDim> packageSNList = batterySNRepository.findByPackageSerialNoIn(serialNo);
		logger.debug("Data retrieved from db for PackageSN. No of records: {}", packageSNList.size());
		BatterySNResponse response = mappingEntityToJaxb(packageSNList);
		logger.debug("End of Package Serial Number Service");
		return response;
		
	}
	
	@Override
	public BatterySNResponse getDualSerialNumber(String[] serialNo) {
		
		logger.debug("Start Service to get Dual Serial Number Line details");
		logger.debug("Getting Dual Serial Number line details");
		
		List<MtrlSnDim> dualSNList = batterySNRepository.findBySerialNoInOrPackageSerialNoIn(serialNo, serialNo);
		logger.debug("Data retrieved from db for DualSN. No of records: {}", dualSNList.size());
		BatterySNResponse response = mappingEntityToJaxb(dualSNList);
		logger.debug("End of Dual Serial Number Service");
		return response;
		
	}

	private BatterySNResponse mappingEntityToJaxb(List<MtrlSnDim> batterySerialNos) {
		logger.debug("Mapping Entity to POJO ");
		BatterySNResponse batterySNResponse = new BatterySNResponse();
		List<BatterySN> batterySNList = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat(ApplicationConstants.DATE_FORMAT_YYYY_MM_DD);
		if(! CollectionUtils.isEmpty(batterySerialNos) ) {
			batterySerialNos.forEach(entity -> {
				BatterySN batterySN = new BatterySN();
				batterySN.setSerialId(entity.getSerialId());
				batterySN.setSerialNo(entity.getSerialNo());
				batterySN.setLastOperationChannel(entity.getLastOperationChannel());
				batterySN.setLastOperationFrom(entity.getLastOperationFrom());
				batterySN.setLastOperationRemark(entity.getLastOperationRemark());
				//if(entity.getLastOprtnTstmp() != null) {
					batterySN.setLastOperationTime(entity.getLastOprtnTstmp());
					//batterySN.setLastOperationTime(sdf.format(entity.getLastOprtnTstmp().getTime()));
				//}
				batterySN.setLastOperationTo(entity.getLastOperationDest());
				batterySN.setLastOperationTypeId(entity.getLastOperationTypeId());
				batterySN.setLastOperator(entity.getLastOperator());
				batterySN.setLastOperatorSystem(entity.getLastOperatorSystem());
				batterySN.setLastReferenceOrderNumber(entity.getLastReferenceOrderNumber());
				batterySN.setLastReferenceOrderType(entity.getLastReferenceOrderType());
				batterySN.setMaterialCommercialItemCode(entity.getMtrlDimCn().getMtrlCmrlNbr());
				batterySN.setMaterialDescriptionCn(entity.getMtrlDimCn().getMtrlDescCn());
				batterySN.setMaterialPlmNumber(entity.getMtrlDimCn().getMtrlPlmNbr());
				batterySN.setMaterialQadNumber(entity.getMaterialQadNumber());
				batterySN.setPackageSerialNo(entity.getPackageSerialNo());
				batterySN.setProductLegacyBrandDes(entity.getMtrlDimCn().getPrdBrndDescCn());
				batterySN.setProductPromoDes(entity.getMtrlDimCn().getPrdLnDescCn());				
				
				batterySNList.add(batterySN);
			}); 
			batterySNResponse.setSerialNumberList(batterySNList);
			batterySNResponse.setTotalNumber(batterySerialNos.size());
		
	}
		return batterySNResponse;
	}
}
