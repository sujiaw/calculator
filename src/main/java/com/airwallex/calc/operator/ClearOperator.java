package com.airwallex.calc.operator;

import com.airwallex.calc.storage.Storage;
import org.springframework.stereotype.Component;

@Component
public class ClearOperator extends StorageOperator {

    public ClearOperator() {
        symbol = "clear";
    }

    @Override
    public void operate(Storage current, Storage history) {
        if (current != null) {
            current.clear();
        }

        if (history != null) {
            history.clear();
        }
    }

}
