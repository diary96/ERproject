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
            <h2><a href="listeUser" class="btn btn-default"><i class="fa fa-arrow-left"></i>  </a> Gestion d'utilisateur </h2>
            
            <div class="clearfix"></div>
        </div>
        <div class="x_content">
            
            <div class="col-md-9 col-sm-12 col-xs-12">
                <div id="catalogue">
                    <div class="x_title">
                        <h2><s:property value="getInformation()"/></h2>

                        <div class="clearfix"></div>
                        <div class="x_content">

                            <br>
                            <form action="saveUser" method="POST" id="demo-form2" data-parsley-validate="" class="form-horizontal form-label-left" novalidate="">
                                <input type="hidden" name="idUser" value="<s:property value="getIdUser()"/>">
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Nom <span class="required">*</span>
                                    </label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input  id="reference" name="nom" value="<s:property value="getNom()"/>" type="text" required="required" class="form-control col-md-7 col-xs-12" >                            
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Prenom <span class="required">*</span>
                                    </label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input id="designation" name="prenom" value="<s:property value="getPrenom()"/>" type="text" required="required" class="form-control col-md-7 col-xs-12" >
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">CIN <span class="required">*</span>
                                    </label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input id="designation" name="CIN" value="<s:property value="getCIN()"/>" type="text" required="required" class="form-control col-md-7 col-xs-12" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Date de naissance <span class="required">*</span>
                                    </label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input id="designation" name="dateNaissance" value="<s:property value="getDateNaissance()"/>" type="date" required="required" class="form-control col-md-7 col-xs-12" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Date d'embauche <span class="required">*</span>
                                    </label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input id="designation" name="dateEmbauche" value="<s:property value="getDateEmbauche()"/>" type="date" required="required" class="form-control col-md-7 col-xs-12" >
                                    </div>
                                </div>
                                        <div class="form-group" <s:if test="getUser().getNiveau()<5">style="display:none"</s:if>>
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Niveau d'accès <span class="required">*</span>
                                    </label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <select name="niveau" class="form-control">
                                            <option value='0' <s:if test="getNiveau()==0">selected="true"</s:if>>Acc&egrave;s simple</option>
                                            <option value='1' <s:if test="getNiveau()==1">selected="true"</s:if>>Acc&egrave;s d'etat de l'offre</option>
                                            <option value='2' <s:if test="getNiveau()==2">selected="true"</s:if>>Acc&egrave;s au gestion des catalogues et type d'archive</option>
                                            <option value='3' <s:if test="getNiveau()==3">selected="true"</s:if>>Acc&egrave;s &agrave; l'historique des offres</option>
                                            <option value='4' <s:if test="getNiveau()==4">selected="true"</s:if>>Acc&egrave;s au paiement des factures</option>
                                            <option value='5' <s:if test="getNiveau()==5">selected="true"</s:if>>Acc&egrave;s au gestion d'utilisateur</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Matricule <span class="required">*</span>
                                    </label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input id="designation" name="matricule" value="<s:property value="getMatricule()"/>" type="text" required="required" class="form-control col-md-7 col-xs-12" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Mot de passe <span class="required">*</span>
                                    </label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input id="designation" name="password" value="" type="password" required="required" class="form-control col-md-7 col-xs-12" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Confirmer le mot de passe <span class="required">*</span>
                                    </label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input id="designation" name="confirmer" value="" type="password" required="required" class="form-control col-md-7 col-xs-12" >
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

<script src="Archive/assets/js/bootstrap-toggle.min.js"></script>
<%@include file="template/default/footer.jsp" %>