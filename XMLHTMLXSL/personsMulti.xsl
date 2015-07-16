<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<head>
				<title>Experiment</title>
				<link rel="stylesheet" type="text/css" href="personsMulti.css" />
			</head>
			<body>
				<h2 class="h2_persons">Persons</h2>
				<table border="1">
					<tr>
						<th>ID</th>
						<th>First name</th>
						<th>Last name</th>
						<th>Nick name</th>
						<th>Date Of Birth</th>
					</tr>
					<!-- <xsl:for-each select="persons/person[nickname='MTSMDA']"> -->
					<xsl:for-each select="persons/person">
						<xsl:sort select="@id" data-type="text" order="ascending" />
						<!-- <xsl:if test="firstname = 'Mynzat'"> -->
						<tr>
							<td>
								<xsl:value-of select="@id"/>
							</td>
							<td>
								<xsl:value-of select="firstname" />
							</td>
							<td>
								<xsl:value-of select="lastname" />
							</td>
							<td>
								<xsl:value-of select="nickname" />
							</td>
							<td>
								<xsl:value-of select="dateOfBirth" />
							</td>
						</tr>
						<!-- </xsl:if> -->
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>