package com.smartphone.engineeringsample.tripconsumer;

import com.smartphone.engineeringsample.tripconsumer.MutableTrip.MutableTrip;
import com.smartphone.engineeringsample.tripconsumer.company.Company;
import com.smartphone.engineeringsample.tripconsumer.stop.Stop;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public sealed class ImmutableTrip implements Trip permits MutableTrip
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

	private Company company;
	private String pan;

	public Long getId() {
		return id;
	}

	public ZonedDateTime getUtcTime() {
		return utcTime;
	}

	public TapType getTapType() {
		return tapType;
	}

	public Stop getStop() {
		return stop;
	}

	public Company getCompany() {
		return company;
	}

	public String getPan() {
		return pan;
	}

	private ImmutableTrip(final Long id, final ZonedDateTime utcTime, final TapType tapType, final Stop stop, final String pan)
	{
		this.id = id;
		this.utcTime = utcTime;
		this.tapType = tapType;
		this.stop = stop;
		this.pan = pan;
	}

	public ImmutableTrip(final List<String> inputList)
	{
		try
		{
			this.id = Long.valueOf(inputList.get(ID_POSITION).trim());
			this.utcTime = LocalDateTime.parse(inputList.get(TIME_POSITION).trim(), formatter).atZone(ZoneOffset.UTC);
			this.tapType = TapType.valueOf(inputList.get(TYPE_POSITION).trim());
			this.stop = new Stop(inputList.get(STOP_POSITION).trim());
			this.company = new Company(inputList.get(COMPANY_POSITION).trim());
			this.pan = inputList.get(PAN_POSITION).trim();

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

	public ImmutableTrip()
	{
		//TODO please remove me
	}
}
