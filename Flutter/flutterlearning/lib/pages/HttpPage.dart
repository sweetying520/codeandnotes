// ignore_for_file: prefer_const_constructors, prefer_const_literals_to_create_immutables, unnecessary_type_check

import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

class HttpPage extends StatefulWidget {
  @override
  _HttpPageState createState() => _HttpPageState();
}

class _HttpPageState extends State<HttpPage> {
  var jsonMap = {"name": "erdai", "age": 18};

  var jsonString = '{"name":"erdai666","age":20}';


  _testEncode() {
    print(jsonMap is Map);
    String result = json.encode(jsonMap);
    print(result is String);
  }

  _testDecode(){
    var response = json.decode(jsonString) as Map;
    print(response["name"]);
    print(response["age"]);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text('Http 演示页面'),
        ),
        body: Align(
          alignment: Alignment.center,
          child: Column(
            children: [
              SizedBox(height: 40),
              //1、Get 请求 Button
              OutlinedButton(
                onPressed: () {
                  _testEncode();
                  _getData();
                },
                child: Text("Get 请求"),
                style: ButtonStyle(
                    backgroundColor: MaterialStateProperty.all(Colors.blue),
                    foregroundColor: MaterialStateProperty.all(Colors.white)),
              ),
              SizedBox(height: 40),
              //2、Post 请求 Button
              OutlinedButton(
                onPressed: () {
                  _testDecode();
                  _postData();
                },
                child: Text("Post 请求"),
                style: ButtonStyle(
                    backgroundColor: MaterialStateProperty.all(Colors.red),
                    foregroundColor: MaterialStateProperty.all(Colors.white)),
              ),
              SizedBox(height: 40),
              //3、实际请求渲染页面
              OutlinedButton(
                onPressed: () {
                  Navigator.pushNamed(context, "/httpPracticePage");
                },
                child: Text("实际请求渲染页面"),
                style: ButtonStyle(
                    backgroundColor: MaterialStateProperty.all(Colors.yellow),
                    foregroundColor: MaterialStateProperty.all(Colors.black)),
              )
            ],
          ),
        ));
  }

  void _getData() async{
    var uri = Uri.https("www.wanandroid.com", "/friend/json");
    var result = await http.get(uri);
    print(result.statusCode);
  }

  void _postData() async{
    var uri = Uri.https("www.wanandroid.com", "/user/login");
    var result = await http.post(uri,body: {"username":"zhouying","password":"erdai666"});
    print(result.statusCode);
    print(result.body);
  }

}
