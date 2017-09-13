t<%-- 
    Document   : newTache
    Created on : 5 juil. 2017, 16:31:01
    Author     : diary
--%>

<%@include file="template/default/header.jsp" %>
<link href="Archive/assets/css/bootstrap-toggle.min.css" rel="stylesheet">
<link href="Archive/assets/css/component.css" rel="stylesheet">

<link href="Archive/assets/css/normalize.css" rel="stylesheet">
<link href="Archive/assets/gentella/vendors/jquery.tagsinput/dist/jquery.tagsinput.min.css" rel="stylesheet" type="text/css">


<div class="col-md-12">
    <div class="x_panel">
        <%@include file="template/default/Erreur.jsp" %>
        <div class="x_title">
            <h2>Ventilation de paiement</h2>

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
                        <h2>Remplissez les données nécessaires</h2>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">

                        <br>
                        <form action="saveVentilation" method="POST" id="bootstrapTagsInputForm" data-parsley-validate="" class="form-horizontal form-label-left" novalidate="" enctype="multipart/form-data">                                
                            <div class="x_title">
                                <h2>Information du bon de commande</h2>
                                <div class="clearfix"></div>
                            </div>
                            <input name="idOffre" value="<s:property value="getOffre().getId()"/>" style="display:none" >
                            <input name="type" value="<s:property value="getType()"/>" style="display:none" >
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Service : <span class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input  value="<s:property value="getService()"/>" id="reference" name="service" type="text" required="required" class="form-control col-md-7 col-xs-12">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">NIF <span class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input  id="reference" value="<s:property value="getNif()"/>" name="nif" type="text" required="required" class="form-control col-md-7 col-xs-12">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Stat <span class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input id="designation" value="<s:property value="getStat()"/>" name="stat" type="text" required="required" class="form-control col-md-7 col-xs-12" >
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Numero <span class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input id="prixUnitaire" value="<s:property value="getNumero()"/>" name="numero" type="text" required="required" class="form-control col-md-7 col-xs-12" >
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="last-name">Reference Int&eacute;rieure <span class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input id="unite" value="<s:property value="getReferenceInterieure()"/>" name="referenceInterieure" name="unite" type="String" class="form-control col-md-7 col-xs-12">
                                </div>
                            </div>
                            <div class="form-group">

                                <input type="file" name="bc" id="file-6" class="inputfile inputfile-5" data-multiple-caption="{count} files selected" multiple="" style="display:none">
                                <label for="file-6"><figure><svg xmlns="http://www.w3.org/2000/svg" width="20" height="17" viewBox="0 0 20 17"><path d="M10 0l-5.2 4.9h3.3v5.1h3.8v-5.1h3.3l-5.2-4.9zm9.3 11.5l-3.2-2.1h-2l3.4 2.6h-3.5c-.1 0-.2.1-.2.1l-.8 2.3h-6l-.8-2.2c-.1-.1-.1-.2-.2-.2h-3.6l3.4-2.6h-2l-3.2 2.1c-.4.3-.7 1-.6 1.5l.6 3.1c.1.5.7.9 1.2.9h16.3c.6 0 1.1-.4 1.3-.9l.6-3.1c.1-.5-.2-1.2-.7-1.5z"></path></svg></figure> <span>T&eacute;l&eacute;charger bon de commande (pdf uniquement)</span></label>

                            </div>
                            <div class="x_title">
                                <h2>Ventilation de paiement</h2>
                                <div class="clearfix"></div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="last-name">Pourcentage <span class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input id="pourcentage" type="number" required="required" class="form-control col-md-7 col-xs-12">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="last-name">Date d'&eacute;ch&eacute;ance <span class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input id="date" type="date" value="<s:property value="getDateToday()"/>" required="required" class="form-control col-md-7 col-xs-12">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="last-name">Type de paiement <span class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <select id="typeP" class="id_100 form-control">
                                        <option value="Mvola">Mvola</option>
                                        <option value="Chèque">Chèque</option>
                                        <option value="Virement">Virement</option>
                                    </select>
                                    <!--<input id="typeP" type="text" required="required" class="form-control col-md-7 col-xs-12">-->
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="last-name">Nom de paiement <span class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input id="nameP" type="text" required="required" class="form-control col-md-7 col-xs-12">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                                    <button class="btn btn-success" type="button" onclick="translat()">Ajouter</button>     
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">donnee de ventillation (%/YYYY-MM-JJ/type/nom paiement)</label>
                                <div class="col-md-9 col-sm-9 col-xs-12">
                                    <input id="tags_1" name="ventillation" type="text" class="tags form-control" value="<s:property value="getVentillation()"/>" />
                                    <div id="suggestions-container" style="position: relative; float: left; width: 250px; margin: 10px;"></div>
                                </div>
                            </div>


                            <div class="ln_solid"></div>
                            <div class="form-group">
                                <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                                    <a href="detailOffre?idOffre=<s:property value="getIdOffre()"/>" class="btn btn-danger" type="button">Annuler</a>                                      
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

<script src="Archive/assets/gentella/vendors/jquery.tagsinput/src/jquery.tagsinput.js"></script>
<script src="Archive/assets/js/bootstrap-toggle.min.js"></script>
<script src="Archive/assets/js/custom-file-input.js"></script>

<script src="Archive/assets/gentella/vendors/jquery.tagsinput/src/jquery.tagsinput.js"></script>
<script>
    function translat() {
        var date = document.getElementById("date");
        var pourcentage = document.getElementById("pourcentage");
        var typeP = document.getElementById('typeP');
        var nameP = document.getElementById('nameP');
        var data = document.getElementById('tags_1');
        if(pourcentage.value=="")pourcentage.value = "none";
        
        if(date.value=="")date.value = "none";
        if(nameP.value=="")nameP.value = "none";

        var newResult = pourcentage.value + "/" + date.value + "/" + typeP.options[typeP.selectedIndex].value + "/" + nameP.value;
        $('#tags_1').addTag(newResult);


        document.getElementById("pourcentage").value = "";
        
        document.getElementById('nameP').value = "";

    }
</script>
<%@include file="template/default/footer.jsp" %>
