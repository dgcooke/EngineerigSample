package com.smartphone.engineeringsample.tripconsumer.billing;

import com.smartphone.engineeringsample.tripconsumer.transaction.Transaction;

import java.util.List;

public interface Billing {
    List<Transaction> processList();
}
