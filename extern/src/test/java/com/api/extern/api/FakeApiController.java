package com.api.extern.api;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FakeApiController {
    @InjectMocks
    FakeApiController controller;
}
