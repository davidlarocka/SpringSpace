package com.equipos.models;

import io.micrometer.common.lang.NonNull;
import lombok.Data;


@Data
public class EquipoModel {

	@NonNull
	public int id_team;
	@NonNull
	public String name;
	@NonNull
	private String confederation;
	
	public EquipoModel(int id_team, String name, String confederation) {
		super();
		this.id_team = id_team;
		this.name = name;
		this.confederation = confederation;
	}
	
}
