package com.smartphone.engineeringsample.tripconsumer.stop;

public class StopOne extends Stop
{
    public StopOne(final String stopName)
    {
        super(stopName);
    }

    @Override
    public String determineFee(Stop destination)
    {
        if(destination instanceof StopTwo)
        {
            return "$3.25";
        }else if(destination instanceof StopThree)
        {
            return "$7.30";
        }else if(destination instanceof StopOne)
        {
            return "$0.00";
        }

        throw new IllegalArgumentException("Invalid destination");
    }
}
