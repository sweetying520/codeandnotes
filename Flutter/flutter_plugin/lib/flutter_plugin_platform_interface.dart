import 'dart:ffi';

import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'flutter_plugin_method_channel.dart';

abstract class FlutterPluginPlatform extends PlatformInterface {
  /// Constructs a FlutterPluginPlatform.
  FlutterPluginPlatform() : super(token: _token);

  static final Object _token = Object();

  static FlutterPluginPlatform _instance = MethodChannelFlutterPlugin();

  /// The default instance of [FlutterPluginPlatform] to use.
  ///
  /// Defaults to [MethodChannelFlutterPlugin].
  static FlutterPluginPlatform get instance => _instance;
  
  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [FlutterPluginPlatform] when
  /// they register themselves.
  static set instance(FlutterPluginPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }

  Future<String?> getUserName() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }

  Future<int?> getAge() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
