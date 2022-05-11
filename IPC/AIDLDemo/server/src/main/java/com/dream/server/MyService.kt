package com.dream.server

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

/**
 * function: 远程 Service 服务
 *
 * @author zy
 * @since 2022/5/11
 */
class MyService: Service() {

    override fun onBind(intent: Intent?): IBinder {
        return MyBinder()
    }


    class MyBinder: IServer.Stub(){
        override fun getAge(): Int {
            return 27
        }

        override fun setName(name: String?) {
            Log.d("erdai", "setName: 获取客户端设置的 name：$name")
        }

        override fun getStudentList(): MutableList<Student> {
            val studentList = mutableListOf<Student>()
            studentList.add(Student("erdai666",25))
            studentList.add(Student("erdai777",27))
            return studentList
        }

        override fun addStudent(student: Student?) {
            Log.d("erdai", "addStudent: 获取客户端添加的学生：$student")
        }
    }
}