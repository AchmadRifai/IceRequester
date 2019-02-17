package cv.bumi.nusantara.icerequester.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConnHelper extends SQLiteOpenHelper {

	public ConnHelper(Context context) {
		super(context, "IceRequester", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table pemilik(an text not null, almt text not null, telp text not null)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int o, int n) {
		if (n > o) {
			db.execSQL("drop table if exists pemilik");
			onCreate(db);
		}
	}

	public Pemilik getPemilik() {
		Pemilik p = null;
		SQLiteDatabase d = getReadableDatabase();
		Cursor c = d.query("pemilik", null, null, null, null, null, null);
		if (c.moveToFirst()) {
			p = new Pemilik();
			p.setAlmt(c.getString(c.getColumnIndex("almt")));
			p.setAn(c.getString(c.getColumnIndex("an")));
			p.setTelp(c.getString(c.getColumnIndex("telp")));
		} c.close();
		d.close();
		return p;
	}

	public void addPemilik(Pemilik p) {
		SQLiteDatabase d = getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("an", p.getAn());
		cv.put("almt", p.getAlmt());
		cv.put("telp", p.getTelp());
		d.insert("pemilik", null, cv);
		d.close();
	}
}
