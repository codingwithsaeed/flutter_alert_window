import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'flutter_alert_window_method_channel.dart';

abstract class FlutterAlertWindowPlatform extends PlatformInterface {
  FlutterAlertWindowPlatform() : super(token: _token);

  static final Object _token = Object();

  static FlutterAlertWindowPlatform _instance =
      MethodChannelFlutterAlertWindow();

  static FlutterAlertWindowPlatform get instance => _instance;

  static set instance(FlutterAlertWindowPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<bool?> show({
    required String time,
    required String title,
    required String description,
    required void Function() onClose,
  });
}
