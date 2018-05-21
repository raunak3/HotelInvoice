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
    <span><img alt="" src="http://www.jonathantneal.com/examples/invoice/logo.png"><input type="file" accept="image/*"></span>
</header>
<article>
    <h1>Recipient</h1>
        <table class="guest">
            <tr>
                <th><span>Guest Name</span></th>
                <td><span>${billGeneration.customeName}</span></td>
            </tr>
            <tr>
                <th><span>Address</span></th>
                <td><span>${billGeneration.customerAddress}</span></td>
            </tr>
            <tr>
                <th><span>Phone No.</span></th>
                <td><span></span></td>
            </tr>
            <tr>
                <th><span>Email</span></th>
                <td><span >${billGeneration.customeName}</span></td>
            </tr>
            <tr>
                <th><span>GST</span></th>
                <td><span >${billGeneration.customeName}</span></td>
            </tr>

        </table>


        <table class="meta">
            <tr>
                <th><span>Invoice #</span></th>
                <td><span>${billGeneration.billNo}</span></td>
            </tr>
            <tr>

                <th><span>Date</span></th>
                <td><span><input type="date" value="${new Date()}"></span></td>
            </tr>
            <tr style="visibility: hidden">
                <th><span>Amount Due</span></th>
                <td><span id="prefix" contenteditable>$</span><span>600.00</span></td>
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

                <g:each in="${billGeneration.roomDetails}" var="roomDetails">
                    <td><span>${roomDetails.roomType}</span></td>
                    <td><span>${roomDetails.noOfRooms}</span></td>
                    <td><span>${roomDetails.rate}</span></td>
                    <td><span data-prefix>Rs.</span><span >${roomDetails.tax}</span></td>
                    <td><span>${roomDetails.discount}</span></td>
                    <td><span data-prefix>Rs.</span><span>${roomDetails.total}</span></td>
                </g:each>
            </tr>
            </tbody>
        </table>
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