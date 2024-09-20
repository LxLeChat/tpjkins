package hello.service;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@EnableConfigurationProperties(ServiceProperties.class)
public class MyService {

    private final ServiceProperties serviceProperties;

    public MyService(ServiceProperties serviceProperties) {
        this.serviceProperties = serviceProperties;
    }

    public String message() {
    	System.out.println("About to return : " + this.serviceProperties.getMessage());
        return this.serviceProperties.getMessage();
    }
    
    public void setMessage(String msg) {
    	System.out.println("About to set message with : " + msg);
        this.serviceProperties.setMessage(msg);
    }
}
