package com.app.split.service;

import com.app.split.entities.Expense;
import com.app.split.entities.Split;
import com.app.split.enums.SplitType;

import java.util.*;

public class ExpenseService {

    static Map<Split, Double> dueSplits = new HashMap<>();

    static List<Expense> expenseList = new ArrayList<>();

    SplitCalculatorService splitCalculatorService = new SplitCalculatorService();

    ExpenseValidatorService expenseValidatorService = new ExpenseValidatorService();

    UserService userService = new UserService();


    public void addExpense(double amount, String paidBy, String expenseType,  Map<String, Double> userToExpenseMap, SplitType splitType){
        //validate if userIds exists
        if(!userService.isValidUserIds(userToExpenseMap)){
            System.out.println("Invalid userIds provided");
            return;
        }
        //validate splitType
        if(!expenseValidatorService.isValidSplitType(amount, splitType, userToExpenseMap)){
            System.out.println("Invalid expense provided");
            return;
        }

        Map<Split, Double> newSplits = splitCalculatorService.calculateSplitsForEachUser(amount, paidBy, expenseType, userToExpenseMap, splitType);
        addToExpenseList(newSplits, expenseType);
        mergeNewSplitToDueSplits(newSplits);
        simplifyDirectSplits();
    }

    private void simplifyDirectSplits() {
        Set<Split> redundantSplits = new HashSet<>();
        for(Map.Entry<Split, Double> entry : dueSplits.entrySet()){
            if(redundantSplits.contains(entry.getKey()))
                continue;
            String owedTo = entry.getKey().getOweToUserId();
            String owedBy = entry.getKey().getOwedByUserId();
            Split reverseSplit = new Split(owedTo, owedBy);
            if(dueSplits.containsKey(reverseSplit)){
                Double amt = entry.getValue();
                Double reverseAmt = dueSplits.get(reverseSplit);
                if(amt - reverseAmt > 0){
                    dueSplits.put(entry.getKey(), amt - reverseAmt);
                    redundantSplits.add(reverseSplit);
                }
            }
        }

        redundantSplits.stream().forEach(sp-> dueSplits.remove(sp));
    }

    private void addToExpenseList(Map<Split, Double> newSplits, String expenseType) {
        for(Map.Entry<Split, Double> entry : newSplits.entrySet()){
            expenseList.add(new Expense(entry.getKey().getOwedByUserId(),
                    entry.getKey().getOweToUserId(),
                    expenseType,
                    entry.getValue()));
        }
    }

    public void printDueSplits(){
        for(Map.Entry<Split, Double> entry : dueSplits.entrySet()){
            System.out.println(entry.getKey() + ":  amount: "+ entry.getValue());
        }
    }

    private void mergeNewSplitToDueSplits(Map<Split, Double> newSplits) {
        for(Map.Entry<Split, Double> entry : newSplits.entrySet()){
            Double amtDueAfterMerge = entry.getValue() + dueSplits.getOrDefault(entry.getKey(), 0.0);
            dueSplits.put(entry.getKey(), amtDueAfterMerge);
            if(amtDueAfterMerge == 0)
                dueSplits.remove(entry.getKey());
        }
    }
}
