#include "include/flutter_plugin_with_pigeon/flutter_plugin_with_pigeon_plugin_c_api.h"

#include <flutter/plugin_registrar_windows.h>

#include "flutter_plugin_with_pigeon_plugin.h"

void FlutterPluginWithPigeonPluginCApiRegisterWithRegistrar(
    FlutterDesktopPluginRegistrarRef registrar) {
  flutter_plugin_with_pigeon::FlutterPluginWithPigeonPlugin::RegisterWithRegistrar(
      flutter::PluginRegistrarManager::GetInstance()
          ->GetRegistrar<flutter::PluginRegistrarWindows>(registrar));
}
