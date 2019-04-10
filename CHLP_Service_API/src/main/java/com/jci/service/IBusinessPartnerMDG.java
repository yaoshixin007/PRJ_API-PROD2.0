package com.jci.service;

import java.util.List;

import com.jci.transfer.BPResponse;


/**
 * @author apiadmin2
 *
 */
public interface IBusinessPartnerMDG {

	public BPResponse getMDGBusinessPartner(List<String> mdgCustKey);
	
	public BPResponse getMDGBusinessPartner();
	
	public BPResponse getMDGBusinessPartnerV2(List<String> mdgCustKey, List<String> bpStatus, List<String> bpType, List<String> bpCode, List<String> countryCode);

}
