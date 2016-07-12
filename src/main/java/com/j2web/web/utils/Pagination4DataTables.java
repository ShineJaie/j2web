package com.j2web.web.utils;

import com.alibaba.fastjson.JSONArray;

import java.util.List;
import java.util.Map;

/**
 * DataTables 插件的分页参数 <br>
 * 查询完一定要设置 draw, recordsTotal, recordsFiltered, data
 */
public class Pagination4DataTables {


    private Integer draw = 1; // DataTables 队列
    private Integer start = 0; // 从第几页开始
    private Integer length = 10; // 每页显示的条数
    private Integer recordsTotal = null; // 筛选前的总记录数
    private Integer recordsFiltered = null; // 筛选后的记录数
    public JSONArray data = new JSONArray(); // 返回的结果, DT_RowId: 返回 tr 节点的 id

    private Map<SearchCriterias, Object> search; // 搜索参数

    private List<Map<ColumnCriterias, Object>> columns; // 关于列的相关操作参数

    private List<Map<OrderCriterias, Object>> order; // 关于排序的相关操作参数

    public enum SearchCriterias {
        value, regex
    }

    public enum OrderCriterias {
        column, dir
    }

    public enum ColumnCriterias {
        data, name, searchable, orderable, searchValue, searchRegex
    }

    // ===========================================


    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Integer recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Integer getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Integer recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public JSONArray getData() {
        return data;
    }

    public void setData(JSONArray data) {
        this.data = data;
    }

    public Map<SearchCriterias, Object> getSearch() {
        return search;
    }

    public void setSearch(Map<SearchCriterias, Object> search) {
        this.search = search;
    }

    public List<Map<ColumnCriterias, Object>> getColumns() {
        return columns;
    }

    public void setColumns(List<Map<ColumnCriterias, Object>> columns) {
        this.columns = columns;
    }

    public List<Map<OrderCriterias, Object>> getOrder() {
        return order;
    }

    public void setOrder(List<Map<OrderCriterias, Object>> order) {
        this.order = order;
    }
}
