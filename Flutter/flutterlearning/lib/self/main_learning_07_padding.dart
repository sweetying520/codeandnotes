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
          title: Text("Flutter Padding 组件"),
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
        padding: EdgeInsets.fromLTRB(0, 0, 10, 10),
        child: GridView.count(crossAxisCount: 2, children: [
          Padding(
            padding: EdgeInsets.fromLTRB(10, 10, 0, 0),
            child: Image.network(
                "https://img.lianzhixiu.com/uploads/210106/37-21010609363aS.jpg",
                fit: BoxFit.cover),
          ),
          Padding(
            padding: EdgeInsets.fromLTRB(10, 10, 0, 0),
            child: Image.network(
                "https://img.lianzhixiu.com/uploads/210106/37-21010609363aS.jpg",
                fit: BoxFit.cover),
          ),
          Padding(
            padding: EdgeInsets.fromLTRB(10, 10, 0, 0),
            child: Image.network(
                "https://img.lianzhixiu.com/uploads/210106/37-21010609363aS.jpg",
                fit: BoxFit.cover),
          ),
          Padding(
            padding: EdgeInsets.fromLTRB(10, 10, 0, 0),
            child: Image.network(
                "https://img.lianzhixiu.com/uploads/210106/37-21010609363aS.jpg",
                fit: BoxFit.cover),
          ),
          Padding(
            padding: EdgeInsets.fromLTRB(10, 10, 0, 0),
            child: Image.network(
                "https://img.lianzhixiu.com/uploads/210106/37-21010609363aS.jpg",
                fit: BoxFit.cover),
          ),
          Padding(
            padding: EdgeInsets.fromLTRB(10, 10, 0, 0),
            child: Image.network(
                "https://img.lianzhixiu.com/uploads/210106/37-21010609363aS.jpg",
                fit: BoxFit.cover),
          ),
          Padding(
            padding: EdgeInsets.fromLTRB(10, 10, 0, 0),
            child: Image.network(
                "https://img.lianzhixiu.com/uploads/210106/37-21010609363aS.jpg",
                fit: BoxFit.cover),
          ),
          Padding(
            padding: EdgeInsets.fromLTRB(10, 10, 0, 0),
            child: Image.network(
                "https://img.lianzhixiu.com/uploads/210106/37-21010609363aS.jpg",
                fit: BoxFit.cover),
          ),
          Padding(
            padding: EdgeInsets.fromLTRB(10, 10, 0, 0),
            child: Image.network(
                "https://img.lianzhixiu.com/uploads/210106/37-21010609363aS.jpg",
                fit: BoxFit.cover),
          ),
          Padding(
            padding: EdgeInsets.fromLTRB(10, 10, 0, 0),
            child: Image.network(
                "https://img.lianzhixiu.com/uploads/210106/37-21010609363aS.jpg",
                fit: BoxFit.cover),
          ),
          Padding(
            padding: EdgeInsets.fromLTRB(10, 10, 0, 0),
            child: Image.network(
                "https://img.lianzhixiu.com/uploads/210106/37-21010609363aS.jpg",
                fit: BoxFit.cover),
          )
        ]));
  }
}
