package org.example.web;

import org.example.domain.Invoice;
import org.example.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Invoice generation controller.
 */
@Controller
public class BillingController
{
  @Autowired
  private BillingService billingService;

  /**
   * Generates a new invoice.
   */
  @RequestMapping(method = RequestMethod.GET, produces = "text/html", value = "/")
  @ResponseBody
  public String invoice()
  {
    final Invoice invoice = billingService.generateInvoice("AB","23");
    final Invoice invoice2 = billingService.generateInvoice("RQ","23");
    final Invoice invoice3 = billingService.generateInvoice("AB","23");

    return "<!DOCTYPE html>"
        + "<html>"
        + "  <head>"
        + "    <title>Invoice Generator</title>"
        + "    <style>"
        + "      body, html { background:#EEE; color:#333; font-family:Arial; font-size:12px; height:100%; margin:0; padding:0; width:100%; }"
        + "      #container { background:#FFF; margin:0 30px; min-height:100%; padding:40px; }"
        + "    </style>"
        + "  </head>"
        + "  <body>"
        + "    <div id='container'>"
        + "      <h3>" + invoice.getCode() + "</h3>"
        + "      <h3>" + invoice2.getCode() + "</h3>"
        + "      <h3>" + invoice3.getCode() + "</h3>"
        + "    </div>"
        + "  </body>"
        + "</html>";
  }
}
