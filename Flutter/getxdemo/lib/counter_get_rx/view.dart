// ignore_for_file: use_key_in_widget_constructors

import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:getxdemo/counter_get_rx/logic.dart';

class CounterRxPage extends StatelessWidget {

  final CounterRxLogic logic = Get.put(CounterRxLogic());

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(title: const Text('计数器-简单式 Rx')),
        body: Center(
          child: Obx(() {
            return Text(
              '点击了 ${logic.count.value} 次',
              style: const TextStyle(
                  fontSize: 30.0
              ),
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

