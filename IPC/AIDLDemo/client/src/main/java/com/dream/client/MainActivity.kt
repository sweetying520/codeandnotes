package com.dream.client

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dream.server.IServer
import com.dream.server.Student

class MainActivity : AppCompatActivity() {

    /**
     * 远程服务在客户端的代理对象
     */
    private var iServer: IServer? = null

    /**
     * 绑定服务所需的 serviceConnection
     */
    private var serviceConnection: ServiceConnection? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * 获取远程服务 stub 的实现
     */
    fun bindConnection(view: View) {
        val intent = Intent()
        intent.`package` = "com.dream.server"
        intent.action = "com.dream.server.myserviceaction"
        serviceConnection = object : ServiceConnection{
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                Log.d("erdai", "onServiceConnected: 服务连接成功")
                iServer = IServer.Stub.asInterface(service)
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                Log.d("erdai", "onServiceDisconnected: 服务断开连接")
                iServer = null
                serviceConnection?.let {
                    unbindService(it)
                }
            }

        }
        serviceConnection?.let {
            bindService(intent,it, BIND_AUTO_CREATE)
        }
        Log.d("erdai", "bindConnection: 客户端开始绑定远程服务...")
    }

    /**
     * 获取服务端的 age
     */
    fun getServerAge(view: View) {
        Log.d("erdai", "getServerAge: 获取服务段 age：${iServer?.age}")
    }

    /**
     * 给服务端设置 name
     */
    fun postNameToServer(view: View) {
        Log.d("erdai", "postNameToServer: 给服务端设置 name 参数...")
        iServer?.setName("erdai666")
    }

    /**
     * 获取服务端返回的学生列表
     */
    fun getStudentList(view: View) {
        try {
            val studentList: MutableList<Student>? = iServer?.studentList
            Log.d("erdai", "getStudentList: 获取服务器学生列表...")
            studentList?.forEach {
                Log.d("erdai", "getStudentList: $it")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 给服务端添加学生
     */
    fun addStudent(view: View) {
        Log.d("erdai", "addStudent: 给服务端添加学生...")
        iServer?.addStudent(Student("erdai888",40))
    }
}