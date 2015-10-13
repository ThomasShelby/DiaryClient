<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="record">
        <html lang="en">
            <head>
                <meta charset="UTF-8" />
                <title>Temporary records</title>
                
                <!-- Latest compiled and minified CSS -->
                <link rel="stylesheet"
                    href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />

                <!-- Optional theme -->
                <link rel="stylesheet"
                    href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css" />

                <!-- Latest compiled and minified JavaScript -->
                <script
                    src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
            </head>
            <body>
            
                <h2>Temporary records</h2>

                <table class="table table-striped">

                    <thead>
                        <tr bgcolor="#1af172">
                            <th>User name</th>
                            <th>Created time</th>
                            <th>Message text</th>
                            <th>Supplement URL</th>
                        </tr>
                    </thead>
                    <tbody>
 
                             <tr bgcolor="lightgreen">
                                <td>
                                    <xsl:value-of select="user_name" />
                                </td>
                                <td>
                                    <xsl:value-of select="created_time" />
                                </td>
                                <td>
                                    <xsl:value-of select="text" />
                                </td>
                                <td>
                                    <xsl:value-of select="supplement" />
                                </td>
                            </tr>

                    </tbody>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>