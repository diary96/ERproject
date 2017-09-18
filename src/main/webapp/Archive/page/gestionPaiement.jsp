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
        <h2><a href="detailOffre?idOffre=<s:property value="getIdOffre()"/>" class="btn btn-default"><i class="fa fa-arrow-left"></i>  </a> Gestion des paiements</h2>
        

        <div class="clearfix"></div>
    </div>
    <div class="col-md-3 col-sm-12 col-xs-12">
                <div>
                    <div class="x_title">
                        <h2>Information de l'offre</h2>

                        <div class="clearfix"></div>
                    </div>                    
                    <h3> <s:property value="getOffre().getNomProjet()"/>  </h3><br>

                    <ul class="list-unstyled user_data">

                        <li><i class="fa fa-id-card-o"></i> <s:property value="getOffre().getTicket()"/> 
                        </li>
                        <li><i class="fa fa-map-marker user-profile-icon"></i> <s:property value="getOffre().getLocalisation()"/> 
                        </li>
                        <li>
                            <i class="fa fa-hourglass-end"></i>  <s:property value="getOffre().getDeadlineString()"/> 
                        </li>
                        <li>
                            <i class="fa fa-hourglass-start"></i> <s:property value="getOffre().getStringTravauxPrevu()"/> 
                        </li>


                    </ul>


                    <br>
                </div>
            </div>
            <div class="col-md-9 col-sm-12 col-xs-12">
                <div id="catalogue">
                    <div class="x_title">
                        <h2>Facture et Paiement</h2>

                        <div class="clearfix"></div>
                        <div class="x_content">
                            <br>
                            <div class="" role="tabpanel" data-example-id="togglable-tabs">
                                <ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
                                  <li role="presentation" class="active"><a href="#initiaux" id="home-tab" role="tab" data-toggle="tab" aria-expanded="true">Travaux Initiaux</a>
                                  </li>
                                  <li role="presentation" class=""><a href="#ts" role="tab" id="profile-tab" data-toggle="tab" aria-expanded="false">Travaux Supplémentaire</a>
                                  </li>
                                  
                                </ul>
                                <div id="myTabContent" class="tab-content">
                                  <div role="tabpanel" class="tab-pane fade active in" id="initiaux" aria-labelledby="home-tab">

                                    <!-- start recent activity -->
                                     <table class="data table table-striped no-margin">
                                      <thead>
                                        <tr>
                                          <th>#</th>
                                          <th>Reference</th>
                                          <th>Pourcentage</th>
                                          <th>Type de paiement</th>
                                          <th>Nom de paiement</th>
                                          <th>Date de paiement pr&eacute;vu</th>
                                          <th>Etat</th>
                                          <th>Date de paiement</th>
                                        </tr>
                                      </thead>
                                        <tbody>
                                            <s:iterator value="ventillationsData" status="ventillation">
                                                <tr>
                                                   <th scope="row"><s:property value="#ventillation.index+1"/></th>
                                                    <td><s:property value="getAllReference()"/> </td>
                                                    <td><s:property value="getPourcentage()"/> %</td>
                                                    <td><s:property value="getTypeDescription()"/></td>
                                                    <td><s:property value="getPayementName()"/></td>
                                                    <td><s:property value="getDate()"/></td>
                                                    <s:if test="isPayer()==true">
                                                        <td><button class="btn btn-success btn-xs">PAY&Eacute;</button></td>
                                                    </s:if>
                                                    <s:if test="isPayer()==false">
                                                        <td><button class="btn btn-danger btn-xs">NON PAYER</button></td>
                                                    </s:if>                                 
                                                    
                                                    <s:if test="isPayer()==false">
                                                        <td align="left">
                                                            <input style="width:100%; height:50%" id="input<s:property value="getAllReference()"/>" type="date" value="<s:property value="getDateNow()"/>">
                                                        </td>
                                                        <td align="left">
                                                            <buton id='<s:property value="getAllReference()"/>' class="modifier btn btn-success btn-xs">Payer</buton>
                                                        </td>
                                                    </s:if>
                                                    <s:if test="isPayer()==true">
                                                        <td align="left">
                                                            <s:property value="getDatepaiement()"/>
                                                        </td>
                                                    </s:if>
                                                  </tr>
                                            </s:iterator>
                                        
                                      </tbody>
                                    </table>
                                    <!-- end recent activity -->

                                  </div>
                                  <div role="tabpanel" class="tab-pane fade" id="ts" aria-labelledby="profile-tab">

                                    <!-- start user projects -->
                                    <table class="data table table-striped no-margin">
                                      <thead>
                                        <tr>
                                          <th>#</th>
                                          <th>Reference</th>
                                          <th>Pourcentage (%)</th>
                                          <th>Type de paiement</th>
                                          <th>Nom de paiement</th>
                                          <th>Date de paiement pr&eacute;vu</th>
                                          <th>Etat</th>
                                          <th>Date de paiement</th>
                                        </tr>
                                      </thead>
                                        <tbody>
                                            <s:iterator value="ventillationsTsData" status="ventillation">
                                                <tr>
                                                   <th scope="row"><s:property value="#ventillation.index+1"/></th>
                                                    <td><s:property value="getAllReference()"/> </td>
                                                    <td><s:property value="getPourcentage()"/> %</td>
                                                    <td><s:property value="getTypeDescription()"/></td>
                                                    <td><s:property value="getPayementName()"/></td>
                                                    <td><s:property value="getDate()"/></td>
                                                    <s:if test="isPayer()==true">
                                                        <td><button class="btn btn-success btn-xs">PAY&Eacute;</button></td>
                                                    </s:if>
                                                    <s:if test="isPayer()==false">
                                                        <td><button class="btn btn-danger btn-xs">NON PAYER</button></td>
                                                    </s:if>                                 
                                                    
                                                    <s:if test="isPayer()==false">
                                                        <td align="left">
                                                            <input style="width:100 height: 50%" id="input<s:property value="getAllReference()"/>" type="date" value="<s:property value="getDateNow()"/>">
                                                        </td>
                                                        <td align="left">
                                                            <buton id='<s:property value="getAllReference()"/>' class="modifier btn btn-success btn-xs">Payer</buton>
                                                        </td>
                                                    </s:if>
                                                    <s:if test="isPayer()==true">
                                                        <td align="left">
                                                            <s:property value="getDatepaiement()"/>
                                                        </td>
                                                    </s:if>
                                                  </tr>
                                            </s:iterator>
                                        
                                      </tbody>
                                    </table>
                                    <!-- end user projects -->

                                  </div>
                                  
                                </div>
                              </div>
                        </div>
                    </div>
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
                window.location.replace("payePaiement?referenceVentilation="+this.getAttribute('id')+"&idOffre=<s:property value="getIdOffre()"/>&date="+document.getElementById("input"+this.getAttribute('id')).value);
            }
        });
    });
</script>
<%@include file="template/default/footer.jsp" %>
