package test1

import java.sql.Blob

class HotelDetails {

    String billSeries
    int totalNoOfRooms
    def phoneNo
    def mobileNo
    String hotelLicenceNo
    String foodLicenceNo
    byte[] logo
    HotelRegistration hotelRegistration

    static constraints = {

        logo nullable: true, maxSize: 1000000
        hotelRegistration(display:false)
    }
}
