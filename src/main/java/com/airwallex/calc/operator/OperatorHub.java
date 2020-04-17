package com.airwallex.calc.operator;

import com.airwallex.calc.exception.UnknownOperatorException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class OperatorHub {

    private Map<String, Operator> container = new HashMap<>();

    public boolean isOperator(String str) {
        return container.containsKey(str);
    }

    public Operator getOperator(String str) throws UnknownOperatorException {
        if (isOperator(str)) {
            return container.get(str);
        }
        throw new UnknownOperatorException();
    }

    public void register(String symbol, Operator operator) {
        container.put(symbol, operator);
    }

}
