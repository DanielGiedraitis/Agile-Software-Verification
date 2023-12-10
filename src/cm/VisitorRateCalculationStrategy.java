package cm;

import java.math.BigDecimal;

public class VisitorRateCalculationStrategy implements RateCalculationStrategy {
    private static final BigDecimal freeAmount = new BigDecimal("10.00");
    private static final BigDecimal reductionPercentage = new BigDecimal("0.50");

    @Override
    public BigDecimal calculateRate(BigDecimal totalCost) {
        if (totalCost.compareTo(freeAmount) > 0) {
            BigDecimal amountAboveFree = totalCost.subtract(freeAmount);
            BigDecimal reduction = amountAboveFree.multiply(reductionPercentage);
            return reduction.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        return BigDecimal.ZERO.setScale(2);
    }
}


