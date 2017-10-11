<%-- 
    Document   : gestionTS
    Created on : 19 juin 2017, 17:00:13
    Author     : diary
--%>
<%@include file="template/default/header.jsp" %>
<!--<script src="../assets/gentella/vendors/jquery/dist/jquery.min.js"></script>-->

<div class="x_panel">
    <div class="x_title">
        <h2><a href="detailOffre?idOffre=<s:property value="getIdOffre()"/>" class="btn btn-default"><i class="fa fa-arrow-left"></i>  </a>  Gestion des travaux <s:property value="getType()"/>  du tickets numero  : <s:property value="getOffre().getTicket()" /> </h2>

        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <%@include file="template/default/Erreur.jsp" %>

        <span class="section">Liste des Travaux <s:property value="getType()"/></span>

        <div id="table">
            
                
            <s:if test="offre.getStatu()==limit">
                
                <a href="newTache?idOffre=<s:property value="getIdOffre()"/>&url=<s:property value="getUrl()"/>&type=<s:property value="getType()"/>" class="btn btn-default"><i class="glyphicon glyphicon-plus"></i>Ajouter une ligne</a>
                <s:if test="getType().equalsIgnoreCase(@com.er.erproject.data.ReferenceType@SOUMISSION)"> 

                    <a href="parametre?idOffre=<s:property value="getIdOffre()"/>&type=<s:property value="@com.er.erproject.data.ReferenceType@SOUMISSION"/>&url=<s:property value="getUrl()"/>" class="btn btn-primary"><i class="fa fa-cogs"></i>  Taxe et Remise</a>

                </s:if>
                <s:if test="getType().equalsIgnoreCase(@com.er.erproject.data.ReferenceType@TAVAUX_SUPPLEMENTAIRE)"> 

                    <a href="parametre?idOffre=<s:property value="getIdOffre()"/>&type=<s:property value="@com.er.erproject.data.ReferenceType@TAVAUX_SUPPLEMENTAIRE"/>&url=<s:property value="getUrl()"/>" class="btn btn-primary"><i class="fa fa-cogs"></i>  Taxe et Remise</a>

                </s:if>
            </s:if>

            <table cellspacing="1" class="table table-bordered">
                <tr>
                    <td>Reference du travaux</td>
                    <th>Designation</th>
                    <th>Unite</th>
                    <th>Quantite</th>
                    <th>P.U</th>
                    <th>Prix Total</th>

                </tr>

                <s:iterator value="getOffre().getTacheinitials().getTravaux()">

                    <tr>
                        <td><s:property value="getAllReference()"/></td>
                        <td><s:property value="getCatalogue().getDesignation()"/></td>
                        <td align="center"><s:property value="getCatalogue().getUnite()"/></td>
                        <td align="center"><s:property value="getQuantite()"/></td>
                        <td align="right"><s:property value="getCatalogue().getPrixUnitaire()"/></td>
                        <td align="right"></td>


                        <s:if test="getStat()==@com.er.erproject.data.StatuReference@OFFRE">
                            <td align="center"><a href="deleteTravaux?url=gestionInitial&idOffre=<s:property value="getIdOffre()"/>&reference=<s:property value="getAllReference()"/>" class="btn btn-danger btn-xs">Supprimer</a></td>
                        </s:if>
                    </tr>
                </s:iterator>
                <s:iterator value="getOffre().getTacheSoumission().getTravaux()">

                    <tr>
                        <td><s:property value="getAllReference()"/></td>
                        <td><s:property value="getCatalogue().getDesignation()"/></td>
                        <td align="center"><s:property value="getCatalogue().getUnite()"/></td>
                        <td align="center"><s:property value="getQuantite()"/></td>
                        <td align="right"><s:property value="getCatalogue().getPrixUnitaire()"/></td>
                        <td align="right"></td>


                        <s:if test="getStat()==@com.er.erproject.data.StatuReference@SOUMISSION">
                            <td align="center"><a href="deleteTravaux?url=gestionSoumission&idOffre=<s:property value="getIdOffre()"/>&reference=<s:property value="getAllReference()"/>" class="btn btn-danger btn-xs">Supprimer</a></td>
                        </s:if>
                    </tr>
                </s:iterator> <!-- This is our clonable table line -->
                <s:iterator value="getOffre().getTacheSupplementaire().getTravaux()">

                    <tr>
                        <td><s:property value="getAllReference()"/></td>
                        <td><s:property value="getCatalogue().getDesignation()"/></td>
                        <td align="center"><s:property value="getCatalogue().getUnite()"/></td>
                        <td align="center"><s:property value="getQuantite()"/></td>
                        <td align="right"><s:property value="getCatalogue().getPrixUnitaire()"/></td>
                        <td align="right"></td>


                        <s:if test="getStat()==@com.er.erproject.data.StatuReference@TRAVAUX">
                            <td align="center"><a href="deleteTravaux?url=gestionSupplementaire&idOffre=<s:property value="getIdOffre()"/>&reference=<s:property value="getAllReference()"/>" class="btn btn-danger btn-xs">Supprimer</a></td>
                        </s:if>
                    </tr>
                </s:iterator> 

            </table >


        </div>



    </div>
</div>
<s:if test="getType().equalsIgnoreCase(@com.er.erproject.data.ReferenceType@TAVAUX_SUPPLEMENTAIRE)">
    <div class="x_panel">
        <div class="row tile_count">
            <div class="col-md-4 col-sm-4 col-xs-6 tile_stats_count">
                <span class="count_top"><i class="fa fa-money"></i> Total</span>
                <div class="count"><s:property value="getOffre().getTravauxSupplementaire().getTotalString()"/></div>             
            </div>
            
            <div class="col-md-4 col-sm-4 col-xs-6 tile_stats_count">
                <span class="count_top"><i class="fa fa-money"></i> Remise</span>
                <div class="count"><s:property value="getOffre().getTravauxSupplementaire().getRemise()"/> %</div>
            </div>
            <div class="col-md-4 col-sm-4 col-xs-6 tile_stats_count">
                <span class="count_top"><i class="fa fa-money"></i> Valeur de la remise</span>
                <div class="count"><s:property value="getOffre().getTravauxSupplementaire().getValeurRemiseString()"/></div>
            </div>
            <div class="col-md-4 col-sm-4 col-xs-6 tile_stats_count">
                <span class="count_top"><i class="fa fa-money"></i> Valeur apres la remise</span>
                <div class="count"><s:property value="getOffre().getTravauxSupplementaire().getValeurApresRemiseString()"/></div>
            </div>
            <div class="col-md-4 col-sm-4 col-xs-6 tile_stats_count">
                <span class="count_top"><i class="fa fa-money"></i> TVA </span>
                <div class="count"><s:property value="getOffre().getTravauxSupplementaire().getTva()"/> %</div>
            </div>
            <div class="col-md-4 col-sm-4 col-xs-6 tile_stats_count">
                <span class="count_top"><i class="fa fa-money"></i> Valeur du TVA </span>
                <div class="count"><s:property value="getOffre().getTravauxSupplementaire().getValeurTVAString()"/></div>
            </div>
            <div class="col-md-4 col-sm-4 col-xs-6 tile_stats_count">
                <span class="count_top"><i class="fa fa-money"></i> Total TTC</span>
                <div class="count"><s:property value="getOffre().getTravauxSupplementaire().getTotalTTCString()"/></div>
            </div>


        </div>
    </div>
    <div class="clearfix"></div>
</s:if>
<s:if test="getType().equalsIgnoreCase(@com.er.erproject.data.ReferenceType@SOUMISSION)">
    <div class="x_panel">
        <div class="row tile_count">
            <div class="col-md-4 col-sm-4 col-xs-6 tile_stats_count">
                <span class="count_top"><i class="fa fa-money"></i> Total</span>
                <div class="count"><s:property value="getOffre().getSoumission().getTotalString()"/></div>             
            </div>
            
            <div class="col-md-4 col-sm-4 col-xs-6 tile_stats_count">
                <span class="count_top"><i class="fa fa-money"></i> Remise</span>
                <div class="count"><s:property value="getOffre().getSoumission().getRemise()"/> %</div>
            </div>
            <div class="col-md-4 col-sm-4 col-xs-6 tile_stats_count">
                <span class="count_top"><i class="fa fa-money"></i> Valeur de la remise</span>
                <div class="count"><s:property value="getOffre().getSoumission().getValeurRemiseString()"/></div>
            </div>
            <div class="col-md-4 col-sm-4 col-xs-6 tile_stats_count">
                <span class="count_top"><i class="fa fa-money"></i> Valeur apres la remise</span>
                <div class="count"><s:property value="getOffre().getSoumission().getValeurApresRemiseString()"/></div>
            </div>
            <div class="col-md-4 col-sm-4 col-xs-6 tile_stats_count">
                <span class="count_top"><i class="fa fa-money"></i> TVA </span>
                <div class="count"><s:property value="getOffre().getSoumission().getTva()"/> %</div>
            </div>
            <div class="col-md-4 col-sm-4 col-xs-6 tile_stats_count">
                <span class="count_top"><i class="fa fa-money"></i> Valeur du TVA </span>
                <div class="count"><s:property value="getOffre().getSoumission().getValeurTVAString()"/></div>
            </div>
            <div class="col-md-4 col-sm-4 col-xs-6 tile_stats_count">
                <span class="count_top"><i class="fa fa-money"></i> Total TTC</span>
                <div class="count"><s:property value="getOffre().getSoumission().getTotalTTCString()"/></div>
            </div>


        </div>
    </div>
    <div class="clearfix"></div>
</s:if>
<script>
    autoCalcul();
    function autoCalcul() {
        jQuery.fn.pop = [].pop;
        jQuery.fn.shift = [].shift;
        var $TABLE = $('#table');
        var $BTN = $('#export-btn');
        var $EXPORT = $('#export');


        var $rows = $TABLE.find('tr:not(:hidden)');
        var headers = [];
        var data = [];


        // Get the headers (add special header logic here)
        $($rows.shift()).find('th:not(:empty)').each(function () {
            headers.push($(this).text().toLowerCase());
        });

        // Turn all existing rows into a loopable array
        for (var index = 0; index < $rows.length; index++) {
//        $rows.each(function (index) {
            var totale = 0;
            var qte = 0;
            var pu = 0;
            var $td = $($rows[index]).find('td');
            var h = {};
            if (index < $rows.length) {
                qte = Number($td.eq(3).text());
                pu = Number($td.eq(4).text());
                $td.eq(5).text(qte * pu);
            }
        }
    }
</script>

<%@include file="template/default/footer.jsp" %>