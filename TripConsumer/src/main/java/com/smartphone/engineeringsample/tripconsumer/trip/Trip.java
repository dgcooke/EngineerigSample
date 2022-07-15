package com.smartphone.engineeringsample.tripconsumer.trip;

import com.smartphone.engineeringsample.tripconsumer.TapType;
import com.smartphone.engineeringsample.tripconsumer.company.Company;
import com.smartphone.engineeringsample.tripconsumer.stop.Stop;

import java.time.ZonedDateTime;

public interface Trip
{
    public Long getId();

    public ZonedDateTime getUtcTime();

    public TapType getTapType();

    public Stop getStop();

    public Company getCompany();

    public String getPan();

}
