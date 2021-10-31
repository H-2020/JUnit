package be.intec.les109new;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



class CalculatorTest {
    //class level field..
    Calculator calculator=new Calculator();

    @Test
    void add() {

        double expected=20.00+5.00;
        double actual=calculator.add(20.00,5.00);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void min() {
        double expected=20.00-5.00;
        double actual=calculator.min(20.00,5.00);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void div() {

        double expected=20.00/5.00;
        double actual=calculator.div(20.00,5.00);
        Assertions.assertEquals(expected,actual);

    }

    @Test
    void mul() {
        double expected=20.00*5.00;
        double actual=calculator.mul(20.00,5.00);
        Assertions.assertEquals(expected,actual);

    }
}