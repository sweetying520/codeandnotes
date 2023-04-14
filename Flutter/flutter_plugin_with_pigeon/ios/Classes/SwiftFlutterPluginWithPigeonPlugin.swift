import Flutter
import UIKit

public class SwiftFlutterPluginWithPigeonPlugin: NSObject, FlutterPlugin {
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "flutter_plugin_with_pigeon", binaryMessenger: registrar.messenger())
    let instance = SwiftFlutterPluginWithPigeonPlugin()
    registrar.addMethodCallDelegate(instance, channel: channel)
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
    result("iOS " + UIDevice.current.systemVersion)
  }
}
