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
            <h2><a href="detailOffre?idOffre=<s:property value="getIdOffre()"/>" class="btn btn-default"><i class="fa fa-arrow-left"></i></a>Parametre <s:property value="getType()"/></h2>
            
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
                        <h2>R&eacute;trograder l'offre, choisir le niveau</h2>

                        <div class="clearfix"></div>
                        <div class="x_content">

                            <br>
                            <form action="saveDowngrade" method="POST" id="demo-form2" data-parsley-validate="" class="form-horizontal form-label-left" novalidate="">
                                
                                <input name="idOffre" value="<s:property value="getOffre().getId()"/>" style="display:none" >
                                
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="last-name">Etape : <span class="required">*</span>
                                    </label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <select name="statu" class="form-control">
                                            <option value="0">Appel d'offre</option>
                                            <s:if test="getOffre().getStatu()>1"> <option value="1" >Soumission</option></s:if>
                                            <s:if test="getOffre().getStatu()>2"> <option value="2" >Travaux</option></s:if>
                                            <s:if test="getOffre().getStatu()>3"> <option value="3" >Proces Verbal</option></s:if>
                                        </select>
                                    </div>
                                </div>
                               
                              
                               
                               
                                
                                <div class="ln_solid"></div>
                                <div class="form-group">
                                    <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                                                                              
                                        <button type="submit" class="btn btn-success">Sauvegarder</button>
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