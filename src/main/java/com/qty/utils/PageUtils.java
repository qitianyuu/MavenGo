package com.qty.utils;

public class PageUtils {
    private int pageNow = 1;//当前页码
    private int pageSize = 5;//每页记录数
    private int totalItem;//总记录数
    private int offset = 0;//每页第一条数据
    private int totalPage;//总页数

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        //计算pageNow同时将offset赋值
        offset = pageSize*(pageNow - 1);
        this.pageNow = pageNow;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(int totalItem) {
        //设置总项目数同时计算总页数，页码与总记录数取余，不足一页也要一页
        if(totalItem%this.pageSize!=0){
            this.totalPage = totalItem / this.pageSize + 1;
        }else{
            this.totalPage = totalItem / this.pageSize;
        }
        this.totalItem = totalItem;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
