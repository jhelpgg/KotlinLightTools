package fr.jhelp.kotlinLigthTools.bytes


import fr.jhelp.kotlinLight.CommonList
import fr.jhelp.kotlinLight.ImportSwift
import fr.jhelp.kotlinLight.Try
import fr.jhelp.kotlinLight.guard
import fr.jhelp.kotlinLigthTools.exceptions.IllegalArgumentException
import fr.jhelp.kotlinLigthTools.exceptions.IllegalStateException
import java.lang.Math.pow

@ImportSwift("Foundation")

class ByteStreamReader
{
    private val data: CommonList<Byte>
    private val bigEndian: Boolean
    private var position: Int

    constructor(data: CommonList<Byte>, bigEndian: Boolean)
    {
        this.data = data
        this.bigEndian = bigEndian
        this.position = 0
    }

    fun getPosition(): Int
    {
        return this.position
    }

    @Throws
    fun setPosition(position: Int)
    {
        (position >= 0 && position < this.data.count).guard { throw IllegalArgumentException("position must be in [0, ${this.data.count}[ not $position") }
        this.position = position
    }

    fun getSize(): Int
    {
        return this.data.count
    }

    fun remaining(): Int
    {
        return this.data.count - this.position
    }

    fun rewind()
    {
        this.position = 0
    }

    @Throws
    fun readInt8(): Int
    {
        @Try this.checkEnoughData(1)
        val value = this.data[this.position].toInt()
        this.position++
        return value
    }

    @Throws
    fun readUInt8(): Int
    {
        @Try return this.readInt8() and 0xFF
    }

    @Throws
    fun readInt16(): Int
    {
        @Try this.checkEnoughData(2)
        val byte1 = this.data[this.position].toInt()
        val byte2 = this.data[this.position + 1].toInt()
        this.position += 2

        if (this.bigEndian)
        {
            return (byte1 shl 8) or (byte2 and 0xFF)
        }

        return (byte2 shl 8) or (byte1 and 0xFF)
    }

    @Throws
    fun readUInt16(): Int
    {
        @Try this.checkEnoughData(2)
        val byte1 = this.data[this.position].toInt()
        val byte2 = this.data[this.position + 1].toInt()
        this.position += 2

        if (this.bigEndian)
        {
            return ((byte1 and 0xFF) shl 8) or (byte2 and 0xFF)
        }

        return ((byte2 and 0xFF) shl 8) or (byte1 and 0xFF)
    }

    @Throws
    fun readInt32(): Int
    {
        @Try this.checkEnoughData(4)
        val byte1 = this.data[this.position].toInt()
        val byte2 = this.data[this.position + 1].toInt()
        val byte3 = this.data[this.position + 2].toInt()
        val byte4 = this.data[this.position + 3].toInt()
        this.position += 4

        if (this.bigEndian)
        {
            var result = byte1 shl 24
            result = result or ((byte2 and 0xFF) shl 16)
            result = result or ((byte3 and 0xFF) shl 8)
            return result or (byte4 and 0xFF)
        }

        var result = byte4 shl 24
        result = result or ((byte3 and 0xFF) shl 16)
        result = result or ((byte2 and 0xFF) shl 8)
        return result or (byte1 and 0xFF)
    }

    @Throws
    fun readUInt32(): Long
    {
        @Try this.checkEnoughData(4)
        val byte1 = this.data[this.position].toLong()
        val byte2 = this.data[this.position + 1].toLong()
        val byte3 = this.data[this.position + 2].toLong()
        val byte4 = this.data[this.position + 3].toLong()
        this.position += 4

        if (this.bigEndian)
        {
            var result = (byte1 and 0xFF) shl 24
            result = result or ((byte2 and 0xFF) shl 16)
            result = result or ((byte3 and 0xFF) shl 8)
            return result or (byte4 and 0xFF)
        }

        var result = (byte4 and 0xFF) shl 24
        result = result or ((byte3 and 0xFF) shl 16)
        result = result or ((byte2 and 0xFF) shl 8)
        return result or (byte1 and 0xFF)
    }

    @Throws
    fun readInt64(): Long
    {
        @Try this.checkEnoughData(8)
        val byte1 = this.data[this.position].toLong()
        val byte2 = this.data[this.position + 1].toLong()
        val byte3 = this.data[this.position + 2].toLong()
        val byte4 = this.data[this.position + 3].toLong()
        val byte5 = this.data[this.position + 4].toLong()
        val byte6 = this.data[this.position + 5].toLong()
        val byte7 = this.data[this.position + 6].toLong()
        val byte8 = this.data[this.position + 7].toLong()
        this.position += 8

        if (this.bigEndian)
        {
            var result = byte1 shl 56
            result = result or ((byte2 and 0xFF) shl 48)
            result = result or ((byte3 and 0xFF) shl 40)
            result = result or ((byte4 and 0xFF) shl 32)
            result = result or ((byte5 and 0xFF) shl 24)
            result = result or ((byte6 and 0xFF) shl 16)
            result = result or ((byte7 and 0xFF) shl 8)
            return result or (byte8 and 0xFF)
        }

        var result = byte8 shl 56
        result = result or ((byte7 and 0xFF) shl 48)
        result = result or ((byte6 and 0xFF) shl 40)
        result = result or ((byte5 and 0xFF) shl 32)
        result = result or ((byte4 and 0xFF) shl 24)
        result = result or ((byte3 and 0xFF) shl 16)
        result = result or ((byte2 and 0xFF) shl 8)
        return result or (byte1 and 0xFF)
    }

    /*

     * If the argument is {@code 0x7f800000}, the result is positive
     * infinity.
     *
     * <p>If the argument is {@code 0xff800000}, the result is negative
     * infinity.


     * <p>If the argument is any value in the range
     * {@code 0x7f800001} through {@code 0x7fffffff} or in
     * the range {@code 0xff800001} through
     * {@code 0xffffffff}, the result is a NaN.

      <blockquote><pre>{@code
     * int s = ((bits >> 31) == 0) ? 1 : -1;
     * int e = ((bits >> 23) & 0xff);
     * int m = (e == 0) ?
     *                 (bits & 0x7fffff) << 1 :
     *                 (bits & 0x7fffff) | 0x800000;
     * }</pre></blockquote>

     * Then the floating-point result equals the value of the mathematical
     * expression <i>s</i>&middot;<i>m</i>&middot;2<sup><i>e</i>-150</sup>.
                 e - 150
        s * m * 2
     */
    @Throws
    fun readFloat(): Float
    {
        @Try this.checkEnoughData(4)
        var byte1 = 0
        var byte2 = 0
        var byte3 = 0
        var byte4 = 0

        if (this.bigEndian)
        {
            byte1 = this.data[this.position].toInt() and 0xFF
            byte2 = this.data[this.position + 1].toInt() and 0xFF
            byte3 = this.data[this.position + 2].toInt() and 0xFF
            byte4 = this.data[this.position + 3].toInt() and 0xFF
        }
        else
        {
            byte1 = this.data[this.position + 3].toInt() and 0xFF
            byte2 = this.data[this.position + 2].toInt() and 0xFF
            byte3 = this.data[this.position + 1].toInt() and 0xFF
            byte4 = this.data[this.position].toInt() and 0xFF
        }

        this.position += 4

        ((byte1 != 0x7F && byte1 != 0xFF) || byte2 < 0x80).guard { throw IllegalStateException("The data not represents a valid Float") }

        var sign = 0f

        if (byte1 >= 0x80)
        {
            sign = -1f
        }
        else
        {
            sign = 1f
        }

        val exponent = ((byte1 and 0x7F) shl 1) or (byte2 shr 7)
        var mantis = (byte2 and 0x7F) shl 16
        mantis = mantis or (byte3 shl 8)
        mantis = mantis or byte4

        if (exponent == 0)
        {
            mantis = mantis shl 1
        }
        else
        {
            mantis = mantis or 0x800000
        }

        val power = pow(2.0, (exponent - 150).toDouble()).toFloat()
        return sign * mantis.toFloat() * power
    }

    @Throws
    private fun checkEnoughData(number: Int)
    {
        (this.position + number <= this.data.count).guard { throw IllegalStateException("Not enough data to read $number bytes") }
    }
}