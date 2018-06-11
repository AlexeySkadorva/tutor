package by.bsu.tutor.models.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SearchForm {

    private static final int FIRST_PAGE_NUMBER = 1;
    private static final int DEFAULT_PAGE_SIZE = 5;

    private Integer pageNumber;
    private Integer pageSize = 5;
    private Integer pageCount;
    private String sortField;
    private String sortOrder;

    private List<Integer> subjectIds = new ArrayList<>();
    private List<Integer> cityIds = new ArrayList<>();


    public void adjust(long recordCount) {
        pageCount = (int) Math.ceil((double) recordCount / pageSize);

        if (pageNumber == null || recordCount == 0) {
            pageNumber = FIRST_PAGE_NUMBER;
        } else if (pageNumber > pageCount) {
            pageNumber = pageCount;
        }
    }
}
