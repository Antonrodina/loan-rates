package domain;

import java.math.BigDecimal;

/**
 * Wraps an offer information, containing rate and amount available
 */
public class LenderOffer {

    private final BigDecimal rate;
    private final BigDecimal available;

    public LenderOffer(BigDecimal rate, BigDecimal available) {
        this.rate = rate;
        this.available = available;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public BigDecimal getAvailable() {
        return available;
    }

    // Ideally we wouldn't have this code, since it's not needed by the application itself, only for tests. In a real
    // world application I would use Lombok's @toString to minimize the impact
    @Override
    public String toString() {
        return "LenderOffer{" +
                "rate=" + rate +
                ", available=" + available +
                '}';
    }
}
