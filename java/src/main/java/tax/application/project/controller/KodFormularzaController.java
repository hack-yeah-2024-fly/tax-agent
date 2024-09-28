package tax.application.project.controller;

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
import tax.application.project.model.KodFormularza;
import tax.application.project.repository.KodFormularzaRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class KodFormularzaController {

    @Autowired
    KodFormularzaRepository kodFormularzaRepository;

    @GetMapping("/kodyformularza")
    public List<KodFormularza> getAllKodyFormularza() {
        return kodFormularzaRepository.findAll();
    }

    @GetMapping("/kodyformularza/{id}")
    public ResponseEntity<KodFormularza> getKodFormularzaById(@PathVariable(value = "id") Long kodFormularzaId) {
        KodFormularza kodFormularza = kodFormularzaRepository.findById(kodFormularzaId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono kodu formularza o podanym id :: " + kodFormularzaId));
        return ResponseEntity.ok().body(kodFormularza);
    }

    @PostMapping("/kodyformularza")
    public KodFormularza createKodFormularza(@Valid @RequestBody KodFormularza kodFormularza) {
        return kodFormularzaRepository.save(kodFormularza);
    }

    @PutMapping("/kodyformularza/{id}")
    public ResponseEntity <KodFormularza> updateKodFormularza(@PathVariable(value = "id") Long kodFormularzaId,
                                                              @Valid @RequestBody KodFormularza kodFormularzaDetails) {
        KodFormularza kodFormularza = kodFormularzaRepository.findById(kodFormularzaId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono kodu formularza o podanym id :: " + kodFormularzaId));

        kodFormularza.setKodSystemowy(kodFormularzaDetails.getKodSystemowy());
        kodFormularza.setKodPodatku(kodFormularzaDetails.getKodPodatku());
        kodFormularza.setRodzajZobowiazania(kodFormularzaDetails.getRodzajZobowiazania());
        kodFormularza.setWersjaSchemy(kodFormularzaDetails.getWersjaSchemy());
        final KodFormularza updatedKodFormularza = kodFormularzaRepository.save(kodFormularza);
        return ResponseEntity.ok(updatedKodFormularza);
    }

    @DeleteMapping("/kodyformularza/{id}")
    public Map< String, Boolean > deleteKodFormularza(@PathVariable(value = "id") Long kodFormularzaId) {
        KodFormularza kodFormularza = kodFormularzaRepository.findById(kodFormularzaId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono kodu formularza o podanym id :: " + kodFormularzaId));

        kodFormularzaRepository.delete(kodFormularza);
        Map < String, Boolean > response = new HashMap< >();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
