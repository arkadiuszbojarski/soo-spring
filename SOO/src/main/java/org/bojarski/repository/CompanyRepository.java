/**
 * 
 */
package org.bojarski.repository;

import org.bojarski.model.Company;
import org.bojarski.model.QCompany;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Arkadiusz Bojarski
 *
 */
public interface CompanyRepository
		extends PagingAndSortingRepository<Company, String>, QueryDslPredicateExecutor<QCompany> {

}
