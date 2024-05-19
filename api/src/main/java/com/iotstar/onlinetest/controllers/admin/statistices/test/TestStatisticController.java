package com.iotstar.onlinetest.controllers.admin.statistices.test;

import com.iotstar.onlinetest.DTOs.responses.Response;
import com.iotstar.onlinetest.DTOs.responses.statistices.TestStatisticResponse;
import com.iotstar.onlinetest.DTOs.responses.statistices.TestStatisticResponseUser;
import com.iotstar.onlinetest.models.statistics.TestStatistic;
import com.iotstar.onlinetest.services.statistices.test.TestStatisticPaging;
import com.iotstar.onlinetest.services.statistices.test.TestStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@CrossOrigin
public class TestStatisticController implements ITestStatisticController{
    @Autowired
    private TestStatisticService service;
    @Autowired
    private TestStatisticPaging paging;

    @Override
    public ResponseEntity<Response> getTestStatistic(int index, int size, String paramSort) {
        paging.setPageSize(size);
        paging.setPageIndex(index);
        Sort sort ;
        if (paramSort.equals("increase"))
            sort = Sort.by(Sort.Direction.ASC, "numberUserTest");
        else
            sort = Sort.by(Sort.Direction.DESC, "numberUserTest");
        paging.setSort(sort);
        List<TestStatisticResponse> responses = service.getTestStatistic();
        return new ResponseEntity<>(
                new Response(false, responses),
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<Response> getTestStatisticUser(int index,
                                                         int size,
                                                         String paramSort,
                                                         Long testId) {
        paging.setPageSize(size);
        paging.setPageIndex(index);
        Sort sort ;
        if (paramSort.equals("increase"))
            sort = Sort.by(Sort.Direction.ASC, "score");
        else
            sort = Sort.by(Sort.Direction.DESC, "score");
        paging.setSort(sort);
        List<TestStatisticResponseUser> responses = service.getUserFinishTest(testId);
        return new ResponseEntity<>(
                new Response(false, responses),
                HttpStatus.OK
        );
    }
}
