<%--
  Created by IntelliJ IDEA.
  User: foraz
  Date: 2021/4/22
  Time: 23:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>DRMS</title>
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="./images/favicon.png">
    <link href="./vendor/owl-carousel/owl.carousel.css" rel="stylesheet">
    <link href="./vendor/datatables/css/jquery.dataTables.min.css" rel="stylesheet">
    <link href="./vendor/bootstrap-select/dist/css/bootstrap-select.min.css" rel="stylesheet">
    <link href="./css/style.css" rel="stylesheet">
    <link href="https://cdn.lineicons.com/2.0/LineIcons.css" rel="stylesheet">

</head>
<body>

<!--*******************
    Preloader start
********************-->
<div id="preloader">
    <div class="sk-three-bounce">
        <div class="sk-child sk-bounce1"></div>
        <div class="sk-child sk-bounce2"></div>
        <div class="sk-child sk-bounce3"></div>
    </div>
</div>
<!--*******************
    Preloader end
********************-->

<!--**********************************
    Main wrapper start
***********************************-->
<div id="main-wrapper">

    <!--**********************************
        Nav header start
    ***********************************-->
    <div class="nav-header">
        <a href="index.jsp" class="brand-logo">
            <img class="logo-abbr" src="./images/logo.png" alt="">
            <img class="logo-compact" src="./images/logo-text.png" alt="">
            <img class="brand-title" src="./images/logo-text.png" alt="">
        </a>

        <div class="nav-control">
            <div class="hamburger">
                <span class="line"></span><span class="line"></span><span class="line"></span>
            </div>
        </div>
    </div>
    <!--**********************************
        Nav header end
    ***********************************-->

    <!--**********************************
        Header start
    ***********************************-->
    <div class="header">
        <div class="header-content">
            <nav class="navbar navbar-expand">
                <div class="collapse navbar-collapse justify-content-between">
                    <div class="header-left">
                        <div class="dashboard_bar">
                            文件列表
                        </div>
                    </div>

                    <ul class="navbar-nav header-right">
                        <li class="nav-item dropdown header-profile">
                            <a class="nav-link" href="javascript:;" role="button" data-toggle="dropdown">
                                <img src="images/profile/Azure.png" width="20" alt=""/>
                                <div class="header-info">
                                    <span>Hello,<strong> <%out.println(session.getAttribute("uid"));%></strong></span>
                                </div>
                            </a>
                            <div class="dropdown-menu dropdown-menu-right">
<%--                                <a href="./app-profile.html" class="dropdown-item ai-icon">--%>
<%--                                    <svg id="icon-user1" xmlns="http://www.w3.org/2000/svg" class="text-primary" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path><circle cx="12" cy="7" r="4"></circle></svg>--%>
<%--                                    <span class="ml-2">Profile </span>--%>
<%--                                </a>--%>
<%--                                <a href="./email-inbox.html" class="dropdown-item ai-icon">--%>
<%--                                    <svg id="icon-inbox" xmlns="http://www.w3.org/2000/svg" class="text-success" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M4 4h16c1.1 0 2 .9 2 2v12c0 1.1-.9 2-2 2H4c-1.1 0-2-.9-2-2V6c0-1.1.9-2 2-2z"></path><polyline points="22,6 12,13 2,6"></polyline></svg>--%>
<%--                                    <span class="ml-2">Inbox </span>--%>
<%--                                </a>--%>
                                <a href="./login.html" class="dropdown-item ai-icon">
                                    <svg id="icon-logout" xmlns="http://www.w3.org/2000/svg" class="text-danger" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"></path><polyline points="16 17 21 12 16 7"></polyline><line x1="21" y1="12" x2="9" y2="12"></line></svg>
                                    <span class="ml-2">Logout </span>
                                </a>
                            </div>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
    </div>
    <!--**********************************
        Header end ti-comment-alt
    ***********************************-->

    <!--**********************************
                Sidebar start
    ***********************************-->
    <div class="deznav">
        <div class="deznav-scroll">
            <ul class="metismenu" id="menu">
                <li><a class="ai-icon" href="index.jsp" aria-expanded="false">
                    <i class="flaticon-381-notepad"></i>
                    <span class="nav-text">文件列表</span>
                </a>
                </li>
                <li><a class="ai-icon" href="upload-file.jsp" aria-expanded="false">
                    <i class="flaticon-381-pencil"></i>
                    <span class="nav-text">文件上传</span>
                </a>
                </li>
            </ul>
        </div>
    </div>
    <!--**********************************
        Sidebar end
    ***********************************-->

    <!--**********************************
        Content body start
    ***********************************-->
    <div class="content-body">
        <!-- row -->
        <div class="container-fluid">
<%--            <div class="form-head d-flex mb-3 mb-md-4 align-items-start">--%>
<%--                <div class="mr-auto d-none d-lg-block">--%>
<%--                    <a href="upload-file.jsp" class="btn btn-primary btn-rounded">Upload</a>--%>
<%--                </div>--%>
<%--                <!--<form action="/DRMS/SearchFile" method="post">--%>
<%--                    <div class="input-group search-area ml-auto d-inline-flex mr-3">--%>
<%--                        <input type="text" class="form-control" placeholder="Search here" name="search_id">--%>
<%--                        <div class="input-group-append">--%>
<%--                            <button type="submit" class="input-group-text"><i class="flaticon-381-search-2"></i></button>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </form>--%>
<%--                <a href="javascript:void(0);" class="settings-icon"><i class="flaticon-381-settings-2 mr-0"></i></a>-->--%>
<%--            </div>--%>
            <div class="row">
                <div class="col-xl-12">
                    <div class="table-responsive">
                        <table id="example5" class="table table-striped patient-list mb-4 dataTablesCard fs-14">
                            <thead>
                            <tr>
<%--                                <th>--%>
<%--                                    <div class="checkbox text-right align-self-center">--%>
<%--                                        <div class="custom-control custom-checkbox ">--%>
<%--                                            <input type="checkbox" class="custom-control-input" id="checkAll" required="">--%>
<%--                                            <label class="custom-control-label" for="checkAll"></label>--%>
<%--                                        </div>--%>
<%--                                    </div>--%>
<%--                                </th>--%>
                                <th>UUID</th>
                                <th>文件名</th>
                                <th>文件大小(KB)</th>
                                <th>上传者</th>
                                <th>上传时间</th>
                                <th>下载</th>
                                <th>删除</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${filelist}" var="filelist">
                                <tr>
<%--                                    <td>--%>
<%--                                        <div class="checkbox text-right align-self-center">--%>
<%--                                            <div class="custom-control custom-checkbox ">--%>
<%--                                                <input type="checkbox" class="custom-control-input" id="customCheckBox1" required="">--%>
<%--                                                <label class="custom-control-label" for="customCheckBox1"></label>--%>
<%--                                            </div>--%>
<%--                                        </div>--%>
<%--                                    </td>--%>
                                    <td>${filelist.uuid}</td>
                                    <td>${filelist.filename}</td>
                                    <td>${filelist.filesize}</td>
                                    <td>${filelist.uploader}</td>
                                    <td>${filelist.uptime}</td>
                                    <td>
<%--                                        <a href="/DRMS/DownloadFile?filename=${filelist.uuid}${filelist.filename}">--%>
<%--                                            <img src="./images/download.png">--%>
<%--&lt;%&ndash;                                            <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                <path d="M17 3C17.2626 2.73735 17.5744 2.52901 17.9176 2.38687C18.2608 2.24473 18.6286 2.17157 19 2.17157C19.3714 2.17157 19.7392 2.24473 20.0824 2.38687C20.4256 2.52901 20.7374 2.73735 21 3C21.2626 3.26264 21.471 3.57444 21.6131 3.9176C21.7553 4.26077 21.8284 4.62856 21.8284 5C21.8284 5.37143 21.7553 5.73923 21.6131 6.08239C21.471 6.42555 21.2626 6.73735 21 7L7.5 20.5L2 22L3.5 16.5L17 3Z" stroke="#3E4954" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                <path d="m12.36355,17.18183l3.96371,-3.81818l-7.66364,0l-7.66362,0l0,-2.36363l0,-2.36364l7.57571,0c4.16663,0 7.5757,-0.1198 7.5757,-0.26623c0,-0.14642 -1.65873,-1.8646 -3.68605,-3.81818l-3.68606,-3.55196l3.40705,0l3.40703,0l5.20331,5.00479l5.20331,5.00479l-5.21329,4.99522l-5.21329,4.99522l-3.58679,0l-3.5868,0l3.96372,-3.81818l0,0l0,-0.00001l0,-0.00001z" stroke="#3E4954" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                            </svg>&ndash;%&gt;--%>
<%--                                        </a>--%>
                                        <form action="/DRMS/DownloadFile" method="post">
                                            <input type="hidden" name="filename" value="${filelist.uuid}${filelist.filename}">
                                            <input type="image" src="./images/download.png">
                                        </form>
                                    </td>
<%--                                    <td>--%>
<%--                                        <a href="#">--%>
<%--                                            <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">--%>
<%--                                                <path d="M3 6H5H21" stroke="#F46B68" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>--%>
<%--                                                <path d="M8 6V4C8 3.46957 8.21071 2.96086 8.58579 2.58579C8.96086 2.21071 9.46957 2 10 2H14C14.5304 2 15.0391 2.21071 15.4142 2.58579C15.7893 2.96086 16 3.46957 16 4V6M19 6V20C19 20.5304 18.7893 21.0391 18.4142 21.4142C18.0391 21.7893 17.5304 22 17 22H7C6.46957 22 5.96086 21.7893 5.58579 21.4142C5.21071 21.0391 5 20.5304 5 20V6H19Z" stroke="#F46B68" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>--%>
<%--                                            </svg>--%>
<%--                                        </a>--%>
<%--                                    </td>--%>
                                    <td>
                                        <form action="/DRMS/DeleteFile" method="post">
                                            <input type="hidden" name="uuid" value="${filelist.uuid}">
                                            <input type="hidden" name="filename" value="${filelist.filename}">
                                            <input type="image" src="./images/delete.png">
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--**********************************
        Content body end
    ***********************************-->

    <!--**********************************
        Footer start
    ***********************************-->

    <!--**********************************
        Footer end
    ***********************************-->

    <!--**********************************
       Support ticket button start
    ***********************************-->

    <!--**********************************
       Support ticket button end
    ***********************************-->


</div>
<!--**********************************
    Main wrapper end
***********************************-->

<!--**********************************
    Scripts
***********************************-->
<!-- Required vendors -->
<script src="./vendor/global/global.min.js"></script>
<script src="./vendor/bootstrap-select/dist/js/bootstrap-select.min.js"></script>
<script src="./vendor/chart.js/Chart.bundle.min.js"></script>
<script src="./js/custom.min.js"></script>
<script src="./js/deznav-init.js"></script>
<script src="./vendor/owl-carousel/owl.carousel.js"></script>

<!-- Datatable -->
<script src="./vendor/datatables/js/jquery.dataTables.min.js"></script>

<script>
    (function($) {

        var table = $('#example5').DataTable({
            searching: false,
            paging:true,
            select: false,
            //info: false,
            lengthChange:false

        });
        $('#example tbody').on('click', 'tr', function () {
            var data = table.row( this ).data();

        });

    })(jQuery);
</script>

</body>
</html>
