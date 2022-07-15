package com.smartphone.engineeringsample.tripconsumer;

import java.util.List;

public interface TripConsumer
{
	List<? extends Trip> deserialiseTripData();
}
