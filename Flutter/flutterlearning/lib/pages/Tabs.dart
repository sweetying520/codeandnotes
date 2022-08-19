// ignore_for_file: prefer_const_constructors, prefer_const_literals_to_create_immutables, unused_field, prefer_final_fields

import 'package:flutter/material.dart';
import 'tabs/Category.dart';
import 'tabs/Home.dart';
import 'tabs/Setting.dart';

class Tabs extends StatefulWidget {
  int index;

  Tabs({this.index = 0, Key? key}) : super(key: key);

  @override
  _TabsState createState() => _TabsState(index);
}

class _TabsState extends State<Tabs> {
  int _currentIndex = 0;

  _TabsState(this._currentIndex);

  final _pageList = [HomePage(), CategoryPage(), SettingPage()];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      floatingActionButton: Container(
        width: 80,
        height: 80,
        padding: EdgeInsets.all(8),
        decoration: BoxDecoration(
            color: Colors.white,
          borderRadius: BorderRadius.circular(40)
        ),
        child: FloatingActionButton(
          backgroundColor: _currentIndex == 1? Colors.red : Colors.yellow,
          onPressed: (){
            setState(() {
              _currentIndex = 1;
            });
          },
          child: Icon(Icons.add),
        ),
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.centerDocked,
      appBar: AppBar(
        title: Text("BottomNavigationBar Widget"),
      ),
      body: _pageList[_currentIndex],
      bottomNavigationBar: BottomNavigationBar(
          iconSize: 35,
          fixedColor: Colors.green,
          currentIndex: _currentIndex,
          onTap: (index) {
            setState(() {
              _currentIndex = index;
            });
          },
          type: BottomNavigationBarType.fixed,
          items: [
            BottomNavigationBarItem(icon: Icon(Icons.home), label: "首页"),
            BottomNavigationBarItem(icon: Icon(Icons.category), label: "分类"),
            BottomNavigationBarItem(icon: Icon(Icons.settings), label: "设置")
          ]),
      drawer: Drawer(
          child: Column(
        children: [
          Row(children: [
            Expanded(
              //     child: DrawerHeader(
              //   child: Text("我的抽屉头"),
              //   decoration: BoxDecoration(
              //       color: Colors.yellow,
              //       image: DecorationImage(
              //           image: NetworkImage(
              //               "https://img.lianzhixiu.com/uploads/210106/37-21010609363aS.jpg"),
              //           fit: BoxFit.cover)),
              // )

              child: UserAccountsDrawerHeader(
                  accountName: Text("二代"),
                  accountEmail: Text("1770385837@qq.com"),
                  currentAccountPicture: CircleAvatar(
                      backgroundImage: NetworkImage(
                          "https://img.lianzhixiu.com/uploads/210106/37-21010609363aS.jpg")
                  ),
                  decoration: BoxDecoration(
                      color: Colors.green,
                      // image: DecorationImage(
                      //     image: NetworkImage(
                      //         "https://img.lianzhixiu.com/uploads/210106/37-21010609363aS.jpg"),
                      //     fit: BoxFit.cover)
                  ),
                otherAccountsPictures: [
                  Image.network("https://img.lianzhixiu.com/uploads/210106/37-21010609363aS.jpg"),
                  Image.network("https://img.lianzhixiu.com/uploads/210106/37-21010609363aS.jpg"),
                  Image.network("https://img.lianzhixiu.com/uploads/210106/37-21010609363aS.jpg")
                ],
              ),
            )
          ]),
          ListTile(
              title: Text("我的空间"),
              leading: CircleAvatar(child: Icon(Icons.home))),
          Divider(),
          ListTile(
              title: Text("用户中心"),
              onTap: (){
                Navigator.of(context).pop();
                Navigator.pushNamed(context,"/userPage");
              },
              leading: CircleAvatar(child: Icon(Icons.people))),
          Divider(),
          ListTile(
              title: Text("设置中心"),
              leading: CircleAvatar(child: Icon(Icons.settings)))
        ],
      )),
      endDrawer: Drawer(child: Text("右侧抽屉")),
    );
  }
}
