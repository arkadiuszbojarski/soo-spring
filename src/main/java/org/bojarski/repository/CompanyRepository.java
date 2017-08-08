/**
 * 
 */
package org.bojarski.repository;

import java.util.Collection;

import org.bojarski.model.Company;
import org.bojarski.model.QCompany;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.StringPath;

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
	default public void customize(QuerydslBindings bindings, QCompany company) {
		bindings.bind(String.class).all((StringPath path, Collection<? extends String> values) -> {
				BooleanBuilder predicate = new BooleanBuilder();
				values.forEach( value -> predicate.and(path.containsIgnoreCase(value)));
				return predicate;
		});
	}
}
