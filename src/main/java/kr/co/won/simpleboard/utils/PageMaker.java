package kr.co.won.simpleboard.utils;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString(exclude = "pageList")
public class PageMaker<T> {

    private Page<T> result;
    private Pageable prevPage;
    private Pageable nextPage;
    private int currentPageNumber;
    private int totalPageNumber;
    private Pageable currentPage;
    private List<Pageable> pageList;

    public PageMaker(Page<T> result) {
        this.result = result;
        this.currentPage = result.getPageable();
        this.currentPageNumber = result.getNumber();
        this.totalPageNumber = result.getTotalPages();
        this.pageList = new ArrayList<>();
        calcPage();
    }

    private void calcPage() {

        int tempEndPageNumber = (int) Math.ceil((this.currentPageNumber / 10.0) * 10);
        int startNumber = tempEndPageNumber - 9;

        // prev page link get
        Pageable startPage = this.currentPage;
        for (int i = startNumber; i < this.currentPageNumber; i++) {
            startPage = startPage.previousOrFirst();
        }
        this.prevPage = startPage.getPageNumber() <= 0 ? null : startPage.previousOrFirst();

        // next page link get
        if (this.totalPageNumber < tempEndPageNumber) {
            tempEndPageNumber = this.totalPageNumber;
            this.nextPage = null;
        }

        for (int i = startNumber; i <= tempEndPageNumber; i++) {
            pageList.add(startPage);
            startPage = startPage.next();
        }
        this.nextPage = startPage.getPageNumber() < totalPageNumber ? startPage : null;
    }
}
