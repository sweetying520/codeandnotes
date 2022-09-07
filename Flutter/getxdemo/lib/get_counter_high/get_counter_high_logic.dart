import 'package:get/get.dart';

import 'get_counter_high_state.dart';

class GetCounterHighLogic extends GetxController {
  final GetCounterHighState state = GetCounterHighState();

  void increase(){
    ++state.count;
    update();
  }
}
