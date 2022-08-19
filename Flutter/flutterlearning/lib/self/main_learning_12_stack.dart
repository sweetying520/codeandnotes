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
        body: MyHomeStackPositioned(),
      ),
    );
  }
}

class MyHomeStack extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Center(
      child: Stack(
        alignment: Alignment.center,
        children: [
          Image.network(
            "https://img.lianzhixiu.com/uploads/210106/37-21010609363aS.jpg",
            height: 100,
            width: 100,
            fit: BoxFit.cover,
          ),
          Text(
            "erdai666",
            style: TextStyle(fontSize: 28),
          ),
        ],
      ),
    );
  }
}

class MyHomeAlign extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Align(
      alignment: Alignment.topRight,
      child: Image.network(
        "https://img.lianzhixiu.com/uploads/210106/37-21010609363aS.jpg",
        height: 100,
        width: 100,
        fit: BoxFit.cover,
      ),
    );
  }
}

class MyHomeStackPositioned extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      height: 1000,
      width: 1000,
      color: Colors.black38,
      child: Stack(
        children: [
          Positioned(
              top: 300,
              left: 150,
              child: Image.network(
                "https://img.lianzhixiu.com/uploads/210106/37-21010609363aS.jpg",
                height: 100,
                width: 100,
                fit: BoxFit.cover,
              )),
          Positioned(
              top: 200,
              left: 150,
              child: Text(
                "erdai666",
                style: TextStyle(fontSize: 28),
              ))
        ],
      ),
    );
  }
}
