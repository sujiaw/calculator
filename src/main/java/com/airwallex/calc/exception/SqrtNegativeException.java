package com.airwallex.calc.exception;

/**
 * Created by sujiaw on 2020/4/17.
 */
public class SqrtNegativeException extends CalcException {

    public SqrtNegativeException() {
        errorCode = "000004";
        errorMsg = "square root of negative number";
    }

}
