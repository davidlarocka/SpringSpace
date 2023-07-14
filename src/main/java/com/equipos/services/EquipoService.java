package com.equipos.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.equipos.models.EquipoModel;

import lombok.Data;

@Service
@Primary
@Data
public class EquipoService implements EquipoInterface{

	private ArrayList<EquipoModel> equipos;
	
	public EquipoService(){
		
		equipos = new ArrayList<>( 
				List.of( 
						new EquipoModel(1, "Arg", "CONMEBOL"),
						new EquipoModel(2, "MAR", "CAF"),
						new EquipoModel(3, "VEN", "CONMEBOL"),
						new EquipoModel(4, "ESP", "UEFA")
				)
			);
	}
	
	public ArrayList<EquipoModel>  getAll() {
		return equipos;
	}
	
}
