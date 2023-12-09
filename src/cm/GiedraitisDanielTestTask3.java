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
        // Test Case 1: Valid Start and End Hours within [0, 24]
        assertInstanceOf(Period.class, new Period(0, 24));
    }

    @Test
    public void testConstructorValidStartAndEndHours() {
        // Test Case 2: Valid Start and End Hours within [0, 24]
        assertInstanceOf(Period.class, new Period(10, 15));
    }

    @Test
    public void testConstructorInvalidStartHourLessThanZero() {
        // Test Case 3: Invalid Start Hour (startHour < 0)
        assertThrows(IllegalArgumentException.class, () -> new Period(-1, 15));
    }

    @Test
    public void testConstructorInvalidStartHourGreaterThanTwentyFour() {
        // Test Case 4: Invalid Start Hour (startHour > 24)
        assertThrows(IllegalArgumentException.class, () -> new Period(25, 15));
    }

    @Test
    public void testConstructorInvalidEndHourLessThanZero() {
        // Test Case 5: Invalid End Hour (endHour < 0)
        assertThrows(IllegalArgumentException.class, () -> new Period(10, -1));
    }

    @Test
    public void testConstructorInvalidEndHourGreaterThanTwentyFour() {
        // Test Case 6: Invalid End Hour (endHour > 24)
        assertThrows(IllegalArgumentException.class, () -> new Period(10, 25));
    }

    @Test
    public void testConstructorEndHourLessThanStartHour() {
        // Test Case 7: End Hour Less Than Start Hour
        assertThrows(IllegalArgumentException.class, () -> new Period(15, 10));
    }

    @Test
    public void testConstructorStartHourSameAsEndHour() {
        // Test Case 8: Start hour same as End hour
        assertThrows(IllegalArgumentException.class, () -> new Period(10, 10));
    }


    // Test for Duration Method
    @Test
    public void testValidDurationZeroAndTwentyFour() {
        // Test Case 9: Valid Start and End Hours within [0, 24]
        Period period = new Period(0, 24);
        int duration = period.duration();
        assertEquals(24, duration);
    }

    @Test
    public void testValidDuration() {
        // Test Case 10: Valid Start and End Hours within [0, 24]
        Period period = new Period(10, 15);
        int duration = period.duration();
        assertEquals(5, duration);
    }



    // Test for Overlaps Method
    @Test
    public void testNoOverlap() {
        // Test Case 17: No Overlap
        Period period1 = new Period(1, 3);
        Period period2 = new Period(4, 6);
        assertFalse(period1.overlaps(period2));
    }

    @Test
    public void testOverlapAtStart() {
        // Test Case 18: Overlap at the Start
        Period period1 = new Period(1, 4);
        Period period2 = new Period(3, 6);
        assertTrue(period1.overlaps(period2));
    }

    @Test
    public void testOverlapAtEnd() {
        // Test Case 19: Overlap at the End
        Period period1 = new Period(1, 5);
        Period period2 = new Period(4, 7);
        assertTrue(period1.overlaps(period2));
    }

    @Test
    public void testOverlapFullyContained() {
        // Test Case 20: Fully Contained
        Period period1 = new Period(2, 6);
        Period period2 = new Period(1, 7);
        assertTrue(period1.overlaps(period2));
    }

    @Test
    public void testOverlapBoundaryValueIdenticalPeriods() {
        // Test Case 24: Identical Periods
        Period period1 = new Period(4, 10);
        Period period2 = new Period(4, 10);
        assertTrue(period1.overlaps(period2));
    }

    // Test Rate class
    // Test for Constructor
    @Test
    public void testRateAllParametersWithinValidRanges() {
        // Test Case 1: All parameters are within valid ranges
        Rate rate = new Rate(
                CarParkKind.STAFF, new BigDecimal("5.0"), new BigDecimal("3.0"),
                new ArrayList<Period>() {{ add(new Period(7, 10)); add(new Period(12, 14)); }},
                new ArrayList<Period>() {{ add(new Period(10, 12)); }}
        );
        assertInstanceOf(Rate.class, rate);
    }

    @Test
    public void testRateNormalAndReducedPeriodsDontOverlap() {
        // Test Case 2: Normal and Reduced Periods Don't Overlap
        Rate rate = new Rate(
                CarParkKind.STUDENT, new BigDecimal("7.0"), new BigDecimal("4.0"),
                new ArrayList<Period>() {{ add(new Period(8, 12)); add(new Period(14, 16)); }},
                new ArrayList<Period>() {{ add(new Period(12, 14)); add(new Period(16, 18)); }}
        );
        assertInstanceOf(Rate.class, rate);
    }

    @Test
    public void testRateNormalAndReducedPeriodsOverlap() {
        // Test Case 3: Normal and Reduced Periods Overlap
        assertThrows(IllegalArgumentException.class, () -> new Rate(
                CarParkKind.MANAGEMENT, new BigDecimal("6.0"), new BigDecimal("5.0"),
                new ArrayList<Period>() {{ add(new Period(10, 15)); add(new Period(14, 18)); }},
                new ArrayList<Period>() {{ add(new Period(12, 14)); add(new Period(16, 20)); }}
        ));
    }

    @Test
    public void testRateNormalRateEqualsReducedRate() {
        // Test Case 4: Normal Rate Equals Reduced Rate
        assertThrows(IllegalArgumentException.class, () -> new Rate(
                CarParkKind.VISITOR, new BigDecimal("4.0"), new BigDecimal("4.0"),
                new ArrayList<Period>() {{ add(new Period(8, 12)); }},
                new ArrayList<Period>() {{ add(new Period(12, 14)); }}
        ));
    }

    @Test
    public void testRateNormalRateGreaterThanReducedRate() {
        // Test Case 5: Normal Rate Greater Than Reduced Rate
        Rate rate = new Rate(
                CarParkKind.STUDENT, new BigDecimal("6.0"), new BigDecimal("3.0"),
                new ArrayList<Period>() {{ add(new Period(8, 12)); }},
                new ArrayList<Period>() {{ add(new Period(12, 14)); }}
        );
        assertInstanceOf(Rate.class, rate);
    }

    @Test
    public void testRateNormalRateLessThanReducedRate() {
        // Test Case 6: Normal Rate Less Than Reduced Rate
        assertThrows(IllegalArgumentException.class, () -> new Rate(
                CarParkKind.STAFF, new BigDecimal("3.0"), new BigDecimal("5.0"),
                new ArrayList<Period>() {{ add(new Period(8, 12)); }},
                new ArrayList<Period>() {{ add(new Period(12, 14)); }}
        ));
    }

    @Test
    public void testRateOverlappingNormalPeriods() {
        // Test Case 7: Overlapping Normal Periods
        assertThrows(IllegalArgumentException.class, () -> new Rate(
                CarParkKind.STUDENT, new BigDecimal("7.0"), new BigDecimal("4.0"),
                new ArrayList<Period>() {{
                    add(new Period(8, 12));
                    add(new Period(11, 14)); // Overlapping periods
                }},
                new ArrayList<Period>() {{
                    add(new Period(12, 14));
                    add(new Period(16, 18));
                }}
        ));
    }

    @Test
    public void testRateOverlappingReducedPeriods() {
        // Test Case 8: Overlapping Reduced Periods
        Rate rate = new Rate(
                CarParkKind.STUDENT, new BigDecimal("7.0"), new BigDecimal("4.0"),
                new ArrayList<Period>() {{ add(new Period(8, 12)); }},
                new ArrayList<Period>() {{ add(new Period(12, 14)); add(new Period(15, 16)); }}
        );
        assertInstanceOf(Rate.class, rate);
    }

    @Test
    public void testRateNormalPeriodEndHourExceeds24() {
        // Test Case 9: Normal Period End Hour Exceeds 24
        assertThrows(IllegalArgumentException.class, () -> new Rate(
                CarParkKind.STUDENT, new BigDecimal("7.0"), new BigDecimal("4.0"),
                new ArrayList<Period>() {{ add(new Period(8, 12)); add(new Period(14, 26)); }},
                new ArrayList<Period>() {{ add(new Period(12, 14)); add(new Period(16, 18)); }}
        ));
    }

    @Test
    public void testRateReducedPeriodStartHourLessThan0() {
        // Test Case 10: Reduced Period Start Hour Less Than 0
        assertThrows(IllegalArgumentException.class, () -> new Rate(
                CarParkKind.STUDENT, new BigDecimal("7.0"), new BigDecimal("4.0"),
                new ArrayList<Period>() {{ add(new Period(8, 12)); }},
                new ArrayList<Period>() {{ add(new Period(-1, 14)); add(new Period(16, 18)); }}
        ));
    }

    @Test
    public void testRateEmptyNormalAndReducedPeriods() {
        // Test Case 11: Empty Normal and Reduced Periods Lists
        Rate rate = new Rate(
                CarParkKind.VISITOR, new BigDecimal("6.0"), new BigDecimal("4.0"),
                new ArrayList<>(), new ArrayList<>()
        );
        assertInstanceOf(Rate.class, rate);
    }

    @Test
    public void testRateOverlappingNormalAndReducedPeriods() {
        // Test Case 12: Overlapping Normal and Reduced Periods
        assertThrows(IllegalArgumentException.class, () -> new Rate(
                CarParkKind.MANAGEMENT, new BigDecimal("7.0"), new BigDecimal("4.0"),
                new ArrayList<Period>() {{ add(new Period(8, 12)); add(new Period(10, 14)); }},
                new ArrayList<Period>() {{ add(new Period(11, 14)); add(new Period(12, 15)); }}
        ));
    }

    @Test
    public void testRateEndHourExceeds24AndStartHourLessThan0() {
        // Test Case 13: Normal Period End Hour Exceeds 24 and Reduced Period Start Hour Less Than 0
        assertThrows(IllegalArgumentException.class, () -> new Rate(
                CarParkKind.STUDENT, new BigDecimal("7.0"), new BigDecimal("4.0"),
                new ArrayList<Period>() {{ add(new Period(8, 26)); }},
                new ArrayList<Period>() {{ add(new Period(-1, 14)); add(new Period(16, 18)); }}
        ));
    }


    // Test for Calculate Method
    @Test
    public void testCalculateNormalRateOnly() {
        // Test Case 14: Normal Rate Only
        Rate rate1 = new Rate(CarParkKind.STAFF, new BigDecimal("5.0"), new BigDecimal("0.0"),
                new ArrayList<Period>() {{ add(new Period(8, 12)); }},
                new ArrayList<Period>()
        );
        BigDecimal result = rate1.calculate(new Period(8, 12));
        assertEquals(new BigDecimal("20.0"), result);
    }

    @Test
    public void testCalculateReducedRateOnly() {
        // Test Case 15: Reduced Rate Only
        Rate rate2 = new Rate(CarParkKind.STUDENT, new BigDecimal("4.0"), new BigDecimal("3.0"),
                new ArrayList<Period>(),
                new ArrayList<Period>() {{ add(new Period(14, 16)); }}
        );
        BigDecimal result = rate2.calculate(new Period(14, 16));
        assertEquals(new BigDecimal("6.0"), result);
    }

    @Test
    public void testCalculateMixedRateAndPeriods() {
        // Test Case 16: Mixed Rate and Periods
        Rate rate3 = new Rate(CarParkKind.MANAGEMENT, new BigDecimal("6.0"), new BigDecimal("4.0"),
                new ArrayList<Period>() {{ add(new Period(8, 12)); add(new Period(14, 16)); }},
                new ArrayList<Period>() {{ add(new Period(12, 14)); add(new Period(16, 18)); }}
        );
        BigDecimal result = rate3.calculate(new Period(10, 15));
        assertEquals(new BigDecimal("26.0"), result);
    }

    @Test
    public void testCalculateNoRateAndEmptyPeriods() {
        // Test Case 17: No Rate and Empty Periods
        Rate rate4 = new Rate(CarParkKind.VISITOR, new BigDecimal("1.0"), new BigDecimal("0.0"),
                new ArrayList<Period>(),
                new ArrayList<Period>()
        );
        BigDecimal expected = new BigDecimal("0.0");
        BigDecimal result = rate4.calculate(new Period(8, 12));
        assertEquals(expected.compareTo(result), 0);
    }

    @Test
    public void testCalculatePartiallyOverlappingPeriods() {
        // Test Case 18: Partially Overlapping Periods
        Rate rate5 = new Rate(CarParkKind.STAFF, new BigDecimal("5.0"), new BigDecimal("3.0"),
                new ArrayList<Period>() {{ add(new Period(8, 12)); add(new Period(14, 16)); }},
                new ArrayList<Period>() {{ add(new Period(12, 14)); add(new Period(16, 18)); }}
        );
        BigDecimal result = rate5.calculate(new Period(11, 15));
        assertEquals(new BigDecimal("16.0"), result);
    }

    @Test
    public void testCalculateMaximumNormalRate() {
        // Test Case 19: Maximum Normal Rate
        Rate rate6 = new Rate(CarParkKind.STUDENT, new BigDecimal("999.99"), new BigDecimal("9.99"),
                new ArrayList<Period>() {{ add(new Period(8, 12)); }},
                new ArrayList<Period>() {{ add(new Period(13, 16)); }}
        );
        BigDecimal result = rate6.calculate(new Period(8, 11));
        assertEquals(new BigDecimal("2999.97"), result);
    }

    @Test
    public void testCalculateMinimumReducedRate() {
        // Test Case 20: Minimum Reduced Rate
        Rate rate7 = new Rate(CarParkKind.MANAGEMENT, new BigDecimal("6.0"), new BigDecimal("0.01"),
                new ArrayList<Period>() {{ add(new Period(8, 12)); }},
                new ArrayList<Period>() {{ add(new Period(14, 16)); }}
        );
        BigDecimal result = rate7.calculate(new Period(14, 16));
        assertEquals(new BigDecimal("0.02"), result);
    }


    // White box test cases added for the Period class
    @Test
    public void testPeriodConstructorInvalidStartAndEndHoursAboveTwentyFour() {
        // Increased branch coverage for Period class by 4%
        assertThrows(IllegalArgumentException.class, () -> new Period(25, 28));
    }


    // White box test cases added for the Rate class
    @Test
    public void testRateConstructorNullPeriods() {
        // Increased branch coverage for Rate class by 3%
        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STAFF, new BigDecimal("5.0"), new BigDecimal("3.0"), null, null));
    }

    @Test
    public void testRateConstructorNegativeRates() {
        // Increased branch coverage for Rate class by 2%
        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STAFF, new BigDecimal("-5.0"), new BigDecimal("-3.0"), new ArrayList<>(), new ArrayList<>()));
    }

    @Test
    public void testRateConstructorNullNormalPeriods() {
        // Increased branch coverage for Rate class by 3%
        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STAFF, new BigDecimal("5.0"), new BigDecimal("3.0"), null, new ArrayList<>()));
    }

    @Test
    public void testRateConstructorNegativeReducedRate() {
        // Increased branch coverage for Rate class by 3%
        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STAFF, new BigDecimal("5.0"), new BigDecimal("-3.0"), new ArrayList<>(), new ArrayList<>()));
    }

    @Test
    public void testRateConstructorNullRates() {
        // Increased branch coverage for Rate class by 2%
        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STAFF, null, null, new ArrayList<>(), new ArrayList<>()));
    }

    @Test
    public void testRateConstructorInvalidOverlappingPeriods() {
        // Increased branch coverage for Rate class by 3%
        assertThrows(IllegalArgumentException.class, () -> new Rate(
                CarParkKind.STUDENT, new BigDecimal("7.0"), new BigDecimal("4.0"),
                new ArrayList<>() {{
                    add(new Period(8, 12));
                }},
                new ArrayList<>() {{
                    add(new Period(11, 14));
                }}
        ));
    }

    @Test
    public void testRateConstructorReducedRateNull() {
        // Increased branch coverage for Rate class by 3%
        assertThrows(IllegalArgumentException.class, () -> new Rate(
                CarParkKind.STAFF, BigDecimal.valueOf(5.0), null,
                new ArrayList<>(),
                new ArrayList<>() {{
                    add(new Period(10, 14));
                }}
        ));
    }


    /*-------------------------------------------------------------------------------------------------
    ---------------------------------------- Task 3 TDD Tests -----------------------------------------
    -------------------------------------------------------------------------------------------------*/

    @Test
    public void testVisitorPay2_5() {
        // Creating a Visitor Rate object with an hourly normal rate of 5 and reduced rate of 2.5
        Rate visitorRate = new Rate(CarParkKind.VISITOR, BigDecimal.valueOf(5), BigDecimal.valueOf(2.5),
                new ArrayList<Period>() {{
                    add(new Period(6, 10));
                }},
                new ArrayList<Period>() {{
                    add(new Period(10, 14));
                }}
        );
        BigDecimal periodStay = visitorRate.calculate(new Period(8, 12));
        assertEquals(new BigDecimal("2.50"), periodStay); // Expected: Pay 2.50
    }

    @Test
    public void testManagementPay5_0() {
        Rate managementRate  = new Rate(CarParkKind.MANAGEMENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2),
                new ArrayList<Period>() {{
                    add(new Period(4, 6));
                }},
                new ArrayList<Period>() {{
                    add(new Period(10, 14));
                }}
        );
        BigDecimal periodStay = managementRate.calculate(new Period(8, 11));
        assertEquals(new BigDecimal("5.00").setScale(2), periodStay.setScale(2)); // Expected: Pay 5.00
    }
}
