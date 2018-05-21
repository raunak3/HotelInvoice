package test1

import sun.security.util.Password

class HotelRegistration {

    String hotelName
    String gstin
    String email
    String password
    String address

    static constraints = {
        hotelName blank: false
        password size: 5..15, blank: false, password:true
        email email: true, blank: false
        gstin unique: true, blank: false
    }
}
