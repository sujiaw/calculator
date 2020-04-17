package com.airwallex.calc.exception;

public class UnknownOperatorException extends CalcException {

    public UnknownOperatorException() {
        errorCode = "000002";
        errorMsg = "unknown operator";
    }
}
