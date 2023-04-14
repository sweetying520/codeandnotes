// In order to *not* need this ignore, consider extracting the "web" version
// of your plugin as a separate package, instead of inlining it in the same
// package as the core of your plugin.
// ignore: avoid_web_libraries_in_flutter
import 'dart:html' as html show window;

import 'package:flutter_web_plugins/flutter_web_plugins.dart';

import 'flutter_plugin_with_pigeon_platform_interface.dart';

/// A web implementation of the FlutterPluginWithPigeonPlatform of the FlutterPluginWithPigeon plugin.
class FlutterPluginWithPigeonWeb extends FlutterPluginWithPigeonPlatform {
  /// Constructs a FlutterPluginWithPigeonWeb
  FlutterPluginWithPigeonWeb();

  static void registerWith(Registrar registrar) {
    FlutterPluginWithPigeonPlatform.instance = FlutterPluginWithPigeonWeb();
  }

  /// Returns a [String] containing the version of the platform.
  @override
  Future<String?> getPlatformVersion() async {
    final version = html.window.navigator.userAgent;
    return version;
  }
}
