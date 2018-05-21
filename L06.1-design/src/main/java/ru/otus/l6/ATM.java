package ru.otus.l6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class ATM {

    private static final int BILL_ONE = 50;
    private static final int BILL_TWO = 20;
    private static final int BILL_THREE = 5;
    private static final int BILL_FOUR = 1;

    private CashCartridge cartridge1;
    private CashCartridge cartridge2;
    private CashCartridge cartridge3;
    private CashCartridge cartridge4;


    public ATM() {
        cartridge1 = new CashCartridge();
        cartridge2 = new CashCartridge();
        cartridge3 = new CashCartridge();
        cartridge4 = new CashCartridge();
        cartridge1.setBillValue(BILL_ONE);
        cartridge2.setBillValue(BILL_TWO);
        cartridge3.setBillValue(BILL_THREE);
        cartridge4.setBillValue(BILL_FOUR);

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
        Integer[] array = {BILL_ONE, BILL_TWO, BILL_THREE, BILL_FOUR};
        List<Integer> list = Arrays.asList(array);
        return list;
    }


    public String withDrawCash(int amount) {
        int billOne = 0;
        int billTwo = 0;
        int billThree = 0;
        int billFour = 0;

        int remainder = amount;
        List<Integer> numbers = Arrays.asList(BILL_ONE, BILL_TWO, BILL_THREE, BILL_FOUR);
        List<Integer> change = new ArrayList<>();
        for (int number : numbers) {
            while (remainder >= number) {
                remainder = remainder - number;
                change.add(number);
            }
        }
        for (int changeBill : change) {
            if (changeBill == BILL_ONE) {
                cartridge1.setBillNumber(cartridge1.getBillNumber() - 1);
                billOne++;
            }
            if (changeBill == BILL_TWO) {
                cartridge1.setBillNumber(cartridge1.getBillNumber() - 1);
                billTwo++;
            }
            if (changeBill == BILL_THREE) {
                cartridge1.setBillNumber(cartridge1.getBillNumber() - 1);
                billThree++;
            }
            if (changeBill == BILL_FOUR) {
                cartridge1.setBillNumber(cartridge1.getBillNumber() - 1);
                billFour++;
            }
        }

        return BILL_ONE + " x " + billOne + ", " + BILL_TWO + " x " + billTwo + ", " + BILL_THREE +
                " x " + billThree + ", " + BILL_FOUR + " x " + billFour;
    }
}
