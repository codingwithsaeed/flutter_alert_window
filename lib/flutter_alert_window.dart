
import 'flutter_alert_window_platform_interface.dart';

class FlutterAlertWindow {
  Future<String?> getPlatformVersion() {
    return FlutterAlertWindowPlatform.instance.getPlatformVersion();
  }
}
