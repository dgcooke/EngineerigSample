package com.smartphone.engineeringsample.tripconsumer;

import com.smartphone.engineeringsample.tripconsumer.stop.Stop;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class BusTripConsumer implements TripConsumer
{
	private static final String HEADER_LINE = "ID, DateTimeUTC, TapType, StopId, CompanyId, BusID, PAN";
	private final InputStream tripStream;

	/**
	 *
	 * @param tripStream - expects non null InputStream
	 * @throws IllegalArgumentException if input file is null
	 */
	public BusTripConsumer(final InputStream tripStream)
	{
		if(tripStream == null)
		{
			throw new IllegalArgumentException("input can not be null");
		}

		this.tripStream = tripStream;
	}

	/**
	 * @throws IllegalArgumentException if input file is not readable
	 * @throws IllegalArgumentException if input file format is incorrect
	 * @return list of objects that extend Trip
	 */
	public List<? extends Trip> deserialiseTripData()
	{
		try
		{
			final var targetReader = new BufferedReader(new InputStreamReader(tripStream));
			final var fileHeaderLine = targetReader.readLine();

			if(fileHeaderLine.contains(HEADER_LINE))
			{
				var result  = targetReader.lines()
						.map((line) -> Arrays.asList(line.split(",")))
						.map(ImmutableTrip::new)
						.toList();
				return  result;
			}else
			{
				throw new IllegalArgumentException("input is wrong format");
			}
		}catch(IOException ioException)
		{
			throw new IllegalArgumentException("input could not be opened");
		}
	}
}
