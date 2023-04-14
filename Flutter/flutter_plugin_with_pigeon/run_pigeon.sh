flutter pub run pigeon \
--input pigeons/all_types_pigeon.dart \
--dart_out lib/all_types_pigeon_generate.dart \
--objc_header_out ios/Classes/AllTypesPigeon.h \
--objc_source_out ios/Classes/AllTypesPigeon.m \
--objc_prefix FLT \
--java_out android/src/main/kotlin/com/example/flutter_plugin_with_pigeon/AllTypesPigeon.java \
--java_package "com.example.flutter_plugin_with_pigeon"