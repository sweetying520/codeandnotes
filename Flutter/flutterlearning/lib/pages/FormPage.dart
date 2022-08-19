// ignore_for_file: prefer_const_constructors_in_immutables, prefer_const_constructors, prefer_const_literals_to_create_immutables

import 'package:flutter/material.dart';

class FormPage extends StatelessWidget {

  String title;
  final arguments;

  FormPage({this.title = "表单",this.arguments,Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        floatingActionButton: FloatingActionButton(
          child: Text("返回"),
          onPressed: (){
            Navigator.of(context).pop();
          },
        ),
        appBar: AppBar(title: Text(arguments)),
        body: ListView(
          children: [
            ListTile(title: Text("我是表单页面")),
            ListTile(
              title: Text("我是表单页面"),
            ),
            ListTile(
              title: Text("我是表单页面"),
            ),
            ListTile(
              title: Text("我是表单页面"),
            )
          ],
        ));
  }
}
