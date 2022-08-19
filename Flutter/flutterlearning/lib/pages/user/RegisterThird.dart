// ignore_for_file: use_key_in_widget_constructors, prefer_const_constructors, unnecessary_new

import 'package:flutter/material.dart';

//引入tabs

import '../Tabs.dart';

class RegisterThirdPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(title: Text("第三步-完成注册")),
        body: Center(
          child: Column(
            children: <Widget>[
              SizedBox(height: 40),
              Text("输入密码完成注册"),
              SizedBox(height: 40),
              ElevatedButton(
                child: Text('确定'),
                onPressed: () {
                  //返回根
                  Navigator.of(context).pushAndRemoveUntil(
                      new MaterialPageRoute(
                          builder: (context) => new Tabs(index: 2)),
                      (route) => route == null);
                },
              )
            ],
          ),
        ));
  }
}
