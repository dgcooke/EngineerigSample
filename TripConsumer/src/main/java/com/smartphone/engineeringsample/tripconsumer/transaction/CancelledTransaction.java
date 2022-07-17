package com.smartphone.engineeringsample.tripconsumer.transaction;

import com.smartphone.engineeringsample.tripconsumer.trip.Trip;

public class CancelledTransaction implements Transaction
{
	private final Trip trip;
	public CancelledTransaction(Trip trip)
	{
		this.trip = trip;
	}

	@Override
	public String generateOutput()
	{
		return null;
	}
}
