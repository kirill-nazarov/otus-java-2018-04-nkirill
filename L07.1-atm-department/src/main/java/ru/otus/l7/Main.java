package ru.otus.l7;


/*
ДЗ 07. ATM Department
Написать приложение ATM Department:
• Приложение может содержать несколько ATM
• Department может собирать сумму остатков со всех ATM
• Department может инициировать событие – восстановить состояние всех ATM до начального.
(начальные состояния у разных ATM могут быть разными)
*/

public class Main {

    public static void main(String... args) {

        ATM atm1 = new ATM(1, BILLS.BILL_ONE, BILLS.BILL_TWO, BILLS.BILL_THREE, BILLS.BILL_FOUR);
        ATM atm2 = new ATM(2, BILLS.BILL_ONE, BILLS.BILL_TWO);
        ATM atm3 = new ATM(3, BILLS.BILL_ONE, BILLS.BILL_THREE, BILLS.BILL_FOUR);

        AtmDepartment atmDepartment = new AtmDepartment();
        atmDepartment.addAtm(atm1);
        atmDepartment.addAtm(atm2);
        atmDepartment.addAtm(atm3);
        System.out.println(atmDepartment.getDepartmentBalance());

        System.out.println("Withdraw 1000 from ATM 1");
        atm1.withDrawCash(1000);
        System.out.println(atmDepartment.getDepartmentBalance());
        System.out.println("Deposit 200 to ATM 2");
        atm2.depositCash(50, 4);
        System.out.println(atmDepartment.getDepartmentBalance());
        System.out.println("Withdraw 600 from ATM 3");
        atm3.withDrawCash(600);
        System.out.println(atmDepartment.getDepartmentBalance());

        atmDepartment.restoreInitialDepartmentState();

        System.out.println(atmDepartment.getDepartmentBalance());

    }

}
