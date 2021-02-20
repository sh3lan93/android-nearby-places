/**
 * Created by Mohamed Shalan on 2/19/21
 */

object Dependencies {

    val gradle_classpath = "com.android.tools.build:gradle:${Versions.gradle_version}"

    val kotlin_gradle_plugin_classpath =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin_version}"

    val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin_version}"

    val app_compact = "androidx.appcompat:appcompat:${Versions.app_compact}"

    val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx}"

    val junit = "junit:junit:${Versions.junit}"

    val androidx_test_junit = "androidx.test.ext:junit:${Versions.androidx_test_junit}"

    val androidx_espresso = "androidx.test.espresso:espresso-core:${Versions.androidx_espresso}"

    val constraint_layout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout}"

    val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"

    val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"

    val lifecycle_annotations = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"

    val koin_android = "org.koin:koin-android:${Versions.koin}"

    val koin_viewmodel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"

    val rxandroid = "io.reactivex.rxjava3:rxandroid:${Versions.rxandroid}"

    val rxjava = "io.reactivex.rxjava3:rxjava:${Versions.rxjava3}"

    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"

    val logging_interceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.logging_interceptor}"

    val moshi_kotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi_kotlin}"

    val moshi_kotlin_code_generation =
        "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi_kotlin}"

    val moshie = "com.squareup.moshi:moshi:${Versions.moshi_kotlin}"

    val moshi_converter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"

    val rxjava3_adapter = "com.github.akarnokd:rxjava3-retrofit-adapter:${Versions.rxjava3}"

    val navigation_component =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation_component}"

    val navigation_component_ui =
        "androidx.navigation:navigation-ui-ktx:${Versions.navigation_component}"

    val safe_args_classpath =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation_component}"

    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"

    val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    val chuckerDebug = "com.github.chuckerteam.chucker:library:${Versions.chuckerVersion}"

    val chuckerRelease = "com.github.chuckerteam.chucker:library-no-op:${Versions.chuckerVersion}"

    val hyperion = "com.willowtreeapps.hyperion:hyperion-core:${Versions.hyperion}"

    val hyperionSharedPreferences =
        "com.willowtreeapps.hyperion:hyperion-shared-preferences:${Versions.hyperion}"

    val hyperionRelease = "com.willowtreeapps.hyperion:hyperion-core-no-op:${Versions.hyperion}"

    val hyperionCrash = "com.willowtreeapps.hyperion:hyperion-crash:${Versions.hyperion}"

    val rxKotlin = "io.reactivex.rxjava3:rxkotlin:${Versions.rxKotlin}"

    val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"

    val googleMaterial = "com.google.android.material:material:${Versions.googleMaterial}"

    val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"

    val googleServiceClassPath = "com.google.gms:google-services:${Versions.googleServiceClassPath}"

    val multidex = "androidx.multidex:multidex:${Versions.multidex}"

    val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"

    val activityx = "androidx.activity:activity-ktx:${Versions.activityx}"

    val fragmentx = "androidx.fragment:fragment-ktx:${Versions.fragmentx}"

    val playServiceLocation = "com.google.android.gms:play-services-location:${Versions.playServiceLocation}"
}