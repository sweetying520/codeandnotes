
import 'flutter_plugin_with_pigeon_platform_interface.dart';

class FlutterPluginWithPigeon {
  Future<String?> getPlatformVersion() {
    return FlutterPluginWithPigeonPlatform.instance.getPlatformVersion();
  }
}
