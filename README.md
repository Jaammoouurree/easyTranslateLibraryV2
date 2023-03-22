



# Easy Translate Library

## What is Easy Translate?
With google-trans and google_translator, this library will help you translate phrases using these libraries in Android. Python modules requires Chaquopy,
and in order to use them in android environment, you will need to import may plugins to support python working in android environment, with this, it is simplified

### Libraries integrated
1. Chaquopy 
1. Google Trans Py
1. Google Translator

### Gradle Setup

```gradle
repositories {
     mavenCentral()
     google()
     maven { url 'https://jitpack.io' }
     jcenter()
}

dependencies {
     implementation 'com.github.Jaammoouurree:easytranslatelibraryv2:1.0.4'
     implementation 'com.google.guava:listenablefuture:9999.0-empty-to-avoid-conflict-with-guava'
}
```


