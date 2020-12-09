package com.arrizaqu.samplecrud.datatable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Datatables<T> {

	public int draw;
	public int length;
	public int start;
	private int recordsTotal;
	private int recordsFiltered;
	private List<Map<ColumnCriterias, String>> columns;
	private Map<SearchCriterias, String> search;
	private List<Map<OrderCriterias, String>> order;
	
	public List<Map<ColumnCriterias, String>> getColumns() {
		return columns;
	}

	public void setColumns(List<Map<ColumnCriterias, String>> columns) {
		this.columns = columns;
	}
	private List<T> data;
	
	public Map<SearchCriterias, String> getSearch() {
		return search;
	}

	public void setSearch(Map<SearchCriterias, String> search) {
		this.search = search;
	}

	public List<Map<OrderCriterias, String>> getOrder() {
		return order;
	}

	public void setOrder(List<Map<OrderCriterias, String>> order) {
		this.order = order;
	}

	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	public int getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public int getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	
	
}
