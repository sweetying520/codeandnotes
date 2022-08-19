// ignore_for_file: prefer_function_declarations_over_variables


import 'package:flutter/material.dart';
import 'package:flutterlearning/main_demo04.dart';
import 'package:flutterlearning/pages/AppBar.dart';
import '../pages/CheckBoxPage.dart';
import '../pages/DateAndTime.dart';
import '../pages/DialogPage.dart';
import '../pages/FormPage.dart';
import '../pages/GeneralFormPage.dart';
import '../pages/HttpPage.dart';
import '../pages/HttpPracticePage.dart';
import '../pages/RadioAndSwitchPage.dart';
import '../pages/Search.dart';
import '../pages/Tabs.dart';

import '../pages/TextFieldPage.dart';
import '../pages/user/Login.dart';
import '../pages/user/RegisterFirst.dart';
import '../pages/user/RegisterSecond.dart';
import '../pages/user/RegisterThird.dart';
import '../pages/TabBarController.dart';
import '../pages/User.dart';
import '../pages/ExtendButtonPage.dart';
import 'package:flutterlearning/pages/ExtendButtonPageFor2.dart';

final routes = {
  '/': (context) => Tabs(),
  '/formPage': (context, {arguments}) => FormPage(arguments: arguments,),
  '/search': (context, {arguments}) => SearchPage(arguments: arguments),
  '/login': (context) => LoginPage(),
  '/registerFirst': (context) => RegisterFirstPage(),
  '/registerSecond': (context) => RegisterSecondPage(),
  '/registerThird': (context) => RegisterThirdPage(),
  '/appbarPage': (context) => AppBarPage(),
  '/tabBarController': (context) => TabBarControllerPage(),
  '/userPage': (context) => UserPage(),
  '/extendButtonPage': (context) => ExtendButtonPage(),
  '/extendButtonPageFor2': (context) => ExtendButtonPageFor2(),
  '/textFieldPage': (context) => TextFieldPage(),
  '/checkBoxPage': (context) => CheckBoxPage(),
  '/radioAndSwitchPage': (context) => RadioAndSwitchPage(),
  '/generalFormPage': (context) => GeneralFormPage(),
  '/dataAndTimePage': (context) => DataAndTimePage(),
  '/dialogPage': (context) => DialogPage(),
  '/httpPage': (context) => HttpPage(),
  '/httpPracticePage': (context) => HttpPracticePage(),

};

var onGenerateRoute = (settings) {
  Function? pageContentBuilder = routes[settings.name];
  if (pageContentBuilder != null) {
    if (settings.arguments != null) {
      var route = MaterialPageRoute(
          builder: (context) =>
              pageContentBuilder(context, arguments: settings.arguments));
      return route;
    } else {
      var route =
          MaterialPageRoute(builder: (context) => pageContentBuilder(context));
      return route;
    }
  }
};
