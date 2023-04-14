package com.example.flutter_plugin_with_pigeon

import androidx.annotation.NonNull

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

/** FlutterPluginWithPigeonPlugin */
class FlutterPluginWithPigeonPlugin: FlutterPlugin, MethodCallHandler,AllTypesPigeon.HostEverything{

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
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
    return impl.giveMeEverything()
  }

  override fun echo(everything: AllTypesPigeon.Everything?): AllTypesPigeon.Everything {
    val impl = AllTypesPigeonImpl()
    return impl.echo(everything)
  }
}
