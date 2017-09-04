t<%-- 
    Document   : newTache
    Created on : 5 juil. 2017, 16:31:01
    Author     : diary
--%>

<%@include file="template/default/header.jsp" %>
<link href="Archive/assets/css/bootstrap-toggle.min.css" rel="stylesheet">
<div class="col-md-12">
    <div class="x_panel">
        <%@include file="template/default/Erreur.jsp" %>
        <div class="x_title">
            <h2><a href="detailOffre?idOffre=<s:property value="getIdOffre()"/>" class="btn btn-default"><i class="fa fa-arrow-left"></i>  </a> Nouvelle Tache <s:property value="getType()"/></h2>
            
            <div class="clearfix"></div>
        </div>
        <div class="x_content">
            <div class="col-md-3 col-sm-12 col-xs-12">
                <div>
                    <div class="x_title">
                        <h2>Information de l'offre</h2>

                        <div class="clearfix"></div>
                    </div>                    
                    <h3> <s:property value="getOffre().getNomProjet()"/>  </h3><br>

                    <ul class="list-unstyled user_data">

                        <li><i class="fa fa-id-card-o"></i> <s:property value="getOffre().getTicket()"/> 
                        </li>
                        <li><i class="fa fa-map-marker user-profile-icon"></i> <s:property value="getOffre().getLocalisation()"/> 
                        </li>
                        <li>
                            <i class="fa fa-hourglass-end"></i>  <s:property value="getOffre().getDeadlineString()"/> 
                        </li>
                        <li>
                            <i class="fa fa-hourglass-start"></i> <s:property value="getOffre().getStringTravauxPrevu()"/> 
                        </li>


                    </ul>


                    <br>

                    <!-- start skills -->

                    <!-- end of skills -->


                </div>
            </div>
            <div class="col-md-9 col-sm-12 col-xs-12">
                <div id="catalogue">
                    <div class="x_title">
                        <h2>Catalogue | Hors Catalogue</h2>

                        <div class="clearfix"></div>
                        <div class="x_content">

                            <br>
                            <form action="saveTache" method="GET" id="demo-form2" data-parsley-validate="" class="form-horizontal form-label-left" novalidate="">
                                <input name="url" value="<s:property value="getUrlStatique()"/>" style="display:none">
                                <input name="idOffre" value="<s:property value="getOffre().getId()"/>" style="display:none" >
                                <input name="type" value="<s:property value="getType()"/>" style="display:none" >
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Reference <span class="required">*</span>
                                    </label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input  id="reference" name="reference" value="<s:property value="getReference()"/>" type="text" required="required" style="width:71%"class="form-control col-md-7 col-xs-12">
                                        <div class="input-group-btn">
                                            <a href="listeCatalogue?retour=newTache&idOffre=<s:property value="getIdOffre()"/>&type=<s:property value="getType()"/>&url=<s:property value="getUrlStatique()"/>" class="btn btn-dark" type="button" style="margin-left:  5px;margin-right:  0px" >C</a>
                                            <a href="listeHorsCatalogue?retour=newTache&idOffre=<s:property value="getIdOffre()"/>&type=<s:property value="getType()"/>&url=<s:property value="getUrlStatique()"/>" class="btn btn-dark" type="button" style="margin-left:  5px;" data-href="listeUser?idDernierArticle=0&amp;type=1">HC</a>
                                        </div>
                                                            
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Designation <span class="required">*</span>
                                    </label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input id="designation" name="designation" value="<s:property value="getDesignation()"/>" type="text" required="required" class="form-control col-md-7 col-xs-12" readonly="true">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">prix unitaire <span class="required">*</span>
                                    </label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input id="prixUnitaire" name="prixUnitaire" value="<s:property value="getPrixUnitaire()"/>" type="number" required="required" class="form-control col-md-7 col-xs-12" readonly="true">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="last-name">Unite <span class="required">*</span>
                                    </label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input id="unite" name="unite" name="unite" value="<s:property value="getUnite()"/>" type="String" class="form-control col-md-7 col-xs-12">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="last-name">Quantite <span class="required">*</span>
                                    </label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input name="quantite" type="number" value="<s:property value="getQuantite()"/>"required="required" class="form-control col-md-7 col-xs-12">
                                    </div>
                                </div>
                                <div id="admin" class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="last-name">Dossier administratif <span class="required">*</span>
                                    </label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input name="admin" type="checkbox" data-toggle="toggle" data-on="oui" data-off="non">
                                    </div>
                                </div>
                                
                                <div class="ln_solid"></div>
                                <div class="form-group">
                                    <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                                                                    
                                        <button type="submit" class="btn btn-success">Enregistrer</button>
                                    </div>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>



            </div>



        </div>
    </div>
</div>
<script>
    check();
    $("#reference").change(function () {
        check();
    });
    function myFunction(val) {
        alert("The input value has changed. The new value is: " + val);
    }
    function check() {

        var value = document.getElementById("reference").value;
        console.log(value);
        if (value !== "") {
            document.getElementById('designation').readOnly = true;
            document.getElementById('designation').value = "";
            document.getElementById('prixUnitaire').readOnly = true;
            document.getElementById('prixUnitaire').value = "";
            document.getElementById('unite').readOnly = true;
            document.getElementById('unite').value = "";
            document.getElementById('admin').style.display = "none";
        } else {
            document.getElementById('designation').readOnly = false;
            document.getElementById('prixUnitaire').readOnly = false;
            document.getElementById('unite').readOnly = false;
            document.getElementById('admin').style.display = "block";
        }

    }

</script>
<script src="Archive/assets/js/bootstrap-toggle.min.js"></script>
<%@include file="template/default/footer.jsp" %>