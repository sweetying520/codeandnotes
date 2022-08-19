// ignore_for_file: prefer_const_constructors, prefer_const_literals_to_create_immutables

import 'package:flutter/material.dart';

class AppBarPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return DefaultTabController(
        length: 2,
        child: Scaffold(
          appBar: AppBar(
            bottom: TabBar(tabs: [
                Tab(text: "热门"),
                Tab(text: "推荐"),
            ]),
            centerTitle: true,
            backgroundColor: Colors.green,
            // leading: IconButton(
            //   icon: Icon(Icons.menu),
            //   onPressed: (){
            //     print('menu');
            //   },
            // ),
            actions: [
              IconButton(
                icon: Icon(Icons.search),
                onPressed: () {
                  print('search');
                },
              ),
              IconButton(
                icon: Icon(Icons.delete),
                onPressed: () {
                  print('delete');
                },
              )
            ],
            title: Text("AppBarDemoPage"),
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
              )
            ],
          ),
        ));
  }
}
