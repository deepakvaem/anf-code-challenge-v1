package com.anf.core.models;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.jcr.RepositoryException;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class NewDataModel {

private static final Logger logger = LoggerFactory.getLogger(NewDataModel.class);

@Self
private Resource resource;

private List<String> newFeed = new ArrayList<>();

private String feed = "/var/commerce/products/anf-code-challenge/newsData";

@PostConstruct
protected void init() throws RepositoryException {
final Resource currentResource = resource.getChild(feed);
int count = 0;
for (final Resource childResource : currentResource.getChildren()) {
final ValueMap valueMap = childResource.getValueMap();
if (null != valueMap.get("author")) {
String author = valueMap.get("author", String.class);
newFeed.add(author);
logger.info(" author :: {}", author);

}
if (null != valueMap.get("content")) {
String content = valueMap.get("content", String.class);
logger.info(" content :: {}", content);
newFeed.add(content);
}
if (null != valueMap.get("description")) {
String description = valueMap.get("description", String.class);
logger.info(" description :: {}", description);
newFeed.add(description);
}

if (null != valueMap.get("title")) {
String title = valueMap.get("title", String.class);
logger.info(" title :: {}", title);
newFeed.add(title);
}
if (null != valueMap.get("url")) {
String url = valueMap.get("url", String.class);
logger.info(" url :: {}", url);
newFeed.add(url);
}
if (null != valueMap.get("urlImage")) {
String urlImage = valueMap.get("urlImage", String.class);
logger.info(" urlImage :: {}", urlImage);
newFeed.add(urlImage);
}
count++;
logger.info("count ::{}",count);
}
}


public List<String> getNewFeed() {
logger.info("newFeed :::{}",newFeed);
return newFeed;
}

}