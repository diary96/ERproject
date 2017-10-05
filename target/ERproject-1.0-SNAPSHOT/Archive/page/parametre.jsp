t<%-- 
    Document   : newTache
    Created on : 5 juil. 2017, 16:31:01
    Author     : diary
--%>

<%@include file="template/default/header.jsp" %>
<div class="col-md-12">
    <div class="x_panel">
        <%@include file="template/default/Erreur.jsp" %>
        <div class="x_title">
            <h2><a href="<s:property value="getUrl()"/>?idOffre=<s:property value="getIdOffre()"/>" class="btn btn-default"><i class="fa fa-arrow-left"></i></a>Parametre <s:property value="getType()"/></h2>
            
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
                        <h2>Modifier les parametre</h2>

                        <div class="clearfix"></div>
                        <div class="x_content">

                            <br>
                            <form action="updateTache" method="GET" id="demo-form2" data-parsley-validate="" class="form-horizontal form-label-left" novalidate="">
                                <input name="url" value="<s:property value="getUrlStatique()"/>" style="display:none">
                                <input name="idOffre" value="<s:property value="getOffre().getId()"/>" style="display:none" >
                                <input name="type" value="<s:property value="getType()"/>" style="display:none" >
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">TVA (en %)<span class="required">*</span>
                                    </label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input  id="reference" value="<s:property value="getTva()"/>" name="tva" type="number" required="required" class="form-control col-md-7 col-xs-12">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Remise (en %)<span class="required">*</span>
                                    </label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input value="<s:property value="getRemise()"/>" id="designation" name="remise" type="number" required="required" class="form-control col-md-7 col-xs-12">
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

  
<%@include file="template/default/footer.jsp" %>