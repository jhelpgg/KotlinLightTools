package fr.jhelp.kotlinLigthTools.promise

import fr.jhelp.kotlinLight.Locker
import fr.jhelp.kotlinLight.TestCaseClass
import fr.jhelp.kotlinLigthTools.atomic.AtomicInt
import fr.jhelp.kotlinLigthTools.atomic.AtomicReference
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

@TestCaseClass

class PromiseFutureTests
{
    @Test
    fun succeedTest()
    {
        val promise = Promise<String>()
        val future = promise.getFuture()
        Assertions.assertEquals(FutureStatus.COMPUTING, future.status())
        Assertions.assertNull(future.getResult())
        Assertions.assertNull(future.getCancelReason())
        Assertions.assertEquals("", future.getError())

        promise.result("Top")
        Assertions.assertEquals(FutureStatus.SUCCEED, future.status())
        Assertions.assertEquals("Top", future.getResult())
        Assertions.assertNull(future.getCancelReason())
        Assertions.assertEquals("", future.getError())

        promise.result("After")
        Assertions.assertEquals(FutureStatus.SUCCEED, future.status())
        Assertions.assertEquals("Top", future.getResult())
        Assertions.assertNull(future.getCancelReason())
        Assertions.assertEquals("", future.getError())
    }

    @Test
    fun failedTest()
    {
        val promise = Promise<String>()
        val future = promise.getFuture()
        Assertions.assertEquals(FutureStatus.COMPUTING, future.status())
        Assertions.assertNull(future.getResult())
        Assertions.assertNull(future.getCancelReason())
        Assertions.assertEquals("", future.getError())

        promise.error("Top")
        Assertions.assertEquals(FutureStatus.FAILED, future.status())
        Assertions.assertNull(future.getResult())
        Assertions.assertNull(future.getCancelReason())
        Assertions.assertEquals("Top", future.getError())

        promise.result("After")
        Assertions.assertEquals(FutureStatus.FAILED, future.status())
        Assertions.assertNull(future.getResult())
        Assertions.assertNull(future.getCancelReason())
        Assertions.assertEquals("Top", future.getError())
    }

    @Test
    fun cancelTest()
    {
        val reason = AtomicReference<String>("")
        val promise = Promise<String>()
        promise.register { cancelReason -> reason.set(cancelReason) }
        val future = promise.getFuture()
        val locker = Locker()
        Assertions.assertEquals(FutureStatus.COMPUTING, future.status())
        Assertions.assertNull(future.getResult())
        Assertions.assertNull(future.getCancelReason())
        Assertions.assertEquals("", future.getError())

        future.cancel("Reason")
        future.then { locker.unlock() }
        locker.lock()
        Assertions.assertEquals(FutureStatus.CANCELED, future.status())
        Assertions.assertNull(future.getResult())
        Assertions.assertEquals("Reason", future.getCancelReason())
        Assertions.assertEquals("", future.getError())
        Assertions.assertEquals("Reason", reason.get())

        promise.result("After")
        Assertions.assertEquals(FutureStatus.CANCELED, future.status())
        Assertions.assertNull(future.getResult())
        Assertions.assertEquals("Reason", future.getCancelReason())
        Assertions.assertEquals("", future.getError())
        Assertions.assertEquals("Reason", reason.get())
    }

    @Test
    fun andSucceedTest()
    {
        val intermediate = AtomicInt(-1)
        val result = AtomicInt(-1)
        val promise = Promise<Int>()
        val locker = Locker()
        promise.getFuture()
            .andThen { number ->
                intermediate.set(number)
                100 / number
            }
            .andThen { number -> result.set(number) }
            .then { locker.unlock() }
        promise.result(4)
        locker.lock()
        Assertions.assertEquals(4, intermediate.get())
        Assertions.assertEquals(25, result.get())
    }

    @Test
    fun andFailedTest()
    {
        val intermediate = AtomicInt(-1)
        val result = AtomicInt(-1)
        val promise = Promise<Int>()
        val locker = Locker()
        promise.getFuture()
            .andThen { number ->
                intermediate.set(number)
                100 / number
            }
            .andThen { number -> result.set(number) }
            .then { locker.unlock() }
        promise.result(0)
        locker.lock()
        Assertions.assertEquals(0, intermediate.get())
        Assertions.assertEquals(-1, result.get())
    }

    @Test
    fun thenSucceedTest()
    {
        val intermediate = AtomicInt(-1)
        val result = AtomicInt(-1)
        val promise = Promise<Int>()
        val locker = Locker()
        promise.getFuture()
            .then { futureNumber ->
                val number = futureNumber.getResult()!!
                intermediate.set(number)
                100 / number
            }
            .then { futureNumber ->
                val number = futureNumber.getResult()!!
                result.set(number)
            }
            .then { locker.unlock() }
        promise.result(4)
        locker.lock()
        Assertions.assertEquals(4, intermediate.get())
        Assertions.assertEquals(25, result.get())
    }

    @Test
    fun thenFailedTest()
    {
        val intermediate = AtomicInt(-1)
        val error = AtomicReference<String>(null)
        val promise = Promise<Int>()
        val locker = Locker()
        promise.getFuture()
            .then { futureNumber ->
                val number = futureNumber.getResult()!!
                intermediate.set(number)
                100 / number
            }
            .then { futureNumber ->
                error.set(futureNumber.getError())
            }
            .then { locker.unlock() }
        promise.result(0)
        locker.lock()
        Assertions.assertEquals(0, intermediate.get())
        val errorMessage = error.get()
        Assertions.assertNotNull(errorMessage)
    }

    @Test
    fun unwrapTest()
    {
        val promise = Promise<Future<String>>()
        promise.result(futureValue("Hello"))
        val future = unwrap(promise.getFuture())
        val locker = Locker()
        future.then { locker.unlock() }
        locker.lock()
        Assertions.assertEquals("Hello", future.getResult())
    }
}