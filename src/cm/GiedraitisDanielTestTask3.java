package cm;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class GiedraitisDanielTestTask3 {

    // Test Period class
    // Test for Constructor
    @Test
    public void testConstructorValidStartAndEndHoursZeroAndTwentyFour() {
        try {
            assertDoesNotThrow(() -> new Period(0, 24));
        } catch (Exception e) {
            fail("Exception " + e);
        }
    }

    @Test
    public void testConstructorValidStartAndEndHours() {
        try {
            assertDoesNotThrow(() -> new Period(10, 15));
        } catch (Exception e) {
            fail("Exception " + e);
        }
    }

    @Test
    public void testConstructorInvalidStartHourLessThanZero() {
        try {
            assertThrows(IllegalArgumentException.class, () -> new Period(-1, 15));
        } catch (Exception e) {
            fail("Exception " + e);
        }
    }

    @Test
    public void testConstructorInvalidStartHourGreaterThanTwentyFour() {
        try {
            assertThrows(IllegalArgumentException.class, () -> new Period(25, 15));
        } catch (Exception e) {
            fail("Exception " + e);
        }
    }

    @Test
    public void testConstructorInvalidEndHourLessThanZero() {
        try {
            assertThrows(IllegalArgumentException.class, () -> new Period(10, -1));
        } catch (Exception e) {
            fail("Exception " + e);
        }
    }

    @Test
    public void testConstructorInvalidEndHourGreaterThanTwentyFour() {
        try {
            assertThrows(IllegalArgumentException.class, () -> new Period(10, 25));
        } catch (Exception e) {
            fail("Exception " + e);
        }
    }

    @Test
    public void testConstructorEndHourLessThanStartHour() {
        try {
            assertThrows(IllegalArgumentException.class, () -> new Period(15, 10));
        } catch (Exception e) {
            fail("Exception " + e);
        }
    }

    @Test
    public void testConstructorStartHourSameAsEndHour() {
        try {
            assertThrows(IllegalArgumentException.class, () -> new Period(10, 10));
        } catch (Exception e) {
            fail("Exception " + e);
        }
    }


    // Test for Duration Method
    @Test
    public void testValidDurationZeroAndTwentyFour() {
        try {
            Period period = new Period(0, 24);
            int duration = period.duration();
            assertEquals(24, duration);
        } catch (Exception e) {
            fail("Exception " + e);
        }
    }

    @Test
    public void testValidDuration() {
        try {
            Period period = new Period(10, 15);
            int duration = period.duration();
            assertEquals(5, duration);
        } catch (Exception e) {
            fail("Exception " + e);
        }
    }



    // Test for Overlaps Method
    @Test
    public void testNoOverlap() {
        try {
            Period period1 = new Period(1, 3);
            Period period2 = new Period(4, 6);
            assertFalse(period1.overlaps(period2));
        } catch (Exception e) {
            fail("Exception " + e);
        }
    }

    @Test
    public void testOverlapAtStart() {
        try {
            Period period1 = new Period(1, 4);
            Period period2 = new Period(3, 6);
            assertTrue(period1.overlaps(period2));
        } catch (Exception e) {
            fail("Exception " + e);
        }
    }

    @Test
    public void testOverlapAtEnd() {
        try {
            Period period1 = new Period(1, 5);
            Period period2 = new Period(4, 7);
            assertTrue(period1.overlaps(period2));
        } catch (Exception e) {
            fail("Exception " + e);
        }
    }

    @Test
    public void testOverlapFullyContained() {
        try {
            Period period1 = new Period(2, 6);
            Period period2 = new Period(1, 7);
            assertTrue(period1.overlaps(period2));
        } catch (Exception e) {
            fail("Exception " + e);
        }
    }

    @Test
    public void testOverlapBoundaryValueIdenticalPeriods() {
        try {
            Period period1 = new Period(4, 10);
            Period period2 = new Period(4, 10);
            assertTrue(period1.overlaps(period2));
        } catch (Exception e) {
            fail("Exception " + e);
        }
    }

    // Test Rate class
    // Test for Constructor
    @Test
    public void testRateAllParametersWithinValidRanges() {
        try {
            new Rate(
                    CarParkKind.STAFF, new BigDecimal("5.0"), new BigDecimal("3.0"),
                    new ArrayList<>() {{
                        add(new Period(7, 10));
                        add(new Period(12, 14));
                    }},
                    new ArrayList<>() {{
                        add(new Period(10, 12));
                    }}
            );
        } catch (Exception e) {
            fail("Exception " + e);
        }
    }

    @Test
    public void testRateNormalAndReducedPeriodsDontOverlap() {
        try {
            new Rate(
                    CarParkKind.STUDENT, new BigDecimal("7.0"), new BigDecimal("4.0"),
                    new ArrayList<>() {{
                        add(new Period(8, 12));
                        add(new Period(14, 16));
                    }},
                    new ArrayList<>() {{
                        add(new Period(12, 14));
                        add(new Period(16, 18));
                    }}
            );
        } catch (Exception e) {
            fail("Exception " + e);
        }
    }

    @Test
    public void testRateNormalAndReducedPeriodsOverlap() {
        try {
            assertThrows(IllegalArgumentException.class, () -> new Rate(
                    CarParkKind.MANAGEMENT, new BigDecimal("6.0"), new BigDecimal("5.0"),
                    new ArrayList<>() {{
                        add(new Period(10, 15));
                        add(new Period(14, 18));
                    }},
                    new ArrayList<>() {{
                        add(new Period(12, 14));
                        add(new Period(16, 20));
                    }}
            ));
        } catch (Exception e) {
            fail("Exception " + e);
        }
    }

    @Test
    public void testRateNormalRateEqualsReducedRate() {
        try {
            assertThrows(IllegalArgumentException.class, () -> new Rate(
                    CarParkKind.VISITOR, new BigDecimal("4.0"), new BigDecimal("4.0"),
                    new ArrayList<>() {{
                        add(new Period(8, 12));
                    }},
                    new ArrayList<>() {{
                        add(new Period(12, 14));
                    }}
            ));
        } catch (Exception e) {
            fail("Exception " + e);
        }
    }

    @Test
    public void testRateNormalRateGreaterThanReducedRate() {
        try {
            new Rate(
                    CarParkKind.STUDENT, new BigDecimal("6.0"), new BigDecimal("3.0"),
                    new ArrayList<>() {{
                        add(new Period(8, 12));
                    }},
                    new ArrayList<>() {{
                        add(new Period(12, 14));
                    }}
            );
        } catch (Exception e) {
            fail("Exception " + e);
        }
    }

    @Test
    public void testRateNormalRateLessThanReducedRate() {
        try {
            assertThrows(IllegalArgumentException.class, () -> new Rate(
                    CarParkKind.STAFF, new BigDecimal("3.0"), new BigDecimal("5.0"),
                    new ArrayList<>() {{
                        add(new Period(8, 12));
                    }},
                    new ArrayList<>() {{
                        add(new Period(12, 14));
                    }}
            ));
        } catch (Exception e) {
            fail("Exception " + e);
        }
    }

    @Test
    public void testRateOverlappingNormalPeriods() {
        try {
            assertThrows(IllegalArgumentException.class, () -> new Rate(
                    CarParkKind.STUDENT, new BigDecimal("7.0"), new BigDecimal("4.0"),
                    new ArrayList<>() {{
                        add(new Period(8, 12));
                        add(new Period(11, 14)); // Overlapping periods
                    }},
                    new ArrayList<>() {{
                        add(new Period(12, 14));
                        add(new Period(16, 18));
                    }}
            ));
        } catch (Exception e) {
            fail("Exception " + e);
        }
    }

    @Test
    public void testRateOverlappingReducedPeriods() {
        try {
            new Rate(
                    CarParkKind.STUDENT, new BigDecimal("7.0"), new BigDecimal("4.0"),
                    new ArrayList<>() {{
                        add(new Period(8, 12));
                    }},
                    new ArrayList<>() {{
                        add(new Period(12, 14));
                        add(new Period(15, 16));
                    }}
            );
        } catch (Exception e) {
            fail("Exception " + e);
        }
    }

    @Test
    public void testRateNormalPeriodEndHourExceeds24() {
        try {
            assertThrows(IllegalArgumentException.class, () -> new Rate(
                    CarParkKind.STUDENT, new BigDecimal("7.0"), new BigDecimal("4.0"),
                    new ArrayList<>() {{
                        add(new Period(8, 12));
                        add(new Period(14, 26)); // Exceeds 24 hours
                    }},
                    new ArrayList<>() {{
                        add(new Period(12, 14));
                        add(new Period(16, 18));
                    }}
            ));
        } catch (Exception e) {
            fail("Exception " + e);
        }
    }

    @Test
    public void testRateReducedPeriodStartHourLessThan0() {
        try {
            assertThrows(IllegalArgumentException.class, () -> new Rate(
                    CarParkKind.STUDENT, new BigDecimal("7.0"), new BigDecimal("4.0"),
                    new ArrayList<>() {{
                        add(new Period(8, 12));
                    }},
                    new ArrayList<>() {{
                        add(new Period(-1, 14)); // Less than 0
                        add(new Period(16, 18));
                    }}
            ));
        } catch (Exception e) {
            fail("Exception " + e);
        }
    }


    @Test
    public void testRateEmptyNormalAndReducedPeriods() {
        try {
            new Rate(
                    CarParkKind.VISITOR, new BigDecimal("6.0"), new BigDecimal("4.0"),
                    new ArrayList<>(), new ArrayList<>()
            );
        } catch (Exception e) {
            fail("Exception " + e);
        }
    }

    @Test
    public void testRateOverlappingNormalAndReducedPeriods() {
        try {
            assertThrows(IllegalArgumentException.class, () -> new Rate(
                    CarParkKind.MANAGEMENT, new BigDecimal("7.0"), new BigDecimal("4.0"),
                    new ArrayList<>() {{
                        add(new Period(8, 12));
                        add(new Period(10, 14));
                    }},
                    new ArrayList<>() {{
                        add(new Period(11, 14));
                        add(new Period(12, 15));
                    }}
            ));
        } catch (Exception e) {
            fail("Exception " + e);
        }
    }

    @Test
    public void testRateEndHourExceeds24AndStartHourLessThan0() {
        try {
            assertThrows(IllegalArgumentException.class, () -> new Rate(
                    CarParkKind.STUDENT, new BigDecimal("7.0"), new BigDecimal("4.0"),
                    new ArrayList<>() {{
                        add(new Period(8, 26)); // End hour exceeds 24
                    }},
                    new ArrayList<>() {{
                        add(new Period(-1, 14)); // Start hour less than 0
                        add(new Period(16, 18));
                    }}
            ));
        } catch (Exception e) {
            fail("Exception " + e);
        }
    }


    // Test for Calculate Method
    @Test
    public void testCalculateNormalRateOnly() {
        try {
            Rate rate1 = new Rate(CarParkKind.STAFF, new BigDecimal("5.0"), new BigDecimal("0.0"),
                    new ArrayList<>() {{
                        add(new Period(8, 12));
                    }},
                    new ArrayList<>()
            );
            rate1.calculate(new Period(8, 12));
        } catch (Exception e) {
            fail("Exception " + e);
        }
    }

    @Test
    public void testCalculateReducedRateOnly() {
        try {
            Rate rate2 = new Rate(CarParkKind.STUDENT, new BigDecimal("4.0"), new BigDecimal("3.0"),
                    new ArrayList<>(),
                    new ArrayList<>() {{
                        add(new Period(14, 16));
                    }}
            );
            rate2.calculate(new Period(14, 16));
        } catch (Exception e) {
            fail("Exception " + e);
        }
    }

    @Test
    public void testCalculateMixedRateAndPeriods() {
        try {
            Rate rate3 = new Rate(CarParkKind.MANAGEMENT, new BigDecimal("6.0"), new BigDecimal("4.0"),
                    new ArrayList<>() {{
                        add(new Period(8, 12));
                        add(new Period(14, 16));
                    }},
                    new ArrayList<>() {{
                        add(new Period(12, 14));
                        add(new Period(16, 18));
                    }}
            );
            rate3.calculate(new Period(10, 15));
        } catch (Exception e) {
            fail("Exception " + e);
        }
    }

    @Test
    public void testCalculateNoRateAndEmptyPeriods() {
        try {
            Rate rate4 = new Rate(
                    CarParkKind.VISITOR, new BigDecimal("1.0"), new BigDecimal("0.0"),
                    new ArrayList<>(),
                    new ArrayList<>()
            );
            rate4.calculate(new Period(8, 12));
        } catch (Exception e) {
            fail("Exception " + e);
        }
    }

    @Test
    public void testCalculatePartiallyOverlappingPeriods() {
        try {
            Rate rate5 = new Rate(
                    CarParkKind.STAFF, new BigDecimal("5.0"), new BigDecimal("3.0"),
                    new ArrayList<>() {{
                        add(new Period(8, 12));
                        add(new Period(14, 16));
                    }},
                    new ArrayList<>() {{
                        add(new Period(12, 14));
                        add(new Period(16, 18));
                    }}
            );
            rate5.calculate(new Period(11, 15));
        } catch (Exception e) {
            fail("Exception " + e);
        }
    }

    @Test
    public void testCalculateMaximumNormalRate() {
        try {
            Rate rate6 = new Rate(
                    CarParkKind.STUDENT, new BigDecimal("999.99"), new BigDecimal("9.99"),
                    new ArrayList<>() {{
                        add(new Period(8, 12));
                    }},
                    new ArrayList<>() {{
                        add(new Period(13, 16));
                    }}
            );
            rate6.calculate(new Period(8, 11));
        } catch (Exception e) {
            fail("Exception " + e);
        }
    }

    @Test
    public void testCalculateMinimumReducedRate() {
        try {
            Rate rate7 = new Rate(
                    CarParkKind.MANAGEMENT, new BigDecimal("6.0"), new BigDecimal("0.01"),
                    new ArrayList<>() {{
                        add(new Period(8, 12));
                    }},
                    new ArrayList<>() {{
                        add(new Period(14, 16));
                    }}
            );
            rate7.calculate(new Period(14, 16));
        } catch (Exception e) {
            fail("Exception " + e);
        }
    }



    // White box test cases added for the Period class
    @Test
    public void testPeriodConstructorInvalidStartAndEndHoursAboveTwentyFour() {
        // Increased branch coverage for Period class by 4%
        try {
            assertThrows(IllegalArgumentException.class, () -> new Period(25, 28));
        } catch (Exception e) {
            fail("Exception " + e);
        }
    }


    // White box test cases added for the Rate class
    @Test
    public void testRateConstructorNullPeriods() {
        // Increased branch coverage for Rate class by 3%
        try {
            assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STAFF, new BigDecimal("5.0"), new BigDecimal("3.0"), null, null));
        } catch (Exception e) {
            fail("Exception " + e);
        }
    }

    @Test
    public void testRateConstructorNegativeRates() {
        // Increased branch coverage for Rate class by 2%
        try {
            assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STAFF, new BigDecimal("-5.0"), new BigDecimal("-3.0"),
                    new ArrayList<>(), new ArrayList<>()));
        } catch (Exception e) {
            fail("Exception " + e);
        }
    }

    @Test
    public void testRateConstructorNullNormalPeriods() {
        // Increased branch coverage for Rate class by 3%
        try {
            assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STAFF, new BigDecimal("5.0"), new BigDecimal("3.0"),
                    null, new ArrayList<>()));
        } catch (Exception e) {
            fail("Exception " + e);
        }
    }

    @Test
    public void testRateConstructorNegativeReducedRate() {
        // Increased branch coverage for Rate class by 3%
        try {
            assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STAFF, new BigDecimal("5.0"), new BigDecimal("-3.0"),
                    new ArrayList<>(), new ArrayList<>()));
        } catch (Exception e) {
            fail("Exception " + e);
        }
    }

    @Test
    public void testRateConstructorNullRates() {
        // Increased branch coverage for Rate class by 2%
        try {
            assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STAFF, null, null,
                    new ArrayList<>(), new ArrayList<>()));
        } catch (Exception e) {
            fail("Exception " + e);
        }
    }

    @Test
    public void testRateConstructorInvalidOverlappingPeriods() {
        // Increased branch coverage for Rate class by 3%
        try {
            assertThrows(IllegalArgumentException.class, () -> new Rate(
                    CarParkKind.STUDENT, new BigDecimal("7.0"), new BigDecimal("4.0"),
                    new ArrayList<>() {{
                        add(new Period(8, 12));
                    }},
                    new ArrayList<>() {{
                        add(new Period(11, 14));
                    }}
            ));
        } catch (Exception e) {
            fail("Exception " + e);
        }
    }

    @Test
    public void testRateConstructorReducedRateNull() {
        // Increased branch coverage for Rate class by 3%
        try {
            assertThrows(IllegalArgumentException.class, () -> new Rate(
                    CarParkKind.STAFF, BigDecimal.valueOf(5.0), null,
                    new ArrayList<>(),
                    new ArrayList<>() {{
                        add(new Period(10, 14));
                    }}
            ));
        } catch (Exception e) {
            fail("Exception " + e);
        }
    }
}
