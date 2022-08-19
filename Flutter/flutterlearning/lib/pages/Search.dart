// ignore_for_file: prefer_const_constructors_in_immutables, prefer_const_constructors

import 'package:flutter/material.dart';

class SearchPage extends StatelessWidget{

  final arguments;

  SearchPage({this.arguments});

  @override
  Widget build(BuildContext context) {
      return Scaffold(
        appBar: AppBar(
          title: Text('我是搜索页面')
        ),
        body: Text("搜索页面内容区域：$arguments")
      );
  }
}