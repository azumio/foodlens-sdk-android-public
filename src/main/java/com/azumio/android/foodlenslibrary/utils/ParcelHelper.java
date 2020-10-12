package com.azumio.android.foodlenslibrary.utils;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ParcelHelper
{
	public static void writeNullable(@NonNull Parcel destination, Date nullable)
	{
		if (nullable != null)
		{
			destination.writeByte((byte) 1);
			destination.writeLong(nullable.getTime());
		}
		else
		{
			destination.writeByte((byte) 0);
		}
	}

	public static Date readNullableDate(@NonNull Parcel source)
	{
		return source.readByte() > 0 ? new Date(source.readLong()) : null;
	}

	public static void writeNullable(@NonNull Parcel destination, String nullableString)
	{
		if (nullableString != null)
		{
			destination.writeByte((byte) 1);
			destination.writeString(nullableString);
		}
		else
		{
			destination.writeByte((byte) 0);
		}
	}

	public static String readNullableString(@NonNull Parcel source)
	{
		return source.readByte() > 0 ? source.readString() : null;
	}

	public static void writeNullable(@NonNull Parcel destination, byte[] nullable)
	{
		if (nullable != null)
		{
			destination.writeInt(nullable.length);
			destination.writeByteArray(nullable);
		}
		else
		{
			destination.writeInt(-1);
		}
	}

	public static byte[] readNullableByteArray(@NonNull Parcel source)
	{
		int size = source.readInt();
		if (size < 0)
		{
			return null;
		}
		else
		{
			byte[] result = new byte[size];
			if (size > 0)
			{
				source.readByteArray(result);
			}
			return result;
		}
	}

	public static void writeNullable(@NonNull Parcel destination, Long nullable)
	{
		if (nullable != null)
		{
			destination.writeByte((byte) 1);
			destination.writeLong(nullable);
		}
		else
		{
			destination.writeByte((byte) 0);
		}
	}

	public static Long readNullableLong(@NonNull Parcel source)
	{
		return source.readByte() > 0 ? source.readLong() : null;
	}

	public static void writeNullable(@NonNull Parcel destination, Integer nullable)
	{
		if (nullable != null)
		{
			destination.writeByte((byte) 1);
			destination.writeInt(nullable);
		}
		else
		{
			destination.writeByte((byte) 0);
		}
	}

	public static Integer readNullableInteger(@NonNull Parcel source)
	{
		return source.readByte() > 0 ? source.readInt() : null;
	}

	public static <T> T readNullable(@NonNull Parcel source, ClassLoader classLoader)
	{
		return source.readParcelable(classLoader);
	}

	public static void writeNullable(@NonNull Parcel destination, Float nullable)
	{
		if (nullable != null)
		{
			destination.writeByte((byte) 1);
			destination.writeFloat(nullable);
		}
		else
		{
			destination.writeByte((byte) 0);
		}
	}

	public static Float readNullableFloat(@NonNull Parcel source)
	{
		return source.readByte() > 0 ? source.readFloat() : null;
	}

	public static void writeNullable(@NonNull Parcel destination, Double nullable)
	{
		if (nullable != null)
		{
			destination.writeByte((byte) 1);
			destination.writeDouble(nullable);
		}
		else
		{
			destination.writeByte((byte) 0);
		}
	}

	public static Double readNullableDouble(@NonNull Parcel source)
	{
		return source.readByte() > 0 ? source.readDouble() : null;
	}

	public static void writeNullable(@NonNull Parcel destination, Boolean nullable)
	{
		if (nullable != null)
		{
			destination.writeByte((byte) 1);
			destination.writeByte(nullable.booleanValue() ? (byte) 1 : (byte) 0);
		}
		else
		{
			destination.writeByte((byte) 0);
		}
	}

	public static Boolean readNullableBoolean(@NonNull Parcel source)
	{
		return source.readByte() > 0 ? (source.readByte() > 0) : null;
	}

	public static void writeNullable(@NonNull Parcel destination, Parcelable nullableParcelable, int flags)
	{
		if (nullableParcelable != null)
		{
			destination.writeByte((byte) 1);
			destination.writeParcelable(nullableParcelable, flags);
		}
		else
		{
			destination.writeByte((byte) 0);
		}
	}

	public static <T extends Parcelable> T readNullableParcelable(@NonNull Parcel source, ClassLoader classLoader)
	{
		return source.readByte() > 0 ? (T) source.readParcelable(classLoader) : null;
	}

	public static void write(@NonNull Parcel destination, boolean bool)
	{
		destination.writeByte(bool ? (byte) 1 : (byte) 0);
	}

	public static boolean readBoolean(@NonNull Parcel source)
	{
		return source.readByte() > 0;
	}

	public static <K extends Serializable, V extends Parcelable> void writeParcelableMap(@NonNull Parcel destination, Map<K, V> map, int flags)
	{
		if (map != null)
		{
			destination.writeInt(map.size());

			Set<Map.Entry<K, V>> entries = map.entrySet();

			for (Map.Entry<K, V> entry : entries)
			{
				destination.writeSerializable(entry.getKey());
				destination.writeParcelable(entry.getValue(), flags);
			}
		}
		else
		{
			destination.writeInt(-1);
		}
	}

	public static <K extends Serializable, V extends Parcelable> Map<K, V> readParcelableMap(@NonNull Parcel source, ClassLoader classLoader)
	{
		Map<K, V> rv = null;
		int size = source.readInt();
		if (size >= 0)
		{
			rv = new HashMap<>(size);
			for (int i = 0; i < size; i++)
			{
				K key = (K) source.readSerializable();
				V value = source.readParcelable(classLoader);
				rv.put(key, value);
			}
		}
		return rv;
	}

	public static <K extends Serializable, V extends Parcelable> void readParcelableMap(@NonNull Parcel source, @NonNull Map<K, V> out, ClassLoader classLoader)
	{
		int size = source.readInt();
		if (size > 0)
		{
			for (int i = 0; i < size; i++)
			{
				K key = (K) source.readSerializable();
				V value = source.readParcelable(classLoader);
				out.put(key, value);
			}
		}
	}

	public static void writeParcelableList(@NonNull Parcel destination, List<? extends Parcelable> list, int flags)
	{
		if (list != null)
		{
			destination.writeInt(list.size());
			for (Parcelable parcelable : list)
			{
				destination.writeParcelable(parcelable, flags);
			}
		}
		else
		{
			destination.writeInt(-1);
		}
	}

	@Nullable
	public static <T extends Parcelable> List<T> readParcelableList(@NonNull Parcel source, ClassLoader classLoader)
	{
		List<T> list = null;
		int size = source.readInt();
		if (size >= 0)
		{
			list = new ArrayList<T>(size);
			for (int i = 0; i < size; i++)
			{
				T object = source.readParcelable(classLoader);
				list.add(object);
			}
		}
		return list;
	}

	public static <T extends Parcelable> void readParcelableList(@NonNull Parcel source, @NonNull List<T> out, ClassLoader classLoader)
	{
		List<T> list = null;
		int size = source.readInt();
		if (size >= 0)
		{
			if (out instanceof ArrayList)
			{
				((ArrayList) out).ensureCapacity(size);
			}
			for (int i = 0; i < size; i++)
			{
				T object = source.readParcelable(classLoader);
				list.add(object);
			}
		}
	}

	public static void writeStringList(@NonNull Parcel destination, List<String> list)
	{
		if (list != null)
		{
			destination.writeInt(list.size());
			for (String parcelable : list)
			{
				destination.writeString(parcelable);
			}
		}
		else
		{
			destination.writeInt(-1);
		}
	}

	public static List<String> readStringList(@NonNull Parcel source)
	{
		List<String> list = null;
		int size = source.readInt();
		if (size >= 0)
		{
			list = new ArrayList<String>(size);
			for (int i = 0; i < size; i++)
			{
				list.add(source.readString());
			}
		}
		return list;
	}

	public static void writeStringSet(@NonNull Parcel destination, Set<String> set)
	{
		if (set != null)
		{
			destination.writeInt(set.size());
			for (String parcelable : set)
			{
				destination.writeString(parcelable);
			}
		}
		else
		{
			destination.writeInt(-1);
		}
	}

	public static Set<String> readStringSet(@NonNull Parcel source)
	{
		Set<String> set = null;
		int size = source.readInt();
		if (size >= 0)
		{
			set = new HashSet<String>(size);
			for (int i = 0; i < size; i++)
			{
				set.add(source.readString());
			}
		}
		return set;
	}

	public static <T extends Serializable> void writeNullable(@NonNull Parcel destination, T serializable)
	{
		if (serializable != null)
		{
			destination.writeByte((byte) 1);
			destination.writeSerializable(serializable);
		}
		else
		{
			destination.writeByte((byte) 0);
		}
	}

	public static <T extends Serializable> T readNullableSerializable(@NonNull Parcel source)
	{
		T rv = null;
		if (source.readByte() > 0)
		{
			rv = (T) source.readSerializable();
		}
		return rv;
	}

	public static <K extends Serializable, V extends Serializable> void writeSerializableMap(@NonNull Parcel destination, Map<K, V> map)
	{
		if (map != null)
		{
			destination.writeInt(map.size());

			Set<Map.Entry<K, V>> entries = map.entrySet();

			for (Map.Entry<K, V> entry : entries)
			{
				destination.writeSerializable(entry.getKey());
				destination.writeSerializable(entry.getValue());
			}
		}
		else
		{
			destination.writeInt(-1);
		}
	}

	public static <K extends Serializable, V extends Serializable> Map<K, V> readSerializableMap(@NonNull Parcel source)
	{
		Map<K, V> rv = null;
		int size = source.readInt();
		if (size >= 0)
		{
			rv = new HashMap<>(size);
			for (int i = 0; i < size; i++)
			{
				K key = (K) source.readSerializable();
				V value = (V) source.readSerializable();
				rv.put(key, value);
			}
		}
		return rv;
	}

	public static <K extends Serializable, V extends Serializable> void readSerializableMap(@NonNull Parcel source, @NonNull Map<K, V> out)
	{
		int size = source.readInt();
		if (size > 0)
		{
			for (int i = 0; i < size; i++)
			{
				K key = (K) source.readSerializable();
				V value = (V) source.readSerializable();
				out.put(key, value);
			}
		}
	}

	public static void writeStringMap(@NonNull Parcel destination, Map<String, String> map)
	{
		if (map != null)
		{
			destination.writeInt(map.size());

			Set<Map.Entry<String, String>> entries = map.entrySet();

			for (Map.Entry<String, String> entry : entries)
			{
				destination.writeString(entry.getKey());
				destination.writeString(entry.getValue());
			}
		}
		else
		{
			destination.writeInt(-1);
		}
	}

	public static Map<String, String> readStringMap(@NonNull Parcel source)
	{
		Map<String, String> rv = null;
		int size = source.readInt();
		if (size >= 0)
		{
			rv = new HashMap<>(size);
			for (int i = 0; i < size; i++)
			{
				String key = source.readString();
				String value = source.readString();
				rv.put(key, value);
			}
		}
		return rv;
	}

	public static void readStringMap(@NonNull Parcel source, @NonNull Map<String, String> out)
	{
		int size = source.readInt();
		if (size > 0)
		{
			for (int i = 0; i < size; i++)
			{
				String key = source.readString();
				String value = source.readString();
				out.put(key, value);
			}
		}
	}

	public static void writeDoubleMap(@NonNull Parcel destination, Map<String, Double> map)
	{
		if (map != null)
		{
			destination.writeInt(map.size());

			Set<Map.Entry<String, Double>> entries = map.entrySet();

			for (Map.Entry<String, Double> entry : entries)
			{
				destination.writeString(entry.getKey());
				destination.writeDouble(entry.getValue());
			}
		}
		else
		{
			destination.writeInt(-1);
		}
	}

	public static Map<String, Double> readDoubleMap(@NonNull Parcel source)
	{
		Map<String, Double> rv = null;
		int size = source.readInt();
		if (size >= 0)
		{
			rv = new HashMap<>(size);
			for (int i = 0; i < size; i++)
			{
				String key = source.readString();
				Double value = source.readDouble();
				rv.put(key, value);
			}
		}
		return rv;
	}

	public static void readDoubleMap(@NonNull Parcel source, @NonNull Map<String, Double> out)
	{
		int size = source.readInt();
		if (size > 0)
		{
			for (int i = 0; i < size; i++)
			{
				String key = source.readString();
				double value = source.readDouble();
				out.put(key, value);
			}
		}
	}

	public static BigDecimal readBigDecimal(Parcel in)
	{
		return new BigDecimal(in.readString());
	}

	public static void writeBigDecimal(BigDecimal amount, Parcel dest)
	{
		dest.writeString(amount.toString());
	}
}
