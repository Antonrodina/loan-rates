package constants;

import java.math.BigDecimal;

/**
 * Constants used throughout the application are kept here, for the sake of consistency
 */
public final class MathConstants {

    /**
     * Scale to be used on dividing BigDecimals when precision matters
     */
    public static final int DIVISION_SCALE = 10;

    /**
     * Rounding mode to be used on dividing BigDecimals when precision matters
     */
    public final static int ROUND_MODE = BigDecimal.ROUND_UP;

    private MathConstants() {
    }

}
