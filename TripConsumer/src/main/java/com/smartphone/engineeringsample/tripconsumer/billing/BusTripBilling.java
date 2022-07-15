package com.smartphone.engineeringsample.tripconsumer.billing;

import com.smartphone.engineeringsample.tripconsumer.trip.Trip;
import com.smartphone.engineeringsample.tripconsumer.transaction.Transaction;
import com.smartphone.engineeringsample.tripconsumer.transaction.TransactionType;

import java.util.*;

public final class BusTripBilling implements Billing
{
    final List<? extends Trip> billingList;
    /**
     *
     * @param billingList valid non-empty list of type Trip
     * @throws IllegalArgumentException if list is empty or null
     */
    public BusTripBilling(final List<? extends Trip> billingList)
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
        final Map<String, Trip> tripMap = new HashMap<>();
        final List<Transaction> transactionList = new ArrayList<>();
        Trip processingTrip = null;
        for(var trip : billingList)
        {
            final Optional<Trip> locatedTrip = Optional.ofNullable(tripMap.get(trip.getPan()));
            locatedTrip.ifPresentOrElse((thisTrip) ->{
                if(thisTrip.getStop().stopName().compareTo(trip.getStop().stopName()) == 0)
                {
                    transactionList.add(new Transaction(thisTrip.getStop(),trip.getStop(), TransactionType.Cancelled));
                }else
                {
                    transactionList.add(new Transaction(thisTrip.getStop(),trip.getStop(), TransactionType.Completed));
                }
                System.out.println("alighting");
                tripMap.remove(thisTrip.getPan());
            }, () -> {
                tripMap.put(trip.getPan(), trip);
                System.out.println("boarding");
            });

        }
        if(tripMap.size() > 0)
        {
            tripMap.keySet().forEach( pan -> {
                final var trip = tripMap.get(pan);
                transactionList.add(new Transaction(trip.getStop(),trip.getStop(),TransactionType.Incomplete));
            });
        }
        return transactionList;
    }
}
