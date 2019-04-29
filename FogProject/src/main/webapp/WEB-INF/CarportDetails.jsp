<%-- 
    Document   : CarpotDetails
    Created on : 24-04-2019, 10:33:30
    Author     : Lukas Bjørnvad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carport</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <jsp:include page="/inclusions/NavBar.jsp" />
    
    <!--<body id = entrance-menu>
        <h1 id=Head-title>Carport Details </h1>
        <form method = POST id="page">-->
    <%--<jsp:include page="/includes/NavBar.jsp" />--%>
    
    <body>
        <h1>hitler did 9/11</h1>
        <form> <%boolean withangle;
            if (request.getParameter("anglechoice") != null) {
                if (request.getParameter("angle") != null) {
                    withangle = true;
                } else if (request.getParameter("noangle") != null) {
                    withangle = false;
                } else {
                    withangle = Boolean.parseBoolean(request.getParameter("anglechoice"));
                }
            } else {
                withangle = false;
            }
            boolean withshed;
            if (request.getParameter("shedchoice") != null) {
                if (request.getParameter("noshed") != null) {
                    withshed = false;
                } else if (request.getParameter("shed") != null) {
                    withshed = true;
                } else {
                    withshed = Boolean.parseBoolean(request.getParameter("shedchoice"));
                }
            } else {
                withshed = false;
            }
            %>
            <input type="Hidden" name="anglechoice" value="<%out.print(withangle);%>">
            <input type="Hidden" name="shedchoice" value="<%out.print(withshed);%>">

            <button formaction="CarportDetails" name="angle" value="true">Angled</button>
            <button formaction="CarportDetails" name="noangle" value="true">Not Angled</button>
            <button name="shed" formaction="CarportDetails" value="true">Shed</button>
            <button name="noshed" formaction="CarportDetails" value="true" >No Shed</button>
        </form>
        <br>
        <form method = POST>
            <select name="width">
                <option value="choosew">Width cm</option>
                <option value="240">240 cm</option>
                <option value="270">270 cm</option>
                <option value="300">300 cm</option>
                <option value="330">330 cm</option>
                <option value="360">360 cm</option>
                <option value="390">390 cm</option>
                <option value="420">420 cm</option>
                <option value="450">450 cm</option>
                <option value="480">480 cm</option>
                <option value="510">510 cm</option>
                <option value="540">540 cm</option>
                <option value="570">570 cm</option>
                <option value="600">600 cm</option>
                <option value="630">630 cm</option>
                <option value="660">660 cm</option>
                <option value="690">690 cm</option>
                <option value="720">720 cm</option>
                <option value="750">750 cm</option>
            </select>
            <select name="length">
                <option value="choosel">Length cm</option>
                <option value="240">240 cm</option>
                <option value="270">270 cm</option>
                <option value="300">300 cm</option>
                <option value="330">330 cm</option>
                <option value="360">360 cm</option>
                <option value="390">390 cm</option>
                <option value="420">420 cm</option>
                <option value="450">450 cm</option>
                <option value="480">480 cm</option>
                <option value="510">510 cm</option>
                <option value="540">540 cm</option>
                <option value="570">570 cm</option>
                <option value="600">600 cm</option>
                <option value="630">630 cm</option>
                <option value="660">660 cm</option>
                <option value="690">690 cm</option>
                <option value="720">720 cm</option>
                <option value="750">750 cm</option>
                <option value="780">780 cm</option>
            </select>
            <select name="slope"    <%
                if (request.getParameter("angle") != null) {
                } else if (request.getParameter("noangle") != null) {
                    out.print("disabled");
                } else if (request.getParameter("anglechoice") != null) {
                    if (!Boolean.parseBoolean(request.getParameter("anglechoice"))) {
                        out.print("disabled");
                    }
                } else {
                    out.print("disabled");
                }%>>
                <option value="15">15 grader</option>
                <option value="20">20 grader</option>
                <option value="25">25 grader</option>
                <option value="30">30 grader</option>
                <option value="35">35 grader</option>
                <option value="40">40 grader</option>
                <option value="45">45 grader</option>
            </select>

            <select name="roof" <%
                if (request.getParameter("noangle") != null) {
                } else if (request.getParameter("angle") != null) {
                    out.print("disabled");
                } else if (request.getParameter("anglechoice") != null) {
                    if (Boolean.parseBoolean(request.getParameter("anglechoice")));
                    out.print("disabled");
                }%>>
                <option value="plasttrapezplader">Plasstrapezplader</option>
            </select>
            <select name="roof" <%
                if (request.getParameter("angle") != null) {
                } else if (request.getParameter("noangle") != null) {
                    out.print("disabled");
                } else if (request.getParameter("anglechoice") != null) {
                    if (!Boolean.parseBoolean(request.getParameter("anglechoice"))) {
                        out.print("disabled");
                    }
                } else {
                    out.print("disabled");
                }

                    %>>
                <option value="BetontagstenRød">Betontagsten - Rød</option>
                <option value="BetontagstenTeglrød">Betontagsten - Teglrød</option>
                <option value="BetontagstenBrun">Betontagsten - Brun</option>
                <option value="BetontagstenSort">Betontagsten - Sort</option>
                <option value="EternittagB6Grå">Eternittag B6 - Grå</option>
                <option value="EternittagB6Sort">Eternittag B6 - Sort</option>
                <option value="EternittagB6Mokkabrun)">Eternittag B6 - Mokka (brun)</option>
                <option value="EternittagB6Rødbrun">Eternittag B6 - Rødbrun</option>
                <option value="EternittagB6Teglrød">Eternittag B6 - Teglrød</option>
                <option value="EternittagB7Grå">Eternittag B7 - Grå</option>
                <option value="EternittagB7Sort">Eternittag B7 - Sort</option>
                <option value="EternittagB7Mokka(brun)">Eternittag B7 - Mokka (brun)</option>
                <option value="EternittagB7Rødbrun">Eternittag B7 - Rødbrun</option>
                <option value="EternittagB7Teglrød">Eternittag B7 - Teglrød</option>
                <option value="EternittagB7Rødflammet">Eternittag B7 - Rødflammet</option>

            </select>
            <select name="shedwidth" 
                    <%  if (request.getParameter("noshed") != null) {
                            out.print("disabled");
                        } else if (request.getParameter("shed") != null) {
                        } else if (request.getParameter("shedchoice") != null) {
                            if (!Boolean.parseBoolean(request.getParameter("shedchoice"))) {
                                out.print("disabled");
                            }
                        } else {
                            out.print("disabled");
                        }%>>

                <option value="210">210 cm</option>
                <option value="240">240 cm</option>
                <option value="270">270 cm</option>
                <option value="300">300 cm</option>
                <option value="330">330 cm</option>
                <option value="360">360 cm</option>
                <option value="390">390 cm</option>
                <option value="420">420 cm</option>
                <option value="450">450 cm</option>
                <option value="480">480 cm</option>
                <option value="510">510 cm</option>
                <option value="540">540 cm</option>
                <option value="570">570 cm</option>
                <option value="600">600 cm</option>
                <option value="630">630 cm</option>
                <option value="660">660 cm</option>
                <option value="690">690 cm</option>
                <option value="720">720 cm</option>
            </select>
            <select name="shedlength" <%
                if (request.getParameter("noshed") != null) {
                    out.print("disabled");
                } else if (request.getParameter("shed") != null) {
                } else if (request.getParameter("shedchoice") != null) {
                    if (!Boolean.parseBoolean(request.getParameter("shedchoice"))) {
                        out.print("disabled");
                    }
                } else {
                    out.print("disabled");
                }%>>

                <option value="150">150 cm</option>
                <option value="180">180 cm</option>
                <option value="210">210 cm</option>
                <option value="240">240 cm</option>
                <option value="270">270 cm</option>
                <option value="300">300 cm</option>
                <option value="330">330 cm</option>
                <option value="360">360 cm</option>
                <option value="390">390 cm</option>
                <option value="420">420 cm</option>
                <option value="450">450 cm</option>
                <option value="480">480 cm</option>
                <option value="510">510 cm</option>
                <option value="540">540 cm</option>
                <option value="570">570 cm</option>
                <option value="600">600 cm</option>
                <option value="630">630 cm</option>
                <option value="660">660 cm</option>
                <option value="690">690 cm</option>
            </select> 

            <select name="walls">
                <option value="thevoid">Void</option>  
                <option value="bones">Suspicious Bones</option>
                <option value="pasta">Spaghetti</option>
                <option value="oldone">Eldritch Goo</option>
            </select>
            <br>
            <br>
            <h3>Name</h3>
            <input type="text" name="name" required="required">
            <h3>Address</h3>
            <input type="text" name="address" required="required">
            <h3>Zipcode/City</h3>
            <input type="text" name="zipcodencity" required="required">
            <h3>Phone Number</h3>
            <input type="text" name="phone" required="required">
            <h3>Email address</h3>
            <input type="text" name="email" required="required">
            <h3>Comments for the Order</h3>
            <input type="text" name="comments" required="required">
            <br>
            <br>
            <button type="submit" formaction="SendInformation" >Order Carport</button>

        </form>
    </body>
</html>
