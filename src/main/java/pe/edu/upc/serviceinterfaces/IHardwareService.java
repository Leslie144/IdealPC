package pe.edu.upc.serviceinterfaces;

import java.util.List;

import pe.edu.upc.entities.Hardwares;

public interface IHardwareService {

	public boolean insert(Hardwares hardware);
	List<Hardwares>list();
	Hardwares listarId(int idHardware);
	public void delete(int idHardware);
	List<Hardwares> findBynombreHardware(String name);
}
