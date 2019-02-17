package cv.bumi.nusantara.icerequester;

import cv.bumi.nusantara.icerequester.beans.Item;
import cv.bumi.nusantara.icerequester.utils.ConnHelper;
import cv.bumi.nusantara.icerequester.utils.Pemilik;
import cv.bumi.nusantara.icerequester.utils.Requester;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

public class ReqActivity extends Activity {
	private Spinner sat;
	private ConnHelper ch;
	private ImageButton min, add;
	private Pemilik p;
	private TextView jum;
	private Button btn;
	private ProgressBar prog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		inisial();
		setContentView(R.layout.activity_req);
		binding();
		validasiForm();
		evene();
	}

	private void evene() {
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				pesanSekarang();
			}
		});
		add.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				lan();
			}
		});
		min.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				sudo();
			}
		});
		sat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				validasiForm();
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				validasiForm();
			}
		});
	}

	private void pesanSekarang() {
		Item i = new Item();
		i.setJum(Integer.parseInt("" + jum.getText()));
		i.setP(p);
		i.setSatuan("" + sat.getSelectedItem());
		Requester r = new Requester(this, btn, prog);
		r.execute(i);
	}

	private void lan() {
		String s = "" + jum.getText();
		int i = Integer.parseInt(s);
		i += 1;
		jum.setText("" + i);
		validasiForm();
	}

	private void sudo() {
		String s = "" + jum.getText();
		int i = Integer.parseInt(s);
		i -= 1;
		jum.setText("" + i);
		validasiForm();
	}

	private void validasiForm() {
		String s = "" + jum.getText();
		int i = Integer.parseInt(s);
		boolean b = 0 < i;
		btn.setEnabled(b);
		min.setEnabled(b);
	}

	private void binding() {
		prog = (ProgressBar) findViewById(R.id.prog);
		sat = (Spinner) findViewById(R.id.satuan);
		min = (ImageButton) findViewById(R.id.min);
		add = (ImageButton) findViewById(R.id.add);
		btn = (Button) findViewById(R.id.reqNow);
		jum = (TextView) findViewById(R.id.jum);
	}

	private void inisial() {
		ch = new ConnHelper(this);
		p = ch.getPemilik();
		if (p == null)
			balik();
	}

	private void balik() {
		startActivity(new Intent(this, RegActivity.class));
		exiting();
	}

	private void exiting() {
		ch.close();
		finish();
	}
}
