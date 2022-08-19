// ignore_for_file: prefer_const_constructors

import 'package:flutter/material.dart';

class RegisterSecondPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(title: Text("第二步-验证码")),
        body: Center(
          child: Column(
            children: <Widget>[
              SizedBox(height: 40),
              Text("输入验证码完成注册"),
              SizedBox(height: 40),
              ElevatedButton(
                child: Text('下一步'),
                onPressed: () {
                  Navigator.pushNamed(context, '/registerThird');

                  //替换路由
                  // Navigator.of(context).pushReplacementNamed('/registerThird');
                },
              )
            ],
          ),
        ));
  }
}
