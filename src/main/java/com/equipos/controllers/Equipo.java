package com.equipos.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.equipos.models.EquipoModel;
import com.equipos.services.EquipoInterface;

@RestController
@RequestMapping("/teams")
public class Equipo {
	
	@Autowired	
	private EquipoInterface equipos;
	
	
	@GetMapping
	public ResponseEntity<List<EquipoModel>>  getAllTeam() {
		return ResponseEntity.ok(equipos.getAll()) ;
	}
	
	@GetMapping("/{idTeam}")
	public ResponseEntity<EquipoModel>  getTeamById(@PathVariable String idTeam) {
		Optional<EquipoModel> team = equipos.getAll().stream().filter(e -> String.valueOf(e.id_team).equalsIgnoreCase(idTeam)).findFirst();
		if(team.isPresent()) {
			return ResponseEntity.ok(team.get());
		}
		return ResponseEntity.notFound().build();	
	}
	
	@PostMapping
	public ResponseEntity newTeam(@RequestBody EquipoModel team) {
		equipos.getAll().add(team);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id_team}")
				.buildAndExpand(String.valueOf(team.id_team))
				.toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	@PutMapping("/{id_team}")
	public ResponseEntity editTeam(@RequestBody EquipoModel teamEdit) {
		for(EquipoModel team : equipos.getAll()) {
			if( String.valueOf(team.id_team).equalsIgnoreCase( String.valueOf(teamEdit.id_team) ) ) {
				team.name = teamEdit.name;
				ResponseEntity.ok(team);
			} 
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity deleteTeamById(@PathVariable String id) {
		if(equipos.getAll().stream().filter(eq -> String.valueOf(eq.id_team).equalsIgnoreCase(id)  ).findFirst().isPresent()){
			equipos.getAll().remove(equipos.getAll().stream().filter(eq -> String.valueOf(eq.id_team).equalsIgnoreCase(id)  ).findFirst().get());
		}else {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
			
	}
	
}
