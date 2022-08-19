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
          title:const Text('Flutter Demo 4'),
        ),
        body: HomePage(),
      ),
    );
  }
}

class HomePage extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    return Center(
      child: Container(
        child: Image.network("https://raw.githubusercontent.com/sweetying520/picgo/master/img/image-20210616204148155.png",
          alignment: Alignment.topCenter,
          // color: Colors.red,
          // colorBlendMode: BlendMode.darken,
          fit: BoxFit.cover,
        ),
        width: 300,
        height: 300,
        decoration:const BoxDecoration(
          color: Colors.yellow
        ),
      ),
    );
  }
}