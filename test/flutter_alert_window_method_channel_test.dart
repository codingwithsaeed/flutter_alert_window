import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:flutter_alert_window/flutter_alert_window_method_channel.dart';

void main() {
  MethodChannelFlutterAlertWindow platform = MethodChannelFlutterAlertWindow();
  const MethodChannel channel = MethodChannel('flutter_alert_window');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await platform.getPlatformVersion(), '42');
  });
}
