package com.smartphone.engineeringsample.tripconsumer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class BusTripBillingTest {
    @Test
    protected void constructor_will_fail_if_null_value_passed_as_argument()
    {
        //given
        //when
        Executable executable = () -> new BusTripConsumer(null);

        //then
        assertThrows(IllegalArgumentException.class, executable);
    }
}