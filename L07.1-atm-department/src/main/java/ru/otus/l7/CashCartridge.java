package ru.otus.l7;

public class CashCartridge {


    private final int DEFAULT_BILL_NUMBER = 100;

    private int billValue;
    private int billNumber = DEFAULT_BILL_NUMBER;

    public CashCartridge() {
    }

    public CashCartridge(CashCartridge cartridge) {
        this.billValue = cartridge.billValue;
        this.billNumber = cartridge.billNumber;
    }

    public CashCartridge(BILLS bills) {
        this.billValue = bills.getValue();
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
