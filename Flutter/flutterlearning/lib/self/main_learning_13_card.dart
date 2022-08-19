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
          title: Text("Flutter Card 组件"),
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
    return ListView(
      children: [
        Card(
          margin: EdgeInsets.all(10),
          child: Column(
            children: [
              ListTile(
                  title: Text("张三", style: TextStyle(fontSize: 28)),
                  subtitle: Text("高级软件工程师")
              ),
              Divider(),
              ListTile(
                  title: Text("电话:1213214142")
              ),
              ListTile(
                  title: Text("地址:北京市海淀区")
              )
            ],
          ),
        ),
        Card(
          margin: EdgeInsets.all(10),
          child: Column(
            children: [
              ListTile(
                  title: Text("李四", style: TextStyle(fontSize: 28)),
                  subtitle: Text("高级软件工程师")
              ),
              Divider(),
              ListTile(
                  title: Text("电话:1213214142")
              ),
              ListTile(
                  title: Text("地址:北京市海淀区")
              )
            ],
          ),
        )
      ],
    );
  }
}
