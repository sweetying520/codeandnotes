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
          title: Text("Flutter GridView 组件"),
          elevation: 30, //设置标题阴影
        ),
        body: MyHome(),
      ),
    );
  }
}

class MyHome extends StatelessWidget {
  var list = [];

  MyHome({Key? key}) : super(key: key) {
    for (int i = 0; i < 20; i++) {
      list.add(
          "https://img.lianzhixiu.com/uploads/210106/37-21010609363aS.jpg");
    }
  }

  @override
  Widget build(BuildContext context) {
    return GridView.builder(
      padding: EdgeInsets.fromLTRB(0, 0, 10, 10),
      gridDelegate:
          SliverGridDelegateWithFixedCrossAxisCount(crossAxisCount: 2),
      itemBuilder: (context, index) {
        return Container(
          margin: EdgeInsets.fromLTRB(10, 10, 0, 0),
          child: Image.network(
            list[index],
            fit: BoxFit.cover,
          ),
        );
      },
      itemCount: list.length,
    );
  }
}
