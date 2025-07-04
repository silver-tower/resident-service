package com.silvertown.resident_service.docs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.Import;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;

@Import(RestDocsConfig.class)
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
public class RestDocsTestSupport {

    protected RestDocumentationResultHandler restDocs;
    protected MockMvc mockMvc;

    @BeforeEach
    void setUp(final WebApplicationContext context,
               final RestDocumentationContextProvider provider) {
        this.restDocs = document("{class-name}/{method-name}",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())
        );

        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(MockMvcRestDocumentation.documentationConfiguration(provider))
                .alwaysDo(MockMvcResultHandlers.print())
                .alwaysDo(restDocs)
                .build();
    }
}
