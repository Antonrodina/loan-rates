package ratefinder;

import domain.LenderOffer;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

/**
 * Tries to find a quote from a single lender
 */
public class SingleLenderRateFinder implements RateFinder {

    @Override
    public Optional<BigDecimal> findLowestRate(Collection<LenderOffer> lenderOffers, BigDecimal amount) {
        return lenderOffers.stream()
                .filter(lenderOffer -> lenderOffer.getAvailable().compareTo(amount) >= 0)
                .sorted((o1, o2) -> o1.getRate().compareTo(o2.getRate()))
                .map(LenderOffer::getRate)
                .findFirst();
    }
}
