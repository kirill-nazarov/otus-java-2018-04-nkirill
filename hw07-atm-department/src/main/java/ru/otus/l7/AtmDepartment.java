package ru.otus.l7;

import java.util.ArrayList;
import java.util.List;

public class AtmDepartment {

    private List<ATM> atms = new ArrayList<>();

    public List<ATM> getAtms() {
        return atms;
    }

    public void setAtms(List<ATM> atms) {
        this.atms = atms;
    }

    public void addAtm(ATM atm) {
        this.atms.add(atm);
    }

    public int getDepartmentBalance() {
        int allAtmBalance = 0;
        for (ATM atm : atms) {
            allAtmBalance = allAtmBalance + atm.getATMbalance();
        }
        return allAtmBalance;
    }

    public void restoreInitialDepartmentState() {
        for (ATM atm : atms) {
            atm.restoreInitialAtmState();
        }
    }


}
