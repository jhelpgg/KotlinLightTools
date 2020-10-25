package fr.jhelp.kotlinLigthTools.map

import fr.jhelp.kotlinLight.CommonMap

interface MapInterface<K,V>
{
    fun size() : Int

    fun get(key:K) : V?

    fun map() : CommonMap<K,V>
}