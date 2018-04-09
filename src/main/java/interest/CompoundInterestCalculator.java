package interest;

import java.math.BigDecimal;

import static constants.MathConstants.DIVISION_SCALE;
import static constants.MathConstants.ROUND_MODE;
import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.valueOf;

public class CompoundInterestCalculator {

    private static final Integer DEFAULT_NUMBER_MONTHS = 36;

    private final BigDecimal baseAmount;
    private final BigDecimal annualInterestRatePerOne;
    private final Integer months;

    public CompoundInterestCalculator(BigDecimal baseAmount, BigDecimal annualInterestRatePerOne) {
        this(baseAmount, annualInterestRatePerOne, DEFAULT_NUMBER_MONTHS);
    }

    public CompoundInterestCalculator(BigDecimal baseAmount, BigDecimal annualInterestRatePerOne, Integer months) {
        this.baseAmount = baseAmount;
        this.annualInterestRatePerOne = annualInterestRatePerOne;
        this.months = months;
    }

    private BigDecimal calculateTotalRepayment() {
        return ONE.add(annualInterestRatePerOne.divide(valueOf(12), DIVISION_SCALE, ROUND_MODE)).pow(months).multiply(baseAmount);
    }

    public BigDecimal getMonthlyRepayment() {
        return calculateTotalRepayment().divide(valueOf(months), DIVISION_SCALE, ROUND_MODE);
    }

    public BigDecimal getTotalRepayment() {
        return calculateTotalRepayment();
    }
}
