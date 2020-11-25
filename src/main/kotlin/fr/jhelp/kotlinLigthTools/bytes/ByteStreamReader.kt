package fr.jhelp.kotlinLigthTools.bytes

import fr.jhelp.kotlinLight.CommonList
import fr.jhelp.kotlinLight.ImportSwift
import fr.jhelp.kotlinLight.Try
import fr.jhelp.kotlinLight.guard
import fr.jhelp.kotlinLigthTools.exceptions.IllegalArgumentException
import fr.jhelp.kotlinLigthTools.exceptions.IllegalStateException
import fr.jhelp.kotlinLigthTools.math.floatFromIntBytes

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

        if (value > 0x7F)
        {
            return value - 0x100
        }
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
        @Try val value = this.readUInt16()

        if (value > 0x7FFF)
        {
            return value - 0x10000
        }

        return value
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
        @Try return this.readUInt32().toInt()
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

    @Throws
    fun readFloat(): Float
    {
        @Try val intBytes = this.readInt32()
        @Try return floatFromIntBytes(intBytes)
    }

    @Throws
    private fun checkEnoughData(number: Int)
    {
        (this.position + number <= this.data.count).guard { throw IllegalStateException("Not enough data to read $number bytes") }
    }
}