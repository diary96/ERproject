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

        <title>Test | Entreprise RANDRIANARISOA </title>
        <link rel="shortcut icon" href="login_style/assets/ico/icon_admin.png">
        <!-- Bootstrap -->

        <link href="../assets/gentella/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome -->
        <link href="../assets/gentella/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
        <!-- NProgress -->
        <link href="../assets/gentella/vendors/nprogress/nprogress.css" rel="stylesheet">
        <!-- iCheck -->
        <link href="../assets/gentella/vendors/iCheck/skins/flat/green.css" rel="stylesheet">

        <!-- bootstrap-progressbar -->
        <link href="../assets/gentella/vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet">
        <!-- JQVMap -->
        <link href="../assets/gentella/vendors/jqvmap/dist/jqvmap.min.css" rel="stylesheet"/>
        <!-- bootstrap-daterangepicker -->
        <link href="../assets/gentella/vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">

        <!-- Custom Theme Style -->
        <link href="../assets/gentella/build/css/custom.min.css" rel="stylesheet">
        <link href='../assets/css/custome.css" rel="stylesheet"'>
        <link href='../assets/css/style_table.css" rel="stylesheet"'>
        <script src="../assets/gentella/vendors/jquery/dist/jquery.min.js"></script>
        
        <!-- test -->
       


     
    </head>
    <body class="nav-md">
        <div class="container body">
            <div class="main_container">
                <div class="col-md-3 left_col">
                    <div class="left_col scroll-view">
                        <div class="navbar nav_title" style="border: 0;">
                            <a href="home" class="site_title"><i class="fa fa-home"></i> <span>E.R</span></a>
                        </div>

                        <div class="clearfix"></div>
                        <!-- menu profile quick info -->
                        <div class="profile clearfix">
                            <div class="profile_pic">
                                <img src="../assets/gentella/img/admin.ico" alt="..." class="img-circle profile_img">
                            </div>
                            <div class="profile_info">
                                <h2>Diary RASOLOFOMANANA</h2>
                            </div>
                        </div>
                        <!-- /menu profile quick info -->

                        <br />

                        <!-- sidebar menu -->
                        <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
                            <div class="menu_section">
                                <h3>Menu</h3>
                                <ul class="nav side-menu">
                                    <li><a href="homeAdmin"><i class="fa fa-home"></i> Accueil </a>

                                    </li>
                                    <li><a><i class="fa fa-table"></i>Offres par Departements <span class="fa fa-chevron-down"></span></a>
                                        <ul class="nav child_menu">
                                            <li><a href="#">Tout les offres</a></li>
                                            <li><a href="#">DSCI-TANA</a></li>
                                            <li><a href="#">DSCI-Province</a></li>
                                            <li><a href="#">Cuivre</a></li>
                                            <li><a href="#">DTG</a></li>

                                        </ul>
                                    </li>
                                    <li><a><i class="fa fa-bars"></i> Projets <span class="fa fa-chevron-down"></span></a>
                                        <ul class="nav child_menu">
                                            <li><a href="listeTeleoperateurAdmin">Liste des projets</a></li>
                                            <li><a href="statistique">Nouveau projet</a></li>
                                          
                                        </ul>
                                    </li>
                                    <li><a><i class="fa fa-user-o"></i> Employes <span class="fa fa-chevron-down"></span></a>
                                        <ul class="nav child_menu">
                                            <li><a href="listeTeleoperateurAdmin">Liste des teleoperateurs</a></li>
                                            <li><a href="statistique">Perfomance des employes</a></li>
                                            <li><a href="newTeleoperateur">Nouveau teleoperateur</a></li>
                                          
                                        </ul>
                                    </li>
                                    <li><a><i class="fa fa-phone"></i> Appels <span class="fa fa-chevron-down"></span></a>
                                        <ul class="nav child_menu">
                                            <li><a href="appelAdmin">Liste des appels</a></li>
                                            <li><a href="listAllAdmin">Recherche avance</a></li>

                                        </ul>
                                    </li>
                                    <li><a><i class="fa fa-file-pdf-o"></i> PDF <span class="fa fa-chevron-down"></span></a>
                                        <ul class="nav child_menu">
                                            <li><a href="pdfTeleoperateur">Generer l'historique des teleoperateurs</a></li>

                                        </ul>
                                    </li>
                                </ul>
                            </div>
                            <!-- /sidebar menu -->

                            <!-- /menu footer buttons -->
                            <div class="sidebar-footer hidden-small">
                                <a data-toggle="tooltip" data-placement="top" title="Settings">
                                    <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
                                </a>

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
                                        <img src="../assets/gentella/img/admin.ico" alt=""><s:property value="manager.getPrenommanager()"/> <s:property value="manager.getNommanager()"/>
                                        <span class=" fa fa-angle-down"></span>
                                    </a>
                                    <ul class="dropdown-menu dropdown-usermenu pull-right">
                                        <li><a href="javascript:;"> Profile</a></li>
                                        <li>
                                            <a href="javascript:;">
                                                <span>Settings</span>
                                            </a>
                                        </li>
                                        <li><a href="deconnexion"><i class="fa fa-sign-out pull-right"></i> SE DECONNECTER</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>
                <div class="right_col" role="main">
