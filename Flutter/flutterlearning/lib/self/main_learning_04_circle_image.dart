// ignore_for_file: use_key_in_widget_constructors, prefer_const_constructors
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
          title: Text("Flutter Container 组件 and Text 组件"),
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
    //方法一
    // return Center(
    //   child: Container(
    //     width: 300,
    //     height: 300,
    //     decoration: BoxDecoration(
    //       color: Colors.blue,
    //       borderRadius: BorderRadius.circular(200),
    //       image: DecorationImage(
    //         image: NetworkImage("https://img.lianzhixiu.com/uploads/210106/37-21010609363aS.jpg"),
    //         fit: BoxFit.cover
    //       )
    //     ),
    //   ),
    // );

    //方法二
    return Center(
      child: ClipOval(
        child: Image.network(
          "https://img.lianzhixiu.com/uploads/210106/37-21010609363aS.jpg",
          width: 300,
          height: 300,
          fit: BoxFit.cover,
        ),
      ),
    );
  }
}
