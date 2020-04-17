package com.airwallex.calc.operator;

import com.airwallex.calc.storage.Data;
import com.airwallex.calc.storage.Storage;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class UndoOperator extends StorageOperator {

    public UndoOperator() {
        symbol = "undo";
    }

    @Override
    public void operate(Storage current, Storage history) {
        if (current != null && current.size() > 0) {
            current.getLast();
        }

        if (current != null && history != null && history.size() > 0) {
            Data<BigDecimal> last = history.getLast();
            List<BigDecimal> values = last.get();
            for(int i = 0; i < values.size(); i++) {
                Data<BigDecimal> temp = new Data<>();
                temp.append(values.get(i));
                current.addLast(temp);
            }
        }
    }

}
