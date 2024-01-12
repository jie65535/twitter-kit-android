# jie65535 changes

- 移除了 twitter-core 以外的其它代码
- 修改了 gradle 版本
- 修改了 twitter-core 的版本为 3.4.0

## 使用说明

1. 使用 `./gradlew assembleRelease` 编译发布版（或者直接从 [Releases](https://github.com/jie65535/twitter-kit-android/releases) 中下载）
2. 在构建目录下找到 `twitter-core-release.arr` 添加到项目的 `lib_arr` 目录
3. 在 `build.greadle` 里添加以下依赖：
```grooxy
    // Twitter core API
    // 手工修复SSL证书问题 https://devcommunity.x.com/t/oauth-request-token-failure-in-android-ios-app-with-ssl-certificate-pinning-error/209247/4
    implementation files('libs_aar/twitter-core-3.4.0.aar') // 本仓库构建的aar
    // twitter-core的依赖  不添加以下依赖会导致 btn_twitter_login.setCallback(new Callback<TwitterSession>() 报错找不到 Callback 的实现类
    implementation('com.squareup.retrofit2:retrofit:2.9.0')
    implementation('com.squareup.retrofit2:converter-gson:2.9.0')
//    // 官方SDK存在问题，见上方连接
//    implementation('com.twitter.sdk.android:twitter-core:3.3.0@aar') {
//        transitive = true;
//    }
```
4. 后续和官方SDK用法相同，如果你用到了不止core的部分，可以从上游仓库构建，起码我只需要core部分。

# dyguests's changes

原代码可以在 Android<=29 上使用。>=30需要做以下处理

修改点：

- 注释掉ssl相关代码 `CertificatePinner` in `OkHttpClientHelper`
- 启用 WebView 的 JavaScript 在 `OAuthActivity`

---

**Twitter will be discontinuing support for Twitter Kit on October 31, 2018. [Read the blog post here](https://blog.twitter.com/developer/en_us/topics/tools/2018/discontinuing-support-for-twitter-kit-sdk.html).**

# Twitter Kit for Android

Twitter Kit is a multi-module gradle project containing several Twitter SDKs including TweetComposer, TwitterCore, and TweetUi. Twitter Kit is designed to make interacting with Twitter seamless and efficient.

## Twitter Kit Features

* Display Tweets and timelines
* Compose Tweets
* Monetize with MoPub integration
* Log in with Twitter
* Access the Twitter API

## Getting Started

* Generate your Twitter API keys through the [Twitter developer apps dashboard](https://apps.twitter.com/).
* Install Twitter Kit using instructions below.
* For extensive documentation, please see the [wiki](https://github.com/twitter/twitter-kit-android/wiki).

### Install using Bintray JCenter

Add twitter dependency to your build.gradle:
```groovy

repositories {
  jcenter()
}

dependencies {
  compile('com.twitter.sdk.android:twitter:3.3.0@aar') {
    transitive = true
  }
}

```

### Building from source

Rename samples/app/twitter.properties.sample to samples/app/twitter.properties and populate the consumer key and secret.

To build the entire project run

```
./gradlew assemble
```

Run all automated tests on device to verify.

```
./gradlew test connectedCheck
```

To run the sample app

```
./gradlew :samples:app:installDebug
```


## Contributing

The master branch of this repository contains the latest stable release of Twitter Kit. See [CONTRIBUTING.md](https://github.com/twitter/twitter-kit-android/blob/master/CONTRIBUTING.md) for more details about how to contribute.

## Code of Conduct

This, and all github.com/twitter projects, are under the [Twitter Open Source Code of Conduct](https://github.com/twitter/code-of-conduct/blob/master/code-of-conduct.md). Additionally, see the [Typelevel Code of Conduct](http://typelevel.org/conduct) for specific examples of harassing behavior that are not tolerated.

## Contact

For usage questions post on [Twitter Community](https://twittercommunity.com/tags/c/publisher/twitter/android).

Please report any bugs as [issues](https://github.com/twitter/twitter-kit-android/issues).

Follow [@TwitterDev](http://twitter.com/twitterdev) on Twitter for updates.

## License

Copyright 2017 Twitter, Inc.

Licensed under the Apache License, Version 2.0: http://www.apache.org/licenses/LICENSE-2.0
