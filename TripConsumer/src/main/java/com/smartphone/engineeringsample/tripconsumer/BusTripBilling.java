package com.smartphone.engineeringsample.tripconsumer;

import java.util.List;

public final class BusTripBilling implements Billing
{
    final List<Trip> billingList;

    public BusTripBilling(final List<Trip> billingList)
    {
        this.billingList = billingList;
    }


}
