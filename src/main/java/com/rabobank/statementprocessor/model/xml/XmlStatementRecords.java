package com.rabobank.statementprocessor.model.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "records")
public class XmlStatementRecords {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "record")
    private List<XmlStatementRecord> xmlStatementRecords;

    public List<XmlStatementRecord> getXmlStatementRecords() {
        return xmlStatementRecords;
    }

    public void setXmlStatementRecords(List<XmlStatementRecord> xmlStatementRecords) {
        this.xmlStatementRecords = xmlStatementRecords;
    }
}