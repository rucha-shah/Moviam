package com.project.moviam.data;

import com.google.gson.annotations.SerializedName;

public class PopularMoviesData{

	@SerializedName("name")
	private String name;

	@SerializedName("url")
	private Url url;

	@SerializedName("timestamp")
	private String timestamp;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setUrl(Url url){
		this.url = url;
	}

	public Url getUrl(){
		return url;
	}

	public void setTimestamp(String timestamp){
		this.timestamp = timestamp;
	}

	public String getTimestamp(){
		return timestamp;
	}

	@Override
 	public String toString(){
		return 
			"PopularMoviesData{" + 
			"name = '" + name + '\'' + 
			",url = '" + url + '\'' + 
			",timestamp = '" + timestamp + '\'' + 
			"}";
		}
}