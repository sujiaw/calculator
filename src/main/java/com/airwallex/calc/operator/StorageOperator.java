package com.airwallex.calc.operator;

import com.airwallex.calc.storage.Storage;

abstract public class StorageOperator extends Operator {

    abstract public void operate(Storage current, Storage history);

}
