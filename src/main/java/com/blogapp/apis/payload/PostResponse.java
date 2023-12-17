package com.blogapp.apis.payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {

	private List<PostDto> postDtoList;
	private Integer pageNumber;
	private Integer pageSize;
	private Integer totalPages;
	private Long totalElements;
	private Boolean isLastPage;
}
