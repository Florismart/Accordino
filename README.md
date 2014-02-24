Accordino
=============

Accordino is an Open Source Android library that allows developers to easily add an Accordion View to their projects. Feel free to use it all you want in your Android apps provided that you cite this project and include the license in your app.


![Screenshot](https://raw2.github.com/Florismart/Accordino/master/screen-app.png)


Setup
-----
__1.__ In Eclipse, import the library as an Android library project. Project > Clean to generate the binaries 
you need, like R.java, etc.

__2.__ Then, add Accordino as a dependency to your existing project.


XML Usage
-----
All options builder are optional. Use only those you really want to customize.
```xml
<com.fattorini.luca.android.accordini.view.Accordino
   xmlns:accordino="http://schemas.android.com/apk/res-auto"
   android:id="@+id/first_accordino"
   style="@style/Accordino"
   accordino:expanded="false"
   accordino:bodyView="@layout/view_body"
   accordino:headerView="@layout/view_header"
   accordino:collapsedResid="@drawable/accordino_collapsed"
   accordino:expandedResid="@drawable/accordino_expanded"/>
```

Customization:
-----
