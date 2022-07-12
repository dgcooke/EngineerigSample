package com.smartphone.engineeringsample.tripsimulator;

import com.smartphone.engineeringsample.tripconsumer.BusTripConsumer;
import com.smartphone.engineeringsample.tripconsumer.TripConsumer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TripSimulator
{
    public static void main(String[] args)
    {
        System.out.println("Hello Modules");
        if(args.length > 0)
        {
            final var inputFile = new File(args[0]);
            if((inputFile != null) && (inputFile.exists()))
            {
                try
                {
                    final TripConsumer tripConsumer = new BusTripConsumer(new FileInputStream(inputFile)) ;

                } catch (FileNotFoundException nfn)
                {
                    System.out.println("File provided was not found");
                }
            }else
            {
                System.out.println("File name provided was null, or file does not exist");
            }
        }else
        {
            System.out.println("Input file name needs to be provided");
        }
    }
}
