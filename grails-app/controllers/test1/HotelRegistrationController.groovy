package test1

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

@Secured('ROLE_ADMIN')
class HotelRegistrationController {

    HotelRegistrationService hotelRegistrationService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond hotelRegistrationService.list(params), model:[hotelRegistrationCount: hotelRegistrationService.count()]
    }

    def show(Long id) {
        respond hotelRegistrationService.get(id)
    }

    def create() {
        respond new HotelRegistration(params)
    }

    def save(HotelRegistration hotelRegistration) {
        def userRole = new Role(authority:'ROLE_USER').save()
        def me = new User(username:hotelRegistration.email, password:hotelRegistration.password).save()
        UserRole.create me, userRole
        UserRole.withSession {
            it.flush()
            it.clear()
        }
        if (hotelRegistration == null) {
            notFound()
            return
        }

        try {
            hotelRegistrationService.save(hotelRegistration)
        } catch (ValidationException e) {
            respond hotelRegistration.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'hotelRegistration.label', default: 'HotelRegistration'), hotelRegistration.id])
                redirect hotelRegistration
            }
            '*' { respond hotelRegistration, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond hotelRegistrationService.get(id)
    }

    def update(HotelRegistration hotelRegistration) {
        if (hotelRegistration == null) {
            notFound()
            return
        }

        try {
            hotelRegistrationService.save(hotelRegistration)
        } catch (ValidationException e) {
            respond hotelRegistration.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'hotelRegistration.label', default: 'HotelRegistration'), hotelRegistration.id])
                redirect hotelRegistration
            }
            '*'{ respond hotelRegistration, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        hotelRegistrationService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'hotelRegistration.label', default: 'HotelRegistration'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'hotelRegistration.label', default: 'HotelRegistration'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
