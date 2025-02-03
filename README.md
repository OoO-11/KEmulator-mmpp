# KEmulator-ez-java/SK-VM

## 1. Overview

Emulator to run SK-VM and ez-java applications. This repository implements oem APIs for old korean games, Especially SK-VM and ez-java(a.k.a. Javastation). They are both compatible with J2ME/MIDP and have their own oem APIs.

To support them, This Emulator include
* LG MMPP API
* XCE SK-VM API

Based on https://github.com/shinovon/KEmulator

## 2. Reproduce

IntelliJ IDEA, JDK1.8

* To run ez-java application, only jar file is needed(TBU)
* To run sk-vm application, select .zip file.

## 3. Contribution

You can find implemented APIs at
```declarative
/KEmulator/src/oem/mmpp
/KEmulator/src/oem/skt
/KEmulator/src/oem/xce
```
Here is docs For mmpp and sk-vm

https://nikita36078.github.io/J2ME_Docs/docs/LG_MMPP_API/

https://web.archive.org/web/20030617131851/http://developer.xce.co.kr/KOREAN/API/sktapi/overview-summary.html



## 4. Thanks
This project includes code licensed under the Mozilla Public License 2.0 (MPL-2.0).

I referred to https://github.com/usernameak/sktemu to get info about LBM file format and Keycodes, and other details. It helped me a lot. Thanks!
