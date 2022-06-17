package com.mysite.core.models;

import com.mysite.core.bean.CtaLinkData;
import com.mysite.core.service.LinkService;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CtaBarExerciseModel {

    @OSGiService
    private LinkService linkService;

    @ChildResource
    private List<String> links;

    List<CtaLinkData> finalLinks;

    @PostConstruct
    public void init() {
        if (!links.isEmpty()){
            finalLinks = new ArrayList<>();
            links.forEach(s -> {
                CtaLinkData ctaLinkData = new CtaLinkData();
                ctaLinkData.setLink(linkService.createLink(s));
                ctaLinkData.setTarget(linkService.linkTarget(s));
                finalLinks.add(ctaLinkData);
            });
        }
    }

    public List<CtaLinkData> getFinalLinks() {
        return finalLinks;
    }
}
