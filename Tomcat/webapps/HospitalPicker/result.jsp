<%@ page import="java.util.*" %>
<html>
  <body>
    <h1 align="center">Hospital Recommendations JSP</h1>
    <p>
    <%
      List styles = (List)request.getAttribute("styles");
      Iterator it = styles.iterator();
      while (it.hasNext()) {
        out.print("<br>Closest Hospital that performs your surgery is " + it.next());
      } %>
    </body>
  </html>
