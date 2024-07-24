package com.concordia.canary.authmodule.util

import org.junit.Test

class StringExtensionTest {


    @Test
    fun `test string contains no number returns false`() {
        val result = "NoNumber".containsNumber()
        assert(!result)
    }

    @Test
    fun `test string contains no uppercase returns false`() {
        val result = "nonumber".containsUpperCase()
        assert(!result)
    }

    @Test
    fun `test string contains no special char returns false`() {
        val result = "nospec".containsSpecialChar()
        assert(!result)
    }

    @Test
    fun `test string contains a special char returns true`() {
        val result = "nospec@".containsSpecialChar()
        assert(result)
    }
}