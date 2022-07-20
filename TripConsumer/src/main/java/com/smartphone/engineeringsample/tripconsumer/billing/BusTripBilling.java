package com.smartphone.engineeringsample.tripconsumer.billing;

import com.smartphone.engineeringsample.tripconsumer.transaction.*;
import com.smartphone.engineeringsample.tripconsumer.trip.Trip;

import java.util.*;

public final class BusTripBilling implements Billing
{
    final List<? extends Trip> billingList;
    private final static String OUTPUT_HEADER = "Started, Finished, DurationSecs, FromStopId, ToStopId, ChargeAmount, CompanyId, BusID, PAN, Status";
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

    /**
     *
     * @return List<Transaction> list of transactions to be processed
     */
    public List<Transaction> processList()
    {
        final Map<String, Trip> tripMap = new HashMap<>();
        final List<Transaction> transactionList = new ArrayList<>();
        Trip processingTrip = null;
        for(var trip : billingList)
        {
            final Optional<Trip> locatedTrip = Optional.ofNullable(tripMap.get(trip.getPan()));
            locatedTrip.ifPresentOrElse((previousTrip) ->
			{
                if(previousTrip.getStop().getStopName().compareTo(trip.getStop().getStopName()) == 0)
                {
                    transactionList.add(new CancelledTransaction(previousTrip,trip));
                }else
                {
                    transactionList.add(new CompletedTransaction(previousTrip,trip));
                }
                tripMap.remove(previousTrip.getPan());
            }, () -> {
                tripMap.put(trip.getPan(), trip);
            });
        }
        if(tripMap.size() > 0)
        {
            tripMap.keySet().forEach( pan -> {
                final var trip = tripMap.get(pan);
                transactionList.add(new IncompleteTransaction(trip));
            });
        }
        if(!transactionList.isEmpty())
        {
            System.out.println(OUTPUT_HEADER);
        }
        return transactionList;
    }
}
