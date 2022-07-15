package com.smartphone.engineeringsample.tripconsumer;

import com.smartphone.engineeringsample.tripconsumer.trip.Trip;

import java.util.List;

public interface TripConsumer
{
	List<? extends Trip> deserialiseTripData();
}
