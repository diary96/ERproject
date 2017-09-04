<%-- 
    Document   : addPV
    Created on : 19 juin 2017, 11:28:32
    Author     : diary
--%>

<%@include file="template/default/header.jsp" %>
<div class="row">
    <div class="col-md-12">
        <div class="x_panel">
            <%@include file="template/default/Erreur.jsp" %>
            <div class="x_title">
                <h2><a href="javascript:window.close()" class="btn btn-default"><i class="fa fa-arrow-left"></i>  </a> Gestion des photos <small>Reference Travaux : <s:property value="getReference()"/></small></h2>
                <ul class="nav navbar-right panel_toolbox">
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                    </li>
                    <li class="dropdown">
                        <a href="newPhoto?referenceTravaux=<s:property value="getReference()"/>" ><i class="fa fa-plus"></i></a>

                    </li>
                    <li><a href="javascript:window.close()"><i class="fa fa-close"></i></a>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                <div class="row">


                    <s:iterator value="photos">
                        <div class="col-md-4 col-sm-4 col-xs-12 profile_details">
                            <div class="well profile_view">
                                <div class="col-sm-12">
                                    <h4 class="brief"><a href="deletePhoto?referenceTravaux=<s:property value="getReferenceTravaux()"/>&referencePhoto=<s:property value="getAllReference()"/>"><i class="fa fa-trash-o"> <s:property value="getAllReference()"/> </i></a> </h4>
                                      <div class="left col-xs-7">
                                        <img class="img-responsive" src="<s:property value="getPathPhoto()"/>">
                                    </div>
                                </div>
                                <div class="col-xs-12 bottom text-center">
                                    Longitude : <s:property value="getLongitude()"/> <br> Latitude : <s:property value="getLatitude()"/>
                                </div>
                            </div>
                        </div>
                    </s:iterator>





                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="template/default/footer.jsp" %>
