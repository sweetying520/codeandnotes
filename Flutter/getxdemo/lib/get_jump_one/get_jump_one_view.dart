// ignore_for_file: use_key_in_widget_constructors

import 'package:flutter/material.dart';
import 'package:get/get.dart';

import 'get_jump_one_logic.dart';

class GetJumpOnePage extends StatelessWidget {

  final logic = Get.put(GetJumpOneLogic());

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      appBar: AppBar(title: const Text('跨页面-One')),
      floatingActionButton: FloatingActionButton(
        onPressed: () => logic.toJumpTow(),
        child: const Icon(Icons.arrow_forward_outlined),
      ),
      body: Center(
        child: GetBuilder<GetJumpOneLogic>(builder: (logic) {
          return Text('跨页面-Two点击了 ${logic.count} 次',
              style: const TextStyle(fontSize: 30.0));
        }),
      ),
    );
  }
}
