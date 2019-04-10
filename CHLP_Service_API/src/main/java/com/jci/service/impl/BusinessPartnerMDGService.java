package com.jci.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.jci.common.core.InjectableLogger;
import com.jci.domain.BusPrtnrDimCn;
import com.jci.repository.BusinessPartnerRepository;
import com.jci.repository.JCIApplicationRepositoryCustom;
import com.jci.service.IBusinessPartnerMDG;
import com.jci.transfer.BPResponse;
import com.jci.transfer.BPSDetails;
import com.jci.util.ApplicationConstants;

/**
 * @author apiadmin2
 *
 */
@Service
public class BusinessPartnerMDGService implements IBusinessPartnerMDG {

	@InjectableLogger
	private static Logger logger;

	@Autowired
	BusinessPartnerRepository bpRepo;
	
	@Autowired
	JCIApplicationRepositoryCustom jciRepo;

	@Override
	public BPResponse getMDGBusinessPartner(List<String> mdgCustKey) {
		logger.debug("Start Business Partner Service to fetch data based on MDG Key : {}", mdgCustKey);
		logger.debug("Fetch data from database");
		List<BusPrtnrDimCn> bussinessPartnerEntity = bpRepo.findByMdgCustKeyIn(mdgCustKey);

		BPResponse response = null;

		if (bussinessPartnerEntity != null && !bussinessPartnerEntity.isEmpty()) {
			logger.debug("Data retireved from db. No of records: {}", bussinessPartnerEntity.size());
			response = mappingEntityToJaxb(bussinessPartnerEntity);
			logger.debug("End of Business Partner Service");
		} else {
			logger.debug("No data found for respective mdgCustKeys :{} ", mdgCustKey);
			response = new BPResponse();
			response.setTotalNumber(0);
		}

		return response;
	}

	@Override
	public BPResponse getMDGBusinessPartner() {
		logger.debug("Start Business Partner Service to fetch all data");
		logger.debug("Fetch data from database");
		List<BusPrtnrDimCn> bussinessPartnerEntity = bpRepo.findAll();

		BPResponse response = null;

		if (bussinessPartnerEntity != null && !bussinessPartnerEntity.isEmpty()) {
			logger.debug("Data retireved from db. No of records: {}", bussinessPartnerEntity.size());
			response = mappingEntityToJaxb(bussinessPartnerEntity);
			logger.debug("End of Business Partner Service");
		} else {
			logger.debug("No data found In Table");
			response = new BPResponse();
			response.setTotalNumber(0);
		}

		return response;
	}

	@Override
	public BPResponse getMDGBusinessPartnerV2(List<String> mdgCustKey, List<String> bpStatus, List<String> bpType,
			List<String> bpCode, List<String> countryCode) {
		logger.debug(
				"Start Business Partner Service to fetch data based on MDG Key, BP Status, BP Type, Bp Code, Country Code : {}, {}, {}, {}, {}",
				mdgCustKey, bpStatus, bpType, bpCode, countryCode);
		logger.debug("Fetch data from database");
		List<BusPrtnrDimCn> businessPartnerEntity = jciRepo.getMDGBusinessPartnerDetailsV2(mdgCustKey, bpStatus, bpType, bpCode, countryCode);
		

		BPResponse response = null;

		if (businessPartnerEntity != null && !businessPartnerEntity.isEmpty()) {
			logger.debug("Data retireved from db. No of records: {}", businessPartnerEntity.size());
			response = mappingEntityToJaxb(businessPartnerEntity);
			logger.debug("End of Business Partner Service");
		} else {
			logger.debug("No data found for respective filters - mdgCustKey, bpStatus, bpType, bpCode, countryCode :{}, {}, {}, {}, {} ", mdgCustKey, bpStatus, bpType,
					bpCode, countryCode);
			response = new BPResponse();
			response.setTotalNumber(0);
		}

		return response;

	}

	private BPResponse mappingEntityToJaxb(List<BusPrtnrDimCn> bussinessPartnerEntity) {
		logger.debug("Mapping Entity to POJO ");
		BPResponse response = new BPResponse();
		List<BPSDetails> bpList = new ArrayList<>();
		
		SimpleDateFormat sdf = new SimpleDateFormat(ApplicationConstants.DATE_FORMAT_YYYY_MM_DD);
		
		if(! CollectionUtils.isEmpty(bussinessPartnerEntity) ) {
			bussinessPartnerEntity.forEach(entity -> {
				BPSDetails businessPartnerMDG = new BPSDetails();
				businessPartnerMDG.setMdgCustKey(entity.getMdgCustKey());
				businessPartnerMDG.setBpCode(entity.getBusPrtnrCde());
				businessPartnerMDG.setBpName(entity.getBusPrtnrNm());
				businessPartnerMDG.setBpType(entity.getBusPrtnrTyp());
				businessPartnerMDG.setBpTypeDesc(entity.getBusPrtnrTypDesc());
				businessPartnerMDG.setCountryCode(entity.getBusPrtnrCntryCde());
				businessPartnerMDG.setMinOrderQty(entity.getMinmOrdQty());
				businessPartnerMDG.setBpStatus(entity.getBusPrtnrStat());
				if(entity.getMdgOprtnTstmp() != null) {
					businessPartnerMDG.setOprTimestamp(sdf.format(entity.getMdgOprtnTstmp().getTime()));
				}
				
				
				bpList.add(businessPartnerMDG);
			});
			response.setBpsList(bpList);
			response.setTotalNumber(bussinessPartnerEntity.size());
		}
		return response;
	}

}
