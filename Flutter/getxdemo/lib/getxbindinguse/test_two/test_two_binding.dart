import 'package:get/get.dart';

import 'test_two_logic.dart';

class TestTwoBinding extends Bindings {
  @override
  void dependencies() {
    Get.lazyPut(() => TestTwoLogic());
  }
}
