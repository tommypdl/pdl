package diary;

import com.qsa.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ActivityDiaryEdit extends Activity {
	private EditText mTitleText;
	private EditText mBodyText;
	private Long mRowId;
	private DiaryDbAdapter mDbHelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mDbHelper=new DiaryDbAdapter(this);
		mDbHelper.open();
		setContentView(R.layout.diary_edit);
		
		mTitleText=(EditText)findViewById(R.id.title);
		mBodyText=(EditText)findViewById(R.id.body);
		
		Button confirmButton=(Button)findViewById(R.id.confirm);
		
		mRowId=null;
		Bundle extras=getIntent().getExtras();
		if(extras!=null){
			String title=extras.getString(DiaryDbAdapter.KEY_TITLE);
			String body=extras.getString(DiaryDbAdapter.KEY_BODY);
			mRowId=extras.getLong(DiaryDbAdapter.KEY_ROWID);
			
			if(title!=null)
				mTitleText.setText(title);
			if(body!=null)
				mBodyText.setText(body);
		}
		confirmButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String title=mTitleText.getText().toString();
				String body=mBodyText.getText().toString();
				if(mRowId!=null)
					mDbHelper.updateDiary(mRowId, title, body);
				else
					mDbHelper.createDiary(title, body);
				Intent intent=new Intent();
				setResult(RESULT_OK,intent);
				finish();
				
						
			}
		});
		
	}
	
	
}
