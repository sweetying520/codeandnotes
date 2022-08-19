// ignore_for_file: prefer_const_constructors

import 'dart:async';

import 'package:flutter/material.dart';

class MyLoadingDialog extends Dialog {
  String title = "";
  String content = "";

  void _showTimer(context) {
    Timer.periodic(Duration(milliseconds: 3000), (t) {
      print('关闭');
      Navigator.pop(context);
      t.cancel();
    });
  }

  MyLoadingDialog({this.title = "", this.content = ""});

  @override
  Widget build(BuildContext context) {
    _showTimer(context);
    return Material(
        type: MaterialType.transparency,
        child: Center(
          child: Container(
            height: 200,
            width: 200,
            color: Colors.white,
            child: Column(
              children: [
                Padding(
                  padding: EdgeInsets.all(10),
                  child: Stack(
                    // ignore: prefer_const_literals_to_create_immutables
                    children: [
                      Align(child: Text(title), alignment: Alignment.center),
                      Align(
                          alignment: Alignment.centerRight,
                          child: InkWell(
                            child: Icon(Icons.close),
                            onTap: () {
                              print('close');
                              Navigator.pop(context);
                            },
                          ))
                    ],
                  ),
                ),
                Divider(),
                Container(
                    width: double.infinity,
                    child: Padding(
                      padding: EdgeInsets.only(left: 10),
                      child: Text(content, textAlign: TextAlign.start),
                    ))
              ],
            ),
          ),
        ));
  }
}
