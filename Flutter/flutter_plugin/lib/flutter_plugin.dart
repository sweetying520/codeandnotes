
import 'dart:ffi';
import 'Test.dart';

import 'flutter_plugin_platform_interface.dart';

class FlutterPlugin {

  Future<String?> getPlatformVersion() {
    return FlutterPluginPlatform.instance.getPlatformVersion();
  }

  Future<String?> getUserName() {
    return FlutterPluginPlatform.instance.getUserName();
  }

  Future<int?> getAge() {
    return FlutterPluginPlatform.instance.getAge();
  }

}
