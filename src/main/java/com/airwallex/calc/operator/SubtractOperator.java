package com.airwallex.calc.operator;

import com.airwallex.calc.exception.InsufficientParameterException;
import com.airwallex.calc.storage.Data;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class SubtractOperator extends BinaryOperator {

    public SubtractOperator() {
        symbol = "-";
    }

    @Override
    public BigDecimal operate(Data<BigDecimal> input) throws InsufficientParameterException {
        if (input.size() != 2) {
            throw new InsufficientParameterException();
        }
        List<BigDecimal> values = input.get();
        BigDecimal left = values.get(0);
        BigDecimal right = values.get(1);
        return left.subtract(right);
    }

}
