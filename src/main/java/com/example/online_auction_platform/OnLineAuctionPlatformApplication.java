package com.example.online_auction_platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnLineAuctionPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnLineAuctionPlatformApplication.class, args);
	}

	public static void test() {
		StringBuilder s = new StringBuilder("");
		Thread thread1 = new Thread(() -> {
			for (int i = 1; i <= 100000; i++)
				s.append("a");
		});
		Thread thread2 = new Thread(() -> {
			for (int i = 1; i <= 100000; i++)
				s.append("b");
		});
		try {
			thread1.start();
			thread2.start();
			thread1.join();
			thread2.join();
			System.out.println("String length : " + s.length());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
