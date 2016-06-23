package com.company.app;

import lombok.SneakyThrows;

import java.io.UnsupportedEncodingException;

class SneakyThrowsExample implements Runnable{
    @SneakyThrows(UnsupportedEncodingException.class)
    public String utf8ToString(byte[] bytes) {
        return new String(bytes, "UTF-8");
    }
    @SneakyThrows
    public void run() {
        try {
            throw new Throwable();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
public class App {
    public static void main(String... args){
        SneakyThrowsExample sneakyThrowsExample = new SneakyThrowsExample();
        sneakyThrowsExample.utf8ToString("Tom".getBytes());
        sneakyThrowsExample.run();
    }
}
/*
https://projectlombok.org/features/SneakyThrows.html
http://rdafbn.blogspot.com/2013/07/lomboks-sneaky-throws.html

@SneakyThrows can be used to sneakily throw checked exceptions without actually declaring this in your method's throws
clause. This somewhat contentious ability should be used carefully, of course. The code generated by lombok will not
ignore, wrap, replace, or otherwise modify the thrown checked exception; it simply fakes out the compiler.

output:
java.lang.Throwable
	at com.company.app.SneakyThrowsExample.run(App.java:15)
	at com.company.app.App.main(App.java:25)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:497)
	at com.intellij.rt.execution.application.AppMain.main(AppMain.java:144)
 */