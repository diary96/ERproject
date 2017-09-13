t<%-- 
    Document   : newTache
    Created on : 5 juil. 2017, 16:31:01
    Author     : diary
--%>

<%@include file="template/default/header.jsp" %>
<link href="Archive/assets/css/bootstrap-toggle.min.css" rel="stylesheet">
<link href="Archive/assets/css/component.css" rel="stylesheet">

<link href="Archive/assets/css/normalize.css" rel="stylesheet">
<link href="Archive/assets/css/bootstrap-toggle.min.css" rel="stylesheet">
<div class="col-md-12">
    <div class="x_panel">
        <%@include file="template/default/Erreur.jsp" %>
        <div class="x_title">
            <h2><a href="listeArchive?idOffre=<s:property value="getIdOffre()"/>" class="btn btn-default"><i class="fa fa-arrow-left"></i>  </a> Nouvelle Tache <s:property value="getType()"/></h2>

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
                        <h2>Archive</h2>

                        <div class="clearfix"></div>
                        <div class="x_content">

                            <br>
                            <form action="saveArchive" method="POST" id="demo-form2" data-parsley-validate="" class="form-horizontal form-label-left" novalidate="" enctype="multipart/form-data">

                                <input name="idOffre" value="<s:property value="getOffre().getId()"/>" type="hidden" >
                                <input name="referenceInterieure" value="<s:property value="getReferenceInterieure()"/>" type="hidden" >

                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Reference <span class="required">*</span>
                                    </label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input  id="reference" name="reference" value="<s:property value="getReference()"/>" type="text" required="required" class="form-control col-md-7 col-xs-12">


                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Type d'archive <span class="required">*</span>
                                    </label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">

                                        <select id="heard" name="idType" class="id_100 form-control" required="">
                                            <s:iterator value="getTypeFichier()">
                                               
                                                <option <s:if test="getId()==getIdType()">selected=""</s:if> value='<s:property value="getId()"/>'><s:property value="getNomType()"/></option>
                                            </s:iterator>
                                        </select>

                                    </div>
                                </div>
                                <div class="form-group">

                                    <input type="file" name="archiveF" id="file-6" class="inputfile inputfile-5" data-multiple-caption="{count} files selected" multiple="" style="display:none">
                                    <label for="file-6"><figure><svg xmlns="http://www.w3.org/2000/svg" width="20" height="17" viewBox="0 0 20 17"><path d="M10 0l-5.2 4.9h3.3v5.1h3.8v-5.1h3.3l-5.2-4.9zm9.3 11.5l-3.2-2.1h-2l3.4 2.6h-3.5c-.1 0-.2.1-.2.1l-.8 2.3h-6l-.8-2.2c-.1-.1-.1-.2-.2-.2h-3.6l3.4-2.6h-2l-3.2 2.1c-.4.3-.7 1-.6 1.5l.6 3.1c.1.5.7.9 1.2.9h16.3c.6 0 1.1-.4 1.3-.9l.6-3.1c.1-.5-.2-1.2-.7-1.5z"></path></svg></figure> <span>T&eacute;l&eacute;charger fichier</span></label>

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
<script src="Archive/assets/gentella/vendors/jquery.tagsinput/src/jquery.tagsinput.js"></script>
<script src="Archive/assets/js/bootstrap-toggle.min.js"></script>
<script src="Archive/assets/js/custom-file-input.js"></script>

<script src="Archive/assets/gentella/vendors/jquery.tagsinput/src/jquery.tagsinput.js"></script>


<script src="Archive/assets/js/bootstrap-toggle.min.js"></script>
<%@include file="template/default/footer.jsp" %>