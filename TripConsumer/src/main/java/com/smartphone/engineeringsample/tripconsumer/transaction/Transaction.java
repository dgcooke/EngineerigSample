package com.smartphone.engineeringsample.tripconsumer.transaction;

import com.smartphone.engineeringsample.tripconsumer.stop.Stop;
import com.smartphone.engineeringsample.tripconsumer.trip.Trip;

public interface Transaction //(Trip board, Trip alight, TransactionType transactionType)
{
	String generateOutput();
}
