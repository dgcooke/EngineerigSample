package com.smartphone.engineeringsample.tripconsumer.billing;

import com.smartphone.engineeringsample.tripconsumer.BusTripConsumer;
import com.smartphone.engineeringsample.tripconsumer.transaction.CancelledTransaction;
import com.smartphone.engineeringsample.tripconsumer.transaction.CompletedTransaction;
import com.smartphone.engineeringsample.tripconsumer.transaction.IncompleteTransaction;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BusTripBillingTest
{
	@Test
	protected void processList_will_return_single_cancelled_trip()
	{
		//given
		final var validSampleData = "ID, DateTimeUTC, TapType, StopId, CompanyId, BusID, PAN\n" +
				"1, 22-01-2018 13:00:00, ON, Stop1, Company1, Bus37, 5500005555555559\n" +
				"2, 22-01-2018 13:05:00, OFF, Stop1, Company1, Bus37, 5500005555555559";
		final var inputStream =  new ByteArrayInputStream(validSampleData.getBytes());
		final var tripConsumer = new BusTripConsumer(inputStream);
		final var tripList = tripConsumer.deserialiseTripData();

		//when
		final var busTripBilling = new BusTripBilling(tripList);
		final var result = busTripBilling.processList();

		//then
		assertAll(
				() -> assertThat(result).isNotNull(),
				() -> assertThat(result.size()).isEqualTo(1),
				() -> assertThat(result.get(0)).isInstanceOf(CancelledTransaction.class),
				() -> assertThat( result.get(0).generateOutput()).isEqualTo("22-01-2018 13:00:00, 22-01-2018 13:05:00, 300, Stop1, Stop1, $0.00, Company1, B37, 5500005555555559, CANCELLED")
		);
	}

	@Test
	protected void processList_will_return_single_incomplete_trip()
	{
		//given
		final var validSampleData = "ID, DateTimeUTC, TapType, StopId, CompanyId, BusID, PAN\n" +
				"1, 22-01-2018 13:00:00, ON, Stop1, Company1, Bus37, 5500005555555559\n";
		final var inputStream =  new ByteArrayInputStream(validSampleData.getBytes());
		final var tripConsumer = new BusTripConsumer(inputStream);
		final var tripList = tripConsumer.deserialiseTripData();

		//when
		final var busTripBilling = new BusTripBilling(tripList);
		final var result = busTripBilling.processList();

//		System.out.println(result.get(0).generateOutput());

		//then
		assertAll(
				() -> assertThat(result).isNotNull(),
				() -> assertThat(result.size()).isEqualTo(1),
				() -> assertThat(result.get(0)).isInstanceOf(IncompleteTransaction.class),
				() -> assertThat( result.get(0).generateOutput()).isEqualTo("22-01-2018 13:00:00, , 0, Stop1, , $7.30, Company1, B37, 5500005555555559, INCOMPLETE")
		);

	}

	@Test
	protected void processList_will_return_single_complete_trip()
	{
		//given
		final var validSampleData = "ID, DateTimeUTC, TapType, StopId, CompanyId, BusID, PAN\n" +
				"1, 22-01-2018 13:00:00, ON, Stop1, Company1, Bus37, 5500005555555559\n" +
				"2, 22-01-2018 13:05:00, OFF, Stop2, Company1, Bus37, 5500005555555559";
		final var inputStream =  new ByteArrayInputStream(validSampleData.getBytes());
		final var tripConsumer = new BusTripConsumer(inputStream);
		final var tripList = tripConsumer.deserialiseTripData();

		//when
		final var busTripBilling = new BusTripBilling(tripList);
		final var result = busTripBilling.processList();

		//then
		assertAll(
				() -> assertThat(result).isNotNull(),
				() -> assertThat(result.size()).isEqualTo(1),
				() -> assertThat(result.get(0)).isInstanceOf(CompletedTransaction.class)
		);
	}

	@Test
	protected void processList_will_return_two_completed_trips()
	{
		//given
		final var validSampleData = "ID, DateTimeUTC, TapType, StopId, CompanyId, BusID, PAN\n" +
				"1, 22-01-2018 13:00:00, ON, Stop1, Company1, Bus37, 5500005555555559\n" +
				"2, 22-01-2018 13:05:00, ON, Stop2, Company1, Bus37, 5500005555555557\n" +
				"3, 22-01-2018 13:10:00, OFF, Stop2, Company1, Bus37, 5500005555555559\n" +
				"4, 22-01-2018 13:17:00, OFF, Stop3, Company1, Bus37, 5500005555555557";
		final var inputStream =  new ByteArrayInputStream(validSampleData.getBytes());
		final var tripConsumer = new BusTripConsumer(inputStream);
		final var tripList = tripConsumer.deserialiseTripData();

		//when
		final var busTripBilling = new BusTripBilling(tripList);
		final var result = busTripBilling.processList();

		//then
		assertAll(
				() -> assertThat(result).isNotNull(),
				() -> assertThat(result.size()).isEqualTo(2),
				() -> assertThat(result.get(0)).isInstanceOf(CompletedTransaction.class)
		);
	}
}
