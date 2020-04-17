package com.airwallex.calc;

import com.airwallex.calc.calculator.RpnCalculator;
import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class App {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        RpnCalculator calculator = context.getBean(RpnCalculator.class);
        System.out.println("Please enter number or operator separated by whitespace, enter 'exit' to quit.");
        try (Scanner scanner = new Scanner(System.in)) {
            String line;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if ("exit".equalsIgnoreCase(line)) {
                    break;
                }
                try {
                    calculator.compute(line);
                    calculator.print();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    break;
                }
            }
        }
    }

}
