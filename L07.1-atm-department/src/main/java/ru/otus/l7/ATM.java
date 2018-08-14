package ru.otus.l7;

import java.util.*;

public class ATM {

    private List<CashCartridge> cartridges;


    public ATM(List<CashCartridge> cartridges) {
        this.cartridges = cartridges;
    }


    public void depositCash(int billValue, int billNumber) {
        for (CashCartridge cartridge : cartridges) {
            if (cartridge.getBillValue() == billValue) {
                cartridge.setBillNumber(cartridge.getBillNumber() + billNumber);
            }
        }

    }

    public int getATMbalance() {
        int balance = 0;
        for (CashCartridge cartridge : cartridges) {
            int cartridgeBalance = cartridge.getBillNumber() * cartridge.getBillValue();
            balance = balance + cartridgeBalance;
        }
        return balance;
    }

    private boolean checkAmountToWithdraw(int amount) {
        int availableAmount = 0;
        for (CashCartridge cartridge : cartridges) {
            availableAmount = availableAmount + cartridge.getCashCartridgeValue();
        }
        return amount > availableAmount;
    }

    public Map<Integer, Integer> withDrawCash(int amount) {
        if (checkAmountToWithdraw(amount)) return null;
        Map<Integer, Integer> bills = new HashMap<>();
        int billOne = 0;
        int billTwo = 0;
        int billThree = 0;
        int billFour = 0;
        int remainder = amount;
        List<Integer> billsValues = BILLS.getValues();
        List<Integer> change = new ArrayList<>();

        for (int billValue : billsValues) {
            while (remainder >= billValue) {
                remainder = remainder - billValue;
                change.add(billValue);
            }
        }

        for (int changeBill : change) {
            for (CashCartridge cartridge : cartridges) {
                if (cartridge.getBillValue() == changeBill) {
                    cartridge.setBillNumber(cartridge.getBillNumber() - 1);
                }
            }
            if (changeBill == BILLS.BILL_ONE.getValue()) {
                billOne++;
            }
            if (changeBill == BILLS.BILL_TWO.getValue()) {
                billTwo++;
            }
            if (changeBill == BILLS.BILL_THREE.getValue()) {
                billThree++;
            }
            if (changeBill == BILLS.BILL_FOUR.getValue()) {
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
