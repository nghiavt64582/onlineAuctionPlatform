package com.example.onlineAuctionPlatform;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.online_auction_platform.entities.Bidder;
import com.example.online_auction_platform.services.bidder.BidderService;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class online_auction_platformApplicationTests {

	@Autowired
	BidderService bidderService;

	@Test
	void contextLoads() {
	}

	@Test
	void testJpa() {
		Bidder bidder = Bidder.builder()
			.username("Trần Trung Sức")
			.email("suctt2912@gmail.com")
			.cash(1)
			.createdDate(LocalDateTime.of(2022, 8, 12, 9, 0, 0))
			.build();
		bidderService.save(bidder);
	}

}
