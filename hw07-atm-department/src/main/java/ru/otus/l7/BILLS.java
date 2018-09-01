package ru.otus.l7;

import java.util.Arrays;
import java.util.List;

public enum BILLS {

    BILL_ONE(50), BILL_TWO(20), BILL_THREE(5), BILL_FOUR(1);
    private int value;

    private BILLS(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int i) {
        this.value = i;
    }

    public static List<Integer> getValues() {
        Integer[] array = {BILL_ONE.getValue(), BILL_TWO.getValue(), BILL_THREE.getValue(), BILL_FOUR.getValue()};
        List<Integer> list = Arrays.asList(array);
        return list;
    }

}
