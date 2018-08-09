package ru.otus.l7;

/*   ДЗ 07. ATM Department
        Написать приложение ATM Department:
        • Приложение может содержать несколько ATM
        • Department может собирать сумму остатков со всех ATM
        • Department может инициировать событие – восстановить состояние всех ATM до начального.
        (начальные состояния у разных ATM могут быть разными)
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
