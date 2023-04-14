#import "FlutterPluginWithPigeonPlugin.h"
#if __has_include(<flutter_plugin_with_pigeon/flutter_plugin_with_pigeon-Swift.h>)
#import <flutter_plugin_with_pigeon/flutter_plugin_with_pigeon-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "flutter_plugin_with_pigeon-Swift.h"
#endif

@implementation FlutterPluginWithPigeonPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftFlutterPluginWithPigeonPlugin registerWithRegistrar:registrar];
}
@end
