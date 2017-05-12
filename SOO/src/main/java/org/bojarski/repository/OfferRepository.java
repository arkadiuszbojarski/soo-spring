/**
 * 
 */
package org.bojarski.repository;

import org.bojarski.model.Offer;
import org.bojarski.model.QOffer;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Arkadiusz Bojarski
 *
 */
public interface OfferRepository extends PagingAndSortingRepository<Offer, String>, QueryDslPredicateExecutor<QOffer> {

}
