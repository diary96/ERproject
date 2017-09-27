<%@include file="template/default/header.jsp" %>
<div class="x_panel">
  <%@include file="template/default/Erreur.jsp" %>
                  <div class="x_title">
                      <h2>Liste des offres</h2>
                    <ul class="nav navbar-right panel_toolbox">
<!--                      <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>-->
                      </li>
                      <li class="dropdown">
                        <a href="newOffre" ><i class="fa fa-plus"></i></a>
                       
                      </li>
<!--                      <li><a class="close-link"><i class="fa fa-close"></i></a>-->
                      </li>
                    </ul>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                      <form action="accueil" id="demo-form2" data-parsley-validate="" class="form-horizontal form-label-left" novalidate="" methode="GET">
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="last-name">Reference : <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input name="reference" value="<s:property value="getReference()"/>" type="text" id="last-name" name="last-name" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="last-name">Libell&eacute; de l'archive : <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input name="nomProjet" value="<s:property value="getNomProjet()"/>" type="texte" id="last-name" name="last-name" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                          
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="last-name">Num&eacute;ro du ticket : <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                               <input name="ticket" value="<s:property value="getTicket()"/>"type="texte" id="last-name" name="last-name" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="type">Nom du departement <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select id="heard" name="nomType"class="form-control" required="">
                                    <option value="">Tout</option>
                                    <s:iterator value="typeOffres">            
                                        <option value="<s:property value="getId()"/>"><s:property value="getNom()"/></option>
                                    </s:iterator>
                                    <
                                </select>

                            </div>
                        </div>  
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="last-name">Etape : <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select name="statu" class="form-control">
                                    <option value="none" <s:if test="getStatu()==none">selected="true"</s:if>>Tout</option>
                                    <option value="0" <s:if test="getStatu()==0">selected="true"</s:if>>Appel d'offre</option>
                                    <option value="1" <s:if test="getStatu()==1">selected="true"</s:if>>Soumission</option>
                                    <option value="2" <s:if test="getStatu()==2">selected="true"</s:if>>Travaux</option>
                                    <option value="3" <s:if test="getStatu()==3">selected="true"</s:if>>Proces Verbal</option>
                                    <option value="4" <s:if test="getStatu()==4">selected="true"</s:if>>Facturation</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="last-name">Etat : <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select name="isClose" class="form-control">
                                    <option value="">Tout</option>
                                    <option value="true">Cloturé</option>
                                    <option value="false">Ouvert</option>                  
                                </select>
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
                          <th>Reference</th>                       
                          <th>Libell&eacute; de l'archive</th>
                          <th>Nom du departement</th>
                          <th>Numero du ticket </th>                         
                          <th>Etape </th>                         
                          <th>Etat </th>
                          
                        </tr>
                      </thead>
                      <tbody>
                          <s:iterator value="offres" status="offre">
                              <tr class="clickable-row" data-href='detailOffre?idOffre=<s:property value="getId()"/>'>
                                <th scope="row"><s:property value="#offre.index+1"/></th>
                                
                                <th><a href="detailOffre?idOffre=<s:property value="getId()"/>"><s:property value="getAllReference()"/></a></th>
                                <td><s:property value="getNomProjet()"/></td>
                                <td><s:property value="getTypeOffre().getNom()"/></td>                              
                                <td><s:property value="getTicket()"/></td>                        
                                <!--<td><buttons type="button" class="btn btn-dark btn-xs"><s:property value="getStatuString()"/></button></td>-->             
                                <s:if test="getStatu()==0" >
                                    <td><button type="button" class="btn btn-default btn-xs"><s:property value="getStatuString()"/></button></td>
                                </s:if>
                                <s:if test="getStatu()==1" >
                                    <td><button type="button" class="btn btn-primary btn-xs"><s:property value="getStatuString()"/></button></td>
                                </s:if>
                                <s:if test="getStatu()==2" >
                                    <td><button type="button" class="btn btn-info btn-xs"><s:property value="getStatuString()"/></button></td>
                                </s:if>
                                <s:if test="getStatu()==3" >
                                    <td><button type="button" class="btn btn-warning btn-xs"><s:property value="getStatuString()"/></button></td>
                                </s:if>
                                <s:if test="getStatu()==4" >
                                    <td><button type="button" class="btn btn-success btn-xs"><s:property value="getStatuString()"/></button></td>
                                </s:if>
                                
                                <s:if test="getClose()==true">
                                    <td><button type="button" class="btn btn-danger btn-xs">Clotur&eacute;</button></td>
                                </s:if>
                                <s:else>
                                    <td><button type="button" class="btn btn-success btn-xs">Ouvert</button></td>
                                </s:else>
                                
                                <td><a href="gestionHistorique?idOffre=<s:property value="getId()" />" class="btn btn-primary btn-xs">Historique</a></td>
                                <td> <a href="listeArchive?idOffre=<s:property value="getId()" />" class="btn btn-dark btn-xs"  >Gestion des fichiers</button></a>
                                <td> <button id="<s:property value="getId()" />" class="supprimer btn btn-danger btn-xs"  >Supprimer</button></td>

                            </tr>
                              
                          </s:iterator>
                      </tbody>
                    </table>

                  </div>
                </div>

<script>
    jQuery(document).ready(function ($) {
        $(".clickable-row").dblclick(function () {
            window.location = $(this).data("href");
        });
    });
</script>
<script>
    jQuery(document).ready(function ()
    {
        $('.supprimer').on('click', function ()
        {
            if (confirm("Voulez-vous vraiment supprimer l'offre OFF"+this.getAttribute('id')+"?")) {
                window.location.replace("deleteReflect?reference=OFF"+this.getAttribute('id')+"&url=accueil");
                
            }
        });
    });
</script>
<%@include file="template/default/footer.jsp" %>