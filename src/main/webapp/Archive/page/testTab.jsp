<%-- 
    Document   : testTab
    Created on : 16 juin 2017, 11:28:33
    Author     : diary
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href='../assets/css/style_table.css" rel="stylesheet"'>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">

            <div id="table" class="table-editable">
                <span class="table-add glyphicon glyphicon-plus"></span>
                <table class="table">
                    <tr>
                        <th>Name</th>
                        <th>Value</th>
                        <th></th>
                        <th></th>
                    </tr>
                    <tr>
                        <td contenteditable="true">Stir Fry</td>
                        <td contenteditable="true">stir-fry</td>
                        <td>
                            <span class="table-remove glyphicon glyphicon-remove"></span>
                        </td>
                        <td>
                            <span class="table-up glyphicon glyphicon-arrow-up"></span>
                            <span class="table-down glyphicon glyphicon-arrow-down"></span>
                        </td>
                    </tr>
                    <!-- This is our clonable table line -->
                    <tr class="hide">
                        <td contenteditable="true">Untitled</td>
                        <td contenteditable="true">undefined</td>
                        <td>
                            <span class="table-remove glyphicon glyphicon-remove"></span>
                        </td>
                        <td>
                            <span class="table-up glyphicon glyphicon-arrow-up"></span>
                            <span class="table-down glyphicon glyphicon-arrow-down"></span>
                        </td>
                    </tr>
                </table>
            </div>

            <button id="export-btn" class="btn btn-primary">Export Data</button>
            <p id="export"></p>
        </div>
    </body>
    
    <script src="../assets/gentella/vendors/jquery/dist/jquery.min.js"></script>
    <script>
        var $TABLE = $('#table');
        var $BTN = $('#export-btn');
        var $EXPORT = $('#export');

        $('.table-add').click(function () {
            var $clone = $TABLE.find('tr.hide').clone(true).removeClass('hide table-line');
            $TABLE.find('table').append($clone);
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

        $BTN.click(function () {
            var $rows = $TABLE.find('tr:not(:hidden)');
            var headers = [];
            var data = [];

            // Get the headers (add special header logic here)
            $($rows.shift()).find('th:not(:empty)').each(function () {
                headers.push($(this).text().toLowerCase());
            });

            // Turn all existing rows into a loopable array
            $rows.each(function () {
                var $td = $(this).find('td');
                var h = {};

                // Use the headers from earlier to name our hash keys
                headers.forEach(function (header, i) {
                    h[header] = $td.eq(i).text();
                });

                data.push(h);
            });

            // Output the result
            $EXPORT.text(JSON.stringify(data));
        });
    </script>
</html>
