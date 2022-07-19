package com.smartphone.engineeringsample.tripconsumer.stop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class StopOneTest {

    @Test
    protected void should_throw_exception_destination_is_invalid()
    {
        //given
        //when
        final Executable executable = () -> Stop.getInstance("");

        //then
        assertThrows(IllegalArgumentException.class, executable);
    }

    @Test
    protected void should_throw_exception_destination_is_null()
    {
        //given
        //when
        final Executable executable = () -> Stop.getInstance(null);

        //then
        assertThrows(IllegalArgumentException.class, executable);
    }

    @Test
    protected void should_throw_exception_when_determineFee_is_passed_null()
    {
        //given
        final var stopOne = Stop.getInstance("Stop1");
        //when
        final Executable executable = () -> stopOne.determineFee(null);

        //then
        assertThrows(IllegalArgumentException.class, executable);
    }

    @Test
    protected void should_succeed_when_determineFee_is_passed_Stop1()
    {
        //given
        final var stopOne = Stop.getInstance("Stop1");
        final var stopTwo = Stop.getInstance("Stop1");
        //when
        final var result = stopOne.determineFee(stopTwo);

        //then
        assertThat(result).isEqualTo("$0.00");
    }

    @Test
    protected void should_succeed_when_determineFee_is_passed_Stop2()
    {
        //given
        final var stopOne = Stop.getInstance("Stop1");
        final var stopTwo = Stop.getInstance("Stop2");
        //when
        final var result = stopOne.determineFee(stopTwo);

        //then
        assertThat(result).isEqualTo("$3.25");
    }

    @Test
    protected void should_succeed_when_determineFee_is_passed_Stop3()
    {
        //given
        final var stopOne = Stop.getInstance("Stop1");
        final var stopTwo = Stop.getInstance("Stop3");
        //when
        final var result = stopOne.determineFee(stopTwo);

        //then
        assertThat(result).isEqualTo("$7.30");
    }
}