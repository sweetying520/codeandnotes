// ignore_for_file: use_key_in_widget_constructors, prefer_const_constructors, prefer_const_literals_to_create_immutables

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
          title: Text("Flutter Card 组件"),
          elevation: 30, //设置标题阴影
        ),
        body: MyHome(),
      ),
    );
  }
}

class MyHome extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Wrap(
      spacing: 10,
      runSpacing: 10,
      alignment: WrapAlignment.start,
      children: [
        ElevatedButton(onPressed: (){}, child: Text("第一集")),
        ElevatedButton(onPressed: (){}, child: Text("第二集")),
        ElevatedButton(onPressed: (){}, child: Text("第三集")),
        ElevatedButton(onPressed: (){}, child: Text("第四集第四集")),
        ElevatedButton(onPressed: (){}, child: Text("第五集")),
        ElevatedButton(onPressed: (){}, child: Text("第六集")),
        ElevatedButton(onPressed: (){}, child: Text("第七集")),
        ElevatedButton(onPressed: (){}, child: Text("第八集第八集")),
        ElevatedButton(onPressed: (){}, child: Text("第九集")),
        ElevatedButton(onPressed: (){}, child: Text("第十集")),
      ],
    );
  }
}
