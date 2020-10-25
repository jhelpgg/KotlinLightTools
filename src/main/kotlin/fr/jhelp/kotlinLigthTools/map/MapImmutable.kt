package fr.jhelp.kotlinLigthTools.map

import fr.jhelp.kotlinLight.CommonMap

class MapImmutable<K, V> : MapInterface<K, V>
{
    private val map: CommonMap<K, V>

    constructor(map: CommonMap<K, V>)
    {
        this.map = map
    }

    override fun size(): Int
    {
        return this.map.count
    }

    override fun get(key: K): V?
    {
        return this.map[key]
    }

    override fun map(): CommonMap<K, V>
    {
        return this.map
    }

    fun mutable(): MapMutable<K, V>
    {
        val map = MapMutable<K, V>()
        map.putAll(this.map)
        return map
    }
}