import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:getxdemo/get_jump_one/get_jump_one_view.dart';

import 'get_counter_high_logic.dart';

class GetCounterHighPage extends StatelessWidget {
  final logic = Get.put(GetCounterHighLogic());
  final state = Get.find<GetCounterHighLogic>().state;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('计数器-进阶')),
      body: Center(
        child: GetBuilder<GetCounterHighLogic>(builder: (logic) {
          return InkWell(
            child: Text(
              '点击了 ${state.count} 次',
              style: const TextStyle(
                  fontSize: 30.0
              ),
            ),
            onTap: (){
              //Get.toNamed(RouteConfig.getJumOne);
              Get.to(GetJumpOnePage());

            },
          );
        }),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () => logic.increase(),
        child: const Icon(Icons.add),
      ),
    );
  }
}
