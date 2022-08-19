// ignore_for_file: prefer_const_constructors

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
          title: Text("My Flutter Demo"),
        ),
        body: const Center(
          child: Text(
            "erdai666",
            textDirection: TextDirection.ltr,
            style: TextStyle(
                fontSize: 28,
                fontWeight: FontWeight.bold,
                color: Colors.yellow),
            maxLines: 2,
          ),
        ),
      ),
    );
  }
}
