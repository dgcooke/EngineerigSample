package com.smartphone.engineeringsample.tripconsumer.bus;

public record Bus(String busId)
{
	public String getShortName()
	{
		if(busId.contains("Bus"))
		{
			return busId.replace("Bus", "B");
		}
		return busId;
	}
}
