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
          title: Text("Flutter ListView 组件"),
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
      child: ListView(
        children: [
          ListTile(
            leading: Icon(Icons.phone),
            title: Text(
              "This is List Title",
              style: TextStyle(fontSize: 28),
            ),
            subtitle: Text("This is list subtitle"),
          ),
          ListTile(
            leading: Icon(Icons.clear),
            title: Text(
              "This is List Title",
              style: TextStyle(fontSize: 28),
            ),
            subtitle: Text("This is list subtitle"),
          ),
          ListTile(
            leading: Icon(Icons.send),
            title: Text(
              "This is List Title",
              style: TextStyle(fontSize: 28),
            ),
            subtitle: Text("This is list subtitle"),
          ),
          ListTile(
            leading: Icon(Icons.https),
            title: Text(
              "This is List Title",
              style: TextStyle(fontSize: 28),
            ),
            subtitle: Text("This is list subtitle"),
          ),
          ListTile(
            leading: Icon(Icons.search),
            title: Text(
              "This is List Title",
              style: TextStyle(fontSize: 28),
            ),
            subtitle: Text("This is list subtitle"),
          )
        ],
      ),
    );
  }
}
