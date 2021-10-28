package pe.edu.upc.serviceimplements;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.TipoPanel;
import pe.edu.upc.repositories.ITipoPanelRepository;
import pe.edu.upc.serviceinterfaces.ITipoPanelService;

@Service
public class TipoPanelServiceImplement implements ITipoPanelService {

	@Autowired
	private ITipoPanelRepository tpR;
	
	@Override
	public Integer insert(TipoPanel tipopanel) {
		int rpta = tpR.TiposPanelExistentes(tipopanel.getnTipoPanel());
		if(rpta ==0) {
			tpR.save(tipopanel);
		}
		return rpta;
	}
		@Override
		public List<TipoPanel> list(){
			return tpR.findAll();
		}
		
	}
	
