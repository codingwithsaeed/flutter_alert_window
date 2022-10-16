import 'dart:io';
import 'dart:ui';

import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'flutter_alert_window_platform_interface.dart';

class MethodChannelFlutterAlertWindow extends FlutterAlertWindowPlatform {
  @visibleForTesting
  final methodChannel =
      const MethodChannel('flutter_alert_window', JSONMethodCodec());

  @visibleForTesting
  final eventChannel =
      const EventChannel('flutter_alert_window_event', JSONMethodCodec());

  Future<void> listenBroadcastStream() async {
    eventChannel.receiveBroadcastStream().listen(
      (event) {
        final handlerLong = event as int;
        final onCloseHandle = CallbackHandle.fromRawHandle(handlerLong);
        final onClose = PluginUtilities.getCallbackFromHandle(onCloseHandle);
        if (onClose != null) onClose();
      },
    );
  }

  @override
  Future<bool?> show({
    required String time,
    required String title,
    required String description,
    required void Function() onClose,
  }) async {
    if (Platform.isAndroid) {
      listenBroadcastStream();
      final onCloseHandle = PluginUtilities.getCallbackHandle(onClose);
      var args = {
        'time': time,
        'title': title,
        'description': description,
        'on_close': onCloseHandle?.toRawHandle()
      };
      return await methodChannel.invokeMethod('showAlert', args);
    }
    return Future.value(false);
  }
}
