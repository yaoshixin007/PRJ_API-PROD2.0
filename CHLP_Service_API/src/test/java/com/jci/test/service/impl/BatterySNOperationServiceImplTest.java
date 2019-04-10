package com.jci.test.service.impl;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jci.common.exception.BatteryOperationBusinessException;
import com.jci.domain.BatteryOperation;
import com.jci.repository.BatterySNOperationRepository;
import com.jci.service.impl.BatterySNOperationServiceImpl;
import com.jci.test.CHLPApplicationTests;
import com.jci.transfer.BatteryOperationRequest;
import com.jci.transfer.BatteryOperationResponse;

@Profile("Test")
@WebAppConfiguration
public class BatterySNOperationServiceImplTest extends CHLPApplicationTests {

	@Autowired
	private BatterySNOperationServiceImpl<?> batterySNOp;

	@Mock
	private BatterySNOperationRepository batterySNOperationRepository;

	@Mock
	private BatteryOperation batteryOperation;

	@Test
	public void fetchBatterySNOperation() {

		String serialNo = "7116391606171310";
		BatteryOperationResponse response = (BatteryOperationResponse) batterySNOp.fetchBatterySNOperation(serialNo);
		assertEquals(true, response.getBatteryOperationResponse().size() > 0);
	}
	

	@Test
	public void createBatterySNOperation() throws BatteryOperationBusinessException {

		Integer actualOperationId = (Integer) batterySNOp.createBatterySNOperation(getBatteryOperationRequestDetails());
		Integer expectedOperationId = 20070219;
		assertEquals(true, expectedOperationId.equals(actualOperationId));

	}

	@Test
	public void deleteBatterySNOperation() {

		BatteryOperationRequest batteryOperationRequest = new BatteryOperationRequest();
		batteryOperationRequest.setOperationId(20070218);
		batterySNOp.deleteBatterySNOperation(batteryOperationRequest);
		String serialNo = "7772966277567650";
		Optional<List<BatteryOperation>> batteryOperationList = batterySNOp.findBySerialNo(serialNo);
		assertEquals(true, batteryOperationList.isPresent());

	}

	private BatteryOperationRequest getBatteryOperationRequestDetails() {
		BatteryOperationRequest operationDetails = new BatteryOperationRequest();

		operationDetails.setMessageId("1223");
		operationDetails.setChannel("QAD");
		operationDetails.setOperationDest("JCI");
		operationDetails.setOperationFrom("JCI");
		operationDetails.setOperationId(20070219);
		operationDetails.setOperationRemark("Battery Production");
		operationDetails.setOperationTime(new Timestamp(System.currentTimeMillis()));
		operationDetails.setOperationTypeId(13);
		operationDetails.setOperator("NA");
		operationDetails.setOperatorSourceSystem("QAD");
		operationDetails.setReferenceOrderNumber("NA");
		operationDetails.setReferenceOrderType("NA");
		operationDetails.setSerialNo("7772966277567650");

		return operationDetails;
	}

}
