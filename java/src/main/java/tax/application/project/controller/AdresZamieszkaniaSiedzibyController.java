package tax.application.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import tax.application.project.model.AdresZamieszkaniaSiedziby;
import tax.application.project.repository.AdresZamieszkaniaSiedzibyRepository;
import javax.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class AdresZamieszkaniaSiedzibyController {

    @Autowired
    AdresZamieszkaniaSiedzibyRepository adresZamieszkaniaSiedzibyRepository;

    @GetMapping("/adresyzamieszkaniasiedziby")
    public List<AdresZamieszkaniaSiedziby> getAllAdresyZamieszkaniaSiedziby() {
        return adresZamieszkaniaSiedzibyRepository.findAll();
    }

    @GetMapping("/adresyzamieszkaniasiedziby/{id}")
    public ResponseEntity<AdresZamieszkaniaSiedziby> getAdresZamieszkaniaSiedzibyById(@PathVariable(value = "id") Long adresZamieszkaniaSiedzibyId) {
        AdresZamieszkaniaSiedziby adresZamieszkaniaSiedziby = adresZamieszkaniaSiedzibyRepository.findById(adresZamieszkaniaSiedzibyId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono adresu zamieszkania siedziby o podanym id :: " + adresZamieszkaniaSiedzibyId));
        return ResponseEntity.ok().body(adresZamieszkaniaSiedziby);
    }

    @PostMapping("/adresyzamieszkaniasiedziby")
    public AdresZamieszkaniaSiedziby createAdresZamieszkaniaSiedziby(@Valid @RequestBody AdresZamieszkaniaSiedziby adresZamieszkaniaSiedziby) {
        return adresZamieszkaniaSiedzibyRepository.save(adresZamieszkaniaSiedziby);
    }

    @PutMapping("/adresyzamieszkaniasiedziby/{id}")
    public ResponseEntity <AdresZamieszkaniaSiedziby> updateAdresZamieszkaniaSiedziby(@PathVariable(value = "id") Long adresZamieszkaniaSiedzibyId,
                                                                                      @Valid @RequestBody AdresZamieszkaniaSiedziby adresZamieszkaniaSiedzibyDetails) {
        AdresZamieszkaniaSiedziby adresZamieszkaniaSiedziby = adresZamieszkaniaSiedzibyRepository.findById(adresZamieszkaniaSiedzibyId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono adresu o podanym id :: " + adresZamieszkaniaSiedzibyId));

        adresZamieszkaniaSiedziby.setRodzajAdresu(adresZamieszkaniaSiedzibyDetails.getRodzajAdresu());
        final AdresZamieszkaniaSiedziby updatedAdresZamieszkaniaSiedziby = adresZamieszkaniaSiedzibyRepository.save(adresZamieszkaniaSiedziby);
        return ResponseEntity.ok(updatedAdresZamieszkaniaSiedziby);
    }

    @DeleteMapping("/adresyzamieszkaniasiedziby/{id}")
    public Map< String, Boolean > deleteAdresZamieszkaniaSiedziby(@PathVariable(value = "id") Long adresZamieszkaniaSiedzibyId) {
        AdresZamieszkaniaSiedziby adresZamieszkaniaSiedziby = adresZamieszkaniaSiedzibyRepository.findById(adresZamieszkaniaSiedzibyId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono adresu o podanym id :: " + adresZamieszkaniaSiedzibyId));

        adresZamieszkaniaSiedzibyRepository.delete(adresZamieszkaniaSiedziby);
        Map < String, Boolean > response = new HashMap< >();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
