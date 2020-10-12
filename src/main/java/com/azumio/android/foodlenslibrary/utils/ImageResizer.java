package com.azumio.android.foodlenslibrary.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ExifInterface;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;

public class ImageResizer
{
	private static final String LOG_TAG = "ImageResizer";
	protected Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	protected Matrix mTransformation = new Matrix();

	public static enum ScaleType
	{
		RESIZE_AND_CROP,
		RESIZE_AND_FIT;
	}

	/**
	 *
	 * @param bitmap Bitmap to be resized. Take a note that this bitmap is NOT recycled.
	 * @param width Returned bitmap desired width (must be greater than zero)
	 * @param height Returned bitmap desired height (must be greater than zero)
	 * @param scaleType scale type to be used.
	 * @return new bitmap or null in case of error (scaleType == null, bitmap == null, width < 1 or height < 1)
	 */
	public Bitmap newResizedBitmap(Bitmap bitmap, int width, int height, ScaleType scaleType)
	{
		Bitmap output = null;
		if (scaleType != null && bitmap != null && !bitmap.isRecycled() && width > 0 && height > 0)
		{
			output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
			Canvas canvas = new Canvas(output);

			mTransformation.reset();

			switch ( scaleType )
			{
				case RESIZE_AND_CROP:
					setupTransformationResizeAndCrop(bitmap.getWidth(), bitmap.getHeight(), width, height);
					break;
				case RESIZE_AND_FIT:
					setupTransformationResizeAndFit(bitmap.getWidth(), bitmap.getHeight(), width, height);
					break;
			}
			canvas.drawBitmap(bitmap, mTransformation, mPaint);
		}

		return output;
	}

	private void setupTransformationResizeAndCrop(int originalWidth, int originalHeight, int width, int height)
	{
		float xScale = (float) width / (float) originalWidth;
		float yScale = (float) height / (float) originalHeight;
		float scale = Math.max(xScale, yScale);
		float xTranslation = (width - originalWidth * scale) / 2.f;
		float yTranslation = (height - originalHeight * scale) / 2.f;
		mTransformation.preScale(scale, scale);
		mTransformation.postTranslate(xTranslation, yTranslation);
	}

	private void setupTransformationResizeAndFit(int originalWidth, int originalHeight, int width, int height)
	{
		float xScale = (float) width / (float) originalWidth;
		float yScale = (float) height / (float) originalHeight;
		float scale = Math.min(xScale, yScale);
		float xTranslation = (width - originalWidth * scale) / 2.f;
		float yTranslation = (height - originalHeight * scale) / 2.f;
		mTransformation.preScale(scale, scale);
		mTransformation.postTranslate(xTranslation, yTranslation);
	}

	private static Matrix newRotateMatrixIfNeeded(int exifOrientation)
	{
		Matrix matrix = null;
		switch ( exifOrientation )
		{
			case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
				matrix = new Matrix();
				matrix.setScale(-1, 1);
				break;
			case ExifInterface.ORIENTATION_ROTATE_180:
				matrix = new Matrix();
				matrix.setRotate(180);
				break;
			case ExifInterface.ORIENTATION_FLIP_VERTICAL:
				matrix = new Matrix();
				matrix.setRotate(180);
				matrix.postScale(-1, 1);
				break;
			case ExifInterface.ORIENTATION_TRANSPOSE:
				matrix = new Matrix();
				matrix.setRotate(90);
				matrix.postScale(-1, 1);
				break;
			case ExifInterface.ORIENTATION_ROTATE_90:
				matrix = new Matrix();
				matrix.setRotate(90);
				break;
			case ExifInterface.ORIENTATION_TRANSVERSE:
				matrix = new Matrix();
				matrix.setRotate(-90);
				matrix.postScale(-1, 1);
				break;
			case ExifInterface.ORIENTATION_ROTATE_270:
				matrix = new Matrix();
				matrix.setRotate(-90);
				break;

		}
		return matrix;
	}

	private static boolean isLandscapeOrientation(int exifOrientation)
	{
		switch (exifOrientation)
		{
			case ExifInterface.ORIENTATION_TRANSPOSE:
			case ExifInterface.ORIENTATION_ROTATE_90:
			case ExifInterface.ORIENTATION_TRANSVERSE:
			case ExifInterface.ORIENTATION_ROTATE_270:
				return true;
			default:
				return false;
		}
	}

	public boolean resizeBitmapFile(File inFile, File outFile, int maxWidth, int maxHeight, ScaleType scaleType)
	{
		return resizeBitmapFile(inFile, outFile, maxWidth, maxHeight, scaleType, false, false);
	}

	public boolean resizeBitmapFile(File inFile, File outFile, int maxWidth, int maxHeight, ScaleType scaleType, boolean forceSquare)
	{
		return resizeBitmapFile(inFile, outFile, maxWidth, maxHeight, scaleType, forceSquare, false);
	}

	public boolean resizeBitmapFile(File inFile, File outFile, int maxWidth, int maxHeight, ScaleType scaleType, boolean forceSquare, boolean maxSizeIsJustAHint)
	{
		boolean rv = false;
		Bitmap newBitmap = null;
		Bitmap bitmap = null;
		try
		{
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inPreferredConfig = Bitmap.Config.ARGB_8888;
			options.inJustDecodeBounds = true;

			Matrix matrix = null;
			boolean isLandscape = false;
			try
			{
				// see http://sylvana.net/jpegcrop/exif_orientation.html
				ExifInterface exif = new ExifInterface(inFile.getPath());
				int exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
				matrix = newRotateMatrixIfNeeded(exifOrientation);
				isLandscape = isLandscapeOrientation(exifOrientation);
			}
			catch (Throwable t)
			{
				Log.w(LOG_TAG, "Error while getting exif interface for bitmap!", t);
			}

			BitmapFactory.decodeFile(inFile.getPath(), options);
			int width = options.outWidth;
			int height = options.outHeight;
			int effectiveMaxWidth = (isLandscape ? maxHeight : maxWidth);
			int effectiveMaxHeight = (isLandscape ? maxWidth : maxHeight);
			int wDecodeScale = (int) Math.max(1, (float) width / effectiveMaxWidth);
			int hDecodeScale = (int) Math.max(1, (float) height / effectiveMaxHeight);

			options.inSampleSize = Math.min(wDecodeScale, hDecodeScale);
			options.inJustDecodeBounds = false;
			options.inMutable = false;
			options.inPreferQualityOverSpeed = true;
			options.inDither = true;
			bitmap = BitmapFactory.decodeFile(inFile.getPath(), options);

			if (bitmap != null && matrix != null)
			{
				Bitmap result = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
				bitmap.recycle();
				bitmap = result;
			}
			if (bitmap != null)
			{
				boolean resizeNeeded = false;
				if (forceSquare)
				{
					if (width > maxWidth || height > maxHeight)
					{
						width = Math.min(bitmap.getWidth(), maxWidth);
						height = Math.min(bitmap.getHeight(), maxHeight);
						int size = scaleType == ScaleType.RESIZE_AND_CROP ? Math.min(width, height) : Math.max(width, height);
						width = size;
						height = size;
						resizeNeeded = true;
					}
					else if (width != height)
					{
						int size = scaleType == ScaleType.RESIZE_AND_CROP ? Math.min(width, height) : Math.max(width, height);
						width = size;
						height = size;
						resizeNeeded = true;
					}
				}
				else
				{
					width = bitmap.getWidth();
					height = bitmap.getHeight();
					float bitmapAspectRatio = (float) bitmap.getWidth() / (float) bitmap.getHeight();
					if (maxSizeIsJustAHint)
					{
						if (width < height)
						{
							if (width > maxWidth)
							{
								width = maxWidth;
								height = (int) (width / bitmapAspectRatio);
								resizeNeeded = true;
							}
						}
						else
						{
							if (height > maxHeight)
							{
								height = maxHeight;
								width = (int) (height * bitmapAspectRatio);
								resizeNeeded = true;
							}
						}
					}
					else
					{
						if (width > maxWidth)
						{
							width = maxWidth;
							height = (int) (width / bitmapAspectRatio);
							resizeNeeded = true;
						}
						if (height > maxHeight)
						{
							height = maxHeight;
							width = (int) (height * bitmapAspectRatio);
							resizeNeeded = true;
						}
					}
				}

				if (resizeNeeded)
				{
					try
					{
						newBitmap = newResizedBitmap(bitmap, width, height, scaleType);
					}
					finally
					{
						bitmap.recycle();
						bitmap = null;
					}
				}
				else
				{
					newBitmap = bitmap;
					bitmap = null;
				}

				if (newBitmap != null)
				{

					FileOutputStream fileOutputStream = null;
					try
					{
						fileOutputStream = new FileOutputStream(outFile, false);
						rv = newBitmap.compress(Bitmap.CompressFormat.JPEG, 93, fileOutputStream);
					}
					finally
					{
						FileUtils.quietCloseStream(fileOutputStream);
						newBitmap.recycle();
					}
				}
				else
				{
					Log.i(LOG_TAG, "Resized bitmap is null!");
				}
			}
			else
			{
				Log.i(LOG_TAG, "Decoded bitmap is null!");
			}
		}
		catch (Throwable t)
		{
			Log.e(LOG_TAG, "Error while resizing bitmap!", t);
		}
		finally
		{
			try
			{
				if (bitmap != null && !bitmap.isRecycled())
				{
					bitmap.recycle();
				}
				if (newBitmap != null && !newBitmap.isRecycled())
				{
					newBitmap.recycle();
				}
			}
			catch (Throwable t)
			{
				t.printStackTrace();
			}
		}
		return rv;
	}
}
