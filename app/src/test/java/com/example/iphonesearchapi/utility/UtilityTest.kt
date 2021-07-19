package com.example.iphonesearchapi.utility

import junit.framework.TestCase
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat

class UtilityTest : TestCase() {

    fun testNew_string_returnsTrue() {

        //GIVEN //WHEN
        val result=new("yes","yes")

        //THEN
        assertThat(result,`is` (true) )
    }
}