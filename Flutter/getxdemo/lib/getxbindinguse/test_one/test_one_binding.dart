import 'package:get/get.dart';

import 'test_one_logic.dart';

class TestOneBinding extends Bindings {
  @override
  void dependencies() {
    Get.lazyPut(() => TestOneLogic());
  }
}
