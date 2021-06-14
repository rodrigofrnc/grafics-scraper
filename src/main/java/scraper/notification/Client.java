package scraper.notification;

import java.util.List;

public class Client {

    private final Gmail host;

    public Client(Gmail host) {
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
                "    <title>Scraper fidings</title>\n" +
                "    <style>\n" +
                "        .styled-table {\n" +
                "            border-collapse: collapse;\n" +
                "            margin: 25px 0;\n" +
                "            font-size: 0.9em;\n" +
                "            font-family: sans-serif;\n" +
                "            min-width: 400px;\n" +
                "            box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);\n" +
                "        }\n" +
                "\n" +
                "        .styled-table thead tr {\n" +
                "            background-color: #009879;\n" +
                "            color: #ffffff;\n" +
                "            text-align: left;\n" +
                "        }\n" +
                "\n" +
                "        .styled-table th,\n" +
                "        .styled-table td {\n" +
                "            padding: 12px 15px;\n" +
                "        }\n" +
                "\n" +
                "        .styled-table tbody tr {\n" +
                "            border-bottom: 1px solid #dddddd;\n" +
                "        }\n" +
                "\n" +
                "        .styled-table tbody tr:nth-of-type(even) {\n" +
                "            background-color: #f3f3f3;\n" +
                "        }\n" +
                "\n" +
                "        .styled-table tbody tr:last-of-type {\n" +
                "            border-bottom: 2px solid #009879;\n" +
                "        }\n" +
                "\n" +
                "        .styled-table tbody tr.active-row {\n" +
                "            font-weight: bold;\n" +
                "            color: #009879;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "\n" +
                "    <table class=\"styled-table\">\n" +
                "        <thead>\n" +
                "            <tr>\n" +
                "                <th>Scraper</th>\n" +
                "            </tr>\n" +
                "        </thead>\n" +
                "        <tbody>\n" +
                "            ");

        for (String row: emailContent) {
            html.append("            <tr  class=\"active-row\">\n" +
                    "                <td>")
                    .append(row)
                    .append("</td>\n" +
                            "            </tr>\n" +
                            "            ");
        }

        html.append("\n" +
                "        </tbody>\n" +
                "    </table>\n" +
                "\n" +
                "</body>\n" +
                "\n" +
                "</html>");

        return html.toString();
    }
}
