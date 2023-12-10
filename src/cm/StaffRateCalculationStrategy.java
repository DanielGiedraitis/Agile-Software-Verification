package cm;

import java.math.BigDecimal;

public class StaffRateCalculationStrategy implements RateCalculationStrategy {
    private static final BigDecimal maxPayable = new BigDecimal("10.0");

    @Override
    public BigDecimal calculateRate(BigDecimal totalCost) {
        return totalCost.min(maxPayable);
    }
}