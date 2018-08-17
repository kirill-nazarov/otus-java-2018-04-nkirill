package ru.otus.l7;

public class CashCartridge {


    private final int DEFAULT_BILL_NUMBER = 100;

    private BILLS bill;

    private int billNumber = DEFAULT_BILL_NUMBER;

    public CashCartridge() {
    }

    public CashCartridge(CashCartridge cartridge) {
        this.bill = cartridge.bill;
        this.billNumber = cartridge.billNumber;
    }

    public CashCartridge(BILLS bill) {
        this.bill = bill;
    }

    public BILLS getBill() {
        return bill;
    }

    public void setBill(BILLS bill) {
        this.bill = bill;
    }

    public int getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(int billNumber) {
        this.billNumber = billNumber;
    }

    public int getCashCartridgeValue() {
        return bill.getValue() * billNumber;
    }
}
