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
    </head>
    <body>
        <h1>Hello</h1>
        <form method = POST>
            <select name="width">
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
            <%
                if (request.getParameter("angle") != null) {
                    out.print((String) request.getParameter("angle"));
                }
            %>
            <select name="slope"    <%
                if (request.getParameter("angle") == null) {
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

            <select name="flatroof" <%
                if (request.getParameter("angle") != null) {
                    out.print("disabled");
                }%>>
                <option value="plasttrapezplader">Plasstrapezplader</option>
            </select>
            <select name="flatroof" <%
                if (request.getParameter("angle") == null) {
                    out.print("disabled");
                }%>>
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
            <select name="shedwidth">
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
            <select name="shedlength">
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
            <button value="angled" formaction="http://localhost:8080/FogProject/CarportDetails.jsp" name="angle" value="true">Angled</button>
            <button value="not angled" formaction="http://localhost:8080/FogProject/CarportDetails.jsp">Not Angled</button>
            <br>
            <br>
            <h1></h1>
            <input type="text" name="name">
            <h1></h1>
            <input type="text" name="address">
            <br>
            <h1></h1>
            <input type="text" name="zipcodencity">
            <h1></h1>
            <input type="text" name="phone">
            <br>
            <h1></h1>
            <input type="text" name="email">
            <h1></h1>
            <input type="text" name="comments   ">

            <button type="submit">Order Carport</button>
        </form>
    </body>
</html>
