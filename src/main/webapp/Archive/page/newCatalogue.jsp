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
            <h2><a href="listeCatalogue" class="btn btn-default"><i class="fa fa-arrow-left"></i>  </a>Gestion de catalogue</h2>
            
            <div class="clearfix"></div>
        </div>
        <div class="x_content">
            
            <div class="col-md-9 col-sm-12 col-xs-12">
                <div id="catalogue">
                    <div class="x_title">
                        <h2>Catalogue</h2>

                        <div class="clearfix"></div>
                        <div class="x_content">

                            <br>
                            <form action="saveCatalogue" method="POST" id="demo-form2" data-parsley-validate="" class="form-horizontal form-label-left" novalidate="">
                                <input name="url" value="<s:property value="getUrl()"/>" style="display:none">
                                
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Reference <span class="required">*</span>
                                    </label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input  id="reference" value="<s:property value="getReference()"/>" name="reference" type="text" required="required" class="form-control col-md-7 col-xs-12" readOnly="true">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Designation <span class="required">*</span>
                                    </label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input id="designation" value="<s:property value="getDesignation()"/>" name="designation" type="text" required="required" class="form-control col-md-7 col-xs-12">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">prix unitaire <span class="required">*</span>
                                    </label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input id="prixUnitaire" value="<s:property value="getPrixUnitaire()"/>" name="prixUnitaire" type="number" required="required" class="form-control col-md-7 col-xs-12" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="last-name">Unite <span class="required">*</span>
                                    </label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input id="unite" value="<s:property value="getUnite()"/>" name="unite" type="String" class="form-control col-md-7 col-xs-12">
                                    </div>
                                </div>
                                
                                
                                <div class="ln_solid"></div>
                                <div class="form-group">
                                    <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                                        <a href="listeCatalogue" class="btn btn-danger" type="button">Annuler</a>                                      
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

<script src="Archive/assets/js/bootstrap-toggle.min.js"></script>
<%@include file="template/default/footer.jsp" %>