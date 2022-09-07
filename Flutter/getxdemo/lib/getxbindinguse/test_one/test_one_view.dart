import 'package:flutter/material.dart';
import 'package:get/get.dart';

import 'test_one_logic.dart';

class TestOnePage extends StatelessWidget {

  final logic = Get.find<TestOneLogic>();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('页面一')),
      body: const Center(child: Text('页面一', style: TextStyle(fontSize: 30.0))),
      floatingActionButton: FloatingActionButton(
        onPressed: () => logic.jump(),
        child: const Icon(Icons.arrow_forward),
      ),
    );
  }
}
