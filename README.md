# feedBack_System

Rating System

**ArchiTecture**

It has following components
1. UserService
2. RatingService
3. HotelService
4. ServiceRegistry
5. ApiGateWay
6. ConfigServer

**UserService** can fetch all the user details from User DB along with all the ratings he has given from **ratingService**. Besides that the all the ratings I am also fetching HottelDetails from **hotelService**. I have used **feignClient/RestTemplate** to connect from the other microservices. 

I have used **ServiceRegistry** to see all the live running services inorder to keep track of them. It is through **serviceRegistry** only I am able to identify other services as well.

For the purpose to provide abstraction to the APIs i am using ApiGateway, this will ensure all the requests now coming can come from ApiGateway without exposing the other services endPoints. Besides security it is also doing load Balancing.

We have configServer as a microservice which will fetch the configs for local/dev/prod from a separate repository present in git. Whenever a service needs configs it will fetch from configServer and configServer inturn will fetch from Configs separate repository whose uri is mentioned in application.yml of configServer
