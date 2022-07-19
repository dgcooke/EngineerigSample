package com.smartphone.engineeringsample.tripconsumer.trip;


import com.smartphone.engineeringsample.tripconsumer.TapType;
import com.smartphone.engineeringsample.tripconsumer.bus.Bus;
import com.smartphone.engineeringsample.tripconsumer.company.Company;
import com.smartphone.engineeringsample.tripconsumer.stop.Stop;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class ImmutableTrip implements Trip
{
	private static int ID_POSITION = 0;
	private static int TIME_POSITION = 1;
	private static int TYPE_POSITION = 2;
	private static int STOP_POSITION = 3;
	private static int COMPANY_POSITION = 4;
	private static int BUS_POSITION = 5;
	private static int PAN_POSITION = 6;

	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	private static Logger LOGGER = Logger.getLogger(ImmutableTrip.class.getName());

	private Long id;
	private ZonedDateTime utcTime;
	private TapType tapType;
	private Stop stop;
	private Bus bus;

	private Company company;
	private String pan;
	@Override
	public Long getId() {
		return id;
	}
	@Override
	public ZonedDateTime getUtcTime() {
		return utcTime;
	}
	@Override
	public TapType getTapType() {
		return tapType;
	}
	@Override
	public Stop getStop() {
		return stop;
	}
	@Override
	public Company getCompany() {
		return company;
	}
	@Override
	public String getPan() {
		return pan;
	}

	@Override
	public Bus getBus()
	{
		return bus;
	}

	public ImmutableTrip(final List<String> inputList)
	{
		try
		{
			this.id = Long.valueOf(inputList.get(ID_POSITION).trim());
			this.utcTime = LocalDateTime.parse(inputList.get(TIME_POSITION).trim(), formatter).atZone(ZoneOffset.UTC);
			this.tapType = TapType.valueOf(inputList.get(TYPE_POSITION).trim());
			this.stop = Stop.getInstance(inputList.get(STOP_POSITION).trim());
			this.company = new Company(inputList.get(COMPANY_POSITION).trim());
			this.pan = inputList.get(PAN_POSITION).trim();
			this.bus = new Bus(inputList.get(BUS_POSITION).trim());

		}catch (Exception exception)
		{
			final var stringBuilder = new StringBuilder();
			inputList.stream().forEach(value -> {
				stringBuilder.append(value);
				stringBuilder.append(" ");
			});
			LOGGER.log(Level.WARNING, "Threw Exception while parsing Trip (details: " + stringBuilder.toString() + ")");
		}
	}

	@Override
	public String getTimeAsString()
	{
		return utcTime.format(formatter);
	}

}
