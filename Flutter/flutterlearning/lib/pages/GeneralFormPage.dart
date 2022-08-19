// ignore_for_file: prefer_const_constructors, use_key_in_widget_constructors, prefer_const_literals_to_create_immutables

import 'package:flutter/material.dart';

class GeneralFormPage extends StatefulWidget {
  @override
  _GeneralFormPageState createState() => _GeneralFormPageState();
}

class _GeneralFormPageState extends State<GeneralFormPage> {

  String info = "";
  String userName = "";
  int sex = 1;
  List hobbies = [
    {"checked": true, "title": "吃饭"},
    {"checked": true, "title": "睡觉"},
    {"checked": true, "title": "写代码"},
  ];

  List<Widget> _getHobbies() {
    List<Widget> temp = [];
    for (var element in hobbies) {
      temp.add(Row(
        children: [
          Text(element["title"] + "："),
          Checkbox(
              value: element["checked"],
              onChanged: (value) {
                setState(() {
                  element["checked"] = value;
                });
              })
        ],
      ));
    }
    return temp;
  }

  void _sexChanged(value){
    setState(() {
      sex = value as int;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('学生信息登记系统'),
      ),
      body: Padding(
        padding: EdgeInsets.all(20),
        child: Column(
          children: [
            TextField(
              decoration: InputDecoration(
                hintText: "输入用户信息"
              ),
              onChanged: (str){
                setState(() {
                  userName = str;
                });
              },
            ),
            Row(
              children: [
                Text("男"),
                Radio(value: 1, groupValue: sex, onChanged: _sexChanged),
                Text("女"),
                Radio(value: 2, groupValue: sex, onChanged:_sexChanged)
              ],
            ),
            //爱好
            Wrap(
              children: _getHobbies(),
            ),

            TextField(
              decoration: InputDecoration(
                  hintText: "描述信息",
                border: OutlineInputBorder()
              ),
              maxLines: 4,

              onChanged: (str){
                setState(() {
                  info = str;
                });
              },
            ),

            SizedBox(height: 40),
            Container(
              width: double.infinity,
              child: RaisedButton(
                child: Text("获取学生信息"),
                onPressed: (){
                  print(userName);
                  print(sex);
                  print(hobbies);
                  print(info);
                  },
                color: Colors.blue,
                textColor: Colors.white,
              ),
            )
          ],
        ),
      )
    );
  }
}
