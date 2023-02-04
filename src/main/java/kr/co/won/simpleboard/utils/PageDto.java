package kr.co.won.simpleboard.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageDto {

    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int DEFAULT_MAX_PAGE_SIZE = 50;

    private int page;
    private int size;
    private String type;
    private String keyword;

    public PageDto() {
        this.page = 1;
        this.size = DEFAULT_PAGE_SIZE;
    }

    public int getPage() {
        return page;
    }

    // page 가 0보다 낮은 값을 준다면, 1로 변경
    public void setPage(int page) {
        this.page = page < 0 ? 0 : page;
    }

    public int getSize() {
        return size;
    }

    // 최대 리스트 갯수는 50개로 제한
    public void setSize(int size) {

        this.size = size < DEFAULT_PAGE_SIZE || size > DEFAULT_MAX_PAGE_SIZE ? DEFAULT_PAGE_SIZE : size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    // pageable 객체 생성
    public Pageable makePageable(int direction, String... props) {
        Sort.Direction sortDirection = direction == 0 ? Sort.Direction.DESC : Sort.Direction.ASC;
        return PageRequest.of(this.page - 1, this.size, Sort.by(sortDirection, props));
    }
}
