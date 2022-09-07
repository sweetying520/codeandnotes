import 'package:get/get.dart';
import 'package:getxdemo/config/route_config.dart';

class GetJumpOneLogic extends GetxController {

  var count = 0;
  
  void toJumpTow(){
    Get.toNamed(RouteConfig.getJumpTwo,arguments: {'msg':"我是上个页面传递过来的数据"});
  }

  void increase(){
    ++count;
    update();
  }
}
