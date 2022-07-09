package com.smartphone.engineeringsample.tripconsumer;

import com.smartphone.engineeringsample.tripconsumer.MutableTrip.MutableTrip;
import com.smartphone.engineeringsample.tripconsumer.stop.Stop;

import java.time.LocalDateTime;

public sealed class ImmutableTrip implements Trip permits MutableTrip
{
	private Long id;
	private LocalDateTime utcTime;
	private TapType tapType;
	private Stop stop;
	private String pan;

	private ImmutableTrip(final Long id, final LocalDateTime utcTime, final TapType tapType, final Stop stop, final String pan)
	{
		this.id = id;
		this.utcTime = utcTime;
		this.tapType = tapType;
		this.stop = stop;
		this.pan = pan;
	}

	public ImmutableTrip()
	{
		//TODO please remove me
	}
}
