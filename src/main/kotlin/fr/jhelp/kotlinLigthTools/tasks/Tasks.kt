package fr.jhelp.kotlinLigthTools.tasks

import fr.jhelp.kotlinLight.DispatchQueue
import fr.jhelp.kotlinLight.DispatchTime
import fr.jhelp.kotlinLight.DispatchTimeInterval
import fr.jhelp.kotlinLight.ImportSwift

@ImportSwift("Foundation")

fun launchTask(task: () -> Unit)
{
    DispatchQueue.global().async(execute = task)
}

fun delayTask(delayMilliseconds: Int, task: () -> Unit)
{
    DispatchQueue.global().asyncAfter(deadline = DispatchTime.now()
                                                 + DispatchTimeInterval.milliseconds(delayMilliseconds),
                                      execute = task)
}
