package com.airwallex.calc.unit.calculator;

import com.airwallex.calc.App;
import com.airwallex.calc.calculator.RpnCalculator;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.testng.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = App.class, loader = AnnotationConfigContextLoader.class)
public class RpnCalculatorTest {

    @Autowired
    private RpnCalculator rpnCalculator;

    @After
    public void cleanUp() {
        rpnCalculator.compute("clear");
    }

    @Test
    public void testAddOperator() {
        rpnCalculator.compute("4 -3 +");
        Assert.assertEquals(rpnCalculator.printToString(), "1");

        rpnCalculator.compute("clear");

        rpnCalculator.compute("4.3000 5.00 +");
        Assert.assertEquals(rpnCalculator.printToString(), "9.3");

        rpnCalculator.compute("clear");

        rpnCalculator.compute("5.123456789153 -1 +");
        Assert.assertEquals(rpnCalculator.printToString(), "4.1234567892");

        rpnCalculator.compute("4 +");
        Assert.assertEquals(rpnCalculator.printToString(), "8.1234567892");

        rpnCalculator.compute("+");
        Assert.assertEquals(rpnCalculator.printToString(), "8.1234567892");

        rpnCalculator.compute("5 unknown");
        Assert.assertEquals(rpnCalculator.printToString(), "8.1234567892 5");
    }

    @Test
    public void testSubtractOperator() {
        rpnCalculator.compute("3 4 -");
        Assert.assertEquals(rpnCalculator.printToString(), "-1");

        rpnCalculator.compute("clear");

        rpnCalculator.compute("5.3000 4.00 -");
        Assert.assertEquals(rpnCalculator.printToString(), "1.3");

        rpnCalculator.compute("clear");

        rpnCalculator.compute("5.123456789159 1.123456789050 -");
        Assert.assertEquals(rpnCalculator.printToString(), "4.0000000001");

        rpnCalculator.compute("0.0000000001 -");
        Assert.assertEquals(rpnCalculator.printToString(), "4");
    }

    @Test
    public void testMultiplyOperator() {
        rpnCalculator.compute("4 3 *");
        Assert.assertEquals(rpnCalculator.printToString(), "12");

        rpnCalculator.compute("clear");

        rpnCalculator.compute("4.30 3.0 *");
        Assert.assertEquals(rpnCalculator.printToString(), "12.9");

        rpnCalculator.compute("clear");

        rpnCalculator.compute("4.30 -0.0 *");
        Assert.assertEquals(rpnCalculator.printToString(), "0");
    }

    @Test
    public void testDivideOperator() {
        rpnCalculator.compute("4 2 /");
        Assert.assertEquals(rpnCalculator.printToString(), "2");

        rpnCalculator.compute("clear");

        rpnCalculator.compute("4.30 2.0 /");
        Assert.assertEquals(rpnCalculator.printToString(), "2.15");

        rpnCalculator.compute("clear");

        rpnCalculator.compute("1 -3 /");
        Assert.assertEquals(rpnCalculator.printToString(), "-0.3333333333");

        rpnCalculator.compute("clear");

        rpnCalculator.compute("-1 -3 /");
        Assert.assertEquals(rpnCalculator.printToString(), "0.3333333333");

        rpnCalculator.compute("clear");

        rpnCalculator.compute("-1 0.0 /");
        Assert.assertEquals(rpnCalculator.printToString(), "-1 0");
    }

    @Test
    public void testSqrtOperator() {
        rpnCalculator.compute("4.0 sqrt");
        Assert.assertEquals(rpnCalculator.printToString(), "2");

        rpnCalculator.compute("clear");

        rpnCalculator.compute("-4.0 sqrt");
        Assert.assertEquals(rpnCalculator.printToString(), "-4");

        rpnCalculator.compute("clear");

        rpnCalculator.compute("-0.0 sqrt");
        Assert.assertEquals(rpnCalculator.printToString(), "0");
    }

    @Test
    public void testUndoOperator() {
        rpnCalculator.compute("4.0 sqrt");
        rpnCalculator.compute("undo");
        Assert.assertEquals(rpnCalculator.printToString(), "4");
        rpnCalculator.compute("undo");
        Assert.assertEquals(rpnCalculator.printToString(), "");

        rpnCalculator.compute("clear");

        rpnCalculator.compute("3 4 5 6 + + unknown");
        rpnCalculator.compute("undo undo undo");
        Assert.assertEquals(rpnCalculator.printToString(), "3 4 5");
    }

    @Test
    public void testComplex() {
        rpnCalculator.compute("1 2 3 4 5");
        Assert.assertEquals(rpnCalculator.printToString(), "1 2 3 4 5");
        rpnCalculator.compute("* * * *");
        Assert.assertEquals(rpnCalculator.printToString(), "120");

        rpnCalculator.compute("clear");

        rpnCalculator.compute("1 2 3 4 -5");
        Assert.assertEquals(rpnCalculator.printToString(), "1 2 3 4 -5");
        rpnCalculator.compute("* * * *");
        Assert.assertEquals(rpnCalculator.printToString(), "-120");

        rpnCalculator.compute("clear");

        rpnCalculator.compute("5 4 3 2");
        rpnCalculator.compute("undo undo *");
        Assert.assertEquals(rpnCalculator.printToString(), "20");
        rpnCalculator.compute("5 *");
        Assert.assertEquals(rpnCalculator.printToString(), "100");
        rpnCalculator.compute("undo");
        Assert.assertEquals(rpnCalculator.printToString(), "20 5");

        rpnCalculator.compute("clear");

        rpnCalculator.compute("2 sqrt");
        Assert.assertEquals(rpnCalculator.printToString(), "1.4142135624");

        rpnCalculator.compute("clear");

        rpnCalculator.compute("7 12 2 /");
        Assert.assertEquals(rpnCalculator.printToString(), "7 6");
        rpnCalculator.compute("*");
        Assert.assertEquals(rpnCalculator.printToString(), "42");
        rpnCalculator.compute("4 /");
        Assert.assertEquals(rpnCalculator.printToString(), "10.5");

        rpnCalculator.compute("clear");

        rpnCalculator.compute("1 2 3 4 5");
        Assert.assertEquals(rpnCalculator.printToString(), "1 2 3 4 5");
        rpnCalculator.compute("*");
        Assert.assertEquals(rpnCalculator.printToString(), "1 2 3 20");
        rpnCalculator.compute("clear 3 4 -");
        Assert.assertEquals(rpnCalculator.printToString(), "-1");

        rpnCalculator.compute("clear");

        rpnCalculator.compute("1 2 3 * 5 + * * 6 5");
        Assert.assertEquals(rpnCalculator.printToString(), "11");
    }

}
