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
            <h2>Generer Proces Verbal <s:property value="getType()"/></h2>
            
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
                        <h2>Information P.V</h2>

                        <div class="clearfix"></div>
                        <div class="x_content">

                            <br>
                            <form action="downloadPv" method="GET" id="demo-form2" data-parsley-validate="" class="form-horizontal form-label-left" novalidate="">
                                
                                <input name="idOffre" value="<s:property value="getOffre().getId()"/>" style="display:none" >
                                
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="date">Date <span class="required">*</span>
                                    </label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input  id="reference" value="<s:property value="getDate()"/>" name="date" type="date" required="required" class="form-control col-md-7 col-xs-12">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="er">Responsable E.R <span class="required">*</span>
                                    </label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input id="er" value="<s:property value="getEr()"/>" name="er" type="text" required="required" class="form-control col-md-7 col-xs-12" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Responsable Telma <span class="required">*</span>
                                    </label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input id="telma" value="<s:property value="getTelma()"/>" name="telma" type="text" required="required" class="form-control col-md-7 col-xs-12" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Lieu <span class="required">*</span>
                                    </label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input id="lieu" value="Antananarivo" name="lieu" type="text" required="required" class="form-control col-md-7 col-xs-12" >
                                    </div>
                                </div>
                               
                                
                                <div class="ln_solid"></div>
                                <div class="form-group">
                                    <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                                        <a href="detailOffre?idOffre=<s:property value="getIdOffre()"/>" class="btn btn-dark" type="button">Retour</a>                                      
                                        <a  class="save btn btn-primary">Mettre par default</a>
                                        <button type="submit" class="btn btn-success">Generer PV</button>
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
    jQuery(document).ready(function ()
    {
        $('.save').on('click', function ()
        {
            if (confirm("Voulez-vous vraiment sauvegarder ses parametres comme par defaut?")) {
                window.location.replace("saveParametre?idOffre=<s:property value="getIdOffre()"/>&url=loadPv&er="+document.getElementById('er').value+"&telma="+document.getElementById('telma').value);
                
            }
        });
    });
</script>
<script src="Archive/assets/js/bootstrap-toggle.min.js"></script>
<%@include file="template/default/footer.jsp" %>