package com.airwallex.calc.exception;

public class DivideByZeroException extends CalcException {

    public DivideByZeroException() {
        errorCode = "000003";
        errorMsg = "divide by zero";
    }

}
