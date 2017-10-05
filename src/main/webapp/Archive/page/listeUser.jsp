<%@include file="template/default/header.jsp" %>
<div class="x_panel">
  <%@include file="template/default/Erreur.jsp" %>
                <div class="x_title">
                    <h2><a href="accueil" class="btn btn-default"><i class="fa fa-arrow-left"></i>  </a>Liste des type d'archive <small></small></h2>
                    <ul class="nav navbar-right panel_toolbox">
<!--                      <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>-->                      
                        <li class="dropdown">
                          <a href="newUser" ><i class="fa fa-plus"></i></a>
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
                                    Reference                                 
                                </th>                       
                                <th>            
                                    Matricule                                   
                                </th>  
                                <th>  
                                    CIN                                 
                                </th>
                                <th>  
                                    Nom                                 
                                </th>
                                <th>  
                                    Prenom                                 
                                </th>
                                <th>  
                                    date de naissance                                 
                                </th>
                                <th>  
                                    date de d'embauche                                 
                                </th>
                                <th>  
                                    Niveau                                 
                                </th>
                                
                                
                                                         
                            </tr>
                        </thead>
                        <tbody>
                            <s:iterator value="users" status="user">
                                <tr>
                                    <th scope="row"><s:property value="#user.index+1"/></th>

                                    <th><s:property value="getAllReference()"/></th>
                                    <th><s:property value="getMatricule()"/></th>
                                    <td><s:property value="getCIN()"/></td>                      
                                    <td><s:property value="getNom()"/></td>                      
                                    <td><s:property value="getPrenom()"/></td>                      
                                    <td><s:property value="getDateNaissance()"/></td>                      
                                    <td><s:property value="getDateEmbauche()"/></td>                      
                                    <td><s:property value="getNiveau()"/></td>                      
                                    <td> <a href="newUser?idUser=<s:property value="getId()"/>"  class="btn btn-primary btn-xs">Modifier</a></td>
                                    <td> <button id="<s:property value="getAllReference()" />" class="supprimer btn btn-danger btn-xs"  >Supprimer</button></td>
                                </tr>
                            </s:iterator>
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
                window.location.replace("deleteReflect?reference="+this.getAttribute('id')+"&url=listeUser");
                
            }
        });
    });
</script>
<%@include file="template/default/footer.jsp" %>