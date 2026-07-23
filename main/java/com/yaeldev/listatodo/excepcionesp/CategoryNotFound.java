package com.yaeldev.listatodo.excepcionesp;

public class CategoryNotFound extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CategoryNotFound(String mensaje) {
		super(mensaje);
	}
}
