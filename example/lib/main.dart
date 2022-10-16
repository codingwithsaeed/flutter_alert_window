import 'package:flutter/material.dart';
import 'package:flutter_alert_window/flutter_alert_window.dart';

FlutterAlertWindow alert = FlutterAlertWindow();

void main() => runApp(const MyApp());

void onClose() {
  print("onClose");
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(title: const Text('Flutter Alert Plugin')),
        body: Center(
          child: ElevatedButton(
            onPressed: () {
              alert.show(
                time: '22:19',
                title: 'title',
                description: 'description',
                onClose: onClose,
              );
            },
            child: const Text('Show Alert'),
          ),
        ),
      ),
    );
  }
}
