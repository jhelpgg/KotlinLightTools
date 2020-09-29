package fr.jhelp.kotlinLigthTools.atomic

import fr.jhelp.kotlinLight.Mutex

class AtomicBool
{
    private val mutex: Mutex
    private var value: Boolean

    constructor(value: Boolean)
    {
        this.mutex = Mutex()
        this.value = value
    }

    fun get(): Boolean
    {
        var value = false
        this.mutex.safeExecute { value = this.value }
        return value
    }

    fun set(value: Boolean)
    {
        this.mutex.safeExecute { this.value = value }
    }

    fun getAndSet(newValue: Boolean): Boolean
    {
        var value = false

        this.mutex.safeExecute {
            value = this.value
            this.value = newValue
        }

        return value
    }

    fun compareAndSet(excepted: Boolean, newValue: Boolean): Boolean
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
}