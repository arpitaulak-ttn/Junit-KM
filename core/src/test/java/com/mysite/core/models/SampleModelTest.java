package com.mysite.core.models;

import com.mysite.core.utils.JcrUtils;
import org.apache.sling.api.resource.Resource;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.*;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ JcrUtils.class })
public class SampleModelTest {

    @Rule
    public MockitoRule mockitorule = MockitoJUnit.rule();

    @InjectMocks
    private SampleModel sampleModel;

    @Mock
    private Resource resource;

    @Before
    public void before() {
        sampleModel.propertyName = "productPagePath";
        PowerMockito.mockStatic(JcrUtils.class);
        PowerMockito.when(JcrUtils.getPageProperty(resource, "productPagePath")).thenReturn("/content/mysite/en/product");
    }

    @Test
    public void getPropertyValue() {
        sampleModel.init();
        assertEquals("/content/mysite/en/product", sampleModel.getPropertyValue());
    }
}