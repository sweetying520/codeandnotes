// ignore_for_file: use_key_in_widget_constructors, prefer_const_constructors
import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text("Flutter Container 组件 and Text 组件"),
          elevation: 30, //设置标题阴影
        ),
        body: MyHome(),
      ),
    );
  }
}

class MyHome extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    //方法一
    return Center(
      child: Container(
        child: Image.asset(
          "images/chat_message_clear.png",
          fit: BoxFit.cover,
        ),
        width: 300,
        height: 300,
        decoration: BoxDecoration(
          color: Colors.green,
        ),
      ),
    );
  }
}
