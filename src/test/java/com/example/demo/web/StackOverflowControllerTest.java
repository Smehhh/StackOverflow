package com.example.demo.web;

import com.example.demo.service.StackOverflowService;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StackOverflowControllerTest {
    @Mock
    private StackOverflowService stackOverflowService;

    @InjectMocks
    StackOverflowController sut;

    @Test
    void getListOfProviders() {
        when(stackOverflowService.findAll()).thenReturn(ImmutableList.of());
        sut.getListOfProviders();
        //check that findall is called
        verify(stackOverflowService).findAll();
    }
}