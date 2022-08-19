// ignore_for_file: use_key_in_widget_constructors, prefer_const_constructors, prefer_const_literals_to_create_immutables

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
          title: Text("Flutter Expanded 组件"),
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
    return Padding(
      padding: EdgeInsets.all(10),
      child: Row(
        children: [
          Expanded(flex: 1, child: IconContainer(Icons.home)),
          SizedBox(width: 10),
          Expanded(flex: 2, child: IconContainer(Icons.search)),
        ],
      ),
    );
  }
}

class IconContainer extends StatelessWidget {
  double? size;
  IconData? icon;
  Color? color;

  IconContainer(this.icon, {this.color = Colors.blue, this.size = 32});

  @override
  Widget build(BuildContext context) {
    return Container(
      width: size! + 60,
      height: size! + 60,
      color: color,
      child: Center(
        child: Icon(
          icon,
          color: Colors.white,
          size: size,
        ),
      ),
    );
  }
}
