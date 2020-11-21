package com.wdy.springboot.biz.java;

public class MyThread {

	public static void main(String[] args) {
		Runnable runner = new Runnable(){
			@Override
			public void run(){
				System.out.print("foo");
			}
		};
		Thread t = new Thread(runner);
		t.run();
		System.out.println("bar");
	}
}
// 输出：foobar