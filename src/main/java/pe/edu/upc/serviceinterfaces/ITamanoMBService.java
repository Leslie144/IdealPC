package pe.edu.upc.serviceinterfaces;

import java.util.List;

import pe.edu.upc.entities.TamanoMB;

public interface ITamanoMBService {

	public Integer insert (TamanoMB tamanomb);
	
	List<TamanoMB> list();
}
