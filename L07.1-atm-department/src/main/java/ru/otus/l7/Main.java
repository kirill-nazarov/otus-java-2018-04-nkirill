package ru.otus.l7;


/*  TASK: Write ATM Department emulator.
    ATM department may:
    -department may contain a few ATMs
    -department may collect all atm balances
    -department may initialise initial ATM states
*/

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String... args) {

        //Initializing ATM with initial values
        CashCartridge cartridge1 = new CashCartridge();
        CashCartridge cartridge2 = new CashCartridge();
        CashCartridge cartridge3 = new CashCartridge();
        CashCartridge cartridge4 = new CashCartridge();

        cartridge1.setBillValue(BILLS.BILL_ONE.getValue());
        cartridge2.setBillValue(BILLS.BILL_TWO.getValue());
        cartridge3.setBillValue(BILLS.BILL_THREE.getValue());
        cartridge4.setBillValue(BILLS.BILL_FOUR.getValue());

        cartridge1.setBillNumber(100);
        cartridge2.setBillNumber(100);
        cartridge3.setBillNumber(100);
        cartridge4.setBillNumber(100);

        List<CashCartridge> cartridges = new ArrayList<>();

        cartridges.add(cartridge1);
        cartridges.add(cartridge2);
        cartridges.add(cartridge3);
        cartridges.add(cartridge4);

        ATM atm = new ATM(cartridges);

        print(atm.getATMbalance());
        atm.depositCash(50, 10);
        print(atm.getATMbalance());
        int amount1 = 200;
        if (atm.withDrawCash(amount1) == null) {
            print("Not enough bills for withdrawal");
        } else {
            print(atm.withDrawCash(amount1));
        }
        print(atm.getATMbalance());
        int amount2 = 200000;
        if (atm.withDrawCash(amount2) == null) {
            print("Not enough bills for withdrawal");
        } else {
            print(atm.withDrawCash(amount2));
        }
        print(atm.getATMbalance());


    }


    private static void print(Object obj) {
        System.out.println(obj.toString());
    }


}
