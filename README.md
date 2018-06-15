# PostsMVP

This is a demo project. 

To run you just should clone the repo and import it to Android Studio and run it with an emulator or any Android device.

The architecture defined is MVP with Dagger, RxJava and Retrofit with Kotlin language.
All the modules that are transversal to the application will be on the application module, otherwise if they have scope between the activity/fragment lifecycle, it will be added to the proper module

RxJava and Retrofit will be used by an official adapter, so the calls will always be on the background and won't cause any problem. Also, the disposables (network calls and background threads) will be disposed if the view is destroyed.
This is to prevent memory leaks.  

 