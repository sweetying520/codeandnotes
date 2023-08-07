import 'package:pigeon/pigeon.dart';


class Everything{
  bool? aBool;
  int? aInt;
  double? aDouble;
  String? aString;
  List? aList;
  Map? aMap;
  List<List<bool?>?>? nestedList;
  Map<String?,String?>? mapWithAnnotations;
}



///Flutter 调用原生的方法
@HostApi()
abstract class HostEverything{
  Everything giveMeEverything();
  Everything echo(Everything everything);
}

///原生调用 Flutter 的方法
@FlutterApi()
abstract class FlutterEverything{
  void giveMeEverythingFlutter(Everything everything);
  //Everything echoFlutter(Everything everything);
}