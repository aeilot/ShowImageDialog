# ShowImageDialog
[![](https://jitpack.io/v/aeilot/ShowImageDialog.svg)](https://jitpack.io/#aeilot/ShowImageDialog)

Show a couple of images with a FULL SCREEN dialog. It is like the one in WeChat. Used in Daily Notes and based on PhotoView.
## How to Install
To get this project into your build:
### Maven
**Step 1.** Add the JitPack repository to your build file
```maven
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```
**Step 2.** Add the dependency
```maven
	<dependency>
	    <groupId>com.github.aeilot</groupId>
	    <artifactId>ShowImageDialog</artifactId>
	    <version>VERSION</version>
	</dependency>
```

### Gradle
**Step 1.** Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:
```gradle
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
**Step 2.** Add the dependency
```gradle
	dependencies {
	        implementation 'com.github.aeilot:ShowImageDialog:VERSION'
	}
```

## How to use

Using it is very simple.
``` kotlin
  //mContext: Context, mImgUrls: List<String>, pos: Int
  val sid = ShowImagesDialog(mContext,mImgUrls,pos)
  sid.show()
```
**That's it!**
