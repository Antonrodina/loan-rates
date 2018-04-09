package validation;

import java.util.Arrays;

import static java.nio.file.Files.exists;
import static java.nio.file.Paths.get;

public class InputValidator {

    private static final int MINIMUM_AMOUNT = 1000;
    private static final int MAXIMUM_AMOUNT = 15000;
    private static final int FACTOR = 100;

    public static void validateArguments(String[] args) {
        validateGotTwoArgs(args);
        validateFileExists(args[0]);
        validateAmount(args[1]);
    }

    private static void validateGotTwoArgs(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("App needs two arguments. Given was: " + Arrays.toString(args));
        }
    }

    private static void validateFileExists(String marketFileName) {
        if (!exists(get(marketFileName))) {
            throw new IllegalArgumentException("Couldn't find market data file in given path: " + marketFileName);
        }
    }

    private static void validateAmount(String amountAsString) {
        validateAmountIsANumber(amountAsString);
        Integer amount = Integer.valueOf(amountAsString);
        validateAmountIsInRange(amount);
        validateAmountIsMultiple(amount);
    }

    private static void validateAmountIsANumber(String amountAsString) {
        try {
            Integer.valueOf(amountAsString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Given amount is not a number: " + amountAsString);
        }
    }

    private static void validateAmountIsInRange(Integer amount) {
        if (amount < MINIMUM_AMOUNT || amount > MAXIMUM_AMOUNT) {
            throw new IllegalArgumentException("Amount must be within [" + MINIMUM_AMOUNT + ", " + MAXIMUM_AMOUNT + "], was: " + amount);
        }
    }

    private static void validateAmountIsMultiple(Integer amount) {
        if (amount % FACTOR != 0) {
            throw new IllegalArgumentException("Amount must be a multiple of " + FACTOR + ", was: " + amount);
        }
    }
}
