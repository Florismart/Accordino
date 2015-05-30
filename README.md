Accordino
=============

Accordino is an Open Source Android library that allows developers to easily add an Accordion View to their projects. Feel free to use it all you want in your Android apps provided that you cite this project and include the license in your app.


![Screenshot](https://raw.githubusercontent.com/Florismart/Accordino/master/screen-app.png)


Setup
-----
__1.__ In Eclipse, import the library as an Android library project. Project > Clean to generate the binaries 
you need, like R.java, etc.

__2.__ Then, add Accordino as a dependency to your existing project.


XML Usage
-----
All options are optional. Use only those you really want to customize.
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
You can setup an OnStateChangeListener in this way:
```java
@Override
protected void onCreate(Bundle savedInstanceState) {
   ...
   
   firstAccordion = (Accordino) findViewById(R.id.first_accordino);
   firstAccordion.setOnStateChangeListener(new OnStateChangeListener() {

	   @Override
	   public void onExpand(Accordino accordion) {
		   // TODO Auto-generated method stub

   	}

	   @Override
	   public void onCollapse(Accordino accordion) {
	   	// TODO Auto-generated method stub
	   }

   });
   
   ...
}
```

or... put custom Views as header and body of your GREAT ACCORDINO this way:
```java
@Override
protected void onCreate(Bundle savedInstanceState) {
   ...
   
   firstAccordion = (Accordino) findViewById(R.id.first_accordino);
   firstAccordion.setHeader(new View(this));
   firstAccordion.setBody(new View(this));
   
   ...
}
```
