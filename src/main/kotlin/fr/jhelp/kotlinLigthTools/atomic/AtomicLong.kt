package fr.jhelp.kotlinLigthTools.atomic

import fr.jhelp.kotlinLight.Mutex

class AtomicLong
{
    private val mutex: Mutex
    private var value: Long

    constructor(value: Long)
    {
        this.mutex = Mutex()
        this.value = value
    }

    fun get(): Long
    {
        var value = 0L
        this.mutex.safeExecute { value = this.value }
        return value
    }

    fun set(value: Long)
    {
        this.mutex.safeExecute { this.value = value }
    }

    fun getAndSet(newValue: Long): Long
    {
        var value = 0L

        this.mutex.safeExecute {
            value = this.value
            this.value = newValue
        }

        return value
    }

    fun compareAndSet(excepted: Long, newValue: Long): Boolean
    {
        var done = false

        this.mutex.safeExecute {
            if (this.value == excepted)
            {
                this.value = newValue
                done = true
            }
        }

        return done
    }

    fun incrementAndGet(): Long
    {
        var value = 0L

        this.mutex.safeExecute {
            this.value++
            value = this.value
        }

        return value
    }

    fun getAndIncrement(): Long
    {
        var value = 0L

        this.mutex.safeExecute {
            value = this.value
            this.value++
        }

        return value
    }

    fun decrementAndGet(): Long
    {
        var value = 0L

        this.mutex.safeExecute {
            this.value--
            value = this.value
        }

        return value
    }

    fun getAndDecrement(): Long
    {
        var value = 0L

        this.mutex.safeExecute {
            value = this.value
            this.value--
        }

        return value
    }
}