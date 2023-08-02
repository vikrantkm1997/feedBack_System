package com.lwcd.hotel.services;

import com.lwcd.hotel.entities.Hotel;

import java.util.List;

public interface HotelService {

    Hotel create(Hotel hotel);

    Hotel getHotel(String hotelId);

    List<Hotel> getAllHotels();

}
