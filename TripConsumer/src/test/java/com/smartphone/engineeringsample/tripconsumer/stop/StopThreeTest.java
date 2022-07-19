package com.smartphone.engineeringsample.tripconsumer.stop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StopThreeTest {
    @Test
    protected void should_throw_exception_when_determineFee_is_passed_null()
    {
        //given
        final var stopTwo = Stop.getInstance("Stop2");
        //when
        final Executable executable = () -> stopTwo.determineFee(null);

        //then
        assertThrows(IllegalArgumentException.class, executable);
    }

    @Test
    protected void should_succeed_when_determineFee_is_passed_Stop1()
    {
        //given
        final var stopThree = Stop.getInstance("Stop3");
        final var otherStop = Stop.getInstance("Stop1");
        //when
        final var result = stopThree.determineFee(otherStop);

        //then
        assertThat(result).isEqualTo("$7.30");
    }

    @Test
    protected void should_succeed_when_determineFee_is_passed_Stop2()
    {
        //given
        final var stopThree = Stop.getInstance("Stop3");
        final var otherStop = Stop.getInstance("Stop2");
        //when
        final var result = stopThree.determineFee(otherStop);

        //then
        assertThat(result).isEqualTo("$5.50");
    }

    @Test
    protected void should_succeed_when_determineFee_is_passed_Stop3()
    {
        //given
        final var stopThree = Stop.getInstance("Stop3");
        final var otherStop = Stop.getInstance("Stop3");
        //when
        final var result = stopThree.determineFee(otherStop);

        //then
        assertThat(result).isEqualTo("$0.00");
    }
}