package cm;

import java.math.BigDecimal;

public class VisitorRateCalculationStrategy implements RateCalculationStrategy {
    private static final BigDecimal freeAmount = new BigDecimal("10.00");
    private static final BigDecimal reductionPercentage = new BigDecimal("0.50");

    @Override
    public BigDecimal calculateRate(BigDecimal totalCost) {
        if (totalCost.compareTo(freeAmount) > 0) {
            BigDecimal excessAmount = totalCost.subtract(freeAmount);
            BigDecimal reduction = excessAmount.multiply(reductionPercentage);
            return freeAmount.add(reduction);
        }
        return BigDecimal.ZERO;
    }
}

