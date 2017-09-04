<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%@include file="template/default/header.jsp" %>
<link href="Archive/assets/css/bootstrap-toggle.min.css" rel="stylesheet">
<div class="col-md-12">
    <div class="x_panel">
        <%@include file="template/default/Erreur.jsp" %>
        <div class="x_title">
            <h2><a href="listeTypeFichier" class="btn btn-default"><i class="fa fa-arrow-left"></i>  </a>Gestion de type d'archive</h2>
            
            <div class="clearfix"></div>
        </div>
        <div class="x_content">
            
            <div class="col-md-9 col-sm-12 col-xs-12">
                <div id="catalogue">
                    <div class="x_title">
                        <h2>Type d'archive</h2>

                        <div class="clearfix"></div>
                        <div class="x_content">

                            <br>
                            <form action="saveTypeFichier" method="POST" id="demo-form2" data-parsley-validate="" class="form-horizontal form-label-left" novalidate="">
                                <input value="<s:property value="getIdType()"/>" type='hidden' name="idType">
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Nom du type d'archive <span class="required">*</span>
                                    </label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input  id="reference" value="<s:property value="getNomType()"/>" name="nomType" type="text" required="required" class="form-control col-md-7 col-xs-12">
                                    </div>
                                </div>
                                
                                
                                
                                <div class="ln_solid"></div>
                                <div class="form-group">
                                    <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                                        <a href="#" class="btn btn-danger" type="button">Annuler</a>                                      
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
