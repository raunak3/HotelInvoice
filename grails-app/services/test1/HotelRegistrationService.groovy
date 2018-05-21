package test1

import grails.gorm.services.Service

@Service(HotelRegistration)
interface HotelRegistrationService {

    HotelRegistration get(Serializable id)

    List<HotelRegistration> list(Map args)

    Long count()

    void delete(Serializable id)

    HotelRegistration save(HotelRegistration hotelRegistration)

}