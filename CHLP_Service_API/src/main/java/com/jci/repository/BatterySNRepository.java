
/**
 * 
 */
package com.jci.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.jci.domain.MtrlSnDim;

/**
 * @author apiadmin2
 *
 */
@Transactional
public interface BatterySNRepository extends JpaRepository<MtrlSnDim, Integer> {
	List<MtrlSnDim> findAll();

	List<MtrlSnDim> findBySerialNoIn(@Param("serialNo") String[] serialNo);

	List<MtrlSnDim> findByPackageSerialNoIn(String[] serialNo);

	List<MtrlSnDim> findBySerialNoInOrPackageSerialNoIn(String[] serialNo, String[] pkgSerialNo);
}