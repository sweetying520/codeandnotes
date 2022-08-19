// ignore_for_file: prefer_const_constructors

import 'package:flutter/material.dart';

class ExtendButtonPageFor2 extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("按钮演示页面")),
      floatingActionButton: FloatingActionButton(
        child: Icon(Icons.add,color: Colors.black,),
        onPressed: (){
          print('click FloatingActionButton');
        },
        backgroundColor: Colors.yellow,
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.centerFloat,
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                ElevatedButton(
                  onPressed: () {
                    print('点击了普通按钮');
                  },
                  child: Text("普通按钮"),
                ),
                SizedBox(width: 20),
                ElevatedButton(
                  onPressed: () {
                    print('点击了有颜色的按钮');
                  },
                  child: Text("有颜色的按钮"),
                  style: ButtonStyle(
                    backgroundColor: MaterialStateProperty.all(Colors.red),
                    foregroundColor:  MaterialStateProperty.all(Colors.white),

                  ),
                ),
                SizedBox(width: 20),
                ElevatedButton(
                  child: Text('阴影按钮'),
                  onPressed: () {
                    print('点击了阴影按钮');
                  },
                  style: ButtonStyle(
                    foregroundColor: MaterialStateProperty.all(Colors.red),
                    backgroundColor: MaterialStateProperty.all(Colors.yellow),
                    elevation: MaterialStateProperty.all(20),
                  ),
                )
              ],
            ),
            Row(
              children: [
                Expanded(
                    child: Container(
                      height: 50,
                      margin: EdgeInsets.all(20),
                      child: RaisedButton(
                        child: Text('自适应按钮'),
                        textColor: Colors.white,
                        color: Colors.blue,
                        elevation: 20,
                        onPressed: () {
                          print('点击了自适应按钮');
                        },
                      ),
                    ))
              ],
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                ElevatedButton.icon(
                  onPressed: () {
                    print('点击了Icon按钮');
                  },
                  icon: Icon(Icons.home),
                  label: Text("Icon 按钮"),
                  style: ButtonStyle(
                    foregroundColor: MaterialStateProperty.all(Colors.red),
                    backgroundColor: MaterialStateProperty.all(Colors.yellow)
                  ),
                ),
                SizedBox(width: 20),
                Container(
                  height: 60,
                  width: 200,
                  child: ElevatedButton(
                    onPressed: () {
                      print('点击了有宽高的按钮');
                    },
                    child: Text("有宽高的按钮"),
                    style: ButtonStyle(
                        foregroundColor: MaterialStateProperty.all(Colors.red),
                        backgroundColor: MaterialStateProperty.all(Colors.yellow),
                      elevation: MaterialStateProperty.all(20)
                    ),
                  ),
                )
              ],
            ),
            SizedBox(height: 20),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                Expanded(
                    child: Container(
                      height: 60,
                      margin: EdgeInsets.all(20),
                      child: RaisedButton(
                        child: Text('全屏按钮'),
                        textColor: Colors.white,
                        color: Colors.blue,
                        elevation: 10,
                        onPressed: () {
                          print('点击了全屏按钮');
                        },
                      ),
                    ))
              ],
            ),
            Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: <Widget>[
                  Expanded(
                      child: Container(
                        height: 60,
                        margin: EdgeInsets.all(20),
                        child: ElevatedButton(
                          child: Text('带圆角的按钮'),
                          style: ButtonStyle(
                            foregroundColor: MaterialStateProperty.all(Colors.white),
                            backgroundColor: MaterialStateProperty.all(Colors.blue),
                            elevation: MaterialStateProperty.all(10),
                            shape: MaterialStateProperty.all(RoundedRectangleBorder(borderRadius: BorderRadius.circular(50)))
                          ),
                          onPressed: () {
                            print('点击了带圆角的按钮');
                          },
                        ),
                      ))
                ]),
            Row(mainAxisAlignment: MainAxisAlignment.center, children: [
              MyButton(
                  txt: "自定义按钮",
                  pressed: () {
                    print('点击了自定义按钮');
                  }
              ),
              Container(
                height: 100,
                width: 150,
                child: ElevatedButton(
                  onPressed: () {
                    print('点击了圆形按钮');
                  },
                  child: Text('圆形按钮'),
                  style: ButtonStyle(
                      foregroundColor: MaterialStateProperty.all(Colors.white),
                      backgroundColor: MaterialStateProperty.all(Colors.blue),
                      elevation: MaterialStateProperty.all(10),
                      shape: MaterialStateProperty.all(const CircleBorder(side: BorderSide(color: Colors.white)))
                  ),
                ),
              )
            ]),
            Row(
              children: [
                Expanded(
                    child:Container(
                      margin: EdgeInsets.fromLTRB(20, 0, 20, 0),
                      child:  OutlinedButton(
                        child: Text("注册"),
                        onPressed: (){
                          print('注册');
                        },
                        style: ButtonStyle(
                          foregroundColor: MaterialStateProperty.all(Colors.black),
                          side: MaterialStateProperty.all(BorderSide(
                            width: 1,
                            color: Colors.red
                          ))
                        ),
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

//自定义按钮组件
class MyButton extends StatelessWidget {
  final txt;
  final pressed;
  final width;
  final height;

  const MyButton({Key? key,
    this.txt = "erdai",
    this.pressed,
    this.width = 150.0,
    this.height = 30.0})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      height: height,
      width: width,
      child: ElevatedButton(
        onPressed: pressed,
        child: Text(txt),
      ),
    );
  }
}