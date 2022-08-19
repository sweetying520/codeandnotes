// ignore_for_file: prefer_const_constructors_in_immutables, prefer_const_constructors, prefer_const_literals_to_create_immutables


import 'package:flutter/material.dart';
import '../FormPage.dart';

class CategoryPage extends StatefulWidget {
  CategoryPage({Key? key}) : super(key: key);

  @override
  _CategoryPageState createState() => _CategoryPageState();
}

class _CategoryPageState extends State<CategoryPage> {
  @override
  Widget build(BuildContext context) {
    return DefaultTabController(
        length: 4,
        child: Scaffold(
          appBar: AppBar(
            backgroundColor: Colors.green,
            title: Row(
              children: [
                Expanded(
                    child: TabBar(tabs: [
                      Tab(text: "热销"),
                      Tab(text: "推荐"),
                      Tab(text: "热销"),
                      Tab(text: "推荐")
                    ],
                      indicatorColor: Colors.black,
                      labelColor: Colors.black,
                      unselectedLabelColor: Colors.white,
                      indicatorSize: TabBarIndicatorSize.label,
                    )
                )
              ],
            ),
          ),
          body: TabBarView(
            children: [
              ListView(
                children: [
                  ListTile(
                    title: Text("第一个 Tab"),
                  ),
                  ListTile(
                    title: Text("第一个 Tab"),
                  ),
                  ListTile(
                    title: Text("第一个 Tab"),
                  ),
                  ListTile(
                    title: Text("第一个 Tab"),
                  )
                ],
              ),
              ListView(
                children: [
                  ListTile(
                    title: Text("第二个 Tab"),
                  ),
                  ListTile(
                    title: Text("第二个 Tab"),
                  ),
                  ListTile(
                    title: Text("第二个 Tab"),
                  ),
                  ListTile(
                    title: Text("第二个 Tab"),
                  )
                ],
              ),
              ListView(
                children: [
                  ListTile(
                    title: Text("第三个 Tab"),
                  ),
                  ListTile(
                    title: Text("第三个 Tab"),
                  ),
                  ListTile(
                    title: Text("第三个 Tab"),
                  ),
                  ListTile(
                    title: Text("第三个 Tab"),
                  )
                ],
              ),
              ListView(
                children: [
                  ListTile(
                    title: Text("第四个 Tab"),
                  ),
                  ListTile(
                    title: Text("第四个 Tab"),
                  ),
                  ListTile(
                    title: Text("第四个 Tab"),
                  ),
                  ListTile(
                    title: Text("第四个 Tab"),
                  )
                ],
              )
            ],
          ),
        ));
  }
}

// Center(
// child: ElevatedButton(onPressed: () {
// // Navigator.of(context).push(
// //   MaterialPageRoute(builder: (context) => FormPage(title: "erdai"))
// // );
//
// //跳转命名路由
// Navigator.pushNamed(context, "/formPage",arguments: "erdai");
// }, child: Text("跳转到表单页面并传值")));
