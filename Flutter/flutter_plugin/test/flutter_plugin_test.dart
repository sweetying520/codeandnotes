import 'package:flutter_test/flutter_test.dart';
import 'package:flutter_plugin/flutter_plugin.dart';
import 'package:flutter_plugin/flutter_plugin_platform_interface.dart';
import 'package:flutter_plugin/flutter_plugin_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockFlutterPluginPlatform 
    with MockPlatformInterfaceMixin
    implements FlutterPluginPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final FlutterPluginPlatform initialPlatform = FlutterPluginPlatform.instance;

  test('$MethodChannelFlutterPlugin is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelFlutterPlugin>());
  });

  test('getPlatformVersion', () async {
    FlutterPlugin flutterPlugin = FlutterPlugin();
    MockFlutterPluginPlatform fakePlatform = MockFlutterPluginPlatform();
    FlutterPluginPlatform.instance = fakePlatform;
  
    expect(await flutterPlugin.getPlatformVersion(), '42');
  });
}
