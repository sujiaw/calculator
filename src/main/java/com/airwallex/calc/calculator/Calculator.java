package com.airwallex.calc.calculator;

import com.airwallex.calc.storage.Storage;
import com.airwallex.calc.operator.Operator;
import com.airwallex.calc.operator.OperatorHub;
import org.springframework.beans.factory.annotation.Autowired;

abstract public class Calculator {

    protected Storage current = new Storage();
    protected Storage history = new Storage();

    @Autowired
    protected OperatorHub operatorHub;

    abstract public void compute(String input);
    abstract public void print();
    abstract protected void processOperator(Operator operator);
    abstract protected String[] parse(String input);

    protected boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

}
