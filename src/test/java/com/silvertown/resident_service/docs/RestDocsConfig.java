package com.silvertown.resident_service.docs;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;

@TestConfiguration
public class RestDocsConfig {

    @Bean
    public RestDocumentationResultHandler restDocumentationResultHandler() {
        return document(
                "{class-name}/{method-name}",
                preprocessRequest(
                        modifyHeaders()
                                .remove("Content-Length")
                                .remove("Host"),
                        prettyPrint()),
                preprocessResponse(
                        modifyHeaders()
                                .remove("Content-Length")
                                .remove("X-Content-Type-Options")
                                .remove("X-XSS-Protection")
                                .remove("Cache-Control")
                                .remove("Pragma")
                                .remove("Expires")
                                .remove("X-Frame-Options"),
                        prettyPrint())
        );
    }
}
