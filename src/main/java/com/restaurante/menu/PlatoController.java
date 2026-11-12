package com.restaurante.menu;

import jakarta.validation.Valid;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/platos")
public class PlatoController {

    // ponytail: datos en memoria, se pierden al reiniciar; usar JPA + BD si se necesita persistir
    private final List<Plato> platos = new CopyOnWriteArrayList<>();
    private final AtomicLong secuencia = new AtomicLong();

    public PlatoController() {
        guardar(new Plato(null, "Lomo saltado", "Fondo", 32.0, true));
        guardar(new Plato(null, "Ceviche", "Entrada", 28.5, true));
    }

    @GetMapping
    public List<Plato> listar() {
        return platos;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plato> buscar(@PathVariable Long id) {
        return platos.stream()
                .filter(p -> p.id().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Plato> crear(@Valid @RequestBody Plato plato) {
        return ResponseEntity.status(HttpStatus.CREATED).body(guardar(plato));
    }

    private Plato guardar(Plato p) {
        Plato nuevo = new Plato(secuencia.incrementAndGet(), p.nombre(), p.categoria(), p.precio(), p.disponible());
        platos.add(nuevo);
        return nuevo;
    }
}
