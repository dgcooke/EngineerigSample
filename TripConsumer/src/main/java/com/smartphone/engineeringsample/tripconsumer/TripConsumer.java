package com.smartphone.engineeringsample.tripconsumer;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public final class TripConsumer
{
	private final InputStream tripStream;
	public TripConsumer(final InputStream tripStream)
	{
		if(tripStream == null)
		{
			throw new IllegalArgumentException("input can not be null");
		}

		this.tripStream = tripStream;
	}

	public List<ImmutableTrip> processTripData()
	{
		final var inputStream = this.getClass().getResourceAsStream("files/stops-sample.txt");
		//if(inputStream)
		return new ArrayList<>();
	}

}
