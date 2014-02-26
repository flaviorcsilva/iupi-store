package br.org.iupi.store.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.PorterDuff.Mode;
import android.util.Log;

public class ImagemHelper {

	private static final String TAG = "ImagemHelper";

	public static Bitmap getImagemRedimensionada(String arquivoDeFoto,
			int largura, int altura) {
		try {
			Bitmap bitmap = BitmapFactory.decodeFile(arquivoDeFoto);

			float escalaX = (float) largura / bitmap.getWidth();
			float escalaY = (float) altura / bitmap.getHeight();

			Matrix matrix = new Matrix();
			matrix.setScale(escalaX, escalaY);

			Bitmap bitmapRedimensionado = Bitmap.createBitmap(bitmap, 0, 0,
					bitmap.getWidth(), bitmap.getHeight(), matrix, true);

			// Limpa a memória do arquivo original
			bitmap.recycle();

			bitmap = null;

			return bitmapRedimensionado;
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
		}

		return null;
	}

	public static Bitmap getImagemArredondada(Bitmap bitmap, int pixels) {
		Bitmap image = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(image);

		int color = 0xff424242;
		Paint paint = new Paint();
		Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		RectF rectF = new RectF(rect);
		float roundPx = pixels;

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 255, 255, 255);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return image;
	}
}
