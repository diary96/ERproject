<%-- 
    Document   : gestionTS
    Created on : 19 juin 2017, 17:00:13
    Author     : diary
--%>
<%@include file="template/default/header.jsp" %>
<!--<script src="../assets/gentella/vendors/jquery/dist/jquery.min.js"></script>-->

<div class="x_panel">
    <div class="x_title">
        <h2><a href="detailOffre?idOffre=<s:property value="getIdOffre()"/>" class="btn btn-default"><i class="fa fa-arrow-left"></i>  </a>Apercu du facture Initial du tickets numero  : <s:property value="getOffre().getTicket()"/> </h2>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">

        <div role="tabpanel" class="tab-pane fade active in" id="init" aria-labelledby="home-tab">
            <s:iterator value="ventillations">
                <a href="downloadFactureTS?idOffre=<s:property value="getIdOffre()"/>&referenceVentilation=<s:property value="getAllReference()"/>" class="btn btn-primary">Telecharger facture : <s:property value="getPourcentage()"/>%</a>
            </s:iterator>
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
                <s:iterator value="getOffre().getTacheSupplementaire().getTravaux()">
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
                        <div class="count"><s:property value="getOffre().getStatTS().getTotalEffectuer()"/></div>             
                    </div>
                    <div class="col-md-4 col-sm-4 col-xs-6 tile_stats_count">
                        <span class="count_top"><i class="fa fa-money"></i> TVA </span>
                        <div class="count"><s:property value="getOffre().getTravauxSupplementaire().getTva()"/> %</div>
                    </div>
                    <div class="col-md-4 col-sm-4 col-xs-6 tile_stats_count">
                        <span class="count_top"><i class="fa fa-money"></i> Valeur du TVA </span>
                        <div class="count"><s:property value="getOffre().getStatTS().getValeurTVA()"/></div>
                    </div>
                    <div class="col-md-4 col-sm-4 col-xs-6 tile_stats_count">
                        <span class="count_top"><i class="fa fa-money"></i> Remise</span>
                        <div class="count"><s:property value="getOffre().getTravauxSupplementaire().getRemise()"/> %</div>
                    </div>
                    <div class="col-md-4 col-sm-4 col-xs-6 tile_stats_count">
                        <span class="count_top"><i class="fa fa-money"></i> Valeur de la remise</span>
                        <div class="count"><s:property value="getOffre().getStatTS().getValeurRemise()"/></div>
                    </div>

                    <div class="col-md-4 col-sm-4 col-xs-6 tile_stats_count">
                        <span class="count_top"><i class="fa fa-money"></i> Total TTC</span>
                        <div class="count"><s:property value="getOffre().getStatTS().getTotalTTC()"/></div>
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

<%@include file="template/default/footer.jsp" %>