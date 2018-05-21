package test1

class BillGeneration {

    String billNo
    String customeName
    String customerAddress
    String custmerEmail
  //  String customerPhNo
  //  String customerGst
  //  String bookedBy
  //  String paymentBy
  //  String blockedBy
    Date checkInDate
    Date checkOutDate
    Date invoiceDate
  //  Date checkinTime
    // Date checkOutTime
    List roomDetails = []

    static hasMany = [roomDetails:RoomDetails]


        static constraints = {
            checkInDate(nullable: true,null:true,blank: true)
            checkOutDate(nullable: true,null:true,blank: true)
            invoiceDate(nullable: true,null:true,blank: true)
    }

}
