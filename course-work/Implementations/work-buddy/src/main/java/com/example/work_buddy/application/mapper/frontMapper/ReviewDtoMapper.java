package com.example.work_buddy.application.mapper.frontMapper;

import com.example.work_buddy.application.dto.ReviewDto;
import com.example.work_buddy.domain.model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring", uses = {WorkTaskDtoMapper.class})
public interface ReviewDtoMapper {

    @Mapping(target = "workTask", source = "workTask")
    ReviewDto toReviewDto(Review review);

    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "worker", ignore = true)
    @Mapping(target = "workTask", ignore = true)
    Review toReviewDomain(ReviewDto reviewDto);

    List<ReviewDto> toReviewDtoList(List<Review> list);

}
