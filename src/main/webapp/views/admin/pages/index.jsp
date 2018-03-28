<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row row-panel report-btn">
  <a href="${contextPath}/admin/reports" class="btn btn-primary">Export Report</a>
</div>

<div class="row row-panel">

  <div class="col-lg-3 col-md-6">
    <c:import url="/views/admin/pages/order_panel.jsp" />
  </div>

  <div class="col-lg-3 col-md-6">
    <c:import url="/views/admin/pages/category_panel.jsp" />
  </div>

  <div class="col-lg-3 col-md-6">
    <c:import url="/views/admin/pages/product_panel.jsp" />
  </div>

  <div class="col-lg-3 col-md-6">
    <c:import url="/views/admin/pages/user_panel.jsp" />
  </div>
</div>

<div class="row">
  <div class="col-lg-8">
    <c:import url="/views/admin/charts/bar_chart.jsp" />
  </div>

  <div class="col-lg-4">
    <c:import url="/views/admin/charts/donut_chart.jsp" />
  </div>
</div>
