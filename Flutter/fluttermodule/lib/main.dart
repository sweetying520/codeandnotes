import 'dart:async';
import 'dart:convert';
import 'dart:ui';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main(){
  //获取 Android 传过来的路由
  String url = "main?{\"name\":\"erdai\",\"age\":18}";/*window.defaultRouteName*/;
  //解析并获取路由名称
  String routeName = url.substring(0,url.indexOf("?"));
  //解析并将参数转换成一个 Map 对象
  String paramsString = url.substring(url.indexOf("?") + 1);
  Map<String,dynamic> paramsMap = json.decode(paramsString);
  //打印参数
  print(paramsMap);
  runApp(getRouter(routeName));
}

void newMain() => runApp(MaterialApp(
  home: Scaffold(
    appBar: AppBar(
      title: Text("FlutterFragment newMain"),
    ),
    body: Center(
      child: Container(
        width: 200,
        height: 200,
        color: Colors.orange,
      ),
    ),
  ),
));

Widget getRouter(String routeName){
  //name = "main?{\"name\":\"000\"}";
  // String url = name;
  // String routeName = !url.contains("?") ? url : url.substring(0,url.indexOf('?'));
  // String routeParams =  !url.contains("?") ? url : url.substring(url.indexOf("?") + 1);
  // Map<String,dynamic> params = json.decode(routeParams);
  // print(routeName);
  // params.forEach((key, value) {
  //   print('$key  $value');
  // });
  switch(routeName){
    case "main":
      return const MyApp();
    default:
      return MaterialApp(
        home: Scaffold(
          appBar: AppBar(
            title: const Text("Flutter Demo Home Page"),
          ),
          body: const Center(
            child: Text(
              "page not found",
              style: TextStyle(
                fontSize: 24,
                color: Colors.red
              ),
            ),
          ),
        ),
      );
  }
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        // This is the theme of your application.
        //
        // Try running your application with "flutter run". You'll see the
        // application has a blue toolbar. Then, without quitting the app, try
        // changing the primarySwatch below to Colors.green and then invoke
        // "hot reload" (press "r" in the console where you ran "flutter run",
        // or press Run > Flutter Hot Reload in a Flutter IDE). Notice that the
        // counter didn't reset back to zero; the application is not restarted.
        primarySwatch: Colors.blue,
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key, required this.title}) : super(key: key);

  // This widget is the home page of your application. It is stateful, meaning
  // that it has a State object (defined below) that contains fields that affect
  // how it looks.

  // This class is the configuration for the state. It holds the values (in this
  // case the title) provided by the parent (in this case the App widget) and
  // used by the build method of the State. Fields in a Widget subclass are
  // always marked "final".

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _counter = 0;
  dynamic electricity;
  final _channel = const MethodChannel("com.dream.interactive");
  dynamic _content;

  final _eventChannel = const EventChannel("com.dream.eventchannel");
  StreamSubscription? _streamSubscription;

  final _messageChannel = const BasicMessageChannel("com.dream.messagechannel", StringCodec());



  @override
  void initState() {
    super.initState();
    _messageChannel.setMessageHandler((message) =>Future<String>((){
      print(message);
      setState(() {
        _content = message;
      });
      return "好啊";
    }));


    _streamSubscription = _eventChannel
        .receiveBroadcastStream(["Hello，建立连接吧"])
        .listen(_onData,onError: _onError,onDone: _onDone);


    _channel.setMethodCallHandler((call) async {
      String method = call.method;
      switch(method){
        case "timer":
          setState(() {
            _counter = call.arguments["count"];
          });
          if(_counter == 5){
            _channel.invokeMethod("sendFinish");
            break;
          }
          break;
        default:
          break;
      }
    });
  }

  void _onData(event){
    print(event);
    setState(() {
      electricity = event;
    });
  }

  void _onError(error){
    print(error);
  }

  void _onDone(){
    print('_onDone');
  }


  @override
  void dispose() {
    if(_streamSubscription != null){
      _streamSubscription?.cancel();
      _streamSubscription = null;

    }
  }

  void _incrementCounter() async{
    // setState(() {
    //   // This call to setState tells the Flutter framework that something has
    //   // changed in this State, which causes it to rerun the build method below
    //   // so that the display can reflect the updated values. If we changed
    //   // _counter without calling setState(), then the build method would not be
    //   // called again, and so nothing would appear to happen.
    //   _counter++;
    //   _channel.invokeMethod("_incrementCounter",_counter);
    // });
    var result = await _messageChannel.send("去爬哪座山?");
    print("$result");
    setState(() {
      _content = result;
    });
  }

  @override
  Widget build(BuildContext context) {
    // This method is rerun every time setState is called, for instance as done
    // by the _incrementCounter method above.
    //
    // The Flutter framework has been optimized to make rerunning build methods
    // fast, so that you can just rebuild anything that needs updating rather
    // than having to individually change instances of widgets.
    return Scaffold(
      appBar: AppBar(
        // Here we take the value from the MyHomePage object that was created by
        // the App.build method, and use it to set our appbar title.
        title: Text(widget.title),
      ),
      body: Center(
        // Center is a layout widget. It takes a single child and positions it
        // in the middle of the parent.
        child: Column(
          // Column is also a layout widget. It takes a list of children and
          // arranges them vertically. By default, it sizes itself to fit its
          // children horizontally, and tries to be as tall as its parent.
          //
          // Invoke "debug painting" (press "p" in the console, choose the
          // "Toggle Debug Paint" action from the Flutter Inspector in Android
          // Studio, or the "Toggle Debug Paint" command in Visual Studio Code)
          // to see the wireframe for each widget.
          //
          // Column has various properties to control how it sizes itself and
          // how it positions its children. Here we use mainAxisAlignment to
          // center the children vertically; the main axis here is the vertical
          // axis because Columns are vertical (the cross axis would be
          // horizontal).
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            const Text(
              'You have pushed the button this many times:',
            ),
            Text(
              '$_content',
              style: Theme.of(context).textTheme.headline4,
            ),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _incrementCounter,
        tooltip: 'Increment',
        child: const Icon(Icons.add),
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}
