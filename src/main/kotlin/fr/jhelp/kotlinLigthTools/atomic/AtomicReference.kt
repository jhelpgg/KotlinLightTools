package fr.jhelp.kotlinLigthTools.atomic

import fr.jhelp.kotlinLight.Mutex

class AtomicReference<T>
{
    private val mutex: Mutex
    private var value: T?

    constructor(value: T?)
    {
        this.mutex = Mutex()
        this.value = value
    }

    fun get(): T?
    {
        var value: T? = null
        this.mutex.safeExecute { value = this.value }
        return value
    }

    fun set(value: T?)
    {
        this.mutex.safeExecute { this.value = value }
    }

    fun getAndSet(newValue: T?): T?
    {
        var value: T? = null

        this.mutex.safeExecute {
            value = this.value
            this.value = newValue
        }

        return value
    }
}