import 'package:flutter/material.dart';
import 'package:get/get.dart';

import 'test_two_logic.dart';

class TestTwoPage extends StatelessWidget {
  final logic = Get.find<TestTwoLogic>();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('页面二')),
      body: const Center(child: Text('页面二', style: TextStyle(fontSize: 30.0))),
    );
  }
}
