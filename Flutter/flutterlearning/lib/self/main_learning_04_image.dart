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
    return Center(
      child: Container(
        child: Image.network(
          "https://img.lianzhixiu.com/uploads/210106/37-21010609363aS.jpg",
          alignment: Alignment.bottomRight,
          color: Colors.green,
          colorBlendMode: BlendMode.screen,
          fit: BoxFit.cover,
        ),
        width: 300,
        height: 300,
        decoration: BoxDecoration(
          color: Colors.blue,
          border: Border.all(
            color: Colors.red,
            width: 2
          ),
        ),
      ),
    );
  }
}
