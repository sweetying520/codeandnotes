// ignore_for_file: prefer_const_constructors, prefer_const_literals_to_create_immutables

import 'package:flutter/material.dart';

class CheckBoxPage extends StatefulWidget {
  @override
  _CheckBoxPagePageState createState() => _CheckBoxPagePageState();
}

class _CheckBoxPagePageState extends State<CheckBoxPage> {

  var flag = true;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('CheckBox 演示页面'),
      ),
      body: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Checkbox(value: flag,
              onChanged: (value){
              setState(() {
                flag = value??false;
              });
          },
            activeColor: Colors.red,
          ),
          Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Text(flag == true ? "选中" : "未选中")
            ],
          ),
          Divider(color: Colors.black),
          CheckboxListTile(value: flag, onChanged: (value){
            setState(() {
              flag = value??false;
            });
          },
            title: Text('标题'),
            subtitle: Text("描述"),
            selected: flag,
          ),

          Divider(color: Colors.black),

          CheckboxListTile(value: flag, onChanged: (value){
            setState(() {
              flag = value??false;
            });
          },
              title: Text('标题'),
              subtitle: Text("描述"),
            secondary: Icon(Icons.home),
            selected: flag,
          )
        ],
      )
    );
  }
}

