package ru.otus.l7;


/*  Написать эмулятор АТМ (банкомата).
    Объект класса АТМ должен уметь
    • принимать банкноты разных номиналов (на каждый номинал должна быть своя ячейка)
    • выдавать запрошенную сумму минимальным количеством банкнот или ошибку если сумму нельзя выдать
    • выдавать сумму остатка денежных средств
 */

import java.util.Scanner;

public class Main {
    public static void main(String... args) {
        ATM atm = new ATM();
        atm.fillWithInitialValues(100, 100, 100, 100);
        print("Good day");
        print("Initial ATM Balance = " + atm.getATMbalance());
        print("Please choose ATM function: 1 - Deposit Cash 2 - Withdraw Cash");
        Scanner scanner = new Scanner(System.in);
        int chosenFunction = scanner.nextInt();
        if (chosenFunction == 1) {
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
            atm.depositCash(chosenBillValue, chosenBillNumber);
            print("ATM balance Now = " + atm.getATMbalance());
            print("Good bye!");
        } else if (chosenFunction == 2) {
            print("You chose to Withdraw Cash");
            print("Please provide amount to withdraw");
            int chosenAmount = scanner.nextInt();
            if (chosenAmount > atm.getATMbalance()) {
                print("You cannot withdraw more than ATM balance. Good bye!");
            } else {
                print("You receive " + atm.withDrawCash(chosenAmount) + " bills");
                print("Final ATM Balance = " + atm.getATMbalance());
            }
        } else {
            print("You did not choose ATM function. Good bye!");
        }

    }

    public static void print(String str) {
        System.out.println(str);
    }

}
