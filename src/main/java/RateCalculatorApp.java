import domain.LenderOffer;
import interest.CompoundInterestCalculator;
import ratefinder.MultipleLendersRateFinder;
import ratefinder.RateFinder;
import result.ResultDisplay;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

import static csv.CsvReader.csvToLenderInfoList;
import static validation.InputValidator.validateArguments;

public class RateCalculatorApp {

    private static RateFinder rateFinder = new MultipleLendersRateFinder();

    public static void main(String[] args) {
        validateArguments(args);
        Collection<LenderOffer> lenderOffers = csvToLenderInfoList(args[0]);
        BigDecimal amount = new BigDecimal(args[1]);
        Optional<BigDecimal> rateOptional = rateFinder.findLowestRate(lenderOffers, amount);
        if (rateOptional.isPresent()) {
            CompoundInterestCalculator compoundInterestCalculator = new CompoundInterestCalculator(amount, rateOptional.get());
            ResultDisplay resultDisplay = new ResultDisplay(amount, rateOptional.get(),
                    compoundInterestCalculator.getMonthlyRepayment(), compoundInterestCalculator.getTotalRepayment());
            resultDisplay.display();
        } else {
            System.out.println("Is not possible to provide a quote at this time.");
        }
    }

}