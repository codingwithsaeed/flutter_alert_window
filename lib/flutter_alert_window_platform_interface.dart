import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'flutter_alert_window_method_channel.dart';

abstract class FlutterAlertWindowPlatform extends PlatformInterface {
  /// Constructs a FlutterAlertWindowPlatform.
  FlutterAlertWindowPlatform() : super(token: _token);

  static final Object _token = Object();

  static FlutterAlertWindowPlatform _instance = MethodChannelFlutterAlertWindow();

  /// The default instance of [FlutterAlertWindowPlatform] to use.
  ///
  /// Defaults to [MethodChannelFlutterAlertWindow].
  static FlutterAlertWindowPlatform get instance => _instance;
  
  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [FlutterAlertWindowPlatform] when
  /// they register themselves.
  static set instance(FlutterAlertWindowPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
