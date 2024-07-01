package com.example.mtlstester;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import java.security.cert.X509Certificate;

@SpringBootApplication
@RestController
public class MtlstesterApplication {

	public static void main(String[] args) {
		SpringApplication.run(MtlstesterApplication.class, args);
	}

	@GetMapping("/")
	public String index(HttpServletRequest request) {
		String retVal = "Cert-Checker2\n\n";
        Map<String,String> headers = getHeaders(request);
        System.out.println(headers.toString());
		retVal += headers.toString() + "\n";

		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			String headerValue = request.getHeader(headerName);
			// if (headerName == "x-forwarded-client-cert")
			retVal += "headerName: " + headerName + "\t\theaderValue: " + headerValue + "\n";
		}

		retVal += "\n\n\n";

		// Enumeration<String> attributeNames = request.getAttributeNames();
		// while (attributeNames.hasMoreElements()) {
		// 	String attributeName = attributeNames.nextElement();
		// 	String attributeValue = request.getAttribute(attributeName).toString();
		// 	// if (attributeName == "jakarta.servlet.request.X509Certificate")
		// 	retVal += "AttributeName: " + attributeName + "\t\tAttributeValue: " + attributeValue + "\n";
		// }


		// retVal += "\n\n***\n\n";

		
		X509Certificate[] certs = (X509Certificate[]) request.getAttribute("jakarta.servlet.request.X509Certificate");
		if (certs != null) {
		retVal += "\n****\n";
	    retVal += certs[0].getSubjectX500Principal().getName();
		}
		else {
			retVal += "No CERTS detected\n";
		}
		return retVal;
	}


	public Map<String,String> getHeaders(HttpServletRequest request) {
		Enumeration<String> headerNames = request.getHeaderNames();
		Map<String,String> headersMap = new HashMap<>();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			String headerValue = request.getHeader(headerName);
			headersMap.put(headerName,headerValue);
			System.out.println("NAME: " + headerName + "  VALUE: " + headerValue);
		}
		return headersMap.isEmpty() ? Collections.singletonMap("message", "no headers found") : headersMap;

	}


	public static String mapToString(Map<String, String> map) {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            result.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }

        return result.toString();
    }

}
