package com.basta.demo.presentation

import org.junit.Assert
import org.junit.Test

class ExtentionsTest {

    @Test
    fun full_string_date_toDateFormat_isCorrect() {
        val date = "2023-03-01T16:40:00Z"
        val result = "2023-03-01"
        Assert.assertEquals(date.toDateFormat(), result)
    }
}