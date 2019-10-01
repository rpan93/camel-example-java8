package org.apache.camel.example.java8;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class FileProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        String originalFileName = (String) exchange.getIn().getHeader(
            Exchange.FILE_NAME, String.class);

        System.out.println("Logger: "
            + "\t\theaders size:"+ exchange.getIn().getHeaders().size() + "\n");
        for (Map.Entry<String, Object> currentHeader : exchange.getIn().getHeaders().entrySet())
        {
            System.out.println("\t\tHeader name:" + currentHeader.getKey() + "\tValue:" + currentHeader.getValue());
        }
        //content file
        String content = exchange.getIn().getBody(String.class);
        System.out.println(content);
        String upperCaseFileContent = content.toUpperCase();
        //set file content as upper case - change the content
        exchange.getIn().setBody(upperCaseFileContent);

        //rename file
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(
            "yyyy-MM-dd_HH-mm-ss");
        String changedFileName = dateFormat.format(date) + originalFileName;
        exchange.getIn().setHeader(Exchange.FILE_NAME, changedFileName);
        System.out.println(changedFileName);
    }
}
