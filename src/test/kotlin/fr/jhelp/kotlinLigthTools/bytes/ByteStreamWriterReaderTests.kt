package fr.jhelp.kotlinLigthTools.bytes

import fr.jhelp.kotlinLight.TestCaseClass
import fr.jhelp.kotlinLight.Try
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

@TestCaseClass

class ByteStreamWriterReaderTests
{
    @Test
    fun int8Data()
    {
        try
        {
            var byteStreamWriter = ByteStreamWriter(true)
            byteStreamWriter.writeInt8(-42)
            byteStreamWriter.writeInt8(0)
            byteStreamWriter.writeInt8(73)
            var byteStreamReader = ByteStreamReader(byteStreamWriter.getData(), true)
            @Try Assertions.assertEquals(-42, byteStreamReader.readInt8())
            @Try Assertions.assertEquals(0, byteStreamReader.readInt8())
            @Try Assertions.assertEquals(73, byteStreamReader.readInt8())

            byteStreamWriter = ByteStreamWriter(false)
            byteStreamWriter.writeInt8(-42)
            byteStreamWriter.writeInt8(0)
            byteStreamWriter.writeInt8(73)
            byteStreamReader = ByteStreamReader(byteStreamWriter.getData(), false)
            @Try Assertions.assertEquals(-42, byteStreamReader.readInt8())
            @Try Assertions.assertEquals(0, byteStreamReader.readInt8())
            @Try Assertions.assertEquals(73, byteStreamReader.readInt8())
        }
        catch (error: Exception)
        {
            Assertions.fail("Issue while reading : $error")
        }
    }

    @Test
    fun uInt8Data()
    {
        try
        {
            var byteStreamWriter = ByteStreamWriter(true)
            byteStreamWriter.writeUInt8(0)
            byteStreamWriter.writeUInt8(42)
            byteStreamWriter.writeUInt8(73)
            byteStreamWriter.writeUInt8(210)
            var byteStreamReader = ByteStreamReader(byteStreamWriter.getData(), true)
            @Try Assertions.assertEquals(0, byteStreamReader.readUInt8())
            @Try Assertions.assertEquals(42, byteStreamReader.readUInt8())
            @Try Assertions.assertEquals(73, byteStreamReader.readUInt8())
            @Try Assertions.assertEquals(210, byteStreamReader.readUInt8())

            byteStreamWriter = ByteStreamWriter(false)
            byteStreamWriter.writeUInt8(0)
            byteStreamWriter.writeUInt8(42)
            byteStreamWriter.writeUInt8(73)
            byteStreamWriter.writeUInt8(210)
            byteStreamReader = ByteStreamReader(byteStreamWriter.getData(), false)
            @Try Assertions.assertEquals(0, byteStreamReader.readUInt8())
            @Try Assertions.assertEquals(42, byteStreamReader.readUInt8())
            @Try Assertions.assertEquals(73, byteStreamReader.readUInt8())
            @Try Assertions.assertEquals(210, byteStreamReader.readUInt8())
        }
        catch (error: Exception)
        {
            Assertions.fail("Issue while reading : $error")
        }
    }

    @Test
    fun int16Data()
    {
        try
        {
            var byteStreamWriter = ByteStreamWriter(true)
            byteStreamWriter.writeInt16(-666)
            byteStreamWriter.writeInt16(-73)
            byteStreamWriter.writeInt16(-42)
            byteStreamWriter.writeInt16(0)
            byteStreamWriter.writeInt16(42)
            byteStreamWriter.writeInt16(73)
            byteStreamWriter.writeInt16(666)
            var byteStreamReader = ByteStreamReader(byteStreamWriter.getData(), true)
            @Try Assertions.assertEquals(-666, byteStreamReader.readInt16())
            @Try Assertions.assertEquals(-73, byteStreamReader.readInt16())
            @Try Assertions.assertEquals(-42, byteStreamReader.readInt16())
            @Try Assertions.assertEquals(0, byteStreamReader.readInt16())
            @Try Assertions.assertEquals(42, byteStreamReader.readInt16())
            @Try Assertions.assertEquals(73, byteStreamReader.readInt16())
            @Try Assertions.assertEquals(666, byteStreamReader.readInt16())

            byteStreamWriter = ByteStreamWriter(false)
            byteStreamWriter.writeInt16(-666)
            byteStreamWriter.writeInt16(-73)
            byteStreamWriter.writeInt16(-42)
            byteStreamWriter.writeInt16(0)
            byteStreamWriter.writeInt16(42)
            byteStreamWriter.writeInt16(73)
            byteStreamWriter.writeInt16(666)
            byteStreamReader = ByteStreamReader(byteStreamWriter.getData(), false)
            @Try Assertions.assertEquals(-666, byteStreamReader.readInt16())
            @Try Assertions.assertEquals(-73, byteStreamReader.readInt16())
            @Try Assertions.assertEquals(-42, byteStreamReader.readInt16())
            @Try Assertions.assertEquals(0, byteStreamReader.readInt16())
            @Try Assertions.assertEquals(42, byteStreamReader.readInt16())
            @Try Assertions.assertEquals(73, byteStreamReader.readInt16())
            @Try Assertions.assertEquals(666, byteStreamReader.readInt16())
        }
        catch (error: Exception)
        {
            Assertions.fail("Issue while reading : $error")
        }
    }

    @Test
    fun uInt16Data()
    {
        try
        {
            var byteStreamWriter = ByteStreamWriter(true)
            byteStreamWriter.writeUInt16(6666)
            byteStreamWriter.writeUInt16(7373)
            byteStreamWriter.writeUInt16(4242)
            byteStreamWriter.writeUInt16(0)
            byteStreamWriter.writeUInt16(42)
            byteStreamWriter.writeUInt16(73)
            byteStreamWriter.writeUInt16(666)
            var byteStreamReader = ByteStreamReader(byteStreamWriter.getData(), true)
            @Try Assertions.assertEquals(6666, byteStreamReader.readUInt16())
            @Try Assertions.assertEquals(7373, byteStreamReader.readUInt16())
            @Try Assertions.assertEquals(4242, byteStreamReader.readUInt16())
            @Try Assertions.assertEquals(0, byteStreamReader.readUInt16())
            @Try Assertions.assertEquals(42, byteStreamReader.readUInt16())
            @Try Assertions.assertEquals(73, byteStreamReader.readUInt16())
            @Try Assertions.assertEquals(666, byteStreamReader.readUInt16())

            byteStreamWriter = ByteStreamWriter(false)
            byteStreamWriter.writeUInt16(6666)
            byteStreamWriter.writeUInt16(7373)
            byteStreamWriter.writeUInt16(4242)
            byteStreamWriter.writeUInt16(0)
            byteStreamWriter.writeUInt16(42)
            byteStreamWriter.writeUInt16(73)
            byteStreamWriter.writeUInt16(666)
            byteStreamReader = ByteStreamReader(byteStreamWriter.getData(), false)
            @Try Assertions.assertEquals(6666, byteStreamReader.readUInt16())
            @Try Assertions.assertEquals(7373, byteStreamReader.readUInt16())
            @Try Assertions.assertEquals(4242, byteStreamReader.readUInt16())
            @Try Assertions.assertEquals(0, byteStreamReader.readUInt16())
            @Try Assertions.assertEquals(42, byteStreamReader.readUInt16())
            @Try Assertions.assertEquals(73, byteStreamReader.readUInt16())
            @Try Assertions.assertEquals(666, byteStreamReader.readUInt16())
        }
        catch (error: Exception)
        {
            Assertions.fail("Issue while reading : $error")
        }
    }

    @Test
    fun int32Data()
    {
        try
        {
            var byteStreamWriter = ByteStreamWriter(true)
            byteStreamWriter.writeInt32(-666)
            byteStreamWriter.writeInt32(-73)
            byteStreamWriter.writeInt32(-42)
            byteStreamWriter.writeInt32(0)
            byteStreamWriter.writeInt32(42)
            byteStreamWriter.writeInt32(73)
            byteStreamWriter.writeInt32(666)
            var byteStreamReader = ByteStreamReader(byteStreamWriter.getData(), true)
            @Try Assertions.assertEquals(-666, byteStreamReader.readInt32())
            @Try Assertions.assertEquals(-73, byteStreamReader.readInt32())
            @Try Assertions.assertEquals(-42, byteStreamReader.readInt32())
            @Try Assertions.assertEquals(0, byteStreamReader.readInt32())
            @Try Assertions.assertEquals(42, byteStreamReader.readInt32())
            @Try Assertions.assertEquals(73, byteStreamReader.readInt32())
            @Try Assertions.assertEquals(666, byteStreamReader.readInt32())

            byteStreamWriter = ByteStreamWriter(false)
            byteStreamWriter.writeInt32(-666)
            byteStreamWriter.writeInt32(-73)
            byteStreamWriter.writeInt32(-42)
            byteStreamWriter.writeInt32(0)
            byteStreamWriter.writeInt32(42)
            byteStreamWriter.writeInt32(73)
            byteStreamWriter.writeInt32(666)
            byteStreamReader = ByteStreamReader(byteStreamWriter.getData(), false)
            @Try Assertions.assertEquals(-666, byteStreamReader.readInt32())
            @Try Assertions.assertEquals(-73, byteStreamReader.readInt32())
            @Try Assertions.assertEquals(-42, byteStreamReader.readInt32())
            @Try Assertions.assertEquals(0, byteStreamReader.readInt32())
            @Try Assertions.assertEquals(42, byteStreamReader.readInt32())
            @Try Assertions.assertEquals(73, byteStreamReader.readInt32())
            @Try Assertions.assertEquals(666, byteStreamReader.readInt32())
        }
        catch (error: Exception)
        {
            Assertions.fail("Issue while reading : $error")
        }
    }

    @Test
    fun uInt32Data()
    {
        try
        {
            var byteStreamWriter = ByteStreamWriter(true)
            byteStreamWriter.writeUInt32(6666L)
            byteStreamWriter.writeUInt32(7373L)
            byteStreamWriter.writeUInt32(4242L)
            byteStreamWriter.writeUInt32(0L)
            byteStreamWriter.writeUInt32(42L)
            byteStreamWriter.writeUInt32(73L)
            byteStreamWriter.writeUInt32(666L)
            var byteStreamReader = ByteStreamReader(byteStreamWriter.getData(), true)
            @Try Assertions.assertEquals(6666L, byteStreamReader.readUInt32())
            @Try Assertions.assertEquals(7373L, byteStreamReader.readUInt32())
            @Try Assertions.assertEquals(4242L, byteStreamReader.readUInt32())
            @Try Assertions.assertEquals(0L, byteStreamReader.readUInt32())
            @Try Assertions.assertEquals(42L, byteStreamReader.readUInt32())
            @Try Assertions.assertEquals(73L, byteStreamReader.readUInt32())
            @Try Assertions.assertEquals(666L, byteStreamReader.readUInt32())

            byteStreamWriter = ByteStreamWriter(false)
            byteStreamWriter.writeUInt32(6666L)
            byteStreamWriter.writeUInt32(7373L)
            byteStreamWriter.writeUInt32(4242L)
            byteStreamWriter.writeUInt32(0L)
            byteStreamWriter.writeUInt32(42L)
            byteStreamWriter.writeUInt32(73L)
            byteStreamWriter.writeUInt32(666L)
            byteStreamReader = ByteStreamReader(byteStreamWriter.getData(), false)
            @Try Assertions.assertEquals(6666L, byteStreamReader.readUInt32())
            @Try Assertions.assertEquals(7373L, byteStreamReader.readUInt32())
            @Try Assertions.assertEquals(4242L, byteStreamReader.readUInt32())
            @Try Assertions.assertEquals(0L, byteStreamReader.readUInt32())
            @Try Assertions.assertEquals(42L, byteStreamReader.readUInt32())
            @Try Assertions.assertEquals(73L, byteStreamReader.readUInt32())
            @Try Assertions.assertEquals(666L, byteStreamReader.readUInt32())
        }
        catch (error: Exception)
        {
            Assertions.fail("Issue while reading : $error")
        }
    }

    @Test
    fun int64Data()
    {
        try
        {
            var byteStreamWriter = ByteStreamWriter(true)
            byteStreamWriter.writeInt64(-666L)
            byteStreamWriter.writeInt64(-73L)
            byteStreamWriter.writeInt64(-42L)
            byteStreamWriter.writeInt64(0L)
            byteStreamWriter.writeInt64(42L)
            byteStreamWriter.writeInt64(73L)
            byteStreamWriter.writeInt64(666L)
            var byteStreamReader = ByteStreamReader(byteStreamWriter.getData(), true)
            @Try Assertions.assertEquals(-666L, byteStreamReader.readInt64())
            @Try Assertions.assertEquals(-73L, byteStreamReader.readInt64())
            @Try Assertions.assertEquals(-42L, byteStreamReader.readInt64())
            @Try Assertions.assertEquals(0L, byteStreamReader.readInt64())
            @Try Assertions.assertEquals(42L, byteStreamReader.readInt64())
            @Try Assertions.assertEquals(73L, byteStreamReader.readInt64())
            @Try Assertions.assertEquals(666L, byteStreamReader.readInt64())

            byteStreamWriter = ByteStreamWriter(false)
            byteStreamWriter.writeInt64(-666L)
            byteStreamWriter.writeInt64(-73L)
            byteStreamWriter.writeInt64(-42L)
            byteStreamWriter.writeInt64(0L)
            byteStreamWriter.writeInt64(42L)
            byteStreamWriter.writeInt64(73L)
            byteStreamWriter.writeInt64(666L)
            byteStreamReader = ByteStreamReader(byteStreamWriter.getData(), false)
            @Try Assertions.assertEquals(-666L, byteStreamReader.readInt64())
            @Try Assertions.assertEquals(-73L, byteStreamReader.readInt64())
            @Try Assertions.assertEquals(-42L, byteStreamReader.readInt64())
            @Try Assertions.assertEquals(0L, byteStreamReader.readInt64())
            @Try Assertions.assertEquals(42L, byteStreamReader.readInt64())
            @Try Assertions.assertEquals(73L, byteStreamReader.readInt64())
            @Try Assertions.assertEquals(666L, byteStreamReader.readInt64())
        }
        catch (error: Exception)
        {
            Assertions.fail("Issue while reading : $error")
        }
    }

    @Test
    fun floatData()
    {
        try
        {
            var byteStreamWriter = ByteStreamWriter(true)
            byteStreamWriter.writeFloat(-123.456f)
            byteStreamWriter.writeFloat(-42f)
            byteStreamWriter.writeFloat(0f)
            byteStreamWriter.writeFloat(73f)
            byteStreamWriter.writeFloat(666.666f)
            var byteStreamReader = ByteStreamReader(byteStreamWriter.getData(), true)
            @Try Assertions.assertEquals(-123.456f, byteStreamReader.readFloat())
            @Try Assertions.assertEquals(-42f, byteStreamReader.readFloat())
            @Try Assertions.assertEquals(0f, byteStreamReader.readFloat())
            @Try Assertions.assertEquals(73f, byteStreamReader.readFloat())
            @Try Assertions.assertEquals(666.666f, byteStreamReader.readFloat())

            byteStreamWriter = ByteStreamWriter(false)
            byteStreamWriter.writeFloat(-123.456f)
            byteStreamWriter.writeFloat(-42f)
            byteStreamWriter.writeFloat(0f)
            byteStreamWriter.writeFloat(73f)
            byteStreamWriter.writeFloat(666.666f)
            byteStreamReader = ByteStreamReader(byteStreamWriter.getData(), false)
            @Try Assertions.assertEquals(-123.456f, byteStreamReader.readFloat())
            @Try Assertions.assertEquals(-42f, byteStreamReader.readFloat())
            @Try Assertions.assertEquals(0f, byteStreamReader.readFloat())
            @Try Assertions.assertEquals(73f, byteStreamReader.readFloat())
            @Try Assertions.assertEquals(666.666f, byteStreamReader.readFloat())
        }
        catch (error: Exception)
        {
            Assertions.fail("Issue while reading : $error")
        }
    }
}