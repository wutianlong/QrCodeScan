/**
 * GenerateActivity.java [V 1..0.0]
 * classes : com.zhangyx.TwoDimensionalCode.util.ZXing.generate.GenerateActivity
 * zhangyx Create at 2014-12-22 下午4:38:21
 */
package com.zhangyx.TwoDimensionalCode.util.ZXing.generate;

import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.zhangyx.TwoDimensionalCode.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * 生成二维码 com.zhangyx.TwoDimensionalCode.util.ZXing.generate.GenerateActivity
 * 
 * @author Admin-zhangyx
 * 
 *         create at 2014-12-22 下午4:38:21
 */
public class GenerateActivity extends Activity {

	@ViewInject(R.id.et_only_company)
	private EditText et_only_company;
	@ViewInject(R.id.et_only_position)
	private EditText et_only_position;
	@ViewInject(R.id.et_only_phone)
	private EditText et_only_phone;
	@ViewInject(R.id.et_only_email)
	private EditText et_only_email;
	@ViewInject(R.id.et_only_web1)
	private EditText et_only_web1;
	@ViewInject(R.id.et_only_add)
	private EditText et_only_add;
	@ViewInject(R.id.et_only_note)
	private EditText et_only_note;
	@ViewInject(R.id.et_only_name)
	private EditText et_only_name;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_generate);
		ViewUtils.inject(this);

		findViewById(R.id.but).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String name = et_only_name.getText().toString().trim();
				String company = et_only_company.getText().toString().trim();
				String position = et_only_position.getText().toString().trim();
				String phone = et_only_phone.getText().toString().trim();
				String email = et_only_email.getText().toString().trim();
				String web1 = et_only_web1.getText().toString().trim();
				String add = et_only_add.getText().toString().trim();
				String note = et_only_note.getText().toString().trim();
				String contents = "BEGIN:VCARD\nVERSION:3.0\n" + "N:" + name
						+ "\nORG:" + company + "\nTITLE:" + position
						+ "\nNOTE:" + note + "\nTEL:" + phone + "\nADR:" + add
						+ "\nURL:" + web1 + "\nEMAIL:" + email + "\nEND:VCARD";
				try {
					Bitmap bm = qr_code(contents, BarcodeFormat.QR_CODE);

					ImageView img = (ImageView) findViewById(R.id.img_only);

					img.setImageBitmap(bm);
				} catch (WriterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
	}

	public Bitmap qr_code(String string, BarcodeFormat format)
			throws WriterException {
		MultiFormatWriter writer = new MultiFormatWriter();
		Hashtable<EncodeHintType, String> hst = new Hashtable<EncodeHintType, String>();
		hst.put(EncodeHintType.CHARACTER_SET, "UTF-8");

		BitMatrix matrix = writer.encode(string, format, 400, 400, hst);
		int width = matrix.getWidth();
		int height = matrix.getHeight();

		int[] pixels = new int[width * height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (matrix.get(x, y)) {
					// 一行一行设置每个像素数据中的颜色值
					pixels[y * width + x] = 0xff000000;
				}

			}
		}
		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		// 通过像素数组生成bitmap,具体参考api，用像素数组中的值来替换bitmap中每行像素
		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		return bitmap;
	}

}
