# Stay On Target 🎯

A mindfulness and productivity Android app that helps you become more conscious of your phone usage by asking "Why are you unlocking your phone?" every time you unlock your device.

## 🎯 What it does

Stay On Target interrupts the automatic, mindless habit of unlocking your phone by:
- Detecting every phone unlock event
- Immediately presenting a dialog asking for your intention
- Converting your reason into a trackable task
- Creating persistent notifications until you mark tasks complete

## ✨ Features

- **Unlock Detection**: Monitors phone unlock events using system broadcasts
- **Reason Prompt**: Forces conscious intention before phone use
- **Task Tracking**: Converts unlock reasons into persistent notification tasks
- **Background Service**: Continues monitoring even when app is closed
- **Auto-restart**: Automatically restarts after device boot
- **Persistent Notifications**: Tasks remain visible until marked complete

## 🔧 Technical Details

- **Language**: Kotlin
- **Platform**: Android 5.0+ (API 21)
- **Target SDK**: 34 (Android 14)
- **Architecture**: Service-based with broadcast receivers

### Key Components
- `UnlockDetectionService`: Foreground service monitoring unlock events
- `UnlockReceiver`: Broadcast receiver for `ACTION_USER_PRESENT` events
- `ReasonInputActivity`: Dialog for capturing unlock intentions
- `NotificationHelper`: Task notification management
- `BootReceiver`: Auto-restart service after device boot

## 🚀 Installation

### Prerequisites
- Android Studio with Kotlin support
- Android SDK 34 or higher
- Gradle 8.13+

### Build & Install
```bash
# Clone the repository
git clone https://github.com/yourusername/StayOnTargetApp.git
cd StayOnTargetApp

# Build debug version
./gradlew assembleDebug

# Install to connected device
./gradlew installDebug
```

## 📱 Setup & Usage

1. **Install the app** and grant required permissions
2. **Start the service** from the main screen
3. **Lock and unlock your phone** - you'll be prompted for your reason
4. **Enter your intention** (e.g., "Check messages", "Set alarm")
5. **Complete tasks** by tapping "Mark Complete" in the notification
6. **Stay mindful** of your phone usage patterns

## 🔒 Permissions Required

The app requires several permissions to function:

- `RECEIVE_BOOT_COMPLETED`: Auto-start after device restart
- `WAKE_LOCK`: Keep monitoring service active
- `FOREGROUND_SERVICE`: Background unlock detection
- `FOREGROUND_SERVICE_SPECIAL_USE`: Special use foreground service
- `POST_NOTIFICATIONS`: Create task notifications
- `SYSTEM_ALERT_WINDOW`: Display overlay dialogs
- `ACCESS_NOTIFICATION_POLICY`: Manage notification access

## 📂 Project Structure

```
StayOnTargetApp/
├── app/
│   ├── src/main/
│   │   ├── java/com/stayontarget/
│   │   │   ├── MainActivity.kt              # Main app interface
│   │   │   ├── ReasonInputActivity.kt       # Unlock reason dialog
│   │   │   ├── UnlockDetectionService.kt    # Background monitoring
│   │   │   ├── UnlockReceiver.kt           # System event handler
│   │   │   ├── NotificationHelper.kt        # Task notifications
│   │   │   ├── TaskCompleteReceiver.kt      # Task completion handler
│   │   │   └── BootReceiver.kt             # Auto-start handler
│   │   ├── res/layout/                      # UI layouts
│   │   └── AndroidManifest.xml              # App configuration
│   └── build.gradle                         # App dependencies
├── build.gradle                             # Project configuration
└── README.md                                # This file
```

## 🔄 How It Works

1. **Service Starts**: `UnlockDetectionService` runs as a foreground service
2. **Event Detection**: `UnlockReceiver` listens for `ACTION_USER_PRESENT` broadcasts
3. **Immediate Response**: When unlock detected, `ReasonInputActivity` launches instantly
4. **Reason Capture**: User must enter why they're unlocking their phone
5. **Task Creation**: Reason becomes a persistent notification task
6. **Task Management**: User can mark tasks complete via notification actions
7. **Persistence**: Service survives app backgrounding and device restarts

## 🛠️ Development

### Building from Source
```bash
# Debug build
./gradlew assembleDebug

# Release build
./gradlew assembleRelease

# Run tests
./gradlew test
```

### Key Dependencies
- `androidx.core:core-ktx:1.12.0`
- `androidx.appcompat:appcompat:1.6.1`
- `com.google.android.material:material:1.11.0`
- `androidx.work:work-runtime-ktx:2.9.0`

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the Creative Commons Attribution-NonCommercial 4.0 International License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- Built with Android's system-level APIs for unlock detection
- Inspired by digital wellbeing and mindful technology use
- Uses foreground services for reliable background monitoring

## 📞 Support

If you encounter any issues or have questions:
- Open an issue on GitHub
- Check the [Issues](https://github.com/yourusername/StayOnTargetApp/issues) page for known problems

---

**Stay conscious, stay on target!** 🎯