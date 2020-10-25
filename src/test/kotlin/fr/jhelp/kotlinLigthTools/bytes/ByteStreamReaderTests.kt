package fr.jhelp.kotlinLigthTools.bytes

import fr.jhelp.kotlinLight.CommonList
import fr.jhelp.kotlinLight.TestCaseClass
import fr.jhelp.kotlinLight.Try
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

@TestCaseClass

class ByteStreamReaderTests
{
    // BigEndian

    @Test
    fun readInt8BigEndian()
    {
        try
        {
            var data = CommonList<Byte>()
            data.append(1.toByte())
            data.append((-25).toByte())
            data.append(125.toByte())
            data.append((-125).toByte())
            val byteStreamReader = ByteStreamReader(data, true)
            @Try var byte = byteStreamReader.readInt8()
            Assertions.assertEquals(1, byte)
            @Try byte = byteStreamReader.readInt8()
            Assertions.assertEquals(-25, byte)
            @Try byte = byteStreamReader.readInt8()
            Assertions.assertEquals(125, byte)
            @Try byte = byteStreamReader.readInt8()
            Assertions.assertEquals(-125, byte)
        }
        catch (error: Exception)
        {
            Assertions.fail("Read failed : $error")
        }
    }

    @Test
    fun readUInt8BigEndian()
    {
        try
        {
            var data = CommonList<Byte>()
            data.append(1.toByte())
            data.append(128.toByte())
            data.append(135.toByte())
            data.append(254.toByte())
            val byteStreamReader = ByteStreamReader(data, true)
            @Try var byte = byteStreamReader.readUInt8()
            Assertions.assertEquals(1, byte)
            @Try byte = byteStreamReader.readUInt8()
            Assertions.assertEquals(128, byte)
            @Try byte = byteStreamReader.readUInt8()
            Assertions.assertEquals(135, byte)
            @Try byte = byteStreamReader.readUInt8()
            Assertions.assertEquals(254, byte)
        }
        catch (error: Exception)
        {
            Assertions.fail("Read failed : $error")
        }
    }

    @Test
    fun readInt16BigEndian()
    {
        try
        {
            var data = CommonList<Byte>()
            var value = 1
            data.append(((value shr 8) and 0xFF).toByte())
            data.append((value and 0xFF).toByte())
            value = -128
            data.append(((value shr 8) and 0xFF).toByte())
            data.append((value and 0xFF).toByte())
            value = -32100
            data.append(((value shr 8) and 0xFF).toByte())
            data.append((value and 0xFF).toByte())
            value = 32100
            data.append(((value shr 8) and 0xFF).toByte())
            data.append((value and 0xFF).toByte())
            val byteStreamReader = ByteStreamReader(data, true)
            @Try var byte = byteStreamReader.readInt16()
            Assertions.assertEquals(1, byte)
            @Try byte = byteStreamReader.readInt16()
            Assertions.assertEquals(-128, byte)
            @Try byte = byteStreamReader.readInt16()
            Assertions.assertEquals(-32100, byte)
            @Try byte = byteStreamReader.readInt16()
            Assertions.assertEquals(32100, byte)
        }
        catch (error: Exception)
        {
            Assertions.fail("Read failed : $error")
        }
    }

    @Test
    fun readUInt16BigEndian()
    {
        try
        {
            var data = CommonList<Byte>()
            var value = 1
            data.append(((value shr 8) and 0xFF).toByte())
            data.append((value and 0xFF).toByte())
            value = 65432
            data.append(((value shr 8) and 0xFF).toByte())
            data.append((value and 0xFF).toByte())
            value = 32211
            data.append(((value shr 8) and 0xFF).toByte())
            data.append((value and 0xFF).toByte())
            value = 11100
            data.append(((value shr 8) and 0xFF).toByte())
            data.append((value and 0xFF).toByte())
            val byteStreamReader = ByteStreamReader(data, true)
            @Try var byte = byteStreamReader.readUInt16()
            Assertions.assertEquals(1, byte)
            @Try byte = byteStreamReader.readUInt16()
            Assertions.assertEquals(65432, byte)
            @Try byte = byteStreamReader.readUInt16()
            Assertions.assertEquals(32211, byte)
            @Try byte = byteStreamReader.readUInt16()
            Assertions.assertEquals(11100, byte)
        }
        catch (error: Exception)
        {
            Assertions.fail("Read failed : $error")
        }
    }

    @Test
    fun readInt32BigEndian()
    {
        try
        {
            var data = CommonList<Byte>()
            var value = 1
            data.append(((value shr 24) and 0xFF).toByte())
            data.append(((value shr 16) and 0xFF).toByte())
            data.append(((value shr 8) and 0xFF).toByte())
            data.append((value and 0xFF).toByte())
            value = 123456
            data.append(((value shr 24) and 0xFF).toByte())
            data.append(((value shr 16) and 0xFF).toByte())
            data.append(((value shr 8) and 0xFF).toByte())
            data.append((value and 0xFF).toByte())
            value = -123456
            data.append(((value shr 24) and 0xFF).toByte())
            data.append(((value shr 16) and 0xFF).toByte())
            data.append(((value shr 8) and 0xFF).toByte())
            data.append((value and 0xFF).toByte())
            value = 248163264
            data.append(((value shr 24) and 0xFF).toByte())
            data.append(((value shr 16) and 0xFF).toByte())
            data.append(((value shr 8) and 0xFF).toByte())
            data.append((value and 0xFF).toByte())
            value = -248163264
            data.append(((value shr 24) and 0xFF).toByte())
            data.append(((value shr 16) and 0xFF).toByte())
            data.append(((value shr 8) and 0xFF).toByte())
            data.append((value and 0xFF).toByte())
            val byteStreamReader = ByteStreamReader(data, true)
            @Try var byte = byteStreamReader.readInt32()
            Assertions.assertEquals(1, byte)
            @Try byte = byteStreamReader.readInt32()
            Assertions.assertEquals(123456, byte)
            @Try byte = byteStreamReader.readInt32()
            Assertions.assertEquals(-123456, byte)
            @Try byte = byteStreamReader.readInt32()
            Assertions.assertEquals(248163264, byte)
            @Try byte = byteStreamReader.readInt32()
            Assertions.assertEquals(-248163264, byte)
        }
        catch (error: Exception)
        {
            Assertions.fail("Read failed : $error")
        }
    }

    @Test
    fun readUInt32BigEndian()
    {
        try
        {
            var data = CommonList<Byte>()
            var value = 1
            data.append(((value shr 24) and 0xFF).toByte())
            data.append(((value shr 16) and 0xFF).toByte())
            data.append(((value shr 8) and 0xFF).toByte())
            data.append((value and 0xFF).toByte())
            value = 123456
            data.append(((value shr 24) and 0xFF).toByte())
            data.append(((value shr 16) and 0xFF).toByte())
            data.append(((value shr 8) and 0xFF).toByte())
            data.append((value and 0xFF).toByte())
            value = 123456789
            data.append(((value shr 24) and 0xFF).toByte())
            data.append(((value shr 16) and 0xFF).toByte())
            data.append(((value shr 8) and 0xFF).toByte())
            data.append((value and 0xFF).toByte())
            value = 248163264
            data.append(((value shr 24) and 0xFF).toByte())
            data.append(((value shr 16) and 0xFF).toByte())
            data.append(((value shr 8) and 0xFF).toByte())
            data.append((value and 0xFF).toByte())
            value = 1248163264
            data.append(((value shr 24) and 0xFF).toByte())
            data.append(((value shr 16) and 0xFF).toByte())
            data.append(((value shr 8) and 0xFF).toByte())
            data.append((value and 0xFF).toByte())
            val byteStreamReader = ByteStreamReader(data, true)
            @Try var byte = byteStreamReader.readUInt32()
            Assertions.assertEquals(1L, byte)
            @Try byte = byteStreamReader.readUInt32()
            Assertions.assertEquals(123456L, byte)
            @Try byte = byteStreamReader.readUInt32()
            Assertions.assertEquals(123456789L, byte)
            @Try byte = byteStreamReader.readUInt32()
            Assertions.assertEquals(248163264L, byte)
            @Try byte = byteStreamReader.readUInt32()
            Assertions.assertEquals(1248163264L, byte)
        }
        catch (error: Exception)
        {
            Assertions.fail("Read failed : $error")
        }
    }

    @Test
    fun readFloatBigEndian()
    {
        try
        {
            var data = CommonList<Byte>()

            // value=1.0 , intBits=1065353216 | hex=3f 80 00 00
            data.append(0x3F.toByte())
            data.append(0x80.toByte())
            data.append(0x00.toByte())
            data.append(0x00.toByte())

            // value=-1234.5677 , intBits=-996519381 | hex=c4 9a 52 2b
            data.append(0xC4.toByte())
            data.append(0x9A.toByte())
            data.append(0x52.toByte())
            data.append(0x2B.toByte())

            // value=1234.5677 , intBits=1150964267 | hex=44 9a 52 2b
            data.append(0x44.toByte())
            data.append(0x9A.toByte())
            data.append(0x52.toByte())
            data.append(0x2B.toByte())

            val byteStreamReader = ByteStreamReader(data, true)
            @Try var byte = byteStreamReader.readFloat()
            Assertions.assertEquals(1f, byte)
            @Try byte = byteStreamReader.readFloat()
            Assertions.assertEquals(-1234.5678f, byte)
            @Try byte = byteStreamReader.readFloat()
            Assertions.assertEquals(1234.5678f, byte)
        }
        catch (error: Exception)
        {
            Assertions.fail("Read failed : $error")
        }
    }

    // LittleEndian

    @Test
    fun readInt8LittleEndian()
    {
        try
        {
            var data = CommonList<Byte>()
            data.append(1.toByte())
            data.append((-25).toByte())
            data.append(125.toByte())
            data.append((-125).toByte())
            val byteStreamReader = ByteStreamReader(data, false)
            @Try var byte = byteStreamReader.readInt8()
            Assertions.assertEquals(1, byte)
            @Try byte = byteStreamReader.readInt8()
            Assertions.assertEquals(-25, byte)
            @Try byte = byteStreamReader.readInt8()
            Assertions.assertEquals(125, byte)
            @Try byte = byteStreamReader.readInt8()
            Assertions.assertEquals(-125, byte)
        }
        catch (error: Exception)
        {
            Assertions.fail("Read failed : $error")
        }
    }

    @Test
    fun readUInt8LittleEndian()
    {
        try
        {
            var data = CommonList<Byte>()
            data.append(1.toByte())
            data.append(128.toByte())
            data.append(135.toByte())
            data.append(254.toByte())
            val byteStreamReader = ByteStreamReader(data, false)
            @Try var byte = byteStreamReader.readUInt8()
            Assertions.assertEquals(1, byte)
            @Try byte = byteStreamReader.readUInt8()
            Assertions.assertEquals(128, byte)
            @Try byte = byteStreamReader.readUInt8()
            Assertions.assertEquals(135, byte)
            @Try byte = byteStreamReader.readUInt8()
            Assertions.assertEquals(254, byte)
        }
        catch (error: Exception)
        {
            Assertions.fail("Read failed : $error")
        }
    }

    @Test
    fun readInt16LittleEndian()
    {
        try
        {
            var data = CommonList<Byte>()
            var value = 1
            data.append((value and 0xFF).toByte())
            data.append(((value shr 8) and 0xFF).toByte())
            value = -128
            data.append((value and 0xFF).toByte())
            data.append(((value shr 8) and 0xFF).toByte())
            value = -32100
            data.append((value and 0xFF).toByte())
            data.append(((value shr 8) and 0xFF).toByte())
            value = 32100
            data.append((value and 0xFF).toByte())
            data.append(((value shr 8) and 0xFF).toByte())
            val byteStreamReader = ByteStreamReader(data, false)
            @Try var byte = byteStreamReader.readInt16()
            Assertions.assertEquals(1, byte)
            @Try byte = byteStreamReader.readInt16()
            Assertions.assertEquals(-128, byte)
            @Try byte = byteStreamReader.readInt16()
            Assertions.assertEquals(-32100, byte)
            @Try byte = byteStreamReader.readInt16()
            Assertions.assertEquals(32100, byte)
        }
        catch (error: Exception)
        {
            Assertions.fail("Read failed : $error")
        }
    }

    @Test
    fun readUInt16LittleEndian()
    {
        try
        {
            var data = CommonList<Byte>()
            var value = 1
            data.append((value and 0xFF).toByte())
            data.append(((value shr 8) and 0xFF).toByte())
            value = 65432
            data.append((value and 0xFF).toByte())
            data.append(((value shr 8) and 0xFF).toByte())
            value = 32211
            data.append((value and 0xFF).toByte())
            data.append(((value shr 8) and 0xFF).toByte())
            value = 11100
            data.append((value and 0xFF).toByte())
            data.append(((value shr 8) and 0xFF).toByte())
            val byteStreamReader = ByteStreamReader(data, false)
            @Try var byte = byteStreamReader.readUInt16()
            Assertions.assertEquals(1, byte)
            @Try byte = byteStreamReader.readUInt16()
            Assertions.assertEquals(65432, byte)
            @Try byte = byteStreamReader.readUInt16()
            Assertions.assertEquals(32211, byte)
            @Try byte = byteStreamReader.readUInt16()
            Assertions.assertEquals(11100, byte)
        }
        catch (error: Exception)
        {
            Assertions.fail("Read failed : $error")
        }
    }

    @Test
    fun readInt32LittleEndian()
    {
        try
        {
            var data = CommonList<Byte>()
            var value = 1
            data.append((value and 0xFF).toByte())
            data.append(((value shr 8) and 0xFF).toByte())
            data.append(((value shr 16) and 0xFF).toByte())
            data.append(((value shr 24) and 0xFF).toByte())
            value = 123456
            data.append((value and 0xFF).toByte())
            data.append(((value shr 8) and 0xFF).toByte())
            data.append(((value shr 16) and 0xFF).toByte())
            data.append(((value shr 24) and 0xFF).toByte())
            value = -123456
            data.append((value and 0xFF).toByte())
            data.append(((value shr 8) and 0xFF).toByte())
            data.append(((value shr 16) and 0xFF).toByte())
            data.append(((value shr 24) and 0xFF).toByte())
            value = 248163264
            data.append((value and 0xFF).toByte())
            data.append(((value shr 8) and 0xFF).toByte())
            data.append(((value shr 16) and 0xFF).toByte())
            data.append(((value shr 24) and 0xFF).toByte())
            value = -248163264
            data.append((value and 0xFF).toByte())
            data.append(((value shr 8) and 0xFF).toByte())
            data.append(((value shr 16) and 0xFF).toByte())
            data.append(((value shr 24) and 0xFF).toByte())
            val byteStreamReader = ByteStreamReader(data, false)
            @Try var byte = byteStreamReader.readInt32()
            Assertions.assertEquals(1, byte)
            @Try byte = byteStreamReader.readInt32()
            Assertions.assertEquals(123456, byte)
            @Try byte = byteStreamReader.readInt32()
            Assertions.assertEquals(-123456, byte)
            @Try byte = byteStreamReader.readInt32()
            Assertions.assertEquals(248163264, byte)
            @Try byte = byteStreamReader.readInt32()
            Assertions.assertEquals(-248163264, byte)
        }
        catch (error: Exception)
        {
            Assertions.fail("Read failed : $error")
        }
    }

    @Test
    fun readUInt32LittleEndian()
    {
        try
        {
            var data = CommonList<Byte>()
            var value = 1
            data.append((value and 0xFF).toByte())
            data.append(((value shr 8) and 0xFF).toByte())
            data.append(((value shr 16) and 0xFF).toByte())
            data.append(((value shr 24) and 0xFF).toByte())
            value = 123456
            data.append((value and 0xFF).toByte())
            data.append(((value shr 8) and 0xFF).toByte())
            data.append(((value shr 16) and 0xFF).toByte())
            data.append(((value shr 24) and 0xFF).toByte())
            value = 123456789
            data.append((value and 0xFF).toByte())
            data.append(((value shr 8) and 0xFF).toByte())
            data.append(((value shr 16) and 0xFF).toByte())
            data.append(((value shr 24) and 0xFF).toByte())
            value = 248163264
            data.append((value and 0xFF).toByte())
            data.append(((value shr 8) and 0xFF).toByte())
            data.append(((value shr 16) and 0xFF).toByte())
            data.append(((value shr 24) and 0xFF).toByte())
            value = 1248163264
            data.append((value and 0xFF).toByte())
            data.append(((value shr 8) and 0xFF).toByte())
            data.append(((value shr 16) and 0xFF).toByte())
            data.append(((value shr 24) and 0xFF).toByte())
            val byteStreamReader = ByteStreamReader(data, false)
            @Try var byte = byteStreamReader.readUInt32()
            Assertions.assertEquals(1L, byte)
            @Try byte = byteStreamReader.readUInt32()
            Assertions.assertEquals(123456L, byte)
            @Try byte = byteStreamReader.readUInt32()
            Assertions.assertEquals(123456789L, byte)
            @Try byte = byteStreamReader.readUInt32()
            Assertions.assertEquals(248163264L, byte)
            @Try byte = byteStreamReader.readUInt32()
            Assertions.assertEquals(1248163264L, byte)
        }
        catch (error: Exception)
        {
            Assertions.fail("Read failed : $error")
        }
    }

    @Test
    fun readFloatLittleEndian()
    {
        try
        {
            var data = CommonList<Byte>()

            // value=1.0 , intBits=1065353216 | hex=3f 80 00 00
            data.append(0x00.toByte())
            data.append(0x00.toByte())
            data.append(0x80.toByte())
            data.append(0x3F.toByte())

            // value=-1234.5677 , intBits=-996519381 | hex=c4 9a 52 2b
            data.append(0x2B.toByte())
            data.append(0x52.toByte())
            data.append(0x9A.toByte())
            data.append(0xC4.toByte())

            // value=1234.5677 , intBits=1150964267 | hex=44 9a 52 2b
            data.append(0x2B.toByte())
            data.append(0x52.toByte())
            data.append(0x9A.toByte())
            data.append(0x44.toByte())

            val byteStreamReader = ByteStreamReader(data, false)
            @Try var byte = byteStreamReader.readFloat()
            Assertions.assertEquals(1f, byte)
            @Try byte = byteStreamReader.readFloat()
            Assertions.assertEquals(-1234.5678f, byte)
            @Try byte = byteStreamReader.readFloat()
            Assertions.assertEquals(1234.5678f, byte)
        }
        catch (error: Exception)
        {
            Assertions.fail("Read failed : $error")
        }
    }
}