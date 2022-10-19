import 'package:flutter_alert_window/configs/alert_type.dart';

import 'flutter_alert_window_platform_interface.dart';

class FlutterAlertWindow {
  Future<bool?> show({
    required String time,
    required String title,
    required String description,
    required void Function() onClose,
    AlertType type = AlertType.alert,
  }) async {
    return await FlutterAlertWindowPlatform.instance.show(
      time: time,
      title: title,
      description: description,
      type: type,
      onClose: onClose,
    );
  }
}
