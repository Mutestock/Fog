<%-- 
    Document   : CarportDetails
    Created on : 24-04-2019, 10:33:30
    Author     : Lukas Bjørnvad
--%>

<%@page import="data.customExceptions.DataAccessException"%>
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
            <h3>og få et godt tilbud!</h3> 

            <%
                String error = (String) request.getAttribute("errormessage");
                String errormessage = "";
                if (error != null) {
                    switch (error) { 
                        case "IllegalArgumentException":
                            errormessage = "Tjek venligst, at alle felterne for personlige oplysninger indeholder tilladte værdier.";
                            break;
                        case "DataAccessException":
                            errormessage = "Der skete en alvorlig fejl i databasen. Kontakt venligst support.";
                            break;
                    }
                }
            %>
            <div class="alert">
                <span class="closebtn" onclick="this.parentElement.style.display = 'none';"><%=errormessage%></span>
            </div>

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
                    } else if (updateAvailableSheds())
                    {
                        document.getElementById("shed_width").style.display = "block";
                        document.getElementById("shed_length").style.display = "block";
                        document.getElementById("shed_cover").style.display = "block";
                    }
                }

                function updateAvailableSheds() {
                    var widthDropdown = document.getElementById("carport_width");
                    var width = widthDropdown.options[widthDropdown.selectedIndex].value;
                    var lengthDropdown = document.getElementById("carport_length");
                    var length = lengthDropdown.options[lengthDropdown.selectedIndex].value;

                    if (width === "Vælg bredde" || length === "Vælg længde") {
                        return false;
                    }
                    var backEaves = 5;
                    var widthEaves = 60;
                    var frontEaves = length * 0.15;

                    var shedWidthDropdown = document.getElementById("shed_width");
                    for (i = 1; i < shedWidthDropdown.options.length; i++) {
                        var shedWidth = shedWidthDropdown.options[i].value;
                        var disable = (width - widthEaves < shedWidth);
                        shedWidthDropdown.options[i].disabled = disable;
                    }
                    var shedLengthDropdown = document.getElementById("shed_length");
                    for (i = 1; i < shedLengthDropdown.options.length; i++) {
                        var shedLength = shedLengthDropdown.options[i].value;
                        var disable = (length - backEaves - frontEaves < shedLength);
                        shedLengthDropdown.options[i].disabled = disable;
                    }
                    return true;
                }
            </script> 

            <br><br>
            <form method = POST>
                <select id="carport_width" name="width" style="display: block" onchange="switchShed();">
                    <option selected disabled>Vælg bredde</option>
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

                <select id="carport_length" name="length" style="display: block" onchange="switchShed();">
                    <option selected disabled>Vælg længde</option>
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
                <br>

                <select id="roof_slope" name="slope" style="display: none">
                    <option selected disabled>Vælg taghældning</option>
                    <option value="15">15 grader</option>
                    <option value="20">20 grader</option>
                    <option value="25">25 grader</option>
                    <option value="30">30 grader</option>
                    <option value="35">35 grader</option>
                    <option value="40">40 grader</option>
                    <option value="45">45 grader</option>
                </select>

                <select id="flat_roof_type" name="roof" style="display: block">
                    <option selected disabled>Vælg fladt tag</option>
                    <option value="plasttrapezplader">Plasstrapezplader</option>
                </select>

                <select id="raised_roof_type" name="roof" style="display: none">
                    <option selected disabled>Vælg tag med rejsning</option>
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
                <br>
                <select id="shed_width" name="shedwidth" style="display: none">
                    <option selected disabled>Vælg skurets bredde</option>
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
                    <option selected disabled>Vælg skurets længde</option>
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
                    <option selected disabled>Vælg skurets vægbeklædning</option>
                    <option value="Trykimprægneret træ">Trykimprægneret træ</option>  
                    <option value="Skumpaneler">Skumpaneler</option>
                    <option value="Eternitplader">Eternitplader</option>
                    <option value="Fibercement">Fibercement</option>
                </select>
                <br>
                <br>

                <button class="btn btn-primary btn-lg" type="submit" formaction="/FogProject/c/ReviewEstimate" >Generer skitsetegning og prisestimat</button>
            </form>
        </div>
    </body>
</html>
