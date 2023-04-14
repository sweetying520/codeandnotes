import 'dart:ffi';

import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'flutter_plugin_platform_interface.dart';

/// An implementation of [FlutterPluginPlatform] that uses method channels.
class MethodChannelFlutterPlugin extends FlutterPluginPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('flutter_plugin');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }

  @override
  Future<String?> getUserName() async{
    final userName = await methodChannel.invokeMethod<String>("getUserName");
    return userName;
  }

  @override
  Future<int?> getAge() async{
    final age = await methodChannel.invokeMethod<int>("getAge");
    return age;
  }

}
