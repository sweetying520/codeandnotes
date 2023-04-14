#include "include/flutter_plugin/flutter_plugin_c_api.h"

#include <flutter/plugin_registrar_windows.h>

#include "flutter_plugin.h"

void FlutterPluginCApiRegisterWithRegistrar(
    FlutterDesktopPluginRegistrarRef registrar) {
  flutter_plugin::FlutterPlugin::RegisterWithRegistrar(
      flutter::PluginRegistrarManager::GetInstance()
          ->GetRegistrar<flutter::PluginRegistrarWindows>(registrar));
}
