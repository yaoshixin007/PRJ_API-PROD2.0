package com.jci.repository;

import java.math.BigInteger;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.jci.domain.MtrlDimCn;
import com.jci.util.ApplicationConstants;

@Transactional
public interface ProductQADRepository extends JpaRepository<MtrlDimCn, Integer>, PagingAndSortingRepository<MtrlDimCn, Integer>{

	List<MtrlDimCn> findByMtrlPlmNbrIn (List<BigInteger> mtrlPlmNbr);

	/**
	 * retrieve data based on pagination
	 * @param pageable
	 * @return
	 */
	@Query("from MtrlDimCn prd ")
	List<MtrlDimCn> findAllProducts( Pageable pageable);
	
	/**
	 * Method to be called by service which need not worry about pagination.
	 * @return
	 */
	default List<MtrlDimCn> findLimitedProducts() {
	    return findAllProducts(new PageRequest(ApplicationConstants.PAGINATION_OFFSET,ApplicationConstants.PAGINATION_LIMIT));
	  }
	
	List<MtrlDimCn> findByMtrlCmrlNbrNot(BigInteger value);
	
}
