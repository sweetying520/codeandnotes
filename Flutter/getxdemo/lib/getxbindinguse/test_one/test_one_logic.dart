import 'package:get/get.dart';
import 'package:getxdemo/config/route_config.dart';

class TestOneLogic extends GetxController {
  void jump() => Get.toNamed(RouteConfig.testTwo);

}
