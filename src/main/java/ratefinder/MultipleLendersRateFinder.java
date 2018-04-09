package ratefinder;

import domain.LenderOffer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static constants.MathConstants.DIVISION_SCALE;
import static constants.MathConstants.ROUND_MODE;
import static java.math.BigDecimal.ZERO;

/**
 * This finder will use the full lender pool, taking adding money from multiple lenders to fulfill the required amount
 */
public class MultipleLendersRateFinder implements RateFinder {

    @Override
    public Optional<BigDecimal> findLowestRate(Collection<LenderOffer> lenderOffers, BigDecimal amount) {
        List<LenderOffer> lenderOfferList = getOffersAsSortedList(lenderOffers);
        BigDecimal resultRate = ZERO;
        BigDecimal cumulativeAmount = ZERO;
        for (int i = 0; i < lenderOfferList.size() && cumulativeAmount.compareTo(amount) < 0; i++) {
            LenderOffer lenderOffer = lenderOfferList.get(i);
            BigDecimal amountToBeLentThisOffer = lenderOffer.getAvailable().min(amount.subtract(cumulativeAmount));
            BigDecimal factorThisOffer = amountToBeLentThisOffer.divide(amount, DIVISION_SCALE, ROUND_MODE);
            resultRate = resultRate.add(lenderOffer.getRate().multiply(factorThisOffer));
            cumulativeAmount = cumulativeAmount.add(amountToBeLentThisOffer);
        }
        if (cumulativeAmount.compareTo(amount) < 0) {
            return Optional.empty();
        } else {
            return Optional.of(resultRate);
        }
    }

    private List<LenderOffer> getOffersAsSortedList(Collection<LenderOffer> lenderOffers) {
        List<LenderOffer> lenderOfferList = new ArrayList<>(lenderOffers);
        lenderOfferList.sort((o1, o2) -> o1.getRate().compareTo(o2.getRate()));
        return lenderOfferList;
    }
}
