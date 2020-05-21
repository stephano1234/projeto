package br.com.contmatic.service.parametros;

import java.util.Map;

import org.bson.Document;

public class FindParams {

	private Map<String, String> filter;
	
	private Map<String, Integer> sort;
	
	private Map<String, Integer> projection;
	
	private Integer pageOffset;
	
	private Integer pageSize;
	
	private boolean toCount;
	
	public Document getFilter() {
		final Document filterDoc = new Document();
		if (filter != null) {
			for (Map.Entry<String, String> filterParam : filter.entrySet()) {
				filterDoc.append(filterParam.getKey(), filterParam.getValue());
			}
		}
		return filterDoc;
	}

	public Document getSort() {
		final Document sortDoc = new Document();
		if (sort != null) {
			for (Map.Entry<String, Integer> sortParam : sort.entrySet()) {
				sortDoc.append(sortParam.getKey(), sortParam.getValue());
			}
		}
		return sortDoc;
	}

	public Document getProjection() {
		final Document projectionDoc = new Document();
		if (projection != null) {
			for (Map.Entry<String, Integer> projectionParam : projection.entrySet()) {
				projectionDoc.append(projectionParam.getKey(), projectionParam.getValue());	
			}
		}
		return projectionDoc;
	}

	public Integer getPageOffset() {
		return pageOffset;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public boolean getToCount() {
		return toCount;
	}
	
	public void setFilter(Map<String, String> filter) {
		this.filter = filter;
	}

	public void setSort(Map<String, Integer> sort) {
		this.sort = sort;
	}

	public void setProjection(Map<String, Integer> projection) {
		this.projection = projection;
	}

	public void setPageOffset(Integer pageOffset) {
		this.pageOffset = pageOffset;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	public void setToCount(boolean toCount) {
		this.toCount = toCount;
	}

}
