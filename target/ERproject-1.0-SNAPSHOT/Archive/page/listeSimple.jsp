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
                          <input type="hidden" name="orderOld" value=""/>
                          <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="last-name">Reference : 
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input name="reference" id="reference" value="<s:property value="getReference()"/>" type="text"  name="last-name" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="last-name">Libell&eacute; de l'archive : 
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input name="nomProjet" id="nomProjet" value="<s:property value="getNomProjet()"/>" type="texte" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                          
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="last-name">Num&eacute;ro du ticket : 
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                               <input name="ticket" value="<s:property value="getTicket()"/>"type="texte" id="ticket" name="last-name" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="type">Nom du departement 
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select id="nomType" name="nomType"class="form-control" required="">
                                    <option value="none" <s:if test="getNomType()==none">selected="true"</s:if>>Tout</option>
                                    <s:iterator value="typeOffres">            
                                        <option value="<s:property value="getId()"/>" <s:if test="getNomType()==getId()">selected="true"</s:if>><s:property value="getNom()"/></option>
                                    </s:iterator>
                                    
                                </select>

                            </div>
                        </div>  
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="last-name">Etape : <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select name="statu" id="statu" class="form-control">
                                    <option value="none" <s:if test="getStatu().equalsIgnoreCase(none)||getStatu().equalsIgnoreCase('')">selected="true"</s:if>>Tout</option>
                                    <option value="0" <s:if test="getStatu()==0">selected="true"</s:if>>Appel d'offre</option>
                                    <option value="1" <s:if test="getStatu()==1">selected="true"</s:if>>Soumission</option>
                                    <option value="2" <s:if test="getStatu()==2">selected="true"</s:if>>Travaux</option>
                                    <option value="3" <s:if test="getStatu()==3">selected="true"</s:if>>Proces Verbal</option>
                                    <option value="4" <s:if test="getStatu()==4">selected="true"</s:if>>Facturation</option>
                                </select>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="last-name">Etat :
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                            
                                <select id="close" name="close" class="form-control">
                                    <option value="none" <s:if test="getClose()==none">selected="true"</s:if> >Tout</option>
                                    <option value="true" <s:if test="getClose().equalsIgnoreCase(true)">selected="true"</s:if>>Cloturé</option>
                                    <option value="false" <s:if test="getClose().equalsIgnoreCase(false)">selected="true"</s:if>>Ouvert</option>                  
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="last-name">Date de travaux : 
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="dateTravaux" name="dateTravaux" value="<s:property value="getDateTravaux()"/>" type="date" id="last-name" name="last-name" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="last-name">Bon de Commande : 
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select id="bcTest" name="bcTest" class="form-control">
                                    <option value="">Tout</option>
                                    <option value="true" <s:if test="getBcTest().equalsIgnoreCase(true)">selected="true"</s:if> >Avec</option>
                                    <option value="false"<s:if test="getBcTest().equalsIgnoreCase(false)">selected="true"</s:if>>Sans</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="last-name">Bon de Commande des T.S : 
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select id="bcTsTest" name="bcTsTest" class="form-control">
                                    <option value="">Tout</option>
                                    <option value="true" <s:if test="getBcTsTest().equalsIgnoreCase(true)">selected="true"</s:if> >Avec</option>
                                    <option value="false"<s:if test="getBcTsTest().equalsIgnoreCase(false)">selected="true"</s:if>>Sans</option>
                                </select>
                            </div>
                        </div>
                                <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="last-name">Proces Verbal : 
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select id="pvTest" name="pvTest" class="form-control">
                                    <option value="">Tout</option>
                                    <option value="true" <s:if test="getPvTest().equalsIgnoreCase(true)">selected="true"</s:if> >Avec</option>
                                    <option value="false"<s:if test="getPcTest().equalsIgnoreCase(false)">selected="true"</s:if>>Sans</option>
                                </select>
                            </div>
                        </div>
                        <input value="1" name="pagination" type="texte" id="last-name" name="last-name" required="required" class="form-control col-md-7 col-xs-12" style="display:none">
                          
                       <div class="form-group">
                            <div class="col-md-6 col-sm-6 col-xs-6 col-md-offset-3">
                                <button type="submit" class="btn btn-success">Recherche</button>
                            </div>
                            
                            
                        </div>
                    </form>
                        <div class="">
                            <button class="telecharger btn btn-primary"><i class="fa fa-2x fa-file-pdf-o" aria-hidden="true"></i> T&eacute;l&eacute;charger la recherche</button>
                        </div>
                   
                        
                    <table class="table table-hover">
                      <thead>
                        <tr>
                            <th>#</th>
                            <th>
                                <a href="?order=id&orderOld=<s:property value="getOrderOld()"/>&pagination=<s:property value="%{#statu.index+1}" />&reference=<s:property value="getReference()"/>&bcTest=<s:property value="getBcTest()"/>&bcTsTest=<s:property value="getBcTsTest()"/>&nomProjet=<s:property value="getNomProjet()"/>&ticket=<s:property value="getTicket()"/>&nomType=<s:property value="getNomType()"/>&statu=<s:property value="getStatu()"/>&close=<s:property value="getClose()"/>&bcTest=<s:property value="getBcTest()"/>&bcTsTest=<s:property value="getBcTsTest()"/>&dateTravaux=<s:property value="getDateTravaux()"/>&pvTest=<s:property value="getPvTest()"/>">
                                    Reference
                                </a>
                            </th>                       
                            <th>
                                <a href="?order=nomProjet&orderOld=<s:property value="getOrderOld()"/>&pagination=<s:property value="%{#statu.index+1}" />&reference=<s:property value="getReference()"/>&bcTest=<s:property value="getBcTest()"/>&bcTsTest=<s:property value="getBcTsTest()"/>&nomProjet=<s:property value="getNomProjet()"/>&ticket=<s:property value="getTicket()"/>&nomType=<s:property value="getNomType()"/>&statu=<s:property value="getStatu()"/>&close=<s:property value="getClose()"/>&bcTest=<s:property value="getBcTest()"/>&bcTsTest=<s:property value="getBcTsTest()"/>&dateTravaux=<s:property value="getDateTravaux()"/>&pvTest=<s:property value="getPvTest()"/>">
                                    Libell&eacute; de l'archive
                                </a>
                            </th>
                            <th>
                                <a href="?order=typeOffre&orderOld=<s:property value="getOrderOld()"/>&pagination=<s:property value="%{#statu.index+1}" />&reference=<s:property value="getReference()"/>&bcTest=<s:property value="getBcTest()"/>&bcTsTest=<s:property value="getBcTsTest()"/>&nomProjet=<s:property value="getNomProjet()"/>&ticket=<s:property value="getTicket()"/>&nomType=<s:property value="getNomType()"/>&statu=<s:property value="getStatu()"/>&close=<s:property value="getClose()"/>&bcTest=<s:property value="getBcTest()"/>&bcTsTest=<s:property value="getBcTsTest()"/>&dateTravaux=<s:property value="getDateTravaux()"/>&pvTest=<s:property value="getPvTest()"/>">
                                    Nom du departement
                                </a>
                            </th>
                            <th>
                                <a href="?order=ticket&orderOld=<s:property value="getOrderOld()"/>&pagination=<s:property value="%{#statu.index+1}" />&reference=<s:property value="getReference()"/>&bcTest=<s:property value="getBcTest()"/>&bcTsTest=<s:property value="getBcTsTest()"/>&nomProjet=<s:property value="getNomProjet()"/>&ticket=<s:property value="getTicket()"/>&nomType=<s:property value="getNomType()"/>&statu=<s:property value="getStatu()"/>&close=<s:property value="getClose()"/>&bcTest=<s:property value="getBcTest()"/>&bcTsTest=<s:property value="getBcTsTest()"/>&dateTravaux=<s:property value="getDateTravaux()"/>&pvTest=<s:property value="getPvTest()"/>">
                                    Numero du ticket 
                                </a>
                            </th>                         
                            <th>
                                <a href="?order=dateAjout&orderOld=<s:property value="getOrderOld()"/>&pagination=<s:property value="%{#statu.index+1}" />&reference=<s:property value="getReference()"/>&bcTest=<s:property value="getBcTest()"/>&bcTsTest=<s:property value="getBcTsTest()"/>&nomProjet=<s:property value="getNomProjet()"/>&ticket=<s:property value="getTicket()"/>&nomType=<s:property value="getNomType()"/>&statu=<s:property value="getStatu()"/>&close=<s:property value="getClose()"/>&bcTest=<s:property value="getBcTest()"/>&bcTsTest=<s:property value="getBcTsTest()"/>&dateTravaux=<s:property value="getDateTravaux()"/>&pvTest=<s:property value="getPvTest()"/>">
                                    Date d'ajout 
                                </a>
                            </th>                         
                            <th>
                                <a href="?order=statu&orderOld=<s:property value="getOrderOld()"/>&pagination=<s:property value="%{#statu.index+1}" />&reference=<s:property value="getReference()"/>&bcTest=<s:property value="getBcTest()"/>&bcTsTest=<s:property value="getBcTsTest()"/>&nomProjet=<s:property value="getNomProjet()"/>&ticket=<s:property value="getTicket()"/>&nomType=<s:property value="getNomType()"/>&statu=<s:property value="getStatu()"/>&close=<s:property value="getClose()"/>&bcTest=<s:property value="getBcTest()"/>&bcTsTest=<s:property value="getBcTsTest()"/>&dateTravaux=<s:property value="getDateTravaux()"/>&pvTest=<s:property value="getPvTest()"/>">
                                   Etape 
                                </a>
                            </th>                         
                            <th> 
                                <a href="?order=close&orderOld=<s:property value="getOrderOld()"/>&pagination=<s:property value="%{#statu.index+1}" />&reference=<s:property value="getReference()"/>&bcTest=<s:property value="getBcTest()"/>&bcTsTest=<s:property value="getBcTsTest()"/>&nomProjet=<s:property value="getNomProjet()"/>&ticket=<s:property value="getTicket()"/>&nomType=<s:property value="getNomType()"/>&statu=<s:property value="getStatu()"/>&close=<s:property value="getClose()"/>">
                                   Etat 
                                </a>
                            </th>
                          
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
                                <td><s:property value="getDateAjout()"/></td>                        
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
                                <s:property value="getUser().getNiveau()"/>
                                <s:if test="getUserTemp().getNiveau()>=3">
                                    <td><a href="gestionHistorique?idOffre=<s:property value="getId()" />" class="btn btn-primary btn-xs">Historique</a></td>
                                </s:if>
                                <td> <a href="listeArchive?idOffre=<s:property value="getId()" />" class="btn btn-dark btn-xs"  >Gestion des fichiers</a>
                                <td> <a href="newOffre?idOffre=<s:property value="getId()" />" class="btn btn-primary btn-xs"  >Modifier information</a>
                                <td> <button id="<s:property value="getId()" />" class="supprimer btn btn-danger btn-xs"  >Supprimer</td>

                            </tr>
                              
                          </s:iterator>
                      </tbody>
                    </table>

                    </div>
                    <div class="col-md-12 text-center">
                        <ul  class="pagination pagination-lg pager">

                            <s:iterator begin="1" end="%{parameter.getMax()}" status="statu">
                                <s:if test="%{#statu.index == parameter.getPage()-1}">
                                    <%@include file="template/default/pagination/offre/pagenumberActive.jsp" %>
                                </s:if>
                                <s:else>
                                    <%@include file="template/default/pagination/offre/pagenumber.jsp" %>
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

<script>
    jQuery(document).ready(function ()
    {
        $('.telecharger').on('click', function ()
        {
            if (confirm("Voulez-vous vraiment telecharger la liste de cette recherche ?")) {
                window.location.replace("downloadOffre?reference="+document.getElementById('reference').value+"&nomProjet="+document.getElementById('nomProjet').value+"&ticket="+document.getElementById('ticket').value+"&nomType="+document.getElementById('nomType').options[document.getElementById('nomType').selectedIndex].value+"&statu="+document.getElementById('statu').options[document.getElementById('statu').selectedIndex].value+"&close="+document.getElementById('close').options[document.getElementById('close').selectedIndex].value+"&bcTest="+document.getElementById('bcTest').options[document.getElementById('bcTest').selectedIndex].value+"&dateTravaux="+document.getElementById('dateTravaux').value+"&bcTsTest="+document.getElementById('bcTsTest').options[document.getElementById('bcTsTest').selectedIndex].value+"&pvTest="+document.getElementById('pvTest').options[document.getElementById('pvTest').selectedIndex].value);
            }
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