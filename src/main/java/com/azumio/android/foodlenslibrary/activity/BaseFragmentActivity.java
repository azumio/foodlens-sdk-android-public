package com.azumio.android.foodlenslibrary.activity;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.azumio.android.foodlenslibrary.R;
import com.azumio.android.foodlenslibrary.fragment.BaseFragment;

import java.util.List;

public class BaseFragmentActivity extends AppCompatActivity
{
	private BaseFragment currentFragment;
	private static final String TAG = BaseFragmentActivity.class.getSimpleName();

	public void push(Fragment newFragment, @NonNull String tag)
	{
		push(newFragment, false, tag);
	}

	public void push(Fragment newFragment, boolean addBackStack)
	{
		push(newFragment, addBackStack, null);
	}

	public void push(Fragment newFragment, boolean addBackStack, @Nullable String tag)
	{
		if(!isFinishing())
		{
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			final int container = R.id.activity_with_fragment_container;
			if(tag == null) {
				ft.replace(container, newFragment);
			} else {
				ft.replace(container, newFragment, tag);
			}
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			if (addBackStack)
			{
				ft.addToBackStack(TAG);
			}

			ft.commitAllowingStateLoss();

			if (newFragment instanceof BaseFragment)
			{
				setActiveFragment((BaseFragment) newFragment);
			}
		}
	}

	public void push(Fragment newFragment)
	{
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		getSupportFragmentManager().popBackStack(TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
		push(newFragment, false, null);
		ft.commitAllowingStateLoss();
	}

	public void pop()
	{
		if(getSupportFragmentManager().getBackStackEntryCount() < 1 )
		{
			finish();
		}
		else
		{
			getSupportFragmentManager().popBackStack();
		}
	}

	public BaseFragment getActiveFragment()
	{
		if (currentFragment != null && !currentFragment.isDetached() && !currentFragment.isRemoving())
		{
			return currentFragment;
		}
		return getVisibleFragment();
	}

	public synchronized void setActiveFragment(BaseFragment fragment)
	{
		currentFragment = fragment;
	}

	private BaseFragment getVisibleFragment()
	{
		FragmentManager fragmentManager = getSupportFragmentManager();
		List<Fragment> fragments = fragmentManager.getFragments();
		if (fragments != null)
		{
			for (Fragment fragment : fragments)
			{
				if (fragment instanceof BaseFragment && fragment.isVisible()) { return (BaseFragment) fragment; }
			}
		}
		return null;
	}

	@Override
	public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState)
	{
		outState.clear();
	}
}
