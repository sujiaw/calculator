package com.airwallex.calc.exception;

public class InsufficientParameterException extends CalcException {

    public InsufficientParameterException() {
        errorCode = "000001";
        errorMsg = "insufficient parameters";
    }

}
