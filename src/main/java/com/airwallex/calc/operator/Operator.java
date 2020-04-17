package com.airwallex.calc.operator;


import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

abstract public class Operator {

    protected String symbol;

    @Autowired
    protected OperatorHub operatorHub;

    public String getSymbol() {
        return symbol;
    }

    //register self into OperatorHub on start
    @PostConstruct
    protected void register() {
        operatorHub.register(symbol, this);
    }

}
