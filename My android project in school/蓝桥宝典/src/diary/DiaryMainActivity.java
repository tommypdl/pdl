package diary;

import com.qsa.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class DiaryMainActivity extends ListActivity {
	private static final int INSERT_ID=Menu.FIRST;
	private static final int DELETE_ID=Menu.FIRST+1;
	private static final int ACTIVITY_CREATE=0;
	private static final int ACTIVITY_EDIT=1;
	private DiaryDbAdapter mDbHelper;
	private Cursor mDiaryCursor;
	public int flag=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.diary_list);
		setTitle("»’º«±æ");
		mDbHelper=new DiaryDbAdapter(this);
		mDbHelper.open();
		renderListView();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		 super.onCreateOptionsMenu(menu);
		 menu.add(0,INSERT_ID,0,R.string.menu_insert);
		 menu.add(0,DELETE_ID,0,R.string.menu_delete);
		 return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case INSERT_ID:
			createDiary();
			return true;
		
		case DELETE_ID:
			//mDbHelper.deleteDiary(getListView().getSelectedItemId());
			flag=1;
			return true;
		
	}
		return super.onMenuItemSelected(featureId, item);
	}
	private void renderListView(){
		mDiaryCursor=mDbHelper.getAllNotes();
		startManagingCursor(mDiaryCursor);
		String[] from=new String[]{DiaryDbAdapter.KEY_TITLE,DiaryDbAdapter.KEY_CREATED};
		int[] to=new int[]{R.id.text1,R.id.created};
		SimpleCursorAdapter notes=new SimpleCursorAdapter(this,R.layout.diary_row,mDiaryCursor,from,to);
		
		setListAdapter(notes);
	}
    private void createDiary(){
    	Intent i=new Intent(this,ActivityDiaryEdit.class);
    	startActivityForResult(i, ACTIVITY_CREATE);
    	
    }

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		renderListView();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		if(flag==0){
		Cursor c=mDiaryCursor;
		c.moveToPosition(position);
		Intent i=new Intent(this,ActivityDiaryEdit.class);
		i.putExtra(DiaryDbAdapter.KEY_ROWID, id);
		i.putExtra(DiaryDbAdapter.KEY_TITLE, c.getString(c.getColumnIndexOrThrow(DiaryDbAdapter.KEY_TITLE)));
		i.putExtra(DiaryDbAdapter.KEY_BODY, c.getString(c.getColumnIndexOrThrow(DiaryDbAdapter.KEY_BODY)));
		startActivityForResult(i, ACTIVITY_EDIT);
		}
		if(flag==1){
			mDbHelper.deleteDiary(id);
			flag=0;
			renderListView();
		}
	}
    
}
