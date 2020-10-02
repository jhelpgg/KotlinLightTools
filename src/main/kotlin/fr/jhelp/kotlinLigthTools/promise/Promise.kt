package fr.jhelp.kotlinLigthTools.promise

import fr.jhelp.kotlinLight.CommonList
import fr.jhelp.kotlinLight.Mutex
import fr.jhelp.kotlinLigthTools.atomic.AtomicBool
import fr.jhelp.kotlinLigthTools.atomic.AtomicReference
import fr.jhelp.kotlinLigthTools.tasks.launchTask

class Promise<T>
{
    private var future: Future<T>?
    private val cancelReason: AtomicReference<String>
    private var listeners: CommonList<(String) -> Unit>
    private val mutex: Mutex
    private val complete: AtomicBool

    constructor()
    {
        this.future = null
        this.cancelReason = AtomicReference<String>(null)
        this.listeners = CommonList<(String) -> Unit>()
        this.mutex = Mutex()
        this.complete = AtomicBool(false)
    }

    fun getFuture(): Future<T>
    {
        this.mutex.safeExecute {
            if (this.future == null)
            {
                this.future = Future<T>(this)
            }
        }

        return this.future!!
    }

    fun canceled(): Boolean
    {
        return this.cancelReason.get() != null
    }

    fun getCancelReason(): String?
    {
        return this.cancelReason.get()
    }

    fun register(cancelListener: (String) -> Unit)
    {
        this.mutex.safeExecute {
            val cancelReason = this.cancelReason.get()

            if (cancelReason != null)
            {
                launchTask { cancelListener(cancelReason!!) }
            }
            else
            {
                this.listeners.append(cancelListener)
            }
        }
    }

    fun result(result: T)
    {
        if (this.complete.compareAndSet(false, true))
        {
            this.mutex.safeExecute { this.listeners.removeAll() }
            this.getFuture().result(result)
        }
    }

    fun error(cause: String)
    {
        if (this.complete.compareAndSet(false, true))
        {
            this.mutex.safeExecute { this.listeners.removeAll() }
            this.getFuture().error(cause)
        }
    }

    internal fun cancel(reason: String)
    {
        if (this.complete.compareAndSet(false, true))
        {
            if (this.cancelReason.get() == null)
            {
                this.cancelReason.set(reason)
                this.complete.set(true)

                for (listener in this.listeners)
                {
                    launchTask { listener(reason) }
                }

                this.listeners.removeAll()
            }
        }
    }
}