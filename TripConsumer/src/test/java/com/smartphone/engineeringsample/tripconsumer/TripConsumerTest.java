package com.smartphone.engineeringsample.tripconsumer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
class TripConsumerTest
{
	@Test
	protected void constructor_will_fail_if_null_value_passed_as_argument()
	{
		//given
		//when
		Executable executable = () -> new BusTripConsumer(null);

		//then
		assertThrows(IllegalArgumentException.class, executable);
	}

	@Test
	protected void processTripData_will_fail_if_invalid_value_passed_as_argument()
	{
		//given
		final var invalidSampleData = ", DateTimeUTC, TapType, StopId, CompanyId, BusID, PAN\n" +
				"1, 22-01-2018 13:00:00, ON, Stop1, Company1, Bus37, 5500005555555559\n" +
				"2, 22-01-2018 13:05:00, OFF, Stop2, Company1, Bus37, 5500005555555559";

		final var inputStream =  new ByteArrayInputStream(invalidSampleData.getBytes());

		//when
		final var tripConsumer = new BusTripConsumer(inputStream);
		final Executable executable = () -> tripConsumer.processTripData();

		//then
		assertThrows(IllegalArgumentException.class, executable);
	}
	@Test
	protected void processTripData_will_return_valid_array_if_valid_value_passed_as_argument() throws IOException {
		//given
		final var validSampleData = "ID, DateTimeUTC, TapType, StopId, CompanyId, BusID, PAN\n" +
				"1, 22-01-2018 13:00:00, ON, Stop1, Company1, Bus37, 5500005555555559\n" +
				"2, 22-01-2018 13:05:00, OFF, Stop2, Company1, Bus37, 5500005555555559";

		final var inputStream =  new ByteArrayInputStream(validSampleData.getBytes());
		final var tripConsumer = new BusTripConsumer(inputStream);

		//when
		final var result = tripConsumer.processTripData();

		//then
		assertAll(
				() -> assertThat(result).isNotNull(),
				() -> assertThat(result.size()).isGreaterThan(0)
		);
	}
}
