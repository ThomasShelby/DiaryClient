<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
			<h3>${user.nickName}</h3>
			<br>
			<form action="edit?${_csrf.parameterName}=${_csrf.token}" name="user" enctype="multipart/form-data" method="post">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" /> <input type="hidden" name="uuid"
					value="${user.uuid}" /> <input type="hidden" name="password"
					value="${user.password}" /> <input type="hidden" name="avatar"
					value="${user.avatar}" /> <input type="hidden" name="role"
					value="${user.role}" /> <input type="hidden" name="session"
					value="${user.session}" /> <input type="hidden" name="nickName"
					value="${user.nickName}" /><input type="hidden" name="address.uuid" value="${user.address.uuid}" />
				<table>
					<tr>
						<td><label for="firstName">First name</label></td>
						<td><input id="firstName" type="text" name="firstName"
							value="${user.firstName}" /></td>
					</tr>
					<tr>
						<td><label for="secondName">Second name</label></td>
						<td><input id="secondName" type="text" name="secondName"
							value="${user.secondName}" /></td>
					</tr>
					<tr>
						<td><label for="country">Country</label></td>
						<td><input id="country" type="text" name="address.country"
							value="${user.address.country}" /></td>
					</tr>
					<tr>
						<td><label for="city">City</label></td>
						<td><input id="city" type="text" name="address.city" value="${user.address.city}" />
						</td>
					</tr>
					<tr>
						<td><label for="street">Street</label></td>
						<td><input id="street" type="text" name="address.street"
							value="${user.address.street}" /></td>
					</tr>
					<tr>
						<td><label for="building">Building</label></td>
						<td><input id="building" type="text" name="address.buildNumber"
							value="${user.address.buildNumber}" /></td>
					</tr>
					<tr>
						<td><label for="eMail">E-Mail</label></td>
						<td><input id="eMail" type="text" name="eMail"
							value="${user.eMail}" /></td>
					</tr>
					<tr>
						<td><label for="sex">Sex</label></td>
						<td><select id="sex" name="sex">
								<option value="MALE" ${user.sex=="MALE"?'selected':''}>MALE</option>
								<option value="FEMALE" ${user.sex=="FEMALE"?'selected':''}>FEMALE</option>
						</select></td>
					</tr>
					<tr>
						<td><label for="dateOfBirth">Date of birth</label></td>
						<td><input id="dateOfBirth" type="text" name="dateOfBirth"
							value="${user.dateOfBirth}" autocomplete="off"/></td>
					</tr>
					<tr>
						<td>File to upload:</td>
						<td><input type="file" name="file"></td>
					</tr>
				</table>
				<input type="submit" value="Save">
			</form>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>

 <script>
  $(function() {
    $( "#dateOfBirth" ).datepicker({
      changeMonth: true,
      changeYear: true,
      dateFormat: 'yy-mm-dd',
      yearRange: "-120:+0",
    });
  });
  </script>