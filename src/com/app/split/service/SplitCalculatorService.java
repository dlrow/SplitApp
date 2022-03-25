package com.app.split.service;

import com.app.split.entities.Split;
import com.app.split.enums.SplitType;

import java.util.HashMap;
import java.util.Map;

public class SplitCalculatorService {

    public Map<Split, Double> calculateSplitsForEachUser(double amount, String paidBy, String expenseType, Map<String, Double> userToExpenseMap, SplitType splitType) {
        Map<Split, Double> splits = new HashMap<>();
        switch (splitType){
            case EQUAL:
            case EXACT:
                for(Map.Entry<String, Double> entry : userToExpenseMap.entrySet()){
                    if(paidBy.equals(entry.getKey()))
                        continue;
                    Split sp = new Split(entry.getKey(), paidBy);
                    splits.put(sp, entry.getValue());
                }
                break;
            case PERCENT:
                for(Map.Entry<String, Double> entry : userToExpenseMap.entrySet()){
                    if(paidBy.equals(entry.getKey()))
                        continue;
                    Split sp = new Split(entry.getKey(), paidBy);
                    Double dueAmt =  ( entry.getValue()/100 ) * amount;
                    splits.put(sp, dueAmt);
                }
                break;
        }
        return splits;
    }
}
