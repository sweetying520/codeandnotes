import 'package:flutter/material.dart';

void main(){
  runApp(MyApp());
}

class MyApp extends StatelessWidget{

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text("Flutter Demo"),
        ),
        body: HomeContent(),
      ),
      theme: ThemeData(
        primarySwatch: Colors.yellow
      ),
    );
  }
}

class HomeContent extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    return const Center(
      child: Text(
        '你好 Flutter haha11',
        textDirection: TextDirection.ltr,
        style: TextStyle(
            fontSize: 30.0,
            color: Colors.yellow
          // color: Color.fromRGBO(244, 233, 214, 0.5),
        ),
      ),
    );
  }
}