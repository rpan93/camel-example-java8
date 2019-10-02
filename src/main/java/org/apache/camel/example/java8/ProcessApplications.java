package org.apache.camel.example.java8;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class ProcessApplications {

public static void main(String[] args) throws Exception {
    CamelContext camelContext = new DefaultCamelContext();
    System.out.println("Apache Camel Context Intialize....");
    try {
        //copy files from folder
        camelContext.addRoutes(copyMoveFilesRouteBuilder("file:D:/data/Test/in?noop=true","file:D:data/Test/out"));

        //move files from folder A to folder B
       // camelContext.addRoutes(copyMoveFilesRouteBuilder("file:D:\\data\\Test\\in?delete=true&noop=true","file:D:\\\\data\\\\Test\\\\out"));

        System.out.println("Files copy To Output Directory....");
        camelContext.start();
        Thread.sleep(5000);
       camelContext.stop();
    } catch (Exception e) {
        e.getStackTrace();
    }
}

private static RouteBuilder copyMoveFilesRouteBuilder(String from, String to) {
    return  new RouteBuilder() {
        @Override
        public void configure() throws Exception {
            from(from)
                .process(new FileProcessor());
               // .to(to);
        }
    };
}

}
