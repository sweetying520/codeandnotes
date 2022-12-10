package com.dream.appblackandwhite.hook

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/12/10
 */
class ObservableArrayList<T>(private val onListAddListener: OnListAddListener<T>?) :
    ArrayList<T>() {

    override fun add(element: T): Boolean {
        val isAdd = super.add(element)
        onListAddListener?.add(this, size - 1)
        return isAdd
    }


    interface OnListAddListener<T> {
        fun add(list: ArrayList<T>, index: Int)
    }
}