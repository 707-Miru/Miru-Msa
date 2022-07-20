package com.back.miru.model.dto;

public class ListParameterDto {
	private String type;
	private String inputText;
	private int start;
	private int currentPerPage;
	private String lat;
	private String lng;
	private String id;

	public ListParameterDto() {
	}

	public ListParameterDto(String type, String inputText) {
		this.type = type;
		this.inputText = inputText;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getInputText() {
		return inputText;
	}

	public void setInputText(String inputText) {
		this.inputText = inputText;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getCurrentPerPage() {
		return currentPerPage;
	}

	public void setCurrentPerPage(int currentPerPage) {
		this.currentPerPage = currentPerPage;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ListParameterDto [type=" + type + ", inputText=" + inputText + ", lat=" + lat + ", lng=" + lng + "]";
	}

}
