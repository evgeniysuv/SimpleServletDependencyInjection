<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Being Java Guys | Registration Form</title>
</head>
<body>
<div style="text-align: center;">
    <div style="color: teal; font-size: 30px">Being Java Guys |
        Registration Form
    </div>
    <form method="post" action="getServlet">
        <table>
            <tr>
                <td>Name :</td>
                <td><input type="text" name="name"/>
                </td>
            </tr>
            <tr>
                <td>Gender :</td>
                <td>
                    <input type="radio" name="gender" value="male">Male<br/>
                    <input type="radio" name="gender" value="female">Female
                </td>
            </tr>
            <tr>
                <td>Phone :</td>
                <td>
                    <input type="text" name="phone"/>
                </td>
            </tr>
            <tr>
                <td>Email :</td>
                <td><input type="text" name="email"/>
                </td>
            </tr>
            <tr>
                <td>City :</td>
                <td>
                    <select name="city">
                        <option value="delhi">Delhi</option>
                        <option value="mumbai">Mumbai</option>
                        <option value="kolkata">Kolkata</option>
                        <option value="chennai">Chennai</option>
                        <option value="noida">Noida</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Save">
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>