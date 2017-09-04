<%@include file="template/default/header.jsp" %>
<div class="x_panel">
  <%@include file="template/default/Erreur.jsp" %>
                <div class="x_title">
                    <h2><a href="accueil" class="btn btn-default"><i class="fa fa-arrow-left"></i>  </a>Liste des type d'archive <small></small></h2>
                    <ul class="nav navbar-right panel_toolbox">
<!--                      <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>-->                      
                        <li class="dropdown">
                          <a href="gestionTypeFichier" ><i class="fa fa-plus"></i></a>
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
                                    Nom du type d'archive                                   
                                </th>                       
                                <th>  
                                    Date d'ajout                                  
                                </th>
                                                         
                            </tr>
                        </thead>
                        <tbody>
                            <s:iterator value="typeFichiers" status="catalogue">
                                <tr>
                                    <th scope="row"><s:property value="#catalogue.index+1"/></th>

                                    <th><s:property value="getNomType()"/></th>
                                    <td><s:property value="getDateajout()"/></td>                      
                                    <td><a href="gestionTypeFichier?idType=<s:property value="getId()"/>" class="btn btn-primary btn-xs">Modifier</a></td>                      
                                </tr>
                            </s:iterator>
                        </tbody>
                    </table>
                </div>                    
            </div>
<%@include file="template/default/footer.jsp" %>