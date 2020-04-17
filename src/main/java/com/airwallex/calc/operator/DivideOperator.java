package com.airwallex.calc.operator;

import com.airwallex.calc.exception.DivideByZeroException;
import com.airwallex.calc.exception.InsufficientParameterException;
import com.airwallex.calc.storage.Data;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class DivideOperator extends BinaryOperator {

    public DivideOperator() {
        symbol = "/";
    }

    @Override
    public BigDecimal operate(Data<BigDecimal> input) throws InsufficientParameterException, DivideByZeroException {
        if (input.size() != 2) {
            throw new InsufficientParameterException();
        }
        List<BigDecimal> values = input.get();
        BigDecimal left = values.get(0);
        BigDecimal right = values.get(1);
        if (right.compareTo(BigDecimal.ZERO) == 0) {
            throw new DivideByZeroException();
        }
        return left.divide(right, 15, BigDecimal.ROUND_HALF_UP);
    }

}
