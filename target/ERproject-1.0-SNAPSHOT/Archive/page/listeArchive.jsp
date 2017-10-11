<%@include file="template/default/header.jsp" %>
<div class="x_panel">
  <%@include file="template/default/Erreur.jsp" %>
                <div class="x_title">
                    <h2><a href="detailOffre?idOffre=<s:property value="getIdOffre()"/>" class="btn btn-default"><i class="fa fa-arrow-left"></i>  </a>Liste des archives de l'offre <s:property value="getOffre().getAllReference()"/> <small></small></h2>
                    <ul class="nav navbar-right panel_toolbox">
<!--                      <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>-->                      
                        <li class="dropdown">
                            <a href="gestionArchive?idOffre=<s:property value="getIdOffre()"/>" ><i class="fa fa-plus"></i></a>
                        </li>
<!--                      <li><a class="close-link"><i class="fa fa-close"></i></a>-->
                    </ul>   
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">                   
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>
                                    Reference interne
                                </th>
                                <th>            
                                    Type de l'archive                                
                                </th>                       
                                <th>            
                                    Reference de l'archive                                   
                                </th>                       
                                <th>  
                                    Date d'ajout                                  
                                </th>
                                                         
                            </tr>
                        </thead>
                        <tbody>
                            <s:iterator value="archives" status="catalogue">
                                <tr>
                                    <th scope="row"><s:property value="#catalogue.index+1"/></th>

                                    <th><s:property value="getAllReference()"/></th>
                                    <th><s:property value="getTypeFichier().getNomType()"/></th>
                                    <th><s:property value="getNom()"/></th>           
                                    <td><s:property value="getDateajout()"/></td>                      
                                    <td><a href="<s:property value="getPath()"/>" class="btn btn-link btn-xs">Voir Fichier</a></td>                      
                                    <td><a href="gestionArchive?idOffre=<s:property value="getIdOffre()"/>&referenceInterieure=<s:property value="getAllReference()"/>" class="btn btn-primary btn-xs">Modifier</a></td>                      
                                    <td> <button id="<s:property value="getAllReference()" />" class="supprimer btn btn-danger btn-xs"  >Supprimer</button></td>
                                </tr>
                            </s:iterator>
                                <tr><th><a href="gestionArchive?idOffre=<s:property value="getIdOffre()"/>" ><i class="fa fa-plus"></i> </a></th></tr>
                        </tbody>
                    </table>
                </div>                    
            </div>
  <script>
    jQuery(document).ready(function ()
    {
        $('.supprimer').on('click', function ()
        {
            if (confirm("Voulez-vous vraiment supprimer le type "+this.getAttribute('id')+"?")) {
                window.location.replace("deleteArchive?reference="+this.getAttribute('id')+"&idOffre=<s:property value="getIdOffre()"/>");
                
            }
        });
    });
</script>
<%@include file="template/default/footer.jsp" %>