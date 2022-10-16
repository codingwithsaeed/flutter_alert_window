import 'package:flutter_test/flutter_test.dart';
import 'package:flutter_alert_window/flutter_alert_window.dart';
import 'package:flutter_alert_window/flutter_alert_window_platform_interface.dart';
import 'package:flutter_alert_window/flutter_alert_window_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockFlutterAlertWindowPlatform 
    with MockPlatformInterfaceMixin
    implements FlutterAlertWindowPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final FlutterAlertWindowPlatform initialPlatform = FlutterAlertWindowPlatform.instance;

  test('$MethodChannelFlutterAlertWindow is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelFlutterAlertWindow>());
  });

  test('getPlatformVersion', () async {
    FlutterAlertWindow flutterAlertWindowPlugin = FlutterAlertWindow();
    MockFlutterAlertWindowPlatform fakePlatform = MockFlutterAlertWindowPlatform();
    FlutterAlertWindowPlatform.instance = fakePlatform;
  
    expect(await flutterAlertWindowPlugin.getPlatformVersion(), '42');
  });
}
