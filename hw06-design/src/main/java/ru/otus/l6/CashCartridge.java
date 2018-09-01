package ru.otus.l6;

public class CashCartridge {


    private int billValue;
    private int billNumber;

    public CashCartridge() {
    }


    public int getBillValue() {
        return billValue;
    }

    public void setBillValue(int billValue) {
        this.billValue = billValue;
    }

    public int getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(int billNumber) {
        this.billNumber = billNumber;
    }

    public int getCashCartridgeValue() {
        return billValue * billNumber;
    }
}
