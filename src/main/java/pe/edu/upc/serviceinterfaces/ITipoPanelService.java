package pe.edu.upc.serviceinterfaces;

import java.util.List;

import pe.edu.upc.entities.TipoPanel;

public interface ITipoPanelService {

		public Integer insert(TipoPanel tipopanel);
		
		List<TipoPanel> list();		
}
