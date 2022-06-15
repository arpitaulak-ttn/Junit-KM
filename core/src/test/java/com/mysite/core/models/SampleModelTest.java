package com.mysite.core.models;

import com.mysite.core.utils.JcrUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ JcrUtils.class })
public class SampleModelTest {

    @Rule
    public MockitoRule mockitorule = MockitoJUnit.rule();

    @InjectMocks
    private SampleModel sampleModel;

    @Mock
    private Resource resource;

    @Mock
    private ValueMap valueMap;

    @Before
    public void before(){
        when(resource.getValueMap()).thenReturn(valueMap);
        when(valueMap.get("propertyName")).thenReturn("productPagePath");
        PowerMockito.mockStatic(JcrUtils.class);
        PowerMockito.when(JcrUtils.getPageProperty(resource, "productPagePath")).thenReturn("/content/mysite/en/product");
    }

    @Test
    public void getPropertyValue() {
        sampleModel.init();
        assertEquals("/content/mysite/en/product", sampleModel.getPropertyValue());
    }
}