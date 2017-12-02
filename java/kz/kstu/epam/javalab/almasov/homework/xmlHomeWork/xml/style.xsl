<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="airport">
        <html>
            <head>
                <title>transformed XML file</title>
            </head>
            <body>
                <xsl:apply-templates></xsl:apply-templates>
            </body>
        </html>
    </xsl:template>

    <xsl:template>
        <div>
            <h1>main teg - <xsl:value-of select="airport"/></h1>
            <h2>Plane number: <xsl:attribute name="number"/></h2>
            <h3>Model: <xsl:value-of select="model"/></h3>
            <h3>Origin: <xsl:value-of select="origin"/></h3>
            <h3>Chars:</h3>
            <p>Crew Seats Number - <xsl:value-of select="crewSeatsNumber"/></p>
            <p>Carrying Capacity - <xsl:value-of select="carryingCapacity"/></p>
            <p>Passengers Number - <xsl:value-of select="passengersNumber"/></p>
            <h3>Parameters:</h3>
            <p>Lenght - <xsl:value-of select="lenght"/></p>
            <p>Width - <xsl:value-of select="width"/></p>
            <p>Height - <xsl:value-of select="height"/></p>
            <h3>cost: <xsl:value-of select="cost"/></h3>
        </div>
    </xsl:template>

</xsl:stylesheet>