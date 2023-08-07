import 'dart:async';
import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter_plugin_with_pigeon/all_types_pigeon_generate.dart';
import 'package:permission_request/permission_utils.dart';
import 'package:permission_request/schema_generate.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget{
  const MyApp({Key? key}) : super(key: key);

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> implements FlutterEverything  {

  var allTypePigeon = HostEverything();
  var schema = AkuPermission();

  @override
  void initState() {
    super.initState();
    initPlatformState();
    FlutterEverything.setup(this);
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPlatformState() async {
    // Platform messages may fail, so we use a try/catch PlatformException.
    // We also handle the message potentially returning null.
    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Column(
          children: [
            ElevatedButton(onPressed: () async{
              var everything = await allTypePigeon.giveMeEverything();
              print('${everything.aString}  ${everything.aBool}  ${everything.aInt}');
                }, child: const Text("Pigeon Test 1")
            ),
            ElevatedButton(onPressed: () async{
              var eve = Everything();
              var everything = await allTypePigeon.echo(eve);
              print('${everything.aString}  ${everything.aBool}  ${everything.aInt}');
            }, child: const Text("Pigeon Test 2")
            ),
            ElevatedButton(onPressed: () async{
              var permissions = [Permissions.CAMERA,Permissions.CALL_PHONE];
              var permissionsGroup = [PermissionGroup.Camera];
              var permsIndexList = PermissionUtils.getPermissionsIndex(Platform.isAndroid ? permissions : permissionsGroup);
              var resultMap = await schema.request(permsIndexList);
              print('resultMap====> $resultMap');
            }, child: const Text("RequestPermission")
            )
          ],
        ),
      ),
    );
  }


  @override
  void giveMeEverythingFlutter(Everything everything) {
    print('giveMeEverythingFlutter： ${everything.aBool}');
    print('giveMeEverythingFlutter： ${everything.aDouble}');
    print('giveMeEverythingFlutter： ${everything.aInt}');
    print('giveMeEverythingFlutter： ${everything.aString}');
  }
}
