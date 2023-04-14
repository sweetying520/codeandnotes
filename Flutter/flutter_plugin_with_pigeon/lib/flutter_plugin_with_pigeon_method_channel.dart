import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'flutter_plugin_with_pigeon_platform_interface.dart';

/// An implementation of [FlutterPluginWithPigeonPlatform] that uses method channels.
class MethodChannelFlutterPluginWithPigeon extends FlutterPluginWithPigeonPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('flutter_plugin_with_pigeon');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }
}
