<%-- 
    Document   : newPhoto
    Created on : 25 juil. 2017, 15:10:08
    Author     : diary
--%>
<%@include file="template/default/header.jsp" %>
<link href="Archive/assets/css/bootstrap-toggle.min.css" rel="stylesheet">

<link rel="stylesheet" type="text/css" href="Archive/assets/css/normalize.css">
<link rel="stylesheet" type="text/css" href="Archive/assets/css/component.css">

<div class="col-md-12">
    <div class="x_panel">
        <%@include file="template/default/Erreur.jsp" %>
        <div class="x_title">
            <h2><a href="gestionPhoto?reference=<s:property value="getReferenceTravaux()"/>" class="btn btn-default"><i class="fa fa-arrow-left"></i>  </a>Nouvelle Photo</h2>

            <div class="clearfix"></div>
        </div>
        <div class="x_content">
            <div class="col-md-3 col-sm-12 col-xs-12">
                <div>
                    <div class="x_title">
                        <h2> Information du Travaux</h2>

                        <div class="clearfix"></div>
                    </div>                    


                    <ul class="list-unstyled user_data">

                        <li><i> Reference : </i> <s:property value="travaux.getAllReference()"/> 
                        </li>
                        <li><i> Nom : </i> <s:property value="travaux.getCatalogue().getDesignation()"/> 
                        </li>
                        <li>
                            <i> Quantite :</i>  <s:property value="travaux.getQuantite()"/> 
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
                        <h2>Detail photo</h2>

                        <div class="clearfix"></div>
                        <div class="x_content">

                            <br>
                            <form action="savePhoto" method="POST" id="demo-form2" data-parsley-validate="" class="form-horizontal form-label-left" novalidate="" enctype="multipart/form-data">


                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Latitude <span class="required">*</span>
                                    </label>
                                    <input name="referenceTravaux" value="<s:property value="getReferenceTravaux()"/>" style="display:none">
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input value="<s:property value="getLatitude()" />" id="designation" name="latitude" type="text" required="required" class="form-control col-md-7 col-xs-12" >
                                        <select class="form-control" name="latitudeM">
                                            <option value="S">SUD</option>
                                            <option value="N">NORD</option>
                                            
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Longitude <span class="required">*</span>
                                    </label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input value="<s:property value="getLongitude()" />" id="designation" name="longitude" type="text" required="required" class="form-control col-md-7 col-xs-12" >
                                        <select class="form-control" name="longitudeM">
                                            <option value="E">EST</option>
                                            <option value="O">OUEST</option>
                                            
                                        </select>
                                    </div>
                                </div>

                                
                                <div class="form-group">


                                    <div class="col-md-4 col-sm-4 col-xs-12">
                                        <input type="file" name="photoFile" id="file-1" class="inputfile inputfile-1" data-multiple-caption="{count} files selected" multiple="" style="display:none;" onchange="readURL(this);">
                                        <label for="file-1"><svg xmlns="http://www.w3.org/2000/svg" width="20" height="17" viewBox="0 0 20 17"><path d="M10 0l-5.2 4.9h3.3v5.1h3.8v-5.1h3.3l-5.2-4.9zm9.3 11.5l-3.2-2.1h-2l3.4 2.6h-3.5c-.1 0-.2.1-.2.1l-.8 2.3h-6l-.8-2.2c-.1-.1-.1-.2-.2-.2h-3.6l3.4-2.6h-2l-3.2 2.1c-.4.3-.7 1-.6 1.5l.6 3.1c.1.5.7.9 1.2.9h16.3c.6 0 1.1-.4 1.3-.9l.6-3.1c.1-.5-.2-1.2-.7-1.5z"></path></svg> Telecharger photo</label>

                                    </div>
                                    <div class="col-md-8 col-sm-8 col-xs-12">
                                        <img class="img-responsive" id="blah" src="#" alt="your image" style="display:none"/>
                                    </div>
                                </div>


                                

                                <div class="ln_solid"></div>
                                <div class="form-group">
                                    <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                                        <button class="btn btn-danger" type="button">Annuler</button>                                      
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
<script>
                                            function readURL(input) {
                                                if (input.files && input.files[0]) {
                                                    var reader = new FileReader();

                                                    reader.onload = function (e) {
                                                        $('#blah')
                                                                .attr('src', e.target.result)


                                                    };
                                                    document.getElementById("blah").style.display = "block";
                                                    reader.readAsDataURL(input.files[0]);
                                                }
                                            }
</script>
<%@include file="template/default/footer.jsp" %>
