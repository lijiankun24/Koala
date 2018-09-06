# Koala

![](https://img.shields.io/badge/language-java-brightgreen.svg)  [ ![Download](https://api.bintray.com/packages/lijiankun/maven/koala/images/download.svg) ](https://bintray.com/lijiankun/maven/koala/_latestVersion)

## 简介

* 在开发项目的时候，经常会想看一个方法的入参、返回结果和执行耗时，我们通常的做法是打日志、打断点调试去看，这样做确实可以达到我们的目的，但是效率是低下的。
* JakeWharton 大神的 [hugo](https://github.com/JakeWharton/hugo) 库便具有这样的功能，可以打印方法的入参、返回结果和执行耗时。
* Koala 也具有和 hugo 同样的功能，可以打印方法的入参、返回结果和执行耗时。Koala 和 hugo 都是基于 AOP 思想，和 hugo 不同的是，hugo 使用 AspectJ 技术实现的，而 Koala 是使用 ASM 修改字节码实现的。

## 添加依赖
### 添加 Koala Gradle Plugin 依赖
在项目工程的 `build.gradle` 中添加如下代码：
```
    buildscript {
        repositories {
            maven {
                url "https://plugins.gradle.org/m2/"
            }
        }
        dependencies {
            classpath "gradle.plugin.com.lijiankun24:buildSrc:1.1.1"
        }
    }
```

在需要使用的 module 中的 build.gradle 中添加如下代码：
```
    apply plugin: "com.lijiankun24.koala-plugin"
```

### 添加 Koala 依赖
Gradle:
``` groovy
    compile 'com.lijiankun24:koala:x.y.z'
```

Maven:
``` groovy
    <dependency>
        <groupId>com.lijiankun24</groupId>
        <artifactId>koala</artifactId>
        <version>x.y.z</version>
        <type>pom</type>
    </dependency>
```
>替换上面的 `x` 、 `y` 和 `z`为最新的版本号:[ ![Download](https://api.bintray.com/packages/lijiankun/maven/koala/images/download.svg) ](https://bintray.com/lijiankun/maven/koala/_latestVersion)

## 使用
使用起来还是非常简单的，在 Java 的方法上添加 `@KoalaLog` 注解，如下所示：
``` Java
    @KoalaLog
    public String getName(String first, String last) {
        SystemClock.sleep(15); // Don't ever really do this!
        return first + " " + last;
    }
```

当上述方法被调用的时候，Logcat 中的输出如下所示：
```
09-04 20:51:38.008 12076-12076/com.lijiankun24.practicedemo I/0KoalaLog: ┌───────────────────────────────────------───────────────────────────────────------
09-04 20:51:38.008 12076-12076/com.lijiankun24.practicedemo I/1KoalaLog: │ The class's name: com.lijiankun24.practicedemo.MainActivity
09-04 20:51:38.008 12076-12076/com.lijiankun24.practicedemo I/2KoalaLog: │ The method's name: getName(java.lang.String, java.lang.String)
09-04 20:51:38.008 12076-12076/com.lijiankun24.practicedemo I/3KoalaLog: │ The arguments: [li, jiankun]
09-04 20:51:38.008 12076-12076/com.lijiankun24.practicedemo I/4KoalaLog: │ The result: li jiankun
09-04 20:51:38.008 12076-12076/com.lijiankun24.practicedemo I/5KoalaLog: │ The cost time: 15ms
09-04 20:51:38.008 12076-12076/com.lijiankun24.practicedemo I/6KoalaLog: └───────────────────────────────────------───────────────────────────────────------
```

## 混淆规则
```
 -keep class com.lijiankun24.koala.** { *; }
```

## About Me
* **Email**: <jiankunli24@gmail.com>
* **Home**: <http://lijiankun24.com>
* **简书**: <https://www.jianshu.com/u/1abe21b7ff5f>
* **微博**: <http://weibo.com/lijiankun24>

## 鸣谢
感谢以下四个库和它的作者：
* **hugo**: <https://github.com/JakeWharton/hugo>
* **CostTime**: <https://github.com/JeasonWong/CostTime>
* **AppMethodTime**: <https://github.com/zjw-swun/AppMethodTime>
* **MeetyouCost**: <https://github.com/iceAnson/MeetyouCost>

## License
```
 Copyright 2018, lijiankun24

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```