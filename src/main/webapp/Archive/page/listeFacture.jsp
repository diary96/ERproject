<%@include file="template/default/header.jsp" %>
<div class="x_panel">
  <%@include file="template/default/Erreur.jsp" %>
                  <div class="x_title">
                      <h2>Liste des Factures</h2>
                    <ul class="nav navbar-right panel_toolbox">
<!--                      <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>-->
                      </li>
                      
<!--                      <li><a class="close-link"><i class="fa fa-close"></i></a>-->
                      </li>
                    </ul>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                      <form action="listeFacturation" id="demo-form2" data-parsley-validate="" class="form-horizontal form-label-left" novalidate="" methode="GET">
                          <input type="hidden" name="orderOld" value=""/>
                          <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="last-name">Reference : <span class="required"></span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input name="reference" value="<s:property value="getReference()"/>" type="text" id="last-name" name="last-name" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="last-name">Reference de l'offre : 
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input name="referenceOffre" value="<s:property value="getReferenceOffre()"/>" type="texte" id="last-name" name="last-name" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                          
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="last-name">Nom de paiement : 
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                               <input name="nomPaiement" value="<s:property value="getNomPaiement()"/>"type="texte" id="last-name" name="last-name" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="last-name">Type de paiement : 
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                               <input name="typePaiement" value="<s:property value="getTypePaiement()"/>"type="texte" id="last-name" name="last-name" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="type">Pay&eacute; 
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select id="heard" name="paye"class="form-control" required="">
                                    <option value="">Tout</option>
                                    <option value="true" <s:if test="getPaye().equalsIgnoreCase(true)">selected="true"</s:if>>Pay&eacute;</option>
                                    <option value="false" <s:if test="getPaye().equalsIgnoreCase(false)">selected="true"</s:if>>Non-Pay&eacute;</option>         
                                </select>

                            </div>
                        </div>  
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="type">En retard 
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select id="heard" name="retard"class="form-control" required="">
                                    <option value="" >Tout</option>
                                    <option value="true" <s:if test="getRetard().equalsIgnoreCase(true)">selected="true"</s:if>>Oui</option>
                                    <option value="false" <s:if test="getRetard().equalsIgnoreCase(false)">selected="true"</s:if>>Non</option>         
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
                            <th>
                                <a href="?order=id&orderOld=<s:property value="getOrderOld()"/>&reference=<s:property value="getReference()"/>&referenceOffre=<s:property value="getReferenceOffre()"/>&nomPaiement=<s:property value="getNomPaiement()"/>&typePaiement=<s:property value="getTypePaiement()"/>&paye=<s:property value="getPaye()"/>&retard=<s:property value="getRetard()"/>">
                                    Reference
                                </a>
                            </th>                       
                            <th>
                                <a href="?order=soumission.offre.id&orderOld=<s:property value="getOrderOld()"/>&reference=<s:property value="getReference()"/>&referenceOffre=<s:property value="getReferenceOffre()"/>&nomPaiement=<s:property value="getNomPaiement()"/>&typePaiement=<s:property value="getTypePaiement()"/>&paye=<s:property value="getPaye()"/>&retard=<s:property value="getRetard()"/>">
                                   Reference de l'offre
                                </a>
                            </th>
                            <th>
                                <a href="?order=offre.nomProjet&orderOld=<s:property value="getOrderOld()"/>&reference=<s:property value="getReference()"/>&referenceOffre=<s:property value="getReferenceOffre()"/>&nomPaiement=<s:property value="getNomPaiement()"/>&typePaiement=<s:property value="getTypePaiement()"/>&paye=<s:property value="getPaye()"/>&retard=<s:property value="getRetard()"/>">
                                   Nom du projet
                                </a>
                            </th>
                            <th>
                                <a href="?order=payementName&orderOld=<s:property value="getOrderOld()"/>&offre&reference=<s:property value="getReference()"/>&referenceOffre=<s:property value="getReferenceOffre()"/>&nomPaiement=<s:property value="getNomPaiement()"/>&typePaiement=<s:property value="getTypePaiement()"/>&paye=<s:property value="getPaye()"/>&retard=<s:property value="getRetard()"/>">
                                    Nom du paiement
                                </a>
                            </th>
                            <th>
                                <a href="?order=typeDescription&orderOld=<s:property value="getOrderOld()"/>&reference=<s:property value="getReference()"/>&referenceOffre=<s:property value="getReferenceOffre()"/>&nomPaiement=<s:property value="getNomPaiement()"/>&typePaiement=<s:property value="getTypePaiement()"/>&paye=<s:property value="getPaye()"/>&retard=<s:property value="getRetard()"/>">
                                   Type de paiement
                                </a>
                            </th>
                            <th>
                                <a href="?order=pourcentage&orderOld=<s:property value="getOrderOld()"/>&reference=<s:property value="getReference()"/>&referenceOffre=<s:property value="getReferenceOffre()"/>&nomPaiement=<s:property value="getNomPaiement()"/>&typePaiement=<s:property value="getTypePaiement()"/>&paye=<s:property value="getPaye()"/>&retard=<s:property value="getRetard()"/>">
                                    Pourcentage
                                </a>
                            </th>                         
                            <th>
                                <a href="?order=date&orderOld=<s:property value="getOrderOld()"/>&reference=<s:property value="getReference()"/>&referenceOffre=<s:property value="getReferenceOffre()"/>&nomPaiement=<s:property value="getNomPaiement()"/>&typePaiement=<s:property value="getTypePaiement()"/>&paye=<s:property value="getPaye()"/>&retard=<s:property value="getRetard()"/>">
                                    Date de paiement pr&eacute;vu
                                </a>
                            </th>                         
                            <th>
                                <a href="?order=datepaiement&orderOld=<s:property value="getOrderOld()"/>&reference=<s:property value="getReference()"/>&referenceOffre=<s:property value="getReferenceOffre()"/>&nomPaiement=<s:property value="getNomPaiement()"/>&typePaiement=<s:property value="getTypePaiement()"/>&paye=<s:property value="getPaye()"/>&retard=<s:property value="getRetard()"/>">
                                    Date du paiement                                
                                </a>
                            </th>                         
                                                   
                            
                          
                        </tr>
                      </thead>
                      <tbody>
                          <s:iterator value="ventillationsData" status="ventilation">
                            <tr class="clickable-row" data-href='detailOffre?idOffre=<s:property value="getId()"/>'>
                                <td scope="row"><s:property value="#ventilation.index+1"/></td>
                                <td><s:property value="getAllReference()"/></td>
                                <td ><a href="gestionPaiement?idOffre=<s:property value="getSoumission().getOffre().getId()"/>"><s:property value="getSoumission().getOffre().getAllReference()"/></a></td>
                                <td ><a href="gestionPaiement?idOffre=<s:property value="getSoumission().getOffre().getId()"/>"><s:property value="getSoumission().getOffre().getNomProjet()"/></a></td>
                                <td><s:property value="getPayementName()"/></td>                              
                                <td><s:property value="getTypeDescription()"/></td>                        
                                <td><s:property value="getPourcentage()"/></td>                        
                                <td><s:property value="getDate()"/></td>                        
                                <td><s:property value="getDatepaiement()"/></td>                        
                               
                            </tr>
                              
                          </s:iterator>
                      </tbody>
                    </table>

                    </div>
                    <div class="col-md-12 text-center">
                        <ul  class="pagination pagination-lg pager">

                            <s:iterator begin="1" end="%{parameter.getMax()}" status="statu">
                                <s:if test="%{#statu.index == parameter.getPage()-1}">
                                    <%@include file="template/default/pagination/facturation/pagenumberActive.jsp" %>
                                </s:if>
                                <s:else>
                                    <%@include file="template/default/pagination/facturation/pagenumber.jsp" %>
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
        $('.supprimer').on('click', function ()
        {
            if (confirm("Voulez-vous vraiment supprimer l'offre OFF"+this.getAttribute('id')+"?")) {
                window.location.replace("deleteReflect?reference=OFF"+this.getAttribute('id')+"&url=accueil");
                
            }
        });
    });
</script>
<%@include file="template/default/footer.jsp" %>