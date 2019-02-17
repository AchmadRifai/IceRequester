package cv.bumi.nusantara.icerequester;

import cv.bumi.nusantara.icerequester.utils.ConnHelper;
import cv.bumi.nusantara.icerequester.utils.Pemilik;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegActivity extends Activity {
	private EditText nama, address, phone;
	private Button btn;
	private ConnHelper ch;
	private Pemilik p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inisial();
        setContentView(R.layout.activity_reg);
        binding();
        evene();
    }

	@Override
	public void onBackPressed() {
		AlertDialog.Builder b = new AlertDialog.Builder(this);
		b.setMessage("Do you want to quit?").setTitle("Quiting?");
		b.setNegativeButton("No", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		}).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				exiting();
				System.exit(0);
			}
		});
		b.create().show();
	}

	private void evene() {
		phone.setOnKeyListener(new View.OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				return validasiForm();
			}
		});
		address.setOnKeyListener(new View.OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				return validasiForm();
			}
		});
		nama.setOnKeyListener(new View.OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				return validasiForm();
			}
		});
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				saving();
			}
		});
	}

	private boolean validasiForm() {
		String an = nama.getText().toString(), almt = address.getText().toString(), 
				telp = phone.getText().toString();
		boolean b = ! an.isEmpty() && ! almt.isEmpty() && ! telp.isEmpty();
		btn.setEnabled(b);
		return false;
	}

	private void saving() {
		p = new Pemilik();
		p.setAlmt(address.getText().toString());
		p.setAn(nama.getText().toString());
		p.setTelp(phone.getText().toString());
		ch.addPemilik(p);
		langsung();
	}

	private void binding() {
		nama = (EditText) findViewById(R.id.an);
		address = (EditText) findViewById(R.id.almt);
		phone = (EditText) findViewById(R.id.telp);
		btn = (Button) findViewById(R.id.regNow);
	}

	private void inisial() {
		ch = new ConnHelper(this);
		p = ch.getPemilik();
		if (p != null)
			langsung();
	}

	private void langsung() {
		startActivity(new Intent(this, ReqActivity.class));
		exiting();
	}

	private void exiting() {
		ch.close();
		finish();
	}
}
