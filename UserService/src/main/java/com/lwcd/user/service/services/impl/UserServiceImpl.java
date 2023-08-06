package com.lwcd.user.service.services.impl;

import com.lwcd.user.service.entities.Hotel;
import com.lwcd.user.service.entities.Rating;
import com.lwcd.user.service.entities.User;
import com.lwcd.user.service.external.services.HotelService;
import com.lwcd.user.service.repositories.UserRepository;
import com.lwcd.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.lwcd.user.service.exceptions.ResourceNotFoundException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

//    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        //This will generate unique userId
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        List <User> userList = userRepository.findAll();
        for(User user: userList)
        {
            ArrayList<Rating> userRating = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), ArrayList.class);
            user.setRatings(userRating);
        }
        return userList;
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("UserDoes Not exist" + userId));
        Rating []userRating = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
        List<Rating> ratings = Arrays.stream(userRating).toList();
        ArrayList<Rating> ratingList = (ArrayList<Rating>) ratings.stream().map(rating-> {
            //http://localhost:8082/hotels/4b492c47-19c8-4cc0-82a9-3e114dcabf5b -> calling this api of hotel
            //ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);  //this mapping with Hotel-Service was done @LoadBalance in RestTemplate in MyConfig
            //Hotel hotel = forEntity.getBody();
            /**Using Feign Client **/
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            //logger.info("the status code for hotel entity is " + forEntity.getStatusCode());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());
//        logger.info(userRating.toString());
        user.setRatings(ratingList);
        return user;
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
        return;
    }

    @Override
    public User updateUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("UserDoes Not exist!!!" + userId));
        return user;
    }
}
