package result;

import java.math.BigDecimal;

import static constants.MathConstants.ROUND_MODE;

public class ResultDisplay {

    private final BigDecimal amount;
    private final BigDecimal rate;
    private final BigDecimal monthlyRepayment;
    private final BigDecimal totalRepayment;

    public ResultDisplay(BigDecimal amount, BigDecimal rate, BigDecimal monthlyRepayment, BigDecimal totalRepayment) {
        this.amount = amount;
        this.rate = rate;
        this.monthlyRepayment = monthlyRepayment;
        this.totalRepayment = totalRepayment;
    }

    public void display() {
        System.out.println("Requested amount: £" + amount.setScale(0, ROUND_MODE));
        System.out.println("Rate: " + rate.multiply(BigDecimal.valueOf(100)).setScale(1, ROUND_MODE) + "%");
        System.out.println("Monthly repayment: £" + monthlyRepayment.setScale(2, ROUND_MODE));
        System.out.println("Total repayment: £" + totalRepayment.setScale(2, ROUND_MODE));
    }
}
