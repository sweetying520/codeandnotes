# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# 对 MyFragment 类进行完全保留，不混淆其任何信息
-keep class com.dream.androidreversedemo.MyFragment{
    *;
}

# 对 MainActivity 类进行完全保留，不混淆其任何信息
-keep class com.dream.androidreversedemo.MainActivity{
    *;
}

# 对 Utils 中的方法进行保留，防止其被混淆或移除
-keep class com.dream.androidreversedemo.Utils{
    *;
}

# 对 NativeUtils 中的非 Native 方法进行保留，防止其被移除
-keepclassmembers class com.dream.androidreversedemo.NativeUtils{
    public static void methodNotNative();
}

# 对第三方库进行保留，防止其被混淆或移除
-keep class com.dream.androidutils.*{
    *;
}