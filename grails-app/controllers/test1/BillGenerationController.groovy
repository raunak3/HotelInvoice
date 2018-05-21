package test1

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*


@Secured('ROLE_USER')
class BillGenerationController {

    BillGenerationService billGenerationService
    SpringSecurityService springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond billGenerationService.list(params), model:[billGenerationCount: billGenerationService.count()]
    }

    def show(Long id) {
        respond billGenerationService.get(id)
    }

    def create() {
        respond new BillGeneration(params)
    }
    def createInvoice(params){

        User user = (User)springSecurityService.currentUser
        HotelRegistration hr =  HotelRegistration.findByEmail(user.username)
        BillGeneration billGeneration = new BillGeneration()
        billGeneration.setBillNo(params.billNo)
        billGeneration.setCustomeName(params.customerName)
        billGeneration.setCustomerAddress(params.customerAddress)
        billGeneration.setCustmerEmail(params.customerEmail)
        int size = params.list('roomType').size()
        if(size==1){
            RoomDetails roomDetails = new RoomDetails()
            roomDetails.roomType = params.roomType
            String noOfRooms = params.noOfRooms
            roomDetails.noOfRooms = noOfRooms.toInteger()
            String rate = params.rate
            roomDetails.rate = rate.toInteger()
            String tax = params.tax
            roomDetails.tax = tax.toInteger()
            String discount = params.discount
            roomDetails.discount = discount.toInteger()
            String total = params.total
            roomDetails.total = total.toInteger()
            roomDetails.save(flush: true,failOnError : true)
            billGeneration.roomDetails.add(roomDetails)
        }else
        {
            for (int i=0;i<size;i++){
                RoomDetails roomDetails = new RoomDetails()
                roomDetails.roomType = params.roomType[i]
                String noOfRooms = params.noOfRooms[i]
                roomDetails.noOfRooms = noOfRooms.toInteger()
                String rate = params.rate[i]
                roomDetails.rate = rate.toInteger()
                String tax = params.tax[i]
                roomDetails.tax = tax.toInteger()
                String discount = params.discount[i]
                roomDetails.discount = discount.toInteger()
                String total = params.total[i]
                roomDetails.total = total.toInteger()
                roomDetails.save(flush: true,failOnError : true)
                billGeneration.roomDetails.add(roomDetails)
            }
        }
        billGeneration.save(flush: true,failOnError : true)
        render(view: 'createInvoice',model: [billGeneration:billGeneration,hr: hr])
    }

    def d(){
        User user = (User)springSecurityService.currentUser
        HotelRegistration hr =  HotelRegistration.findByEmail(user.username)
        HotelDetails hotelDetails = HotelDetails.findByHotelRegistration(hr)
        render(view: "d", model: [hr:hr,hotelDetails:hotelDetails])
    }


    def save(BillGeneration billGeneration) {
        if (billGeneration == null) {
            notFound()
            return
        }

        try {
            billGenerationService.save(billGeneration)
        } catch (ValidationException e) {
            respond billGeneration.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'billGeneration.label', default: 'BillGeneration'), billGeneration.id])
                redirect billGeneration
            }
            '*' { respond billGeneration, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond billGenerationService.get(id)
    }

    def update(BillGeneration billGeneration) {
        if (billGeneration == null) {
            notFound()
            return
        }

        try {
            billGenerationService.save(billGeneration)
        } catch (ValidationException e) {
            respond billGeneration.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'billGeneration.label', default: 'BillGeneration'), billGeneration.id])
                redirect billGeneration
            }
            '*'{ respond billGeneration, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        billGenerationService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'billGeneration.label', default: 'BillGeneration'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'billGeneration.label', default: 'BillGeneration'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
