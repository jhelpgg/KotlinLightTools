package fr.jhelp.kotlinLigthTools.math

import fr.jhelp.kotlinLight.TestCaseClass
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

@TestCaseClass

class MathToolsTests
{
    @Test
    fun signumTest()
    {
        Assertions.assertEquals(1, signum(5f))
        Assertions.assertEquals(-1, signum(-55f))
        Assertions.assertEquals(0, signum(0f))
    }

    @Test
    fun absoluteTest()
    {
        Assertions.assertEquals(5f, absolute(5f))
        Assertions.assertEquals(25f, absolute(-25f))
        Assertions.assertEquals(0f, absolute(0f))
    }

    @Test
    fun limitIntegerTest()
    {
        Assertions.assertEquals(-8, limitInteger(-10, 5, -8))
        Assertions.assertEquals(0, limitInteger(0, -42, 73))
        Assertions.assertEquals(73, limitInteger(666, -42, 73))
    }

    @Test
    fun floatToIntBytesTest()
    {
        // value=1.0 , intBits=1065353216
        Assertions.assertEquals(1065353216, floatToIntBytes(1.0f))
        // value=-1234.5677 , intBits=-996519381
        Assertions.assertEquals(-996519381, floatToIntBytes(-1234.5677f))
        // value=1234.5677 , intBits=1150964267
        Assertions.assertEquals(1150964267, floatToIntBytes(1234.5677f))
        // value=0 , intBits=0
        Assertions.assertEquals(0, floatToIntBytes(0.0f))
    }

    @Test
    fun floatFromIntBytesTest()
    {
        // value=1.0 , intBits=1065353216
        Assertions.assertEquals(1.0f, floatFromIntBytes(1065353216))
        // value=-1234.5677 , intBits=-996519381
        Assertions.assertEquals(-1234.5677f, floatFromIntBytes(-996519381))
        // value=1234.5677 , intBits=1150964267
        Assertions.assertEquals(1234.5677f, floatFromIntBytes(1150964267))
        // value=0 , intBits=0
        Assertions.assertEquals(0.0f, floatFromIntBytes(0))
    }
}