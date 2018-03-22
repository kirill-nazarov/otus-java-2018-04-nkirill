package ru.otus.l6;


/*  Написать эмулятор АТМ (банкомата).
    Объект класса АТМ должен уметь
    • принимать банкноты разных номиналов (на каждый номинал должна быть своя ячейка)
    • выдавать запрошенную сумму минимальным количеством банкнот или ошибку если сумму нельзя выдать
    • выдавать сумму остатка денежных средств
 */

import java.util.Scanner;

public class Main {
    public static void main(String... args) {
        //depositCash
        //withdrawCash
        //getBalance

        ATM atm = new ATM();
        atm.fillWithInitialValues(100, 100, 100, 100);
        System.out.println("Good day");
        System.out.println("Initial ATM Balance = " + atm.getATMbalance());
        System.out.println("Please choose ATM function: 1 - Deposit Cash 2 - Withdraw Cash");
        Scanner scanner = new Scanner(System.in);
        int chosenFunction = scanner.nextInt();
        if (chosenFunction == 1) {
            System.out.println("You chose Deposit Cash");
            System.out.println("Available deposit bills:" + atm.getBillValues());
            System.out.println("Please choose deposit bill value from 1 to 4 accordingly");
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
            System.out.println("Please choose deposited bills number");
            int chosenBillNumber = scanner.nextInt();
            System.out.println("You deposited bills of value " + billValue + " in the amount of " + chosenBillNumber);
            System.out.println("You deposited sum = " + billValue * chosenBillNumber);
            atm.depositCash(chosenBillValue, chosenBillNumber);
            System.out.println("ATM balance Now = " + atm.getATMbalance());
            System.out.println("Good bye!");
        } else if (chosenFunction == 2) {
            System.out.println("You chose to Withdraw Cash");
        } else {
            System.out.println("You did not choose ATM function. Good bye!");
        }

    }

}
