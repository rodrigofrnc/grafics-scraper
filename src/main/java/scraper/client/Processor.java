package scraper.client;

import java.util.List;

public class Processor {

    private final Gmail host;

    public Processor(Gmail host) {
        this.host = host;
    }

    public void process(List<String> listEmailAddressTo, String subject, List<String> emailContent){
        for (String emailAddressTo : listEmailAddressTo) {
            host.send(emailAddressTo, subject, generateTableOfContent(emailContent));
        }
    }


    private String generateTableOfContent(List<String> emailContent){
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<style>\n" +
                "table, th, td {\n" +
                "  border: 1px solid black;\n" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>" +
                "\n" +
                "<h2>Gráficas disponíveis</h2>\n" +
                "\n" +
                "<table style=\"width:100%\">\n" +
                "  <tr>");

        for (String row: emailContent) {
            html.append("<td>").append(row).append("</td>");
        }

        html.append("  </tr>\n" +
                "</table>\n" +
                "\n" +
                "</body>\n" +
                "</html>");

        return html.toString();
    }
}
