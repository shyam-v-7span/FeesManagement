package com.FeesManagement;

import com.FeesManagement.controller.FeeService;

public class Main {
    public static void main(String[] args) {

        FeeService feeService = new FeeService();
        feeService.showMenu();

    }
}