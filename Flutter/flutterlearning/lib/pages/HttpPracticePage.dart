// ignore_for_file: prefer_const_constructors, prefer_const_literals_to_create_immutables, unnecessary_type_check

import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:http/http.dart' as http;

class HttpPracticePage extends StatefulWidget {
  @override
  _HttpPracticePageState createState() => _HttpPracticePageState();
}

class _HttpPracticePageState extends State<HttpPracticePage> {
  var list = [];

  @override
  void initState() {
    super.initState();
    _getData();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
          appBar: AppBar(
            title: Text('Http 实践页面'),
          ),
          body: list.isNotEmpty
              ? ListView.builder(
              itemCount: list.length,
              itemBuilder: (context, index) {
                  return ListTile(
                      onTap: (){
                        Fluttertoast.showToast(
                            msg: "${list[index]["name"]}",
                          toastLength: Toast.LENGTH_LONG,
                          gravity: ToastGravity.CENTER,
                          backgroundColor: Colors.green
                        );
                      },
                      title: Text("${list[index]["name"]}"),
                      subtitle: Text("${list[index]["link"]}"));
                })
              : Text("")),
    );
  }

  void _getData() async {
    var uri = Uri.https("www.wanandroid.com", "/friend/json");
    var result = await http.get(uri);
    if (result.statusCode == 200) {
      var map = json.decode(result.body) as Map<String, dynamic>;
      setState(() {
        list = map["data"];
      });
    }
  }
}
