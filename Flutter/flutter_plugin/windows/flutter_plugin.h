#ifndef FLUTTER_PLUGIN_FLUTTER_PLUGIN_H_
#define FLUTTER_PLUGIN_FLUTTER_PLUGIN_H_

#include <flutter/method_channel.h>
#include <flutter/plugin_registrar_windows.h>

#include <memory>

namespace flutter_plugin {

class FlutterPlugin : public flutter::Plugin {
 public:
  static void RegisterWithRegistrar(flutter::PluginRegistrarWindows *registrar);

  FlutterPlugin();

  virtual ~FlutterPlugin();

  // Disallow copy and assign.
  FlutterPlugin(const FlutterPlugin&) = delete;
  FlutterPlugin& operator=(const FlutterPlugin&) = delete;

 private:
  // Called when a method is called on this plugin's channel from Dart.
  void HandleMethodCall(
      const flutter::MethodCall<flutter::EncodableValue> &method_call,
      std::unique_ptr<flutter::MethodResult<flutter::EncodableValue>> result);
};

}  // namespace flutter_plugin

#endif  // FLUTTER_PLUGIN_FLUTTER_PLUGIN_H_
