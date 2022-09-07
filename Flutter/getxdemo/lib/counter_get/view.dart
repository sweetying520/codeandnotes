// ignore_for_file: use_key_in_widget_constructors

import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:getxdemo/config/route_config.dart';
import 'package:getxdemo/counter_get/logic.dart';

class CounterEasyPage extends StatelessWidget {

  final CounterGetLogic logic = Get.put(CounterGetLogic());

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(title: const Text('计数器-简单式')),
        body: Center(
          child: GetBuilder<CounterGetLogic>(builder: (logic) {
            return InkWell(
              child: Text(
                '点击了 ${logic.count} 次',
                style: const TextStyle(
                    fontSize: 30.0
                ),
              ),
              onTap: (){
                Get.toNamed(RouteConfig.getJumOne);
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

