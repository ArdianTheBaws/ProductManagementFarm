package controller;


public class GetSetData {
	public String getKategori() {
		return kategori;
	}

	public String getNavn() {
		return navn;
	}

	private final String kategori;

	public GetSetData(String kategori, String navn) {
		this.kategori = kategori;
		this.navn = navn;
	}

	private final String navn;
}
