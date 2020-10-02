package fr.jhelp.kotlinLigthTools.promise

import fr.jhelp.kotlinLight.CommonList
import fr.jhelp.kotlinLight.Mutex
import fr.jhelp.kotlinLight.Try
import fr.jhelp.kotlinLigthTools.atomic.AtomicReference
import fr.jhelp.kotlinLigthTools.tasks.launchTask

class Future<T>
{
    private val promise: Promise<T>
    private val futureStatus: AtomicReference<FutureStatus>
    private val mutex: Mutex
    private var result: T?
    private var error: String
    private var continuations: CommonList<() -> Unit>

    internal constructor(promise: Promise<T>)
    {
        this.promise = promise
        this.futureStatus = AtomicReference<FutureStatus>(FutureStatus.COMPUTING)
        this.mutex = Mutex()
        this.result = null
        this.error = ""
        this.continuations = CommonList<() -> Unit>()
    }

    fun status(): FutureStatus
    {
        return this.futureStatus.get()!!
    }

    fun getResult(): T?
    {
        return this.result
    }

    fun getError(): String
    {
        return this.error
    }

    fun getCancelReason(): String?
    {
        return this.promise.getCancelReason()
    }

    fun cancel(reason: String)
    {
        if (this.futureStatus.get() == FutureStatus.COMPUTING)
        {
            this.futureStatus.set(FutureStatus.CANCELED)
            this.promise.cancel(reason)
            this.fireListeners()
        }
    }

    fun <T1> andThen(continuation: (T) -> T1): Future<T1>
    {
        val promise = Promise<T1>()
        val action: () -> Unit = {
            when (this.futureStatus.get()!!)
            {
                FutureStatus.SUCCEED ->
                {
                    try
                    {
                        @Try promise.result(continuation(this.result!!))
                    }
                    catch (error: Exception)
                    {
                        promise.error("Failed to execute continuation : $error")
                    }
                }
                FutureStatus.FAILED -> promise.error(this.error)
                FutureStatus.CANCELED -> promise.getFuture().cancel(this.getCancelReason()!!)
                else                  ->
                {
                }

            }
        }

        this.mutex.safeExecute {
            when (this.futureStatus.get()!!)
            {
                FutureStatus.COMPUTING -> this.continuations.append(action)
                else                   -> launchTask(action)
            }
        }

        return promise.getFuture()
    }

    fun <T1> then(continuation: (Future<T>) -> T1): Future<T1>
    {
        val promise = Promise<T1>()
        val action: () -> Unit = {
            try
            {
                @Try promise.result(continuation(this))
            }
            catch (error: Exception)
            {
                promise.error("Failed to execute continuation : $error")
            }
        }

        this.mutex.safeExecute {
            when (this.futureStatus.get()!!)
            {
                FutureStatus.COMPUTING -> this.continuations.append(action)
                else                   -> launchTask(action)
            }
        }

        return promise.getFuture()
    }

    fun <T1> andThenUnwrap(continuation: (T) -> Future<T1>): Future<T1>
    {
        return unwrap(this.andThen(continuation))
    }

    fun <T1> thenUnwrap(continuation: (Future<T>) -> Future<T1>): Future<T1>
    {
        return unwrap(this.then(continuation))
    }

    internal fun result(result: T)
    {
        if (this.futureStatus.get() == FutureStatus.COMPUTING)
        {
            this.futureStatus.set(FutureStatus.SUCCEED)
            this.result = result
            this.fireListeners()
        }
    }

    internal fun error(cause: String)
    {
        if (this.futureStatus.get() == FutureStatus.COMPUTING)
        {
            this.futureStatus.set(FutureStatus.FAILED)
            this.error = cause
            this.fireListeners()
        }
    }

    private fun fireListeners()
    {
        this.mutex.safeExecute {
            for (continuation in this.continuations)
            {
                launchTask(continuation)
            }

            this.continuations.removeAll()
        }
    }
}