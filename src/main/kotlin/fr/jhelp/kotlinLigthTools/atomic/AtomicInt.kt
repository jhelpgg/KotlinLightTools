package fr.jhelp.kotlinLigthTools.atomic

import fr.jhelp.kotlinLight.Mutex

class AtomicInt
{
    private val mutex: Mutex
    private var value: Int

    constructor(value: Int)
    {
        this.mutex = Mutex()
        this.value = value
    }

    fun get(): Int
    {
        var value = 0
        this.mutex.safeExecute { value = this.value }
        return value
    }

    fun set(value: Int)
    {
        this.mutex.safeExecute { this.value = value }
    }

    fun getAndSet(newValue: Int): Int
    {
        var value = 0

        this.mutex.safeExecute {
            value = this.value
            this.value = newValue
        }

        return value
    }

    fun compareAndSet(excepted: Int, newValue: Int): Boolean
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

    fun incrementAndGet(): Int
    {
        var value = 0

        this.mutex.safeExecute {
            this.value++
            value = this.value
        }

        return value
    }

    fun getAndIncrement(): Int
    {
        var value = 0

        this.mutex.safeExecute {
            value = this.value
            this.value++
        }

        return value
    }

    fun decrementAndGet(): Int
    {
        var value = 0

        this.mutex.safeExecute {
            this.value--
            value = this.value
        }

        return value
    }

    fun getAndDecrement(): Int
    {
        var value = 0

        this.mutex.safeExecute {
            value = this.value
            this.value--
        }

        return value
    }
}