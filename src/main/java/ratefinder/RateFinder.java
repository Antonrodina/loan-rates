package ratefinder;

import domain.LenderOffer;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

public interface RateFinder {

    /**
     * Find the lowest rate within the given offers that satisfies the specified amount
     *
     * @param lenderOffers the offer list containing rates and available amount
     * @param amount       the amount to be lent
     * @return an optional with the found rate, empty if no matching quote can be provided
     */
    Optional<BigDecimal> findLowestRate(Collection<LenderOffer> lenderOffers, BigDecimal amount);
}
