package com.smartphone.engineeringsample.tripconsumer;

import com.smartphone.engineeringsample.tripconsumer.transaction.TransactionType;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.ByteArrayInputStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BusTripBillingTest {
    @Test
    protected void processList_will_return_single_cancelled_trip()
    {
        //given
        final var validSampleData = "ID, DateTimeUTC, TapType, StopId, CompanyId, BusID, PAN\n" +
                "1, 22-01-2018 13:00:00, ON, Stop1, Company1, Bus37, 5500005555555559\n" +
                "2, 22-01-2018 13:05:00, OFF, Stop1, Company1, Bus37, 5500005555555559";
        final var inputStream =  new ByteArrayInputStream(validSampleData.getBytes());
        final var tripConsumer = new BusTripConsumer(inputStream);
        final var tripList = (List<Trip>) tripConsumer.deserialiseTripData();

        //when
        final var busTripBilling = new BusTripBilling(tripList);
        final var result = busTripBilling.processList();

        //then
        assertAll(
                () -> assertThat(result).isNotNull(),
                () -> assertThat(result.size()).isEqualTo(1),
                () -> assertThat(result.get(0).transactionType()).isEqualTo(TransactionType.Cancelled)
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
        final var tripList = (List<Trip>) tripConsumer.deserialiseTripData();

        //when
        final var busTripBilling = new BusTripBilling(tripList);
        final var result = busTripBilling.processList();

        //then
        assertAll(
                () -> assertThat(result).isNotNull(),
                () -> assertThat(result.size()).isEqualTo(1),
                () -> assertThat(result.get(0).transactionType()).isEqualTo(TransactionType.Incomplete)
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
        final var tripList = (List<Trip>) tripConsumer.deserialiseTripData();

        //when
        final var busTripBilling = new BusTripBilling(tripList);
        final var result = busTripBilling.processList();

        //then
        assertAll(
                () -> assertThat(result).isNotNull(),
                () -> assertThat(result.size()).isEqualTo(1),
                () -> assertThat(result.get(0).transactionType()).isEqualTo(TransactionType.Completed)
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
        final var tripList = (List<Trip>) tripConsumer.deserialiseTripData();

        //when
        final var busTripBilling = new BusTripBilling(tripList);
        final var result = busTripBilling.processList();

        //then
        assertAll(
                () -> assertThat(result).isNotNull(),
                () -> assertThat(result.size()).isEqualTo(2),
                () -> assertThat(result.get(0).transactionType()).isEqualTo(TransactionType.Completed)
        );
    }

}