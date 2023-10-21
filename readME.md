# Project BEV Documentation for ClientSide
Other Documentations will be released in the next update. For periodical update cycle cadence
(every Monday at 12:00nn GMT+8)

## What's inside the Documentation

1. ClientSide Documentation

## Documentation Proper

### ClientSide Documentation

#### ClientSide App `build.gradle.kts` App directory

```kotlin
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")

}
```
`plugins { ... }`: This block is used to apply plugins to the Gradle project. Plugins are extensions that provide various functionalities to your project. In this case, you are applying three different plugins to your Android application project.

`id("com.android.application")`: This line applies the "com.android.application" plugin. This is the Android Gradle Plugin, and it's a core plugin required for building Android applications. It enables tasks and configurations specific to Android projects.

`id("org.jetbrains.kotlin.android")`: This line applies the "org.jetbrains.kotlin.android" plugin. This is the Kotlin Android Extensions plugin, and it's used to enable Kotlin support for Android projects. It simplifies working with Android layouts and other resources in Kotlin code.

`id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")`: This line applies the "com.google.android.libraries.mapsplatform.secrets-gradle-plugin" plugin. This plugin is related to handling API keys and other secrets for using Google Maps and other Google services in your Android application.


```kotlin
android {
    namespace = "com.example.puvclient"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.puvclient"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }

}
```
The provided configuration is defining important settings for an Android application, including the API level, package name, build types, ProGuard settings, Java and Kotlin compatibility, and features like data binding and view binding. It also includes specific settings related to Jetpack Compose.

DEPENDENCIES

```kotlin
dependencies {
    implementation("com.google.android.gms:play-services-maps:18.1.0")
    implementation("androidx.compose.material3:material3:1.1.2")
    implementation("androidx.compose.material3:material3-window-size-class:1.1.2")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.compose.ui:ui-graphics-android:1.5.3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("com.google.android.gms:play-services-maps:18.1.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.2")
}
```

1. ```implementation("com.google.android.gms:play-services-maps:18.1.0")```:
   - This dependency is for Google Play Services Maps, which is used for integrating Google Maps functionality into your Android application. It allows you to display maps, add markers, and use various map-related features in your app.

2. `implementation("androidx.compose.material3:material3:1.1.2")`:
   - This is a dependency for Jetpack Compose Material3, which provides UI components and design elements for building modern Android user interfaces using Jetpack Compose, a declarative UI framework.

3. `implementation("androidx.compose.material3:material3-window-size-class:1.1.2")`:
   - This is another Jetpack Compose Material3 dependency but specifically for handling window size class information, which helps adapt your UI to different device screen sizes and orientations.

4. `implementation("androidx.core:core-ktx:1.12.0")`:
   - This is the Kotlin extension library for AndroidX Core, which simplifies working with Android core components in Kotlin. It includes various extension functions to make Android development more concise and idiomatic in Kotlin.

5. `implementation("androidx.appcompat:appcompat:1.6.1")`:
   - This is the AndroidX AppCompat library, which provides backward-compatible versions of UI components found in the latest Android platform, allowing your app to have a consistent look and feel across different Android versions.

6. `implementation("com.google.android.material:material:1.10.0")`:
   - This is the Material Design Components library by Google. It provides a collection of UI components and styles that follow the Material Design guidelines, helping you create visually appealing Android apps.

7. `implementation("androidx.constraintlayout:constraintlayout:2.1.4")`:
   - This is the AndroidX ConstraintLayout library, which offers a flexible and powerful way to create complex layouts in Android applications. It's particularly useful for responsive and adaptive UI design.

8. `implementation("androidx.compose.ui:ui-graphics-android:1.5.3")`:
   - This is a dependency for Jetpack Compose's UI Graphics library for Android. It provides support for graphics and drawing operations within Jetpack Compose-based UIs.

9. `testImplementation("junit:junit:4.13.2")`:
   - This is a JUnit dependency for unit testing in Android. JUnit is a popular testing framework for Java and Android, allowing you to write and execute unit tests for your app's code.

10. `androidTestImplementation("androidx.test.ext:junit:1.1.5")`:
    - This is an AndroidX Test dependency for JUnit. It's specifically used for Android instrumented (UI) testing and extends the capabilities of JUnit for Android testing.

11. `androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")`:
    - This is an Espresso dependency for UI testing in Android. Espresso is a framework for writing UI tests to automate interaction with your app's user interface.

12. `implementation("com.google.android.gms:play-services-maps:18.1.0")` (repeated):
    - This is another instance of the Google Play Services Maps dependency. It seems to be included twice in your Gradle file.

13. `implementation("com.squareup.okhttp3:okhttp:4.9.2")`:
    - This is the OkHttp library by Square, which is used for making HTTP requests in Android applications. It's a popular choice for performing network operations, including sending and receiving data over the internet.

#### Client Side `build.gradle.kt` root directory
```kotlin
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version "2.0.1" apply false

}
```
Has the Gradle Build for all directories under the client side app

####Client Side `mapsActivity.java` file
This code is a part of the Android application that tracks the user's location and sends location updates to a 
server when tracking is enabled. It also handles location permissions and UI interactions using data binding.

1. Class Declaration and Member Variables:

```java

public class MapsActivity extends AppCompatActivity {
    private LocationManager locationManager;
    private boolean isTracking = false;
    private String serverUrl = "https://your-server/api/update-location";
    private int locationPermissionCode = 123;
```
This code defines a class named `MapsActivity`, which extends the `AppCompatActivity` class.
It declares several member variables:

`locationManager`: An instance of the LocationManager for managing location-related services.
`isTracking`: A boolean variable to track whether the user is actively tracking their location.
`serverUrl`: A string variable that holds the URL where location updates will be sent.
`locationPermissionCode`: An integer code for location permission requests.

2. `onCreate Method:`

```java

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
This is the onCreate method, which is called when the activity is created.
```
It inflates a layout using data binding (ActivityMainBinding) and sets the content view for the activity.

3. `ToggleButton Setup:`

```java

    locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
    final ToggleButton toggleButton = binding.toggleButton;
```
It obtains the LocationManager service, allowing access to location-related services.
It retrieves a   view from the layout using data binding.

4. `ToggleButton OnCheckedChangeListener:`

```java

    toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
        isTracking = isChecked;
        if (isTracking) {
            startLocationUpdates();
        } else {
            stopLocationUpdates();
        }
    });
```
Sets an `OnCheckedChangeListener` for the `ToggleButton`. When the user toggles the button, this listener is triggered.
It updates the isTracking variable based on the button state.
If tracking is enabled, it calls `startLocationUpdates()`. If tracking is disabled, it calls `stopLocationUpdates().`

5. `LocationListener:`

```java

private LocationListener locationListener = new LocationListener() {
    @Override
    public void onLocationChanged(Location location) {
        if (isTracking) {
            sendLocationToServer(location.getLatitude(), location.getLongitude());
        }
    }
    // Other overridden methods
};
```
This code defines a LocationListener that listens for location updates.
The onLocationChanged method is called when a location update is received. If tracking is enabled, it sends the location to the server.

6. `startLocationUpdates Method:`

```java
private void startLocationUpdates() {
    if (checkPermission()) {
        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0f, locationListener);
        } catch (SecurityException e) {
            Log.e("MainActivity", "SecurityException: " + e.getMessage());
        }
    } else {
        requestPermission();
    }
}
```
The startLocationUpdates method initiates the process of receiving location updates.
It first checks if the app has the necessary location permission.
If permission is granted, it requests location updates from the GPS provider. If not, it requests permission.

7. `stopLocationUpdates Method:`

```java

private void stopLocationUpdates() {
    locationManager.removeUpdates(locationListener);
}
```
The stopLocationUpdates method stops receiving location updates by removing the listener.

8. `Permission Handling:`

```java

private boolean checkPermission() { /* ... */ }
private void requestPermission() { /* ... */ }
```
These methods are used to check for and request location permission. If permission is not granted, the app will request it.

9. `onRequestPermissionsResult Method:`

```java

@Override
public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) { /* ... */ }
```
This method is called when the user responds to a permission request.
It checks if the requested location permission was granted, and if so, it initiates location updates.

10. `sendLocationToServer Method:`

```java

private void sendLocationToServer(double latitude, double longitude) { /* ... */ }
```
This method is responsible for sending location data (latitude and longitude) to the server using an `HTTP POST` request. It uses the `OkHttp library` for making the network request.

#### `AndroidManifest.xml` layout file
The AndroidManifest.xml code defines various aspects of your Android application, including permissions, the main activity, icons, labels, and metadata such as API keys. It's a crucial file for configuring the app and specifying its behavior and requirements.

```xml

<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <application
        android:allowBackup="true"
        android:dataExtractionRules="@xml/data_extraction_rules"
        android:fullBackupContent="@xml/backup_rules"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.MyMap"
        tools:targetApi="31">
        <activity
            android:name=".MapsActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>


        <meta-data
            android:name="com.google.android.geo.API_KEY"
            android:value="@string/my_map_api_key" />
    </application>

</manifest>

```

1. `<?xml version="1.0" encoding="utf-8"?>`:
   - This line indicates that the document follows the XML format and specifies its encoding as UTF-8.

2. `<manifest>`:
   - This is the root element of the AndroidManifest.xml file, and it contains various metadata and configuration for your Android application.

3. `xmlns:android="http://schemas.android.com/apk/res/android"`:
   - This line defines the XML namespace for the "android" prefix, which is used to reference Android-specific attributes and elements.

4. `xmlns:tools="http://schemas.android.com/tools"`:
   - This line defines the XML namespace for the "tools" prefix, which is used to include attributes provided by the Android Gradle plugin for development and build tools.

5. `<uses-permission>` (ACCESS_FINE_LOCATION and ACCESS_COARSE_LOCATION):
   - These elements specify the permissions required by the app. The app requests permission to access fine-grained and coarse-grained location information. These permissions are necessary for location-based services.

6. `<application>`:
   - This element contains configuration and metadata related to the Android application.

7. `android:allowBackup="true"`:
   - This attribute indicates that the app supports backup and restore functionality.

8. `android:dataExtractionRules="@xml/data_extraction_rules"`:
   - This attribute refers to a set of rules defined in an XML file named "data_extraction_rules." These rules may specify how data is extracted or handled within the app.

9. `android:fullBackupContent="@xml/backup_rules"`:
   - This attribute points to a content descriptor file named "backup_rules." It defines what data should be included in full backups of the app.

10. `android:icon="@mipmap/ic_launcher"` and `android:roundIcon="@mipmap/ic_launcher_round"`:
    - These attributes set the icons for the app. "ic_launcher" is the main icon, and "ic_launcher_round" is a round version of the icon.

11. `android:label="@string/app_name"`:
    - This attribute specifies the label (display name) of the app, which is defined as a string resource referenced by "app_name."

12. `android:supportsRtl="true"`:
    - This attribute indicates that the app supports right-to-left (RTL) layout direction, which is essential for languages that read from right to left.

13. `android:theme="@style/Theme.MyMap"`:
    - This attribute sets the theme/style for the app, indicating how the user interface will be styled.

14. `tools:targetApi="31"`:
    - This attribute, provided by the "tools" namespace, specifies the target API level for tools and analysis. In this case, it targets API level 31.

15. `<activity>` (MapsActivity):
    - This element defines an activity component named "MapsActivity" for your app.
    - `android:name=".MapsActivity"` specifies the class name for the activity.
    - `android:exported="true"` allows other applications to launch this activity.

16. `<intent-filter>`:
    - This element defines an intent filter, which specifies how the activity should respond to incoming intents (requests).

17. `<action>` (android.intent.action.MAIN):
    - This element declares that the activity can be the main entry point of the app when launched.

18. `<category>` (android.intent.category.LAUNCHER):
    - This element specifies that the activity should appear in the device's launcher as an app icon, making it the entry point for the app.

19. `<meta-data>` (com.google.android.geo.API_KEY):
    - This element is used to include metadata in the manifest.
    - `android:name="com.google.android.geo.API_KEY"` specifies the name of the metadata.
    - `android:value="@string/my_map_api_key"` references a string resource named "my_map_api_key" that likely contains an API key for Google Maps.

