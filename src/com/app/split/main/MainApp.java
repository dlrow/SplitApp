package com.app.split.main;

import com.app.split.enums.SplitType;
import com.app.split.service.ExpenseService;
import com.app.split.service.UserService;

import java.util.HashMap;
import java.util.Map;


public class MainApp {

    public static void main(String[] args) {
        UserService userService = new UserService();
        ExpenseService expenseService = new ExpenseService();

        userService.addUser("1", "user1", "abc@ab.com", "9905533669");
        userService.addUser("2", "user2", "abc@ab.com", "9905533679");
        userService.addUser("3", "user3", "abc@ab.com", "9905533869");
        userService.addUser("4", "user4", "abc@ab.com", "9905533969");
        userService.addUser("5", "user5", "abr@ab.com", "9935533969");

        //Add PETROL expense: equal split of 600 paid by user 2 between (1,2,5) - Petrol
        Map<String, Double> userToExpenseMap1 = new HashMap<>();
        userToExpenseMap1.put("1", 200.0);
        userToExpenseMap1.put("2", 200.0);
        userToExpenseMap1.put("5", 200.0);
        expenseService.addExpense(600, "2", "PETROL", userToExpenseMap1, SplitType.EQUAL);
        expenseService.printDueSplits();
        System.out.println("\n");

        //Add MOVIE expense percentage split 400 paid by user 1 between (1,2,4,5) [ 30%, 20%, 40%, 10%]  - Movie
        Map<String, Double> userToExpenseMap2 = new HashMap<>();
        userToExpenseMap2.put("1", 30.0);
        userToExpenseMap2.put("2", 20.0);
        userToExpenseMap2.put("4", 40.0);
        userToExpenseMap2.put("5", 10.0);
        expenseService.addExpense(400, "1", "MOVIE", userToExpenseMap2, SplitType.PERCENT);
        expenseService.printDueSplits();
        System.out.println("\n");

        //Add FOOD expense: exact split of 700 paid by user 4 btw (1,2,5) [ 100, 200,  400] - Food
        Map<String, Double> userToExpenseMap3 = new HashMap<>();
        userToExpenseMap3.put("1", 100.0);
        userToExpenseMap3.put("2", 200.0);
        userToExpenseMap3.put("5", 400.0);
        expenseService.addExpense(700, "4", "FOOD", userToExpenseMap3, SplitType.EXACT);
        expenseService.printDueSplits();



    }
}
