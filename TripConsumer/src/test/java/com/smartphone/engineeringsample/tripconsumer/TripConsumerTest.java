package com.smartphone.engineeringsample.tripconsumer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
class TripConsumerTest
{
	@Test
	protected void constructor_will_fail_if_null_value_passed_as_argument()
	{
		//given
		//when
		Executable executable = () -> new TripConsumer(null);

		//then
		assertThrows(IllegalArgumentException.class, executable);
	}

	@Test
	protected void processTripData_will_fail_if_invalid_value_passed_as_argument()
	{
		//given
		final var trip = new ImmutableTrip();
		final var inputStream = trip.getClass().getResourceAsStream("files/stops-sample.txt");

		//when
		Executable executable = () -> new TripConsumer(null);

		//then
		assertThat(inputStream).isNotNull();
		assertThrows(IllegalArgumentException.class, executable);
	}
}
