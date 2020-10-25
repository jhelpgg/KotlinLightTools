package fr.jhelp.kotlinLigthTools.list

import fr.jhelp.kotlinLight.CommonList

interface ListInterface<T>
{
    fun size() : Int

    fun get(index:Int):T

    fun list() : CommonList<T>
}