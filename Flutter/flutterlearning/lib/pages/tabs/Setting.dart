// ignore_for_file: prefer_const_constructors_in_immutables, prefer_const_constructors, prefer_const_literals_to_create_immutables

import 'package:flutter/material.dart';

class SettingPage extends StatefulWidget {
  SettingPage({Key? key}) : super(key: key);

  @override
  _SettingPageState createState() => _SettingPageState();
}

class _SettingPageState extends State<SettingPage> {
  @override
  Widget build(BuildContext context) {
    return ListView(
      children: [
        ListTile(
          title: Text("我是一个文本"),
        ),
        ListTile(
          title: Text("我是一个文本"),
        ),
        ListTile(
          title: Text("我是一个文本"),
        ),
        ListTile(
          title: Text("我是一个文本"),
        ),
        ElevatedButton(onPressed: (){
          Navigator.pushNamed(context, "/login");
        }, child: Text("跳转到登陆页面")),
        ElevatedButton(onPressed: (){
          Navigator.pushNamed(context, "/registerFirst");
        }, child: Text("跳转到注册页面")),
      ],
    );
  }
}
