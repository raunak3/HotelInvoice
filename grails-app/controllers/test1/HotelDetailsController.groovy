package test1

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*



@Secured('ROLE_USER')
class HotelDetailsController {

    HotelDetailsService hotelDetailsService
    SpringSecurityService springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond hotelDetailsService.list(params), model:[hotelDetailsCount: hotelDetailsService.count()]
    }

    def show(Long id) {
        respond hotelDetailsService.get(id)
    }

    def create() {
        respond new HotelDetails(params)
    }

    def save(HotelDetails hotelDetails) {

        User user = (User)springSecurityService.currentUser
        HotelRegistration hotelRegistration = HotelRegistration.findByEmail(user.username)
        hotelDetails.hotelRegistration = hotelRegistration
        if (hotelDetails == null) {
            notFound()
            return
        }
        try {
            hotelDetailsService.save(hotelDetails)
        } catch (ValidationException e) {
            respond hotelDetails.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'hotelDetails.label', default: 'HotelDetails'), hotelDetails.id])
                redirect hotelDetails
            }
            '*' { respond hotelDetails, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond hotelDetailsService.get(id)
    }

    def update(HotelDetails hotelDetails) {
        if (hotelDetails == null) {
            notFound()
            return
        }

        try {
            hotelDetailsService.save(hotelDetails)
        } catch (ValidationException e) {
            respond hotelDetails.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'hotelDetails.label', default: 'HotelDetails'), hotelDetails.id])
                redirect hotelDetails
            }
            '*'{ respond hotelDetails, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        hotelDetailsService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'hotelDetails.label', default: 'HotelDetails'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'hotelDetails.label', default: 'HotelDetails'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
