# A simple MVVM project - Get podcast and episode data from server and play video / audio with [Jetpack Media3](https://developer.android.com/media/media3)

## Project Structure:
It follows the basic project structure established by the official Android app  [sunflower](https://github.com/android/sunflower)

## Screen Flow:
![image](https://github.com/user-attachments/assets/04598a94-97ed-4435-9b9d-d9a4b4e13969)

## Media Player near Quick settings
![image](https://github.com/user-attachments/assets/daaeaebc-eb5a-4508-a91a-66bf7e57c427)


## Libraries 
| Library           | Used For                  | Remark |
|-------------------|---------------------------| ------ |
|[Hilt-Dagger](https://developer.android.com/training/dependency-injection/hilt-android)|DI| --- |
|[Retrofit](https://square.github.io/retrofit/) & [OkHttp](https://square.github.io/okhttp/) |Network| --- |
| [Jetpack Compose](https://developer.android.com/develop/ui/compose)           | UI           | ---- |
| [turbine](https://github.com/cashapp/turbine)           | For testing flow          | ----|
| [timber](https://github.com/JakeWharton/timber)            | Logging                   | ----|
| [moshi](https://github.com/square/moshi) & [Kotlin Serialization﻿](https://kotlinlang.org/docs/serialization.html)  | parse json                | ----|
|  [Junit 5](https://junit.org/junit5/) & [mockk](https://mockk.io/)              | unit test           | ----| 
|   [Coil](https://coil-kt.github.io/coil/)             | Image display           | ----| 
|   [media3](https://developer.android.com/media/media3)            | Media player           | ----| 

## project demo
https://github.com/user-attachments/assets/82300dde-fce0-4cb3-b64d-ffcae32fc9de


## UI test demo
https://github.com/user-attachments/assets/fcb6e508-67af-4ef5-9324-397767b93a18


## build app command
./gradlew assembleDebug

![image](https://github.com/user-attachments/assets/cc5d6db2-c947-48fe-90f4-f34d6efeda00)

## TODO
1. Implement [jacoco report](https://www.baeldung.com/jacoco) to ensure comprehensive test coverage for all cases
2. Support landscape
3. Multi-lang
4. Support switch theme
5. Implement [Automate UI tests](https://developer.android.com/training/testing/instrumented-tests/ui-tests) 
6. Implement [Firebase Crashlyics](https://firebase.google.com/docs/crashlytics)
7. test (unit test and ui test) - Media Player on Quick settings
8. ...
