import 'package:flutter/material.dart';

class ExtendButtonPage extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("按钮演示页面")),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                RaisedButton(
                  onPressed: () {
                    print('点击了普通按钮');
                  },
                  child: Text("普通按钮"),
                ),
                SizedBox(width: 20),
                RaisedButton(
                  onPressed: () {
                    print('点击了有颜色的按钮');
                  },
                  child: Text("有颜色的按钮"),
                  textColor: Colors.white,
                  color: Colors.blue,
                ),
                SizedBox(width: 20),
                RaisedButton(
                  child: Text('阴影按钮'),
                  textColor: Colors.white,
                  color: Colors.blue,
                  elevation: 20,
                  onPressed: () {
                    print('点击了阴影按钮');
                  },
                )
              ],
            ),
            SizedBox(height: 40),
            Row(
              children: [
                Expanded(
                    child: Container(
                      height: 100,
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
            SizedBox(height: 40),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                RaisedButton.icon(
                  onPressed: () {
                    print('点击了Icon按钮');
                  },
                  icon: Icon(Icons.home),
                  label: Text("Icon 按钮"),
                  textColor: Colors.white,
                  color: Colors.blue,
                ),
                SizedBox(width: 20),
                Container(
                  height: 60,
                  width: 200,
                  child: RaisedButton(
                    onPressed: () {
                      print('点击了有宽高的按钮');
                    },
                    child: Text("有宽高的按钮"),
                    textColor: Colors.white,
                    color: Colors.blue,
                    elevation: 10,
                  ),
                )
              ],
            ),
            SizedBox(height: 40),
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
                        child: RaisedButton(
                          child: Text('带圆角的按钮'),
                          textColor: Colors.white,
                          color: Colors.blue,
                          elevation: 10,
                          shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(10),
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
                child: RaisedButton(
                  onPressed: () {
                    print('点击了圆形按钮');
                  },
                  child: Text('圆形按钮'),
                  textColor: Colors.white,
                  color: Colors.black,
                  elevation: 20,
                  splashColor: Colors.green,
                  shape: CircleBorder(side: BorderSide(color: Colors.white)),
                ),
              )
            ])
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
      child: RaisedButton(
        onPressed: pressed,
        child: Text(txt),
      ),
    );
  }
}