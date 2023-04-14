import 'package:flutter_test/flutter_test.dart';
import 'package:flutter_plugin_with_pigeon/flutter_plugin_with_pigeon.dart';
import 'package:flutter_plugin_with_pigeon/flutter_plugin_with_pigeon_platform_interface.dart';
import 'package:flutter_plugin_with_pigeon/flutter_plugin_with_pigeon_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockFlutterPluginWithPigeonPlatform 
    with MockPlatformInterfaceMixin
    implements FlutterPluginWithPigeonPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final FlutterPluginWithPigeonPlatform initialPlatform = FlutterPluginWithPigeonPlatform.instance;

  test('$MethodChannelFlutterPluginWithPigeon is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelFlutterPluginWithPigeon>());
  });

}
