/**
 * 
 */
package org.bojarski.repository;

import org.bojarski.model.Company;
import org.bojarski.model.QCompany;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Arkadiusz Bojarski
 *
 */
public interface CompanyRepository extends PagingAndSortingRepository<Company, String>,
		QueryDslPredicateExecutor<Company>, QuerydslBinderCustomizer<QCompany> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.querydsl.binding.QuerydslBinderCustomizer#
	 * customize(org.springframework.data.querydsl.binding.QuerydslBindings,
	 * com.querydsl.core.types.EntityPath)
	 */
	@Override
	default void customize(QuerydslBindings bindings, QCompany company) {

	}
}
