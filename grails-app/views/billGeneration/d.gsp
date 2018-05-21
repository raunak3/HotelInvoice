<%--
  Created by IntelliJ IDEA.
  User: Raunak
  Date: 19-05-2018
  Time: 13:13
--%>

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Invoice</title>
    <asset:stylesheet src="d.css"></asset:stylesheet>
    <asset:javascript src="d.js"></asset:javascript>
    <asset:javascript src="application.js"></asset:javascript>
    <link rel="license" href="https://www.opensource.org/licenses/mit-license/">
</head>
<body>
<header>
    <h1>Invoice</h1>
    <address >
        <p>${hr.hotelName}</p>
        <p>${hr.address}</p>
        <p>${hr.gstin}</p>
    </address>
    <span><img alt="" src="data:image/png;base64,${hotelDetails.logo.encodeBase64()}"></span>
</header>
<article>
    <h1>Recipient</h1>
    <g:form action="createInvoice" controller="billGeneration">
       <table class="guest">
           <tr>
               <th><span>Guest Name</span></th>
               <td> <g:select name="titles" from="'Mr','Mrs','Miss','C/O'"></g:select><span><input name="customerName" type="text"></span></td>
           </tr>
           <tr>
               <th><span>Address</span></th>
               <td><span ><input name="customerAddress" type="text"></span></td>
           </tr>
           <tr>
               <th><span>Phone No.</span></th>
               <td><span ><input name="customerPhNo" type="text"></span></td>
           </tr>
           <tr>
               <th><span>Email</span></th>
               <td><span ><input name="customerEmail" type="text"></span></td>
           </tr>
           <tr>
               <th><span>GST</span></th>
               <td><span ><input type="text"></span></td>
           </tr>

       </table>


    <table class="meta">
        <tr>
            <th><span>Invoice #</span></th>
            <td><span><input name="billNo" type="text"></span></td>
        </tr>
        <tr>

            <th><span>Invoice Date</span></th>
            <td><span><input type="date" value="${new Date()}"></span></td>
        </tr>
        <tr style="visibility: hidden">
            <th><span>Amount Due</span></th>
            <td><span id="prefix" >$</span><span>600.00</span></td>
        </tr>
        <tr>

            <th><span>Check In Date</span></th>
            <td><span><input type="date" value="${new Date()}"></span></td>
        </tr>
        <tr>

            <th><span>Check Out Date</span></th>
            <td><span><input type="date" value="${new Date()}"></span></td>
        </tr>
    </table>
    <table class="inventory">
        <thead>
        <tr>
            <th><span >Room Type</span></th>
            <th><span >No. Of Rooms</span></th>
            <th><span >Room Rate</span></th>
            <th><span >Tax</span></th>
            <th><span >Discount</span></th>
            <th><span >Total</span></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><a class="cut">-</a><span ><g:textField name="roomType" /></span></td>
            <td><span ><g:textField name="noOfRooms" /></span></td>
            <td><span data-prefix>Rs.</span><span ><g:textField name="rate" /></span></td>
            <td><span ><g:textField name="tax" /></span></td>
            <td><span data-prefix>Rs.</span><span><g:textField name="discount" /></span></td>
            <td><span data-prefix>Rs.</span><span><g:textField name="total" /></span></td>
        </tr>
        </tbody>
    </table>
    <a class="add">+</a>
    <table class="balance">
        <tr>
            <th><span contenteditable>Total</span></th>
            <td><span data-prefix>$</span><span>600.00</span></td>
        </tr>
        <tr>
            <th><span contenteditable>Amount Paid</span></th>
            <td><span data-prefix>$</span><span contenteditable>0.00</span></td>
        </tr>
        <tr>
            <th><span contenteditable>Balance Due</span></th>
            <td><span data-prefix>$</span><span>600.00</span></td>
        </tr>
    </table>
        <g:actionSubmit value="Submit" action="createInvoice"/>
    </g:form>
</article>
<aside>
    <h1><span contenteditable>Additional Notes</span></h1>
    <div contenteditable>
        <p>A finance charge of 1.5% will be made on unpaid balances after 30 days.</p>
    </div>
</aside>
</body>
<script type="application/javascript">
    $( function() {
        $( "#myDate" ).datepicker();

    });
</script>
</html>