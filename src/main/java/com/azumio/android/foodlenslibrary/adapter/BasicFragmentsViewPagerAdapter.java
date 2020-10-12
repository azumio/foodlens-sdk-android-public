package com.azumio.android.foodlenslibrary.adapter;


import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;



import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicFragmentsViewPagerAdapter extends PagerAdapter
{
	private static final String LOG_TAG = "BasicFragmentsViewPager";
	private static final boolean DEBUG = false;

	public static String makeFragmentTag(String adapterName, long itemId)
	{
		return "android:switcher:named:" + adapterName + ":" + itemId;
	}

	private List<FragmentDefinition> mFragmentsDefinitions;

	private Map<Integer, Fragment> mFragmentsCreationCache;

	private final FragmentManager mFragmentManager;
	private final String mAdapterName;
	private FragmentTransaction mCurTransaction = null;
	private Fragment mCurrentPrimaryItem = null;

	private BasicFragmentsViewPagerAdapter(@NonNull String adapterName, @NonNull FragmentManager fm)
	{
		//private final Map<Integer, Fragment> fragments = new HashMap(getCount());
		mFragmentsCreationCache = new HashMap<>();
		mAdapterName = adapterName;
		mFragmentManager = fm;
	}

	public BasicFragmentsViewPagerAdapter(@NonNull String adapterName, @NonNull FragmentManager fm, List<FragmentDefinition> fragmentsDefinitions)
	{
		this(adapterName, fm);
		if (fragmentsDefinitions == null || fragmentsDefinitions.size() == 0)
		{
			mFragmentsDefinitions = Collections.emptyList();
		}
		else
		{
			mFragmentsDefinitions = fragmentsDefinitions;
		}
	}

	public BasicFragmentsViewPagerAdapter(@NonNull String adapterName, @NonNull FragmentManager fm, FragmentDefinition... fragmentsDefinitions)
	{
		this(adapterName, fm);
		if (fragmentsDefinitions == null || fragmentsDefinitions.length == 0)
		{
			mFragmentsDefinitions = Collections.emptyList();
		}
		else
		{
			mFragmentsDefinitions = Arrays.asList(fragmentsDefinitions);
		}
	}

	protected Fragment createItem(int position)
	{
		Fragment fragment = null;
		if (position < mFragmentsDefinitions.size())
		{
			FragmentDefinition fragmentDefinition = mFragmentsDefinitions.get(position);
			try
			{
				fragment = fragmentDefinition.getFragmentClass().newInstance();
				fragment.setArguments(fragmentDefinition.getFragmentArguments());
			}
			catch (Throwable t)
			{
				Log.e(LOG_TAG, "Could not instantiate the fragment from definition " + fragmentDefinition + "!", t);
			}
			return fragment;
		}
		else
		{
			return new Fragment();
		}
	}

	public Fragment getItem(int position)
	{
		final String tag = makeFragmentTag(mAdapterName, getItemId(position));
		Fragment fragment = mFragmentManager.findFragmentByTag(tag);
		if (fragment == null)
		{
			fragment = createItem(position);
			if (fragment != null)
			{
				mFragmentsCreationCache.put(position, fragment);
			}
			else
			{
				fragment = new Fragment();
			}
		}
		return fragment;
	}

	@Override
	public int getCount()
	{
		return mFragmentsDefinitions.size();
	}

	@Override
	public CharSequence getPageTitle(int position)
	{
		return mFragmentsDefinitions.get(position).getTitle();
	}

	@Override
	public void startUpdate(ViewGroup container)
	{
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position)
	{
		if (mCurTransaction == null)
		{
			mCurTransaction = mFragmentManager.beginTransaction();
		}

		final long itemId = getItemId(position);

		// Do we already have this fragment?
		String name = makeFragmentTag(mAdapterName, itemId);
		Fragment fragment = mFragmentManager.findFragmentByTag(name);
		if (fragment != null)
		{
			if (DEBUG)
			{
				Log.v(LOG_TAG, "Attaching item #" + itemId + ": f=" + fragment);
			}
			mCurTransaction.attach(fragment);
		}
		else
		{
			fragment = mFragmentsCreationCache.get(position);
			if (fragment == null)
			{
				fragment = createItem(position);
			}
			else
			{
				mFragmentsCreationCache.remove(position);
			}
			if (DEBUG)
			{
				Log.v(LOG_TAG, "Adding item #" + itemId + ": f=" + fragment);
			}
			mCurTransaction.add(container.getId(), fragment, name);
		}
		if (fragment != mCurrentPrimaryItem && fragment != null)
		{
			fragment.setMenuVisibility(false);
			fragment.setUserVisibleHint(false);
		}

		return fragment;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object)
	{
		if (mCurTransaction == null)
		{
			mCurTransaction = mFragmentManager.beginTransaction();
		}
		if (DEBUG)
		{
			Log.v(LOG_TAG, "Detaching item #" + getItemId(position) + ": f=" + object + " v=" + ((Fragment) object).getView());
		}
		mCurTransaction.detach((Fragment) object);
	}

	@Override
	public void setPrimaryItem(ViewGroup container, int position, Object object)
	{
		Fragment fragment = (Fragment) object;
		if (fragment != mCurrentPrimaryItem)
		{
			if (mCurrentPrimaryItem != null)
			{
				mCurrentPrimaryItem.setMenuVisibility(false);
				mCurrentPrimaryItem.setUserVisibleHint(false);
			}
			if (fragment != null)
			{
				fragment.setMenuVisibility(true);
				fragment.setUserVisibleHint(true);
			}
			mCurrentPrimaryItem = fragment;
		}
	}

	@Override
	public void finishUpdate(ViewGroup container)
	{
		if (mCurTransaction != null)
		{
			mCurTransaction.commitAllowingStateLoss();
			mCurTransaction = null;
			mFragmentManager.executePendingTransactions();
		}
	}

	@Override
	public boolean isViewFromObject(View view, Object object)
	{
		return ((Fragment) object).getView() == view;
	}

	@Override
	public Parcelable saveState()
	{
		return null;
	}

	@Override
	public void restoreState(Parcelable state, ClassLoader loader)
	{
	}

	/**
	 * Return a unique identifier for the item at the given position.
	 *
	 * <p>The default implementation returns the given position.
	 * Subclasses should override this method if the positions of items can change.</p>
	 *
	 * @param position Position within this adapter
	 * @return Unique identifier for the item at position
	 */
	public long getItemId(int position)
	{
		return position;
	}

	public static class FragmentDefinition
	{
		private CharSequence mTitle = "";
		private int mTitleIcon = 0;
		private final Class<? extends Fragment> mFragmentClass;
		private final Bundle mFragmentArguments;
		private Context mContext;
		private final float mPageWidth;

		public FragmentDefinition(CharSequence title, Class<? extends Fragment> fragmentClass, Bundle fragmentArguments, float pageWidth)
		{
			mTitle = title;
			mFragmentClass = fragmentClass;
			mFragmentArguments = fragmentArguments;
			mPageWidth = pageWidth;
		}

		public FragmentDefinition(CharSequence title, Class<? extends Fragment> fragmentClass, Bundle fragmentArguments, float pageWidth, Context context)
		{
			mTitle = title;
			mFragmentClass = fragmentClass;
			mFragmentArguments = fragmentArguments;
			mPageWidth = pageWidth;
			mContext = context;
		}


		public FragmentDefinition(int title, Class<? extends Fragment> fragmentClass, Bundle fragmentArguments, float pageWidth)
		{
			mTitleIcon = title;
			mFragmentClass = fragmentClass;
			mFragmentArguments = fragmentArguments;
			mPageWidth = pageWidth;
		}

		public FragmentDefinition(CharSequence title, Class<? extends Fragment> fragmentClass, Bundle fragmentArguments)
		{
			this(title, fragmentClass, fragmentArguments, 1.0f);
		}

		public FragmentDefinition(CharSequence title, Class<? extends Fragment> fragmentClass, Bundle fragmentArguments, Context context)
		{
			this(title, fragmentClass, fragmentArguments, 1.0f, context);
		}


		public FragmentDefinition(int title, Class<? extends Fragment> fragmentClass, Bundle fragmentArguments)
		{
			this(title, fragmentClass, fragmentArguments, 1.0f);
		}


		public CharSequence getTitle()
		{
			//			LayoutInflater inflater = LayoutInflater.from(mContext);
			//			View rootView = inflater.inflate(R.layout.custom_pager_title, null);
			//			CenteredCustomFontView title = (CenteredCustomFontView) rootView.findViewById(R.id.title);
			//			title.setText(ArgusIconMap.getInstance().get(ArgusIconMap.CANCEL));
			return mTitle;
		}

		public int getTitleIcon()
		{
			return mTitleIcon;
		}

		public Class<? extends Fragment> getFragmentClass()
		{
			return mFragmentClass;
		}

		public Bundle getFragmentArguments()
		{
			return mFragmentArguments;
		}

		@Override
		public String toString()
		{
			final StringBuilder sb = new StringBuilder(this.getClass().getSimpleName());
			sb.append("{");
			sb.append(" title = ").append(mTitle);
			sb.append(", fragmentClass = ").append(mFragmentClass);
			sb.append(", fragmentArguments = ").append(mFragmentArguments);
			sb.append("}");
			return sb.toString();
		}

		public float getPageWidth()
		{
			return mPageWidth;
		}
	}

	@Override
	public float getPageWidth(int position)
	{
		return mFragmentsDefinitions.get(position).getPageWidth();
	}

	@Override
	public int getItemPosition(Object object)
	{
		int itemIndex = getFragmentIndex(object);
		if (itemIndex >= -1 && itemIndex <= 1)
		{
			return POSITION_UNCHANGED;
		}

		return POSITION_NONE;
	}

	private int getFragmentIndex(Object object)
	{
		for (Integer index : mFragmentsCreationCache.keySet())
		{
			if (object == mFragmentsCreationCache.get(index))
			{
				return index;
			}
		}

		return -1;
	}

	public void onDestroy()
	{
		mFragmentsDefinitions.clear();
		mFragmentsCreationCache.clear();

		mCurTransaction = null;
		mCurrentPrimaryItem = null;
	}
}
