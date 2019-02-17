package cv.bumi.nusantara.icerequester.utils;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import cv.bumi.nusantara.icerequester.beans.Item;
import cv.bumi.nusantara.icerequester.beans.Pesan;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Requester extends android.os.AsyncTask<Item, Void, Void> {
	private Context ctx;
	private Button btn;
	private ProgressBar prog;
	private com.google.gson.Gson g;
	private RequestQueue rq;
	private String url;

	public Requester(Context ctx, Button btn, ProgressBar prog) {
		super();
		this.ctx = ctx;
		this.btn = btn;
		this.prog = prog;
		g = new com.google.gson.Gson();
		rq = Volley.newRequestQueue(ctx);
		url = "";
	}

	@Override
	protected void onPreExecute() {
		btn.setEnabled(false);
		prog.setVisibility(View.VISIBLE);
	}

	@Override
	protected Void doInBackground(Item... params) {
		if (0 < params.length) {
			Item i = params[0]; try {
				JSONObject req = new JSONObject(g.toJson(i));
				JsonObjectRequest r = new JsonObjectRequest(Request.Method.POST, url, req, 
						new Response.Listener<JSONObject>() {
							@Override
							public void onResponse(JSONObject o) {
								Pesan p = g.fromJson(o.toString(), Pesan.class);
								Toast.makeText(ctx, p.getMsg(), Toast.LENGTH_LONG).show();
							}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError e) {
						Log.i("IceRequester", e.getMessage());
					}
				}); rq.add(r);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		prog.setVisibility(View.GONE);
	}
}
