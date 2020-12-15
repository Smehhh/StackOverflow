package com.example.demo.web;

import com.example.demo.service.StackOverflowService;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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