package fr.jhelp.kotlinLigthTools.map

import fr.jhelp.kotlinLight.CommonMap

class MapMutable<K, V> : MapInterface<K, V>
{
    private var map: CommonMap<K, V>

    constructor()
    {
        this.map = CommonMap<K, V>()
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

    fun remove(key: K)
    {
        this.map[key] = null
    }

    fun clear()
    {
        for (key in this.map.keys)
        {
            this.map[key] = null
        }
    }

    fun put(key: K, value: V)
    {
        this.map[key] = value
    }

    fun putAll(map: CommonMap<K, V>)
    {
        for ((key, value) in map)
        {
            this.map[key] = value
        }
    }

    fun putAll(map: MapInterface<K, V>)
    {
        for ((key, value) in map.map())
        {
            this.map[key] = value
        }
    }

    fun immutable(): MapImmutable<K, V>
    {
        return MapImmutable(this.map)
    }
}