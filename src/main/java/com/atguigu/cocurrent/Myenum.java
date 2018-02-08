package com.atguigu.cocurrent;

public enum Myenum {
	SPRING(1, "春天"), SUMMER(2, "夏天"),AUTUMN(3,"秋天"),WINTER(4,"冬天");

	private int num;

	private String coutry;

	private Myenum(int num, String coutry) {
		this.num = num;
		this.coutry = coutry;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getCoutry() {
		return coutry;
	}

	public void setCoutry(String coutry) {
		this.coutry = coutry;
	}

	private static Myenum n = null;

	public static Myenum getCountry(int num) {
		for (Myenum i : values()) {
			if (i.getNum() == num) {
				n=i;
			}
		}
		return n;
	}

}


