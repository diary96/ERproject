<%@include file="template/default/header.jsp" %>
<div class="x_panel">
    <div class="x_title">
        <h2><a href="detailOffre?idOffre=<s:property value="getIdOffre()"/>" class="btn btn-default"><i class="fa fa-arrow-left"></i>  </a> <s:property value="getOffre().getNomProjet()"/> , Ticket numero : <s:property value="getOffre().getTicket()"/> </h2>
        
        <div class="clearfix"></div>
    </div>
    <div class="x_content">

        <table width=25 class="table table-striped">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Reference</th>
                    <th>Date et Heure</th>
                    <th>Action</th>
                    <th>Reference de l'offre</th>
                    <th>Utilisateur</th>

                </tr>
            </thead>
            <tbody>
                <s:iterator value="historiques" status="historique">
                    <tr  height='25px'>
                        <th style="padding: 2px;" scope="row"><s:property value="#historique.index+1"/></th>
                        <td style="padding: 2px;" ><s:property value="getAllReference()"/></td>
                        <td style="padding: 2px;" ><s:property value="getDate()"/></td>
                        <td style="padding: 2px;" ><s:property value="getDescription()"/></td>
                        <td style="padding: 2px;" ><s:property value="getReferenceExterieur()"/></td>
                        <td style="padding: 2px;" ><s:property value="getUser().getPrenom()"/> <s:property value="getUser().getNom()"/></td>
                        
                    </tr>
                </s:iterator>
                

            </tbody>
        </table>

    </div>
    <div class="row">
        <div class="col-sm-10">
        </div>
        <div class="col-sm-2">
        </div>

    </div>
</div>
<%@include file="template/default/footer.jsp" %>