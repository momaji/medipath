<%@ page import="java.util.*" %>
<html>
  <body>
    <h1 align="center">Hospital Recommendations</h1>
    <p>
    <%
      List styles = (List)request.getAttribute("styles");
      out.print("<br>" + styles.get(0));

      %>
    </body>
  </html>


  <%-- Iterator it = styles.iterator();
  while (it.hasNext()) {
    out.print("<br>Your selected surgery is " + it.next());
  } --%>
