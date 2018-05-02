package com.util;

public class MathYz {
	int a;

	int b;
	int result;
	int methodindex;
	String[] method = { "+", "-" };

	public static MathYz getMathYz() {
		MathYz instance = new MathYz();
		instance.a = (int) (Math.random() * 10);
		instance.methodindex = (int) (Math.random() * 2);
		instance.b = (int) (Math.random() * 10);
		if (instance.methodindex == 0) {
			instance.result = instance.a + instance.b;
		}
		if (instance.methodindex == 1) {

			instance.result = instance.a - instance.b;

		}
		if (instance.result >= 10) {
			instance.result = (int) (Math.random() * 4) + 5;
			instance.b = 3;
			instance.a = instance.result - instance.b;
		} else if (instance.result < 0) {
			int a = instance.a;
			instance.a = instance.b;
			instance.b = a;
			instance.result = instance.a - instance.b;
		}
		return instance;
	}

	@Override
	public String toString() {
		return "" + a + method[methodindex] + "" + b + "=" + result;
	}

	public static void main(String[] s) {
		System.out.println(MathYz.getMathYz());
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public int getMethodindex() {
		return methodindex;
	}

	public void setMethodindex(int methodindex) {
		this.methodindex = methodindex;
	}

	public String[] getMethod() {
		return method;
	}

	public void setMethod(String[] method) {
		this.method = method;
	}
	
}
