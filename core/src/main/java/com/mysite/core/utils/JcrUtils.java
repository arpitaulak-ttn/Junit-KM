package com.mysite.core.utils;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;

import java.util.Optional;

public class JcrUtils {

    public static String getPageProperty(Resource resource, String propertyName){
        PageManager pageManager = Optional.of(resource).map(Resource::getResourceResolver).map(resourceResolver -> resourceResolver.adaptTo(PageManager.class)).orElse(null);
        return Optional.ofNullable(pageManager)
                    .map(pm -> pm.getContainingPage(resource))
                    .map(Page::getProperties).map(valueMap -> valueMap.get(propertyName,String.class)).orElse(StringUtils.EMPTY);

    }
}
