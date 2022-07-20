package com.smartphone.engineeringsample.tripsimulator;

import com.smartphone.engineeringsample.tripconsumer.BusTripConsumer;
import com.smartphone.engineeringsample.tripconsumer.TripConsumer;
import com.smartphone.engineeringsample.tripconsumer.billing.BusTripBilling;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TripSimulator
{
    public static void main(String[] args)
    {
        if(args.length > 0)
        {
            final var inputFile = new File(args[0]);
            if((inputFile != null) && (inputFile.exists()))
            {
                try
                {
                    final TripConsumer tripConsumer = new BusTripConsumer(new FileInputStream(inputFile));
                    final var busTripBilling = new BusTripBilling(tripConsumer.deserialiseTripData());
                    final var result = busTripBilling.processList();
                    result.forEach(transaction -> {
                        System.out.println(transaction.generateOutput());
                    });
                } catch (FileNotFoundException nfn)
                {
                    System.out.println("File provided was not found");
                } catch (IllegalArgumentException ie)
                {
                    System.out.println("IllegalArgumentException was caught: "+ie.getLocalizedMessage());
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
