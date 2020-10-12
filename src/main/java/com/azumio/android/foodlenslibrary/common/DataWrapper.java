package com.azumio.android.foodlenslibrary.common;

import com.azumio.android.foodlenslibrary.model.FoodSearchData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class DataWrapper implements Serializable
{
	private List<String> list = new ArrayList<>();

	public DataWrapper()
	{
	}

	public DataWrapper(List<String> data)
	{
		this.list = data;
	}

	public List<String> getList()
	{
		return this.list;
	}

	public void setList(List<String> data) {this.list = data;}
}
