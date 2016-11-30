                <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                    <h1 class="page-header">Dashboard</h1>

                    <div class="row badges">
                        <div class="col-xs-12 col-sm-12 col-md-4 badges panel">

                            <div class="panel-body">
                                <span class="glyphicon glyphicon-file"></span> Posts Needing Approval  <span class="badge">${numPosts}</span>
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-4 badges panel">
                            <h3 class="panel-title">Recent Posts</h3>
                            <div class="panel-body">
                                <c:forEach items="">
                                  <button class="btn btn-default btn-stacked"><span class="glyphicon glyphicon-th"></span> Working Out is Great  <span class="badge"></span></button>   
                                </c:forEach>
                              
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-4 badges panel">
                            <h3 class="panel-title">Most Viewed Posts</h3>
                            <div class="panel-body">
                                <button class="btn btn-default btn-stacked"><span class="glyphicon glyphicon-th"></span> Working Out is Great  <span class="badge">700</span></button> 
                                <button class="btn btn-default btn-stacked"><span class="glyphicon glyphicon-th"></span> Nail Your Double-Unders  <span class="badge">208</span></button> 
                                <button class="btn btn-default btn-stacked"><span class="glyphicon glyphicon-th"></span> How are you preparing for the open?  <span class="badge">15</span></button> 
                            </div>

                        </div>

                    </div>
                </div>
