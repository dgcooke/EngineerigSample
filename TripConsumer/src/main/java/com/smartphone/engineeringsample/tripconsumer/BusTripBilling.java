package com.smartphone.engineeringsample.tripconsumer;

import com.smartphone.engineeringsample.tripconsumer.transaction.Transaction;
import com.smartphone.engineeringsample.tripconsumer.transaction.TransactionType;

import java.util.ArrayList;
import java.util.List;

public final class BusTripBilling implements Billing
{
    final List<Trip> billingList;


    /**
     *
     * @param billingList valid non-empty list of type Trip
     * @throws IllegalArgumentException if list is empty or null
     */
    public BusTripBilling(final List<Trip> billingList)
    {
        if((billingList == null) || (billingList.isEmpty()))
        {
            throw new IllegalArgumentException("List of trips can not be null or empty");
        }
        this.billingList = billingList;
    }


    private void calculateExpense(final Trip startTrip, final Trip endTrip)
    {

    }


    //needs to handle cancelled trip
    //needs to handle incomplete trip
    public List<Transaction> processList()
    {
        final List<Transaction> transactionList = new ArrayList<>();
        Trip processingTrip = null;
        for(var trip : billingList)
        {
            if(trip.getTapType() == TapType.ON)
            {
                if(processingTrip != null)
                {
                    //incomplete case
                    transactionList.add(new Transaction(processingTrip.getStop(),processingTrip.getStop(), TransactionType.Incomplete));
                }else
                {
                    processingTrip = trip;
                }

            }else if(trip.getTapType() == TapType.OFF)
            {
                if(processingTrip.getStop().stopName().compareTo(trip.getStop().stopName()) == 0)
                {
                    //canceled case
                    transactionList.add(new Transaction(trip.getStop(),trip.getStop(), TransactionType.Cancelled));
                }else
                {
                    //completed
                    transactionList.add(new Transaction(trip.getStop(),trip.getStop(), TransactionType.Completed));
                }
                if(processingTrip != null)
                {
                    calculateExpense(processingTrip, trip);
                }
                processingTrip = null;
            }
        }
        return transactionList;
    }
}
