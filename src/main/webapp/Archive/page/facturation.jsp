<%-- 
    Document   : gestionTS
    Created on : 19 juin 2017, 17:00:13
    Author     : diary
--%>
<%@include file="template/default/header.jsp" %>
<!--<script src="../assets/gentella/vendors/jquery/dist/jquery.min.js"></script>-->

<div class="x_panel">
      <%@include file="template/default/Erreur.jsp" %>
    <div class="x_title">
        <h2><a href="detailOffre?idOffre=<s:property value="getIdOffre()"/>" class="btn btn-default"><i class="fa fa-arrow-left"></i>  </a>Apercu du facture Initial du tickets numero  : <s:property value="getOffre().getTicket()"/> </h2>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">

        <div role="tabpanel" class="tab-pane fade active in" id="init" aria-labelledby="home-tab">
            <div class="form-group">
                <label class="control-label col-md-1 col-sm-1 col-xs-1" for="first-name">Fonction :  </label>
                <div class="col-md-4 col-sm-4 col-xs-4">
                    <input  id="fonction" value="<s:property value="getFonction()"/>"  type="text" required="required" class="form-control col-md-7 col-xs-12">
                </div>
                <div class="col-md-3 col-sm-3 col-xs-3">
                    <input  id="responsable" value="<s:property value="getResponsable()"/>"  type="text" required="required" class="form-control col-md-7 col-xs-12">
                </div>
            </div><div class="clearfix"></div><br>
            <s:iterator value="ventillations">
                <button id="<s:property value="getAllReference()"/>" class="download btn btn-primary">Telecharger facture : <s:property value="getPourcentage()"/>%</button>
            </s:iterator>
            <s:if test="getUser().getNiveau()>=4">
                <a href="loadVentilation?idOffre=<s:property value="getIdOffre()"/>&type=<s:property value="@com.er.erproject.data.VentilationData@SOUMISSION"/>" class="btn btn-dark">Modifier condition de paiement</a>
            </s:if>
                <p><u>Condition de r&edot;glement : </u><s:property value="getCondition()"/></p>
            <table id="table" cellspacing="1" class="table table-bordered">
                <tr>
                    <td>Reference du travaux</td>
                    <th>Designation</th>
                    <th>Unite</th>
                    <th>Quantite</th>
                    <th>Effectuer</th>
                    <th>P.U</th>
                    <th>Prix Total</th>

                </tr>
                <s:iterator value="getOffre().getTacheinitials().getTravaux()">
                    <tr>
                        <td><s:property value="getAllReference()"/></td>
                        <td><s:property value="getCatalogue().getDesignation()"/></td>
                        <td align="center"><s:property value="getCatalogue().getUnite()"/></td>
                        <td align="center"><s:property value="getQuantite()"/></td>
                        <td align="center"><s:property value="getEffectuer()"/></td>
                        <td align="right"><s:property value="getCatalogue().getPrixUnitaire()"/></td>
                        <td align="right"><s:property value="getResultEffectuer()"/></td>
                    </tr>
                </s:iterator>
                <s:iterator value="getOffre().getTacheSoumission().getTravaux()">
                    <tr>
                        <td><s:property value="getAllReference()"/></td>
                        <td><s:property value="getCatalogue().getDesignation()"/></td>
                        <td align="center"><s:property value="getCatalogue().getUnite()"/></td>
                        <td align="center"><s:property value="getQuantite()"/></td>
                        <td align="center"><s:property value="getEffectuer()"/></td>
                        <td align="right"><s:property value="getCatalogue().getPrixUnitaire()"/></td>
                        <td align="right"><s:property value="getResultEffectuer()"/></td>
                    </tr>
                </s:iterator>
            </table>
            <div class="x_panel">
                <div class="row tile_count">
                    <div class="col-md-4 col-sm-4 col-xs-6 tile_stats_count">
                        <span class="count_top"><i class="fa fa-money"></i> Total</span>
                        <div class="count"><s:property value="getOffre().getStatInitial().getTotalEffectuerString()"/></div>             
                    </div>
                    
                    <div class="col-md-4 col-sm-4 col-xs-6 tile_stats_count">
                        <span class="count_top"><i class="fa fa-money"></i> Remise</span>
                        <div class="count"><s:property value="getOffre().getSoumission().getRemise()"/> %</div>
                    </div>
                    <div class="col-md-4 col-sm-4 col-xs-6 tile_stats_count">
                        <span class="count_top"><i class="fa fa-money"></i> Valeur de la remise</span>
                        <div class="count"><s:property value="getOffre().getStatInitial().getValeurRemiseString()"/></div>
                    </div>
                    <div class="col-md-4 col-sm-4 col-xs-6 tile_stats_count">
                        <span class="count_top"><i class="fa fa-money"></i> Valeur apr&egrave;s la remise</span>
                        <div class="count"><s:property value="getOffre().getStatInitial().getValeurApresRemiseString()"/></div>
                    </div>
                    <div class="col-md-4 col-sm-4 col-xs-6 tile_stats_count">
                        <span class="count_top"><i class="fa fa-money"></i> TVA </span>
                        <div class="count"><s:property value="getOffre().getSoumission().getTva()"/> %</div>
                    </div>
                    <div class="col-md-4 col-sm-4 col-xs-6 tile_stats_count">
                        <span class="count_top"><i class="fa fa-money"></i> Valeur du TVA </span>
                        <div class="count"><s:property value="getOffre().getStatInitial().getValeurTVAString()"/></div>
                    </div>
                    <div class="col-md-4 col-sm-4 col-xs-6 tile_stats_count">
                        <span class="count_top"><i class="fa fa-money"></i> Total TTC</span>
                        <div class="count"><s:property value="getOffre().getStatInitial().getTotalTTCString()"/></div>
                    </div>


                </div>
            </div>
        </div>
    </div>

</div>
<script>
    jQuery(document).ready(function ()
    {
        $('.download').on('click', function ()
        {
            if (confirm("Voulez-vous vraiment telecharger la facture ?")) {
                window.location.replace("downloadFacture?idOffre=<s:property value="getIdOffre()"/>&referenceVentilation="+this.getAttribute('id')+"&responsable="+document.getElementById('responsable').value+"&fonction="+document.getElementById('fonction').value);
                
            }
        });
    });
</script>
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
</script>

<%@include file="template/default/footer.jsp" %>