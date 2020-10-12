/**
 *
 */
package com.azumio.android.foodlenslibrary.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.azumio.android.foodlenslibrary.activity.BaseFragmentActivity;


public class BaseFragment extends Fragment
{
	public Boolean getHideBackButton()
	{
		return hideBackButton;
	}

	public void setHideBackButton(Boolean hideBackButton)
	{
		this.hideBackButton = hideBackButton;
	}

	Boolean hideBackButton = false;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		this.setRetainInstance(true);
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	public BaseFragmentActivity getParent()
	{
		return (BaseFragmentActivity)getActivity();
	}

	@Override
	public void onResume()
	{
		super.onResume();
	}

	public boolean onBackPressed()
	{
		return true;
	}
}
