import 'package:get/get.dart';

class CounterGetLogic extends GetxController {

  var count = 0;

  void increase(){
    ++count;
    update();
  }
}
