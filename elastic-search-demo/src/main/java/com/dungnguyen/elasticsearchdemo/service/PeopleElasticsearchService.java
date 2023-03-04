package com.dungnguyen.elasticsearchdemo.service;


import com.dungnguyen.elasticsearchdemo.entity.People;
import com.dungnguyen.utils.SequenceGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.RequestLine;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Service
@RequiredArgsConstructor
public class PeopleElasticsearchService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final RestClient restClient;
    private final ObjectMapper objectMapper;
    private final SequenceGenerator sequenceGenerator;

    public void save(People people) throws JsonProcessingException {
        people.setId(sequenceGenerator.nextId());
        Request request = new Request(
                "POST",
                "/people/_create/" + people.getId());
        request.setJsonEntity(objectMapper.writeValueAsString(people));
        Cancellable cancellable = restClient.performRequestAsync(request,
                new ResponseListener() {
                    @Override
                    public void onSuccess(Response response) {
                        logger.info("SAVE_SUCCESS", response.toString());
                    }

                    @Override
                    public void onFailure(Exception exception) {
                        logger.info("SAVE_FAILED", exception.getMessage());
                        exception.printStackTrace();
                    }
                });
    }

    public void saveAll(List<People> people) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(people.size());
        people.forEach( p -> {
            p.setId(sequenceGenerator.nextId());
            Request request = new Request("PUT", "/people/_create/" + p.getId());
            //let's assume that the documents are stored in an HttpEntity array
            try {
                request.setJsonEntity(objectMapper.writeValueAsString(p));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            restClient.performRequestAsync(
                    request,
                    new ResponseListener() {
                        @Override
                        public void onSuccess(Response response) {
                            logger.info("SAVE_SUCCESS", response.toString());
                            latch.countDown();
                        }

                        @Override
                        public void onFailure(Exception exception) {
                            logger.info("SAVE_FAILED", exception.getMessage());
                            latch.countDown();
                        }
                    }
            );
        });
        latch.await();
    }

    public void findAll() throws IOException {
        Response response = restClient.performRequest(new Request("GET", "/people/_search"));
        RequestLine requestLine = response.getRequestLine();
        HttpHost host = response.getHost();
        int statusCode = response.getStatusLine().getStatusCode();
        Header[] headers = response.getHeaders();
        String responseBody = EntityUtils.toString(response.getEntity());
    }

}
