// ignore_for_file: use_key_in_widget_constructors, prefer_const_constructors
import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "我是一个标题",
      home: Scaffold(
        appBar: AppBar(
          title: Text("erdai"),
          elevation: 30, //设置标题阴影
        ),
        body: MyHome(),
      ),
      theme: ThemeData(primarySwatch: Colors.green),
    );
  }
}

class MyHome extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Center(
      child: Text(
        "我是一个文本内容",
        textDirection: TextDirection.ltr,
        style: TextStyle(
          fontSize: 40,
          fontWeight: FontWeight.bold,
          color: Colors.black38
        ),
      ),
    );
  }
}
