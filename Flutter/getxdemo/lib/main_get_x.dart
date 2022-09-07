import 'package:flutter/material.dart';
import 'package:get/route_manager.dart';
import 'package:getxdemo/config/route_config.dart';



void main(){
  runApp(const MyApp());
}

class MyApp extends StatelessWidget{
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return GetMaterialApp(
      initialRoute: RouteConfig.main,
      getPages: RouteConfig.getPages,
    );
  }
}