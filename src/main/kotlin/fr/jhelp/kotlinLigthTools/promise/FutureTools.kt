package fr.jhelp.kotlinLigthTools.promise

fun <T> unwrap(future: Future<Future<T>>): Future<T>
{
    val promise = Promise<T>()

    future.then { futureCombined ->
        when (futureCombined.status())
        {
            FutureStatus.SUCCEED -> promise.result(futureCombined.getResult()!!.getResult()!!)
            FutureStatus.FAILED -> promise.error(future.getError())
            FutureStatus.CANCELED -> promise.getFuture().cancel(future.getCancelReason()!!)
            else                  ->
            {
            }
        }
    }

    return promise.getFuture()
}

fun <T> futureValue(value:T) : Future<T>
{
    val promise = Promise<T>()
    promise.result(value)
    return promise.getFuture()
}

fun <T> futureFailed(error:String) : Future<T>
{
    val promise = Promise<T>()
    promise.error(error)
    return promise.getFuture()
}