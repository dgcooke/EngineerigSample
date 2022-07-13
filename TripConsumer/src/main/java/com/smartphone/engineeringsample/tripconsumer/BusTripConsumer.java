package com.smartphone.engineeringsample.tripconsumer;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public final class BusTripConsumer implements TripConsumer
{
	private static final String HEADER_LINE = "ID, DateTimeUTC, TapType, StopId, CompanyId, BusID, PAN";
	private final InputStream tripStream;
	public BusTripConsumer(final InputStream tripStream)
	{
		if(tripStream == null)
		{
			throw new IllegalArgumentException("input can not be null");
		}

		this.tripStream = tripStream;
	}

	public List<ImmutableTrip> deserialiseTripData()
	{
		try
		{
			final var targetReader = new BufferedReader(new InputStreamReader(tripStream));
			final var fileHeaderLine = targetReader.readLine();

			if(fileHeaderLine.contains(HEADER_LINE))
			{
				var result = targetReader.lines()
						.map((line) -> Arrays.asList(line.split(",")))
						.map((list) -> new ImmutableTrip(list))
						.toList();
				return result;
			}else
			{
				throw new IllegalArgumentException("input is wrong format");
			}
		}catch(IOException ioException)
		{
			throw new IllegalArgumentException("input is wrong format");
		}
	}
}
