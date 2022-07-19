package com.smartphone.engineeringsample.tripconsumer.stop;

public class Stop implements StopOperations
{
    protected final String stopName;

    protected  Stop(final String stopName)
    {
        this.stopName = stopName;
    }

    public static Stop getInstance(final String stopName)
    {
        return switch (stopName)
        {
            case "Stop1"  -> new StopOne(stopName);
            case "Stop2" -> new StopTwo(stopName);
            case "Stop3" -> new StopThree(stopName);
                default -> throw new IllegalArgumentException(stopName + " Not valid input");
        };
    }
    public String getStopName()
    {
        return stopName;
    }

    @Override
    public String determineFee(Stop destination) {
        throw new IllegalArgumentException("Can not be executed from base class");
    }
}
