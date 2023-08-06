package com.lwcd.user.service;

import com.lwcd.user.service.entities.Rating;
import com.lwcd.user.service.external.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

//	@Autowired
//	private RatingService ratingService;

//	@Test
//	void createRating()
//	{
//		Rating rating = Rating.builder().rating(9).feedBack("Not so good so far").userId("").hotelId("").build();
//		Rating createdRating = ratingService.createRating(rating);
//		System.out.println("New Rating created");
//	}


}
