package com.zhangyx.TwoDimensionalCode;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.zhangyx.TwoDimensionalCode.util.ZXing.Scanning.CaptureActivity;
import com.zhangyx.TwoDimensionalCode.util.ZXing.generate.GenerateActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 二维码操作：生成二维码、扫描二维码
 *        
 *com.zhangyx.TwoDimensionalCode.MainActivity
 * @author Admin-zhangyx
 *
 * create at 2014-12-22 下午3:57:09
 */
public class MainActivity extends Activity implements OnClickListener{

	@ViewInject(R.id.scann)
	private Button scann;
	@ViewInject(R.id.generate)
	private Button generate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);
        scann.setOnClickListener(this);
        generate.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v==scann){
			startActivity(new Intent(this,CaptureActivity.class));
		}else if(v==generate){
			startActivity(new Intent(this,GenerateActivity.class));
		}
	}
    
}
