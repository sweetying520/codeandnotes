import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:flutter_plugin/flutter_plugin.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _platformVersion = "unknown version";
  String _userName = "unknown";
  int _age = -1;
  final _flutterPlugin = FlutterPlugin();

  @override
  void initState() {
    super.initState();
    initPlatformState();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPlatformState() async {
    String platformVersion = "";
    String userName = "";
    int age = -1;

    try {
      platformVersion = await _flutterPlugin.getPlatformVersion() ??
          'Unknown platform version';
      userName = await _flutterPlugin.getUserName() ?? 'Unknown UserName';
      age = await _flutterPlugin.getAge() ?? -1;
    } catch (e) {
      print(e);
    }

    if (!mounted) return;

    setState(() {
      _platformVersion = platformVersion;
      _userName = userName;
      _age = age;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Text(
              'Running on: $_platformVersion\nMy name is $_userName,\n I am $_age years old'),
        ),
      ),
    );
  }
}
