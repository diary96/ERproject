<%@include file="template/default/header.jsp" %>
<div class="x_panel">
  <%@include file="template/default/Erreur.jsp" %>
                <div class="x_title">
                    <h2><s:if test="getRetour()==null"></s:if><s:else><button onclick="javascript:history.back();" class="btn btn-default"><i class="fa fa-arrow-left"></i>  </button></s:else> Liste des catalogues <small></small></h2>
                    <ul class="nav navbar-right panel_toolbox">
<!--                      <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>-->                      
                        <li class="dropdown">
                          <a href="gestionCatalogue" ><i class="fa fa-plus"></i></a>
                        </li>
<!--                      <li><a class="close-link"><i class="fa fa-close"></i></a>-->
                    </ul>   
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form action="listeCatalogue" id="demo-form2" data-parsley-validate="" class="form-horizontal form-label-left" novalidate="" methode="GET">

                        <input name="idOffre" value="<s:property value="getIdOffre()"/>" type="text" style="display:none">
                        <input name="type" value="<s:property value="getType()"/>" type="text" style="display:none">
                        <input name="retour" value="<s:property value="getRetour()"/>" type="text" style="display:none">
                        <input name="url" value="<s:property value="getUrl()"/>" type="text" style="display:none"  >
                    
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="last-name">Reference : <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input name="reference" value="<s:property value="getReference()"/>" type="text" id="last-name" name="last-name" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="last-name">Designation : <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input name="designation" value="<s:property value="getDesignation()"/>" type="texte" id="last-name" name="last-name" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="last-name">Unite : <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input name="unite" value="<s:property value="getUnite()"/>"type="texte" id="last-name" name="last-name" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="last-name">Prix Unitaire Minimum : <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input value="<s:property value="getPrixUnitaire()" />" name="prixUnitaire" type="double" id="last-name" name="last-name" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="last-name">Prix Unitaire Maximum : <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input value="<s:property value="getFinPrixUnitaire()"/>" name="finPrixUnitaire" type="double" id="last-name" name="last-name" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>

                        <input value="1" name="pagination" type="texte" id="last-name" name="last-name" required="required" class="form-control col-md-7 col-xs-12" style="display:none">
                          
                        <div class="form-group">
                            <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                                <button type="submit" class="btn btn-success">Recherche</button>
                            </div>
                        </div>

                    </form>
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th >
                                    <a href="?order=id&idOffre=<s:property value="getIdOffre()"/>&url=<s:property value="getUrl()"/>&type=<s:property value="getType()"/>&retour=<s:property value="getRetour()"/>&pagination=<s:property value="%{#statu.index+1}" />&reference=<s:property value="getReference()"/>&unite=<s:property value="getUnite()"/>&designation=<s:property value="getDesignation()"/>&prixUnitaire=<s:property value="getPrixUnitaire()"/>&finPrixUnitaire=<s:property value="getFinPrixUnitaire()"/>">
                                        Reference
                                    </a>
                                </th>                       
                                <th>
                                    <a href="?order=designation&idOffre=<s:property value="getIdOffre()"/>&url=<s:property value="getUrl()"/>&type=<s:property value="getType()"/>&retour=<s:property value="getRetour()"/>&pagination=<s:property value="%{#statu.index+1}" />&reference=<s:property value="getReference()"/>&unite=<s:property value="getUnite()"/>&designation=<s:property value="getDesignation()"/>&prixUnitaire=<s:property value="getPrixUnitaire()"/>&finPrixUnitaire=<s:property value="getFinPrixUnitaire()"/>">
                                        Libell&eacute; du catalogue
                                    </a>
                                </th>
                                <th>
                                    <a href="?order=unite&idOffre=<s:property value="getIdOffre()"/>&url=<s:property value="getUrl()"/>&type=<s:property value="getType()"/>&retour=<s:property value="getRetour()"/>&pagination=<s:property value="%{#statu.index+1}" />&reference=<s:property value="getReference()"/>&unite=<s:property value="getUnite()"/>&designation=<s:property value="getDesignation()"/>&prixUnitaire=<s:property value="getPrixUnitaire()"/>&finPrixUnitaire=<s:property value="getFinPrixUnitaire()"/>">        
                                        Unite
                                    </a>
                                </th>
                                <th>
                                    <a href="?order=prixUnitaire&idOffre=<s:property value="getIdOffre()"/>&url=<s:property value="getUrl()"/>&type=<s:property value="getType()"/>&retour=<s:property value="getRetour()"/>&pagination=<s:property value="%{#statu.index+1}" />&reference=<s:property value="getReference()"/>&unite=<s:property value="getUnite()"/>&designation=<s:property value="getDesignation()"/>&prixUnitaire=<s:property value="getPrixUnitaire()"/>&finPrixUnitaire=<s:property value="getFinPrixUnitaire()"/>">                                   
                                        Prix Unitaire 
                                    </a>
                                </th>                            
                            </tr>
                        </thead>
                        <tbody>
                            <s:iterator value="catalogues" status="catalogue">
                                <tr class="clickable-row" data-href='<s:property value="getRetourUrl()"/>&reference=<s:property value="getAllReference()"/>'>
                                    <th scope="row"><s:property value="#catalogue.index+1"/></th>

                                    <th><s:property value="getAllReference()"/></th>
                                    <td><s:property value="getDesignation()"/></td>
                                    <td><s:property value="getUnite()"/></td>                              
                                    <td><s:property value="getPrixUnitaire()"/></td>                        
                                </tr>
                            </s:iterator>
                        </tbody>
                    </table>
                </div>
                    <div class="col-md-12 text-center">
                        <ul  class="pagination pagination-lg pager">


                            <s:iterator begin="1" end="%{parameter.getMax()}" status="statu">
                                <s:if test="%{#statu.index == parameter.getPage()-1}">
                                    <%@include file="template/default/pagination/catalogue/pagenumberActive.jsp" %>
                                </s:if>
                                <s:else>
                                    <%@include file="template/default/pagination/catalogue/pagenumber.jsp" %>
                                </s:else>

                            </s:iterator>


                        </ul>
                    </div>
            </div>

<script>
    jQuery(document).ready(function ($) {
        $(".clickable-row").dblclick(function () {
            window.location = $(this).data("href");
        });
    });
</script>

<%@include file="template/default/footer.jsp" %>