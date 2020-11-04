package fr.jhelp.kotlinLigthTools.bytes

import fr.jhelp.kotlinLight.CommonList
import fr.jhelp.kotlinLight.ImportSwift
import fr.jhelp.kotlinLigthTools.math.floatToIntBytes

@ImportSwift("Foundation")

class ByteStreamWriter
{
    private var data: CommonList<Byte>
    private val bigEndian: Boolean

    constructor(bigEndian: Boolean)
    {
        this.data = CommonList<Byte>()
        this.bigEndian = bigEndian
    }

    fun getData(): CommonList<Byte>
    {
        return this.data
    }

    fun writeInt8(byte: Int)
    {
        this.data.append(byte.toByte())
    }

    fun writeUInt8(byte: Int)
    {
        this.data.append((byte and 0xFF).toByte())
    }

    fun writeInt16(short: Int)
    {
        if (this.bigEndian)
        {
            this.data.append(((short shr 8) and 0xFF).toByte())
            this.data.append((short and 0xFF).toByte())
        }
        else
        {
            this.data.append((short and 0xFF).toByte())
            this.data.append(((short shr 8) and 0xFF).toByte())
        }
    }

    fun writeUInt16(short: Int)
    {
        if (this.bigEndian)
        {
            this.data.append(((short shr 8) and 0xFF).toByte())
            this.data.append((short and 0xFF).toByte())
        }
        else
        {
            this.data.append((short and 0xFF).toByte())
            this.data.append(((short shr 8) and 0xFF).toByte())
        }
    }

    fun writeInt32(integer: Int)
    {
        if (this.bigEndian)
        {
            this.data.append(((integer shr 24) and 0xFF).toByte())
            this.data.append(((integer shr 16) and 0xFF).toByte())
            this.data.append(((integer shr 8) and 0xFF).toByte())
            this.data.append((integer and 0xFF).toByte())
        }
        else
        {
            this.data.append((integer and 0xFF).toByte())
            this.data.append(((integer shr 8) and 0xFF).toByte())
            this.data.append(((integer shr 16) and 0xFF).toByte())
            this.data.append(((integer shr 24) and 0xFF).toByte())
        }
    }

    fun writeUInt32(integer: Long)
    {
        if (this.bigEndian)
        {
            this.data.append(((integer shr 24) and 0xFF).toByte())
            this.data.append(((integer shr 16) and 0xFF).toByte())
            this.data.append(((integer shr 8) and 0xFF).toByte())
            this.data.append((integer and 0xFF).toByte())
        }
        else
        {
            this.data.append((integer and 0xFF).toByte())
            this.data.append(((integer shr 8) and 0xFF).toByte())
            this.data.append(((integer shr 16) and 0xFF).toByte())
            this.data.append(((integer shr 24) and 0xFF).toByte())
        }
    }

    fun writeInt64(integer: Long)
    {
        if (this.bigEndian)
        {
            this.data.append(((integer shr 56) and 0xFF).toByte())
            this.data.append(((integer shr 48) and 0xFF).toByte())
            this.data.append(((integer shr 40) and 0xFF).toByte())
            this.data.append(((integer shr 32) and 0xFF).toByte())
            this.data.append(((integer shr 24) and 0xFF).toByte())
            this.data.append(((integer shr 16) and 0xFF).toByte())
            this.data.append(((integer shr 8) and 0xFF).toByte())
            this.data.append((integer and 0xFF).toByte())
        }
        else
        {
            this.data.append((integer and 0xFF).toByte())
            this.data.append(((integer shr 8) and 0xFF).toByte())
            this.data.append(((integer shr 16) and 0xFF).toByte())
            this.data.append(((integer shr 24) and 0xFF).toByte())
            this.data.append(((integer shr 32) and 0xFF).toByte())
            this.data.append(((integer shr 40) and 0xFF).toByte())
            this.data.append(((integer shr 48) and 0xFF).toByte())
            this.data.append(((integer shr 56) and 0xFF).toByte())
        }
    }

    fun writeFloat(float: Float)
    {
        this.writeInt32(floatToIntBytes(float))
    }
}
