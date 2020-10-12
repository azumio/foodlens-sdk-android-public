package com.azumio.android.foodlenslibrary.utils;

import android.util.Pair;

public class Triplet<F, S, T> extends Pair<F, S>
{
	public final T third;

	public Triplet(F first, S second, T third)
	{
		super(first, second);
		this.third = third;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof Triplet))
		{
			return false;
		}
		if (!super.equals(o))
		{
			return false;
		}

		Triplet triplet = (Triplet) o;

		if (first != null ? !first.equals(triplet.first) : triplet.first != null)
		{
			return false;
		}
		if (second != null ? !second.equals(triplet.second) : triplet.second != null)
		{
			return false;
		}
		if (third != null ? !third.equals(triplet.third) : triplet.third != null)
		{
			return false;
		}

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = super.hashCode();
		result = 31 * result + (first != null ? first.hashCode() : 0);
		result = 31 * result + (second != null ? second.hashCode() : 0);
		result = 31 * result + (third != null ? third.hashCode() : 0);
		return result;
	}
}
