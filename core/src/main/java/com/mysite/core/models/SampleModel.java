/*
 *  Copyright 2015 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.mysite.core.models;

import com.mysite.core.utils.JcrUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;

/**
 * The type Sample model.
 */
@Model(adaptables = Resource.class)
public class SampleModel {

    /**
     * The Property name.
     */
    @ValueMapValue
    protected String propertyName;

    @Self
    private Resource currentResource;

    private String propertyValue;

    /**
     * Init.
     */
    @PostConstruct
    public void init() {
        propertyValue = JcrUtils.getPageProperty(currentResource, propertyName);
    }

    /**
     * Gets property value.
     *
     * @return the property value
     */
    public String getPropertyValue() {
        return propertyValue;
    }
}
