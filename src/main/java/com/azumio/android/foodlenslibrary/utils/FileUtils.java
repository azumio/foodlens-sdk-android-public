package com.azumio.android.foodlenslibrary.utils;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Size;


import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.channels.FileChannel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

public class FileUtils
{
	private static final String LOG_TAG = "FileUtils";
	public static final String JPG_FORMAT = ".jpg";

	public static void ensureDirectoriesExists(String path)
	{
		if (path != null)
		{
			try
			{
				ensureDirectoriesExists(new File(path));
			}
			catch (Throwable t)
			{
				Log.e(LOG_TAG, String.valueOf(t));
			}
		}
	}

	public static void ensureDirectoriesExists(@Nullable File file)
	{
		if (file != null)
		{
			try
			{
				File dir = file.getParentFile();
				boolean mkdirs = dir.mkdirs();
				if(!mkdirs) {
					System.out.println();
				} else {
					System.out.println();
				}
			}
			catch (Throwable t)
			{
				Log.e(LOG_TAG,String.valueOf(t));
				t.printStackTrace();
			}
		}
	}

	public  static Size sizeOfImageAtPath(Uri mPhotoUri)  {

		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		try {
			BitmapFactory.decodeStream(
					ApplicationContextProvider.getApplicationContext().getContentResolver().openInputStream(mPhotoUri),
					null,
					options);
			int imageHeight = options.outHeight;
			int imageWidth = options.outWidth;
			return new Size(imageWidth,imageHeight);
		}
		catch (Throwable t)
		{
			Log.e(LOG_TAG,String.valueOf(t));
			t.printStackTrace();
		}
		return new Size(0,0);

	}

	public static String createExternalStorageFilePath(@NonNull String path)
	{
		File externalStorage = Environment.getExternalStoragePublicDirectory(Environment.getDataDirectory().getAbsolutePath());
		ensureDirectoriesExists(externalStorage);
		return externalStorage.getAbsolutePath() + path;
	}

	public static void quietCloseStream(InputStream inputStream)
	{
		StreamUtils.quietCloseStream(inputStream);
	}

	public static void quietCloseStream(OutputStream outputStream)
	{
		if (outputStream != null)
		{
			try
			{
				outputStream.close();
			}
			catch (Throwable t)
			{
				Log.e(LOG_TAG, String.valueOf(t));
			}
		}
	}

	public static void quietCloseWriter(Writer writer)
	{
		if (writer != null)
		{
			try
			{
				writer.close();
			}
			catch (Throwable t)
			{
				Log.e(LOG_TAG, String.valueOf(t));
			}
		}
	}

	public static void quietCloseReader(Reader reader)
	{
		if (reader != null)
		{
			try
			{
				reader.close();
			}
			catch (Throwable t)
			{
				Log.e(LOG_TAG, String.valueOf(t));
			}
		}
	}

	public static Uri getUriForFile(File file)
	{
		Context context = ApplicationContextProvider.getApplicationContext();
		return FileProvider.getUriForFile(context, getFileProviderAuthority(), file);
	}

    @NotNull
    public static String getFileProviderAuthority() {
        return ApplicationContextProvider.getApplicationContext().getPackageName() + ".provider";
    }


    public static String getImagePath(Context activity, Uri uri)
	{
		String path = null;
		Cursor cursor = null;
		try
		{
			String[] projection = {MediaStore.MediaColumns.DATA};
			cursor = activity.getContentResolver().query(uri, projection, null, null, null);
			int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
			cursor.moveToFirst();
			path = cursor.getString(column_index);
		}
		catch (Throwable t)
		{
			Log.e(LOG_TAG, "Could not get picture uri path!", t);
		}
		finally
		{
			if (cursor != null && !cursor.isClosed())
			{
				cursor.close();
			}
		}

		if (path == null)
		{
			return getFilePathFromContentProvider(activity, uri);
		}

		return path;
	}

	private static String getFilePathFromContentProvider(Context context, Uri uri)
	{
		InputStream input = null;
		FileOutputStream stream = null;

		try
		{
			input = context.getContentResolver().openInputStream(uri);
			String filename = String.format("%d.png",System.currentTimeMillis() );
			File sd = context.getCacheDir();
			File dest = new File(sd, filename);
			stream = new FileOutputStream(dest);
			byte data[] = new byte[1024];
			int count;

			while ((count = input.read(data)) != -1)
			{
				stream.write(data, 0, count);
			}

			return dest.getAbsolutePath();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			quietCloseStream(input);
			quietCloseStream(stream);
		}


		return null;
	}

	public static
	@Nullable
	Bitmap getLastTakenUserPhoto(Context context)
	{
		String[] projection = new String[]{MediaStore.Images.ImageColumns._ID, MediaStore.Images.ImageColumns.DATA, MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME,
				MediaStore.Images.ImageColumns.DATE_TAKEN, MediaStore.Images.ImageColumns.MIME_TYPE};
		final Cursor cursor = context.getContentResolver()
				.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC");

		cursor.moveToFirst();

		while (cursor.moveToNext())
		{
			String path = cursor.getString(1);
			//not a best idea, but seems to works on all tested devices
			if (path.toUpperCase().contains("DCIM") )
			{
				File imageFile = new File(path);
				if (imageFile.exists())
				{
					Bitmap bm = BitmapFactory.decodeFile(path);
					return bm;
				}
			}

			continue;
		}

		return null;
	}

	public static void quietCloseChannel(FileChannel src)
	{
		if(src != null){
			try
			{
				src.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	private static final String SOUNDSCAPE_AUDIOS = "soundscape_audios";

	public static String getSTAudioPath()
	{
		String dirPath = getCachePath("", SOUNDSCAPE_AUDIOS + File.separator);
		File projDir = new File(dirPath);
		if (!projDir.exists()) { projDir.mkdirs(); }
		return dirPath;
	}

	public static File getCacheDir(Context context)
	{
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
		{
			return Environment.getExternalStorageDirectory();
		}
		else
		{
			return context.getCacheDir();
		}
	}

	public static String getCachePath(String fileName, String path)
	{
		File root = getCacheDir(ApplicationContextProvider.getApplicationContext());
		return root.getAbsolutePath() + File.separator + path + File.separator + fileName;
	}
}
