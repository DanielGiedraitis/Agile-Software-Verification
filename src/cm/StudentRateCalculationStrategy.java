package cm;

import java.math.BigDecimal;

public class StudentRateCalculationStrategy implements RateCalculationStrategy {
    private static final BigDecimal minAmount = new BigDecimal("5.50");
    private static final BigDecimal reductionPercentage = new BigDecimal("0.33");

    @Override
    public BigDecimal calculateRate(BigDecimal totalCost) {
        if (totalCost.compareTo(minAmount) > 0) {
            BigDecimal excessAmount = totalCost.subtract(minAmount);
            BigDecimal reduction = excessAmount.multiply(reductionPercentage);
            return minAmount.add(reduction);
        }
        return totalCost;
    }
}


