package com.example.flutter_plugin_with_pigeon

import androidx.annotation.NonNull
import com.example.flutter_plugin_with_pigeon.AllTypesPigeon.Everything

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

/** FlutterPluginWithPigeonPlugin */
class FlutterPluginWithPigeonPlugin: FlutterPlugin, MethodCallHandler,AllTypesPigeon.HostEverything{

  private lateinit var mFlutterPluginBinding: FlutterPlugin.FlutterPluginBinding
  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    mFlutterPluginBinding = flutterPluginBinding
    AllTypesPigeon.HostEverything.setup(flutterPluginBinding.binaryMessenger,this)

  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
    result.notImplemented()
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    AllTypesPigeon.HostEverything.setup(binding.binaryMessenger,null)
  }

  override fun giveMeEverything(): AllTypesPigeon.Everything {
    val impl = AllTypesPigeonImpl()
    val everything = Everything().also {
      it.aDouble = 788.0
      it.aBool = true
      it.aInt = 666
      it.aString = "erdai"
    }
    AllTypesPigeon.FlutterEverything(mFlutterPluginBinding.binaryMessenger).giveMeEverythingFlutter(everything){}
    return impl.giveMeEverything()
  }

  override fun echo(everything: AllTypesPigeon.Everything?): AllTypesPigeon.Everything {
    val impl = AllTypesPigeonImpl()
    return impl.echo(everything)
  }
}
