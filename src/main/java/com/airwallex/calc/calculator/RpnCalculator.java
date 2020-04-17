package com.airwallex.calc.calculator;

import com.airwallex.calc.exception.DivideByZeroException;
import com.airwallex.calc.exception.InsufficientParameterException;
import com.airwallex.calc.exception.SqrtNegativeException;
import com.airwallex.calc.exception.UnknownOperatorException;
import com.airwallex.calc.operator.ArithmeticOperator;
import com.airwallex.calc.operator.BinaryOperator;
import com.airwallex.calc.operator.StorageOperator;
import com.airwallex.calc.operator.Operator;
import com.airwallex.calc.operator.UnaryOperator;
import com.airwallex.calc.storage.Data;
import java.math.BigDecimal;
import java.util.Iterator;
import org.springframework.stereotype.Component;

@Component
public class RpnCalculator extends Calculator {

    public void compute(String input) {
        String[] list = parse(input);
        for (int i = 0; i < list.length; i++) {
            if (operatorHub.isOperator(list[i])) {
                try {
                    Operator operator = operatorHub.getOperator(list[i]);
                    processOperator(operator);
                } catch (InsufficientParameterException e) {
                    System.out.println("operator " + list[i] + " (position: " + (2 * i + 1)
                        + "): insufficient parameters");
                    break;
                } catch (DivideByZeroException e) {
                    System.out.println("operator " + list[i] + " (position: " + (2 * i + 1)
                        + "): divide by 0 is invalid");
                    break;
                } catch (SqrtNegativeException e) {
                    System.out.println("operator " + list[i] + " (position: " + (2 * i + 1)
                        + "): square root of negative number is invalid");
                    break;
                } catch (UnknownOperatorException e) {
                    System.out.println("unknown operator: " + list[i]);
                    break;
                }
            } else if (isNumeric(list[i])) {
                Data<BigDecimal> num = new Data<>();
                num.append(new BigDecimal(list[i]));
                current.addLast(num);
                history.addLast(new Data<BigDecimal>());//save an empty data so that undo will do nothing
            } else {
                System.out.println("invalid symbol: " + list[i]);
                break;
            }
        }
    }

    public void print() {
        System.out.println("Stack: " + printToString());
    }

    public String printToString() {
        String result = "";
        Iterator iterator = current.iterator();
        while (iterator.hasNext()) {
            BigDecimal num = ((Data<BigDecimal>) iterator.next()).get().get(0);
            result += num.setScale(10, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString();
            if (iterator.hasNext()) {
                result += " ";
            }
        }
        return result;
    }

    protected void processOperator(Operator operator) throws InsufficientParameterException, DivideByZeroException, UnknownOperatorException {
        if (operator instanceof UnaryOperator) {
            if (current.size() < 1) {
                throw new InsufficientParameterException();
            }
            Data<BigDecimal> input = current.getLast();
            BigDecimal num;
            try {
                num = ((ArithmeticOperator) operator).operate(input);
            } catch (Exception e) {
                current.addLast(input);
                throw e;
            }
            Data<BigDecimal> result = new Data<>();
            result.append(num);
            history.addLast(input);
            current.addLast(result);
        } else if (operator instanceof BinaryOperator) {
            if (current.size() < 2) {
                throw new InsufficientParameterException();
            }
            Data<BigDecimal> rightData = ((Data<BigDecimal>) current.getLast());
            BigDecimal right = rightData.get().get(0);
            Data<BigDecimal> leftData = ((Data<BigDecimal>) current.getLast());
            BigDecimal left = leftData.get().get(0);
            Data<BigDecimal> input = new Data<>();
            input.append(left);
            input.append(right);
            BigDecimal num;
            try {
                num = ((ArithmeticOperator) operator).operate(input);
            } catch (Exception e) {
                current.addLast(leftData);
                current.addLast(rightData);
                throw e;
            }
            Data<BigDecimal> result = new Data<>();
            result.append(num);
            history.addLast(input);
            current.addLast(result);
        } else if (operator instanceof StorageOperator) {
            ((StorageOperator) operator).operate(current, history);
        } else {
            throw new UnknownOperatorException();
        }
    }

    protected String[] parse(String input) {
        //for RPN calculator, formula translation is not needed
        return input.trim().split(" ");
    }

}
