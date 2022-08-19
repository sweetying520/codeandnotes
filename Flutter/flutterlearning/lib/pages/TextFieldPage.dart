// ignore_for_file: prefer_const_constructors, prefer_const_literals_to_create_immutables

import 'package:flutter/material.dart';

class TextFieldPage extends StatefulWidget {
  @override
  _TextFieldPagePageState createState() => _TextFieldPagePageState();
}

class _TextFieldPagePageState extends State<TextFieldPage> {

  var userName = TextEditingController();
  var _password;


  @override
  void initState() {
    super.initState();
    userName.text = "初始值";
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('表单演示页面'),
      ),
      body: Padding(
        padding: EdgeInsets.all(20),
        //child: TextPage(),
        child: Column(
          children: [
            TextField(
              decoration: InputDecoration(
                  hintText: "请输入用户名",
              ),
              controller: userName,
              onChanged: (value){
                setState(() {
                  userName.text = value;
                });
              },
            ),
            SizedBox(height: 10),
            TextField(
              obscureText: true,
              decoration: InputDecoration(
                hintText: "请输入用密码",
              ),
              onChanged: (value){
                setState(() {
                  _password = value;
                });
              },
            ),
            SizedBox(height: 40),
            Row(
              children: [
                Expanded(
                    child: Container(
                      child: RaisedButton(
                        child: Text("登陆"),
                        onPressed: (){
                          print(userName.text);
                          print(_password);
                    },
                        color: Colors.blue,
                        textColor: Colors.white,
                      ),
                    )
                )
              ],
            )
          ],
        ),
      ),
    );
  }
}

class TextPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        TextField(
          maxLines: 1,
          decoration: InputDecoration(
              icon: Icon(Icons.input),
              hintText: "请输入账号",
              border: OutlineInputBorder(),
              labelText: "账号"),
        ),
        SizedBox(height: 20),
        TextField(
            decoration: InputDecoration(
                icon: Icon(Icons.password),
                hintText: "请输入密码",
                border: OutlineInputBorder(),
                labelText: "密码")),
      ],
    );
  }
}
