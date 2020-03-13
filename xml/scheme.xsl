<?xml version="1.0"?>
    <xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:template match="entries">
            <entries>
            <xsl:apply-templates select="entry"/>
            </entries>
    </xsl:template>

    <xsl:template match="entries/entry">
        <entry>
            <xsl:attribute name="href">
                <xsl:value-of select="field"/>
            </xsl:attribute>
        </entry>
    </xsl:template>
</xsl:stylesheet>