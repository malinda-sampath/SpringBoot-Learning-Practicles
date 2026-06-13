package com.springboot.point_of_sale.dto.paginated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginatedResponseDTO<T> {
    private long dataCount;
    private int totalPages;
    private int pageNumber;
    private int pageSize;
    private T data;
}
