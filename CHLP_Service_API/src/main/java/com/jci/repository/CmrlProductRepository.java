package com.jci.repository;

import java.math.BigInteger;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.jci.domain.PrdCmrlDtl;

@Transactional
public interface CmrlProductRepository extends JpaRepository<PrdCmrlDtl, Integer>, PagingAndSortingRepository<PrdCmrlDtl, Integer>{

	List<PrdCmrlDtl> findByMtrlCmrlNbrIn(List<BigInteger> mtrlCmrlNbr);

	/**
	 * retrieve data based on pagination
	 * @param pageable
	 * @return
	 */
	@Query("from PrdCmrlDtl prdCmrl ")
	List<PrdCmrlDtl> findAllCmrlProducts();
	
	/**
	 * Method to be called by service which need not worry about pagination.
	 * @return
	 */
	
	
}
