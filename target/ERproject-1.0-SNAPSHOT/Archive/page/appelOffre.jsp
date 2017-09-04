<%@include file="template/default/header.jsp" %>
<script src="Archive/assets/gentella/vendors/jquery/dist/jquery.min.js"></script>
<div class="x_panel">
    <div class="x_title">
        <h2>Nouvelle Offre <small></small></h2>

        <div class="clearfix"></div>
         
    </div>
    <div class="x_content">
        <%@include file="template/default/Erreur.jsp" %>
        <form action="saveOffre" methode="POST" class="form-horizontal form-label-left" novalidate="">

            <span class="section">Information de l'offre</span>

            <div class="item form-group">
                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="ticket">Ticket :<span class="required">*</span>
                </label>
                <div class="col-md-6 col-sm-6 col-xs-12">
                    <input value="<s:property value="getTicket()"/>" id="ticket" class="form-control col-md-7 col-xs-12" data-validate-length-range="6" data-validate-words="2" name="ticket"  required="required" type="text">
                </div>
            </div>
            <div class="item form-group">
                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="project">Nom du projet <span class="required">*</span>
                </label>
                <div class="col-md-6 col-sm-6 col-xs-12">
                    <input value="<s:property value="getProjet()"/>" type="text" id="email" name="projet" required="required" class="form-control col-md-7 col-xs-12">
                </div>
            </div>
            <div class="item form-group">
                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="localisation">Localisation <span class="required">*</span>
                </label>
                <div class="col-md-6 col-sm-6 col-xs-12">
                    <input value="<s:property value="getLocalisation()"/>"type="email" id="email2" name="localisation" data-validate-linked="email" required="required" class="form-control col-md-7 col-xs-12">
                </div>
            </div>
            <div class="item form-group">
                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="deadline">Deadline  <span class="required"></span>
                </label>
                <div class="col-md-6 col-sm-6 col-xs-12">
                    <input value="<s:property value="getDeadLine()"/>" type="date" id="number" name="deadLine" required="required" data-validate-minmax="10,100" class="form-control col-md-7 col-xs-12">
                </div>
            </div>
            <div class="item form-group">
                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="time">Heure du Deadline  <span class="required"></span>
                </label>
                <div class="col-md-6 col-sm-6 col-xs-12">
                    <input value="<s:property value="getTime()"/>" type="time" id="number" name="time" required="required" data-validate-minmax="10,100" class="form-control col-md-7 col-xs-12">
                </div>
            </div>
            <div class="item form-group">
                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="dateTravaux">Date prevu du traveau <span class="required"></span>
                </label>
                <div class="col-md-6 col-sm-6 col-xs-12">
                    <input value="<s:property value="getDateTravaux()"/>" type="date" id="website" name="dateTravaux" required="required" class="form-control col-md-7 col-xs-12">
                </div>
            </div>
            <div class="item form-group">
                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="type">Nom du departement <span class="required">*</span>
                </label>
                <div class="col-md-6 col-sm-6 col-xs-12">
                    <select id="heard" name="type"class="form-control" required="">
                        <s:iterator value="typeOffres">
                            <option value="<s:property value="getId()"/>"><s:property value="getNom()"/></option>
                        </s:iterator>
                        <
                    </select>

                </div>
            </div>    
            <div class="ln_solid"></div>
            <div class="form-group">
                <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                    <button type="submit" class="btn btn-success">Enregistrer</button>
                    <button class="btn btn-danger" type="button">Annuler</button>


                </div>
            </div>

        </form>
       
    <%@include file="template/default/footer.jsp" %>