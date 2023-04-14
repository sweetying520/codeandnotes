import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:flutter_plugin_with_pigeon/all_types_pigeon_generate.dart';
import 'package:flutter_plugin_with_pigeon/flutter_plugin_with_pigeon.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {

  var allTypePigeon = HostEverything();

  @override
  void initState() {
    super.initState();
    initPlatformState();
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
            )
          ],
        ),
      ),
    );
  }
}
