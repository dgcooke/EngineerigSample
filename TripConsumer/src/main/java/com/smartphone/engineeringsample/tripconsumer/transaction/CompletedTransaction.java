package com.smartphone.engineeringsample.tripconsumer.transaction;

import com.smartphone.engineeringsample.tripconsumer.trip.Trip;

public class CompletedTransaction implements Transaction
{
	private final Trip board;
	private final Trip alight;
	public CompletedTransaction(Trip board, Trip alight)
	{
		this.board = board;
		this.alight = alight;
	}

	@Override
	public String generateOutput()
	{
		final var durationInMilliSeconds = alight.getUtcTime().toInstant().toEpochMilli() - board.getUtcTime().toInstant().toEpochMilli();
		final var durationInSeconds = (durationInMilliSeconds > 1000)?durationInMilliSeconds/1000:0L;
		var stringBuilder = new StringBuilder();
		stringBuilder.append(board.getTimeAsString());
		stringBuilder.append(", ");
		stringBuilder.append(alight.getTimeAsString());
		stringBuilder.append(", ");
		stringBuilder.append(durationInSeconds);
		stringBuilder.append(", ");
		stringBuilder.append(board.getStop().stopName());
		stringBuilder.append(", ");
		stringBuilder.append(alight.getStop().stopName());
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
		stringBuilder.append(board.getCompany().companyName());
		stringBuilder.append(", ");
		final var busid = board.getBus().getShortName();
		stringBuilder.append((busid != null)?busid:board.getBus().busId());
		stringBuilder.append(", ");
		stringBuilder.append(board.getPan());
		stringBuilder.append(", ");
		stringBuilder.append("COMPLETED");
		return stringBuilder.toString();
	}
}
