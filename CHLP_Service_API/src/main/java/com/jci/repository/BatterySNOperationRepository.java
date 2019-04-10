package com.jci.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jci.domain.BatteryOperation;

@Transactional
@Repository
public interface BatterySNOperationRepository extends JpaRepository<BatteryOperation, Integer> {

	Optional<List<BatteryOperation>> findBySerialNoAndExpiryTimeStampNull(String serialNo);

	Optional<BatteryOperation> findByOperationId(Integer operationId);
	
	BatteryOperation findByMessageIdAndOperationChannelAndInsertTimestamp(String messageId,String operationChannel,Date date);
	

}
