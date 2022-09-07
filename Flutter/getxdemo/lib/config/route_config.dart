import 'package:get/get.dart';
import 'package:getxdemo/get_counter_high/get_counter_high_view.dart';
import 'package:getxdemo/get_jump_one/get_jump_one_view.dart';
import 'package:getxdemo/get_jump_two/get_jump_two_view.dart';
import 'package:getxdemo/getxbindinguse/test_one/test_one_binding.dart';
import 'package:getxdemo/getxbindinguse/test_one/test_one_view.dart';
import 'package:getxdemo/getxbindinguse/test_two/test_two_binding.dart';
import 'package:getxdemo/getxbindinguse/test_two/test_two_view.dart';

class RouteConfig{

  static const String main = "/";

  static const String getJumOne = "/jumpOne";
  static const String getJumpTwo = "/jumpTwo";
  static String testOne = "/testOne";
  static String testTwo = "/testOne/testTwo";

  static final List<GetPage> getPages = [
    GetPage(name: main, page: () => GetCounterHighPage()),
    GetPage(name: getJumOne, page: () => GetJumpOnePage()),
    GetPage(name: getJumpTwo, page: () => GetJumpTwoPage()),
    GetPage(name: testOne, page: () => TestOnePage(),binding: TestOneBinding()),
    GetPage(name: testTwo, page: () => TestTwoPage(),binding: TestTwoBinding()),
  ];





}