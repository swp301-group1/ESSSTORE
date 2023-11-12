package com.shopping.esoshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.shopping.esoshop.model.Bill;
import com.shopping.esoshop.model.OrderDelail;

import jakarta.mail.internet.MimeMessage;


@Service
public class MailService {
	 @Autowired
	 private JavaMailSender javaMailSender;
	 
	 public boolean sendEmail(String to, String sub, String text) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(sub);
            String htmlContent =
			         "<html><head><style>"
                    + "body { font-family: Arial, sans-serif; }"
                    + ".container { max-width: 600px; margin: 0 auto; padding: 20px; background-color: #f5f5f5; }"
                    + "</style></head><body>"
                    + "<div class='container'>"
                    + "<h2>ESSSTORE</h2>"
                    + "<p>" + text + "</p>"
                    + "</div>"
                    + "</body></html>";
            helper.setText(htmlContent, true);
            javaMailSender.send(mimeMessage);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

	public Boolean sendEmailBill(Bill bill) {
        try {
			String emmail = bill.getAccount().getEmail();
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(emmail);
            helper.setSubject("Bill");
            String htmlContent = bill(bill.getOrderdetails());
            helper.setText(htmlContent, true);
            javaMailSender.send(mimeMessage);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public String bill(List<OrderDelail> orderdetail){
		String html ="<html dir=\"ltr\" xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" lang=\"und\">\r\n" + //
				"\r\n" + //
				"<head>\r\n" + //
				"    <meta charset=\"UTF-8\">\r\n" + //
				"    <meta content=\"width=device-width, initial-scale=1\" name=\"viewport\">\r\n" + //
				"    <meta name=\"x-apple-disable-message-reformatting\">\r\n" + //
				"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" + //
				"    <meta content=\"telephone=no\" name=\"format-detection\">\r\n" + //
				"    <title>New Template 2</title>\r\n" + //
				"    <!--[if (mso 16)]><style type=\"text/css\">     a {text-decoration: none;}     </style><![endif]-->\r\n" + //
				"    <!--[if gte mso 9]><style>sup { font-size: 100% !important; }</style><![endif]-->\r\n" + //
				"    <!--[if gte mso 9]><xml> <o:OfficeDocumentSettings> <o:AllowPNG></o:AllowPNG> <o:PixelsPerInch>96</o:PixelsPerInch> </o:OfficeDocumentSettings> </xml>\r\n" + //
				"    <![endif]-->\r\n" + //
				"    <style type=\"text/css\">\r\n" + //
				"        .rollover:hover .rollover-first {\r\n" + //
				"            max-height: 0px !important;\r\n" + //
				"            display: none !important;\r\n" + //
				"        }\r\n" + //
				"\r\n" + //
				"        .rollover:hover .rollover-second {\r\n" + //
				"            max-height: none !important;\r\n" + //
				"            display: inline-block !important;\r\n" + //
				"        }\r\n" + //
				"\r\n" + //
				"        .rollover div {\r\n" + //
				"            font-size: 0px;\r\n" + //
				"        }\r\n" + //
				"\r\n" + //
				"        u+.body img~div div {\r\n" + //
				"            display: none;\r\n" + //
				"        }\r\n" + //
				"\r\n" + //
				"        #outlook a {\r\n" + //
				"            padding: 0;\r\n" + //
				"        }\r\n" + //
				"\r\n" + //
				"        span.MsoHyperlink,\r\n" + //
				"        span.MsoHyperlinkFollowed {\r\n" + //
				"            color: inherit;\r\n" + //
				"            mso-style-priority: 99;\r\n" + //
				"        }\r\n" + //
				"\r\n" + //
				"        a.es-button {\r\n" + //
				"            mso-style-priority: 100 !important;\r\n" + //
				"            text-decoration: none !important;\r\n" + //
				"        }\r\n" + //
				"\r\n" + //
				"        a[x-apple-data-detectors] {\r\n" + //
				"            color: inherit !important;\r\n" + //
				"            text-decoration: none !important;\r\n" + //
				"            font-size: inherit !important;\r\n" + //
				"            font-family: inherit !important;\r\n" + //
				"            font-weight: inherit !important;\r\n" + //
				"            line-height: inherit !important;\r\n" + //
				"        }\r\n" + //
				"\r\n" + //
				"        .es-desk-hidden {\r\n" + //
				"            display: none;\r\n" + //
				"            float: left;\r\n" + //
				"            overflow: hidden;\r\n" + //
				"            width: 0;\r\n" + //
				"            max-height: 0;\r\n" + //
				"            line-height: 0;\r\n" + //
				"            mso-hide: all;\r\n" + //
				"        }\r\n" + //
				"\r\n" + //
				"        .es-button-border:hover>a.es-button {\r\n" + //
				"            color: #ffffff !important;\r\n" + //
				"        }\r\n" + //
				"\r\n" + //
				"        @media only screen and (max-width:600px) {\r\n" + //
				"            .es-m-p0r {\r\n" + //
				"                padding-right: 0px !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            .es-m-p0r {\r\n" + //
				"                padding-right: 0px !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            .es-m-p0l {\r\n" + //
				"                padding-left: 0px !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            .es-m-p0r {\r\n" + //
				"                padding-right: 0px !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            .es-m-p0l {\r\n" + //
				"                padding-left: 0px !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            .es-m-p0r {\r\n" + //
				"                padding-right: 0px !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            .es-m-p0r {\r\n" + //
				"                padding-right: 0px !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            .es-m-p0r {\r\n" + //
				"                padding-right: 0px !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            .es-m-p0r {\r\n" + //
				"                padding-right: 0px !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            .es-m-p20b {\r\n" + //
				"                padding-bottom: 20px !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            .es-m-p0r {\r\n" + //
				"                padding-right: 0px !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            *[class=\"gmail-fix\"] {\r\n" + //
				"                display: none !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            p,\r\n" + //
				"            a {\r\n" + //
				"                line-height: 150% !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            h1,\r\n" + //
				"            h1 a {\r\n" + //
				"                line-height: 120% !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            h2,\r\n" + //
				"            h2 a {\r\n" + //
				"                line-height: 120% !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            h3,\r\n" + //
				"            h3 a {\r\n" + //
				"                line-height: 120% !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            h4,\r\n" + //
				"            h4 a {\r\n" + //
				"                line-height: 120% !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            h5,\r\n" + //
				"            h5 a {\r\n" + //
				"                line-height: 120% !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            h6,\r\n" + //
				"            h6 a {\r\n" + //
				"                line-height: 120% !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            h1 {\r\n" + //
				"                font-size: 36px !important;\r\n" + //
				"                text-align: left\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            h2 {\r\n" + //
				"                font-size: 26px !important;\r\n" + //
				"                text-align: left\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            h3 {\r\n" + //
				"                font-size: 20px !important;\r\n" + //
				"                text-align: left\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            h4 {\r\n" + //
				"                font-size: 24px !important;\r\n" + //
				"                text-align: left\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            h5 {\r\n" + //
				"                font-size: 20px !important;\r\n" + //
				"                text-align: left\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            h6 {\r\n" + //
				"                font-size: 16px !important;\r\n" + //
				"                text-align: left\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            .es-header-body h1 a,\r\n" + //
				"            .es-content-body h1 a,\r\n" + //
				"            .es-footer-body h1 a {\r\n" + //
				"                font-size: 36px !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            .es-header-body h2 a,\r\n" + //
				"            .es-content-body h2 a,\r\n" + //
				"            .es-footer-body h2 a {\r\n" + //
				"                font-size: 26px !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            .es-header-body h3 a,\r\n" + //
				"            .es-content-body h3 a,\r\n" + //
				"            .es-footer-body h3 a {\r\n" + //
				"                font-size: 20px !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            .es-header-body h4 a,\r\n" + //
				"            .es-content-body h4 a,\r\n" + //
				"            .es-footer-body h4 a {\r\n" + //
				"                font-size: 24px !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            .es-header-body h5 a,\r\n" + //
				"            .es-content-body h5 a,\r\n" + //
				"            .es-footer-body h5 a {\r\n" + //
				"                font-size: 20px !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            .es-header-body h6 a,\r\n" + //
				"            .es-content-body h6 a,\r\n" + //
				"            .es-footer-body h6 a {\r\n" + //
				"                font-size: 16px !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            .es-menu td a {\r\n" + //
				"                font-size: 12px !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            .es-header-body p,\r\n" + //
				"            .es-header-body a {\r\n" + //
				"                font-size: 14px !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            .es-content-body p,\r\n" + //
				"            .es-content-body a {\r\n" + //
				"                font-size: 14px !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            .es-footer-body p,\r\n" + //
				"            .es-footer-body a {\r\n" + //
				"                font-size: 14px !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            .es-infoblock p,\r\n" + //
				"            .es-infoblock a {\r\n" + //
				"                font-size: 12px !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            .es-m-txt-c,\r\n" + //
				"            .es-m-txt-c h1,\r\n" + //
				"            .es-m-txt-c h2,\r\n" + //
				"            .es-m-txt-c h3,\r\n" + //
				"            .es-m-txt-c h4,\r\n" + //
				"            .es-m-txt-c h5,\r\n" + //
				"            .es-m-txt-c h6 {\r\n" + //
				"                text-align: center !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            .es-m-txt-r,\r\n" + //
				"            .es-m-txt-r h1,\r\n" + //
				"            .es-m-txt-r h2,\r\n" + //
				"            .es-m-txt-r h3,\r\n" + //
				"            .es-m-txt-r h4,\r\n" + //
				"            .es-m-txt-r h5,\r\n" + //
				"            .es-m-txt-r h6 {\r\n" + //
				"                text-align: right !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            .es-m-txt-j,\r\n" + //
				"            .es-m-txt-j h1,\r\n" + //
				"            .es-m-txt-j h2,\r\n" + //
				"            .es-m-txt-j h3,\r\n" + //
				"            .es-m-txt-j h4,\r\n" + //
				"            .es-m-txt-j h5,\r\n" + //
				"            .es-m-txt-j h6 {\r\n" + //
				"                text-align: justify !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            .es-m-txt-l,\r\n" + //
				"            .es-m-txt-l h1,\r\n" + //
				"            .es-m-txt-l h2,\r\n" + //
				"            .es-m-txt-l h3,\r\n" + //
				"            .es-m-txt-l h4,\r\n" + //
				"            .es-m-txt-l h5,\r\n" + //
				"            .es-m-txt-l h6 {\r\n" + //
				"                text-align: left !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            .es-m-txt-r img,\r\n" + //
				"            .es-m-txt-c img,\r\n" + //
				"            .es-m-txt-l img {\r\n" + //
				"                display: inline !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            .es-m-txt-r .rollover:hover .rollover-second,\r\n" + //
				"            .es-m-txt-c .rollover:hover .rollover-second,\r\n" + //
				"            .es-m-txt-l .rollover:hover .rollover-second {\r\n" + //
				"                display: inline !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            .es-m-txt-r .rollover div,\r\n" + //
				"            .es-m-txt-c .rollover div,\r\n" + //
				"            .es-m-txt-l .rollover div {\r\n" + //
				"                line-height: 0 !important;\r\n" + //
				"                font-size: 0 !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            .es-spacer {\r\n" + //
				"                display: inline-table\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            a.es-button,\r\n" + //
				"            button.es-button {\r\n" + //
				"                font-size: 20px !important;\r\n" + //
				"                line-height: 120% !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            a.es-button,\r\n" + //
				"            button.es-button,\r\n" + //
				"            .es-button-border {\r\n" + //
				"                display: inline-block !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            .es-m-fw,\r\n" + //
				"            .es-m-fw.es-fw,\r\n" + //
				"            .es-m-fw .es-button {\r\n" + //
				"                display: block !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            .es-m-il,\r\n" + //
				"            .es-m-il .es-button,\r\n" + //
				"            .es-social,\r\n" + //
				"            .es-social td,\r\n" + //
				"            .es-menu {\r\n" + //
				"                display: inline-block !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            .es-adaptive table,\r\n" + //
				"            .es-left,\r\n" + //
				"            .es-right {\r\n" + //
				"                width: 100% !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            .es-content table,\r\n" + //
				"            .es-header table,\r\n" + //
				"            .es-footer table,\r\n" + //
				"            .es-content,\r\n" + //
				"            .es-footer,\r\n" + //
				"            .es-header {\r\n" + //
				"                width: 100% !important;\r\n" + //
				"                max-width: 600px !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            .adapt-img {\r\n" + //
				"                width: 100% !important;\r\n" + //
				"                height: auto !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            .es-mobile-hidden,\r\n" + //
				"            .es-hidden {\r\n" + //
				"                display: none !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            .es-desk-hidden {\r\n" + //
				"                width: auto !important;\r\n" + //
				"                overflow: visible !important;\r\n" + //
				"                float: none !important;\r\n" + //
				"                max-height: inherit !important;\r\n" + //
				"                line-height: inherit !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            tr.es-desk-hidden {\r\n" + //
				"                display: table-row !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            table.es-desk-hidden {\r\n" + //
				"                display: table !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            td.es-desk-menu-hidden {\r\n" + //
				"                display: table-cell !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            .es-menu td {\r\n" + //
				"                width: 1% !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            table.es-table-not-adapt,\r\n" + //
				"            .esd-block-html table {\r\n" + //
				"                width: auto !important\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            .es-social td {\r\n" + //
				"                padding-bottom: 10px\r\n" + //
				"            }\r\n" + //
				"\r\n" + //
				"            .h-auto {\r\n" + //
				"                height: auto !important\r\n" + //
				"            }\r\n" + //
				"        }\r\n" + //
				"    </style>\r\n" + //
				"</head>\r\n" + //
				"\r\n" + //
				"<body class=\"body\" style=\"width:100%;height:100%;padding:0;Margin:0\">\r\n" + //
				"    <div dir=\"ltr\" class=\"es-wrapper-color\" lang=\"und\" style=\"background-color:#FAFAFA\">\r\n" + //
				"        <!--[if gte mso 9]><v:background xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"t\"> <v:fill type=\"tile\" color=\"#fafafa\"></v:fill> </v:background><![endif]-->\r\n" + //
				"        <table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"none\"\r\n" + //
				"            style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;padding:0;Margin:0;width:100%;height:100%;background-repeat:repeat;background-position:center top;background-color:#FAFAFA\">\r\n" + //
				"            <tr>\r\n" + //
				"                <td valign=\"top\" style=\"padding:0;Margin:0\">\r\n" + //
				"                    <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\" role=\"none\"\r\n" + //
				"                        style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;width:100%;table-layout:fixed !important\">\r\n" + //
				"                        <tr>\r\n" + //
				"                            <td class=\"es-info-area\" align=\"center\" style=\"padding:0;Margin:0\">\r\n" + //
				"                                <table class=\"es-content-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\"\r\n" + //
				"                                    style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:600px\"\r\n" + //
				"                                    bgcolor=\"#FFFFFF\" role=\"none\">\r\n" + //
				"                                    <tr>\r\n" + //
				"                                        <td align=\"left\" style=\"padding:20px;Margin:0\">\r\n" + //
				"                                            <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"none\"\r\n" + //
				"                                                style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" + //
				"                                                <tr>\r\n" + //
				"                                                    <td align=\"center\" valign=\"top\"\r\n" + //
				"                                                        style=\"padding:0;Margin:0;width:560px\">\r\n" + //
				"                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\r\n" + //
				"                                                            role=\"presentation\"\r\n" + //
				"                                                            style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" + //
				"                                                            <tr>\r\n" + //
				"                                                                <td align=\"center\" class=\"es-infoblock\"\r\n" + //
				"                                                                    style=\"padding:0;Margin:0\">\r\n" + //
				"                                                                    <p\r\n" + //
				"                                                                        style=\"Margin:0;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:18px;letter-spacing:0;color:#CCCCCC;font-size:12px\">\r\n" + //
				"                                                                        <a target=\"_blank\" href=\"\"\r\n" + //
				"                                                                            style=\"mso-line-height-rule:exactly;text-decoration:underline;color:#CCCCCC;font-size:12px\">View\r\n" + //
				"                                                                            online version</a> </p>\r\n" + //
				"                                                                </td>\r\n" + //
				"                                                            </tr>\r\n" + //
				"                                                        </table>\r\n" + //
				"                                                    </td>\r\n" + //
				"                                                </tr>\r\n" + //
				"                                            </table>\r\n" + //
				"                                        </td>\r\n" + //
				"                                    </tr>\r\n" + //
				"                                </table>\r\n" + //
				"                            </td>\r\n" + //
				"                        </tr>\r\n" + //
				"                    </table>\r\n" + //
				"                    <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-header\" align=\"center\" role=\"none\"\r\n" + //
				"                        style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;width:100%;table-layout:fixed !important;background-color:transparent;background-repeat:repeat;background-position:center top\">\r\n" + //
				"                        <tr>\r\n" + //
				"                            <td align=\"center\" style=\"padding:0;Margin:0\">\r\n" + //
				"                                <table bgcolor=\"#ffffff\" class=\"es-header-body\" align=\"center\" cellpadding=\"0\"\r\n" + //
				"                                    cellspacing=\"0\" role=\"none\"\r\n" + //
				"                                    style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:600px\">\r\n" + //
				"                                    <tr>\r\n" + //
				"                                        <td align=\"left\" style=\"padding:20px;Margin:0\">\r\n" + //
				"                                            <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"none\"\r\n" + //
				"                                                style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" + //
				"                                                <tr>\r\n" + //
				"                                                    <td class=\"es-m-p0r\" valign=\"top\" align=\"center\"\r\n" + //
				"                                                        style=\"padding:0;Margin:0;width:560px\">\r\n" + //
				"                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\r\n" + //
				"                                                            role=\"presentation\"\r\n" + //
				"                                                            style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" + //
				"                                                            <tr>\r\n" + //
				"                                                                <td align=\"center\"\r\n" + //
				"                                                                    style=\"padding:0;Margin:0;padding-bottom:10px;font-size:0px\">\r\n" + //
				"                                                                    <img src=\"https://fcosgad.stripocdn.email/content/guids/CABINET_887f48b6a2f22ad4fb67bc2a58c0956b/images/93351617889024778.png\"\r\n" + //
				"                                                                        alt=\"Logo\"\r\n" + //
				"                                                                        style=\"display:block;font-size:12px;border:0;outline:none;text-decoration:none\"\r\n" + //
				"                                                                        width=\"200\" title=\"Logo\" height=\"48\"></td>\r\n" + //
				"                                                            </tr>\r\n" + //
				"                                                        </table>\r\n" + //
				"                                                    </td>\r\n" + //
				"                                                </tr>\r\n" + //
				"                                            </table>\r\n" + //
				"                                        </td>\r\n" + //
				"                                    </tr>\r\n" + //
				"                                </table>\r\n" + //
				"                            </td>\r\n" + //
				"                        </tr>\r\n" + //
				"                    </table>\r\n" + //
				"                    <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-header\" align=\"center\" role=\"none\"\r\n" + //
				"                        style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;width:100%;table-layout:fixed !important;background-color:transparent;background-repeat:repeat;background-position:center top\">\r\n" + //
				"                        <tr>\r\n" + //
				"                            <td align=\"center\" style=\"padding:0;Margin:0\">\r\n" + //
				"                                <table bgcolor=\"#ffffff\" class=\"es-header-body\" align=\"center\" cellpadding=\"0\"\r\n" + //
				"                                    cellspacing=\"0\" role=\"none\"\r\n" + //
				"                                    style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:600px\">\r\n" + //
				"                                    <tr>\r\n" + //
				"                                        <td align=\"left\"\r\n" + //
				"                                            style=\"padding:0;Margin:0;padding-top:15px;padding-right:20px;padding-left:20px\">\r\n" + //
				"                                            <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"none\"\r\n" + //
				"                                                style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" + //
				"                                                <tr>\r\n" + //
				"                                                    <td align=\"center\" valign=\"top\"\r\n" + //
				"                                                        style=\"padding:0;Margin:0;width:560px\">\r\n" + //
				"                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\r\n" + //
				"                                                            role=\"presentation\"\r\n" + //
				"                                                            style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" + //
				"                                                            <tr>\r\n" + //
				"                                                                <td align=\"center\"\r\n" + //
				"                                                                    style=\"padding:0;Margin:0;padding-bottom:10px;padding-top:10px;font-size:0px\">\r\n" + //
				"                                                                    <img src=\"https://fcosgad.stripocdn.email/content/guids/CABINET_54100624d621728c49155116bef5e07d/images/84141618400759579.png\"\r\n" + //
				"                                                                        alt=\"\"\r\n" + //
				"                                                                        style=\"display:block;font-size:14px;border:0;outline:none;text-decoration:none\"\r\n" + //
				"                                                                        width=\"100\" height=\"98\"></td>\r\n" + //
				"                                                            </tr>\r\n" + //
				"                                                            <tr>\r\n" + //
				"                                                                <td align=\"center\" class=\"es-m-txt-c\"\r\n" + //
				"                                                                    style=\"padding:0;Margin:0;padding-bottom:10px\">\r\n" + //
				"                                                                    <h1\r\n" + //
				"                                                                        style=\"Margin:0;font-family:arial, 'helvetica neue', helvetica, sans-serif;mso-line-height-rule:exactly;letter-spacing:0;font-size:46px;font-style:normal;font-weight:bold;line-height:46px;color:#333333\">\r\n" + //
				"                                                                        Bill</h1>\r\n" + //
				"                                                                </td>\r\n" + //
				"                                                            </tr>\r\n" + //
				"                                                        </table>\r\n" + //
				"                                                    </td>\r\n" + //
				"                                                </tr>\r\n" + //
				"                                            </table>\r\n" + //
				"                                        </td>\r\n" + //
				"                                    </tr>\r\n" + //
				"                                </table>\r\n" + //
				"                            </td>\r\n" + //
				"                        </tr>\r\n" + //
				"                    </table>\r\n" + //
				"                    <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\" role=\"none\"\r\n" + //
				"                        style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;width:100%;table-layout:fixed !important\">\r\n" + //
				"                        <tr>\r\n" + //
				"                            <td align=\"center\" style=\"padding:0;Margin:0\">\r\n" + //
				"                                <table bgcolor=\"#ffffff\" class=\"es-content-body\" align=\"center\" cellpadding=\"0\"\r\n" + //
				"                                    cellspacing=\"0\" role=\"none\"\r\n" + //
				"                                    style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;width:600px\">\r\n" + //
				"                                    <tr>\r\n" + //
				"                                        <td align=\"left\" style=\"padding:20px;Margin:0\">\r\n" + //
				"                                            <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"none\"\r\n" + //
				"                                                style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" + //
				"                                                <tr>\r\n" + //
				"                                                    <td align=\"center\" valign=\"top\"\r\n" + //
				"                                                        style=\"padding:0;Margin:0;width:560px\">\r\n" + //
				"                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\r\n" + //
				"                                                            role=\"presentation\"\r\n" + //
				"                                                            style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" + //
				"                                                            <tr>\r\n" + //
				"                                                                <td align=\"center\" class=\"es-m-txt-c\"\r\n" + //
				"                                                                    style=\"padding:0;Margin:0\">\r\n" + //
				"                                                                    <h2\r\n" + //
				"                                                                        style=\"Margin:0;font-family:arial, 'helvetica neue', helvetica, sans-serif;mso-line-height-rule:exactly;letter-spacing:0;font-size:26px;font-style:normal;font-weight:bold;line-height:31px;color:#333333\">\r\n" + //
				"                                                                        Bill <a target=\"_blank\" href=\"http://localhost:8080/order_history"+orderdetail.get(0).getOrderId()+"\"\r\n" + //
				"                                                                            style=\"mso-line-height-rule:exactly;text-decoration:underline;color:#5C68E2;font-size:26px\">#"+orderdetail.get(0).getOrderId()+"</a>\r\n" + //
				"                                                                    </h2>\r\n" + //
				"                                                                </td>\r\n" + //
				"                                                            </tr>\r\n" + //
				"                                                            <tr>\r\n" + //
				"                                                                <td align=\"center\" class=\"es-m-p0r es-m-p0l\"\r\n" + //
				"                                                                    style=\"Margin:0;padding-top:5px;padding-right:40px;padding-bottom:5px;padding-left:40px\">\r\n" + //
				"                                                                    <p\r\n" + //
				"                                                                        style=\"Margin:0;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;letter-spacing:0;color:#333333;font-size:14px\">\r\n" + //
				"                                                                        Apr 17,&nbsp;2021</p>\r\n" + //
				"                                                                </td>\r\n" + //
				"                                                            </tr>\r\n" + //
				"                                                            <tr>\r\n" + //
				"                                                                <td align=\"center\" class=\"es-m-p0r es-m-p0l\"\r\n" + //
				"                                                                    style=\"Margin:0;padding-top:5px;padding-right:40px;padding-left:40px;padding-bottom:15px\">\r\n" + //
				"                                                                    <p\r\n" + //
				"                                                                        style=\"Margin:0;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;letter-spacing:0;color:#333333;font-size:14px\">\r\n" + //
				"                                                                        This email is to confirm&nbsp;your order. We\r\n" + //
				"                                                                        will send you another email as soon as it ships.\r\n" + //
				"                                                                    </p>\r\n" + //
				"                                                                </td>\r\n" + //
				"                                                            </tr>\r\n" + //
				"                                                            <tr>\r\n" + //
				"                                                                <td align=\"center\" style=\"padding:0;Margin:0\"><span\r\n" + //
				"                                                                        class=\"es-button-border\"\r\n" + //
				"                                                                        style=\"border-style:solid;border-color:#5c68e2;background:#5c68e2;border-width:2px;display:inline-block;border-radius:6px;width:auto\"><a\r\n" + //
				"                                                                            href=\"\" class=\"es-button\" target=\"_blank\"\r\n" + //
				"                                                                            style=\"mso-style-priority:100 !important;text-decoration:none !important;mso-line-height-rule:exactly;color:#FFFFFF;font-size:20px;padding:10px 30px 10px 30px;display:inline-block;background:#5C68E2;border-radius:6px;font-family:arial, 'helvetica neue', helvetica, sans-serif;font-weight:normal;font-style:normal;line-height:24px;width:auto;text-align:center;letter-spacing:0;mso-padding-alt:0;mso-border-alt:10px solid #5C68E2;border-left-width:30px;border-right-width:30px\">TRACK\r\n" + //
				"                                                                            ORDER STATUS</a></span></td>\r\n" + //
				"                                                            </tr>\r\n" + //
				"                                                        </table>\r\n" + //
				"                                                    </td>\r\n" + //
				"                                                </tr>\r\n" + //
				"                                            </table>\r\n" + //
				"                                        </td>\r\n" + //
				"                                    </tr>\r\n" + //
				"\r\n" + //
				"                                  "+ orderDetail(orderdetail)+"\r\n" + //
				"                                    <tr>\r\n" + //
				"                                        <td align=\"left\"\r\n" + //
				"                                            style=\"Margin:0;padding-bottom:10px;padding-right:20px;padding-left:20px;padding-top:20px\">\r\n" + //
				"                                            <!--[if mso]><table style=\"width:560px\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"width:280px\" valign=\"top\"><![endif]-->\r\n" + //
				"                                            <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-left\" align=\"left\"\r\n" + //
				"                                                role=\"none\"\r\n" + //
				"                                                style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\r\n" + //
				"                                                <tr>\r\n" + //
				"                                                    <td class=\"es-m-p0r es-m-p20b\" align=\"center\"\r\n" + //
				"                                                        style=\"padding:0;Margin:0;width:280px\">\r\n" + //
				"                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\r\n" + //
				"                                                            role=\"presentation\"\r\n" + //
				"                                                            style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" + //
				"                                                            <tr>\r\n" + //
				"                                                                <td align=\"left\" style=\"padding:0;Margin:0\">\r\n" + //
				"                                                                    <p\r\n" + //
				"                                                                        style=\"Margin:0;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;letter-spacing:0;color:#333333;font-size:14px\">\r\n" + //
				"                                                                        Customer:\r\n" + //
				"                                                                        <strong>sarah_powell@domain.com</strong></p>\r\n" + //
				"                                                                    <p\r\n" + //
				"                                                                        style=\"Margin:0;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;letter-spacing:0;color:#333333;font-size:14px\">\r\n" + //
				"                                                                        Order number:&nbsp;<strong>#65000500</strong>\r\n" + //
				"                                                                    </p>\r\n" + //
				"                                                                    <p\r\n" + //
				"                                                                        style=\"Margin:0;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;letter-spacing:0;color:#333333;font-size:14px\">\r\n" + //
				"                                                                        Invoice date:&nbsp;<strong>Apr 17, 2021</strong>\r\n" + //
				"                                                                    </p>\r\n" + //
				"                                                                    <p\r\n" + //
				"                                                                        style=\"Margin:0;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;letter-spacing:0;color:#333333;font-size:14px\">\r\n" + //
				"                                                                        Payment method:&nbsp;<strong>PayPal</strong></p>\r\n" + //
				"                                                                    <p\r\n" + //
				"                                                                        style=\"Margin:0;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;letter-spacing:0;color:#333333;font-size:14px\">\r\n" + //
				"                                                                        Currency:&nbsp;<strong>USD</strong></p>\r\n" + //
				"                                                                </td>\r\n" + //
				"                                                            </tr>\r\n" + //
				"                                                        </table>\r\n" + //
				"                                                    </td>\r\n" + //
				"                                                </tr>\r\n" + //
				"                                            </table>\r\n" + //
				"                                            <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-right\" align=\"right\"\r\n" + //
				"                                                role=\"none\"\r\n" + //
				"                                                style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right\">\r\n" + //
				"                                                <tr>\r\n" + //
				"                                                    <td class=\"es-m-p0r\" align=\"center\"\r\n" + //
				"                                                        style=\"padding:0;Margin:0;width:280px\">\r\n" + //
				"                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\r\n" + //
				"                                                            role=\"presentation\"\r\n" + //
				"                                                            style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" + //
				"                                                            <tr>\r\n" + //
				"                                                                <td align=\"left\" class=\"es-m-txt-l\"\r\n" + //
				"                                                                    style=\"padding:0;Margin:0\">\r\n" + //
				"                                                                    <p\r\n" + //
				"                                                                        style=\"Margin:0;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;letter-spacing:0;color:#333333;font-size:14px\">\r\n" + //
				"                                                                        Shipping Method: <strong>UPS - Ground</strong>\r\n" + //
				"                                                                    </p>\r\n" + //
				"                                                                    <p\r\n" + //
				"                                                                        style=\"Margin:0;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;letter-spacing:0;color:#333333;font-size:14px\">\r\n" + //
				"                                                                        Shipping address:</p>\r\n" + //
				"                                                                    <p\r\n" + //
				"                                                                        style=\"Margin:0;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;letter-spacing:0;color:#333333;font-size:14px\">\r\n" + //
				"                                                                        <strong>Sarah Powell,<br>600 Montgomery\r\n" + //
				"                                                                            St,<br>San Francisco, CA 94111</strong></p>\r\n" + //
				"                                                                </td>\r\n" + //
				"                                                            </tr>\r\n" + //
				"                                                        </table>\r\n" + //
				"                                                    </td>\r\n" + //
				"                                                </tr>\r\n" + //
				"                                            </table>\r\n" + //
				"                                        </td>\r\n" + //
				"                                    </tr>\r\n" + //
				"                                    <tr>\r\n" + //
				"                                        <td align=\"left\"\r\n" + //
				"                                            style=\"Margin:0;padding-bottom:10px;padding-top:15px;padding-right:20px;padding-left:20px\">\r\n" + //
				"                                            <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"none\"\r\n" + //
				"                                                style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" + //
				"                                                <tr>\r\n" + //
				"                                                    <td align=\"left\" style=\"padding:0;Margin:0;width:560px\">\r\n" + //
				"                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\r\n" + //
				"                                                            role=\"presentation\"\r\n" + //
				"                                                            style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" + //
				"                                                            <tr>\r\n" + //
				"                                                                <td align=\"center\"\r\n" + //
				"                                                                    style=\"padding:0;Margin:0;padding-bottom:10px;padding-top:10px\">\r\n" + //
				"                                                                    <p\r\n" + //
				"                                                                        style=\"Margin:0;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;letter-spacing:0;color:#333333;font-size:14px\">\r\n" + //
				"                                                                        Got a question?&nbsp;Email us at&nbsp;<a\r\n" + //
				"                                                                            target=\"_blank\" href=\"\"\r\n" + //
				"                                                                            style=\"mso-line-height-rule:exactly;text-decoration:underline;color:#5C68E2;font-size:14px\">support@</a>\r\n" + //
				"                                                                        <a target=\"_blank\" href=\"\"\r\n" + //
				"                                                                            style=\"mso-line-height-rule:exactly;text-decoration:underline;color:#5C68E2;font-size:14px\">stylecasual</a><a\r\n" + //
				"                                                                            target=\"_blank\" href=\"\"\r\n" + //
				"                                                                            style=\"mso-line-height-rule:exactly;text-decoration:underline;color:#5C68E2;font-size:14px\">.com</a>\r\n" + //
				"                                                                        &nbsp;or give us a call at&nbsp;<a\r\n" + //
				"                                                                            target=\"_blank\" href=\"\"\r\n" + //
				"                                                                            style=\"mso-line-height-rule:exactly;text-decoration:underline;color:#5C68E2;font-size:14px\">+000\r\n" + //
				"                                                                            123 456</a>.</p>\r\n" + //
				"                                                                </td>\r\n" + //
				"                                                            </tr>\r\n" + //
				"                                                        </table>\r\n" + //
				"                                                    </td>\r\n" + //
				"                                                </tr>\r\n" + //
				"                                            </table>\r\n" + //
				"                                        </td>\r\n" + //
				"                                    </tr>\r\n" + //
				"                                </table>\r\n" + //
				"                            </td>\r\n" + //
				"                        </tr>\r\n" + //
				"                    </table>\r\n" + //
				"                    <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-footer\" align=\"center\" role=\"none\"\r\n" + //
				"                        style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;width:100%;table-layout:fixed !important;background-color:transparent;background-repeat:repeat;background-position:center top\">\r\n" + //
				"                        <tr>\r\n" + //
				"                            <td align=\"center\" style=\"padding:0;Margin:0\">\r\n" + //
				"                                <table class=\"es-footer-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\"\r\n" + //
				"                                    style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:640px\"\r\n" + //
				"                                    role=\"none\">\r\n" + //
				"                                    <tr>\r\n" + //
				"                                        <td align=\"left\"\r\n" + //
				"                                            style=\"Margin:0;padding-right:20px;padding-left:20px;padding-bottom:20px;padding-top:20px\">\r\n" + //
				"                                            <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"none\"\r\n" + //
				"                                                style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" + //
				"                                                <tr>\r\n" + //
				"                                                    <td align=\"left\" style=\"padding:0;Margin:0;width:600px\">\r\n" + //
				"                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\r\n" + //
				"                                                            role=\"presentation\"\r\n" + //
				"                                                            style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" + //
				"                                                            <tr>\r\n" + //
				"                                                                <td align=\"center\"\r\n" + //
				"                                                                    style=\"padding:0;Margin:0;padding-top:15px;padding-bottom:15px;font-size:0\">\r\n" + //
				"                                                                    <table cellpadding=\"0\" cellspacing=\"0\"\r\n" + //
				"                                                                        class=\"es-table-not-adapt es-social\"\r\n" + //
				"                                                                        role=\"presentation\"\r\n" + //
				"                                                                        style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" + //
				"                                                                        <tr>\r\n" + //
				"                                                                            <td align=\"center\" valign=\"top\"\r\n" + //
				"                                                                                style=\"padding:0;Margin:0;padding-right:40px\">\r\n" + //
				"                                                                                <img title=\"Facebook\"\r\n" + //
				"                                                                                    src=\"https://fcosgad.stripocdn.email/content/assets/img/social-icons/logo-black/facebook-logo-black.png\"\r\n" + //
				"                                                                                    alt=\"Fb\" width=\"32\" height=\"32\"\r\n" + //
				"                                                                                    style=\"display:block;font-size:14px;border:0;outline:none;text-decoration:none\">\r\n" + //
				"                                                                            </td>\r\n" + //
				"                                                                            <td align=\"center\" valign=\"top\"\r\n" + //
				"                                                                                style=\"padding:0;Margin:0;padding-right:40px\">\r\n" + //
				"                                                                                <img title=\"X.com\"\r\n" + //
				"                                                                                    src=\"https://fcosgad.stripocdn.email/content/assets/img/social-icons/logo-black/x-logo-black.png\"\r\n" + //
				"                                                                                    alt=\"X\" width=\"32\" height=\"32\"\r\n" + //
				"                                                                                    style=\"display:block;font-size:14px;border:0;outline:none;text-decoration:none\">\r\n" + //
				"                                                                            </td>\r\n" + //
				"                                                                            <td align=\"center\" valign=\"top\"\r\n" + //
				"                                                                                style=\"padding:0;Margin:0;padding-right:40px\">\r\n" + //
				"                                                                                <img title=\"Instagram\"\r\n" + //
				"                                                                                    src=\"https://fcosgad.stripocdn.email/content/assets/img/social-icons/logo-black/instagram-logo-black.png\"\r\n" + //
				"                                                                                    alt=\"Inst\" width=\"32\" height=\"32\"\r\n" + //
				"                                                                                    style=\"display:block;font-size:14px;border:0;outline:none;text-decoration:none\">\r\n" + //
				"                                                                            </td>\r\n" + //
				"                                                                            <td align=\"center\" valign=\"top\"\r\n" + //
				"                                                                                style=\"padding:0;Margin:0\"><img\r\n" + //
				"                                                                                    title=\"Youtube\"\r\n" + //
				"                                                                                    src=\"https://fcosgad.stripocdn.email/content/assets/img/social-icons/logo-black/youtube-logo-black.png\"\r\n" + //
				"                                                                                    alt=\"Yt\" width=\"32\" height=\"32\"\r\n" + //
				"                                                                                    style=\"display:block;font-size:14px;border:0;outline:none;text-decoration:none\">\r\n" + //
				"                                                                            </td>\r\n" + //
				"                                                                        </tr>\r\n" + //
				"                                                                    </table>\r\n" + //
				"                                                                </td>\r\n" + //
				"                                                            </tr>\r\n" + //
				"                                                            <tr>\r\n" + //
				"                                                                <td align=\"center\"\r\n" + //
				"                                                                    style=\"padding:0;Margin:0;padding-bottom:35px\">\r\n" + //
				"                                                                    <p\r\n" + //
				"                                                                        style=\"Margin:0;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:18px;letter-spacing:0;color:#333333;font-size:12px\">\r\n" + //
				"                                                                        Style Casual&nbsp;© 2021 Style Casual, Inc. All\r\n" + //
				"                                                                        Rights Reserved.</p>\r\n" + //
				"                                                                    <p\r\n" + //
				"                                                                        style=\"Margin:0;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:18px;letter-spacing:0;color:#333333;font-size:12px\">\r\n" + //
				"                                                                        4562 Hazy Panda Limits, Chair Crossing,\r\n" + //
				"                                                                        Kentucky, US, 607898</p>\r\n" + //
				"                                                                </td>\r\n" + //
				"                                                            </tr>\r\n" + //
				"                                                            <tr>\r\n" + //
				"                                                                <td style=\"padding:0;Margin:0\">\r\n" + //
				"                                                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\r\n" + //
				"                                                                        class=\"es-menu\" role=\"presentation\"\r\n" + //
				"                                                                        style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" + //
				"                                                                        <tr class=\"links\">\r\n" + //
				"                                                                            <td align=\"center\" valign=\"top\"\r\n" + //
				"                                                                                width=\"33.33%\"\r\n" + //
				"                                                                                style=\"Margin:0;border:0;padding-bottom:5px;padding-top:5px;padding-right:5px;padding-left:5px\">\r\n" + //
				"                                                                                <a target=\"_blank\" href=\"\"\r\n" + //
				"                                                                                    style=\"mso-line-height-rule:exactly;text-decoration:none;font-family:arial, 'helvetica neue', helvetica, sans-serif;display:block;color:#999999;font-size:12px\">Visit\r\n" + //
				"                                                                                    Us </a></td>\r\n" + //
				"                                                                            <td align=\"center\" valign=\"top\"\r\n" + //
				"                                                                                width=\"33.33%\"\r\n" + //
				"                                                                                style=\"Margin:0;border:0;padding-bottom:5px;padding-top:5px;padding-right:5px;padding-left:5px;border-left:1px solid #cccccc\">\r\n" + //
				"                                                                                <a target=\"_blank\" href=\"\"\r\n" + //
				"                                                                                    style=\"mso-line-height-rule:exactly;text-decoration:none;font-family:arial, 'helvetica neue', helvetica, sans-serif;display:block;color:#999999;font-size:12px\">Privacy\r\n" + //
				"                                                                                    Policy</a></td>\r\n" + //
				"                                                                            <td align=\"center\" valign=\"top\"\r\n" + //
				"                                                                                width=\"33.33%\"\r\n" + //
				"                                                                                style=\"Margin:0;border:0;padding-bottom:5px;padding-top:5px;padding-right:5px;padding-left:5px;border-left:1px solid #cccccc\">\r\n" + //
				"                                                                                <a target=\"_blank\" href=\"\"\r\n" + //
				"                                                                                    style=\"mso-line-height-rule:exactly;text-decoration:none;font-family:arial, 'helvetica neue', helvetica, sans-serif;display:block;color:#999999;font-size:12px\">Terms\r\n" + //
				"                                                                                    of Use</a></td>\r\n" + //
				"                                                                        </tr>\r\n" + //
				"                                                                    </table>\r\n" + //
				"                                                                </td>\r\n" + //
				"                                                            </tr>\r\n" + //
				"                                                        </table>\r\n" + //
				"                                                    </td>\r\n" + //
				"                                                </tr>\r\n" + //
				"                                            </table>\r\n" + //
				"                                        </td>\r\n" + //
				"                                    </tr>\r\n" + //
				"                                </table>\r\n" + //
				"                            </td>\r\n" + //
				"                        </tr>\r\n" + //
				"                    </table>\r\n" + //
				"                    <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\" role=\"none\"\r\n" + //
				"                        style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;width:100%;table-layout:fixed !important\">\r\n" + //
				"                        <tr>\r\n" + //
				"                            <td class=\"es-info-area\" align=\"center\" style=\"padding:0;Margin:0\">\r\n" + //
				"                                <table class=\"es-content-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\"\r\n" + //
				"                                    style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:600px\"\r\n" + //
				"                                    bgcolor=\"#FFFFFF\" role=\"none\">\r\n" + //
				"                                    <tr>\r\n" + //
				"                                        <td align=\"left\" style=\"padding:20px;Margin:0\">\r\n" + //
				"                                            <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"none\"\r\n" + //
				"                                                style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" + //
				"                                                <tr>\r\n" + //
				"                                                    <td align=\"center\" valign=\"top\"\r\n" + //
				"                                                        style=\"padding:0;Margin:0;width:560px\">\r\n" + //
				"                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\r\n" + //
				"                                                            role=\"presentation\"\r\n" + //
				"                                                            style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" + //
				"                                                            <tr>\r\n" + //
				"                                                                <td align=\"center\" class=\"es-infoblock\"\r\n" + //
				"                                                                    style=\"padding:0;Margin:0\">\r\n" + //
				"                                                                    <p\r\n" + //
				"                                                                        style=\"Margin:0;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:18px;letter-spacing:0;color:#CCCCCC;font-size:12px\">\r\n" + //
				"                                                                        <a target=\"_blank\" href=\"\"\r\n" + //
				"                                                                            style=\"mso-line-height-rule:exactly;text-decoration:underline;color:#CCCCCC;font-size:12px\"></a>\r\n" + //
				"                                                                        No longer want to receive these emails?&nbsp;<a\r\n" + //
				"                                                                            href=\"\" target=\"_blank\"\r\n" + //
				"                                                                            style=\"mso-line-height-rule:exactly;text-decoration:underline;color:#CCCCCC;font-size:12px\">Unsubscribe</a>.<a\r\n" + //
				"                                                                            target=\"_blank\" href=\"\"\r\n" + //
				"                                                                            style=\"mso-line-height-rule:exactly;text-decoration:underline;color:#CCCCCC;font-size:12px\"></a>\r\n" + //
				"                                                                    </p>\r\n" + //
				"                                                                </td>\r\n" + //
				"                                                            </tr>\r\n" + //
				"                                                        </table>\r\n" + //
				"                                                    </td>\r\n" + //
				"                                                </tr>\r\n" + //
				"                                            </table>\r\n" + //
				"                                        </td>\r\n" + //
				"                                    </tr>\r\n" + //
				"                                </table>\r\n" + //
				"                            </td>\r\n" + //
				"                        </tr>\r\n" + //
				"                    </table>\r\n" + //
				"                </td>\r\n" + //
				"            </tr>\r\n" + //
				"        </table>\r\n" + //
				"    </div>\r\n" + //
				"</body>\r\n" + //
				"\r\n" + //
				"</html>";



		return html;
	}
	public String orderDetail(List<OrderDelail> orderdetail){
		String html="";
		double total = 0;
		for (OrderDelail orderDelail : orderdetail) {
			total+=orderDelail.getPrice()*orderDelail.getQuantity();
		      html+=  " <tr>\r\n" + //
				"                                        <td class=\"esdev-adapt-off\" align=\"left\"\r\n" + //
				"                                            style=\"Margin:0;padding-bottom:10px;padding-right:20px;padding-left:20px;padding-top:10px\">\r\n" + //
				"                                            <table cellpadding=\"0\" cellspacing=\"0\" class=\"esdev-mso-table\" role=\"none\"\r\n" + //
				"                                                style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;width:560px\">\r\n" + //
				"                                                <tr>\r\n" + //
				"                                                    <td class=\"esdev-mso-td\" valign=\"top\" style=\"padding:0;Margin:0\">\r\n" + //
				"                                                        <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-left\"\r\n" + //
				"                                                            align=\"left\" role=\"none\"\r\n" + //
				"                                                            style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\r\n" + //
				"                                                            <tr>\r\n" + //
				"                                                                <td class=\"es-m-p0r\" align=\"center\"\r\n" + //
				"                                                                    style=\"padding:0;Margin:0;width:70px\">\r\n" + //
				"                                                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\r\n" + //
				"                                                                        role=\"presentation\"\r\n" + //
				"                                                                        style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" + //
				"                                                                        <tr>\r\n" + //
				// "                                                                            <td align=\"center\"\r\n" + //
				// "                                                                                style=\"padding:0;Margin:0;font-size:0px\">\r\n" + //
				// "                                                                                <img class=\"adapt-img\"\r\n" + //
				// "                                                                                    src=\"/img/"+orderDelail.getColor().getImage()+"\"\r\n" + //
				// "                                                                                    alt=\"\"\r\n" + //
				// "                                                                                    style=\"display:block;font-size:14px;border:0;outline:none;text-decoration:none\"\r\n" + //
				// "                                                                                    width=\"70\" height=\"70\"></td>\r\n" + //
				"                                                                        </tr>\r\n" + //
				"                                                                    </table>\r\n" + //
				"                                                                </td>\r\n" + //
				"                                                            </tr>\r\n" + //
				"                                                        </table>\r\n" + //
				"                                                    </td>\r\n" + //
				"                                                    <td style=\"padding:0;Margin:0;width:20px\"></td>\r\n" + //
				"                                                    <td class=\"esdev-mso-td\" valign=\"top\" style=\"padding:0;Margin:0\">\r\n" + //
				"                                                        <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-left\"\r\n" + //
				"                                                            align=\"left\" role=\"none\"\r\n" + //
				"                                                            style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\r\n" + //
				"                                                            <tr>\r\n" + //
				"                                                                <td align=\"center\"\r\n" + //
				"                                                                    style=\"padding:0;Margin:0;width:265px\">\r\n" + //
				"                                                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\r\n" + //
				"                                                                        role=\"presentation\"\r\n" + //
				"                                                                        style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" + //
				"                                                                        <tr>\r\n" + //
				"                                                                            <td align=\"left\" style=\"padding:0;Margin:0\">\r\n" + //
				"                                                                                <p\r\n" + //
				"                                                                                    style=\"Margin:0;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;letter-spacing:0;color:#333333;font-size:14px\">\r\n" + //
				"                                                                                    <strong>"+orderDelail.getProduct().getName()+"</strong></p>\r\n" + //
				"                                                                            </td>\r\n" + //
				"                                                                        </tr>\r\n" + //
				"                                                                        <tr>\r\n" + //
				"                                                                            <td align=\"left\"\r\n" + //
				"                                                                                style=\"padding:0;Margin:0;padding-top:5px\">\r\n" + //
				"                                                                                <p\r\n" + //
				"                                                                                    style=\"Margin:0;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;letter-spacing:0;color:#333333;font-size:14px\">\r\n" + //
				"                                                                                    Price: "+orderDelail.getPrice()+" $ <br>Color: "+orderDelail.getColor().getColorname()+"</p>\r\n" + //
				"                                                                            </td>\r\n" + //
				"                                                                        </tr>\r\n" + //
				"                                                                    </table>\r\n" + //
				"                                                                </td>\r\n" + //
				"                                                            </tr>\r\n" + //
				"                                                        </table>\r\n" + //
				"                                                    </td>\r\n" + //
				"                                                    <td style=\"padding:0;Margin:0;width:20px\"></td>\r\n" + //
				"                                                    <td class=\"esdev-mso-td\" valign=\"top\" style=\"padding:0;Margin:0\">\r\n" + //
				"                                                        <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-left\"\r\n" + //
				"                                                            align=\"left\" role=\"none\"\r\n" + //
				"                                                            style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\r\n" + //
				"                                                            <tr>\r\n" + //
				"                                                                <td align=\"left\" style=\"padding:0;Margin:0;width:80px\">\r\n" + //
				"                                                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\r\n" + //
				"                                                                        role=\"presentation\"\r\n" + //
				"                                                                        style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" + //
				"                                                                        <tr>\r\n" + //
				"                                                                            <td align=\"center\"\r\n" + //
				"                                                                                style=\"padding:0;Margin:0\">\r\n" + //
				"                                                                                <p\r\n" + //
				"                                                                                    style=\"Margin:0;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;letter-spacing:0;color:#333333;font-size:14px\">\r\n" + //
				"                                                                                    "+orderDelail.getQuantity()+"</p>\r\n" + //
				"                                                                            </td>\r\n" + //
				"                                                                        </tr>\r\n" + //
				"                                                                    </table>\r\n" + //
				"                                                                </td>\r\n" + //
				"                                                            </tr>\r\n" + //
				"                                                        </table>\r\n" + //
				"                                                    </td>\r\n" + //
				"                                                    <td style=\"padding:0;Margin:0;width:20px\"></td>\r\n" + //
				"                                                    <td class=\"esdev-mso-td\" valign=\"top\" style=\"padding:0;Margin:0\">\r\n" + //
				"                                                        <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-right\"\r\n" + //
				"                                                            align=\"right\" role=\"none\"\r\n" + //
				"                                                            style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right\">\r\n" + //
				"                                                            <tr>\r\n" + //
				"                                                                <td align=\"left\" style=\"padding:0;Margin:0;width:85px\">\r\n" + //
				"                                                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\r\n" + //
				"                                                                        role=\"presentation\"\r\n" + //
				"                                                                        style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" + //
				"                                                                        <tr>\r\n" + //
				"                                                                            <td align=\"right\"\r\n" + //
				"                                                                                style=\"padding:0;Margin:0\">\r\n" + //
				"                                                                                <p\r\n" + //
				"                                                                                    style=\"Margin:0;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;letter-spacing:0;color:#333333;font-size:14px\">\r\n" + //
				"                                                                                    $"+orderDelail.getTotalPrice()+"</p>\r\n" + //
				"                                                                            </td>\r\n" + //
				"                                                                        </tr>\r\n" + //
				"                                                                    </table>\r\n" + //
				"                                                                </td>\r\n" + //
				"                                                            </tr>\r\n" + //
				"                                                        </table>\r\n" + //
				"                                                    </td>\r\n" + //
				"                                                </tr>\r\n" + //
				"                                            </table>\r\n" + //
				"                                        </td>\r\n" + //
				"                                    </tr>";
		}
		html+="                                    <tr>\r\n" + //
				"                                        <td align=\"left\"\r\n" + //
				"                                            style=\"padding:0;Margin:0;padding-right:20px;padding-left:20px;padding-top:10px\">\r\n" + //
				"                                            <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"none\"\r\n" + //
				"                                                style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n" + //
				"                                                <tr>\r\n" + //
				"                                                    <td class=\"es-m-p0r\" align=\"center\"\r\n" + //
				"                                                        style=\"padding:0;Margin:0;width:560px\">\r\n" + //
				"                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\r\n" + //
				"                                                            style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;border-top:2px solid #efefef;border-bottom:2px solid #efefef\"\r\n" + //
				"                                                            role=\"presentation\">\r\n" + //
				"                                                            <tr>\r\n" + //
				"                                                                <td align=\"right\" class=\"es-m-txt-r\"\r\n" + //
				"                                                                    style=\"padding:0;Margin:0;padding-top:10px;padding-bottom:20px\">\r\n" + //
				"                                                                    <p\r\n" + //
				"                                                                        style=\"Margin:0;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;letter-spacing:0;color:#333333;font-size:14px\">\r\n" + //
				"                                                                        <br>Shipping:&nbsp;<strong>$0.00</strong><br>Tax:&nbsp;<strong>$10.00</strong><br>Total:&nbsp;<strong>$"+total+"</strong>\r\n" + //
				"                                                                    </p>\r\n" + //
				"                                                                </td>\r\n" + //
				"                                                            </tr>\r\n" + //
				"                                                        </table>\r\n" + //
				"                                                    </td>\r\n" + //
				"                                                </tr>\r\n" + //
				"                                            </table>\r\n" + //
				"                                        </td>\r\n" + //
				"                                    </tr>";
		return html;
				
	}

  
}
