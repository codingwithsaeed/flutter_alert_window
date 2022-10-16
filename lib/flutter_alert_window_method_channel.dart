import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'flutter_alert_window_platform_interface.dart';

/// An implementation of [FlutterAlertWindowPlatform] that uses method channels.
class MethodChannelFlutterAlertWindow extends FlutterAlertWindowPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('flutter_alert_window');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }
}
