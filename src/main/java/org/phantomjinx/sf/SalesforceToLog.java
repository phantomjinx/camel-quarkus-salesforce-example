package org.phantomjinx.sf;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.salesforce.SalesforceEndpointConfig;
import org.apache.camel.model.dataformat.JsonLibrary;

public class SalesforceToLog extends RouteBuilder {

  private static final String SOBJECT_FIELDS = "Id,Account.Name,CaseNumber,Subject,Description";

  public SalesforceToLog() throws Exception {
  }

  @Override
  public void configure() throws Exception {

    // main route
    from("salesforce:CamelCaseTopic?"
        + "notifyForOperationCreate=true&notifyForOperationUpdate=true&notifyForOperationDelete=false&notifyForOperationUndelete=false"
        + "&updateTopic=true&rawPayload=true&sObjectQuery=SELECT Id, CaseNumber FROM Case")

    .log("New Case: ID: ${body[Id]}, Account.Name: ${body[Account][Name]}, CaseNumber: ${body[CaseNumber]}, Subject: ${body[Subject]}");
  };

}

