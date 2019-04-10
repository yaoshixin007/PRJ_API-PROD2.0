package com.jci.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.jci.domain.BusPrtnrDimCn;

/**
 * @author apiadmin2
 *
 */
public interface BusinessPartnerRepository extends JpaRepository<BusPrtnrDimCn, Integer> {

	List<BusPrtnrDimCn> findByMdgCustKeyIn(@Param("mdgCustKey") List<String> mdgCustKey);

	List<BusPrtnrDimCn> findAll();
}
