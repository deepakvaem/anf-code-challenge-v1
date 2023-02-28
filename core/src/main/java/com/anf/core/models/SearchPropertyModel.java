package com.anf.core.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;

@Model(adaptables = SlingHttpServletRequest.class)
public class SearchPropertyModel {

private static final Logger logger = LoggerFactory.getLogger(SearchPropertyModel.class);
public static String PATH = "/content/anf-code-challenge/us/en";
public static String SEARCH_TAG = "anfCodeChallenge";

    @Self
    private SlingHttpServletRequest request;

@OSGiService
private QueryBuilder builder;
private List<String> searchResult=new ArrayList<>();

@PostConstruct
protected void init() throws RepositoryException {
Session session = request.getResourceResolver().adaptTo(Session.class);
logger.info("session ::{}", session);
Map<String, String> map = new HashMap<String, String>();
map.put("path", PATH);
map.put("type", "cq:PageContent");
map.put("property", SEARCH_TAG); // combine this group with OR
//map.put("1_property.value", "true");
map.put("p.offset", "0");
map.put("p.limit", "10");
Query query = builder.createQuery(PredicateGroup.create(map), session);
query.setStart(0);
query.setHitsPerPage(10);
SearchResult result = query.getResult();
logger.info("result ::{}", result);
for (Hit hit : result.getHits()) {
String resultHit = hit.getPath();
logger.info("resultHit ::{}", resultHit);
searchResult.add(resultHit);
logger.info("searchResult ::{}", searchResult);
}
}

public List<String> getSearchResult() {
return searchResult;
}



}