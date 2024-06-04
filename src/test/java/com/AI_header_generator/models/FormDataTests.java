package com.AI_header_generator.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormDataTests {

    private FormData formData;

    @BeforeEach
    public void setup() {
        formData = new FormData();
        formData.setFormField1("value1");
        formData.setFormField2("value2");
        formData.setFormField3("value3");
    }

    @Test
    public void testGetFormField1() {
        assertEquals("value1", formData.getFormField1());
    }

    @Test
    public void testGetFormField2() {
        assertEquals("value2", formData.getFormField2());
    }

    @Test
    public void testGetFormField3() {
        assertEquals("value3", formData.getFormField3());
    }
}