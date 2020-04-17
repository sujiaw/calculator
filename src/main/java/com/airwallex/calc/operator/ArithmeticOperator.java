package com.airwallex.calc.operator;

import com.airwallex.calc.storage.Data;
import java.math.BigDecimal;

abstract public class ArithmeticOperator extends Operator {

    abstract public BigDecimal operate(Data<BigDecimal> input);

}
