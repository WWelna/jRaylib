# jRaylib
This is a [JNA](https://github.com/java-native-access/jna) wrapper for [raylib](https://github.com/raysan5/raylib) 4.0.0

![Hello World](https://github.com/WWelna/jRaylib/blob/screenshots/jraylib-firstwindow.png)

![Cube](https://github.com/WWelna/jRaylib/blob/screenshots/ihaveacube.png)

This code is very much Alpha with a lot of it not fully tested. This is for a project of mine needing of graphical rendering, but I hope to at some point have it be a fully functional wrapper.

# Usage

Drop all the classes from src into your code base, and add JNA to your POM.XML.

POM.XML:
```
<dependencies>
<dependency>
  <groupId>net.java.dev.jna</groupId>
  <artifactId>jna</artifactId>
  <version>5.10.0</version>
</dependency>
<dependency>
  <groupId>net.java.dev.jna</groupId>
  <artifactId>jna-platform</artifactId>
  <version>5.10.0</version>
</dependency>
</dependencies>
```

At present, this wrapper has only been tested under Ubuntu 20.04.3 LTS (Focal Fossa), but it should work under any other platform supported by JNA and having the required binaries for raylib. You may have to change (RayLibNative.java)[https://github.com/WWelna/jRaylib/blob/main/com/occultusterra/raylib/RayLibNative.java#L35] for your desired raylib library location and name.

Only tested using openJDK 16.

## LICENSE

Copyright (c) 2021 William Welna (wwelna@occultusterra.com)

This software is provided "as-is", without any express or implied warranty. In no event 
will the authors be held liable for any damages arising from the use of this software.

Permission is granted to anyone to use this software for any purpose, including commercial 
applications, and to alter it and redistribute it freely, subject to the following restrictions:

1. The origin of this software must not be misrepresented; you must not claim that you 
wrote the original software. If you use this software in a product, an acknowledgment 
in the product documentation would be appreciated but is not required.

2. Altered source versions must be plainly marked as such, and must not be misrepresented
as being the original software.

3. This notice may not be removed or altered from any source distribution. 

