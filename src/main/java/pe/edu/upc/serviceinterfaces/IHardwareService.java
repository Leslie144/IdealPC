package pe.edu.upc.serviceinterfaces;

import java.util.List;

import pe.edu.upc.entities.Hardware;

public interface IHardwareService {

	public boolean insert(Hardware hardware);
	List<Hardware>list();
	Hardware listarId(int idHardware);
	public void delete(int idHardware);
	List<Hardware> findBynombreHardware(String name);
}
