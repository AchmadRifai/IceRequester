package cv.bumi.nusantara.icerequester.beans;

import cv.bumi.nusantara.icerequester.utils.Pemilik;

public class Item {
	private Pemilik p;
	private int jum;
	private String satuan;

	public Item() {
		super();
	}

	public Pemilik getP() {
		return p;
	}

	public void setP(Pemilik p) {
		this.p = p;
	}

	public int getJum() {
		return jum;
	}

	public void setJum(int jum) {
		this.jum = jum;
	}

	public String getSatuan() {
		return satuan;
	}

	public void setSatuan(String satuan) {
		this.satuan = satuan;
	}
}
