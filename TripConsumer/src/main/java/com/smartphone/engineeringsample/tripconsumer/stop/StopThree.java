package com.smartphone.engineeringsample.tripconsumer.stop;

public class StopThree extends Stop
{
    public StopThree(final String stopName)
    {
        super(stopName);
    }

    @Override
    public String determineFee(Stop destination) {

        if(destination instanceof StopTwo)
        {
            return "$5.50";

        }else if(destination instanceof StopThree)
        {
            return "$0.00";
        }else if(destination instanceof StopOne)
        {
            return "$7.30";
        }

        throw new IllegalArgumentException("Invalid destination");
    }
}
