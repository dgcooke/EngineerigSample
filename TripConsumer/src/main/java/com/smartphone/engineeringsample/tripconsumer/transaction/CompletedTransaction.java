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
		return null;
	}
}
