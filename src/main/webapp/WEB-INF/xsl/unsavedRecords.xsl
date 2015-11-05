<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">
		<html lang="en">
			<head>
				<meta charset="UTF-8" />
				<title>Temporary records</title>

				<link rel="stylesheet"
					href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />

				<link rel="stylesheet"
					href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css" />

				<script
					src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
			</head>
			<body>

				<h2>Unsaved records</h2>

				<table class="table table-striped">

					<thead>
						<tr bgcolor="#1af172">
							<th>User name</th>
							<th>Record uuid</th>
							<th>Title</th>
							<th>Text</th>
						</tr>
					</thead>
					<tbody>
						<xsl:for-each select="unsavedRecords/record">
							<tr bgcolor="lightgreen">
								<td>
									<xsl:value-of select="nick" />
								</td>
								<td>
									<xsl:value-of select="uuid" />
								</td>
								<td>
									<xsl:value-of select="title" />
								</td>
								<td>
									<xsl:value-of select="text" />
								</td>
							</tr>
						</xsl:for-each>
					</tbody>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>