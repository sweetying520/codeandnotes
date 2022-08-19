// ignore_for_file: prefer_const_constructors, prefer_const_literals_to_create_immutables


import 'package:flutter/material.dart';

class RadioAndSwitchPage extends StatefulWidget {
  @override
  _RadioAndSwitchPageState createState() => _RadioAndSwitchPageState();
}

class _RadioAndSwitchPageState extends State<RadioAndSwitchPage> {
  int sex = 1;
  bool flag = true;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text('Radio 演示页面'),
        ),
        body: Column(
          children: [
            // Row(
            //   children: [
            //     Text("男"),
            //     Radio(
            //         value: 1,
            //         onChanged: (value) {
            //           setState(() {
            //             sex = value as int;
            //           });
            //         },
            //         groupValue: sex),
            //     SizedBox(width: 20),
            //     Text("女"),
            //     Radio(
            //         value: 2,
            //         onChanged: (value) {
            //           setState(() {
            //             sex = value as int;
            //           });
            //         },
            //         groupValue: sex)
            //   ],
            // ),
            // Row(
            //   children: [
            //     Text("$sex"),
            //     SizedBox(width: 40),
            //     Text(sex == 1 ? "男" : "女")
            //   ],
            // ),
            // SizedBox(height: 40),

            RadioListTile(
                value: 1,
                onChanged: (value) {
                  setState(() {
                    sex = value as int;
                  });
                },
                groupValue: sex,
              title: Text("一级标题"),
              subtitle: Text("二级标题"),
              secondary: Icon(Icons.home),
              selected: sex == 1,
            ),
            RadioListTile(
                value: 2,
                onChanged: (value) {
                  setState(() {
                    sex = value as int;
                  });
                },
                groupValue: sex,
                title: Text("一级标题"),
                subtitle: Text("二级标题"),
                secondary: Image.network("https://img.lianzhixiu.com/uploads/210106/37-21010609363aS.jpg"),
                selected: sex == 2,
            ),
            SizedBox(height: 40),
            Switch(value: flag, onChanged: (value){
              setState(() {
                print(value);
                flag = value;
              });
            })
          ],
        ));
  }
}

