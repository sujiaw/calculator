package com.airwallex.calc.operator;

import com.airwallex.calc.exception.InsufficientParameterException;
import com.airwallex.calc.exception.SqrtNegativeException;
import com.airwallex.calc.storage.Data;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class SqrtOperator extends UnaryOperator {

    public SqrtOperator() {
        symbol = "sqrt";
    }

    @Override
    public BigDecimal operate(Data<BigDecimal> input) throws InsufficientParameterException, SqrtNegativeException {
        if (input.size() != 1) {
            throw new InsufficientParameterException();
        }
        List<BigDecimal> values = input.get();
        BigDecimal operand = values.get(0);

        if (operand.compareTo(BigDecimal.ZERO) < 0) {
            throw new SqrtNegativeException();
        }

        return new BigDecimal(Math.sqrt(operand.doubleValue())); //TODO lost precision
    }

}
