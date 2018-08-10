package ru.otus.l6;

import java.util.*;

public class ATM {

    private CashCartridge cartridge1;
    private CashCartridge cartridge2;
    private CashCartridge cartridge3;
    private CashCartridge cartridge4;


    public ATM() {
        cartridge1 = new CashCartridge();
        cartridge2 = new CashCartridge();
        cartridge3 = new CashCartridge();
        cartridge4 = new CashCartridge();
        cartridge1.setBillValue(BILLS.BILL_ONE.getValue());
        cartridge2.setBillValue(BILLS.BILL_TWO.getValue());
        cartridge3.setBillValue(BILLS.BILL_THREE.getValue());
        cartridge4.setBillValue(BILLS.BILL_FOUR.getValue());

    }

    public void fillWithInitialValues(int billOneNumber, int billTwoNumber, int billThreeNumber, int billFourNumber) {
        cartridge1.setBillNumber(billOneNumber);
        cartridge2.setBillNumber(billTwoNumber);
        cartridge3.setBillNumber(billThreeNumber);
        cartridge4.setBillNumber(billFourNumber);
    }

    public void depositCash(int billValue, int billNumber) {
        switch (billValue) {
            case 1:
                cartridge1.setBillNumber(cartridge1.getBillNumber() + billNumber);
                break;
            case 2:
                cartridge2.setBillNumber(cartridge2.getBillNumber() + billNumber);
                break;
            case 3:
                cartridge3.setBillNumber(cartridge3.getBillNumber() + billNumber);
                break;
            case 4:
                cartridge4.setBillNumber(cartridge4.getBillNumber() + billNumber);
                break;
        }

    }

    public int getATMbalance() {
        return cartridge1.getCashCartridgeValue() + cartridge2.getCashCartridgeValue() + cartridge3.getCashCartridgeValue()
                + cartridge4.getCashCartridgeValue();
    }

    public List<Integer> getBillValues() {
        return BILLS.getValues();
    }


    public Map<Integer, Integer> withDrawCash(int amount) {
        Map<Integer, Integer> bills = new HashMap<>();
        int billOne = 0;
        int billTwo = 0;
        int billThree = 0;
        int billFour = 0;

        int remainder = amount;
        List<Integer> billsValues = getBillValues();
        List<Integer> change = new ArrayList<>();
        for (int billValue : billsValues) {
            while (remainder >= billValue) {
                remainder = remainder - billValue;
                change.add(billValue);
            }
        }
        for (int changeBill : change) {
            if (changeBill == BILLS.BILL_ONE.getValue()) {
                cartridge1.setBillNumber(cartridge1.getBillNumber() - 1);
                billOne++;
            }
            if (changeBill == BILLS.BILL_TWO.getValue()) {
                cartridge2.setBillNumber(cartridge2.getBillNumber() - 1);
                billTwo++;
            }
            if (changeBill == BILLS.BILL_THREE.getValue()) {
                cartridge3.setBillNumber(cartridge3.getBillNumber() - 1);
                billThree++;
            }
            if (changeBill == BILLS.BILL_FOUR.getValue()) {
                cartridge4.setBillNumber(cartridge4.getBillNumber() - 1);
                billFour++;
            }
        }

        bills.put(BILLS.BILL_ONE.getValue(), billOne);
        bills.put(BILLS.BILL_TWO.getValue(), billTwo);
        bills.put(BILLS.BILL_THREE.getValue(), billThree);
        bills.put(BILLS.BILL_FOUR.getValue(), billFour);

        return bills;
    }
}
