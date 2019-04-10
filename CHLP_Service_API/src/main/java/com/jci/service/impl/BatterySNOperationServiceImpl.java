package com.jci.service.impl;

import java.net.URI;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.jci.common.core.InjectableLogger;
import com.jci.common.exception.BatteryOperationBusinessException;
import com.jci.common.exception.ResourceNotFoundException;
import com.jci.domain.BatteryOperation;
import com.jci.repository.BatterySNOperationRepository;
import com.jci.repository.JCIApplicationRepositoryCustom;
import com.jci.service.IBatterySNOperationService;
import com.jci.transfer.BatteryOperationDetails;
import com.jci.transfer.BatteryOperationRequest;
import com.jci.transfer.BatteryOperationResponse;
import com.jci.util.ApplicationConstants;

@Qualifier(ApplicationConstants.BATTERY_OPERATION_SERVICE)
@Service
public class BatterySNOperationServiceImpl<T> implements IBatterySNOperationService<T> {

	@InjectableLogger
	private static Logger logger;

	@Autowired
	BatterySNOperationRepository batterySNOperationRepository;

	@Autowired
	JCIApplicationRepositoryCustom JCIApplicationRepository;

	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Fetch records based on serialNo from BatteryOperation
	 * 
	 * @return BatterySNResponse
	 */
	@Override
	@SuppressWarnings("unchecked")
	public T fetchBatterySNOperation(String serialNo) {

		logger.info("Inside method fetchBatterySNOperation in BatterySNOperationImpl {}", serialNo);

		BatteryOperationResponse operationResponse = new BatteryOperationResponse();
		List<BatteryOperationDetails> detailsList = new ArrayList<>();

		List<BatteryOperation> operation = findBySerialNo(serialNo).orElse(null);

		/*if (operation.isEmpty()) {
			throw new ResourceNotFoundException("Operation ", "serialNo", serialNo);
		}*/

		if (!operation.isEmpty()) {
		operation.forEach(OperationItem -> {
			BatteryOperationDetails detailItem = modelMapper.map(OperationItem, BatteryOperationDetails.class);
			detailsList.add(detailItem);

		});
		}

		operationResponse.setBatteryOperationResponse(detailsList);

		logger.info("Ending method fetchBatterySNOperation in BatterySNOperationImpl.");

		return (T) operationResponse;

	}

	@SuppressWarnings("unchecked")
	@Override
	public T createBatterySNOperation(BatteryOperationRequest batteryOperationRequest)
			throws BatteryOperationBusinessException {

		logger.info("Inside method fetchBatterySNOperation in BatterySNOperationImpl");

		// Step 1: Check if a message Id present for given request
		Date date = new java.sql.Date(System.currentTimeMillis());
		Integer currentOperationId = null;
		URI location = null;

		BatteryOperation batteryOperation = findBatteryOperationByMessageId(batteryOperationRequest, date);

		// Step 2: if step 1 true

		if (batteryOperation == null) {

			// Step 2.1: post in Operation

			batteryOperation = new BatteryOperation();

			batteryOperation.setOperationChannel(batteryOperationRequest.getChannel());
			batteryOperation.setSerialNo(batteryOperationRequest.getSerialNo());
			batteryOperation.setMessageId(batteryOperationRequest.getMessageId());
			batteryOperation.setOperationTypeId(batteryOperationRequest.getOperationTypeId());
			batteryOperation.setOperationTime(batteryOperationRequest.getOperationTime());
			batteryOperation.setOperationFrom(batteryOperationRequest.getOperationFrom());
			batteryOperation.setOperationDest(batteryOperationRequest.getOperationDest());
			batteryOperation.setOperator(batteryOperationRequest.getOperator());
			batteryOperation.setOperatorSourceSystem(batteryOperationRequest.getOperatorSourceSystem());
			batteryOperation.setOperationRemark(batteryOperationRequest.getOperationRemark());
			batteryOperation.setReferenceOrderType(batteryOperationRequest.getReferenceOrderType());
			batteryOperation.setReferenceOrderNumber(batteryOperationRequest.getReferenceOrderNumber());

			batterySNOperationRepository.save(batteryOperation);

			/*
			 * location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			 * .buildAndExpand(batteryOperation.getOperationId()).toUri();
			 */
			currentOperationId = batteryOperation.getOperationId();

		}

		// Step 3: if step 2 false

		else if (batteryOperation != null) {

			throw new BatteryOperationBusinessException(
					String.format("Operation already exist for the day for messageId '%s'  with operationId :  '%s'",
							batteryOperationRequest.getMessageId(), batteryOperation.getOperationId()));
		}

		return (T) currentOperationId;

	}

	public Optional<List<BatteryOperation>> findBySerialNo(String serialNo) {

		return batterySNOperationRepository.findBySerialNoAndExpiryTimeStampNull(serialNo);

	}

	private BatteryOperation findBatteryOperationByMessageId(BatteryOperationRequest batteryOperationRequest,
			Date currentTime) {

		return batterySNOperationRepository.findByMessageIdAndOperationChannelAndInsertTimestamp(
				batteryOperationRequest.getMessageId(), batteryOperationRequest.getChannel(), currentTime);

	}

	@Override
	public void deleteBatterySNOperation(BatteryOperationRequest batteryOperationRequest) {

		BatteryOperation operation = batterySNOperationRepository
				.findByOperationId(batteryOperationRequest.getOperationId())

				.orElseThrow(() -> new ResourceNotFoundException("Operation ", "operationId",
						batteryOperationRequest.getOperationId()));

		operation.setExpiryTimeStamp(new Timestamp(System.currentTimeMillis()));

		batterySNOperationRepository.save(operation);

	}

}
