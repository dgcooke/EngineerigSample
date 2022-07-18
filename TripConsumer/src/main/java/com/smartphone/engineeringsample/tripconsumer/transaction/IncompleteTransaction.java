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
		stringBuilder.append(trip.getTimeAsString());
		stringBuilder.append(", ");
		stringBuilder.append(", ");
		stringBuilder.append("0");
		stringBuilder.append(", ");
		stringBuilder.append(trip.getStop().stopName());
		stringBuilder.append(", ");
		stringBuilder.append(", ");
		if(trip.getStop().stopName().compareTo("Stop1") == 0)
		{
			stringBuilder.append("$7.30");
		}else if (trip.getStop().stopName().compareTo("Stop2") == 0)
		{
			stringBuilder.append("$5.50");
		}else //stop 3
		{
			stringBuilder.append("$7.30");
		}
		stringBuilder.append(", ");
		stringBuilder.append(trip.getCompany().companyName());
		stringBuilder.append(", ");
		final var busid = trip.getBus().getShortName();
		stringBuilder.append((busid != null)?busid:trip.getBus().busId());
		stringBuilder.append(", ");
		stringBuilder.append(trip.getPan());
		stringBuilder.append(", ");
		stringBuilder.append("INCOMPLETE");
		return stringBuilder.toString();
	}
}
