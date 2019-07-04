package com.rabobank.statementprocessor.controller;

import com.rabobank.statementprocessor.service.StatementProcessorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class StatementProcessorControllerTest {

    private InputStream is;
    private MockMvc mockMvc;

    @Mock
    private StatementProcessorService statementProcessorService;

    @Spy
    @InjectMocks
    private StatementProcessorController controller = new StatementProcessorController(statementProcessorService);

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        is = controller.getClass().getClassLoader().getResourceAsStream("/records.xml");
    }

    @Test
    public void testUploadFile() throws Exception {
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "records.xml", "multipart/form-data", is);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.multipart("/customer/api/v1/process-statement").file(mockMultipartFile).contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(MockMvcResultMatchers.status().is(200)).andReturn();
        assertEquals(200, result.getResponse().getStatus());
        assertNotNull(result.getResponse().getContentAsString());
    }
}
