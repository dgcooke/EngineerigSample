package com.smartphone.engineeringsample.tripconsumer;

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
    protected void processList()
    {
        Trip processingTrip = null;
        for(var trip : billingList)
        {
            if(trip.getTapType() == TapType.ON)
            {
                processingTrip = trip;
            }else if(trip.getTapType() == TapType.OFF)
            {
                if(processingTrip != null)
                {
                    calculateExpense(processingTrip, trip);
                }
                processingTrip = null;
            }
        }
    }

}
