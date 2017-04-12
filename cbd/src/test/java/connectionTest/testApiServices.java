package connectionTest;

import util.ApiServices;

public class testApiServices {

	public static void main(String[] args) {
		
		String zona = "Sevilla";
		int numDias = 4;
		int diasAnteriores = 2;
		String path = "c:\\Users\\Javier\\Desktop\\";
		String nombreFichero = "test_6.json";
		
		System.out.println("--------------------------- Test API 1 ---------------------------");
		System.out.println(ApiServices.tiempoActual(zona));
		System.out.println("------------------------------------------------------------------");
		System.out.println("--------------------------- Test API 2 ---------------------------");
		System.out.println(ApiServices.prevision(zona));
		System.out.println("------------------------------------------------------------------");
		System.out.println("--------------------------- Test API 3.1 -------------------------");
		System.out.println(ApiServices.prevision(zona, numDias));
		System.out.println("------------------------------------------------------------------");
		System.out.println("--------------------------- Test API 3.2 -------------------------");
		System.out.println(ApiServices.prevision(zona, numDias-numDias));
		System.out.println("------------------------------------------------------------------");
		System.out.println("--------------------------- Test API 4.1 -------------------------");
		System.out.println(ApiServices.tiempoNDiasPasados(zona, diasAnteriores));
		System.out.println("------------------------------------------------------------------");
		System.out.println("--------------------------- Test API 4.2 -------------------------");
		System.out.println(ApiServices.tiempoNDiasPasados(zona, diasAnteriores+5));
		System.out.println("------------------------------------------------------------------");
		System.out.println("--------------------------- Test API 5.1 -------------------------");
		System.out.println(ApiServices.fechaPasadaExacta(zona, "2017-04-05"));
		System.out.println("------------------------------------------------------------------");
		System.out.println("--------------------------- Test API 6 -------------------------");
		System.out.println("Fichero guardado en: " + path+nombreFichero);
		ApiServices.guardarEnJson(path,nombreFichero,ApiServices.tiempoActual(zona));
		System.out.println("------------------------------------------------------------------");
	}

}
