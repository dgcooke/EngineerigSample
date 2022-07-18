package com.smartphone.engineeringsample.tripconsumer.transaction;

import com.smartphone.engineeringsample.tripconsumer.trip.Trip;

public class IncompleteTransaction implements Transaction
{
	private final Trip trip;
	public IncompleteTransaction(Trip trip)
	{
		this.trip = trip;
	}

	@Override
	public String generateOutput()
	{
		var stringBuilder = new StringBuilder();
		stringBuilder.append(trip.getUtcTime());
		stringBuilder.append(",");
		stringBuilder.append(",");
		stringBuilder.append("0");
		stringBuilder.append(",");
		stringBuilder.append(trip.getStop().stopName());
		stringBuilder.append(",");
		stringBuilder.append(",");
		stringBuilder.append("0.00");
		stringBuilder.append(",");
		stringBuilder.append(trip.getCompany().companyName());
		stringBuilder.append(",");
		stringBuilder.append(trip.getBus().busId());
		stringBuilder.append(",");
		stringBuilder.append(trip.getPan());
		stringBuilder.append(",");
		stringBuilder.append("INCOMPLETE");
		return stringBuilder.toString();
	}
}
