package fr.jhelp.kotlinLigthTools.list

import fr.jhelp.kotlinLight.CommonList

class ListMutable<T> : ListInterface<T>
{
    private var list: CommonList<T>

    constructor()
    {
        this.list = CommonList<T>()
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

    fun add(element: T)
    {
        this.list.append(element)
    }

    fun set(index: Int, element: T)
    {
        this.list[index] = element
    }

    fun remove(index: Int)
    {
        this.list.remove(at = index)
    }

    fun insert(index: Int, element: T)
    {
        this.list.insert(element, at = index)
    }

    fun clear()
    {
        this.list.removeAll()
    }

    fun addAll(list: CommonList<T>)
    {
        for (element in list)
        {
            this.list.append(element)
        }
    }

    fun addAll(list: ListInterface<T>)
    {
        for (element in list.list())
        {
            this.list.append(element)
        }
    }

    fun immutable(): ListImmutable<T>
    {
        return ListImmutable(this.list)
    }
}