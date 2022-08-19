import 'package:flutter/material.dart';

void main() {
  runApp(MaterialApp(
    home: Scaffold(
      appBar: AppBar(
        title: Text("Flutter Demo"),
      ),
      body: HomeContent(),
    ),
  ));
}

class HomeContent extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Center(
      child: Container(
        child: const Text(
          "各位同学大家好，我是xxx，各位同学大家好，我是xxx",
          textAlign: TextAlign.left,
          overflow: TextOverflow.ellipsis,
          textScaleFactor: 2,
          maxLines: 2,
          style: TextStyle(
              fontSize: 16.0,
              color: Colors.red,
              fontWeight: FontWeight.w800,
              fontStyle: FontStyle.italic,
              decoration: TextDecoration.lineThrough,
              decorationColor: Colors.black,
              decorationStyle: TextDecorationStyle.dashed,
              letterSpacing: 5.0),
        ),
        height: 300.0,
        width: 300.0,
        decoration: BoxDecoration(
            color: Colors.yellow,
            border: Border.all(color: Colors.blue, width: 2.0),
            borderRadius: const BorderRadius.all(Radius.circular(20))),
        padding: const EdgeInsets.fromLTRB(10, 30, 5, 0),
        margin: const EdgeInsets.all(50),
        // transform: Matrix4.translationValues(100, 0, 0),
        // transform: Matrix4.rotationZ(-0.3),
        // transform: Matrix4.diagonal3Values(1.2, 1, 1),
        alignment: Alignment.bottomRight,
      ),
    );
  }
}
