<%-- 
    Document   : addPV
    Created on : 19 juin 2017, 11:28:32
    Author     : diary
--%>

<%@include file="template/default/header.jsp" %>
<link rel="stylesheet" type="text/css" href="../assets/css/normalize.css">
<link rel="stylesheet" type="text/css" href="../assets/css/component.css">
<div class="x_panel">
    <%@include file="template/default/Erreur.jsp" %>
    <div class="x_title">
        <h2><a href="accueil" class="btn btn-default"><i class="fa fa-arrow-left"></i>  </a> Ticket Numero  : <s:property value="getOffre().getTicket()"/> <small>Projet : <s:property value="getOffre().getNomProjet()"/> </small></h2>


        <div class="clearfix"></div>
    </div>
    <div class="" role="tabpanel" data-example-id="togglable-tabs">
        <a href="listeArchive?idOffre=<s:property value="getIdOffre()"/>" class="btn btn-primary">Gestion des fichiers</a>
        <s:if test="getUser().getNiveau()>=3">
            <a href="gestionHistorique?idOffre=<s:property value="getIdOffre()"/>" class="btn btn-dark">Historique</a>
        </s:if>
        <s:if test="getUser().getNiveau()>=1">
            <a href="loadDowngrade?idOffre=<s:property value="getIdOffre()"/>" class="btn btn-dark">R&eacute;trograder l'Offre</a>
            <s:if test="getOffre().getClose()==true">
                <a href="openOffre?idOffre=<s:property value="getIdOffre()"/>" class="btn btn-danger">r&eacute;ouvrire l'offre</a>
            </s:if>
        </s:if>
        <s:if test="getOffre().getClose()==false">
            <a href="closeOffre?idOffre=<s:property value="getIdOffre()"/>" class="btn btn-danger">Cloturé l'offre</a>
        </s:if>
        
        <s:if test="getUser().getNiveau()>=4">
            <s:if test="getOffre().getStatu()==@com.er.erproject.data.StatuReference@FACTURATION">
                    <a href="gestionPaiement?idOffre=<s:property value="getIdOffre()"/>" class="btn btn-dark">Gestion des paiements</a>
            </s:if>
        </s:if>
        <ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
            <%--<s:property value="@com.er.erproject.data.StatuReference@OFFRE"/>--%>
            
            <s:if test="getOffre().getStatu()==@com.er.erproject.data.StatuReference@OFFRE">
                <li role="presentation"><a href="#offre" role="tab" id="profile-tab" data-toggle="tab" aria-expanded="false">Offre Initial</a>
                </li>
            </s:if>
            <s:if test="getOffre().getStatu()>@com.er.erproject.data.StatuReference@OFFRE">
                <li role="presentation" class=""><a href="#soumission" role="tab" id="profile-tab" data-toggle="tab" aria-expanded="false">Sousmission</a>
                </li>
            </s:if>
            <s:if test="getOffre().getStatu()>@com.er.erproject.data.StatuReference@SOUMISSION">
                <li role="presentation" class=""><a href="#travaux" role="tab" id="profile-tab" data-toggle="tab" aria-expanded="false">Travaux</a>
                </li>
            </s:if>
            <s:if test="getOffre().getStatu()>@com.er.erproject.data.StatuReference@TRAVAUX">
                <li role="presentation" class=""><a href="#pv" role="tab" id="profile-tab" data-toggle="tab" aria-expanded="false">Proces Verbal</a>
                </li>
            </s:if>

        </ul>
        <div id="myTabContent" class="tab-content">
            <s:if test="getOffre().getStatu()==@com.er.erproject.data.StatuReference@OFFRE">
                <div role="tabpanel" class="tab-pane fade active in" id="offre" aria-labelledby="home-tab">

                    <!-- start offre -->
                    <div id="table">
                        <a href="gestionInitial?idOffre=<s:property value="getIdOffre()"/>" class="btn btn-default"><i class="glyphicon glyphicon-plus"></i> D&eacute;tails des travaux de l'offre </a>
                        <s:if test="getOffre().getStatu()==@com.er.erproject.data.StatuReference@OFFRE">
                            <a href="valider?idOffre=<s:property value="getIdOffre()"/>&nextLevel=<s:property value="@com.er.erproject.data.StatuReference@SSOUMISSION"/>&url=detailOffre" class="btn btn-success"><i class="fa fa-check"></i> Valider </a>
                        </s:if>
                        <table cellspacing="1" class="table table-bordered">
                            <tr>
                                <td>Reference du travaux</td>
                                <th>Designation</th>
                                <th>Unite</th>
                                <th>Quantite</th>                     
                            </tr>

                            <s:iterator value="getOffre().getTacheinitials().getTravaux()">
                                <tr>
                                    <td><s:property value="getAllReference()"/></td>
                                    <td><s:property value="getCatalogue().getDesignation()"/></td>
                                    <td align="center"><s:property value="getCatalogue().getUnite()"/></td>
                                    <td align="center"><s:property value="getQuantite()"/></td>
                                </tr>
                            </s:iterator>                       
                            <!-- This is our clonable table line -->
                        </table>
                        <!--<a id="export-btn" class="btn btn-primary">Export Data</a>-->
                        <!--<p id="export"></p>-->
                    </div>
                    <!-- end offre -->

                </div>
            </s:if>
            <div role="tabpanel" class="tab-pane fade <s:if test="getOffre().getStatu()==@com.er.erproject.data.StatuReference@SOUMISSION">active in</s:if>" id="soumission" aria-labelledby="profile-tab">
                <!-- start  soumission--> 
                <div id="table">
                    <a href="gestionSoumission?idOffre=<s:property value="getIdOffre()"/>" class="btn btn-default"><i class="glyphicon glyphicon-plus"></i> Gestion des status des travaux de soumission</a>
                    
                    <s:if test="getOffre().getStatu()==@com.er.erproject.data.StatuReference@SOUMISSION">
                        <a href="parametre?idOffre=<s:property value="getIdOffre()"/>&type=<s:property value="@com.er.erproject.data.ReferenceType@SOUMISSION"/>&url=detailOffre" class="btn btn-primary"><i class="fa fa-cogs"></i>  Taxe et Remise</a>
                    </s:if>
                    <s:if test="getOffre().getStatu()==@com.er.erproject.data.StatuReference@SOUMISSION">
                        <a href="valider?idOffre=<s:property value="getIdOffre()"/>&nextLevel=<s:property value="@com.er.erproject.data.StatuReference@STRAVAUX"/>&url=detailOffre" class="btn btn-success"><i class="fa fa-check"></i> Valider </a>
                    </s:if>
                    <s:if test="getOffre().getStatu()>@com.er.erproject.data.StatuReference@SOUMISSION">
                        <a href="download?idOffre=<s:property value="getIdOffre()"/>" class="btn btn-primary"><i class="fa fa-download"></i> Telecharger devis</a>
                    </s:if>
                    <s:if test="getOffre().getStatu()>@com.er.erproject.data.StatuReference@SOUMISSION">
                        <a href="gestionBC?idOffre=<s:property value="getIdOffre()"/>&type=<s:property value="@com.er.erproject.data.VentilationData@SOUMISSION"/>" class="btn btn-primary"><i class="fa fa-folder-o"></i> Bon de commande</a>
                    </s:if>
                    
                    <table cellspacing="1" class="table table-bordered">
                        <tr>
                            <td>Reference du travaux</td>
                            <th>Designation</th>
                            <th>Unite</th>
                            <th>Quantite</th>                     
                        </tr>
                        <s:iterator value="getOffre().getTacheinitials().getTravaux()">
                            <tr>
                                <td><s:property value="getAllReference()"/></td>
                                <td><s:property value="getCatalogue().getDesignation()"/></td>
                                <td align="center"><s:property value="getCatalogue().getUnite()"/></td>
                                <td align="center"><s:property value="getQuantite()"/></td>
                            </tr>
                        </s:iterator>
                        <s:iterator value="getOffre().getTacheSoumission().getTravaux()">
                            <tr>
                                <td><s:property value="getAllReference()"/></td>
                                <td><s:property value="getCatalogue().getDesignation()"/></td>
                                <td align="center"><s:property value="getCatalogue().getUnite()"/></td>
                                <td align="center"><s:property value="getQuantite()"/></td>
                            </tr>
                        </s:iterator>

                        <!-- This is our clonable table line -->

                    </table>
                    <!--<a id="export-btn" class="btn btn-primary">Export Data</a>-->
                    <!--<p id="export"></p>-->
                </div>
                <!-- end soumission -->

            </div>
            <div role="tabpanel" class="tab-pane fade" id="travaux" aria-labelledby="profile-tab">

                <!-- start travaux  --> 
                <div class="x_panel">
                    <div class="x_title">
                        <h2>Travaux Supplementaire<small></small></h2>
                        <ul class="nav navbar-right panel_toolbox">
                            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                            </li>

                        </ul>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <div id="table">
                            <a href="gestionSupplementaire?idOffre=<s:property value="getIdOffre()"/>" class="btn btn-default"><i class="glyphicon glyphicon-plus"></i> Gestion des status des travaux supplementaire</a>
                            <s:if test="getOffre().getStatu()==@com.er.erproject.data.StatuReference@TRAVAUX">
                                <a href="parametre?idOffre=<s:property value="getIdOffre()"/>&type=<s:property value="@com.er.erproject.data.ReferenceType@TAVAUX_SUPPLEMENTAIRE"/>&url=detailOffre" class="btn btn-primary"><i class="fa fa-cogs"></i>  Taxe et Remise</a>
                            </s:if>
                            <s:if test="getOffre().getStatu()==@com.er.erproject.data.StatuReference@TRAVAUX">
                                <a href="valider?idOffre=<s:property value="getIdOffre()"/>&nextLevel=<s:property value="@com.er.erproject.data.StatuReference@SPV"/>&url=detailOffre" class="btn btn-success"><i class="fa fa-check"></i> Valider </a>
                            </s:if>
                            <s:if test="getOffre().getStatu()>@com.er.erproject.data.StatuReference@TRAVAUX">
                                <a href="gestionBC?idOffre=<s:property value="getIdOffre()"/>&type=<s:property value="@com.er.erproject.data.VentilationData@TS"/>" class="btn btn-primary"><i class="fa fa-folder-o"></i> Bon de commande</a>
                            </s:if>
                            <table cellspacing="1" class="table table-bordered">
                                <tr>
                                    <td>Reference du travaux</td>
                                    <th>Designation</th>
                                    <th>Unite</th>
                                    <th>Quantite</th>
                                </tr>
                                <s:iterator value="getOffre().getTacheSupplementaire().getTravaux()">
                                    <tr>
                                        <td><s:property value="getAllReference()"/></td>
                                        <td><s:property value="getCatalogue().getDesignation()"/></td>
                                        <td align="center"><s:property value="getCatalogue().getUnite()"/></td>
                                        <td align="center"><s:property value="getQuantite()"/></td>
                                    </tr>
                                </s:iterator>


                                <!-- This is our clonable table line -->

                            </table>
                            <!--<a id="export-btn" class="btn btn-primary">Export Data</a>-->
                            <!--<p id="export"></p>-->
                        </div>
                    </div>
                </div>
                <div class="x_panel">
                    <div class="x_title">
                        <h2>Liste des materiaux recu<small></small></h2>
                        <ul class="nav navbar-right panel_toolbox">
                            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                            </li>

                        </ul>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <div id="table">
                            <s:if test="getOffre().getStatu()==@com.er.erproject.data.StatuReference@TRAVAUX">

                                <a href="gestionMateriel?idOffre=<s:property value="getIdOffre()"/>&url=detailOffre" class="btn btn-default"><i class="glyphicon glyphicon-plus"></i>Gestion des mat&eacute;riaux re&ccedil;us</a>
                            </s:if>
                            <table cellspacing="1" class="table table-bordered">
                                <tr>                                   
                                    <th>Designation</th>
                                    <th>Unite</th>
                                    <th>Quantite</th>

                                </tr>
                                <s:iterator value="getOffre().getMateriaux()">

                                    <tr>
                                        <td><s:property value="getDesignation()"/></td>
                                        <td align="center"><s:property value="getUnite()"/></td>
                                        <td align="center"><s:property value="getQuantite()"/></td>
                                        <s:if test="getTemp().getStatu()==@com.er.erproject.data.StatuReference@TRAVAUX">
                                            <td align="center"><a href="deleteReflect?url=detailOffre&idOffre=<s:property value="getIdOffre()"/>&reference=<s:property value="getAllReference()"/>" class="btn btn-danger btn-xs">Supprimer</a></td>
                                        </s:if>

                                    </tr>
                                </s:iterator>



                                <tr>
                                </tr>
                                <!-- This is our clonable table line -->

                            </table>
                            <!--<a id="export-btn" class="btn btn-primary">Export Data</a>-->
                            <!--<p id="export"></p>-->
                        </div>  
                    </div>
                </div>
                <!-- end travaux -->

            </div>
            <div role="tabpanel" class="tab-pane fade" id="pv" aria-labelledby="profile-tab">

                <!-- start  pv--> 
                <span class="section">Information du PV</span>


                <a href="loadPv?idOffre=<s:property value="getIdOffre()"/>" class="btn btn-primary">Generer PV PDF</a>
                <s:if test="getOffre().getStatu()==@com.er.erproject.data.StatuReference@PV">
                    <a href="allOffreEffect?idOffre=<s:property value="getIdOffre()"/>&url=detailOffre" class="btn btn-success"><i class="fa fa-reply-all" aria-hidden="true"></i> Tout effectuer les taches </a>
                    <a href="valider?idOffre=<s:property value="getIdOffre()"/>&nextLevel=<s:property value="@com.er.erproject.data.StatuReference@SFACTURATION"/>&url=detailOffre" class="btn btn-success"><i class="fa fa-check"></i> Valider </a>
                </s:if>
                <s:if test="getOffre().getStatu()==@com.er.erproject.data.StatuReference@FACTURATION">
                    <s:if test="getInitiaux()==false">
                        <a href="loadVentilation?idOffre=<s:property value="getIdOffre()"/>&type=<s:property value="@com.er.erproject.data.VentilationData@SOUMISSION"/>" class="btn btn-success">Conditions de paiement  initiaux</a>
              
                    </s:if>
                    <s:else>
                        <a href="facturation?idOffre=<s:property value="getIdOffre()"/>" class="btn btn-success">Facturation des taches initiaux</a>
              
                    </s:else>
                    <s:if test="getTs()==false">
                        <a href="loadVentilation?idOffre=<s:property value="getIdOffre()"/>&type=<s:property value="@com.er.erproject.data.VentilationData@TS"/>" class="btn btn-success">Conditions de paiement T.S</a>
              
                    </s:if>
                    <s:else>
                         <a href="facturationTS?idOffre=<s:property value="getIdOffre()"/>" class="btn btn-success">Facturation des T.S</a>
              
                    </s:else>
                </s:if>

                <span class="section">Liste et Statut des travaux</span>

                <div id="table">

                    <table cellspacing="1" class="table table-bordered">
                        <tr>
                            <th>Designation</th>
                            <th>Unite</th>
                            <th>Quantite</th>
                            <th>Total fait</th>

                            <th>Photos</th>
                            <th>Nombre de photo</th>
                            <s:if test="getTemp().getStatu()==@com.er.erproject.data.StatuReference@PV">
                                <th>Action</th>
                            </s:if>

                        </tr>

                        <s:iterator value="getOffre().getTacheinitials().getTravaux()">
                            <tr>                              
                                <td><s:property value="getCatalogue().getDesignation()"/></td>
                                <td align="center"><s:property value="getCatalogue().getUnite()"/></td>
                                <td align="center"><s:property value="getQuantite()"/></td>
                                <td align="center"><s:property value="getEffectuer()"/></td>
                                <td align="center"><a href="gestionPhoto?reference=<s:property value="getAllReference()"/>" target="_blank">Details photos</a></td>
                                <td align="center"><s:property value="getPhotos().size()"/></td>
                                <s:if test="getTemp().getStatu()==@com.er.erproject.data.StatuReference@PV">
                                    <td align="center">
                                        <a href="allEffect?reference=<s:property value="getAllReference()" />&url=detailOffre&idOffre=<s:property value="getIdOffre()"/>"><i class="fa fa-reply-all" aria-hidden="true"></i></a>
                                    </td>
                                    <td align="center">
                                        <input style="width:25%" id="input<s:property value="getAllReference()"/>">
                                        <buton id='<s:property value="getAllReference()"/>' class="modifier btn btn-success">Modifier</buton>
                                    </td>
                                </s:if>
                                

                            </tr>
                        </s:iterator>
                        <s:iterator value="getOffre().getTacheSoumission().getTravaux()">
                            <tr>                               
                                <td><s:property value="getCatalogue().getDesignation()"/></td>
                                <td align="center"><s:property value="getCatalogue().getUnite()"/></td>
                                <td align="center"><s:property value="getQuantite()"/></td>
                                <!--<td align="center"><s:property value="getEffectuer()"/></td>-->
                                <td align="center"><s:property value="getEffectuer()"/></td>
                                <td align="center"><a href="gestionPhoto?reference=<s:property value="getAllReference()"/>" target="_blank">Details photos</a></td>
                                <td align="center"><s:property value="getPhotos().size()"/></td>
                                <s:if test="getTemp().getStatu()==@com.er.erproject.data.StatuReference@PV">
                                    <td align="center">
                                         <a href="allEffect?reference=<s:property value="getAllReference()" />&url=detailOffre&idOffre=<s:property value="getIdOffre()"/>"><i class="fa fa-reply-all" aria-hidden="true"></i></a>                             
                                    </td>
                                    <td align="center">
                                        <input style="width:25%" id="input<s:property value="getAllReference()"/>">
                                        <buton id='<s:property value="getAllReference()"/>' class="modifier btn btn-success">Modifier</buton>
                                    </td>
                                
                                </s:if>
                            </tr>
                        </s:iterator>
                        <!-- This is our clonable table line -->

                    </table>
                    <!--<a id="export-btn" class="btn btn-primary">Export Data</a>-->
                    <!--<p id="export"></p>-->
                </div>    

                <span class="section">Liste des travaux suppl&eacute;mentaires</span>

                <div id="table">

                    <table cellspacing="1" class="table table-bordered">
                        <tr>
                            <th>Designation</th>
                            <th>Unite</th>
                            <th>Quantite</th>
                            <th>Nombre de photo</th>
                            <th>Photos</th>

                        </tr>


                        <s:iterator value="getOffre().getTacheSupplementaire().getTravauxPV()">
                            <tr>                              
                                <td><s:property value="getCatalogue().getDesignation()"/></td>
                                <td align="center"><s:property value="getCatalogue().getUnite()"/></td>
                                <td align="center"><s:property value="getQuantite()"/></td>
                                <td align="center"><s:property value="getPhotos().size()"/></td>
                                <td align="center"><a href="gestionPhoto?reference=<s:property value="getAllReference()"/>" target="_blank">Details photos</a></td>
                            </tr>
                        </s:iterator>
                        <!-- This is our clonable table line -->

                    </table>
                    <!--<a id="export-btn" class="btn btn-primary">Export Data</a>-->
                    <!--<p id="export"></p>-->
                </div>    
                <span class="section">Liste des Materiaux re&ccedil;us</span>

                <div id="table">
                    <table cellspacing="1" class="table table-bordered">
                        <tr>
                            <th>Designation</th>
                            <th>Unite</th>
                            <th>Quantite</th>
                            <th>Statut</th>
                        </tr>



                        <s:iterator value="getOffre().getMateriaux()">
                            <tr>
                                <td><s:property value="getDesignation()"/></td>
                                <td align="center"><s:property value="getUnite()"/></td>
                                <td align="center"><s:property value="getQuantite()"/></td>
                                <s:if test="getEtat()==@com.er.erproject.data.StatuReference@RECU">
                                    <td align="center"><a href="changeStat?reference=<s:property value="getAllReference()" />&url=detailOffre&idOffre=<s:property value="getIdOffre()"/>" class="btn btn-success btn-xs">RECU</a></td>
                                </s:if>
                                <s:if test="getEtat()==@com.er.erproject.data.StatuReference@NON_RECU">
                                    <td align="center"><a href="changeStat?reference=<s:property value="getAllReference()" />&url=detailOffre&idOffre=<s:property value="getIdOffre()"/>" class="btn btn-danger btn-xs">NON RECU</a></td>
                                </s:if>

                            </tr>
                        </s:iterator>
                        <!-- This is our clonable table line -->

                    </table>
                    <!--<a id="export-btn" class="btn btn-primary">Export Data</a>-->
                    <!--<p id="export"></p>-->
                </div>    
            </div>
            <!-- end  pv-->



        </div>
    </div>

</div>
<script>
    $(document).ready(function () {
        $('a[data-toggle="tab"]').on('show.bs.tab', function (e) {
            localStorage.setItem('activeTab', $(e.target).attr('href'));
        });
        var activeTab = localStorage.getItem('activeTab');
        if (activeTab) {
            $('#myTab a[href="' + activeTab + '"]').tab('show');
        }

    });
    $(window).scroll(function () {
        sessionStorage.scrollTop = $(this).scrollTop();
    });

    $(document).ready(function () {
        if (sessionStorage.scrollTop != "undefined") {
            $(window).scrollTop(sessionStorage.scrollTop);
        }
    });
//tu recupere la valeur dans lurl




</script>
<script>
    jQuery(document).ready(function ()
    {
        $('.modifier').on('click', function ()
        {
            console.log("click");
            console.log("input"+this.getAttribute('id'));
            if(document.getElementById("input"+this.getAttribute('id')).value!=""){
                window.location.replace("manualEffect?reference="+this.getAttribute('id')+"&url=detailOffre&idOffre=<s:property value="getIdOffre()"/>&effectuer="+document.getElementById("input"+this.getAttribute('id')).value);
            }
        });
    });
</script>
<%@include file="template/default/footer.jsp" %>
