<%-- 
    Document   : CarportDetails
    Created on : 24-04-2019, 10:33:30
    Author     : Lukas Bj�rnvad
--%>

<%@page contentType="text/html" pageEncoding="Windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=Windows-1252">
        <title>Johannes Fog Carporte</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">       
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="../CSS/main.css">

        <!-- TOGGLE BUTTONS FROM BOOTSTRAP -->
        <link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
        <script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
    </head>
    <jsp:include page="/inclusions/NavBar.jsp" />

    <body>
        <div class="mainbody">

            <h1>Design din egen carport</h1>
            <h3>og f� et godt tilbud!</h3>

            <%
                if (request.getSession().getAttribute("portError") != null) {
            %>
            <div class="alert">
                <span class="closebtn" onclick="this.parentElement.style.display = 'none';">&times;</span> 
                V�lg venligst tilladte valgmuligheder i menuerne.
            </div>
            <%      request.getSession().setAttribute("portError", null);
                }

                if (request.getSession().getAttribute("custError") != null) {
            %>
            <div class="alert">
                <span class="closebtn" onclick="this.parentElement.style.display = 'none';">&times;</span> 
                Tjek venligst, at alle felterne for personlige oplysninger indeholder tilladte v�rdier.
            </div>
            <%      request.getSession().setAttribute("custError", null);
                }
            %>

            <h4>Tag:</h4>
            <input id="roofSwitch" type="checkbox" data-toggle="toggle" data-on="Med rejsning" data-off="Fladt" onchange="switchRoof()">

            <h4>Redskabsskur:</h4>
            <input id="shedSwitch" type="checkbox" data-toggle="toggle" data-on="Med" data-off="Uden" onchange="switchShed()">

            <script type="text/javascript">
                function switchRoof() {
                    var raised = document.getElementById("roofSwitch").checked;
                    if (raised) {
                        document.getElementById("flat_roof_type").selectedIndex = 0;
                        document.getElementById("roof_slope").style.display = "block";
                        document.getElementById("raised_roof_type").style.display = "block";
                        document.getElementById("flat_roof_type").style.display = "none";
                    } else {
                        document.getElementById("roof_slope").selectedIndex = 0;
                        document.getElementById("raised_roof_type").selectedIndex = 0;
                        document.getElementById("roof_slope").style.display = "none";
                        document.getElementById("raised_roof_type").style.display = "none";
                        document.getElementById("flat_roof_type").style.display = "block";
                    }
                    document.getElementById("roof_slope").disabled = (!raised);
                    document.getElementById("flat_roof_type").disabled = (raised);
                    document.getElementById("raised_roof_type").disabled = (!raised);
                }

                function switchShed() {
                    var enabled = document.getElementById("shedSwitch").checked;
                    if (!enabled) {
                        document.getElementById("shed_width").selectedIndex = 0;
                        document.getElementById("shed_length").selectedIndex = 0;
                        document.getElementById("shed_cover").selectedIndex = 0;

                        document.getElementById("shed_width").style.display = "none";
                        document.getElementById("shed_length").style.display = "none";
                        document.getElementById("shed_cover").style.display = "none";
                    } else
                    {
                        document.getElementById("shed_width").style.display = "block";
                        document.getElementById("shed_length").style.display = "block";
                        document.getElementById("shed_cover").style.display = "block";
                    }
                    document.getElementById("shed_width").disabled = (!enabled);
                    document.getElementById("shed_length").disabled = (!enabled);
                    document.getElementById("shed_cover").disabled = (!enabled);
                }
            </script> 

            <br><br>
            <form method = POST>
                <select name="width" style="display: block" >
                    <option selected disabled>V�lg bredde</option>
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
                <select name="length" style="display: block">
                    <option selected disabled>V�lg l�ngde</option>
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
                <select id="roof_slope" name="slope" style="display: none">
                    <option selected disabled>V�lg tagh�ldning</option>
                    <option value="15">15 grader</option>
                    <option value="20">20 grader</option>
                    <option value="25">25 grader</option>
                    <option value="30">30 grader</option>
                    <option value="35">35 grader</option>
                    <option value="40">40 grader</option>
                    <option value="45">45 grader</option>
                </select>

                <select id="flat_roof_type" name="roof" style="display: block">
                    <option selected disabled>V�lg fladt tag</option>
                    <option value="plasttrapezplader">Plasstrapezplader</option>
                </select>
                <select id="raised_roof_type" name="roof" style="display: none">
                    <option selected disabled>V�lg tag med rejsning</option>
                    <option value="BetontagstenR�d">Betontagsten - R�d</option>
                    <option value="BetontagstenTeglr�d">Betontagsten - Teglr�d</option>
                    <option value="BetontagstenBrun">Betontagsten - Brun</option>
                    <option value="BetontagstenSort">Betontagsten - Sort</option>
                    <option value="EternittagB6Gr�">Eternittag B6 - Gr�</option>
                    <option value="EternittagB6Sort">Eternittag B6 - Sort</option>
                    <option value="EternittagB6Mokkabrun)">Eternittag B6 - Mokka (brun)</option>
                    <option value="EternittagB6R�dbrun">Eternittag B6 - R�dbrun</option>
                    <option value="EternittagB6Teglr�d">Eternittag B6 - Teglr�d</option>
                    <option value="EternittagB7Gr�">Eternittag B7 - Gr�</option>
                    <option value="EternittagB7Sort">Eternittag B7 - Sort</option>
                    <option value="EternittagB7Mokka(brun)">Eternittag B7 - Mokka (brun)</option>
                    <option value="EternittagB7R�dbrun">Eternittag B7 - R�dbrun</option>
                    <option value="EternittagB7Teglr�d">Eternittag B7 - Teglr�d</option>
                    <option value="EternittagB7R�dflammet">Eternittag B7 - R�dflammet</option>

                </select>
                <select id="shed_width" name="shedwidth" style="display: none">
                    <option selected disabled>V�lg skurets bredde</option>
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
                <select id="shed_length" name="shedlength" style="display: none">
                    <option selected disabled>V�lg skurets l�ngde</option>
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

                <select id="shed_cover" name="walls" style="display: none">
                    <option selected disabled>V�lg skurets v�gbekl�dning</option>
                    <option value="thevoid">Void</option>  
                    <option value="bones">Suspicious Bones</option>
                    <option value="pasta">Spaghetti</option>
                    <option value="oldone">Eldritch Goo</option>
                </select>
                <br>
                <br>
                <h4>Fornavn</h4>
                <input type="text" name="firstname" required="required">
                <h4>Efternavn</h4>
                <input type="text" name="lastname" required="required">
                <h4>Adresse</h4>
                <input type="text" name="address" required="required">
                <h4>Postnummer</h4>
                <input type="text" name="zipcode" required="required">
                <h4>By</h4>
                <input type="text" name="city" required="required">
                <h4>Telefon</h4>
                <input type="text" name="phone" required="required">
                <h4>Email</h4>
                <input type="text" name="email" required="required">
                <h4>Kommentarer</h4>
                <input type="text" name="comments" >
                <br>
                <br>
                <button class="btn btn-primary btn-lg" type="submit" formaction="/FogProject/c/SendInformation" >Send foresp�rgsel</button>
            </form>
        </div>
    </body>
</html>
