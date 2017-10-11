<%-- 
    Document   : header
    Created on : 3 juin 2017, 18:25:22
    Author     : diary
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Meta, title, CSS, favicons, etc. -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title><s:property value="titre" /> | E.R </title>
        <link rel="shortcut icon" href="login_style/assets/ico/icon_admin.png">
        <!-- Bootstrap -->

        <link href="Archive/assets/gentella/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome -->
        <link href="Archive/assets/gentella/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
        <!-- NProgress -->
        <link href="Archive/assets/gentella/vendors/nprogress/nprogress.css" rel="stylesheet">
        <!-- iCheck -->
        <link href="Archive/assets/gentella/vendors/iCheck/skins/flat/green.css" rel="stylesheet">

        <!-- bootstrap-progressbar -->
        <link href="Archive/assets/gentella/vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet">
        <!-- JQVMap -->
        <link href="Archive/assets/gentella/vendors/jqvmap/dist/jqvmap.min.css" rel="stylesheet"/>
        <!-- bootstrap-daterangepicker -->
        <link href="Archive/assets/gentella/vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">

        <!-- Custom Theme Style -->
        <link href="Archive/assets/gentella/build/css/custom.min.css" rel="stylesheet">
        <link href="Archive/assets/css/custome.css" rel="stylesheet">
        <link href='Archive/assets/css/style_table.css" rel="stylesheet"'>
        <script src="Archive/assets/gentella/vendors/jquery/dist/jquery.min.js"></script>
        
        <link href="Archive/assets/css/gps.css" rel="stylesheet">
        <!-- test -->
       


     
    </head>
    <body class="nav-md footer_fixed">
        <div class="container body">
            <div class="main_container">
                <div class="col-md-3 left_col">
                    <div class="left_col scroll-view">
                        <div class="navbar nav_title" style="border: 0;">
                            <a href="accueil" class="site_title"><i class="fa fa-home"></i> <span>E.R</span></a>
                        </div>

                        <div class="clearfix"></div>
                        <!-- menu profile quick info -->
                        <div class="profile clearfix">
                            <div class="profile_pic">
                                <img src="Archive/assets/gentella/img/admin.ico" alt="..." class="img-circle profile_img">
                            </div>
                            <div class="profile_info">
                                <h2><s:property value="user.getPrenom()"/> <s:property value="user.getNom()"/></h2>
                            </div>
                        </div>
                        <!-- /menu profile quick info -->

                        <br />

                        <!-- sidebar menu -->
                           <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
                            <div class="menu_section">
                                <h3>Menu</h3>
                                <ul class="nav side-menu">
                                    <li><a href="accueil"><i class="fa fa-home"></i> Accueil </a>
                                    <li><a href="newOffre"><i class="fa fa-plus"></i> Nouvelle Offre </a>

                                    </li>
                                    <li><a><i class="fa fa-table"></i>Liste <span class="fa fa-chevron-down"></span></a>
                                        <ul class="nav child_menu">
                                            <li><a href="accueil">Offre</a></li>
                                           
                                            

                                        </ul>
                                    </li>
                                    <s:if test="getUser().getNiveau()>=2">
                                        <li><a><i class="fa fa-table"></i>Base <span class="fa fa-chevron-down"></span></a>
                                            <ul class="nav child_menu">
                                                <s:if test="getUser().getNiveau()>=5">
                                                    <li><a href="listeUser">Employee</a></li>
                                                </s:if>
                                                <li><a href="listeCatalogue">Catalogue</a></li>
                                                <li><a href="listeHorsCatalogue">Hors Catalogue</a></li>
                                                <li><a href="listeTypeFichier">Gestion Type d'archive</a></li>
                                                <s:if test="getUser().getNiveau()>=4">
                                                    <li><a href="listeFacturation">Liste des factures et paiement</a></li>
                                                    <li><a href="listeFacturationTs">Liste des factures et paiement des travaux suppl&eacute;mentaires</a></li>
                                                </s:if>


                                            </ul>
                                        </li>
                                    </s:if>
                                    
                                </ul>
                            </div>
                            <!-- /sidebar menu -->

                            <!-- /menu footer buttons -->
                            <div class="sidebar-footer hidden-small">
                                

                                
                                <a data-toggle="tooltip" data-placement="top" title="Se deconnecter" href="deconnexion">
                                    <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
                                </a>
                            </div>
                            <!-- /menu footer buttons -->
                        </div>
                    
                    </div>
                </div>
                <!-- top navigation -->
                <div class="top_nav">
                    <div class="nav_menu">
                        <nav>
                            <div class="nav toggle">
                                <a id="menu_toggle"><i class="fa fa-bars"></i></a>
                            </div>

                            <ul class="nav navbar-nav navbar-right">
                                <li class="">
                                    <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                        <img src="Archive/assets/gentella/img/admin.ico" alt=""><s:property value="user.getPrenom()"/> <s:property value="user.getNom()"/>
                                        <span class=" fa fa-angle-down"></span>
                                    </a>
                                    <ul class="dropdown-menu dropdown-usermenu pull-right">
                                        
                                        <li><a href="newUser?idUser=<s:property value="getUser().getId()"/>"> Modifier mon profile</a></li>
                                        <li><a href="deconnexion"><i class="fa fa-sign-out pull-right"></i> SE DECONNECTER</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>
                <div class="right_col" role="main">
