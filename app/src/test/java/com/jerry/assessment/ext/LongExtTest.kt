package com.jerry.assessment.ext

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class LongExtTest {


    @Test
    fun `test Long toDateString`(){
        val dateLong: Long = 1719567000
        Assertions.assertEquals(
            "2024-06-28 10:30:00", dateLong.toDateString()
        )
    }

    @Test
    fun `test Long with zero toDateString`(){
        val dateLong: Long = 0L
        Assertions.assertEquals(
            "---", dateLong.toDateString()
        )
    }


    //duration
    @Test
    fun `test Long convertDuration with hour`(){
        val durationInSeconds: Long = 5501
        val formattedDuration = durationInSeconds.toDuration()
        Assertions.assertEquals(
            "1:31:41", formattedDuration
        )
    }

    @Test
    fun `test convertDuration without hour`(){
        val durationInSeconds: Long = 2648
        val formattedDuration = durationInSeconds.toDuration()
        Assertions.assertEquals(
            "44:08", formattedDuration
        )
    }

    //convertTimeFormat
    @Test
    fun `test Long convertTimeFormat without hour`(){
        val milliseconds : Long = 27007
        val timeStr = milliseconds.convertTimeFormat()
        Assertions.assertEquals(
            "00:27", timeStr
        )
    }

    @Test
    fun `test Long convertTimeFormat with hour`(){
        val milliseconds: Long = 7952000
        val timeStr = milliseconds.convertTimeFormat()
        Assertions.assertEquals(
            "02:12:32", timeStr
        )
    }
}