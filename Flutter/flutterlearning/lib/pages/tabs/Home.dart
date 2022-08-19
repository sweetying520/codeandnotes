// ignore_for_file: prefer_const_constructors_in_immutables, prefer_const_constructors

import 'package:flutter/material.dart';
import '../Search.dart';

class HomePage extends StatefulWidget {
  HomePage({Key? key}) : super(key: key);

  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  @override
  Widget build(BuildContext context) {
    return Align(
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          ElevatedButton(
              onPressed: () {
                // Navigator.of(context)
                //     .push(MaterialPageRoute(builder: (context) => SearchPage()));

                //跳转命名路由
                Navigator.pushNamed(context, "/search", arguments: "123");
              },
              child: Text("跳转到搜索页面")),
          ElevatedButton(
              onPressed: () {
                //跳转命名路由
                Navigator.pushNamed(context, "/appbarPage");
              },
              child: Text("跳转到AppBarPage页面")),
          ElevatedButton(
              onPressed: () {
                //跳转命名路由
                Navigator.pushNamed(context, "/tabBarController");
              },
              child: Text("跳转到TabBarController页面")),
          ElevatedButton(
              onPressed: () {
                //跳转命名路由
                Navigator.pushNamed(context, "/extendButtonPage");
              },
              child: Text("跳转到extendButtonPage页面")),
          ElevatedButton(
              onPressed: () {
                //跳转命名路由
                Navigator.pushNamed(context, "/extendButtonPageFor2");
              },
              child: Text("跳转到extendButtonPageFor2页面")),
          ElevatedButton(
              onPressed: () {
                //跳转命名路由
                Navigator.pushNamed(context, "/textFieldPage");
              },
              child: Text("跳转到TextFiledPage页面")),
          ElevatedButton(
              onPressed: () {
                //跳转命名路由
                Navigator.pushNamed(context, "/checkBoxPage");
              },
              child: Text("跳转到CheckBoxPage页面")),
          ElevatedButton(
              onPressed: () {
                //跳转命名路由
                Navigator.pushNamed(context, "/radioAndSwitchPage");
              },
              child: Text("跳转到RadioAndSwitchPage页面")),
          ElevatedButton(
              onPressed: () {
                //跳转命名路由
                Navigator.pushNamed(context, "/generalFormPage");
              },
              child: Text("跳转到GeneralFormPage页面")),
          ElevatedButton(
              onPressed: () {
                //跳转命名路由
                Navigator.pushNamed(context, "/dataAndTimePage");
              },
              child: Text("跳转到DataAndTimePage页面")),
          ElevatedButton(
              onPressed: () {
                //跳转命名路由
                Navigator.pushNamed(context, "/dialogPage");
              },
              child: Text("跳转到DialogPage页面")),
          ElevatedButton(
              onPressed: () {
                //跳转命名路由
                Navigator.pushNamed(context, "/httpPage");
              },
              child: Text("跳转到HttpPage页面")),
        ],
      ),
    );
  }
}


