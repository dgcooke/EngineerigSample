package com.smartphone.engineeringsample.tripconsumer.transaction;

import com.smartphone.engineeringsample.tripconsumer.stop.Stop;

public record Transaction(Stop boardingStop, Stop alightingStop, TransactionType transactionType) {
}
