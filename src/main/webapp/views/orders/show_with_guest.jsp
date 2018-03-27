<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="order-guest">
  <h2>The order has created successful!</h2>
  <div>
    <span>
      <b>Name:</b>
    </span>
    <span>${order.getName()}</span>
  </div>

  <div>
    <span>
      <b>Phone number:</b>
      ${order.getPhoneNumber()}
    </span>
  </div>

  <div>
    <span>
      <b>Email:</b>
      ${order.getEmail()}
    </span>
  </div>

  <div>
    <span>
      <b>Total price:</b>
      <fmt:setLocale value="en_US" />
      <fmt:formatNumber value="${order.getTotalPrice()}" type="currency" />
    </span>
  </div>

  <h3>Please waiting for admin to call you to confirm, and check email! Thanks you.</h3>
</div>

