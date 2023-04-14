import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:flutter_plugin_with_pigeon/flutter_plugin_with_pigeon_method_channel.dart';

void main() {
  MethodChannelFlutterPluginWithPigeon platform = MethodChannelFlutterPluginWithPigeon();
  const MethodChannel channel = MethodChannel('flutter_plugin_with_pigeon');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });
}
