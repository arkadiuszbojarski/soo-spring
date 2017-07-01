/**
 * 
 */
package org.bojarski.repository;

import org.bojarski.model.Offer;
import org.bojarski.model.QOffer;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Arkadiusz Bojarski
 *
 */
public interface OfferRepository extends PagingAndSortingRepository<Offer, String>, QueryDslPredicateExecutor<Offer>,
		QuerydslBinderCustomizer<QOffer> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.querydsl.binding.QuerydslBinderCustomizer#
	 * customize(org.springframework.data.querydsl.binding.QuerydslBindings,
	 * com.querydsl.core.types.EntityPath)
	 */
	@Override
	default void customize(QuerydslBindings bindings, QOffer offer) {

	}
}
