        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">CrossFit Guild</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="${pageContext.request.contextPath}/j_spring_security_logout"><sec:authentication property="principal.username" /> Logout</a></li>
                    </ul>
                    <!--Maybe add back in in later version-->
                    <!--                    <form class="navbar-form navbar-right">
                                            <input type="text" class="form-control" placeholder="Search...">
                                        </form>-->
                </div>
            </div>
        </nav>