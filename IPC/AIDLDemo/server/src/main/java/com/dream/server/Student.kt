package com.dream.server

import android.os.Parcel
import android.os.Parcelable

/**
 * function: 实现 Parcelable 接口的 Student 类
 *
 * @author zy
 * @since 2020/7/17
 */
class Student() : Parcelable {

    var name: String? = null
    var age: Int = 0

    constructor(name: String?,age: Int): this(){
        this.name = name
        this.age = age
    }

    constructor(parcel: Parcel) : this() {
        this.name = parcel.readString()
        this.age = parcel.readInt()
    }

    fun readFromParcel(parcel: Parcel){
        this.name = parcel.readString()
        this.age = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(age)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Student> {
        override fun createFromParcel(parcel: Parcel): Student {
            return Student(parcel)
        }

        override fun newArray(size: Int): Array<Student?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        return "Student(name=$name, age=$age)"
    }
}