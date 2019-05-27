<%-- 
    Document   : CarportDetails
    Created on : 24-04-2019, 10:33:30
    Author     : Lukas Bjørnvad
--%>

<%@page import="java.util.LinkedList"%>
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
            
            <form method = POST>

            <h4>Tag:</h4>
            <input id="roofSwitch" name="isRaised" type="checkbox" data-toggle="toggle" data-on="Med rejsning" data-off="Fladt" onchange="switchRoof()">

            <h4>Redskabsskur:</h4>
            <input id="shedSwitch" name="hasShed" type="checkbox" data-toggle="toggle" data-on="Med" data-off="Uden" onchange="switchShed()">

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
            
                <select id="carport_width" name="width" style="display: block" onchange="switchShed();">
                    <option selected disabled>Vælg bredde</option>
                    <% LinkedList<Integer> widths = (LinkedList<Integer>) request.getAttribute("widths");
                        for (Integer width : widths) {
                            out.print("<option value=\"" + width + "\">" + width + " cm</option>");
                        }
                    %>
                </select>

                <select id="carport_length" name="length" style="display: block" onchange="switchShed();">
                    <option selected disabled>Vælg længde</option>
                    <% LinkedList<Integer> lengths = (LinkedList<Integer>) request.getAttribute("lengths");
                        for (Integer length : lengths) {
                            out.print("<option value=\"" + length + "\">" + length + " cm</option>");
                        }
                    %>
                </select>
                <br>

                <select id="roof_slope" name="slope" style="display: none">
                    <option selected disabled>Vælg taghældning</option>
                    <% LinkedList<Integer> roofSlopes = (LinkedList<Integer>) request.getAttribute("roofSlopes");
                        for (Integer slope : roofSlopes) {
                            out.print("<option value=\"" + slope + "\">" + slope + " grader</option>");
                        }
                    %>
                </select>

                <select id="flat_roof_type" name="roof" style="display: block">
                    <option selected disabled>Vælg fladt tag</option>
                    <% LinkedList<String> roofsFlat = (LinkedList<String>) request.getAttribute("roofsFlat");
                        for (String roof : roofsFlat) {
                            out.print("<option value=\""+roof+"\">"+roof+"</option>");
                        }
                    %>
                </select>

                <select id="raised_roof_type" name="roof" style="display: none">
                    <option selected disabled>Vælg tag med rejsning</option>
                    <% LinkedList<String> roofsRaised = (LinkedList<String>) request.getAttribute("roofsRaised");
                        for (String roof : roofsRaised) {
                            out.print("<option value=\""+roof+"\">"+roof+"</option>");
                        }
                    %>
                </select>
                <br>
                <select id="shed_width" name="shedwidth" style="display: none">
                    <option selected disabled>Vælg skurets bredde</option>
                    <% LinkedList<Integer> shedWidths = (LinkedList<Integer>) request.getAttribute("shedWidths");
                        for (Integer width : shedWidths) {
                            out.print("<option value=\"" + width + "\">" + width + " cm</option>");
                        }
                    %>
                </select>

                <select id="shed_length" name="shedlength" style="display: none">
                    <option selected disabled>Vælg skurets længde</option>
                    <% LinkedList<Integer> shedLengths = (LinkedList<Integer>) request.getAttribute("shedLengths");
                        for (Integer length : shedLengths) {
                            out.print("<option value=\""+length+"\">"+length+" cm</option>");
                        }
                    %>
                </select> 

                <select id="shed_cover" name="walls" style="display: none">
                    <option selected disabled>Vælg skurets vægbeklædning</option>
                    <% LinkedList<String> covers = (LinkedList<String>) request.getAttribute("shedCoverings");
                        for (String cover : covers) {
                            out.print("<option value=\""+cover+"\">"+cover+"</option>");
                        }
                    %>
                </select>
                <br>
                <br>

                <button class="btn btn-primary btn-lg" type="submit" formaction="/FogProject/c/ReviewEstimate" >Generer skitsetegning og prisestimat</button>
            </form>


            <%
                String errorK = (String) request.getAttribute("errormessage");
                String errormessageK = "";

                if (errorK == null) {
                    errormessageK = "";
                } else if (errorK.equals("DataAccess")) {
                    errormessageK = "<p style=\"color:red\">Der skete en alvorlig fejl i databasen. Kontakt venligst support.</p>";
                } else if (errorK.equals("InvalidInput")) {
                    errormessageK = "<p style=\"color:red\">Vælg venligst valide værdier i alle felterne. Vi kan ikke levere til postnumre mellem 3700 og 4000.</p>";
                } else {
                    errormessageK = "";
                }
                out.println(errormessageK);
            %>
        </div>
    </body>
</html>
