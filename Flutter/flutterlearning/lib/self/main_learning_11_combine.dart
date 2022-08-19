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
      child: Column(
        children: [
          Container(color: Colors.black, height: 180),
          SizedBox(height: 10),
          Row(
            children: [
              Expanded(
                  flex: 2,
                  child: Container(
                    child: Image.network(
                        "https://img.lianzhixiu.com/uploads/210106/37-21010609363aS.jpg",
                        fit: BoxFit.cover,
                        height: 100),
                  )),
              SizedBox(width: 10),
              Expanded(
                flex: 1,
                child: Container(
                  height: 100,
                  child: ListView(
                    children: [
                      Image.network(
                          "https://img.lianzhixiu.com/uploads/210106/37-21010609363aS.jpg",
                          fit: BoxFit.cover,
                          height: 45),
                      SizedBox(height: 10),
                      Image.network(
                          "https://img.lianzhixiu.com/uploads/210106/37-21010609363aS.jpg",
                          fit: BoxFit.cover,
                          height: 45),
                    ],
                  ),
                ),
              )
            ],
          )
        ],
      ),
    );
  }
}
