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
                                <!--<td><button type="button" class="btn btn-dark btn-xs"><s:property value="getStatuString()"/></button></td>-->
                                <s:if test="getClose()==true">
                                    <td><button type="button" class="btn btn-danger btn-xs"><s:property value="getStatuString()"/></button></td>
                                </s:if>
                                <s:else>
                                    <td><button type="button" class="btn btn-success btn-xs"><s:property value="getStatuString()"/></button></td>
                                </s:else>
                                
                                <td><a href="gestionHistorique?idOffre=<s:property value="getId()" />" class="btn btn-primary btn-xs">Historique</a></td>
                                <td> <a href="listeArchive?idOffre=<s:property value="getId()" />" class="btn btn-primary btn-xs"  >Gestion des fichiers</button></a>
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