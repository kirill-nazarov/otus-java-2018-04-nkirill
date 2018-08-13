package ru.otus.l7;


/*  Написать эмулятор АТМ (банкомата).
    Объект класса АТМ должен уметь
    • принимать банкноты разных номиналов (на каждый номинал должна быть своя ячейка)
    • выдавать запрошенную сумму минимальным количеством банкнот или ошибку если сумму нельзя выдать
    • выдавать сумму остатка денежных средств
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static final int DEPOSIT_CASH_FUNCTION = 1;
    public static final int WITHDRAW_CASH_FUNCTION = 2;


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

        print("Good day");
        print("Initial ATM Balance = " + atm.getATMbalance());
        print("Please choose ATM function: 1 - Deposit Cash 2 - Withdraw Cash");
        Scanner scanner = new Scanner(System.in);
        int chosenFunction = scanner.nextInt();
        if (chosenFunction == DEPOSIT_CASH_FUNCTION) {
            print("You chose Deposit Cash");
            print("Available deposit bills:" + atm.getBillValues());
            print("Please choose deposit bill value from 1 to 4 accordingly");
            int chosenBillValue = scanner.nextInt();
            int billValue = 0;
            switch (chosenBillValue) {
                case 1:
                    billValue = atm.getBillValues().get(0);
                    break;
                case 2:
                    billValue = atm.getBillValues().get(1);
                    break;
                case 3:
                    billValue = atm.getBillValues().get(2);
                    break;
                case 4:
                    billValue = atm.getBillValues().get(3);
                    break;
            }
            print("Please choose deposited bills number");
            int chosenBillNumber = scanner.nextInt();
            print("You deposited bills of value " + billValue + " in the amount of " + chosenBillNumber);
            print("You deposited sum = " + billValue * chosenBillNumber);
            atm.depositCash(billValue, chosenBillNumber);
            print("ATM balance Now = " + atm.getATMbalance());
            print("Good bye!");
        } else if (chosenFunction == WITHDRAW_CASH_FUNCTION) {
            print("You chose to Withdraw Cash");
            print("Please provide amount to withdraw");
            int chosenAmount = scanner.nextInt();
            if (chosenAmount > atm.getATMbalance()) {
                print("You cannot withdraw more than ATM balance. Good bye!");
            } else {
                print("You receive " + printBillsAmount(atm.withDrawCash(chosenAmount)) + " bills");
                print("Final ATM Balance = " + atm.getATMbalance());
            }
        } else {
            print("You did not choose ATM function. Good bye!");
        }

    }

    public static void print(String str) {
        System.out.println(str);
    }

    public static String printBillsAmount(Map<Integer, Integer> bills) {
        int billOne = bills.get(BILLS.BILL_ONE.getValue());
        int billTwo = bills.get(BILLS.BILL_TWO.getValue());
        int billThree = bills.get(BILLS.BILL_THREE.getValue());
        int billFour = bills.get(BILLS.BILL_FOUR.getValue());

        return BILLS.BILL_ONE.getValue() + " x " + billOne + ", " + BILLS.BILL_TWO.getValue() + " x " + billTwo + ", " + BILLS.BILL_THREE.getValue() +
                " x " + billThree + ", " + BILLS.BILL_FOUR.getValue() + " x " + billFour;

    }

}
