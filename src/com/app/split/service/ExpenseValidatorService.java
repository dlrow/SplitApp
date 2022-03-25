package com.app.split.service;

import com.app.split.enums.SplitType;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ExpenseValidatorService {

    public boolean isValidSplitType(double amount, SplitType splitType, Map<String, Double> userToExpenseMap) {
        switch (splitType){
            case EQUAL:
                Set<Double> values = new HashSet<>(userToExpenseMap.values());
                return values.size()==1;
            case EXACT:
                double total = 0.0;
                for(Map.Entry<String, Double> entry : userToExpenseMap.entrySet()){
                    total+=entry.getValue();
                }
                return amount==total;
            case PERCENT:
                double totalPercent = 0.0;
                for(Map.Entry<String, Double> entry : userToExpenseMap.entrySet()){
                    totalPercent+=entry.getValue();
                }
                return 100==totalPercent;
        }
        return false;
    }
}
