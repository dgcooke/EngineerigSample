package com.smartphone.engineeringsample.tripconsumer.stop;

public class StopTwo extends Stop
{
    public StopTwo(final String stopName)
    {
        super(stopName);
    }

    @Override
    public String determineFee(Stop destination) {

        if(destination instanceof StopTwo)
        {
            return "$0.00";

        }else if(destination instanceof StopThree)
        {
            return "$5.50";
        }else if(destination instanceof StopOne)
        {
            return "$3.25";
        }

        throw new IllegalArgumentException("Invalid destination");
    }
}
