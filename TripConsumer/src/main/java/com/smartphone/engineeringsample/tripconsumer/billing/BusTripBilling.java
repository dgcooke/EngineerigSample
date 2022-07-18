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


    public void produceOutputFromTransactionList(final List<Transaction> transactionList)
    {

    }

	private void processRecord(final Transaction transaction)
	{

	}

    public List<Transaction> processList()
    {
        final Map<String, Trip> tripMap = new HashMap<>();
        final List<Transaction> transactionList = new ArrayList<>();
        Trip processingTrip = null;
        for(var trip : billingList)
        {
            final Optional<Trip> locatedTrip = Optional.ofNullable(tripMap.get(trip.getPan()));
            locatedTrip.ifPresentOrElse((thisTrip) ->
			{
                if(thisTrip.getStop().stopName().compareTo(trip.getStop().stopName()) == 0)
                {
                    transactionList.add(new CancelledTransaction(thisTrip,trip));
                }else
                {
                    transactionList.add(new CompletedTransaction(thisTrip,trip));
                }
                tripMap.remove(thisTrip.getPan());
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
        return transactionList;
    }
}
