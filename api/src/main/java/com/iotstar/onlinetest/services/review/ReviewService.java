package com.iotstar.onlinetest.services.review;

import com.iotstar.onlinetest.DTOs.requests.ReviewItemRequest;
import com.iotstar.onlinetest.DTOs.responses.ReviewItemResponse;

import java.util.List;

public interface ReviewService {
    ReviewItemResponse addReview(ReviewItemRequest request);
    List<ReviewItemResponse> getAllReviewByTestId(Long testId);
    List<ReviewItemResponse> getAllReviewByUser(Long userId);
    void deleteReview(Long userId, Long testId);
    ReviewItemResponse updateReview(ReviewItemRequest request);
}
