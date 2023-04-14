import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'flutter_plugin_with_pigeon_method_channel.dart';

abstract class FlutterPluginWithPigeonPlatform extends PlatformInterface {
  /// Constructs a FlutterPluginWithPigeonPlatform.
  FlutterPluginWithPigeonPlatform() : super(token: _token);

  static final Object _token = Object();

  static FlutterPluginWithPigeonPlatform _instance = MethodChannelFlutterPluginWithPigeon();

  /// The default instance of [FlutterPluginWithPigeonPlatform] to use.
  ///
  /// Defaults to [MethodChannelFlutterPluginWithPigeon].
  static FlutterPluginWithPigeonPlatform get instance => _instance;
  
  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [FlutterPluginWithPigeonPlatform] when
  /// they register themselves.
  static set instance(FlutterPluginWithPigeonPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
