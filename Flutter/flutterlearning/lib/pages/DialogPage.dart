// ignore_for_file: prefer_const_constructors, prefer_const_literals_to_create_immutables, unnecessary_string_interpolations



import 'package:flutter/material.dart';
import 'package:flutterlearning/components/MyLoadingDialog.dart';
import 'package:fluttertoast/fluttertoast.dart';

class DialogPage extends StatefulWidget {
  @override
  _DialogPageState createState() => _DialogPageState();
}

class _DialogPageState extends State<DialogPage> {


  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text('Dialog 组件演示页面'),
        ),
        body: Center(
          child: Column(
            children: [
              ElevatedButton(
                  onPressed: () {
                    _showAlertDialog();
                  },
                  child: Text('弹 AlertDialog')
              ),
              ElevatedButton(
                  onPressed: () {
                    _showSimpleDialog();
                  },
                  child: Text('弹 SimpleDialog')
              ),
              ElevatedButton(
                  onPressed: () {
                    _showBottomSheetDialog();
                  },
                  child: Text('弹 BottomSheetDialog')
              ),
              ElevatedButton(
                  onPressed: () {
                    _showToast();
                  },
                  child: Text('弹 Toast')
              ),
              ElevatedButton(
                  onPressed: () {
                    _showCustomDialog();
                  },
                  child: Text('弹自定义 Dialog')
              )
            ],
          ),
        )
    );
  }

  void _showAlertDialog() async{
    var result = await showDialog(
        context: context,
        builder: (context){
            return AlertDialog(
              title: Text("提示"),
              content: Text("你确定要删除吗？"),
              actions: [
                TextButton(onPressed: (){
                  print('取消');
                  Navigator.pop(context,"cancel");
                  }, child: Text("取消")
                ),
                TextButton(onPressed: (){
                  print('确定');
                  Navigator.pop(context,"ok");
                }, child: Text("确定")
                )
              ],
            );
        }
    );
    print(result);
  }
  void _showSimpleDialog() async{
    var result = await showDialog(
        context: context,
        builder: (context){
          return SimpleDialog(
            title: Text("SimpleDialog 提示"),
            children: [
              SimpleDialogOption(
                child: Text("Option A"),
                onPressed: (){
                  print('Option A');
                  Navigator.pop(context,"A");
                }
              ),
              Divider(),
              SimpleDialogOption(
                  child: Text("Option B"),
                  onPressed: (){
                    print('Option B');
                    Navigator.pop(context,"B");
                  }
              ),
              Divider(),
              SimpleDialogOption(
                  child: Text("Option C"),
                  onPressed: (){
                    print('Option C');
                    Navigator.pop(context,"C");
                  }
              )
            ],
          );
        }
    );
    print(result);
  }
  void _showBottomSheetDialog() async{
    var result = await showModalBottomSheet(
        context: context,
        builder: (context){
          return Container(
            height: 200,
            child: Column(
              children: [
                ListTile(
                  title: Text("分享 A"),
                  onTap: (){
                    Navigator.pop(context,"分享 A");
                  },
                ),
                Divider(),
                ListTile(
                  title: Text("分享 B"),
                  onTap: (){
                    Navigator.pop(context,"分享 B");
                  },
                ),
                Divider(),
                ListTile(
                  title: Text("分享 C"),
                  onTap: (){
                    Navigator.pop(context,"分享 C");
                  },
                )
              ],
            ),
          );
        }
    );
    print(result);
  }

  void _showToast() {
    Fluttertoast.showToast(
        msg: "提示信息",
        toastLength: Toast.LENGTH_LONG,
        gravity: ToastGravity.CENTER,
        timeInSecForIosWeb: 1,
        backgroundColor: Colors.black,
        textColor: Colors.white,
        fontSize: 16
    );
  }

  void _showCustomDialog() {
    showDialog(
        context: context,
        builder: (context) {
          return MyLoadingDialog(title: "你好",content:"erdai666");
        }
    );
  }
}

