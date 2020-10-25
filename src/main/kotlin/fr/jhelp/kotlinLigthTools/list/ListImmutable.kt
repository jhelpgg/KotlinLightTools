package fr.jhelp.kotlinLigthTools.list

import fr.jhelp.kotlinLight.CommonList

class ListImmutable<T> : ListInterface<T>
{
    private val list: CommonList<T>

    constructor(list: CommonList<T>)
    {
        this.list = list
    }

    override fun size(): Int
    {
        return this.list.count
    }

    override fun get(index: Int): T
    {
        return this.list[index]
    }

    override fun list(): CommonList<T>
    {
        return this.list
    }

    fun mutable(): ListMutable<T>
    {
        val list = ListMutable<T>()
        list.addAll(this.list)
        return list
    }
}