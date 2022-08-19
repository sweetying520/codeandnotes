// ignore_for_file: prefer_const_constructors, prefer_const_literals_to_create_immutables, unnecessary_string_interpolations


import 'package:date_format/date_format.dart';
import 'package:flutter/material.dart';

class DataAndTimePage extends StatefulWidget {
  @override
  _DataAndTimePageState createState() => _DataAndTimePageState();
}

class _DataAndTimePageState extends State<DataAndTimePage> {
  int sex = 1;
  bool flag = true;

  DateTime _nowDate = DateTime.now();
  TimeOfDay _nowTime = TimeOfDay.now();

  int getTimeMillion(){
    var now = DateTime.now();
    return now.microsecondsSinceEpoch;
  }


  DateTime getDate(){
    return DateTime.fromMicrosecondsSinceEpoch(getTimeMillion());
  }



  // /**
  //  * 展示日期组件
  //  */
  // void _showDatePicker() {
  //   showDatePicker(
  //       context: context,
  //       initialDate: _nowDate,
  //       firstDate: DateTime(1980),
  //       lastDate: DateTime(2100)
  //   ).then((value){
  //     print(value);
  //   });
  // }


  void _showDatePicker() async{
    var result = await showDatePicker(
        context: context,
        initialDate: _nowDate,
        firstDate: DateTime(1980),
        lastDate: DateTime(2100),
        locale: Locale('zh')
    );

    setState(() {
      _nowDate = result??DateTime.now();
    });
  }



  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text('日期和时间组件演示页面'),
        ),
        body: Padding(
          padding: EdgeInsets.all(20),
          child: Align(
              alignment: Alignment.center,
              child: Column(
                children: [
                  Text("时间戳：${getTimeMillion()}"),
                  SizedBox(height: 20),
                  Text("日期：${getDate()}"),
                  SizedBox(height: 20),
                  Text("${formatDate(DateTime(1989, 02, 21), [yyyy, '-', mm, '-', dd])}"),
                  SizedBox(height: 40),
                  InkWell(
                    child:  Row(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        Text("${formatDate(_nowDate, [yyyy, '年', mm, '月', dd,'日'])}"),
                        Icon(Icons.arrow_drop_down)

                      ],
                    ),
                    onTap: (){
                      print('打开日期组件...');
                      _showDatePicker();
                    },
                  ),
                  SizedBox(height: 40),
                  InkWell(
                    child:  Row(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        Text("${_nowTime.format(context)}"),
                        Icon(Icons.arrow_drop_down)

                      ],
                    ),
                    onTap: (){
                      print('打开时间组件...');
                      _showTimePicker();
                    },
                  )
                ],
              )
          ),
        )
    );
  }

  void _showTimePicker() async{
      var result = await showTimePicker(context: context, initialTime: TimeOfDay.now());
      setState(() {
        _nowTime = result??TimeOfDay.now();
      });
  }
}

