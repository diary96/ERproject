<%-- 
    Document   : gestionTS
    Created on : 19 juin 2017, 17:00:13
    Author     : diary
--%>
<%@include file="template/test/header.jsp" %>
<script src="../assets/gentella/vendors/jquery/dist/jquery.min.js"></script>

<div class="x_panel">
    <div class="x_title">
        <h2>Gestion des travaux supplementaires du tickets numero  :  <small>DSCI</small></h2>
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                <ul class="dropdown-menu" role="menu">
                    <li><a href="#">Settings 1</a>
                    </li>
                    <li><a href="#">Settings 2</a>
                    </li>
                </ul>
            </li>
            <li><a class="close-link"><i class="fa fa-close"></i></a>
            </li>
        </ul>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">


            <span class="section">Liste des T.S</span>

            <div id="table">
                <a class="btn btn-default"><i class="glyphicon glyphicon-plus"></i>Ajouter une ligne</a>
               
                <table cellspacing="1" class="table table-bordered">
                    <tr>
                        <th>Designation</th>
                        <th>Unite</th>
                        <th>Quantite</th>
                        <th>P.U</th>
                        <th>Prix Total</th>
                        
                    </tr>
                    <tr>
                        <td>Ouverture d'une chambre soud&eacute;e</td>
                        <td align="center">U</td>
                        <td align="center">3</td>
                        <td align="right">8000</td>
                        <td align="right">24000</td>
                        <td align="center"><a class="btn btn-primary btn-xs">Modifier</a></td>
                        <td align="center"><a class="btn btn-danger btn-xs">Supprimer</a></td>
                      

                    </tr>
                    <tr>
                        <td>Soudure d'une chambre L5T avec trappes en b&eacute;ton </td>
                        <td align="center">U</td>
                        <td align="center">1</td>
                        <td align="right">70000</td>
                        <td align="right">70000</td>
                        <td align="center"><a class="btn btn-primary btn-xs">Modifier</a></td>
                        <td align="center"><a class="btn btn-danger btn-xs">Supprimer</a></td>
                        

                    </tr>
                    <tr>
                        <td>Soudure d'une chambre L5T avec trappes en fonte s&eacute;curis&eacute;e</td>
                        <td align="center">U</td>
                        <td align="center">2</td>
                        <td align="right">98000</td>
                        <td align="right">196000</td>
                        <td align="center"><a class="btn btn-primary btn-xs">Modifier</a></td>
                        <td align="center"><a class="btn btn-danger btn-xs">Supprimer</a></td>
                        

                    </tr>
                    <tr>
                        <td>Frais de dossier PV</td>
                        <td align="center">U</td>
                        <td align="center">1</td>
                        <td align="right">20000</td>
                        <td align="right">2000</td>
                        <td align="center"><a class="btn btn-primary btn-xs">Modifier</a></td>
                        <td align="center"><a class="btn btn-danger btn-xs">Supprimer</a></td>

                    </tr>
                    <tr class="hide">
                        <td contenteditable="true"></td>
                        <td contenteditable="true"></td>
                        <td contenteditable="true"></td>
                        <td contenteditable="true" align="right"></td>
                        <td contenteditable="true" align="right"></td>
                        <td>
                            <a class="table-remove glyphicon glyphicon-remove"></a>
                        </td>

                    </tr>

                    <tr>
                        <td style='border-right:none;border-left:none;border-bottom:none;border-top:none'></td>
                        <td style='border-right:none;border-left:none;border-bottom:none;border-top:none'></td>
                        <td style='border-right:none;border-left:none;border-bottom:none;border-top:none'></td>
                        <td>Total</td>
                        <td align="right"></td>

                    </tr>
                    
                    <tr>
                        <td style='border-right:none;border-left:none;border-bottom:none;border-top:none'></td>
                        <td style='border-right:none;border-left:none;border-bottom:none;border-top:none'></td>
                        <td style='border-right:none;border-left:none;border-bottom:none;border-top:none'></td>
                        <td>TVA</td>
                        <td align="right"></td>

                    </tr>
                    <tr>
                        <td style='border-right:none;border-left:none;border-bottom:none;border-top:none'></td>
                        <td style='border-right:none;border-left:none;border-bottom:none;border-top:none'></td>
                        <td style='border-right:none;border-left:none;border-bottom:none;border-top:none'></td>
                        <td>Total TTC</td>
                        <td align="right"></td>

                    </tr>

                    </tr>
                    <!-- This is our clonable table line -->

                </table>
                
            </div>
           
    </div>
</div>
<script>
    setTimeout(function () {
        calcultTotal();
        autoCalcul();
        tva();
        
        totalTTC();

    }, 15);

    var $TABLE = $('#table');
    var $BTN = $('#export-btn');
    var $EXPORT = $('#export');

    $('.table-add').click(function () {
        var $clone = $TABLE.find('tr.hide').clone(true).removeClass('hide table-line');
//        $TABLE.parents('table').find("tr:last").before($clone);
        $TABLE.find('table tr:nth-last-child(6)').before($clone);
    });

    $('.table-remove').click(function () {
        $(this).parents('tr').detach();
    });

    $('.table-up').click(function () {
        var $row = $(this).parents('tr');
        if ($row.index() === 1)
            return; // Don't go above the header
        $row.prev().before($row.get(0));
    });

    $('.table-down').click(function () {
        var $row = $(this).parents('tr');
        $row.next().after($row.get(0));
    });

// A few jQuery helpers for exporting only
    jQuery.fn.pop = [].pop;
    jQuery.fn.shift = [].shift;
    function calcultTotal() {
        jQuery.fn.pop = [].pop;
        jQuery.fn.shift = [].shift;
        
        var $TABLE = $('#table');
        var $BTN = $('#export-btn');
        var $EXPORT = $('#export');


        var $rows = $TABLE.find('tr:not(:hidden)');
        var headers = [];
        var data = [];
        var totale = 0;

        // Get the headers (add special header logic here)
        $($rows.shift()).find('th:not(:empty)').each(function () {
            headers.push($(this).text().toLowerCase());
        });

        // Turn all existing rows into a loopable array
        for (var index = 0; index < $rows.length; index++) {
//        $rows.each(function (index) {

            var $td = $($rows[index]).find('td');
            var h = {};

            // Use the headers from earlier to name our hash keys        
            headers.forEach(function (header, i) {
                h[header] = $td.eq(i).text();
                if (index < $rows.length - 3) {
                    if (i == 4) {
                        totale += Number($td.eq(i).text());
                    }
                }
            });
            data.push(h);
        }
        var lastRows = $rows[$rows.length - 3];
        var $td = $(lastRows).find('td');
        $td.eq(4).text(totale);
    }
    function tva(){
        jQuery.fn.pop = [].pop;
        jQuery.fn.shift = [].shift;
        var $TABLE = $('#table');
        var $BTN = $('#export-btn');
        var $EXPORT = $('#export');


        var $rows = $TABLE.find('tr:not(:hidden)');
        var headers = [];
        var data = [];
        
        var pourcentage = 20;
        var total =0;
        var rowTotal = $rows[$rows.length - 3];
        var rowTVA = $rows[$rows.length - 2];
        var tdTotal = $(rowTotal).find('td');
        var tdTVA = $(rowTVA).find('td');
        total = tdTotal.eq(4).text();
        console.log(total);
        tdTVA.eq(4).text(total*pourcentage/100);
    }
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
            if (index < $rows.length - 6) {
                qte = Number($td.eq(2).text());
                pu = Number($td.eq(3).text());
                $td.eq(4).text(qte * pu);

            }


        }
    }
    function totalTTC(){
         jQuery.fn.pop = [].pop;
        jQuery.fn.shift = [].shift;
        var $TABLE = $('#table');
        var $BTN = $('#export-btn');
        var $EXPORT = $('#export');


        var $rows = $TABLE.find('tr:not(:hidden)');
        var headers = [];
        var data = [];
        
        var pourcentage = 20;
        var total =0;
        var tva =0;
        var rowTotal = $rows[$rows.length - 3];
       
        var rowTVA = $rows[$rows.length - 2];
        var rowTTC = $rows[$rows.length - 1];
        var tdTotal = $(rowTotal).find('td');
       
        var tdTVA = $(rowTVA).find('td');
        var tdTTC = $(rowTTC).find('td');
        total = tdTotal.eq(4).text();      
         tdTVA.eq(4).text(total*pourcentage/100);
        tva = tdTVA.eq(4).text();
        tdTTC.eq(4).text(Number(total)+Number(tva));
        
    }
    function remise(){
         jQuery.fn.pop = [].pop;
        jQuery.fn.shift = [].shift;
        var $TABLE = $('#table');
        var $BTN = $('#export-btn');
        var $EXPORT = $('#export');


        var $rows = $TABLE.find('tr:not(:hidden)');
        var headers = [];
        var data = [];
        
        var remiseP = 0;
        var remise = 0;
        var total =0;
        var tva =0;
        var rowTotal = $rows[$rows.length - 6];
        var rowRemiseP = $rows[$rows.length - 5];
        var rowRemise = $rows[$rows.length - 4];
        var rowRemiseV = $rows[$rows.length - 3];
        var tdTotal = $(rowTotal).find('td');
        var tdRemiseP = $(rowRemiseP).find('td');
        var tdRemiseV = $(rowRemiseV).find('td');
        var tdRemise = $(rowRemise).find('td');
        total = tdTotal.eq(4).text();  
        remiseP = tdRemiseP.eq(4).text();
        remise = tdRemiseP.eq(4).text();
        
        if(remiseP<0||isNaN(remise)){
            tdRemiseP.eq(4).text("0");
            remise=0;
        }
        var totalRemise = Number(total)*remiseP/100;
        tdRemiseV.eq(4).text(total-totalRemise);
        tdRemise.eq(4).text(totalRemise);      
    }
</script>

<%@include file="template/test/footer.jsp" %>