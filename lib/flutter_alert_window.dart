import 'flutter_alert_window_platform_interface.dart';

class FlutterAlertWindow {
  Future<bool?> show({
    required String time,
    required String title,
    required String description,
    required void Function() onClose,
  }) async {
    return await FlutterAlertWindowPlatform.instance.show(
      time: time,
      title: title,
      description: description,
      onClose: onClose,
    );
  }
}
